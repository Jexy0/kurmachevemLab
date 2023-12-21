package tech.reliab.course.kurmachevem.bank.service;

import java.math.BigDecimal;

import tech.reliab.course.kurmachevem.bank.entity.User;

public interface UserService {
    User create(User user);
    BigDecimal calculateCreditRating(User user);
}