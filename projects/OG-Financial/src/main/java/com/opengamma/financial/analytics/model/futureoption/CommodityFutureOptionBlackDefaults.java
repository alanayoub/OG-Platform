/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.futureoption;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.opengamma.core.security.Security;
import com.opengamma.engine.ComputationTarget;
import com.opengamma.engine.ComputationTargetType;
import com.opengamma.engine.function.FunctionCompilationContext;
import com.opengamma.engine.value.ValuePropertyNames;
import com.opengamma.engine.value.ValueRequirement;
import com.opengamma.engine.value.ValueRequirementNames;
import com.opengamma.financial.property.DefaultPropertyFunction;
import com.opengamma.financial.security.FinancialSecurityUtils;
import com.opengamma.financial.security.option.CommodityFutureOptionSecurity;
import com.opengamma.util.ArgumentChecker;

/**
 *
 */
public class CommodityFutureOptionBlackDefaults extends DefaultPropertyFunction {
  private static final Logger s_logger = LoggerFactory.getLogger(CommodityFutureOptionBlackDefaults.class);
  private static final String[] VALUE_REQUIREMENTS = new String[] {
    ValueRequirementNames.PRESENT_VALUE
  };
  private final Map<String, String> _currencyToCurveName;
  private final Map<String, String> _currencyToCurveCalculationConfigName;
  private final Map<String, String> _currencyToSurfaceName;

  public CommodityFutureOptionBlackDefaults(final String... defaultsPerCurrency) {
    super(ComputationTargetType.SECURITY, true);
    ArgumentChecker.notNull(defaultsPerCurrency, "defaults per currency");
    final int n = defaultsPerCurrency.length;
    ArgumentChecker.isTrue(n % 4 == 0, "Need one discounting curve name, discounting curve calculation config and surface name per currency");
    _currencyToCurveName = Maps.newLinkedHashMap();
    _currencyToCurveCalculationConfigName = Maps.newLinkedHashMap();
    _currencyToSurfaceName = Maps.newLinkedHashMap();
    for (int i = 0; i < n; i += 4) {
      final String currencyPair = defaultsPerCurrency[i];
      _currencyToCurveName.put(currencyPair, defaultsPerCurrency[i + 1]);
      _currencyToCurveCalculationConfigName.put(currencyPair, defaultsPerCurrency[i + 2]);
      _currencyToSurfaceName.put(currencyPair, defaultsPerCurrency[i + 3]);
    }
  }

  @Override
  public boolean canApplyTo(final FunctionCompilationContext context, final ComputationTarget target) {
    if (target.getType() != ComputationTargetType.SECURITY) {
      return false;
    }
    final Security security = target.getSecurity();
    if (!(security instanceof CommodityFutureOptionSecurity)) {
      return false;
    }
    final String currency = ((CommodityFutureOptionSecurity) security).getCurrency().getCode();
    return getAllCurrencies().contains(currency);
  }

  @Override
  protected void getDefaults(final PropertyDefaults defaults) {
    for (final String valueRequirement : VALUE_REQUIREMENTS) {
      defaults.addValuePropertyName(valueRequirement, ValuePropertyNames.CURVE);
      defaults.addValuePropertyName(valueRequirement, ValuePropertyNames.CURVE_CALCULATION_CONFIG);
      defaults.addValuePropertyName(valueRequirement, ValuePropertyNames.SURFACE);
    }
  }

  @Override
  protected Set<String> getDefaultValue(final FunctionCompilationContext context, final ComputationTarget target, final ValueRequirement desiredValue, final String propertyName) {
    final String currencyPair = FinancialSecurityUtils.getCurrency(target.getSecurity()).getCode();
    final String curveName = _currencyToCurveName.get(currencyPair);
    if (curveName == null) {
      s_logger.error("Could not get curve name for {}; should never happen", target.getValue());
      return null;
    }
    if (ValuePropertyNames.CURVE.equals(propertyName)) {
      return Collections.singleton(curveName);
    }
    if (ValuePropertyNames.CURVE_CALCULATION_CONFIG.equals(propertyName)) {
      return Collections.singleton(_currencyToCurveCalculationConfigName.get(currencyPair));
    }
    if (ValuePropertyNames.SURFACE.equals(propertyName)) {
      return Collections.singleton(_currencyToSurfaceName.get(currencyPair));
    }
    s_logger.error("Could not find default value for {} in this function", propertyName);
    return null;
  }

  protected Collection<String> getAllCurrencies() {
    return _currencyToCurveName.keySet();
  }

//  @Override
//  public String getMutualExclusionGroup() {
//    return OpenGammaFunctionExclusions.COMMODITY_BLACK_VOLATILITY_SURFACE_DEFAULTS;
//  }

}
