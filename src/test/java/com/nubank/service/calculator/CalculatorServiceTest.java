package com.nubank.service.calculator;

import com.nubank.enumeration.OperationType;
import com.nubank.model.Share;
import com.nubank.model.Summary;
import com.nubank.service.summary.SummaryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.nubank.util.BigDecimalUtil.toBigDecimal;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CalculatorServiceTest {

    @InjectMocks
    private CalculatorService calculatorService;

    @InjectMocks
    private SummaryService summaryService;

    @Test
    public void test_should_calculator_gain_or_loss() {

        List<Share> shares = new ArrayList<>();
        shares.add(Share.builder().operation(OperationType.BUY.getOperation()).unitCost(BigDecimal.TEN).quantity(BigDecimal.TEN).build());
        shares.add(Share.builder().operation(OperationType.BUY.getOperation()).unitCost(BigDecimal.ONE).quantity(BigDecimal.TEN).build());
        shares.add(Share.builder().operation(OperationType.SELL.getOperation()).unitCost(BigDecimal.TEN).quantity(BigDecimal.TEN).build());

        Summary summarize = summaryService.summarize(shares);
        calculatorService.gainOrLoss(summarize);
        Assert.assertEquals(toBigDecimal(0), summarize.getLoss());

    }
}