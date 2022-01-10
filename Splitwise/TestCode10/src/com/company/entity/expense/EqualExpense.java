package com.company.entity.expense;

import com.company.entity.ExpenseType;
import com.company.entity.User;
import com.company.entity.split.EqualSplit;
import com.company.entity.split.Split;

import java.util.List;

public class EqualExpense extends Expense{
    public EqualExpense(String id, ExpenseType expenseType, double amount, User paidBy, List<Split> splits) {
        super(id, expenseType, amount, paidBy, splits);
    }

    @Override
    public boolean isValidSplit() {
        for(Split split : getSplits())
        {
            if (!(split instanceof EqualSplit))
                return false;

            if(split.getAmount() < 0)
                return false;
        }
        return true;
    }


}
