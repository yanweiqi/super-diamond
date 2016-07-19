package com.github.diamond.client.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListenerRegister {
	/**
	 * Collection存储事件监听器
	 */
	private Collection<ConfigurationListener> listeners;
	
	public void add(ConfigurationListener listenerl) {
		check(listenerl); //检测是否为空
		listeners.add(listenerl);  //添加监听
	}

	public boolean remove(ConfigurationListener l) {
		return listeners.remove(l);
	}

	public Collection<ConfigurationListener> get() {
		return Collections.unmodifiableCollection(new ArrayList<ConfigurationListener>(listeners));
	}

	public void clear() {
		listeners.clear();
	}
	
	/**
	 * 检测事件是否为空
	 * @param obj
	 */
	private static void check(Object obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Listener must not be null!");
		}
	}

	/**
	 * 初始化事件
	 */
	public void init() {
		listeners = new CopyOnWriteArrayList<ConfigurationListener>();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		ListenerRegister copy = (ListenerRegister) super.clone();
		copy.init();
		return copy;
	}
}
