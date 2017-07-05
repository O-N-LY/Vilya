package com.stella.service.vilya.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = VilyaApiApplicationContext.class)
@ActiveProfiles("test")
public class BaseApiTest {

    @Test
    public void fakeTest() {

    }
}
