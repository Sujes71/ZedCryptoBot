package es.zed.jscalp.infrastructure.config;

import es.zed.jscalp.shared.utils.CustomObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public CustomObjectMapper customObjectMapper() {
    return new CustomObjectMapper();
  }
}
