package com.nubank.service.tribute;

import com.nubank.model.Share;
import com.nubank.model.Summary;
import com.nubank.service.tribute.factory.TributeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.nubank.util.BigDecimalUtil.format;

@Service
public class TributeService {

    private final TributeFactory tributeFactory;

    @Autowired
    public TributeService(TributeFactory tributeFactory) {
        this.tributeFactory = tributeFactory;
    }

    public void applyTribute(Summary summary) {
        summary.getShares().forEach(this::applyTax);
    }

    private void applyTax(Share share) {
        share.setTax(format(tributeFactory.apply(share.getOperation()).tax(share)));
    }

}
