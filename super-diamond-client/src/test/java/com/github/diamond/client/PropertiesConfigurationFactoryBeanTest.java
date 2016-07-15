/**        
 * Copyright (c) 2013 by 苏州科大国创信息技术有限公司.    
 */    
package com.github.diamond.client;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.diamond.client.event.ConfigurationEvent;
import com.github.diamond.client.event.ConfigurationListener;


/**
 * Create on @2013-8-26 @上午10:00:54 
 * @author bsli@ustcinfo.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:bean.xml"})
public class PropertiesConfigurationFactoryBeanTest  {
	
	@Test
	public  void config() throws IOException, InterruptedException {
		/**
		PropertiesConfiguration configuration = PropertiesConfigurationFactoryBean.getPropertiesConfiguration();
		configuration.addConfigurationListener(new ConfigurationListener() {
	        @Override
	        public void configurationChanged(ConfigurationEvent event) {
	            System.out.println(event.getType().name() + " " + event.getPropertyName() + " " + event.getPropertyValue());
	        }
	    });
		
		System.in.read();
		**/
		
		Thread.sleep(1000*60*60);
	}

}
