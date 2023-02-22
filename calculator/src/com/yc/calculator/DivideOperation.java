package com.yc.calculator;

import java.math.BigDecimal;

/**
 * 除法处理类
 * @author A80390
 */
public class DivideOperation implements CalculatorInterface{

    /**
     * 除法处理(保留两位小数，四舍五入)
     * @param total      历史总和
     * @param newNum     新的输入
     * @return
     */
    @Override
    public BigDecimal apply(BigDecimal total, BigDecimal newNum) {
        System.out.println("除法处理-------------------------firstNum:" + total + ",secondNum:"+ newNum);
        return total.divide(newNum, 2, BigDecimal.ROUND_HALF_UP);
    }
}
