package com.jtj.web;

import com.jtj.web.controller.PublicController;
import com.jtj.web.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PublicController.class)
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

	@Test
	public void contextLoads() throws Exception {
		MvcResult result = this.mvc.perform(get("/public/map/role")).andExpect(status().isOk()).andReturn();
		System.err.println("------------------");
	}

}
