package com.nubank.service.tribute.factory;

import com.nubank.enumeration.OperationType;
import com.nubank.service.tribute.strategy.TributeStrategy;
import com.nubank.service.tribute.strategy.buy.TributeStrategyBuy;
import com.nubank.service.tribute.strategy.sell.TributeStrategySell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Order(999)
@Service
public class TributeFactory {
    private final Map<String, TributeStrategy> tributes = new HashMap<>();

    @Autowired
    public TributeFactory(TributeStrategyBuy tributeBuy, TributeStrategySell tributeSell) {
        tributes.put(OperationType.BUY.getOperation(), tributeBuy);
        tributes.put(OperationType.SELL.getOperation(), tributeSell);
    }

    public TributeFactory() {
    }

    public TributeStrategy apply(String operation) {
        return tributes.get(operation);
    }

}
