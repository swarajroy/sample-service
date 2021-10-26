package uk.co.sr.sample.repository.blockchain;

import static uk.co.sr.sample.repository.DependencyConstants.BLOCKCHAIN_API;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.math.BigDecimal;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import uk.co.sr.sample.domain.Currency;
import uk.co.sr.sample.repository.blockchain.domain.BlockchainDto;

@Slf4j
@AllArgsConstructor
public class BlockchainApiRepository {

  protected static final String BLOCKCHAIN_API_ENDPOINT = "/ticker";
  private final WebClient webClient;

  @CircuitBreaker(name = BLOCKCHAIN_API)
  @Bulkhead(name = BLOCKCHAIN_API)
  public Mono<BigDecimal> getPrice(final Currency currency) {
    log.info("enter getPrice currency = {}", currency);
    return webClient.get()
        .uri(BLOCKCHAIN_API_ENDPOINT)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<Map<String, BlockchainDto>>() {})
        .map(map -> map.getOrDefault(currency.getValue(), null))
        .map(BlockchainDto::getPrice)
        .doOnSuccess(value -> log.info("exit getPrice price = {}", value));
  }

}
