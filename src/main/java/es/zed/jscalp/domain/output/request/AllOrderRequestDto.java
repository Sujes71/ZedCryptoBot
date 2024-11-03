package es.zed.jscalp.domain.output.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AllOrderRequestDto {

  private String symbol;
  private String orderId;
  private Integer limit;
  private String startTime;
  private String endTime;

}
