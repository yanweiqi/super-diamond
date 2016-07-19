package com.github.diamond.client.event;

import java.util.EventObject;

/**
 * @author yanweiqi
 * 
 */
public class ConfigurationEvent extends EventObject {
	
	private static final long serialVersionUID = 3277238219073504136L;

	/**
	 * 事件类型 add,update,clear
	 */
    private EventType type; 

    /**
     * 属性名称
     */
    private String propertyName;

    /**
     * 属性值
     */
    private Object propertyValue;
    
    public ConfigurationEvent(Object source, EventType type, String propertyName, Object propertyValue) {
		super(source);
		this.type = type;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Object getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(Object propertyValue) {
		this.propertyValue = propertyValue;
	}
}
