package com.company.entity.split;

import com.company.entity.User;

public class ExactSplit extends Split {
    public ExactSplit(User user, double amount) {
        super(user);
        setAmount(amount);
    }

    @Override
    public String toString() {
        return "ExactSplit{" +
                "user=" + user +
                ", amount=" + amount +
                '}';
    }
}
