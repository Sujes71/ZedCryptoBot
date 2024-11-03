package es.zed.jscalp.domain.output.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetOrderRequestDto {

  private String symbol;
  private String orderId;
  private String origClientOrderId;
}
