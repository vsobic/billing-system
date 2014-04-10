package org.vm7.billing_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.vm7.billing_system.Service;

import static org.testng.Assert.*;

@ContextConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
public class ExampleConfigurationTests extends AbstractTestNGSpringContextTests  {
	
	@Autowired
	private Service service;

	@Test
	public void testSimpleProperties() throws Exception {
		assertNotNull(service);
	}
	
}
