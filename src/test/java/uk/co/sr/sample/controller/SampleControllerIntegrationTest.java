package uk.co.sr.sample.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import uk.co.sr.sample.domain.Currency;
import uk.co.sr.sample.domain.HighestPrice;
import uk.co.sr.sample.service.BitcoinService;

@ExtendWith(SpringExtension.class)
@WebFluxTest(SampleController.class)
class SampleControllerIntegrationTest {

  @MockBean
  private BitcoinService bitcoinService;

  @Autowired
  private WebTestClient webTestClient;

  @Test
  public void shouldGet200OkWithPrice() {
    when(bitcoinService.getHighestPrice(any(Currency.class))).thenReturn(Mono.just(new HighestPrice(BigDecimal.TEN)));

    webTestClient.get()
        .uri(uriBuilder ->
            uriBuilder
                .path("/api/1/highestprice")
                .queryParam("currency", new Currency("USD"))
                .build())
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.price", BigDecimal.TEN);

    verify(bitcoinService, times(1)).getHighestPrice(any(Currency.class));
  }



}