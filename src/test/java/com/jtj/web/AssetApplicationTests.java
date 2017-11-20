package com.jtj.web;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssetApplicationTests extends AbstractTransactionalTestNGSpringContextTests {

	@Test
	public void contextLoads() {
		//检测运行成功
		Assert.assertTrue(true);
	}

}
