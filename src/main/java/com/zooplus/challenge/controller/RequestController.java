package com.zooplus.challenge.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zooplus.challenge.model.User;
import com.zooplus.challenge.services.CurrencyConverter;
import com.zooplus.challenge.services.UserServices;

import java.io.IOException;
import java.math.BigDecimal;

@Controller
public class RequestController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private UserServices userServices;

    @Autowired
    private CurrencyConverter currencyConverter;

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String userId, @RequestParam(required = true) String password, Model model) {
        if (userServices.isValidUser(userId, password)) {
            model.addAttribute("userId", userId);
            model.addAttribute("conversions", currencyConverter.getAllConversionsForUser(userId));
            return "converter";
        } else {
            model.addAttribute("message", "Unable to login. Check userId and password");
            return "error";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam(required = true) String userId,
                           @RequestParam(required = true) String userName,
                           @RequestParam(required = true) String password,
                           @RequestParam(required = true) String email,
                           Model model) {
        if (userServices.isUserIdAvailable(userId)) {
            User user = new User();
            user.setUserId(userId);
            user.setUserName(userName);
            user.setPassword(password);
            user.setEmail(email);
            user.setActive(true);
            userServices.registerUser(user);
            model.addAttribute("userId", userId);
            return "success";
        } else {
            model.addAttribute("message", "UserId already exists");
            return "error";
        }
    }

    @RequestMapping(value = "/convert", method = RequestMethod.POST)
    public String conversionPage(@RequestParam(required = true) String fromCurrency,
                                 @RequestParam(required = true) BigDecimal fromAmount,
                                 @RequestParam(required = true) String toCurrency,
                                 @RequestParam(required = true) String userId,
                                 Model model) {
        try {
            if (!StringUtils.isBlank(userId)) {
                BigDecimal toAmount = currencyConverter.convert(userId, fromAmount, fromCurrency, toCurrency);
                model.addAttribute("userId", userId);
                model.addAttribute("fromCurrency", fromCurrency);
                model.addAttribute("fromAmount", fromAmount);
                model.addAttribute("toCurrency", toCurrency);
                model.addAttribute("toAmount", toAmount);
                model.addAttribute("conversions", currencyConverter.getAllConversionsForUser(userId));
                return "converter";
            } else {
                model.addAttribute("message", "Please make sure you have logged in.");
                logger.error("Unable to find user in the model attributes. So, returning error page.");
                return "error";
            }
        } catch (IOException e) {
            model.addAttribute("message", "Exception while retrieving FXQuote. Verify the currencies and amount");
            logger.error("Exception while fetching FXQuote. ", e);
            return "error";
        }
    }

    @RequestMapping(value = "/getRegistered", method = RequestMethod.GET)
    public String registrationPage() {
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

}
