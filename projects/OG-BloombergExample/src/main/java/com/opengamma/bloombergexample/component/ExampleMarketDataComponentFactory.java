/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.bloombergexample.component;

import java.net.URI;
import java.util.LinkedHashMap;
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

import com.google.common.collect.ImmutableMap;
import com.opengamma.bbg.util.BloombergDataUtils;
import com.opengamma.component.ComponentInfo;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.AbstractComponentFactory;
import com.opengamma.core.security.SecuritySource;
import com.opengamma.engine.marketdata.InMemoryNamedMarketDataSpecificationRepository;
import com.opengamma.engine.marketdata.MarketDataProviderFactory;
import com.opengamma.engine.marketdata.NamedMarketDataSpecificationRepository;
import com.opengamma.engine.marketdata.availability.MarketDataAvailabilityProvider;
import com.opengamma.engine.marketdata.live.LiveDataFactory;
import com.opengamma.engine.marketdata.live.LiveMarketDataProviderFactory;
import com.opengamma.engine.marketdata.spec.LiveMarketDataSpecification;
import com.opengamma.livedata.LiveDataClient;
import com.opengamma.livedata.client.RemoteLiveDataClientFactoryBean;
import com.opengamma.provider.livedata.LiveDataMetaData;
import com.opengamma.provider.livedata.LiveDataMetaDataProvider;
import com.opengamma.provider.livedata.LiveDataServerTypes;
import com.opengamma.util.jms.JmsConnector;
import com.opengamma.util.jms.JmsConnectorFactoryBean;

/**
 * Component factory for consuming Bloomberg market data.
 */
@BeanDefinition
public class ExampleMarketDataComponentFactory extends AbstractComponentFactory {

  /**
   * name to use.
   */
  private static final String BLOOMBERG_LIVE_SOURCE_NAME = "Live market data (Bloomberg)";

  /**
   * The classifier under which to publish.
   */
  @PropertyDefinition(validate = "notNull")
  private String _classifier;
  /**
   * The security source.
   */
  @PropertyDefinition(validate = "notNull")
  private SecuritySource _securitySource;
  /**
   * The meta-data about the server.
   */
  @PropertyDefinition(validate = "notNull")
  private LiveDataMetaDataProvider _serverMetaDataProvider;
  /**
   * The JMS connector.
   */
  @PropertyDefinition(validate = "notNull")
  private JmsConnector _jmsConnector;

  //-------------------------------------------------------------------------
  @Override
  public void init(ComponentRepository repo, LinkedHashMap<String, String> configuration) throws Exception {
    initLiveMarketDataProviderFactory(repo);
    initNamedMarketDataSpecificationRepository(repo);
  }

  private MarketDataProviderFactory initLiveMarketDataProviderFactory(ComponentRepository repo) {
    LiveDataMetaDataProvider provider = getServerMetaDataProvider();
    LiveDataClient liveDataClient = createLiveDataClient(provider);
    
    MarketDataAvailabilityProvider availabilityProvider = BloombergDataUtils.createAvailabilityProvider(getSecuritySource());
    LiveDataFactory defaultFactory = new LiveDataFactory(liveDataClient, availabilityProvider, getSecuritySource());
    Map<String, LiveDataFactory> factoryMap = ImmutableMap.of(BLOOMBERG_LIVE_SOURCE_NAME, defaultFactory);
    LiveMarketDataProviderFactory marketDataProviderFactory = new LiveMarketDataProviderFactory(defaultFactory, factoryMap);
    
    ComponentInfo info = new ComponentInfo(MarketDataProviderFactory.class, getClassifier());
    repo.registerComponent(info, marketDataProviderFactory);
    return marketDataProviderFactory;
  }

  private LiveDataClient createLiveDataClient(LiveDataMetaDataProvider provider) {
    LiveDataMetaData metaData = provider.metaData();
    URI jmsUri = metaData.getJmsBrokerUri();
    if (metaData.getServerType() != LiveDataServerTypes.STANDARD || jmsUri == null) {
      throw new IllegalStateException();
    }
    JmsConnector jmsConnector = getJmsConnector();
    if (jmsConnector.getClientBrokerUri().equals(jmsUri) == false) {
      JmsConnectorFactoryBean jmsFactory = new JmsConnectorFactoryBean(jmsConnector);
      jmsFactory.setClientBrokerUri(jmsUri);
      jmsConnector = jmsFactory.getObjectCreating();
    }
    
    RemoteLiveDataClientFactoryBean ldcFb = new RemoteLiveDataClientFactoryBean();
    ldcFb.setJmsConnector(jmsConnector);
    ldcFb.setSubscriptionTopic(metaData.getJmsSubscriptionTopic());
    ldcFb.setEntitlementTopic(metaData.getJmsEntitlementTopic());
    ldcFb.setHeartbeatTopic(metaData.getJmsHeartbeatTopic());
    LiveDataClient ldcDistributed = ldcFb.getObjectCreating();
    return ldcDistributed;
  }

  private NamedMarketDataSpecificationRepository initNamedMarketDataSpecificationRepository(ComponentRepository repo) {
    InMemoryNamedMarketDataSpecificationRepository specRepository = new InMemoryNamedMarketDataSpecificationRepository();
    
    specRepository.addSpecification(BLOOMBERG_LIVE_SOURCE_NAME, new LiveMarketDataSpecification(BLOOMBERG_LIVE_SOURCE_NAME));
    ComponentInfo info = new ComponentInfo(NamedMarketDataSpecificationRepository.class, getClassifier());
    repo.registerComponent(info, specRepository);
    return specRepository;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ExampleMarketDataComponentFactory}.
   * @return the meta-bean, not null
   */
  public static ExampleMarketDataComponentFactory.Meta meta() {
    return ExampleMarketDataComponentFactory.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(ExampleMarketDataComponentFactory.Meta.INSTANCE);
  }

  @Override
  public ExampleMarketDataComponentFactory.Meta metaBean() {
    return ExampleMarketDataComponentFactory.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -281470431:  // classifier
        return getClassifier();
      case -702456965:  // securitySource
        return getSecuritySource();
      case -187029565:  // serverMetaDataProvider
        return getServerMetaDataProvider();
      case -1495762275:  // jmsConnector
        return getJmsConnector();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -281470431:  // classifier
        setClassifier((String) newValue);
        return;
      case -702456965:  // securitySource
        setSecuritySource((SecuritySource) newValue);
        return;
      case -187029565:  // serverMetaDataProvider
        setServerMetaDataProvider((LiveDataMetaDataProvider) newValue);
        return;
      case -1495762275:  // jmsConnector
        setJmsConnector((JmsConnector) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_classifier, "classifier");
    JodaBeanUtils.notNull(_securitySource, "securitySource");
    JodaBeanUtils.notNull(_serverMetaDataProvider, "serverMetaDataProvider");
    JodaBeanUtils.notNull(_jmsConnector, "jmsConnector");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ExampleMarketDataComponentFactory other = (ExampleMarketDataComponentFactory) obj;
      return JodaBeanUtils.equal(getClassifier(), other.getClassifier()) &&
          JodaBeanUtils.equal(getSecuritySource(), other.getSecuritySource()) &&
          JodaBeanUtils.equal(getServerMetaDataProvider(), other.getServerMetaDataProvider()) &&
          JodaBeanUtils.equal(getJmsConnector(), other.getJmsConnector()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getClassifier());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSecuritySource());
    hash += hash * 31 + JodaBeanUtils.hashCode(getServerMetaDataProvider());
    hash += hash * 31 + JodaBeanUtils.hashCode(getJmsConnector());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the classifier under which to publish.
   * @return the value of the property, not null
   */
  public String getClassifier() {
    return _classifier;
  }

  /**
   * Sets the classifier under which to publish.
   * @param classifier  the new value of the property, not null
   */
  public void setClassifier(String classifier) {
    JodaBeanUtils.notNull(classifier, "classifier");
    this._classifier = classifier;
  }

  /**
   * Gets the the {@code classifier} property.
   * @return the property, not null
   */
  public final Property<String> classifier() {
    return metaBean().classifier().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the security source.
   * @return the value of the property, not null
   */
  public SecuritySource getSecuritySource() {
    return _securitySource;
  }

  /**
   * Sets the security source.
   * @param securitySource  the new value of the property, not null
   */
  public void setSecuritySource(SecuritySource securitySource) {
    JodaBeanUtils.notNull(securitySource, "securitySource");
    this._securitySource = securitySource;
  }

  /**
   * Gets the the {@code securitySource} property.
   * @return the property, not null
   */
  public final Property<SecuritySource> securitySource() {
    return metaBean().securitySource().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the meta-data about the server.
   * @return the value of the property, not null
   */
  public LiveDataMetaDataProvider getServerMetaDataProvider() {
    return _serverMetaDataProvider;
  }

  /**
   * Sets the meta-data about the server.
   * @param serverMetaDataProvider  the new value of the property, not null
   */
  public void setServerMetaDataProvider(LiveDataMetaDataProvider serverMetaDataProvider) {
    JodaBeanUtils.notNull(serverMetaDataProvider, "serverMetaDataProvider");
    this._serverMetaDataProvider = serverMetaDataProvider;
  }

  /**
   * Gets the the {@code serverMetaDataProvider} property.
   * @return the property, not null
   */
  public final Property<LiveDataMetaDataProvider> serverMetaDataProvider() {
    return metaBean().serverMetaDataProvider().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the JMS connector.
   * @return the value of the property, not null
   */
  public JmsConnector getJmsConnector() {
    return _jmsConnector;
  }

  /**
   * Sets the JMS connector.
   * @param jmsConnector  the new value of the property, not null
   */
  public void setJmsConnector(JmsConnector jmsConnector) {
    JodaBeanUtils.notNull(jmsConnector, "jmsConnector");
    this._jmsConnector = jmsConnector;
  }

  /**
   * Gets the the {@code jmsConnector} property.
   * @return the property, not null
   */
  public final Property<JmsConnector> jmsConnector() {
    return metaBean().jmsConnector().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ExampleMarketDataComponentFactory}.
   */
  public static class Meta extends AbstractComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code classifier} property.
     */
    private final MetaProperty<String> _classifier = DirectMetaProperty.ofReadWrite(
        this, "classifier", ExampleMarketDataComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code securitySource} property.
     */
    private final MetaProperty<SecuritySource> _securitySource = DirectMetaProperty.ofReadWrite(
        this, "securitySource", ExampleMarketDataComponentFactory.class, SecuritySource.class);
    /**
     * The meta-property for the {@code serverMetaDataProvider} property.
     */
    private final MetaProperty<LiveDataMetaDataProvider> _serverMetaDataProvider = DirectMetaProperty.ofReadWrite(
        this, "serverMetaDataProvider", ExampleMarketDataComponentFactory.class, LiveDataMetaDataProvider.class);
    /**
     * The meta-property for the {@code jmsConnector} property.
     */
    private final MetaProperty<JmsConnector> _jmsConnector = DirectMetaProperty.ofReadWrite(
        this, "jmsConnector", ExampleMarketDataComponentFactory.class, JmsConnector.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
      this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "classifier",
        "securitySource",
        "serverMetaDataProvider",
        "jmsConnector");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          return _classifier;
        case -702456965:  // securitySource
          return _securitySource;
        case -187029565:  // serverMetaDataProvider
          return _serverMetaDataProvider;
        case -1495762275:  // jmsConnector
          return _jmsConnector;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ExampleMarketDataComponentFactory> builder() {
      return new DirectBeanBuilder<ExampleMarketDataComponentFactory>(new ExampleMarketDataComponentFactory());
    }

    @Override
    public Class<? extends ExampleMarketDataComponentFactory> beanType() {
      return ExampleMarketDataComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code classifier} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> classifier() {
      return _classifier;
    }

    /**
     * The meta-property for the {@code securitySource} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<SecuritySource> securitySource() {
      return _securitySource;
    }

    /**
     * The meta-property for the {@code serverMetaDataProvider} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<LiveDataMetaDataProvider> serverMetaDataProvider() {
      return _serverMetaDataProvider;
    }

    /**
     * The meta-property for the {@code jmsConnector} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<JmsConnector> jmsConnector() {
      return _jmsConnector;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
