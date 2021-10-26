package uk.co.sr.sample.repository.blockchain;

import static uk.co.sr.sample.repository.blockchain.BlockchainApiRepository.BLOCKCHAIN_API_ENDPOINT;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import uk.co.sr.sample.SampleServiceProperties;

@Configuration
@Slf4j
public class BlockchainApiRepositoryConfig {

  @Bean
  public BlockchainApiRepository blockchainApiRepository(final WebClient.Builder builder, final SampleServiceProperties properties) {
    log.info("In blockchainApiRepository");
    var blockchainApiBaseUrl = properties.getBlockchainApiBaseUrl();
    log.info("Blockchain api repository url = {}", blockchainApiBaseUrl.concat(BLOCKCHAIN_API_ENDPOINT));
    // TODO build webclient with timeouts https://www.baeldung.com/spring-5-webclient section 4.2
    var webClient = builder.baseUrl(blockchainApiBaseUrl)
        .build();
    log.info("Out blockchainApiRepository");
    return new BlockchainApiRepository(webClient);
  }

}
