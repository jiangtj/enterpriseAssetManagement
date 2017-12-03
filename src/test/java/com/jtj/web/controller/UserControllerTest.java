package com.jtj.web.controller;

import com.jtj.web.base.AbstractAssetsTests;
import com.jtj.web.common.utils.JacksonUtils;
import com.jtj.web.entity.User;
import org.hamcrest.Matchers;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.testng.annotations.Test;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/11/19 17:57 End.
 */
public class UserControllerTest extends AbstractAssetsTests {

    @Test
    @Rollback
    public void add() throws Exception {
        User user = new User();
        user.setName("test-001");
        user.setPassword("123456");
        user.setPointId(1L);
        user.setRoleId(1L);
        user.setDescription("TestNG测试帐号");
        super.mockMvc.perform(post("/user/add").contentType(MediaType.APPLICATION_JSON).content(JacksonUtils.toJson(user)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(document("user-add"));
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void getList() throws Exception {
        super.mockMvc.perform(get("/user/list?begin=0&offset=10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("object.count", Matchers.equalTo(1)))
                .andDo(document("user-list"));
    }

}