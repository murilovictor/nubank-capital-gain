package com.nubank.service.converter;

import com.nubank.enumeration.OperationType;
import com.nubank.model.Share;
import com.nubank.model.Summary;
import com.nubank.model.Tax;
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
public class TaxConverterTest {

    @InjectMocks
    private TaxConverter taxConverter;

    @Test
    public void test_convert_summary_in_tax_list() {
        List<Share> shares = new ArrayList<>();
        shares.add(Share.builder().operation(OperationType.BUY.getOperation()).unitCost(BigDecimal.TEN).quantity(BigDecimal.TEN).build());
        shares.add(Share.builder().operation(OperationType.BUY.getOperation()).unitCost(BigDecimal.ONE).quantity(BigDecimal.TEN).build());
        shares.add(Share.builder().operation(OperationType.SELL.getOperation()).unitCost(BigDecimal.TEN).quantity(BigDecimal.TEN).build());

        Summary summary = Summary
                .builder()
                .shares(shares)
                .build();

        List<Tax> taxes = taxConverter.convert(summary);

        Assert.assertEquals(3, taxes.size());

    }
}