package com.nubank.service.tribute.strategy.sell.impl;

import com.nubank.model.Share;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static com.nubank.enumeration.OperationType.SELL;
import static com.nubank.util.BigDecimalUtil.toBigDecimal;
import static com.nubank.util.BigDecimalUtil.zero;
import static java.math.BigDecimal.TEN;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class IRTributeStrategySellTest {

    @InjectMocks
    private IRTributeSellStrategy irTributeSell;

    @Test
    public void test_should_exempt_tribute_when_TotalInvestmentAmount_be_between_0_and_20000() {
        Share share = Share.builder()
                .totalInvestmentAmount(toBigDecimal(10000))
                .unitCost(TEN)
                .quantity(TEN)
                .build();

        BigDecimal tribute = irTributeSell.calculate(share);

        Assert.assertEquals(zero(), tribute);
    }

    @Test
    public void test_should_assess_when_gainOrLoss_be_greaterEqual_0() {
        Share share = Share.builder()
                .operation(SELL.getOperation())
                .gainOrLoss(toBigDecimal(10000))
                .unitCost(toBigDecimal(10000))
                .quantity(TEN)
                .build();

        BigDecimal tribute = irTributeSell.calculate(share);

        Assert.assertEquals(toBigDecimal(2000), tribute);
    }

    @Test
    public void test_should_exempt_when_gainOrLoss_be_lesserEquals_0() {
        Share share = Share.builder()
                .operation(SELL.getOperation())
                .gainOrLoss(toBigDecimal(-10000))
                .unitCost(toBigDecimal(10000))
                .quantity(TEN)
                .build();

        BigDecimal tribute = irTributeSell.calculate(share);

        Assert.assertEquals(zero(), tribute);
    }
}