/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.security.swap;

import org.joda.beans.BeanDefinition;
import org.joda.beans.PropertyDefinition;
import java.util.Map;
import org.joda.beans.BeanBuilder;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.frequency.Frequency;
import com.opengamma.id.ExternalId;

/**
 * The floating spread interest rate swap leg.
 */
@BeanDefinition
public class FloatingSpreadIRLeg extends FloatingInterestRateLeg {
  
  /** Serialization version. */
  private static final long serialVersionUID = 1L;
  
  /**
   * The spread.
   */
  @PropertyDefinition
  private double _spread;
  
  /**
   * Creates an instance.
   */
  FloatingSpreadIRLeg() { // For builder
  }

  /**
   * Creates an instance.
   * 
   * @param dayCount  the day count, not null
   * @param frequency  the frequency, not null
   * @param regionIdentifier  the region, not null
   * @param businessDayConvention  the business day convention, not null
   * @param notional  the notional, not null
   * @param eom  whether this is EOM
   * @param floatingReferenceRateId  the reference rate, not null
   * @param floatingRateType  the floating rate type, not null
   * @param spread the spread
   */
  public FloatingSpreadIRLeg(DayCount dayCount, Frequency frequency, ExternalId regionIdentifier, BusinessDayConvention businessDayConvention,
      Notional notional, boolean eom, ExternalId floatingReferenceRateId, FloatingRateType floatingRateType, double spread) {
    super(dayCount, frequency, regionIdentifier, businessDayConvention, notional, eom, floatingReferenceRateId, floatingRateType);
    setSpread(spread);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FloatingSpreadIRLeg}.
   * @return the meta-bean, not null
   */
  public static FloatingSpreadIRLeg.Meta meta() {
    return FloatingSpreadIRLeg.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(FloatingSpreadIRLeg.Meta.INSTANCE);
  }

  @Override
  public FloatingSpreadIRLeg.Meta metaBean() {
    return FloatingSpreadIRLeg.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -895684237:  // spread
        return getSpread();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -895684237:  // spread
        setSpread((Double) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FloatingSpreadIRLeg other = (FloatingSpreadIRLeg) obj;
      return JodaBeanUtils.equal(getSpread(), other.getSpread()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getSpread());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the spread.
   * @return the value of the property
   */
  public double getSpread() {
    return _spread;
  }

  /**
   * Sets the spread.
   * @param spread  the new value of the property
   */
  public void setSpread(double spread) {
    this._spread = spread;
  }

  /**
   * Gets the the {@code spread} property.
   * @return the property, not null
   */
  public final Property<Double> spread() {
    return metaBean().spread().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FloatingSpreadIRLeg}.
   */
  public static class Meta extends FloatingInterestRateLeg.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code spread} property.
     */
    private final MetaProperty<Double> _spread = DirectMetaProperty.ofReadWrite(
        this, "spread", FloatingSpreadIRLeg.class, Double.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map = new DirectMetaPropertyMap(
      this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "spread");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -895684237:  // spread
          return _spread;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FloatingSpreadIRLeg> builder() {
      return new DirectBeanBuilder<FloatingSpreadIRLeg>(new FloatingSpreadIRLeg());
    }

    @Override
    public Class<? extends FloatingSpreadIRLeg> beanType() {
      return FloatingSpreadIRLeg.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code spread} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> spread() {
      return _spread;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
