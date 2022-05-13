package com.nubank.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtil {

    private BigDecimalUtil() {
    }

    public static boolean greaterEqual(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) >= 0;
    }

    public static boolean between(BigDecimal price, BigDecimal start, BigDecimal end) {
        return price.compareTo(start) >= 0 && price.compareTo(end) <= 0;
    }

    public static BigDecimal multiply(BigDecimal first, BigDecimal second) {
        return format(first.multiply(second));
    }

    public static BigDecimal subtract(BigDecimal first, BigDecimal second) {
        return format(first.subtract(second));
    }

    public static BigDecimal sum(BigDecimal first, BigDecimal second) {
        return format(first.add(second));
    }

    public static BigDecimal divide(BigDecimal first, BigDecimal second) {
        return format(first.divide(second, 2, RoundingMode.DOWN));
    }

    public static BigDecimal zero() {
        return BigDecimal.ZERO.setScale(2, RoundingMode.DOWN);
    }

    public static BigDecimal toBigDecimal(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.DOWN);
    }

    public static BigDecimal format(BigDecimal value) {
        return value.setScale(2, RoundingMode.DOWN);
    }

}
