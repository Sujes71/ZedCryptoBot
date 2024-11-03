package es.zed.jscalp.domain.output.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class OrdersResponseDto {

  private String symbol;
  private String orderId;
  private String orderListId;
  private String clientOrderId;
  private String price;
  private String origQty;
  private String executedQty;
  private String cummulativeQuoteQty;
  private String status;
  private String timeInForce;
  private String type;
  private String side;
  private String stopPrice;
  private String icebergQty;
  private String time;
  private String updateTime;
  private boolean isWorking;
  private String workingTime;
  private String origQuoteOrderQty;
  private String selfTradePreventionMode;
}
