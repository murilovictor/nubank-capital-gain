package com.nubank.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Tax {

    private BigDecimal tax;

    public Tax(BigDecimal tax) {
        this.tax = tax;
    }
}
