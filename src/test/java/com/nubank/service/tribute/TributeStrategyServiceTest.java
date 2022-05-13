package com.nubank.service.tribute;

import com.nubank.enumeration.OperationType;
import com.nubank.model.Share;
import com.nubank.model.Summary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TributeStrategyServiceTest {

    @Mock
    private TributeService tributeService;

    @Test
    public void test_should_apply_tax() {
        Summary summary = Summary
                .builder()
                .shares(Collections.singletonList(Share.builder().operation(OperationType.BUY.getOperation()).unitCost(BigDecimal.TEN).quantity(BigDecimal.TEN).build()))
                .build();

        doNothing().when(tributeService).applyTribute(summary);

        tributeService.applyTribute(summary);

        verify(tributeService, times(1)).applyTribute(summary);

    }
}