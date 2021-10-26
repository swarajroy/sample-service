package uk.co.sr.sample.repository.blockchain;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;
import uk.co.sr.sample.domain.Currency;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
public class BlockchainApiRepositorySandpit {

  @Autowired
  private BlockchainApiRepository repository;

  @Test
  public void shouldGetPrice() {
    StepVerifier.create(repository.getPrice(new Currency("USD")))
        .consumeNextWith(result -> {
          assertThat(result).isNotNull();
          log.info("result = {}", result);
        }).verifyComplete();
  }
}
