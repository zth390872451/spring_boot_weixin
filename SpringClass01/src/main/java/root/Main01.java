package root;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import root.intefaceTest.ApplicationEventTest;

/**
 *
 */
@SpringBootApplication
public class Main01 {
    public static void main(String[] args) {
        SpringApplication.run(Main01.class, args).publishEvent(new ApplicationEventTest("testEvent"));
    }
}
