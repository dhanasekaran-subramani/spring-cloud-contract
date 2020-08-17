package com.retailbank.creditcheckservice;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.retailbank.creditcheckservice.CreditCheckResponse.Score.HIGH;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class BaseContractTest {

    @InjectMocks
    private CreditCheckController creditCheckController;

    @Mock
    private CreditCheckService creditCheckService;


    @BeforeEach
    public void setUp() {
        when(creditCheckService.doCreditCheck(1234)).thenReturn(new CreditCheckResponse(HIGH));
        RestAssuredMockMvc.standaloneSetup(this.creditCheckController);
    }
}
