package uk.co.sr.sample.domain;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
@Builder
public class HighestPrice {
  BigDecimal price;
}
