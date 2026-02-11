package com.calculator.calculator_app.service;

import com.calculator.calculator_app.model.Calculation;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public Calculation performCalculation(Calculation calculation) {
        double result = 0;
        String op = calculation.getOperation();

        switch (op) {
            case "add":
                result = calculation.getNumber1() + calculation.getNumber2();
                break;
            case "subtract":
                result = calculation.getNumber1() - calculation.getNumber2();
                break;
            case "multiply":
                result = calculation.getNumber1() * calculation.getNumber2();
                break;
            case "divide":
                if (calculation.getNumber2() == 0) {
                    throw new ArithmeticException("Cannot divide by zero!");
                }
                result = calculation.getNumber1() / calculation.getNumber2();
                break;
            case "power":
                result = Math.pow(calculation.getNumber1(), calculation.getNumber2());
                break;
            case "sqrt":
                if (calculation.getNumber1() < 0) {
                    throw new ArithmeticException("Cannot take square root of negative number!");
                }
                result = Math.sqrt(calculation.getNumber1());
                break;
            default:
                throw new IllegalArgumentException("Invalid operation: " + op);
        }

        // Round to 2 decimal places
        calculation.setResult(Math.round(result * 100.0) / 100.0);
        return calculation;
    }
}