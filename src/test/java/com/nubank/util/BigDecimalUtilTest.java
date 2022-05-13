package com.nubank.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.nubank.util.BigDecimalUtil.*;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BigDecimalUtilTest {

    @Test
    public void test_should_10_greater_equal_10_when_true() {
        boolean greaterEqual = greaterEqual(BigDecimal.TEN, BigDecimal.TEN);
        assertTrue(greaterEqual);
    }

    @Test
    public void test_should_9_greater_equal_10_when_false() {
        boolean greaterEqual = greaterEqual(toBigDecimal(9), BigDecimal.TEN);
        assertFalse(greaterEqual);
    }

    @Test
    public void test_should_the_10_must_be_between_0_and_10() {
        boolean between = between(BigDecimal.TEN, zero(), BigDecimal.TEN);
        assertTrue(between);
    }

    @Test
    public void test_should_the_11_not_must_be_between_0_and_10() {
        boolean between = between(toBigDecimal(11), zero(), BigDecimal.TEN);
        assertFalse(between);
    }

    @Test
    public void test_should_multiply_before_two_values() {
        BigDecimal multiply = multiply(BigDecimal.TEN, BigDecimal.TEN);
        assertEquals(toBigDecimal(100), multiply);
    }

    @Test
    public void test_should_subtract_before_two_values() {
        BigDecimal subtract = subtract(BigDecimal.TEN, BigDecimal.TEN);
        assertEquals(zero(), subtract);
    }

    @Test
    public void test_should_sum_before_two_values() {
        BigDecimal sum = sum(BigDecimal.TEN, BigDecimal.TEN);
        assertEquals(toBigDecimal(20), sum);
    }

    @Test
    public void test_should_divide_before_two_values() {
        BigDecimal ten = BigDecimal.TEN.setScale(2, RoundingMode.DOWN);
        BigDecimal divide = divide(BigDecimal.TEN, BigDecimal.ONE);
        assertEquals(ten, divide);
    }
}