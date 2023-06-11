package com.denilsson.factorypattern.controller;

import com.denilsson.factorypattern.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldCalculateFeeForPersonalAccount() throws Exception {

        Account personalAccount = new Account("1","Denilsson","Montoya",
                "personal",1_000d);

        MvcResult postResult  = mvc.perform(MockMvcRequestBuilders
                                    .post("/account/v1/create")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(new ObjectMapper().writeValueAsString(personalAccount)))
                                .andExpect(status().isOk())
                                .andReturn();
        String id = postResult.getResponse().getContentAsString();

        MvcResult feeCalculatorResult = mvc.perform(MockMvcRequestBuilders
                                            .get("/account/v1/calculate-fee/" + id)
                                            .accept(MediaType.APPLICATION_JSON))
                                    .andExpect(status().isOk())
                                    .andReturn();
        Double expectedPersonalAccountFee = 0.01;
        Double actualPersonalAccountFee = Double.parseDouble(feeCalculatorResult.getResponse().getContentAsString());
        assertThat(actualPersonalAccountFee).isEqualTo(expectedPersonalAccountFee);
    }

    @Test
    public void shouldCalculateFeeForCorporateAccount() throws Exception {
        Account corporateAccount = new Account("2","Denilsson","Montoya",
                "corporate",1_000_000d);

        MvcResult postResult  = mvc.perform(MockMvcRequestBuilders
                        .post("/account/v1/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(corporateAccount)))
                .andExpect(status().isOk())
                .andReturn();
        String id = postResult.getResponse().getContentAsString();

        MvcResult feeCalculatorResult = mvc.perform(MockMvcRequestBuilders
                        .get("/account/v1/calculate-fee/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Double expectedCorporateAccountFee = 0.05;
        Double actualCorporateAccountFee = Double.parseDouble(feeCalculatorResult.getResponse().getContentAsString());
        assertThat(actualCorporateAccountFee).isEqualTo(expectedCorporateAccountFee);
    }

    @Test
    public void shouldReturnErrorForNonImplementedAccountType() throws Exception {
        Account newUnknowAccount = new Account("3","Denilsson","Montoya",
                "savings",1_500d);

        MvcResult postResult  = mvc.perform(MockMvcRequestBuilders
                        .post("/account/v1/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newUnknowAccount)))
                .andExpect(status().isOk())
                .andReturn();
        String id = postResult.getResponse().getContentAsString();

        mvc.perform(MockMvcRequestBuilders
                        .get("/account/v1/calculate-fee/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void shouldReturnErrorForNonExistentAccount() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/account/v1/calculate-fee/80")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

}
