package es.zed.jscalp.infrastructure.exception.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApiTypeException {

  /**
   * Invalid uri exception.
   */
  INVALID_URI_EXCEPTION(ExceptionCode.C500000.name(), HttpStatus.INTERNAL_SERVER_ERROR, "Invalid uri"),

  /**
   * Resource access exception.
   */
  RESOURCE_ACCESS_EXCEPTION(ExceptionCode.C500001.name(), HttpStatus.INTERNAL_SERVER_ERROR, "Error accessing resource");

  private final String code;

  private final HttpStatus status;

  private final String message;
}
