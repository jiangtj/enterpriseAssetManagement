package com.jtj.web;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest
public class AssetApplicationTests extends AbstractTestNGSpringContextTests {

	@Test
	public void contextLoads() {
		//检测运行成功
		Assert.assertTrue(true);
	}

}
