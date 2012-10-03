/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.highlevelapi.datatypes.derived.OGSvdResult;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;

/**
 * Overloaded Svd
 */
public class Svd {

  /**
  *
  */
  public static enum compute {
    /**
    * U compute U
    */
    U,
    /**
    * S compute S
    */
    S,
    /**
    * V compute V
    */
    V,
    /**
    * US compute U and S
    */    
    US,
    /**
    * UV compute U and V
    */    
    UV,
    /**
    * V compute S and V
    */    
    SV,
    /**
    *  compute U, S and V
    */
    USV
  }

  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, SvdAbstract<?>> s_functionPointers = new HashMap<Class<?>, SvdAbstract<?>>();
  static {
    s_functionPointers.put(OGDoubleArray.class, SvdOGDoubleArray.getInstance());
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<Number>> OGSvdResult svd(T array1, compute these) {
    SvdAbstract<T> use = (SvdAbstract<T>) s_functionPointers.get(array1.getClass());
    if (use == null) {
      throw new MathsExceptionNotImplemented("svd() on array class " + array1.getClass().toString() + " is not yet implemented");
    }
    return use.svd(array1, these);
  }

}
