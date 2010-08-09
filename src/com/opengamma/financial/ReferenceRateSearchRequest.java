/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial;

import com.opengamma.id.Identifier;
import com.opengamma.id.IdentifierBundle;

/**
 * A search request to retrieve reference rate information.
 */
public class ReferenceRateSearchRequest {
  private IdentifierBundle _identifiers;
  
  public ReferenceRateSearchRequest(Identifier identifier) {
    _identifiers = IdentifierBundle.of(identifier);
  }
  
  public ReferenceRateSearchRequest(IdentifierBundle identifiers) {
    _identifiers = identifiers;
  }
  
  public IdentifierBundle getIdentifiers() {
    return _identifiers;
  }
}
