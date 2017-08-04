package com.jtj.web;

import com.jtj.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@WebMvcTest(PublicController.class)
public class AssetApplicationWebMvcTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService userService;
	@MockBean
	private RoleService roleService;
	@MockBean
	private SystemService systemService;
	@MockBean
	private MenuService menuService;
	@MockBean
	private PointService pointService;

	//@Test
	public void contextLoads() throws Exception {
		//this is a example ,bu I'm not use it
		MvcResult result = this.mvc.perform(get("/public/map/role")).andExpect(status().isOk()).andReturn();
		System.err.println("------------------");
	}

}
