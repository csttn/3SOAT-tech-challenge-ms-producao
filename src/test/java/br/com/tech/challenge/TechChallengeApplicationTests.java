package br.com.tech.challenge;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@EnableFeignClients(basePackages = "br.com.tech.challenge.ms.producao.api.client")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
class TechChallengeApplicationTests {


	@Test
	void contextLoads() {
	}

}
