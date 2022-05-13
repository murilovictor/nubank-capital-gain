package com.nubank.service.tribute.strategy.sell.impl;

import com.nubank.model.Share;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.nubank.util.BigDecimalUtil.*;

@Service
public class IRTributeSellStrategy implements TributeSellStrategy {

    private static final BigDecimal MAX_VALUE = toBigDecimal(20000);
    private static final BigDecimal PERCENT = toBigDecimal(0.2);

    @Override
    public BigDecimal calculate(Share share) {

        if (between(share.getTotalInvestmentAmount(), zero(), MAX_VALUE)) {
            return zero();
        }

        if (greaterEqual(share.getGainOrLoss(), zero())) {
            return multiply(share.getGainOrLoss(), PERCENT);
        }

        return zero();
    }
}
