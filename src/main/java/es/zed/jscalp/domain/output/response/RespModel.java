package es.zed.jscalp.domain.output.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;

@Builder
@JsonInclude(Include.NON_NULL)
public class RespModel<T> implements IRespModel<T> {

  private T data;

  private String message;

  @Override
  public T getData() {
    return data;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
