package com.epam.zubar.hr;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.epam.zubar.hr.utils.Validator;
/**
 * Test is used for testing methods of Validator class.
 * @author Mikalay Zubar
 *
 */
public class ValidatorTest {

    private static final Logger LOGGER = LogManager.getLogger(ValidatorTest.class);

    private List<String> correctPas = new ArrayList<>();
    private List<String> incorrectPas = new ArrayList<>();

    /* initializes the list of correct and incorrect passwords */
    {
        correctPas.add("Qwer1234");
        correctPas.add("12ZXasdf");
        correctPas.add("1234f67Q");
        correctPas.add("ASDFGf2K");
        incorrectPas.add("!@#$%^&*(");
        incorrectPas.add(null);
        incorrectPas.add("afshdjfdsfdsfdsfsdfsdfdsfkcb");
        incorrectPas.add("");
    }

    /**
     * Tests the correctness of password validation method.
     */
    @Test
    public void validateCorrectPasswordTest() {
        for (String password : correctPas) {
            boolean valid = Validator.validatePassword(password);
            LOGGER.info("Correct password: " + password + " Validation result: " + valid);
            Assert.assertTrue(valid);
        }
    }

    /**
     * Tests the correctness of password validation method.
     */
    @Test
    public void validateIncorrectPasswordTest() {
        for (String password : incorrectPas) {
            boolean valid = Validator.validatePassword(password);
            LOGGER.info("Incorrect password: " + password + " Validation result: " + valid);
            Assert.assertFalse(valid);
        }
    }

}
