package uk.co.sr.sample.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import uk.co.sr.sample.domain.Currency;
import uk.co.sr.sample.domain.HighestPrice;
import uk.co.sr.sample.repository.blockchain.BlockchainApiRepository;

@Service
@Slf4j
@AllArgsConstructor
public class BitcoinServiceImpl implements BitcoinService {

  private final BlockchainApiRepository blockchainApiRepository;

  @Override
  public Mono<HighestPrice> getHighestPrice(final Currency currency) {
    log.debug("Enter getHighestPrice currency = {}", currency);
    return blockchainApiRepository.getPrice(currency)
        .map(HighestPrice::new)
        .doOnSuccess(highestPrice -> log.debug("Exit getHighestPrice highestPrice = {}", highestPrice));
  }
}
