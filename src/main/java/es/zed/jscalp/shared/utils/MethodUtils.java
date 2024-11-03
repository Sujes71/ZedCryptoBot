package es.zed.jscalp.shared.utils;

import es.zed.jscalp.domain.output.request.DeleteOrderRequestDto;
import es.zed.jscalp.domain.output.request.GetOrderRequestDto;
import es.zed.jscalp.domain.output.request.PostOrderRequestDto;
import es.zed.jscalp.domain.output.response.RespModel;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;

public class MethodUtils {

  public static void addPostOrderParams(Map<String, Object> params, PostOrderRequestDto request) {
    params.put("symbol", request.getSymbol());
    params.put("side", request.getSide());
    params.put("type", request.getType());
    params.put("timeInForce", request.getTimeInForce());
    params.put("quantity", request.getQuantity());
    params.put("quoteOrderQty", request.getQuoteOrderQty());
    params.put("price", request.getPrice());
    params.put("newClientOrderId", request.getNewClientOrderId());
    params.put("strategyId", request.getStrategyId());
    params.put("strategyType", request.getStrategyType());
    params.put("stopPrice", request.getStopPrice());
    params.put("trailingDelta", request.getTrailingDelta());
    params.put("icebergQty", request.getIcebergQty());
    params.put("newOrderRespType", request.getNewOrderRespType());
    params.put("selfTradePreventionMode", request.getSelfTradePreventionMode());
  }

  public static void addGetOrderParams(Map<String, Object> params, GetOrderRequestDto request) {
    params.put("symbol", request.getSymbol());
    params.put("orderId", request.getOrderId());
    params.put("origClientOrderId", request.getOrigClientOrderId());
  }

  public static void addDeleteOrderParams(Map<String, Object> params, DeleteOrderRequestDto request) {
    params.put("symbol", request.getSymbol());
    params.put("orderId", request.getOrderId());
    params.put("origClientOrderId", request.getOrigClientOrderId());
    params.put("newClientOrderId", request.getNewClientOrderId());
    params.put("cancelRestrictions", request.getCancelRestrictions());
  }

  public static <T> RespModel<T> buildResponse(T data) {
    if (data == null) {
      return RespModel.<T>builder()
          .data(null)
          .message(HttpStatus.NOT_FOUND.name())
          .build();
    }

    if (data instanceof List && ((List<?>) data).isEmpty()) {
      return RespModel.<T>builder()
          .data(data)
          .message(HttpStatus.NO_CONTENT.name())
          .build();
    }

    return RespModel.<T>builder()
        .data(data)
        .message(HttpStatus.OK.name())
        .build();
  }
}
