/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.livedata.server;

import org.fudgemsg.FudgeFieldContainer;
import org.fudgemsg.FudgeMsgEnvelope;
import org.fudgemsg.mapping.FudgeDeserializationContext;
import org.fudgemsg.mapping.FudgeSerializationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.livedata.EntitlementRequest;
import com.opengamma.livedata.EntitlementResponse;
import com.opengamma.livedata.client.LiveDataEntitlementChecker;
import com.opengamma.transport.FudgeRequestReceiver;
import com.opengamma.util.ArgumentChecker;

/**
 * Receives <code>EntitlementRequests</code>, passes them onto a delegate <code>LiveDataEntitlementChecker</code>,
 * and returns <code>EntitlementResponses</code>. 
 *
 * @author pietari
 */
public class EntitlementCheckerServer implements FudgeRequestReceiver {
  
  private static final Logger s_logger = LoggerFactory.getLogger(EntitlementCheckerServer.class);
  private final LiveDataEntitlementChecker _delegate;
  
  public EntitlementCheckerServer(LiveDataEntitlementChecker delegate) {
    ArgumentChecker.checkNotNull(delegate, "Delegate entitlement checker");
    _delegate = delegate;
  }
  
  @Override
  public FudgeFieldContainer requestReceived(FudgeDeserializationContext context, FudgeMsgEnvelope requestEnvelope) {
    FudgeFieldContainer requestFudgeMsg = requestEnvelope.getMessage();
    EntitlementRequest entitlementRequest = EntitlementRequest.fromFudgeMsg(context, requestFudgeMsg);
    s_logger.debug("Received entitlement request from {} for {}", entitlementRequest.getUserName(), entitlementRequest.getLiveDataSpecification());
    
    boolean isEntitled = _delegate.isEntitled(entitlementRequest.getUserName(), entitlementRequest.getLiveDataSpecification());
    
    EntitlementResponse response = new EntitlementResponse(isEntitled);
    FudgeFieldContainer responseFudgeMsg = response.toFudgeMsg(new FudgeSerializationContext(context.getFudgeContext()));
    return responseFudgeMsg;
  }
  
}
