/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial;

import org.apache.commons.lang.ObjectUtils;

import com.opengamma.id.IdentifierBundle;
import com.opengamma.id.UniqueIdentifier;

/**
 * Represents an exchange on which financial products can be traded or settled.
 * Use an ExchangeRepository to get information about the exchange.
 */
public class Exchange {
  private IdentifierBundle _identifiers;
  private UniqueIdentifier _uniqueId;
  private String _name;
  private UniqueIdentifier _regionId;
  
  /**
   * Resolved constructor - to be invoked only by the ExchangeRepository
   * @param identifiers a bundle of identifiers for this exchange
   * @param name the name of the exchange, purely for display/debugging purposes
   * @param region the region that the exchange is located in, or null if not applicable (e.g. dark pool, electronic)
   */
  /*package*/ Exchange(IdentifierBundle identifiers, String name, UniqueIdentifier regionId) {
    _identifiers = identifiers;
    _uniqueId = null; // not assigned until later
    _name = name;
    _regionId = regionId;
  }
  
  /**
   * Get the identifier bundle
   * @return the identifier bundle for this exchange
   */
  public IdentifierBundle getIdentifiers() {
    return _identifiers;
  }
  
  /**
   * @param identifiers
   */
  /*package*/ void setIdentifiers(IdentifierBundle identifiers) { 
    _identifiers = identifiers;
  }
  
  /**
   * Get the unique identifier
   * @return the unique identifier for this exchange
   */
  public UniqueIdentifier getUniqueIdentifier() {
    return _uniqueId;
  }
  
  /*package*/ void setUniqueIdentifier(UniqueIdentifier uniqueId) {
    _uniqueId = uniqueId;
  }
  
  /**
   * Get the name - this is a reasonable display name e.g. NASDAQ Global Select.  It should
   * not be used for anything other than display purposes.
   * @return the display name
   */
  public String getName() {
    return _name;
  }
  
  /**
   * Get the region this exchange is in.  For electronic exchanges/dark pools etc. 
   * this may be null.
   * @return the region if available, otherwise null
   */
  public UniqueIdentifier getRegion() {
    return _regionId;
  }
    
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (!(o instanceof Exchange)) {
      return false;
    }
    Exchange other = (Exchange) o;
    return (ObjectUtils.equals(other.getUniqueIdentifier(), getUniqueIdentifier()));
  }
  
  public int hashCode() {
    return getIdentifiers().hashCode();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Exchange[");
    sb.append(_name);
    sb.append(" in ");
    sb.append(_regionId);
    sb.append("(uid:");
    sb.append(_uniqueId);
    sb.append(")");
    return sb.toString();
  }

}
