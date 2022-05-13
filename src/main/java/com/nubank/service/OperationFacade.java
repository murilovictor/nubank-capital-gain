package com.nubank.service;

import com.nubank.model.Share;
import com.nubank.model.Summary;
import com.nubank.model.Tax;
import com.nubank.service.calculator.CalculatorService;
import com.nubank.service.converter.TaxConverter;
import com.nubank.service.summary.SummaryService;
import com.nubank.service.tribute.TributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationFacade {

    private final SummaryService summaryService;
    private final CalculatorService calculator;
    private final TributeService tributeService;
    private final TaxConverter taxConverter;

    @Autowired
    public OperationFacade(SummaryService summaryService, CalculatorService calculator, TributeService tributeService, TaxConverter taxConverter) {
        this.summaryService = summaryService;
        this.calculator = calculator;
        this.tributeService = tributeService;
        this.taxConverter = taxConverter;
    }

    public List<Tax> running(List<Share> shares) {
        Summary summarize = summaryService.summarize(shares);

        calculator.gainOrLoss(summarize);
        tributeService.applyTribute(summarize);

        return taxConverter.convert(summarize);
    }

}
