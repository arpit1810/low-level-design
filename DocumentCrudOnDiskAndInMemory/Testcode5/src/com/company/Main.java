package com.company;

import com.company.test.Testcases;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello World");
        JUnitCore jUnitCore = new JUnitCore();
        Result result = jUnitCore.run(Testcases.class);
        System.out.println("Total testcases : " + result.getRunCount());
        System.out.println("Failed testcases : " + result.getFailureCount());
        System.out.println("Failures: " + result.getFailures());
    }
}
