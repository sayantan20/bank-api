package com.foundation.sa20123067_bank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Sa20123067BankApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}