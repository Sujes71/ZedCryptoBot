package es.zed.jscalp.application;

import es.zed.jscalp.domain.input.AppInputPort;
import es.zed.jscalp.domain.output.AppOutputPort;
import es.zed.jscalp.domain.output.request.AccountRequestDto;
import es.zed.jscalp.domain.output.request.AllOrderRequestDto;
import es.zed.jscalp.domain.output.request.CandlesRequestDto;
import es.zed.jscalp.domain.output.request.DepthRequestDto;
import es.zed.jscalp.domain.output.request.MyTradeRequestDto;
import es.zed.jscalp.domain.output.request.OpenOrdersRequestDto;
import es.zed.jscalp.domain.output.request.TradeRequestDto;
import es.zed.jscalp.domain.output.response.AccountResponseDto;
import es.zed.jscalp.domain.output.response.DepthDto;
import es.zed.jscalp.domain.output.response.MyTradeResponseDto;
import es.zed.jscalp.domain.output.response.OrderResponseDto;
import es.zed.jscalp.domain.output.response.OrdersResponseDto;
import es.zed.jscalp.domain.output.response.RespModel;
import es.zed.jscalp.domain.output.response.TradeResponseDto;
import es.zed.jscalp.shared.utils.MethodUtils;
import java.util.List;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class ApiService implements AppInputPort {

  private final AppOutputPort appOutputPort;

  public ApiService(AppOutputPort appOutputPort) {
    this.appOutputPort = appOutputPort;
  }

  @Override
  public RespModel<List<List<String>>> getCandles(CandlesRequestDto body) {
    return MethodUtils.buildResponse(appOutputPort.doCallGetCandles(body));
  }

  @Override
  public RespModel<DepthDto> getDepth(final DepthRequestDto body) {
    return MethodUtils.buildResponse(appOutputPort.doCallGetDepth(body));
  }

  @Override
  public RespModel<OrderResponseDto> justOrder(final Object body, final HttpMethod method) {
    return MethodUtils.buildResponse(appOutputPort.doCallJustOrder(body, method));
  }

  @Override
  public RespModel<List<OrdersResponseDto>> getAllOrders(final AllOrderRequestDto body) {
    return MethodUtils.buildResponse(appOutputPort.doCallGetAllOrders(body));
  }

  @Override
  public RespModel<List<OrdersResponseDto>> justOpenOrders(final OpenOrdersRequestDto body, final HttpMethod method) {
    return MethodUtils.buildResponse(appOutputPort.doCallOpenOrders(body, method));
  }

  @Override
  public RespModel<AccountResponseDto> getAccount(final AccountRequestDto body) {
    return MethodUtils.buildResponse(appOutputPort.doCallGetAccount(body));
  }

  @Override
  public RespModel<List<TradeResponseDto>> getTrades(TradeRequestDto body) {
    return MethodUtils.buildResponse(appOutputPort.doCallGetTrades(body));
  }

  @Override
  public RespModel<List<MyTradeResponseDto>> getMyTrades(final MyTradeRequestDto body) {
    return MethodUtils.buildResponse(appOutputPort.doCallGetMyTrades(body));
  }
}
