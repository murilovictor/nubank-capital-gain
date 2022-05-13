package com.nubank.service.tribute.strategy;

import com.nubank.model.Share;

import java.math.BigDecimal;

public interface TributeStrategy {

    BigDecimal tax(Share share);

}
