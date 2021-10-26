package uk.co.sr.sample.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.sr.sample.service.BitcoinService;

@ExtendWith(SpringExtension.class)
@WebFluxTest(SampleController.class)
class SampleControllerIntegrationTest {

  @MockBean
  private BitcoinService bitcoinService;



}