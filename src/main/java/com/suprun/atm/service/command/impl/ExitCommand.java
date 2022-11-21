package com.suprun.atm.service.command.impl;

import com.suprun.atm.App;
import com.suprun.atm.exception.CustomAtmException;
import com.suprun.atm.service.ContentATM;
import com.suprun.atm.service.MessageHelper;
import com.suprun.atm.service.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code ExitCommand} a class that implements the function of exiting the ATM application.
 *
 * @author Philip Suprun
 */
public class ExitCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    public ExitCommand(){

    }

    @Override
    public void execute() throws CustomAtmException {

        boolean work = true;
        MessageHelper.writeMessage(ContentATM.QUESTION_EXIT_MESSAGE);
        do {
            String answer = MessageHelper.readString();
            if (answer.equals(ContentATM.CHOICE_1)) {
                work = false;
                App.exit = true;
            } else if (answer.equals(ContentATM.CHOICE_2)) {
                App.exit = false;
                work = false;
            } else {
                MessageHelper.writeMessage(ContentATM.WRONG_MESSAGE);
                LOGGER.error("Incorrect input: " + answer);
            }
        } while (work);
    }
}
