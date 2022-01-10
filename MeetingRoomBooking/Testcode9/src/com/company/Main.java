package com.company;

import com.company.test.Testcases;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class Main {

    public static void main(String[] args) {
	// write your code here

        JUnitCore jUnitCore = new JUnitCore();
        Result result = jUnitCore.run(Testcases.class);
        System.out.println(result.getFailures());
    }
}
