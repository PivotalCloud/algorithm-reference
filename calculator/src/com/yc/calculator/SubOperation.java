package com.yc.calculator;

import java.math.BigDecimal;

/**
 * 减法处理类
 * @author A80390
 */
public class SubOperation implements CalculatorInterface{

    /**
     * 减法处理
     * @param total      历史总和
     * @param newNum     新的输入
     * @return
     */
    @Override
    public BigDecimal apply(BigDecimal total, BigDecimal newNum) {
        System.out.println("减法处理-------------------------firstNum:" + total + ",secondNum:"+ newNum);
        return total.subtract(newNum);
    }
}
