package com.company;

import com.company.entity.ExpenseType;
import com.company.entity.User;
import com.company.entity.split.EqualSplit;
import com.company.entity.split.Split;
import com.company.manager.ExpenseManager;
import com.company.test.Testcases;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        JUnitCore core = new JUnitCore();
        Result result = core.run(Testcases.class);
        System.out.println(result.getFailures());
    }
}
