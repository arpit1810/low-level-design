package com.company.manager;

import com.company.entity.ExpenseType;
import com.company.entity.User;
import com.company.entity.expense.Expense;
import com.company.entity.split.Split;
import com.company.service.ExpenseService;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;

public class ExpenseManager {
    List<Expense> expenses;
    HashMap<String, User> users;
    HashMap<String, HashMap<String, Double>> balanceMap;

    public ExpenseManager() {
        users = new HashMap<>();
        expenses = new ArrayList<>();
        balanceMap = new HashMap<>();
    }

    public void addUser(User user)
    {
        users.put(user.getId(), user);
    }

    public void addExpense(String id, ExpenseType expenseType, double amount, User paidBy, List<Split> splits)
    {
        Expense expense = ExpenseService.createExpense(id, expenseType, amount, paidBy, splits);
        if(expense != null)
        {
            System.out.println("Added the expense");
            expenses.add(expense);
            // After adding we will be updating overall data as we will be having the splits data modified here.

            for(Split split : splits)
            {
                if(split.getUser().getId() == paidBy.getId())
                    continue;

                balanceMap.putIfAbsent(paidBy.getId(), new HashMap<>());
                balanceMap.putIfAbsent(split.getUser().getId(), new HashMap<>());

                double data = 0;
                if(balanceMap.get(paidBy.getId())!=null && balanceMap.get(paidBy.getId()).get(split.getUser().getId()) != null)
                data =  balanceMap.get(paidBy.getId()).get(split.getUser().getId());
                System.out.println(split.getUser().getId() + " " + data);
                balanceMap.get(paidBy.getId()).put(split.getUser().getId(), data + split.getAmount());

                data = 0;
                if(balanceMap.get(split.getUser().getId()) != null && balanceMap.get(split.getUser().getId()).get(paidBy.getId()) != null)
                data = balanceMap.get(split.getUser().getId()).get(paidBy.getId());

                System.out.println(split.getUser().getId() + " " + data);
                balanceMap.get(split.getUser().getId()).put(paidBy.getId(), data - split.getAmount());
            }
        }
        else
        {
            System.out.println("Not adding the expense");
        }
    }

    public void show()
    {
        for(Map.Entry<String, HashMap<String, Double>> record : getBalanceMap().entrySet())
        {
            for(Map.Entry<String, Double> innerRecord :  record.getValue().entrySet())
            {
                if(innerRecord.getValue() <= 0)
                    continue;
                showMessage(record.getKey(), innerRecord.getKey(), innerRecord.getValue());
            }
        }
    }

    public void show(User user)
    {
        if(getBalanceMap().get(user.getId()) == null)
        {
            System.out.println("No records to show");
            return;
        }

        for(Map.Entry<String, Double> innerRecord :  getBalanceMap().get(user.getId()).entrySet())
            showMessage(user.getId(), innerRecord.getKey(), innerRecord.getValue());
    }

    public void showMessage(String u1, String u2, double amount)
    {
        if(amount > 0)
            System.out.println(u2 + " Will give " + Math.abs(amount) + " to " + u1);
        else
            System.out.println(u1 + " Will give " + Math.abs(amount) + " to " + u2);
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public ExpenseManager(List<Expense> expenses, HashMap<String, User> users) {
        this.expenses = expenses;
        this.users = users;
    }

    public HashMap<String, HashMap<String, Double>> getBalanceMap() {
        return balanceMap;
    }

    public void setBalanceMap(HashMap<String, HashMap<String, Double>> balanceMap) {
        this.balanceMap = balanceMap;
    }

    @Override
    public String toString() {
        return "ExpenseManager{" +
                "expenses=" + expenses +
                ", users=" + users +
                ", balanceMap=" + balanceMap +
                '}';
    }
}
