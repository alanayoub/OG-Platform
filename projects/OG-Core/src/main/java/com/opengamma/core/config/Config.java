/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for classes that represents a configuration item intended to be stored in a {@link ConfigMaster}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Config {
  
  /**
   * The class type to use for searching for the configuration item in a {@link ConfigMaster}.
   * <p>
   * Optional search type when it is different from base class
   */
  Class<?> searchType() default Object.class;
}
