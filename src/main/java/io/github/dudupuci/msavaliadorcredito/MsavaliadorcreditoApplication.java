package io.github.dudupuci.msavaliadorcredito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients // habilitamos uma varredura de componentes para interfaces que declaram ser clientes Feign
public class MsavaliadorcreditoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsavaliadorcreditoApplication.class, args);
    }

}
