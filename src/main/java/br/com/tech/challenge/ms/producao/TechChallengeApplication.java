package br.com.tech.challenge.ms.producao;

import br.com.tech.challenge.ms.producao.config.FeignConfig;
import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@Generated
@EnableFeignClients(basePackages = "br.com.tech.challenge.ms.producao.api.client")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class TechChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechChallengeApplication.class, args);
    }

}
