/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.engine.security;

import java.util.Collection;

/**
 * 
 *
 * @author kirk
 */
public interface Security {

  /**
   * Obtain all the security identifiers which are part of this
   * {@code Security}'s description.
   * 
   * @return All identifiers for this security.
   */
  Collection<SecurityIdentifier> getIdentifiers();
}
