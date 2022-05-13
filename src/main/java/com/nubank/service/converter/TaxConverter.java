package com.nubank.service.converter;

import com.nubank.model.Share;
import com.nubank.model.Summary;
import com.nubank.model.Tax;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaxConverter {

    public List<Tax> convert(Summary summary) {
        return summary.getShares()
                .stream()
                .map(Share::getTax)
                .map(Tax::new)
                .collect(Collectors.toList());
    }

}
