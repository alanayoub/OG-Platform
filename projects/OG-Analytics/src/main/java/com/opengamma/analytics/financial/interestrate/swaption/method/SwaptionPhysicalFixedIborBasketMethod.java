/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.interestrate.swaption.method;

import com.opengamma.analytics.financial.interestrate.annuity.derivative.AnnuityCouponFixed;
import com.opengamma.analytics.financial.interestrate.payments.derivative.Payment;
import com.opengamma.analytics.financial.interestrate.swap.derivative.SwapFixedCoupon;
import com.opengamma.analytics.financial.interestrate.swaption.derivative.SwaptionPhysicalFixedIbor;

/**
 * Method to create calibration baskets for swaptions.
 */
public final class SwaptionPhysicalFixedIborBasketMethod {

  /**
   * The method unique instance.
   */
  private static final SwaptionPhysicalFixedIborBasketMethod INSTANCE = new SwaptionPhysicalFixedIborBasketMethod();

  /**
   * Return the unique instance of the class.
   * @return The instance.
   */
  public static SwaptionPhysicalFixedIborBasketMethod getInstance() {
    return INSTANCE;
  }

  /**
   * Private constructor.
   */
  private SwaptionPhysicalFixedIborBasketMethod() {
  }

  /**
   * Create a calibration basket for the swaption. The basket is made of one swaption for each period on the underlying swap fixed leg. The basket swaptions
   * are trimmed after the end of the periods. The notional of all the coupons in all the underlying swaps is equal to the absolute notional of the first fixed leg coupon.
   * All the swaptions are long. The expiry time of all swaptions in the basket are the one of the original one.
   * @param swaption The swaption.
   * @return The basket.
   */
  public SwaptionPhysicalFixedIbor[] calibrationBasketFixedLegPeriod(final SwaptionPhysicalFixedIbor swaption) {
    AnnuityCouponFixed legFixed = swaption.getUnderlyingSwap().getFixedLeg();
    int nbCal = legFixed.getNumberOfPayments();
    SwaptionPhysicalFixedIbor[] basket = new SwaptionPhysicalFixedIbor[nbCal];
    double notional = Math.abs(legFixed.getNthPayment(0).getNotional());
    for (int loopcal = 0; loopcal < nbCal; loopcal++) {
      double maturity = legFixed.getNthPayment(loopcal).getPaymentTime();
      SwapFixedCoupon<? extends Payment> swap = swaption.getUnderlyingSwap().trimAfter(maturity).withNotional(notional);
      basket[loopcal] = SwaptionPhysicalFixedIbor.from(swaption.getTimeToExpiry(), swap, swaption.getSettlementTime(), true);
    }
    return basket;
  }

  /**
   * Create a calibration basket for the swaption. The basket is made of one swaption for each period on the underlying swap fixed leg and for each relative moneyness provided. 
   * The basket swaptions are trimmed after the end of the periods. The notional of all the coupons in all the underlying swaps is equal to the absolute notional of the first fixed leg coupon.
   * The basket swaptions rates are shifted by the relative moneyness provided.
   * All the swaptions are long. The expiry time of all swaptions in the basket are the one of the original one.
   * @param swaption The swaption.
   * @param relativeMoneyness The relative moneyness.
   * @return The basket.
   */
  public SwaptionPhysicalFixedIbor[] calibrationBasketFixedLegPeriod(final SwaptionPhysicalFixedIbor swaption, double[] relativeMoneyness) {
    AnnuityCouponFixed legFixed = swaption.getUnderlyingSwap().getFixedLeg();
    int nbPeriods = legFixed.getNumberOfPayments();
    int nbStrikes = relativeMoneyness.length;
    SwaptionPhysicalFixedIbor[] basket = new SwaptionPhysicalFixedIbor[nbPeriods * nbStrikes];
    double notional = 1; // Math.abs(legFixed.getNthPayment(0).getNotional());
    for (int loopcal = 0; loopcal < nbPeriods; loopcal++) {
      double maturity = legFixed.getNthPayment(loopcal).getPaymentTime();
      SwapFixedCoupon<? extends Payment> swap = swaption.getUnderlyingSwap().trimAfter(maturity).withNotional(notional);
      for (int loopstrike = 0; loopstrike < nbStrikes; loopstrike++) {
        basket[loopcal * nbStrikes + loopstrike] = SwaptionPhysicalFixedIbor.from(swaption.getTimeToExpiry(), swap.withRateShifted(relativeMoneyness[loopstrike]), swaption.getSettlementTime(), true);
      }
    }
    return basket;
  }

}
