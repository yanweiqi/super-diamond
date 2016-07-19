package com.github.diamond.client.event;

import com.github.diamond.client.util.NamedThreadFactory;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author yanweiqi
 * 事件源监听器
 */
public class EventSource {
	
   private ListenerRegister listeners = new ListenerRegister();
	
	private ExecutorService executorService = Executors.newSingleThreadExecutor(new NamedThreadFactory("config-event"));

	public EventSource() {
		initListeners();
	}

	public void addConfigurationListener(ConfigurationListener listenerl) {
		checkListener(listenerl); //检测是否为空
		listeners.add(listenerl);  //添加监听
	}

	public boolean removeConfigurationListener(ConfigurationListener l) {
		return listeners.remove(l);
	}

	public Collection<ConfigurationListener> getConfigurationListeners() {
		return listeners.get();
	}

	public void clearConfigurationListeners() {
		listeners.clear();
	}

	/**
	 * 异步执行ConfigurationListener。
	 * @param type
	 * @param propName
	 * @param propValue
	 */
	protected void fireEvent(EventType type, String propName, Object propValue) {
		final Iterator<ConfigurationListener> it = listeners.get().iterator();
		if (it.hasNext()) {
			final ConfigurationEvent event = createEvent(type, propName, propValue);
			while (it.hasNext()) {
				final ConfigurationListener listener = it.next();
				executorService.submit(new Runnable() {
					@Override
					public void run() {
						listener.configurationChanged(event);
					}
				});
			}
		}
	}

	/**
	 * 创建事件
	 * @param type 
	 * @param propName
	 * @param propValue
	 * @return 
	 */
	protected ConfigurationEvent createEvent(EventType type, String propName, Object propValue) {
		return new ConfigurationEvent(this, type, propName, propValue);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		EventSource copy = (EventSource) super.clone();
		copy.initListeners();
		return copy;
	}

	/**
	 * 检测事件是否为空
	 * @param obj
	 */
	private static void checkListener(Object obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Listener must not be null!");
		}
	}

	/**
	 * 初始化事件
	 */
	private void initListeners() {
		listeners.init();
	}
}
