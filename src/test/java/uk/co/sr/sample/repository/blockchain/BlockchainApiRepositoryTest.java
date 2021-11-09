package uk.co.sr.sample.repository.blockchain;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.sr.sample.repository.blockchain.BlockchainApiRepository.BLOCKCHAIN_API_ENDPOINT;

import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import reactor.test.StepVerifier;
import uk.co.sr.mockwebserver.MockWebServerHelper;
import uk.co.sr.sample.domain.Currency;
import uk.co.sr.sample.repository.blockchain.domain.BlockchainDto;

class BlockchainApiRepositoryTest {
  private BlockchainApiRepository sut;
  private MockWebServerHelper<Map<String, BlockchainDto>> mockWebServer;


  @BeforeEach
  public void setUp() {
    mockWebServer = new MockWebServerHelper<>();
    mockWebServer.start();
    this.sut = new BlockchainApiRepository(mockWebServer.createWebClient());
  }

  @Test
  public void shouldGetPrice() {
    mockWebServer.mockResponse(HttpStatus.OK, Map.of("USD", BlockchainDto.builder().price(BigDecimal.TEN).build()));

    StepVerifier.create(this.sut.getPrice(new Currency("USD")))
        .consumeNextWith(price -> assertThat(price).isEqualTo(BigDecimal.TEN))
        .verifyComplete();

    mockWebServer.expectRequest(recordedRequest -> {
      assertThat(recordedRequest.getMethod()).isEqualTo(HttpMethod.GET.name());
      assertThat(recordedRequest.getPath()).isEqualTo(BLOCKCHAIN_API_ENDPOINT);
    });
  }

  @AfterEach
  public void tearDown() {
    mockWebServer.shutdown();
  }
}