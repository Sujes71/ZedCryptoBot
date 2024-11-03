package es.zed.jscalp.domain.output;

import es.zed.jscalp.domain.output.request.AccountRequestDto;
import es.zed.jscalp.domain.output.request.AllOrderRequestDto;
import es.zed.jscalp.domain.output.request.DepthRequestDto;
import es.zed.jscalp.domain.output.request.OpenOrdersRequestDto;
import es.zed.jscalp.domain.output.request.TradeRequestDto;
import es.zed.jscalp.domain.output.response.AccountResponseDto;
import es.zed.jscalp.domain.output.response.DepthDto;
import es.zed.jscalp.domain.output.response.OrderResponseDto;
import es.zed.jscalp.domain.output.response.OrdersResponseDto;
import es.zed.jscalp.domain.output.response.TradeResponseDto;
import java.util.List;
import org.springframework.http.HttpMethod;

public interface AppOutputPort {

  DepthDto doCallGetDepth(final DepthRequestDto body);

  OrderResponseDto doCallJustOrder(final Object body, final HttpMethod method);

  List<OrdersResponseDto> doCallGetAllOrders(final AllOrderRequestDto body);

  List<OrdersResponseDto> doCallOpenOrders(final OpenOrdersRequestDto body, final HttpMethod method);

  AccountResponseDto doCallGetAccount(final AccountRequestDto body);

  List<TradeResponseDto> doCallGetMyTrades(final TradeRequestDto body);
}