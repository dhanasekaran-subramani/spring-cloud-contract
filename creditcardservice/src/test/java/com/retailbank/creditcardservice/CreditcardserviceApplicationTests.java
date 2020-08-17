package com.retailbank.creditcardservice;

import com.retailbank.creditcardservice.gateway.CreditCheckResponse;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
@AutoConfigureStubRunner(ids = "com.retailbank:creditcheckservice:+:stubs:8080",  stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class CreditcardserviceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGrantApplicationWhenCreditScoreIsHigh() throws Exception {
        mockMvc.perform(
                post("/credit-card-applications")
                        .contentType(APPLICATION_JSON)
                        .content("{" +
                                "\"citizenNumber\": 1234," +
                                "\"cardType\": \"GOLD\"" +
                                "}"
                        ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("{" +
                                "\"status\":\"GRANTED\"" +
                                "}"))
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));
    }


    /*@Test
    public void test_should_return_all_employee_name_integration_by_calling_stub_with_fixed_port() {
        String json = "{\" +\n" +
                "                                \"\\\"citizenNumber\\\": 1234,\" +\n" +
                "                                \"\\\"cardType\\\": \\\"GOLD\\\"\" +\n" +
                "                                \"}";
        RestTemplate restTemplate = new RestTemplate();
        CreditCheckResponse response = restTemplate.postForObject("http://localhost:8080/credit-card-applications", json, CreditCheckResponse.class);
    }
*/
}