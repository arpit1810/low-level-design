package com.company.test;

import com.company.entity.ExpenseType;
import com.company.entity.User;
import com.company.entity.split.EqualSplit;
import com.company.entity.split.ExactSplit;
import com.company.entity.split.Split;
import com.company.manager.ExpenseManager;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Testcases {
    ExpenseManager expenseManager;

    public Testcases() {
        expenseManager = new ExpenseManager();
        User user1 = new User("11", "aa", "88");
        User user2 = new User("12", "bb", "99");
        User user3 = new User("13", "bb", "99");
        expenseManager.addUser(user1);
        expenseManager.addUser(user2);
        System.out.println(expenseManager);


        List<Split> splitList = new ArrayList<>();
        splitList.add(new EqualSplit(user1));
        splitList.add(new ExactSplit(user2, 100));
        splitList.add(new EqualSplit(user3));
        expenseManager.addExpense("111", ExpenseType.EQUAL, 300, user1, splitList);


        System.out.println("\n\n");
        List<Split> splitList5 = new ArrayList<>();
        splitList5.add(new EqualSplit(user1));
        splitList5.add(new EqualSplit(user2));
        splitList5.add(new EqualSplit(user3));
        expenseManager.addExpense("111", ExpenseType.EQUAL, 300, user1, splitList5);

        System.out.println(expenseManager);




        System.out.println("\n\n");
        System.out.println(expenseManager.getBalanceMap());

        System.out.println("\n\n");
        expenseManager.show();


        System.out.println("\n\n");
        expenseManager.show(user1);

        System.out.println("\n\n");
        expenseManager.show(user2);

        System.out.println("\n\n");



        System.out.println(expenseManager.getBalanceMap());
        List<Split> splitList1 = new ArrayList<>();
        //splitList1.add(new ExactSplit(user1, 100));
        splitList1.add(new ExactSplit(user2, 200));
        splitList1.add(new ExactSplit(user3, 100));
        expenseManager.addExpense("112", ExpenseType.EXACT, 300, user1, splitList1);
        System.out.println("\n\n");
        System.out.println(expenseManager.getBalanceMap());
        expenseManager.show();

        List<Split> splitList6 = new ArrayList<>();
        splitList6.add(new EqualSplit(user1));
        splitList6.add(new EqualSplit(user3));
        expenseManager.addExpense("111", ExpenseType.EQUAL, 600, user2, splitList6);
        System.out.println("\n\n");
        System.out.println(expenseManager.getBalanceMap());
        expenseManager.show();
    }

    @Test
    public void test1()
    {
        System.out.println("Running the testcase");
    }
}
