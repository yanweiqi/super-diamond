
package com.github.diamond.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.diamond.client.event.ConfigurationEvent;
import com.github.diamond.client.event.ConfigurationListener;
import com.github.diamond.client.util.PropertyConfig;


/**
 * 测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:bean.xml"})
public class PropertiesConfigurationFactoryBeanTest  {
	
	@Test
	public  void config() throws IOException, InterruptedException {
		
		PropertiesConfiguration configuration = PropertiesConfigurationFactoryBean.getPropertiesConfiguration();
		configuration.addConfigurationListener(new ConfigurationListener() {
	        @Override
	        public void configurationChanged(ConfigurationEvent event) {
	            System.out.println(event.getType().name() + " " + event.getPropertyName() + " " + event.getPropertyValue());
	        }
	    });
		
		String tt = configuration.getString("jdbc.url");
		System.out.println(tt);
		
		String yy = PropertyConfig.getProperty("jdbc.url"); 
		System.out.println(tt+","+yy+","+tt.equals(yy));
		
		System.in.read();
		Thread.sleep(1000*60*60);
	}
	
	@Test
	public void test(){
        Collection<String> c = new ArrayList<String>();  
        Collection<String> s = Collections.unmodifiableCollection(c);  
        c.add("str");  
        System.out.println(s); 
        s.add("888");
	}

}
