package es.zed.jscalp.domain.output.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepthRequestDto {

  private String symbol;
  private Integer limit;

}
