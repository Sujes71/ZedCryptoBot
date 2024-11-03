package es.zed.jscalp.infrastructure.endpoint;

import com.fasterxml.jackson.core.type.TypeReference;
import es.zed.jscalp.domain.output.AppOutputPort;
import es.zed.jscalp.domain.output.request.AccountRequestDto;
import es.zed.jscalp.domain.output.request.AllOrderRequestDto;
import es.zed.jscalp.domain.output.request.DeleteOrderRequestDto;
import es.zed.jscalp.domain.output.request.DepthRequestDto;
import es.zed.jscalp.domain.output.request.GetOrderRequestDto;
import es.zed.jscalp.domain.output.request.OpenOrdersRequestDto;
import es.zed.jscalp.domain.output.request.PostOrderRequestDto;
import es.zed.jscalp.domain.output.request.TradeRequestDto;
import es.zed.jscalp.domain.output.response.AccountResponseDto;
import es.zed.jscalp.domain.output.response.DepthDto;
import es.zed.jscalp.domain.output.response.OrderResponseDto;
import es.zed.jscalp.domain.output.response.OrdersResponseDto;
import es.zed.jscalp.domain.output.response.TradeResponseDto;
import es.zed.jscalp.infrastructure.endpoint.abstracts.AbstractEndpoint;
import es.zed.jscalp.shared.Constants;
import es.zed.jscalp.shared.utils.CustomObjectMapper;
import es.zed.jscalp.shared.utils.KeyManager;
import es.zed.jscalp.shared.utils.MethodUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiEndpoint extends AbstractEndpoint implements AppOutputPort {

  @Value("${binance.secret-key}")
  private String secretKey;

  @Value("${url.base.test.path}")
  private String basePath;

  @Value("${url.time.path}")
  private String timePath;

  @Value("${url.depth.path}")
  private String depthPath;

  @Value("${url.order.path}")
  private String orderPath;

  @Value("${url.order.all.path}")
  private String allOrderPath;

  @Value("${url.account.path}")
  private String accountPath;

  @Value("${url.mytrades.path}")
  private String myTradesPath;

  @Value("${url.open.orders.path}")
  private String openOrdersPath;

  public ApiEndpoint(RestTemplate restTemplate, CustomObjectMapper customObjectMapper) {
    super(restTemplate, customObjectMapper);
  }

  public DepthDto doCallGetDepth(final DepthRequestDto body) {
    Map<String, Object> params = new HashMap<>();
    params.put(Constants.PARAM_SYMBOL, body.getSymbol());
    params.put(Constants.PARAM_LIMIT, body.getLimit());

    removeNullValues(params);

    return doCall(buildUrlWithQueryParams(basePath.concat(depthPath), params), HttpMethod.GET, null, null, DepthDto.class);
  }

  @Override
  public OrderResponseDto doCallJustOrder(final Object body, final HttpMethod method) {
    Map<String, Object> params = new HashMap<>();

    params.put(Constants.PARAM_RECV_WINDOW, Constants.RECV_WINDOW);
    params.put(Constants.PARAM_TIMESTAMP, System.currentTimeMillis());

    if (method.equals(HttpMethod.POST)) {
      MethodUtils.addPostOrderParams(params, (PostOrderRequestDto) body);
    } else if (method.equals(HttpMethod.GET)) {
      MethodUtils.addGetOrderParams(params, (GetOrderRequestDto) body);
    } else {
      MethodUtils.addDeleteOrderParams(params, (DeleteOrderRequestDto) body);
    }

    removeNullValues(params);
    String queryString = buildQueryString(params);
    String signature = KeyManager.hmacSHA256(queryString, secretKey);
    params.put(Constants.PARAM_SIGNATURE, signature);

    try {
      return doCall(buildUrlWithQueryParams(basePath.concat(orderPath), params), method, addDefaultHeaders(), null, OrderResponseDto.class);
    } catch (HttpClientErrorException e) {
      return null;
    }
  }

  @Override
  public List<OrdersResponseDto> doCallGetAllOrders(final AllOrderRequestDto body) {
    Map<String, Object> params = new HashMap<>();
    params.put(Constants.PARAM_SYMBOL, body.getSymbol());
    params.put(Constants.PARAM_ORDER_ID, body.getOrderId());
    params.put(Constants.PARAM_START_TIME, body.getStartTime());
    params.put(Constants.PARAM_END_TIME, body.getEndTime());
    params.put(Constants.PARAM_LIMIT, body.getLimit());
    params.put(Constants.PARAM_RECV_WINDOW, Constants.RECV_WINDOW);
    params.put(Constants.PARAM_TIMESTAMP, System.currentTimeMillis());

    removeNullValues(params);

    String queryString = buildQueryString(params);

    String signature = KeyManager.hmacSHA256(queryString, secretKey);
    params.put(Constants.PARAM_SIGNATURE, signature);

    return doCallTypeRef(buildUrlWithQueryParams(basePath.concat(allOrderPath), params), HttpMethod.GET, addDefaultHeaders(), null,
        new TypeReference<>() {});
  }

  @Override
  public List<OrdersResponseDto> doCallOpenOrders(final OpenOrdersRequestDto body, final HttpMethod method) {
    Map<String, Object> params = new HashMap<>();
    params.put(Constants.PARAM_SYMBOL, body.getSymbol());
    params.put(Constants.PARAM_RECV_WINDOW, Constants.RECV_WINDOW);
    params.put(Constants.PARAM_TIMESTAMP, System.currentTimeMillis());

    removeNullValues(params);

    String queryString = buildQueryString(params);

    String signature = KeyManager.hmacSHA256(queryString, secretKey);
    params.put(Constants.PARAM_SIGNATURE, signature);

    try {
      return doCallTypeRef(buildUrlWithQueryParams(basePath.concat(openOrdersPath), params), method, addDefaultHeaders(), null,
          new TypeReference<>() {});
    } catch (HttpClientErrorException e) {
      return Collections.emptyList();
    }
  }

  @Override
  public AccountResponseDto doCallGetAccount(final AccountRequestDto body) {
    Map<String, Object> params = new HashMap<>();
    params.put(Constants.PARAM_OMIT_ZERO_BALANCES, body.getOmitZeroBalances());
    params.put(Constants.PARAM_RECV_WINDOW, Constants.RECV_WINDOW);
    params.put(Constants.PARAM_TIMESTAMP, System.currentTimeMillis());

    removeNullValues(params);

    String queryString = buildQueryString(params);

    String signature = KeyManager.hmacSHA256(queryString, secretKey);
    params.put(Constants.PARAM_SIGNATURE, signature);

    return doCall(buildUrlWithQueryParams(basePath.concat(accountPath), params), HttpMethod.GET, addDefaultHeaders(), null, AccountResponseDto.class);
  }

  @Override
  public List<TradeResponseDto> doCallGetMyTrades(final TradeRequestDto body) {
    Map<String, Object> params = new HashMap<>();
    params.put(Constants.PARAM_SYMBOL, body.getSymbol());
    params.put(Constants.PARAM_ORDER_ID, body.getOrderId());
    params.put(Constants.PARAM_START_TIME, body.getStartTime());
    params.put(Constants.PARAM_END_TIME, body.getEndTime());
    params.put(Constants.PARAM_FROM_ID, body.getFromId());
    params.put(Constants.PARAM_LIMIT, body.getLimit());
    params.put(Constants.PARAM_RECV_WINDOW, Constants.RECV_WINDOW);
    params.put(Constants.PARAM_TIMESTAMP, System.currentTimeMillis());

    removeNullValues(params);

    String queryString = buildQueryString(params);

    String signature = KeyManager.hmacSHA256(queryString, secretKey);
    params.put(Constants.PARAM_SIGNATURE, signature);

    return doCallTypeRef(buildUrlWithQueryParams(basePath.concat(myTradesPath), params), HttpMethod.GET, addDefaultHeaders(), null,
        new TypeReference<>() {});
  }
}