package org.vm7.billing_system;

import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;
import org.vm7.billing_system.ExampleService;

@Test
public class ExampleServiceTests {

	private ExampleService service = new ExampleService();
	
	public void testReadOnce() throws Exception {
		assertEquals("Hello world!", service.getMessage());
	}

}
