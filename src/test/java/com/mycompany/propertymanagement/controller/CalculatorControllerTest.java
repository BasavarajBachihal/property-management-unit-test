package com.mycompany.propertymanagement.controller;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CalculatorControllerTest {
    @InjectMocks
    private CalculatorController calculatorController;

    static Double num1;
    static Double num2;
    static Double num3;
    @BeforeAll
    static void beforeAll(){
        System.out.println("before All");
        num1 = 3.5;
        num2 = 3.5;
        num3 = 3.5;
    }
    @BeforeEach
    void init(){
        System.out.println("Before Each");
    }
    @AfterEach
    void destroy(){
        System.out.println("After Each");
    }
    @AfterAll
    static void afterAll(){
        System.out.println("After All");
        num1 = null;
        num2 = null;
        num3 = null;
    }
    @Test
    @DisplayName("Test Addtion Success Scenario")
    void testAddFunction_Success(){
        System.out.println("Test Addtion Succes Scenario");
        Double result = calculatorController.add(num1,num2,num3);
        assertEquals(7.0,result);
    }
    @Test
    @DisplayName("Test Addtion Failure Scenario")
    void testAddFunction_Failure(){
        System.out.println("Test Addtion Failure Scenario");
        Double result = calculatorController.add(num1-0.5,num2,num3);
        Assertions.assertNotEquals(7.0,result);
    }
    @Test
    @DisplayName("Test Substraction for num1>num2 Scenario")
    public void testSubFunction_num1_gt_num2(){
        Double result = calculatorController.substract(num1+1,num2);
        assertEquals(1.0,result);
    }
    @Test
    @DisplayName("Test Substraction for num1>num2 Scenario")
    public void testSubFunction_num1_1t_num2(){
        Double result = calculatorController.substract(num1,num2+1);
        assertEquals(1.0,result);
    }

}
