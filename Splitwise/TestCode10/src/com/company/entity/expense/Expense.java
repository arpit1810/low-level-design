package com.company.entity.expense;

import com.company.entity.ExpenseType;
import com.company.entity.User;
import com.company.entity.split.Split;

import java.util.List;

public abstract class Expense {
    private String id;
    private ExpenseType expenseType;
    private double amount;
    private User paidBy;
    private List<Split> splits;

    public Expense(String id, ExpenseType expenseType, double amount, User paidBy, List<Split> splits) {
        this.id = id;
        this.expenseType = expenseType;
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public abstract boolean isValidSplit();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", paidBy=" + paidBy +
                ", splits=" + splits +
                '}';
    }
}