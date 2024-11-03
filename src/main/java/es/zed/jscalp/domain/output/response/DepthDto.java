package es.zed.jscalp.domain.output.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class DepthDto {

  private String lastUpdateId;

  private List<List<String>> bids;

  private List<List<String>> asks;
}
