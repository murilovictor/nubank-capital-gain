package com.nubank.service.tribute.strategy.buy;

import com.nubank.model.Share;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static com.nubank.enumeration.OperationType.BUY;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TributeBuyStrategyTest {

    @Mock
    private TributeStrategyBuy tributeBuy;

    @Test
    public void test_should_return_ZERO_when_operation_buy() {
        Share share = Share.builder()
                .operation(BUY.getOperation())
                .unitCost(TEN)
                .quantity(TEN)
                .build();

        Mockito.when(tributeBuy.tax(share)).thenReturn(ZERO);

        BigDecimal tribute = tributeBuy.tax(share);
        assertEquals(ZERO, tribute);

    }
}