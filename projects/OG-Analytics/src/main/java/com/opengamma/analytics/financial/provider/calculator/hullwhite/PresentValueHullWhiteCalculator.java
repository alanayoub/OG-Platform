/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.provider.calculator.hullwhite;

import com.opengamma.analytics.financial.interestrate.InstrumentDerivativeVisitorDelegate;
import com.opengamma.analytics.financial.interestrate.future.derivative.InterestRateFuture;
import com.opengamma.analytics.financial.interestrate.future.provider.InterestRateFutureSecurityHullWhiteMethod;
import com.opengamma.analytics.financial.interestrate.payments.derivative.CapFloorIbor;
import com.opengamma.analytics.financial.interestrate.payments.provider.CapFloorIborHullWhiteMethod;
import com.opengamma.analytics.financial.interestrate.swaption.derivative.SwaptionCashFixedIbor;
import com.opengamma.analytics.financial.interestrate.swaption.derivative.SwaptionPhysicalFixedIbor;
import com.opengamma.analytics.financial.interestrate.swaption.provider.SwaptionCashFixedIborHullWhiteApproximationMethod;
import com.opengamma.analytics.financial.interestrate.swaption.provider.SwaptionPhysicalFixedIborHullWhiteMethod;
import com.opengamma.analytics.financial.provider.calculator.discounting.PresentValueDiscountingCalculator;
import com.opengamma.analytics.financial.provider.description.HullWhiteOneFactorProviderInterface;
import com.opengamma.util.money.MultipleCurrencyAmount;

/**
 * Calculates the present value of an inflation instruments by discounting for a given MarketBundle
 */
public final class PresentValueHullWhiteCalculator extends InstrumentDerivativeVisitorDelegate<HullWhiteOneFactorProviderInterface, MultipleCurrencyAmount> {

  /**
   * The unique instance of the calculator.
   */
  private static final PresentValueHullWhiteCalculator INSTANCE = new PresentValueHullWhiteCalculator();

  /**
   * Constructor.
   */
  private PresentValueHullWhiteCalculator() {
    super(new HullWhiteProviderAdapter<MultipleCurrencyAmount>(PresentValueDiscountingCalculator.getInstance()));
  }

  /**
   * Gets the calculator instance.
   * @return The calculator.
   */
  public static PresentValueHullWhiteCalculator getInstance() {
    return INSTANCE;
  }

  /**
   * Pricing methods.
   */
  private static final CapFloorIborHullWhiteMethod METHOD_CAPFLOOR_IBOR = CapFloorIborHullWhiteMethod.getInstance();
  private static final InterestRateFutureSecurityHullWhiteMethod METHOD_IRFUT = InterestRateFutureSecurityHullWhiteMethod.getInstance();
  private static final SwaptionPhysicalFixedIborHullWhiteMethod METHOD_SWPT_PHYS = SwaptionPhysicalFixedIborHullWhiteMethod.getInstance();
  private static final SwaptionCashFixedIborHullWhiteApproximationMethod METHOD_SWPT_CASH = SwaptionCashFixedIborHullWhiteApproximationMethod.getInstance();

  //     -----     Payment/Coupon     -----

  @Override
  public MultipleCurrencyAmount visitCapFloorIbor(final CapFloorIbor cap, final HullWhiteOneFactorProviderInterface hullWhite) {
    return METHOD_CAPFLOOR_IBOR.presentValue(cap, hullWhite);
  }

  //     -----     Futures     -----

  @Override
  public MultipleCurrencyAmount visitInterestRateFuture(final InterestRateFuture futures, final HullWhiteOneFactorProviderInterface hullWhite) {
    return METHOD_IRFUT.presentValue(futures, hullWhite);
  }

  //     -----     Swaption     -----

  @Override
  public MultipleCurrencyAmount visitSwaptionPhysicalFixedIbor(final SwaptionPhysicalFixedIbor swaption, final HullWhiteOneFactorProviderInterface hullWhite) {
    return METHOD_SWPT_PHYS.presentValue(swaption, hullWhite);
  }

  @Override
  public MultipleCurrencyAmount visitSwaptionCashFixedIbor(final SwaptionCashFixedIbor swaption, final HullWhiteOneFactorProviderInterface hullWhite) {
    return METHOD_SWPT_CASH.presentValue(swaption, hullWhite);
  }

}