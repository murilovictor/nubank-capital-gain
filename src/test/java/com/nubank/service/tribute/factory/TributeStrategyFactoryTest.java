package com.nubank.service.tribute.factory;

import com.nubank.model.Share;
import com.nubank.service.tribute.strategy.TributeStrategy;
import com.nubank.service.tribute.strategy.buy.TributeStrategyBuy;
import com.nubank.service.tribute.strategy.sell.TributeStrategySell;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static com.nubank.enumeration.OperationType.BUY;
import static com.nubank.enumeration.OperationType.SELL;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TributeStrategyFactoryTest {

    @Mock
    private TributeStrategyBuy tributeStrategyBuy;

    @Mock
    private TributeStrategySell tributeStrategySell;

    @InjectMocks
    private TributeFactory tributeFactory;

    @Test
    public void test_should_return_instance_TributeBuy() {
        Share share = Share.builder()
                .operation(BUY.getOperation())
                .build();

        TributeStrategy tributeStrategyBuy = tributeFactory.apply(share.getOperation());

        Assert.assertEquals(this.tributeStrategyBuy, tributeStrategyBuy);

    }

    @Test
    public void test_should_return_instance_TributeSell() {
        Share share = Share.builder()
                .operation(SELL.getOperation())
                .build();

        TributeStrategy tributeStrategySell = tributeFactory.apply(share.getOperation());

        Assert.assertEquals(this.tributeStrategySell, tributeStrategySell);

    }
}