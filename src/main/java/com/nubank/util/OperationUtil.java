package com.nubank.util;

import com.nubank.enumeration.OperationType;
import com.nubank.model.Share;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.nubank.util.BigDecimalUtil.*;

public class OperationUtil {

    private OperationUtil() {
    }

    public static BigDecimal amountInvested(List<Share> shares) {
        return getSharesBuy(shares)
                .stream()
                .map(share -> multiply(share.getUnitCost(), share.getQuantity()))
                .reduce(zero(), BigDecimal::add);
    }

    public static BigDecimal totalSharesBuy(List<Share> shares) {
        return getSharesBuy(shares)
                .stream()
                .map(Share::getQuantity)
                .reduce(zero(), BigDecimal::add);
    }

    public static BigDecimal calculateWeightedAverageBuy(List<Share> shares) {
        return calculateWeightedAverage(getSharesBuy(shares));
    }

    private static BigDecimal calculateWeightedAverage(List<Share> shares) throws ArithmeticException {

        if (shares.size() == 1) {
            return shares.get(0).getUnitCost();
        }

        BigDecimal num = zero();
        BigDecimal weight = zero();

        for (Share share : shares) {
            num = sum(num, multiply(share.getUnitCost(), share.getQuantity()));
            weight = sum(weight, share.getQuantity());
        }

        return divide(num, weight);
    }

    private static List<Share> getSharesBuy(List<Share> shares) {
        return shares
                .stream()
                .filter(share -> share.getOperation().equals(OperationType.BUY.getOperation()))
                .collect(Collectors.toList());
    }

}
