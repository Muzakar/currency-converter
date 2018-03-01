package com.zooplus.challenge.controller;

import com.zooplus.challenge.helpers.HelperUtil;
import com.zooplus.challenge.model.User;
import com.zooplus.challenge.services.CurrencyConverter;
import com.zooplus.challenge.services.UserServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        ServiceMockProvider.class,
        RequestController.class
})
public class RequestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    private UserServices userServices;

    @Autowired
    private CurrencyConverter currencyConverter;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testLogout() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/logout"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("userId", "")).andReturn();
        assertEquals("login", result.getModelAndView().getViewName());
    }

    @Test
    public void testLogin() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/showLogin"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("userId", "")).andReturn();
        assertEquals("login", result.getModelAndView().getViewName());
    }

    @Test
    public void testGetRegistered() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/getRegistered"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("countries", HelperUtil.getCountries())).andReturn();
        assertEquals("register", result.getModelAndView().getViewName());
    }

    @Test
    public void testConvertWithoutUserId() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/convert")
                .param("fromCurrency", "USD")
                .param("fromAmount", "10.0")
                .param("toCurrency", "INR")
                .param("userId", ""))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", "Please make sure you have logged in.")).andReturn();
        assertEquals("error", result.getModelAndView().getViewName());
    }

    @Test
    public void testConvert() throws Exception {
        when(currencyConverter.convert(any(), any(), any(), any())).thenReturn(new BigDecimal(10));
        when(userServices.fetchUser(any())).thenReturn(new User.Builder("abc1").userName("ABC1").build());
        when(currencyConverter.getAllConversionsForUser(any())).thenReturn(new ArrayList<>());
        MvcResult result = this.mockMvc.perform(post("/convert")
                .param("fromCurrency", "USD")
                .param("fromAmount", "10.0")
                .param("toCurrency", "INR")
                .param("userId", "abc1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("userId", "abc1"))
                .andExpect(model().attribute("userName", "ABC1"))
                .andExpect(model().attribute("conversions", new ArrayList<>()))
                .andExpect(model().attribute("currencies", HelperUtil.getCurrencies()))
                .andReturn();
        assertEquals("converter", result.getModelAndView().getViewName());
    }

    @Test
    public void testConvertHavingException() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/convert")
                .param("fromCurrency", "USD")
                .param("fromAmount", "10.0")
                .param("toCurrency", "INR")
                .param("userId", "abc"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", "Unable to fetch FX Rate for the currencies. Please select different currencies")).andReturn();
        assertEquals("error", result.getModelAndView().getViewName());
    }

    @Test
    public void testLoginValidationWithoutUserId() throws Exception {
        when(userServices.isValidUser(any(), any())).thenReturn(false);
        MvcResult result = this.mockMvc.perform(post("/validate")
                .param("userId", "abc1")
                .param("password", "pass1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", "Unable to login. Check userId and password"))
                .andReturn();
        assertEquals("error", result.getModelAndView().getViewName());
    }

    @Test
    public void testLoginWithValidUser() throws Exception {
        when(userServices.isValidUser(any(), any())).thenReturn(true);
        when(userServices.fetchUser(any())).thenReturn(new User.Builder("abc1").userName("ABC1").build());
        when(currencyConverter.getAllConversionsForUser(any())).thenReturn(new ArrayList<>());
        MvcResult result = this.mockMvc.perform(post("/validate")
                .param("userId", "abc1")
                .param("password", "pass1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("userId", "abc1"))
                .andExpect(model().attribute("userName", "ABC1"))
                .andExpect(model().attribute("conversions", new ArrayList<>()))
                .andExpect(model().attribute("currencies", HelperUtil.getCurrencies()))
                .andReturn();
        assertEquals("converter", result.getModelAndView().getViewName());
    }

    @Test
    public void testRegisterWithInValidUserId() throws Exception {
        when(userServices.isUserIdAvailable(any())).thenReturn(false);
        MvcResult result = this.mockMvc.perform(post("/register")
                .param("userId", "abc1")
                .param("userName", "ABC1")
                .param("password", "pass1")
                .param("email", "abc1@abc.com")
                .param("address", "address abc 1")
                .param("postcode", "p1")
                .param("country", "C1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", "UserId already exists"))
                .andReturn();
        assertEquals("error", result.getModelAndView().getViewName());
    }

    @Test
    public void testRegisterWithValidUser() throws Exception {
        when(userServices.isUserIdAvailable(any())).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/register")
                .param("userId", "abc1")
                .param("userName", "ABC1")
                .param("password", "pass1")
                .param("email", "abc1@abc.com")
                .param("address", "address abc 1")
                .param("postcode", "p1")
                .param("country", "C1"))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals("success", result.getModelAndView().getViewName());
    }
}