package uk.co.sr.sample.domain;

import java.math.BigDecimal;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
public class HighestPrice {
  BigDecimal price;
}
