package com.geekbrains.geekspring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockTesting {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomePage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("index"));
    }

    @Test
    public void testShopPage() throws Exception {
        this.mockMvc.perform(get("/shop"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("shop-page"));
    }

    @Test
    public void tryToStart() throws Exception{
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void testLoginAuthenticate() throws Exception{
        this.mockMvc.perform(formLogin("/authenticateTheUser").user("alex").password("123"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void badCredentials() throws Exception{
        this.mockMvc.perform(formLogin("/authenticateTheUser").user("falex").password("321"))
                .andDo(print())
                .andExpect(redirectedUrl("/login?error"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testCartPage() throws Exception{
        this.mockMvc.perform(get("/cart"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("cart-page"));
    }

}
