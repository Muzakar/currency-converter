package com.zooplus.challenge.controller;

import com.zooplus.challenge.model.User;
import com.zooplus.challenge.services.CurrencyConverter;
import com.zooplus.challenge.services.UserServices;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

/**
 * All the requests from the user will be controlled via this class
 */
@Controller
public class RequestController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private UserServices userServices;

    @Autowired
    private CurrencyConverter currencyConverter;

    @Autowired
    private Set<String> currencies;

    @Autowired
    private Set<String> countries;

    /**
     * Once user fills his user id and password in login page and clicks on login button.
     *
     * @param userId
     * @param password
     * @param model
     * @return
     */
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String userId, @RequestParam(required = true) String password, Model model) {
        if (userServices.isValidUser(userId, password)) {
            User user = userServices.fetchUser(userId);
            model.addAttribute("userId", user.getUserId());
            model.addAttribute("userName", user.getUserName());
            model.addAttribute("conversions", currencyConverter.getAllConversionsForUser(userId));
            model.addAttribute("currencies", currencies);
            return "converter";
        } else {
            model.addAttribute("message", "Unable to login. Check userId and password");
            return "error";
        }
    }

    /**
     * When user fills the data in registration page and clicks on submit button.
     *
     * @param userId
     * @param userName
     * @param password
     * @param email
     * @param model
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam(required = true) String userId,
                           @RequestParam(required = true) String userName,
                           @RequestParam(required = true) String password,
                           @RequestParam(required = true) String email,
                           @RequestParam(required = true) String address,
                           @RequestParam(required = true) String postcode,
                           @RequestParam(required = true) String country,
                           Model model) {
        if (userServices.isUserIdAvailable(userId)) {
            User user = new User.Builder(userId).userName(userName).password(password)
                    .email(email).address(address).postcode(postcode).country(country).active(true)
                    .build();
            userServices.registerUser(user);
            return "success";
        } else {
            model.addAttribute("message", "UserId already exists");
            return "error";
        }
    }

    /**
     * When the form is submitted to do the conversion. This method is called.
     *
     * @param fromCurrency
     * @param fromAmount
     * @param toCurrency
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = "/convert", method = RequestMethod.POST)
    public String conversionPage(@RequestParam(required = true) String fromCurrency,
                                 @RequestParam(required = true) BigDecimal fromAmount,
                                 @RequestParam(required = true) String toCurrency,
                                 @RequestParam(required = true) String userId,
                                 Model model) {
        try {
            if (!StringUtils.isBlank(userId)) {
                fromCurrency = fromCurrency.toUpperCase();
                toCurrency = toCurrency.toUpperCase();
                currencyConverter.convert(userId, fromAmount, fromCurrency, toCurrency);
                User user = userServices.fetchUser(userId);
                model.addAttribute("userId", user.getUserId());
                model.addAttribute("userName", user.getUserName());
                model.addAttribute("conversions", currencyConverter.getAllConversionsForUser(userId));
                model.addAttribute("currencies", currencies);
                return "converter";
            } else {
                model.addAttribute("message", "Please make sure you have logged in.");
                logger.error("Unable to find user in the model attributes. So, returning error page.");
                return "error";
            }
        } catch (Exception e) {
            model.addAttribute("message", "Unable to fetch FX Rate for the currencies. Please select different currencies");
            logger.error("Exception while fetching FXQuote. ", e);
            return "error";
        }
    }

    /**
     * Registration page will be returned.
     * Index page will have two links. This is one of the links.
     *
     * @return - name of registration page
     */
    @RequestMapping(value = "/getRegistered", method = RequestMethod.GET)
    public String registrationPage(Model model) {
        model.addAttribute("countries", countries);
        return "register";
    }

    /**
     * Login page will be returned.
     * Index page will have two links. This is one of the links.
     *
     * @return - name of login page
     */
    @RequestMapping(value = "/showLogin", method = RequestMethod.GET)
    public String loginPage(Model model) {
        model.addAttribute("userId", "");
        return "login";
    }

    /**
     * By clicking on logout link, User should be redirected to login page.
     *
     * @return - name of login page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        model.addAttribute("userId", "");
        return "login";
    }

}
