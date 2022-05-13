package com.nubank.service.tribute.strategy.sell.impl;

import com.nubank.model.Share;

import java.math.BigDecimal;

public interface TributeSellStrategy {

    BigDecimal calculate(Share share);

}
