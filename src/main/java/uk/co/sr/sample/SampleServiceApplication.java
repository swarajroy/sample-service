package uk.co.sr.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = SampleServiceProperties.class)
public class SampleServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SampleServiceApplication.class, args);
  }
}
