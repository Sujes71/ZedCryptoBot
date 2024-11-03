package es.zed.jscalp.domain.output.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TradeRequestDto {

  private String symbol;
  private Integer limit;
}
