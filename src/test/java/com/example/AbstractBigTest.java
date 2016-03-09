package com.example;

import com.example.account.J2ETestsApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("integration")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = J2ETestsApplication.class)
@WebIntegrationTest("server.port=9898")
public abstract class AbstractBigTest {

    private static final Logger LOGGER = Logger.getLogger(AbstractBigTest.class);

    @Autowired
    ObjectMapper objectMapper;

    @Value("${server.port}")
    private Integer port;

    @Before
    public void setupRestassured(){
        RestAssured.port = 9898;
    }

    public <T> String toJson(T entity){
        try{
            return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e){
            LOGGER.error("", e);
            return null;
        }
    }
}
