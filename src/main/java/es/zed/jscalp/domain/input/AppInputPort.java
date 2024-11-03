package es.zed.jscalp.domain.input;

import es.zed.jscalp.domain.output.request.AccountRequestDto;
import es.zed.jscalp.domain.output.request.AllOrderRequestDto;
import es.zed.jscalp.domain.output.request.DepthRequestDto;
import es.zed.jscalp.domain.output.request.OpenOrdersRequestDto;
import es.zed.jscalp.domain.output.request.TradeRequestDto;
import es.zed.jscalp.domain.output.response.AccountResponseDto;
import es.zed.jscalp.domain.output.response.DepthDto;
import es.zed.jscalp.domain.output.response.OrderResponseDto;
import es.zed.jscalp.domain.output.response.OrdersResponseDto;
import es.zed.jscalp.domain.output.response.RespModel;
import es.zed.jscalp.domain.output.response.TradeResponseDto;
import java.util.List;
import org.springframework.http.HttpMethod;

public interface AppInputPort {

  RespModel<DepthDto> getDepth(final DepthRequestDto body);

  RespModel<OrderResponseDto> justOrder(final Object body, final HttpMethod method);

  RespModel<List<OrdersResponseDto>> getAllOrders(final AllOrderRequestDto body);

  RespModel<List<OrdersResponseDto>> justOpenOrders(final OpenOrdersRequestDto body, final HttpMethod method);

  RespModel<AccountResponseDto> getAccount(final AccountRequestDto body);

  RespModel<List<TradeResponseDto>> getMyTrades(final TradeRequestDto body);
}
