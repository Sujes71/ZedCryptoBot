package es.zed.jscalp.domain.output.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class OrderResponseDto {

  private String symbol;
  private String orderId;
  private String orderListId;
  private String clientOrderId;
  private String transactTime;
  private String price;
  private String origQty;
  private String executedQty;
  private String cummulativeQuoteQty;
  private String status;
  private String timeInForce;
  private String type;
  private String side;
  private String workingTime;
  @JsonProperty("fills")
  private List<Fill> fills;

  @Data
  @NoArgsConstructor
  @JsonInclude(Include.NON_NULL)
  public static class Fill {

    private String price;
    private String qty;
    private String commission;
    private String commissionAsset;
    private String tradeId;

  }
}
