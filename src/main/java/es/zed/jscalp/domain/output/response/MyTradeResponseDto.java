package es.zed.jscalp.domain.output.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MyTradeResponseDto {

  private String symbol;
  private String id;
  private String orderId;
  private String orderListId;
  private String price;
  private String qty;
  private String quoteQty;
  private String commission;
  private String commissionAsset;
  private String time;
  private boolean isBuyer;
  private boolean isMaker;
  private boolean isBestMatch;
}
