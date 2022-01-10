package com.company.entity.expense;

import com.company.entity.ExpenseType;
import com.company.entity.User;
import com.company.entity.split.ExactSplit;
import com.company.entity.split.Split;

import java.util.List;

public class ExactExpense extends Expense{
    public ExactExpense(String id, ExpenseType expenseType, double amount, User paidBy, List<Split> splits) {
        super(id, expenseType, amount, paidBy, splits);
    }

    @Override
    public boolean isValidSplit() {

        double tempAmount = 0;
        for(Split split : getSplits())
        {
            if(!(split instanceof ExactSplit))
            {
                System.out.println("Instance not maatching");
                return false;
            }

            if(split.getAmount() < 0)
            {
                System.out.println("Amount less than 0");
                return false;
            }

            tempAmount += split.getAmount();
        }

        if(tempAmount != getAmount())
        {
            System.out.println("Amount not macthing");
            return false;
        }
        return true;
    }


}
