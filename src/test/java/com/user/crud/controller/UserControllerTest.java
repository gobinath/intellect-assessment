package com.user.crud.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.crud.model.User;
import com.user.crud.model.UserUpdateModel;
import com.user.crud.util.ResponseUtil;
import com.user.crud.util.UserFieldsChecker;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserController userController;

	@Test
	public void testCreateUser() throws Exception {

		User mockUser = new User();

		mockUser.setId("100");
		mockUser.setfName("Gobinath");
		mockUser.setlName("Velmurugesan");
		mockUser.setEmail("gobinath.gct@gmail.com");
		mockUser.setPinCode(123456);
		mockUser.setBirthDate("17-JUL-1992");
		mockUser.setActive(true);

		String inputInJson = this.mapToJson(mockUser);

		String inputString = ResponseUtil.jsonFormatter("Duplicate UserId issue!", mockUser.getId(), "XX(OPTIONAL)", "id",
				"User Id " + mockUser.getId() + " Already exists. Please Enter Unique Id");

		String URI = "/users";

		Mockito.when(userController.createUser((Mockito.any(User.class)))).thenReturn(inputString);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(inputString);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testUpdateUser() throws Exception {

		UserUpdateModel mockUserUpdateModel = new UserUpdateModel();

		mockUserUpdateModel.setEmail("gobinvath.gct@gmail.com");
		mockUserUpdateModel.setBirthDate("17-JUL-1972");

		String id = "100";

		String URI = "/users/user-id/" + id;

		String inputInJson = this.mapToJson(mockUserUpdateModel);
		String inputString = ResponseUtil.jsonFormatter(" User " + id + " Updated Successfully.", id, null, null, null);

		Mockito.when(userController.updateUser(Mockito.any(String.class), Mockito.any(UserUpdateModel.class)))
				.thenReturn(inputString);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(inputString);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testDeleteUser() throws Exception {

		String id = "108";
		String URI = "/users/user-id/" + id;

		String inputString = ResponseUtil.jsonFormatter(" User " + id + " does't exists.", id, "XX(OPTIONAL)", "id",
				" Please enter correct user Id for updation");
		Mockito.when(userController.deleteUser(Mockito.any(String.class))).thenReturn(inputString);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(inputString);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
