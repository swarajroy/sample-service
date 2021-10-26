package uk.co.sr.sample.repository.blockchain.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BlockchainDto {

  @JsonProperty("sell")
  BigDecimal price;
}
