package com.nubank.service.tribute.strategy.sell;

import com.nubank.model.Share;
import com.nubank.service.tribute.strategy.TributeStrategy;
import com.nubank.service.tribute.strategy.sell.impl.TributeSellStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.nubank.util.BigDecimalUtil.zero;

@Service
@Order(11)
public class TributeStrategySell implements TributeStrategy {

    @Autowired
    private List<TributeSellStrategy> tributeSells;

    @Override
    public BigDecimal tax(Share share) {
        return tributeSells
                .stream()
                .map(tributeSell -> tributeSell.calculate(share))
                .reduce(zero(), BigDecimal::add);
    }
}
