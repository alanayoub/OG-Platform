/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.volatility.surface.black.defaultproperties;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.opengamma.engine.ComputationTarget;
import com.opengamma.engine.ComputationTargetType;
import com.opengamma.engine.function.FunctionCompilationContext;
import com.opengamma.engine.value.ValuePropertyNames;
import com.opengamma.engine.value.ValueRequirement;
import com.opengamma.engine.value.ValueRequirementNames;
import com.opengamma.financial.property.DefaultPropertyFunction;
import com.opengamma.id.UniqueId;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.money.UnorderedCurrencyPair;

/**
 *
 */
public class FXBlackVolatilitySurfaceDefaults extends DefaultPropertyFunction {
  private static final Logger s_logger = LoggerFactory.getLogger(FXBlackVolatilitySurfaceDefaults.class);
  private static final String[] VALUE_REQUIREMENTS = new String[] {
    ValueRequirementNames.BLACK_VOLATILITY_SURFACE,
    ValueRequirementNames.LOCAL_VOLATILITY_SURFACE,
    ValueRequirementNames.FORWARD_DELTA,
    ValueRequirementNames.DUAL_DELTA,
    ValueRequirementNames.DUAL_GAMMA,
    ValueRequirementNames.FORWARD_GAMMA,
    ValueRequirementNames.FOREX_DOMESTIC_PRICE,
    ValueRequirementNames.FOREX_PV_QUOTES,
    ValueRequirementNames.FORWARD_VEGA,
    ValueRequirementNames.FORWARD_VOMMA,
    ValueRequirementNames.FORWARD_VANNA,
    ValueRequirementNames.PRESENT_VALUE,
    ValueRequirementNames.FX_PRESENT_VALUE,
    ValueRequirementNames.IMPLIED_VOLATILITY,
    ValueRequirementNames.GRID_DUAL_DELTA,
    ValueRequirementNames.GRID_DUAL_GAMMA,
    ValueRequirementNames.GRID_FORWARD_DELTA,
    ValueRequirementNames.GRID_FORWARD_GAMMA,
    ValueRequirementNames.GRID_FORWARD_VEGA,
    ValueRequirementNames.GRID_FORWARD_VANNA,
    ValueRequirementNames.GRID_FORWARD_VOMMA,
    ValueRequirementNames.GRID_IMPLIED_VOLATILITY,
    ValueRequirementNames.GRID_PRESENT_VALUE
  };
  private final Map<String, String> _currencyPairToCurveName;
  private final Map<String, String> _currencyPairToCurveCalculationMethodName;
  private final Map<String, String> _currencyPairToSurfaceName;

  public FXBlackVolatilitySurfaceDefaults(final String... defaultsPercurrencyPair) {
    super(ComputationTargetType.PRIMITIVE, true);
    ArgumentChecker.notNull(defaultsPercurrencyPair, "defaults per currency");
    final int n = defaultsPercurrencyPair.length;
    ArgumentChecker.isTrue(n % 4 == 0, "Need one forward curve name, forward curve calculation method and surface name per currency pair");
    _currencyPairToCurveName = Maps.newLinkedHashMap();
    _currencyPairToCurveCalculationMethodName = Maps.newLinkedHashMap();
    _currencyPairToSurfaceName = Maps.newLinkedHashMap();
    for (int i = 0; i < n; i += 4) {
      final String currencyPair = defaultsPercurrencyPair[i];
      _currencyPairToCurveName.put(currencyPair, defaultsPercurrencyPair[i + 1]);
      _currencyPairToCurveCalculationMethodName.put(currencyPair, defaultsPercurrencyPair[i + 2]);
      _currencyPairToSurfaceName.put(currencyPair, defaultsPercurrencyPair[i + 3]);
    }
  }

  @Override
  public boolean canApplyTo(final FunctionCompilationContext context, final ComputationTarget target) {
    if (target.getType() != ComputationTargetType.PRIMITIVE) {
      return false;
    }
    final UniqueId uniqueId = target.getUniqueId();
    if (UnorderedCurrencyPair.OBJECT_SCHEME.equals(uniqueId.getScheme())) {
      final String currencyPair = uniqueId.getValue();
      if (_currencyPairToCurveName.containsKey(currencyPair)) {
        return true;
      }
      final String firstCcy = currencyPair.substring(0, 3);
      final String secondCcy = currencyPair.substring(3, 6);
      final String reversedCcys = secondCcy + firstCcy;
      return _currencyPairToCurveName.containsKey(reversedCcys);
    }
    return false;
  }

  @Override
  protected void getDefaults(final PropertyDefaults defaults) {
    for (final String valueRequirement : VALUE_REQUIREMENTS) {
      defaults.addValuePropertyName(valueRequirement, ValuePropertyNames.CURVE);
      defaults.addValuePropertyName(valueRequirement, ValuePropertyNames.CURVE_CALCULATION_METHOD);
      defaults.addValuePropertyName(valueRequirement, ValuePropertyNames.SURFACE);
    }
  }

  @Override
  protected Set<String> getDefaultValue(final FunctionCompilationContext context, final ComputationTarget target, final ValueRequirement desiredValue, final String propertyName) {
    final String currencyPair = target.getUniqueId().getValue();
    final String curveName = _currencyPairToCurveName.get(currencyPair);
    if (curveName == null) {
      s_logger.error("Could not get curve name for {}; should never happen", target.getValue());
      return null;
    }
    if (ValuePropertyNames.CURVE.equals(propertyName)) {
      return Collections.singleton(curveName);
    }
    if (ValuePropertyNames.CURVE_CALCULATION_METHOD.equals(propertyName)) {
      return Collections.singleton(_currencyPairToCurveCalculationMethodName.get(currencyPair));
    }
    if (ValuePropertyNames.SURFACE.equals(propertyName)) {
      return Collections.singleton(_currencyPairToSurfaceName.get(currencyPair));
    }
    s_logger.error("Could not find default value for {} in this function", propertyName);
    return null;
  }

}