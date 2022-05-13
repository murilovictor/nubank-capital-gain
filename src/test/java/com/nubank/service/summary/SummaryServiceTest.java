package com.nubank.service.summary;

import com.nubank.enumeration.OperationType;
import com.nubank.model.Share;
import com.nubank.model.Summary;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SummaryServiceTest {


    @InjectMocks
    private SummaryService summaryService;

    @Test
    public void test_should_summarize_operations() {
        List<Share> shares = new ArrayList<>();

        shares.add(Share.builder().operation(OperationType.BUY.getOperation()).unitCost(BigDecimal.TEN).quantity(BigDecimal.TEN).build());
        shares.add(Share.builder().operation(OperationType.BUY.getOperation()).unitCost(BigDecimal.ONE).quantity(BigDecimal.TEN).build());
        shares.add(Share.builder().operation(OperationType.SELL.getOperation()).unitCost(BigDecimal.TEN).quantity(BigDecimal.TEN).build());

        Summary summarize = summaryService.summarize(shares);

        Assert.assertNotNull("Shares not null", summarize.getShares());
        Assert.assertNotNull("AverageWeightedValue not null", summarize.getAverageWeightedValue());
        Assert.assertNotNull("TotalInvestmentAmount not null", summarize.getTotalInvestmentAmount());
        Assert.assertNotNull("TotalAmountOfShares not null", summarize.getTotalAmountOfShares());

    }
}