package uk.co.sr.sample.service;

import reactor.core.publisher.Mono;
import uk.co.sr.sample.domain.Currency;
import uk.co.sr.sample.domain.HighestPrice;

public interface BitcoinService {
  Mono<HighestPrice> getHighestPrice(final Currency currency);
}
