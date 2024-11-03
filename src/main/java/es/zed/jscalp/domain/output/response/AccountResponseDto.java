package es.zed.jscalp.domain.output.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class AccountResponseDto {

  private int makerCommission;
  private int takerCommission;
  private int buyerCommission;
  private int sellerCommission;
  private CommissionRatesDto commissionRates;
  private boolean canTrade;
  private boolean canWithdraw;
  private boolean canDeposit;
  private boolean brokered;
  private boolean requireSelfTradePrevention;
  private boolean preventSor;
  private String updateTime;
  private String accountType;
  private List<BalanceDto> balances; // Aquí está la lista de balances
  private List<String> permissions; // Lista de permisos
  private String uid;

  @Data
  @NoArgsConstructor
  @JsonInclude(Include.NON_NULL)
  public static class CommissionRatesDto {
    private String maker;
    private String taker;
    private String buyer;
    private String seller;
  }

  @Data
  @NoArgsConstructor
  @JsonInclude(Include.NON_NULL)
  public static class BalanceDto {
    private String asset; // El símbolo de la criptomoneda
    private String free;  // Cantidad libre
    private String locked; // Cantidad bloqueada
  }
}