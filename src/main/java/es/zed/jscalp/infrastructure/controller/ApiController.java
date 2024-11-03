package es.zed.jscalp.infrastructure.controller;

import es.zed.jscalp.domain.input.AppInputPort;
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
import es.zed.jscalp.domain.output.response.RespModel;
import es.zed.jscalp.domain.output.response.TradeResponseDto;
import es.zed.jscalp.shared.Constants;
import java.util.List;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.DEFAULT_MAPPING)
public class ApiController {

  private final AppInputPort appInputPort;

  public ApiController(AppInputPort appInputPort) {
    this.appInputPort = appInputPort;
  }

  @GetMapping(Constants.ORDER_BOOK_PATH)
  public RespModel<DepthDto> getDepth(@RequestBody final DepthRequestDto body) {
    return this.appInputPort.getDepth(body);
  }

  @GetMapping(Constants.ORDER_PATH)
  public RespModel<OrderResponseDto> getOrder(@RequestBody final GetOrderRequestDto body) {
    return this.appInputPort.justOrder(body, HttpMethod.GET);
  }

  @PostMapping(Constants.ORDER_PATH)
  public RespModel<OrderResponseDto> postOrder(@RequestBody final PostOrderRequestDto body) {
    return this.appInputPort.justOrder(body, HttpMethod.POST);
  }

  @DeleteMapping(Constants.ORDER_PATH)
  public RespModel<OrderResponseDto> deleteOrder(@RequestBody final DeleteOrderRequestDto body) {
    return this.appInputPort.justOrder(body, HttpMethod.DELETE);
  }

  @GetMapping(Constants.OPEN_ORDERS_PATH)
  public RespModel<List<OrdersResponseDto>> getOpenOrders(@RequestBody final OpenOrdersRequestDto body) {
    return this.appInputPort.justOpenOrders(body, HttpMethod.GET);
  }

  @DeleteMapping(Constants.OPEN_ORDERS_PATH)
  public RespModel<List<OrdersResponseDto>> deleteOpenOrders(@RequestBody final OpenOrdersRequestDto body) {
    return this.appInputPort.justOpenOrders(body, HttpMethod.DELETE);
  }

  @GetMapping(Constants.ALL_ORDER_PATH)
  public RespModel<List<OrdersResponseDto>> getAllOrder(@RequestBody final AllOrderRequestDto body) {
    return this.appInputPort.getAllOrders(body);
  }

  @GetMapping(Constants.ACCOUNT_PATH)
  public RespModel<AccountResponseDto> getAccount(@RequestBody final AccountRequestDto body) {
    return this.appInputPort.getAccount(body);
  }

  @GetMapping(Constants.MY_TRADES_PATH)
  public RespModel<List<TradeResponseDto>> getMyTrades(@RequestBody final TradeRequestDto body) {
    return this.appInputPort.getMyTrades(body);
  }
}
