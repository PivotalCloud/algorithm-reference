package com.yc.calculator;

import java.math.BigDecimal;

/**
 * 计算器类
 * 使用工厂设计模式
 * @author A80390
 */
public interface CalculatorInterface {
    /**
     * 计算器类的抽象接口
     * @param total      历史总和
     * @param newNum     新的输入
     * @return
     */
    BigDecimal apply(BigDecimal total, BigDecimal newNum);
}
