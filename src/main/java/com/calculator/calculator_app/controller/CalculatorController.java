package com.calculator.calculator_app.controller;

import com.calculator.calculator_app.model.Calculation;
import com.calculator.calculator_app.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    // Show the calculator page
    @GetMapping("/")
    public String showCalculator(Model model) {
        model.addAttribute("calculation", new Calculation());
        model.addAttribute("result", null);   // no result on first load
        return "calculator";
    }

    // Process the calculation (called by the calculator UI)
    @PostMapping("/calculate")
    public String calculate(@ModelAttribute Calculation calculation, Model model) {
        try {
            Calculation result = calculatorService.performCalculation(calculation);
            model.addAttribute("result", result.getResult());
            model.addAttribute("calculation", calculation);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("calculation", calculation);
        }
        return "calculator";
    }

    // ---------- Optional REST endpoints for testing / API usage ----------
    @GetMapping("/api/add")
    @ResponseBody
    public Calculation add(@RequestParam double num1, @RequestParam double num2) {
        Calculation calc = new Calculation(num1, num2, "add");
        return calculatorService.performCalculation(calc);
    }

    @GetMapping("/api/subtract")
    @ResponseBody
    public Calculation subtract(@RequestParam double num1, @RequestParam double num2) {
        Calculation calc = new Calculation(num1, num2, "subtract");
        return calculatorService.performCalculation(calc);
    }

    @GetMapping("/api/multiply")
    @ResponseBody
    public Calculation multiply(@RequestParam double num1, @RequestParam double num2) {
        Calculation calc = new Calculation(num1, num2, "multiply");
        return calculatorService.performCalculation(calc);
    }

    @GetMapping("/api/divide")
    @ResponseBody
    public Calculation divide(@RequestParam double num1, @RequestParam double num2) {
        Calculation calc = new Calculation(num1, num2, "divide");
        return calculatorService.performCalculation(calc);
    }
}