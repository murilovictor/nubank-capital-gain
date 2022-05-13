package com.nubank.service.calculator;

import com.nubank.model.Share;
import com.nubank.model.Summary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.nubank.util.BigDecimalUtil.*;

@Service
public class CalculatorService {

    public void gainOrLoss(Summary summary) {

        for (Share share : summary.getShares()) {
            BigDecimal totalInvestmentAmount = multiply(summary.getAverageWeightedValue(), share.getQuantity());
            BigDecimal gainOrLoss = subtract(share.getTotalInvestmentAmount(), totalInvestmentAmount);

            if (unitCostGreaterEqualAverageWeight(summary, share)) {
                gainOrLoss = sum(gainOrLoss, summary.getLoss());
            }

            summary.setLoss(gainOrLoss);
            share.setGainOrLoss(gainOrLoss);
        }

    }

    private boolean unitCostGreaterEqualAverageWeight(Summary summary, Share share) {
        return greaterEqual(share.getUnitCost(), summary.getAverageWeightedValue());
    }

}
