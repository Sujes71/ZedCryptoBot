package es.zed.jscalp.domain.output.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CandlesRequestDto {

  private String symbol;
  private String interval;
  private String startTime;
  private String endTime;
  private String timeZone;
  private Integer limit;
}
