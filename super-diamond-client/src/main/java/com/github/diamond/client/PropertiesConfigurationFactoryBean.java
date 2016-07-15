package com.github.diamond.client;

import com.github.diamond.client.event.ConfigurationListener;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

import java.util.*;

/**
 * 
 * @author yanweiqi
 */
public class PropertiesConfigurationFactoryBean implements FactoryBean<Properties> {

	private static PropertiesConfiguration configuration;

	private static boolean init = false;

	public PropertiesConfigurationFactoryBean() {
		this(null);
	}

	public PropertiesConfigurationFactoryBean(List<ConfigurationListener> listeners) {
		init = true;
		configuration = new PropertiesConfiguration();

		if (listeners != null) {
			for (ConfigurationListener listener : listeners) {
				configuration.addConfigurationListener(listener);
			}
		}
	}

	/**
	 * 构造函数
	 * @param projCode
	 * @param profile
	 * @param modules
	 */
	public PropertiesConfigurationFactoryBean(final String projCode, final String profile, final String modules) {
		this(projCode, profile, modules, null);
	}

	/**
	 * 构造函数
	 * @param projCode
	 * @param profile
	 * @param modules
	 * @param listeners
	 */
	public PropertiesConfigurationFactoryBean(final String projCode, final String profile, final String modules, List<ConfigurationListener> listeners) {
		init = true;
		configuration = new PropertiesConfiguration(projCode, profile);

		if (listeners != null) {
			for (ConfigurationListener listener : listeners) {
				configuration.addConfigurationListener(listener);
			}
		}
	}

	/**
	 * 构造函数
	 * @param host
	 * @param port
	 * @param projCode
	 * @param profile
	 * @param modules
	 */
	public PropertiesConfigurationFactoryBean(String host, int port, final String projCode, final String profile, final String modules) {
		this(host, port, projCode, profile, modules, null);
	}

	/**
	 *  构造函数
	 * @param host
	 * @param port
	 * @param projCode
	 * @param profile
	 * @param modules
	 * @param listeners
	 */
	public PropertiesConfigurationFactoryBean(String host, int port, final String projCode, final String profile, final String modules, List<ConfigurationListener> listeners) {
		init = true;
		configuration = new PropertiesConfiguration(host, port, projCode, profile, modules);

		if (listeners != null) {
			for (ConfigurationListener listener : listeners) {
				configuration.addConfigurationListener(listener);
			}
		}
	}

	@Override
	public Properties getObject() throws Exception {
		Assert.notNull(configuration);
		return configuration.getProperties();
	}

	@Override
	public Class<?> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public static PropertiesConfiguration getPropertiesConfiguration() {
		if (!init) {
			throw new ConfigurationRuntimeException("PropertiesConfigurationFactoryBean 没有初始化");
		}
		return configuration;
	}
}
