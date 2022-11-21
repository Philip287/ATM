package com.suprun.atm.service.command.impl;

import com.suprun.atm.App;
import com.suprun.atm.exception.CustomAtmException;
import com.suprun.atm.service.ContentATM;
import com.suprun.atm.service.MessageHelper;
import com.suprun.atm.service.SaveResult;
import com.suprun.atm.service.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code WithdrawCommand} a class that implements the cash withdrawal function from the card.
 *
 * @author Philip Suprun
 */
public class WithdrawCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private SaveResult saveResult;
    private LoginCommand loginCommand;

    public WithdrawCommand(){
        saveResult = new SaveResult();
        loginCommand = new LoginCommand();
    }

    @Override
    public void execute() throws CustomAtmException {
        if (App.bankCard == null) {
            loginCommand.execute();
        } else if (loginCommand.pinCodeCheck(App.bankCard)) {
            boolean exit = false;
            do {
                MessageHelper.writeMessage(ContentATM.QUESTION_CASH_WITHDRAWAL_MESSAGE);
                String answer = MessageHelper.readString();
                switch (answer) {
                    case "1" -> exit = retrieved(5.00);
                    case "2" -> exit = retrieved(10.00);
                    case "3" -> exit = retrieved(25.00);
                    case "4" -> exit = retrieved(50.00);
                    case "5" -> exit = retrieved(100.00);
                    case "6" -> exit = retrieved(200.00);
                    case "7" -> exit = true;
                    case "8" -> exit = retrieved();
                }
            } while (!exit);
        }
    }

    private boolean retrieved() throws CustomAtmException {
        Double cash = getAnswer();
        boolean exit = false;
        if (cash % 5 == 0) {
            exit = retrieved(cash);
        } else {
            MessageHelper.writeMessage(ContentATM.SUM_EXCEPTION_MESSAGE);
        }
        return exit;
    }

    private Double getAnswer() throws CustomAtmException {
        Double cash = null;
        boolean exit = false;
        while (!exit) {
            MessageHelper.writeMessage(ContentATM.SUM_QUESTION_MESSAGE);
            try {
                cash = Double.parseDouble(MessageHelper.readString().trim());
                exit = true;
            } catch (NumberFormatException e) {
                LOGGER.error("Uncorrected answer. " + e.getMessage());
                MessageHelper.writeMessage(ContentATM.SUM_EXCEPTION_MESSAGE);
            }
        }
        return cash;
    }

    private boolean retrieved(double sum) throws CustomAtmException {
        if (sum <= App.atm.getRemainingBalance() && sum <= App.bankCard.getCardBalance() && sum > 0) {
            boolean result = givCash(sum);
            if (result) {
                MessageHelper.writeMessage(ContentATM.TAKE_THE_MONEY_MESSAGE);
            }
            return result;
        } else {
            MessageHelper.writeMessage(ContentATM.SUM_EXCEPTION_MESSAGE);
            return false;
        }
    }

    private boolean givCash(double sum) throws CustomAtmException {
        double cardBalance = App.bankCard.getCardBalance();
        cardBalance = cardBalance - sum;
        App.bankCard.setCardBalance(cardBalance);
        boolean result = saveResult.saveResultOperation(App.bankCard);
        if (result) {
            double remainingBalance = App.atm.getRemainingBalance();
            double newRemainingBalance = remainingBalance - sum;
            App.atm.setRemainingBalance(newRemainingBalance);
        }
        return result;
    }
}
