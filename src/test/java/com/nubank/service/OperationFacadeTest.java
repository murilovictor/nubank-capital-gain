package com.nubank.service;

import com.nubank.model.Share;
import com.nubank.model.Tax;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.nubank.enumeration.OperationType.BUY;
import static com.nubank.enumeration.OperationType.SELL;
import static com.nubank.util.BigDecimalUtil.toBigDecimal;
import static com.nubank.util.BigDecimalUtil.zero;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OperationFacadeTest {

    @Autowired
    private OperationFacade operationFacade;

    @Test
    public void test_must_not_apply_tax_on_any_item_case_1() {
        List<Tax> taxes = operationFacade.running(buildCase1());
        Assert.assertEquals(zero(), taxes.get(0).getTax());
        Assert.assertEquals(zero(), taxes.get(1).getTax());
        Assert.assertEquals(zero(), taxes.get(2).getTax());
    }

    @Test
    public void test_must_apply_10000_tax_in_item_2_case_2() {
        List<Tax> taxes = operationFacade.running(buildCase2());
        Assert.assertEquals(zero(), taxes.get(0).getTax());
        Assert.assertEquals(toBigDecimal(10000), taxes.get(1).getTax());
        Assert.assertEquals(zero(), taxes.get(2).getTax());
    }

    @Test
    public void test_must_apply_a_tax_of_5000_in_the_last_item_case_3() {
        List<Tax> taxes = operationFacade.running(buildCase3());
        Assert.assertEquals(zero(), taxes.get(0).getTax());
        Assert.assertEquals(zero(), taxes.get(1).getTax());
        Assert.assertEquals(toBigDecimal(5000), taxes.get(2).getTax());
    }

    @Test
    public void test_must_not_apply_tax_on_any_item_case_4() {
        List<Tax> taxes = operationFacade.running(buildCase4());
        Assert.assertEquals(zero(), taxes.get(0).getTax());
        Assert.assertEquals(zero(), taxes.get(1).getTax());
        Assert.assertEquals(zero(), taxes.get(2).getTax());
    }

    @Test
    public void test_must_apply_a_tax_of_10000_in_the_last_item_case_5() {
        List<Tax> taxes = operationFacade.running(buildCase5());
        Assert.assertEquals(zero(), taxes.get(0).getTax());
        Assert.assertEquals(zero(), taxes.get(1).getTax());
        Assert.assertEquals(zero(), taxes.get(2).getTax());
        Assert.assertEquals(toBigDecimal(10000), taxes.get(3).getTax());
    }

    @Test
    public void test_must_apply_a_tax_of_3000_in_the_last_item_case_6() {
        List<Tax> taxes = operationFacade.running(buildCase6());
        Assert.assertEquals(zero(), taxes.get(0).getTax());
        Assert.assertEquals(zero(), taxes.get(1).getTax());
        Assert.assertEquals(zero(), taxes.get(2).getTax());
        Assert.assertEquals(zero(), taxes.get(3).getTax());
        Assert.assertEquals(toBigDecimal(3000), taxes.get(4).getTax());
    }

    private List<Share> buildCase1() {
        return Arrays.asList(
                Share.builder().operation(BUY.getOperation()).unitCost(toBigDecimal(10)).quantity(toBigDecimal(100)).build(),
                Share.builder().operation(SELL.getOperation()).unitCost(toBigDecimal(15)).quantity(toBigDecimal(50)).build(),
                Share.builder().operation(SELL.getOperation()).unitCost(toBigDecimal(15)).quantity(toBigDecimal(50)).build());
    }

    private List<Share> buildCase2() {
        return Arrays.asList(
                Share.builder().operation(BUY.getOperation()).unitCost(toBigDecimal(10)).quantity(toBigDecimal(10000)).build(),
                Share.builder().operation(SELL.getOperation()).unitCost(toBigDecimal(20)).quantity(toBigDecimal(5000)).build(),
                Share.builder().operation(SELL.getOperation()).unitCost(toBigDecimal(5)).quantity(toBigDecimal(5000)).build());
    }

    private List<Share> buildCase3() {
        return Arrays.asList(
                Share.builder().operation(BUY.getOperation()).unitCost(toBigDecimal(10)).quantity(toBigDecimal(10000)).build(),
                Share.builder().operation(SELL.getOperation()).unitCost(toBigDecimal(5)).quantity(toBigDecimal(5000)).build(),
                Share.builder().operation(SELL.getOperation()).unitCost(toBigDecimal(20)).quantity(toBigDecimal(5000)).build());
    }

    private List<Share> buildCase4() {
        return Arrays.asList(
                Share.builder().operation(BUY.getOperation()).unitCost(toBigDecimal(10)).quantity(toBigDecimal(10000)).build(),
                Share.builder().operation(BUY.getOperation()).unitCost(toBigDecimal(25)).quantity(toBigDecimal(5000)).build(),
                Share.builder().operation(SELL.getOperation()).unitCost(toBigDecimal(15)).quantity(toBigDecimal(10000)).build());
    }

    private List<Share> buildCase5() {
        return Arrays.asList(
                Share.builder().operation(BUY.getOperation()).unitCost(toBigDecimal(10)).quantity(toBigDecimal(10000)).build(),
                Share.builder().operation(BUY.getOperation()).unitCost(toBigDecimal(25)).quantity(toBigDecimal(5000)).build(),
                Share.builder().operation(SELL.getOperation()).unitCost(toBigDecimal(15)).quantity(toBigDecimal(10000)).build(),
                Share.builder().operation(SELL.getOperation()).unitCost(toBigDecimal(25)).quantity(toBigDecimal(5000)).build());
    }

    private List<Share> buildCase6() {
        return Arrays.asList(
                Share.builder().operation(BUY.getOperation()).unitCost(toBigDecimal(10)).quantity(toBigDecimal(10000)).build(),
                Share.builder().operation(SELL.getOperation()).unitCost(toBigDecimal(2)).quantity(toBigDecimal(5000)).build(),
                Share.builder().operation(SELL.getOperation()).unitCost(toBigDecimal(20)).quantity(toBigDecimal(2000)).build(),
                Share.builder().operation(SELL.getOperation()).unitCost(toBigDecimal(20)).quantity(toBigDecimal(2000)).build(),
                Share.builder().operation(SELL.getOperation()).unitCost(toBigDecimal(25)).quantity(toBigDecimal(1000)).build());
    }
}