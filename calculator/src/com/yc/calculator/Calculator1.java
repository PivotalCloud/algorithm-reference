package com.yc.calculator;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Stack;

/**
 * @author A80390
 */
public class Calculator1 {
    /**
     * 存储历史操作符
     */
    private final Stack<Character> historyOptStack = new Stack<>();
    private final Stack<Character> tmpOptStack = new Stack<>();

    /**
     * 存储历史总和
     */
    private final Stack<BigDecimal> historyTotalStack = new Stack<>();
    private final Stack<BigDecimal> tmpTotalStack = new Stack<>();

    /**
     * 存储历史输入
     */
    private final Stack<BigDecimal> historyInputStack = new Stack<>();
    private final Stack<BigDecimal> tmpInputStack = new Stack<>();

    /**
     * 是否计算结果前产生过undo操作
     */
    private boolean undoFlag = false;
    /**
     * 当前总和
     */
    private BigDecimal total;
    /**
     * 当前输入
     */
    private BigDecimal input;
    /**
     * 当前操作符
     */
    private Character opt;

    public void setInput(BigDecimal input) {
        if (Objects.isNull(total)) {
            this.total = input;
            historyTotalStack.push(total);
        } else {
            this.input = input;
        }
    }

    public void setOpt(Character opt) {
        this.opt = opt;
    }

    /**
     * 计算结果
     */
    private void calResult() throws Exception {
        CalculatorInterface calculatorInterface;
        switch (opt) {
            case '+':
                calculatorInterface = new AddOperation();
                total = calculatorInterface.apply(total, input);
                break;
            case '-':
                calculatorInterface = new SubOperation();
                total = calculatorInterface.apply(total, input);
                break;
            case '*':
                calculatorInterface = new MultiplyOperation();
                total = calculatorInterface.apply(total, input);
                break;
            case '/':
                calculatorInterface = new DivideOperation();
                total = calculatorInterface.apply(total, input);
                break;
            default:
                throw new Exception("操作符输入有误!");
        }
        if (undoFlag) {
            tmpOptStack.clear();
            tmpInputStack.clear();
            tmpTotalStack.clear();
            undoFlag = false;
        }
        pushItemToStack(total, opt, input);
    }

    /**
     * 把要素压入栈
     * @param total     总数
     * @param opt       操作符
     * @param input     输入
     */
    private void pushItemToStack(BigDecimal total, Character opt, BigDecimal input) {
        historyTotalStack.push(total);
        historyOptStack.push(opt);
        historyInputStack.push(input);
    }

    /**
     * 撤回
     */
    private void undo() {
        if (historyTotalStack.size() == 1) {
            System.out.println("无法undo!");
            return;
        }
        tmpTotalStack.push(historyTotalStack.pop());
        Character undoOpt = historyOptStack.pop();
        BigDecimal undoInput = historyInputStack.pop();
        tmpInputStack.push(undoInput);
        tmpOptStack.push(undoOpt);
        BigDecimal undoTotal = historyTotalStack.get(historyTotalStack.size() - 1);
        System.out.println("undo前的值是:" + total + " undo后的值是:" + undoTotal + " undo的操作:" + undoOpt + " undo的输入:" + undoInput);
        total = undoTotal;
        undoFlag = true;
    }

    /**
     * 撤销undo操作
     */
    private void redo() {
        if (tmpTotalStack.size() == 0) {
            System.out.println("无法redo!");
            return;
        }
        BigDecimal redoTotal = tmpTotalStack.pop();
        Character redoOpt = tmpOptStack.pop();
        BigDecimal redoInput = tmpInputStack.pop();
        historyOptStack.push(redoOpt);
        historyInputStack.push(redoInput);
        historyTotalStack.push(redoTotal);
        System.out.println("redo前的值是:" + total + " redo后的值是:" + redoTotal + " redo的操作:" + redoOpt + " redo的输入:" + redoInput);
        total = redoTotal;
    }

    /**
     * 展示总数
     */
    private void displayTotal() {
        System.out.println(total);
    }

    /**
     * 展示计算过程
     */
    private void displayProcess() {
        System.out.println("" + total + opt + input);
    }

    public static void main(String[] args) throws Exception {
        Calculator1 calculator1 = new Calculator1();
        calculator1.setInput(new BigDecimal(3));
        calculator1.setOpt('+');
        calculator1.setInput(new BigDecimal(5));
        calculator1.displayProcess();
        calculator1.calResult();
        calculator1.displayTotal();
        calculator1.setOpt('*');
        calculator1.setInput(new BigDecimal(2));
        calculator1.displayProcess();
        calculator1.calResult();
        calculator1.displayTotal();
        calculator1.redo();
        calculator1.undo();
        calculator1.undo();
        calculator1.redo();
        calculator1.setOpt('*');
        calculator1.setInput(new BigDecimal(2));
        calculator1.displayProcess();
        calculator1.calResult();
        calculator1.displayTotal();
        calculator1.undo();

        System.out.println("开始打断undo并附加额外计算:+2");
        calculator1.setOpt('+');
        calculator1.setInput(new BigDecimal(2));
        calculator1.displayProcess();
        calculator1.calResult();
        calculator1.displayTotal();
        System.out.println("打断计算结束,重新进行undo/redo操作!");

        calculator1.undo();
        calculator1.undo();
        calculator1.redo();
        calculator1.redo();
        calculator1.redo();
        calculator1.redo();
    }
}
