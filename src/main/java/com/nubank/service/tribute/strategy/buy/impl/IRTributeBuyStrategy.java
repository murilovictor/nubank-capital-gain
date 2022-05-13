package com.nubank.service.tribute.strategy.buy.impl;

import com.nubank.model.Share;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.nubank.util.BigDecimalUtil.zero;

@Service
public class IRTributeBuyStrategy implements TributeBuyStrategy {

    @Override
    public BigDecimal calculate(Share share) {
        return zero();
    }
}
