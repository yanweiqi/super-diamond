package com.github.diamond.client.event;

/**
 * @author yanweiqi
 *
 */
public interface ConfigurationListener {

	/**
	 * 事件监听器
	 * @param event
	 */
	void configurationChanged(ConfigurationEvent event);
}
