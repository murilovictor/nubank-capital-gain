package com.nubank.service.summary;

import com.nubank.model.Share;
import com.nubank.model.Summary;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nubank.util.OperationUtil.*;

@Service
public class SummaryService {

    public Summary summarize(List<Share> shares) {
        return Summary.builder()
                .totalAmountOfShares(totalSharesBuy(shares))
                .totalInvestmentAmount(amountInvested(shares))
                .averageWeightedValue(calculateWeightedAverageBuy(shares))
                .shares(shares)
                .build();
    }

}
