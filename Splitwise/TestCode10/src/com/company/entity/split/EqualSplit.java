package com.company.entity.split;

import com.company.entity.User;

public class EqualSplit extends Split{
    public EqualSplit(User user) {
        super(user);
    }

    @Override
    public String toString() {
        return "EqualSplit{" +
                "user=" + user +
                ", amount=" + amount +
                '}';
    }
}
