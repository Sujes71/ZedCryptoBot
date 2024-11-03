package es.zed.jscalp.domain.output.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TradeResponseDto {

  private Long id;
  private String price;
  private String qty;
  private String quoteQty;
  private Long time;
  private Boolean isBuyerMaker;
  private Boolean isBestMatch;
}
