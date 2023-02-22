package com.yc.calculator;

import java.math.BigDecimal;

/**
 * 加法处理类
 * @author A80390
 */
public class AddOperation implements CalculatorInterface{

    /**
     * 加法处理
     * @param total      历史总和
     * @param newNum     新的输入
     * @return
     */
    @Override
    public BigDecimal apply(BigDecimal total, BigDecimal newNum) {
        System.out.println("加法处理-------------------------firstNum:" + total + ",secondNum:"+ newNum);
        return total.add(newNum);
    }
}
