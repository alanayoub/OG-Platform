/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.option;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.financial.security.FinancialSecurity;
import com.opengamma.financial.security.FinancialSecurityVisitor;
import com.opengamma.id.ExternalId;
import com.opengamma.util.money.Currency;
import com.opengamma.util.time.Expiry;

/**
 * A security for equity options.
 */
@BeanDefinition
public class SwaptionSecurity extends FinancialSecurity {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The security type.
   */
  public static final String SECURITY_TYPE = "SWAPTION";

  /**
   * The payer flag.
   */
  @PropertyDefinition
  private boolean _payer;
  /**
   * The underlying identifier.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _underlyingId;
  /**
   * The long flag.
   */
  @PropertyDefinition(get = "get")
  private boolean _isLong;
  /**
   * The expiry.
   */
  @PropertyDefinition(validate = "notNull")
  private Expiry _expiry;
  /**
   * The cash settled flag.
   */
  @PropertyDefinition
  private boolean _cashSettled;
  /**
   * The currency.
   */
  @PropertyDefinition(validate = "notNull")
  private Currency _currency;

  /**
   * Creates an empty instance.
   * <p>
   * The security details should be set before use.
   */
  public SwaptionSecurity() {
  }

  public SwaptionSecurity(boolean payer, ExternalId underlyingIdentifier, boolean isLong, Expiry expiry, boolean cashSettled, Currency currency) {
    super(SECURITY_TYPE);
    setPayer(payer);
    setUnderlyingId(underlyingIdentifier);
    setIsLong(isLong);
    setExpiry(expiry);
    setCashSettled(cashSettled);
    setCurrency(currency);
  }

  //-------------------------------------------------------------------------
  @Override
  public final <T> T accept(FinancialSecurityVisitor<T> visitor) {
    return visitor.visitSwaptionSecurity(this);
  }

  /**
   * Accepts a visitor to manage traversal of the hierarchy.
   * 
   * @param <T> the result type of the visitor
   * @param visitor  the visitor, not null
   * @return the result
   */
  public <T> T accept(SwaptionSecurityVisitor<T> visitor) {
    return visitor.visitSwaptionSecurity(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code SwaptionSecurity}.
   * @return the meta-bean, not null
   */
  public static SwaptionSecurity.Meta meta() {
    return SwaptionSecurity.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(SwaptionSecurity.Meta.INSTANCE);
  }

  @Override
  public SwaptionSecurity.Meta metaBean() {
    return SwaptionSecurity.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 106443605:  // payer
        return isPayer();
      case -771625640:  // underlyingId
        return getUnderlyingId();
      case -1180327226:  // isLong
        return getIsLong();
      case -1289159373:  // expiry
        return getExpiry();
      case -871053882:  // cashSettled
        return isCashSettled();
      case 575402001:  // currency
        return getCurrency();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 106443605:  // payer
        setPayer((Boolean) newValue);
        return;
      case -771625640:  // underlyingId
        setUnderlyingId((ExternalId) newValue);
        return;
      case -1180327226:  // isLong
        setIsLong((Boolean) newValue);
        return;
      case -1289159373:  // expiry
        setExpiry((Expiry) newValue);
        return;
      case -871053882:  // cashSettled
        setCashSettled((Boolean) newValue);
        return;
      case 575402001:  // currency
        setCurrency((Currency) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_underlyingId, "underlyingId");
    JodaBeanUtils.notNull(_expiry, "expiry");
    JodaBeanUtils.notNull(_currency, "currency");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      SwaptionSecurity other = (SwaptionSecurity) obj;
      return JodaBeanUtils.equal(isPayer(), other.isPayer()) &&
          JodaBeanUtils.equal(getUnderlyingId(), other.getUnderlyingId()) &&
          JodaBeanUtils.equal(getIsLong(), other.getIsLong()) &&
          JodaBeanUtils.equal(getExpiry(), other.getExpiry()) &&
          JodaBeanUtils.equal(isCashSettled(), other.isCashSettled()) &&
          JodaBeanUtils.equal(getCurrency(), other.getCurrency()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(isPayer());
    hash += hash * 31 + JodaBeanUtils.hashCode(getUnderlyingId());
    hash += hash * 31 + JodaBeanUtils.hashCode(getIsLong());
    hash += hash * 31 + JodaBeanUtils.hashCode(getExpiry());
    hash += hash * 31 + JodaBeanUtils.hashCode(isCashSettled());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCurrency());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the payer flag.
   * @return the value of the property
   */
  public boolean isPayer() {
    return _payer;
  }

  /**
   * Sets the payer flag.
   * @param payer  the new value of the property
   */
  public void setPayer(boolean payer) {
    this._payer = payer;
  }

  /**
   * Gets the the {@code payer} property.
   * @return the property, not null
   */
  public final Property<Boolean> payer() {
    return metaBean().payer().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the underlying identifier.
   * @return the value of the property, not null
   */
  public ExternalId getUnderlyingId() {
    return _underlyingId;
  }

  /**
   * Sets the underlying identifier.
   * @param underlyingId  the new value of the property, not null
   */
  public void setUnderlyingId(ExternalId underlyingId) {
    JodaBeanUtils.notNull(underlyingId, "underlyingId");
    this._underlyingId = underlyingId;
  }

  /**
   * Gets the the {@code underlyingId} property.
   * @return the property, not null
   */
  public final Property<ExternalId> underlyingId() {
    return metaBean().underlyingId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the long flag.
   * @return the value of the property
   */
  public boolean getIsLong() {
    return _isLong;
  }

  /**
   * Sets the long flag.
   * @param isLong  the new value of the property
   */
  public void setIsLong(boolean isLong) {
    this._isLong = isLong;
  }

  /**
   * Gets the the {@code isLong} property.
   * @return the property, not null
   */
  public final Property<Boolean> isLong() {
    return metaBean().isLong().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the expiry.
   * @return the value of the property, not null
   */
  public Expiry getExpiry() {
    return _expiry;
  }

  /**
   * Sets the expiry.
   * @param expiry  the new value of the property, not null
   */
  public void setExpiry(Expiry expiry) {
    JodaBeanUtils.notNull(expiry, "expiry");
    this._expiry = expiry;
  }

  /**
   * Gets the the {@code expiry} property.
   * @return the property, not null
   */
  public final Property<Expiry> expiry() {
    return metaBean().expiry().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the cash settled flag.
   * @return the value of the property
   */
  public boolean isCashSettled() {
    return _cashSettled;
  }

  /**
   * Sets the cash settled flag.
   * @param cashSettled  the new value of the property
   */
  public void setCashSettled(boolean cashSettled) {
    this._cashSettled = cashSettled;
  }

  /**
   * Gets the the {@code cashSettled} property.
   * @return the property, not null
   */
  public final Property<Boolean> cashSettled() {
    return metaBean().cashSettled().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the currency.
   * @return the value of the property, not null
   */
  public Currency getCurrency() {
    return _currency;
  }

  /**
   * Sets the currency.
   * @param currency  the new value of the property, not null
   */
  public void setCurrency(Currency currency) {
    JodaBeanUtils.notNull(currency, "currency");
    this._currency = currency;
  }

  /**
   * Gets the the {@code currency} property.
   * @return the property, not null
   */
  public final Property<Currency> currency() {
    return metaBean().currency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SwaptionSecurity}.
   */
  public static class Meta extends FinancialSecurity.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code payer} property.
     */
    private final MetaProperty<Boolean> _payer = DirectMetaProperty.ofReadWrite(
        this, "payer", SwaptionSecurity.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code underlyingId} property.
     */
    private final MetaProperty<ExternalId> _underlyingId = DirectMetaProperty.ofReadWrite(
        this, "underlyingId", SwaptionSecurity.class, ExternalId.class);
    /**
     * The meta-property for the {@code isLong} property.
     */
    private final MetaProperty<Boolean> _isLong = DirectMetaProperty.ofReadWrite(
        this, "isLong", SwaptionSecurity.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code expiry} property.
     */
    private final MetaProperty<Expiry> _expiry = DirectMetaProperty.ofReadWrite(
        this, "expiry", SwaptionSecurity.class, Expiry.class);
    /**
     * The meta-property for the {@code cashSettled} property.
     */
    private final MetaProperty<Boolean> _cashSettled = DirectMetaProperty.ofReadWrite(
        this, "cashSettled", SwaptionSecurity.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code currency} property.
     */
    private final MetaProperty<Currency> _currency = DirectMetaProperty.ofReadWrite(
        this, "currency", SwaptionSecurity.class, Currency.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map = new DirectMetaPropertyMap(
      this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "payer",
        "underlyingId",
        "isLong",
        "expiry",
        "cashSettled",
        "currency");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 106443605:  // payer
          return _payer;
        case -771625640:  // underlyingId
          return _underlyingId;
        case -1180327226:  // isLong
          return _isLong;
        case -1289159373:  // expiry
          return _expiry;
        case -871053882:  // cashSettled
          return _cashSettled;
        case 575402001:  // currency
          return _currency;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends SwaptionSecurity> builder() {
      return new DirectBeanBuilder<SwaptionSecurity>(new SwaptionSecurity());
    }

    @Override
    public Class<? extends SwaptionSecurity> beanType() {
      return SwaptionSecurity.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code payer} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> payer() {
      return _payer;
    }

    /**
     * The meta-property for the {@code underlyingId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> underlyingId() {
      return _underlyingId;
    }

    /**
     * The meta-property for the {@code isLong} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> isLong() {
      return _isLong;
    }

    /**
     * The meta-property for the {@code expiry} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Expiry> expiry() {
      return _expiry;
    }

    /**
     * The meta-property for the {@code cashSettled} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> cashSettled() {
      return _cashSettled;
    }

    /**
     * The meta-property for the {@code currency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Currency> currency() {
      return _currency;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
