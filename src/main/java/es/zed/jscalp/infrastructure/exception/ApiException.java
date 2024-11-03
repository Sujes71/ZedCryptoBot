package es.zed.jscalp.infrastructure.exception;

import es.zed.jscalp.infrastructure.exception.enums.ApiTypeException;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

  private final HttpStatus status;

  private final String code;

  public ApiException(ApiTypeException apiTypeException) {
    super(apiTypeException.getMessage(), null);
    this.status = apiTypeException.getStatus();
    this.code = apiTypeException.getCode();
  }
}
