/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.config;

import java.util.Map;

import javax.time.InstantProvider;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.id.ObjectIdentifiable;
import com.opengamma.master.AbstractHistoryRequest;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.PublicSPI;

/**
 * Request for the history of a piece of configuration.
 * <p>
 * A full configuration master implements historical storage of data.
 * History can be stored in two dimensions and this request provides searching.
 * <p>
 * The first historic dimension is the classic series of versions.
 * Each new version is stored in such a manor that previous versions can be accessed.
 * <p>
 * The second historic dimension is corrections.
 * A correction occurs when it is realized that the original data stored was incorrect.
 * A simple configuration master might simply replace the original version with the corrected value.
 * A full implementation will store the correction in such a manner that it is still possible
 * to obtain the value before the correction was made.
 * <p>
 * For example, a piece of configuration added on Monday and updated on Thursday has two versions.
 * If it is realized on Friday that the version stored on Monday was incorrect, then a
 * correction may be applied. There are now two versions, the first of which has one correction.
 * This may continue, with multiple corrections allowed for each version.
 * <p>
 * Versions and corrections are represented by instants in the search.
 * 
 * @param <T>  the configuration element type
 */
@PublicSPI
@BeanDefinition
public class ConfigHistoryRequest<T> extends AbstractHistoryRequest {

  /**
   * The class of the configuration.
   */
  @PropertyDefinition
  private Class<T> _type;
  
  /**
   * Creates an instance.
   * The object identifier must be added before searching.
   */
  public ConfigHistoryRequest() {
    super();
  }

  /**
   * Creates an instance with object identifier.
   * This will retrieve all versions and corrections unless the relevant fields are set.
   * 
   * @param objectId  the object identifier, not null
   * @param type the type of the configuration, not null
   */
  public ConfigHistoryRequest(final ObjectIdentifiable objectId, Class<T> type) {
    super(objectId);
    ArgumentChecker.notNull(type, "type");
    _type = type;
  }

  /**
   * Creates an instance with object identifier and optional version and correction.
   * 
   * @param objectId  the object identifier, not null
   * @param versionInstantProvider  the version instant to retrieve, null for all versions
   * @param correctedToInstantProvider  the instant that the data should be corrected to, null for all corrections
   */
  public ConfigHistoryRequest(final ObjectIdentifiable objectId, InstantProvider versionInstantProvider, InstantProvider correctedToInstantProvider) {
    super(objectId, versionInstantProvider, correctedToInstantProvider);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ConfigHistoryRequest}.
   * @param <R>  the bean's generic type
   * @return the meta-bean, not null
   */
  @SuppressWarnings("unchecked")
  public static <R> ConfigHistoryRequest.Meta<R> meta() {
    return ConfigHistoryRequest.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(ConfigHistoryRequest.Meta.INSTANCE);
  }

  @SuppressWarnings("unchecked")
  @Override
  public ConfigHistoryRequest.Meta<T> metaBean() {
    return ConfigHistoryRequest.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 3575610:  // type
        return getType();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 3575610:  // type
        setType((Class<T>) newValue);
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
      ConfigHistoryRequest<?> other = (ConfigHistoryRequest<?>) obj;
      return JodaBeanUtils.equal(getType(), other.getType()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getType());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the class of the configuration.
   * @return the value of the property
   */
  public Class<T> getType() {
    return _type;
  }

  /**
   * Sets the class of the configuration.
   * @param type  the new value of the property
   */
  public void setType(Class<T> type) {
    this._type = type;
  }

  /**
   * Gets the the {@code type} property.
   * @return the property, not null
   */
  public final Property<Class<T>> type() {
    return metaBean().type().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ConfigHistoryRequest}.
   */
  public static class Meta<T> extends AbstractHistoryRequest.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    @SuppressWarnings("rawtypes")
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code type} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Class<T>> _type = DirectMetaProperty.ofReadWrite(
        this, "type", ConfigHistoryRequest.class, (Class) Class.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
      this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "type");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3575610:  // type
          return _type;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ConfigHistoryRequest<T>> builder() {
      return new DirectBeanBuilder<ConfigHistoryRequest<T>>(new ConfigHistoryRequest<T>());
    }

    @SuppressWarnings({"unchecked", "rawtypes" })
    @Override
    public Class<? extends ConfigHistoryRequest<T>> beanType() {
      return (Class) ConfigHistoryRequest.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code type} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Class<T>> type() {
      return _type;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}