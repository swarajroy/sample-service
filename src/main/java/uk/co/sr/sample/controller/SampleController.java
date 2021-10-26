package uk.co.sr.sample.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import uk.co.sr.sample.controller.viewmodel.HighestPriceViewModel;
import uk.co.sr.sample.domain.Currency;
import uk.co.sr.sample.service.BitcoinService;

@RestController("/api/1")
@Tag(name = "Sample", description = "Sample Management")
@Slf4j
@AllArgsConstructor
public class SampleController {

  private final BitcoinService bitcoinService;

  @GetMapping("/highestprice")
  @Operation(operationId = "getHighestPrice_v1", description = "get highest price")
  @ApiResponses(
      @ApiResponse(responseCode = "200", description = "get highest price")
  )
  public Mono<ResponseEntity<HighestPriceViewModel>> getHighestPrice(@RequestParam(name = "currency", defaultValue = "USD") final Currency currency) {
    log.info("enter getHighestPrice currency = {}", currency);
    return bitcoinService.getHighestPrice(currency)
        .map(highestPrice -> ResponseEntity.ok(new HighestPriceViewModel(highestPrice.getPrice())))
        .doOnSuccess(rs -> log.info("exit getHighestPrice price rs = {}", rs));
  }
}
