package com.nubank.service.tribute.strategy.buy;

import com.nubank.model.Share;
import com.nubank.service.tribute.strategy.TributeStrategy;
import com.nubank.service.tribute.strategy.buy.impl.TributeBuyStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.nubank.util.BigDecimalUtil.zero;

@Service
@Order(10)
public class TributeStrategyBuy implements TributeStrategy {

    @Autowired
    private List<TributeBuyStrategy> tributeBuys;

    public BigDecimal tax(Share share) {
        return tributeBuys
                .stream()
                .map(tributeBuyStrategy -> tributeBuyStrategy.calculate(share))
                .reduce(zero(), BigDecimal::add);
    }

}
