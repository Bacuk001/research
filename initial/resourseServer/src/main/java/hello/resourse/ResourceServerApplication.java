package hello.resourse;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableRetry
public class ResourceServerApplication {

    public static void main(String[] args) {
        run(ResourceServerApplication.class, args);
    }

}
