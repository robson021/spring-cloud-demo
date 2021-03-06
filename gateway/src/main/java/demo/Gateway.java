package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
public class Gateway {

    public static void main(String[] args) {
        SpringApplication.run(Gateway.class, args);
    }

    @Bean
    @Order(0)
    public WebExceptionHandler responseStatusExceptionHandler() {
        final Logger log = LoggerFactory.getLogger("ExceptionHandler");
        return (serverWebExchange, throwable) -> {
            log.error(throwable.getMessage());
            return Mono.empty();
        };
    }
}