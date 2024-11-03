package es.zed.jscalp.infrastructure.endpoint.abstracts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import es.zed.jscalp.infrastructure.exception.ApiException;
import es.zed.jscalp.infrastructure.exception.enums.ApiTypeException;
import es.zed.jscalp.shared.Constants;
import es.zed.jscalp.shared.utils.CustomObjectMapper;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractEndpoint {

  private final RestTemplate restTemplate;

  private final CustomObjectMapper customObjectMapper;

  protected URI createUri(final String uriPath) {
    try {
      return new URI(uriPath);
    } catch (URISyntaxException e) {
      throw new ApiException(ApiTypeException.INVALID_URI_EXCEPTION) {
      };
    }
  }

  protected <T> T handleConnectionException(Supplier<T> supplier) {
    try {
      return supplier.get();
    } catch (ResourceAccessException ex) {
      throw new ApiException(ApiTypeException.RESOURCE_ACCESS_EXCEPTION) {
      };
    }
  }

  protected HttpHeaders addDefaultHeaders() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Content-Type", "application/json");
    httpHeaders.add("X-MBX-APIKEY", Constants.API_KEY);
    return httpHeaders;
  }

  protected String buildQueryString(Map<String, Object> params) {
    StringBuilder queryString = new StringBuilder();
    for (Map.Entry<String, Object> entry : params.entrySet()) {
      if (queryString.length() > 0) {
        queryString.append("&");
      }
      queryString.append(entry.getKey()).append("=").append(entry.getValue());
    }
    return queryString.toString();
  }

  protected void removeNullValues(Map<String, Object> params) {
    params.entrySet().removeIf(entry -> entry.getValue() == null);
  }

  public static String buildUrlWithQueryParams(String baseUrl, Map<String, Object> queryParams) {
    StringBuilder urlBuilder = new StringBuilder(baseUrl);
    if (queryParams != null && !queryParams.isEmpty()) {
      StringJoiner joiner = new StringJoiner("&", "?", "");
      queryParams.forEach((key, value) -> {
        if (value != null) {
          joiner.add(key + "=" + value);
        }
      });
      urlBuilder.append(joiner);
    }
    return urlBuilder.toString();
  }

  protected <T> T extractResponseData(final ResponseEntity<T> responseEntity) {
    return responseEntity.getBody();
  }

  private <T> T extractResponseData(ResponseEntity<String> responseEntity, TypeReference<T> responseType) {
    if (responseEntity.getStatusCode().is2xxSuccessful()) {
      String responseBody = responseEntity.getBody();

      try {
        return customObjectMapper.readValue(responseBody, responseType);
      } catch (JsonProcessingException e) {
        log.error("Error deserializing response: {}", e.getMessage());
        throw new RuntimeException("Error deserializing response", e);
      }
    } else {
      throw new RuntimeException("Error response: " + responseEntity.getStatusCode());
    }
  }

  protected <T> T doCall(final String url, final HttpMethod httpMethod, final HttpHeaders httpHeaders, final Object body,
      final Class<T> responseClass) {
    return handleConnectionException(
        () -> {
          log.info("Do call {}, method {}", url, httpMethod);

          return extractResponseData(restTemplate.exchange(new RequestEntity<>(body, httpHeaders, httpMethod, createUri(url)), responseClass));
        }
    );
  }

  protected <T> T doCallTypeRef(final String url, final HttpMethod httpMethod, final HttpHeaders httpHeaders, final Object body,
      final TypeReference<T> responseType) {
    return handleConnectionException(() -> {
      log.info("Do call {}, method {}", url, httpMethod);

      ResponseEntity<String> responseEntity = restTemplate.exchange(
          new RequestEntity<>(body, httpHeaders, httpMethod, createUri(url)), String.class
      );

      return extractResponseData(responseEntity, responseType);
    });
  }

}
