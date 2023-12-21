package tech.reliab.course.kurmachevem.bank.service.impl;

import java.math.BigDecimal;

import tech.reliab.course.kurmachevem.bank.entity.User;
import tech.reliab.course.kurmachevem.bank.service.UserService;
import tech.reliab.course.kurmachevem.bank.utils.BigRandom;

public class UserServiceImpl implements UserService {

    @Override
    public User create(User user) {
        if (user == null) {
            return null;
        }

        if (user.getBank() == null) {
            System.err.println("Error: User - must have bank");
            return null;
        }

        User createdUser = new User(user);

        final BigDecimal monthlyIncome = BigRandom.between(new BigDecimal("0.0"), new BigDecimal("1.0"))
                .multiply(User.MAX_MONTHLY_INCOME);
        createdUser.setMonthlyIncome(monthlyIncome);
        calculateCreditRating(createdUser);

        return createdUser;
    }

    @Override
    public BigDecimal calculateCreditRating(User user) {
        user.setCreditRating(
                user.getMonthlyIncome().divide(new BigDecimal("1000").multiply(new BigDecimal("100"))));
        return user.getCreditRating();
    }

}