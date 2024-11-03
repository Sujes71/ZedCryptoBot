package es.zed.jscalp.domain.output.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyTradeRequestDto {

  private String symbol;
  private String orderId;
  private String startTime;
  private String endTime;
  private String fromId;
  private Integer limit;
  private String recvWindow;
  private String timestamp;
}
