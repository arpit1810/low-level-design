package com.company.service;

import com.company.entity.ExpenseType;
import com.company.entity.User;
import com.company.entity.expense.EqualExpense;
import com.company.entity.expense.ExactExpense;
import com.company.entity.expense.Expense;
import com.company.entity.split.Split;

import java.util.List;

public class ExpenseService {

    public static Expense createExpense(String id, ExpenseType expenseType, double amount, User paidBy, List<Split> splits)
    {
        if (expenseType == ExpenseType.EQUAL)
        {
            Expense expense = new EqualExpense(id, expenseType, amount, paidBy, splits);
            if(!expense.isValidSplit())
                return null;

            // Lets update the amount in splits
            int count = splits.size();
            double eachAmount = (double)(Math.round(amount*100/count))/100.0;

            // Revisit this logic...
            for(Split split : splits)
            {
                split.setAmount(eachAmount);
            }

            return expense;
        }
        else if(expenseType == ExpenseType.EXACT)
        {
            System.out.println("Entering here");
            Expense expense = new ExactExpense(id, expenseType, amount, paidBy, splits);
            if(!expense.isValidSplit())
                return null;
            return expense;
        }
        else
            return null;
    }
}
