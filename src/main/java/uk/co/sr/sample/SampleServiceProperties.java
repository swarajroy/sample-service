package uk.co.sr.sample;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "uk.co.sr.sample")
public class SampleServiceProperties {
  String blockchainApiBaseUrl;
  String exmoApiBaseUrl;
  String bitfinexApiBaseUrl;
}
