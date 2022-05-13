package com.nubank.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

import static com.nubank.util.BigDecimalUtil.zero;

@Data
@Builder
public class Summary {

    private BigDecimal averageWeightedValue;
    private BigDecimal totalInvestmentAmount;
    private BigDecimal totalAmountOfShares;
    private BigDecimal loss;
    private List<Share> shares;

    public BigDecimal getLoss() {
        if (loss == null) {
            loss = zero();
        }
        return loss;
    }
}
