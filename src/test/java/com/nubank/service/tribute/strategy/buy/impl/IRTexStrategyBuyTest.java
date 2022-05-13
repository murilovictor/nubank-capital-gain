package com.nubank.service.tribute.strategy.buy.impl;

import com.nubank.model.Share;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static com.nubank.enumeration.OperationType.BUY;
import static com.nubank.util.BigDecimalUtil.zero;
import static java.math.BigDecimal.TEN;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class IRTexStrategyBuyTest {

    @InjectMocks
    private IRTributeBuyStrategy irTributeBuy;

    @Test
    public void test_should_return_ZERO_when_operation_buy() {
        Share share = Share.builder()
                .operation(BUY.getOperation())
                .unitCost(TEN)
                .quantity(TEN)
                .build();
        BigDecimal tax = irTributeBuy.calculate(share);
        assertEquals(zero(), tax);
    }
}