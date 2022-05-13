package com.nubank.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.nubank.util.BigDecimalUtil.multiply;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Share {

    @JsonProperty("operation")
    private String operation;

    @JsonProperty("unit-cost")
    private BigDecimal unitCost;

    @JsonProperty("quantity")
    private BigDecimal quantity;

    private BigDecimal totalInvestmentAmount;
    private BigDecimal tax;
    private BigDecimal gainOrLoss;

    public BigDecimal getTotalInvestmentAmount() {
        return multiply(getUnitCost(), getQuantity());
    }
}
