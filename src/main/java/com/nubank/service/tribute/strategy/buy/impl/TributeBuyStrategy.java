package com.nubank.service.tribute.strategy.buy.impl;

import com.nubank.model.Share;

import java.math.BigDecimal;

public interface TributeBuyStrategy {

    BigDecimal calculate(Share share);

}
