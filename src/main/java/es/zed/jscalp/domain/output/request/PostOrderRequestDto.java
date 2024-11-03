package es.zed.jscalp.domain.output.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostOrderRequestDto {

  private String symbol;
  private String side;
  private String type;
  private String timeInForce;
  private String quantity;
  private String quoteOrderQty;
  private String price;
  private String newClientOrderId;
  private Integer strategyId;
  private Integer strategyType;
  private String stopPrice;
  private String trailingDelta;
  private String icebergQty;
  private String newOrderRespType;
  private String selfTradePreventionMode;

}
