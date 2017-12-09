package com.jtj.web.controller;

import com.jtj.web.base.AbstractAssetsTests;
import com.jtj.web.common.utils.JacksonUtils;
import com.jtj.web.entity.User;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.annotation.Rollback;
import org.testng.annotations.Test;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
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
                .andExpect(jsonPath("$.object.count").value(1))
                .andDo(document("user-list",
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING).description("code"),
                                fieldWithPath("title").type(JsonFieldType.STRING).description("code"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("信息").optional(),
                                fieldWithPath("time").type(JsonFieldType.STRING).description("信息")
                        ).andWithPrefix("object.",
                                fieldWithPath("count").type(JsonFieldType.NUMBER).description("The user's number"),
                                fieldWithPath("list").type(JsonFieldType.ARRAY).description("三星")
                        ).andWithPrefix("object.list[].",
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("id"),
                                fieldWithPath("createTime").type(JsonFieldType.NUMBER).description("The user's number"),
                                fieldWithPath("updateTime").type(JsonFieldType.NUMBER).description("The user's number"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("The user's number"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("The user's number"),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("The user's number"),
                                fieldWithPath("roleId").type(JsonFieldType.NUMBER).description("The user's number"),
                                fieldWithPath("role").type(JsonFieldType.OBJECT).description("The user's number").optional(),
                                fieldWithPath("pointId").type(JsonFieldType.NUMBER).description("The user's number"),
                                fieldWithPath("point").type(JsonFieldType.OBJECT).description("The user's number").optional(),
                                fieldWithPath("queryPoints").type(JsonFieldType.ARRAY).description("The user's number").optional(),
                                fieldWithPath("editPoints").type(JsonFieldType.ARRAY).description("The user's number").optional()
                        )
                ));
    }

}