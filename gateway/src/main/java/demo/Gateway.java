package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestControllerAdvice
public class Gateway {

    public static void main(String[] args) {
        SpringApplication.run(Gateway.class, args);
    }

    private static final Logger log = LoggerFactory.getLogger("ExceptionHandler");

    @Bean
    @Order(0)
    public WebExceptionHandler responseStatusExceptionHandler() {
        return (serverWebExchange, throwable) -> {
            log.error(throwable.getMessage());
            return Mono.empty();
        };
    }
}