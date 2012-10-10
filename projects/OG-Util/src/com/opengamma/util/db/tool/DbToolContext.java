/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.util.db.tool;

import java.io.Closeable;
import java.util.Map;
import java.util.Set;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.util.db.DbConnector;
import com.opengamma.util.db.management.DbManagement;
import com.opengamma.util.db.script.DbScriptReader;

/**
 * A standard context that is used to provide components to database tools.
 */
@BeanDefinition
public class DbToolContext extends DirectBean implements Closeable {

  /**
   * The database connector.
   */
  @PropertyDefinition
  private DbConnector _dbConnector;
  /**
   * The database management instance.
   */
  @PropertyDefinition
  private DbManagement _dbManagement;
  /**
   * The database catalog name.
   */
  @PropertyDefinition
  private String _catalog;
  /**
   * The database object groups on which to operate.
   */
  @PropertyDefinition
  private Set<String> _groups;
  /**
   * The database script reader.
   */
  @PropertyDefinition
  private DbScriptReader _scriptReader;
  
  @Override
  public void close() {
    if (getDbConnector() != null) {
      getDbConnector().close();
      setDbConnector(null);
    }
  }
  
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code DbToolContext}.
   * @return the meta-bean, not null
   */
  public static DbToolContext.Meta meta() {
    return DbToolContext.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(DbToolContext.Meta.INSTANCE);
  }

  @Override
  public DbToolContext.Meta metaBean() {
    return DbToolContext.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 39794031:  // dbConnector
        return getDbConnector();
      case 209279841:  // dbManagement
        return getDbManagement();
      case 555704345:  // catalog
        return getCatalog();
      case -1237460524:  // groups
        return getGroups();
      case -1635446418:  // scriptReader
        return getScriptReader();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 39794031:  // dbConnector
        setDbConnector((DbConnector) newValue);
        return;
      case 209279841:  // dbManagement
        setDbManagement((DbManagement) newValue);
        return;
      case 555704345:  // catalog
        setCatalog((String) newValue);
        return;
      case -1237460524:  // groups
        setGroups((Set<String>) newValue);
        return;
      case -1635446418:  // scriptReader
        setScriptReader((DbScriptReader) newValue);
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
      DbToolContext other = (DbToolContext) obj;
      return JodaBeanUtils.equal(getDbConnector(), other.getDbConnector()) &&
          JodaBeanUtils.equal(getDbManagement(), other.getDbManagement()) &&
          JodaBeanUtils.equal(getCatalog(), other.getCatalog()) &&
          JodaBeanUtils.equal(getGroups(), other.getGroups()) &&
          JodaBeanUtils.equal(getScriptReader(), other.getScriptReader());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getDbConnector());
    hash += hash * 31 + JodaBeanUtils.hashCode(getDbManagement());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCatalog());
    hash += hash * 31 + JodaBeanUtils.hashCode(getGroups());
    hash += hash * 31 + JodaBeanUtils.hashCode(getScriptReader());
    return hash;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the database connector.
   * @return the value of the property
   */
  public DbConnector getDbConnector() {
    return _dbConnector;
  }

  /**
   * Sets the database connector.
   * @param dbConnector  the new value of the property
   */
  public void setDbConnector(DbConnector dbConnector) {
    this._dbConnector = dbConnector;
  }

  /**
   * Gets the the {@code dbConnector} property.
   * @return the property, not null
   */
  public final Property<DbConnector> dbConnector() {
    return metaBean().dbConnector().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the database management instance.
   * @return the value of the property
   */
  public DbManagement getDbManagement() {
    return _dbManagement;
  }

  /**
   * Sets the database management instance.
   * @param dbManagement  the new value of the property
   */
  public void setDbManagement(DbManagement dbManagement) {
    this._dbManagement = dbManagement;
  }

  /**
   * Gets the the {@code dbManagement} property.
   * @return the property, not null
   */
  public final Property<DbManagement> dbManagement() {
    return metaBean().dbManagement().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the database catalog name.
   * @return the value of the property
   */
  public String getCatalog() {
    return _catalog;
  }

  /**
   * Sets the database catalog name.
   * @param catalog  the new value of the property
   */
  public void setCatalog(String catalog) {
    this._catalog = catalog;
  }

  /**
   * Gets the the {@code catalog} property.
   * @return the property, not null
   */
  public final Property<String> catalog() {
    return metaBean().catalog().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the database object groups on which to operate.
   * @return the value of the property
   */
  public Set<String> getGroups() {
    return _groups;
  }

  /**
   * Sets the database object groups on which to operate.
   * @param groups  the new value of the property
   */
  public void setGroups(Set<String> groups) {
    this._groups = groups;
  }

  /**
   * Gets the the {@code groups} property.
   * @return the property, not null
   */
  public final Property<Set<String>> groups() {
    return metaBean().groups().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the database script reader.
   * @return the value of the property
   */
  public DbScriptReader getScriptReader() {
    return _scriptReader;
  }

  /**
   * Sets the database script reader.
   * @param scriptReader  the new value of the property
   */
  public void setScriptReader(DbScriptReader scriptReader) {
    this._scriptReader = scriptReader;
  }

  /**
   * Gets the the {@code scriptReader} property.
   * @return the property, not null
   */
  public final Property<DbScriptReader> scriptReader() {
    return metaBean().scriptReader().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code DbToolContext}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code dbConnector} property.
     */
    private final MetaProperty<DbConnector> _dbConnector = DirectMetaProperty.ofReadWrite(
        this, "dbConnector", DbToolContext.class, DbConnector.class);
    /**
     * The meta-property for the {@code dbManagement} property.
     */
    private final MetaProperty<DbManagement> _dbManagement = DirectMetaProperty.ofReadWrite(
        this, "dbManagement", DbToolContext.class, DbManagement.class);
    /**
     * The meta-property for the {@code catalog} property.
     */
    private final MetaProperty<String> _catalog = DirectMetaProperty.ofReadWrite(
        this, "catalog", DbToolContext.class, String.class);
    /**
     * The meta-property for the {@code groups} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Set<String>> _groups = DirectMetaProperty.ofReadWrite(
        this, "groups", DbToolContext.class, (Class) Set.class);
    /**
     * The meta-property for the {@code scriptReader} property.
     */
    private final MetaProperty<DbScriptReader> _scriptReader = DirectMetaProperty.ofReadWrite(
        this, "scriptReader", DbToolContext.class, DbScriptReader.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "dbConnector",
        "dbManagement",
        "catalog",
        "groups",
        "scriptReader");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 39794031:  // dbConnector
          return _dbConnector;
        case 209279841:  // dbManagement
          return _dbManagement;
        case 555704345:  // catalog
          return _catalog;
        case -1237460524:  // groups
          return _groups;
        case -1635446418:  // scriptReader
          return _scriptReader;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends DbToolContext> builder() {
      return new DirectBeanBuilder<DbToolContext>(new DbToolContext());
    }

    @Override
    public Class<? extends DbToolContext> beanType() {
      return DbToolContext.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code dbConnector} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<DbConnector> dbConnector() {
      return _dbConnector;
    }

    /**
     * The meta-property for the {@code dbManagement} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<DbManagement> dbManagement() {
      return _dbManagement;
    }

    /**
     * The meta-property for the {@code catalog} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> catalog() {
      return _catalog;
    }

    /**
     * The meta-property for the {@code groups} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Set<String>> groups() {
      return _groups;
    }

    /**
     * The meta-property for the {@code scriptReader} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<DbScriptReader> scriptReader() {
      return _scriptReader;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}