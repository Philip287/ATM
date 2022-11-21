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
 * {@code DepositCommand} a class that implements the ATM card reload function.
 *
 * @author Philip Suprun
 */
public final class DepositCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private LoginCommand loginCommand;
    private SaveResult saveResult;

    public DepositCommand() {
        loginCommand = new LoginCommand();
        saveResult = new SaveResult();
    }

    @Override
    public void execute() throws CustomAtmException {
        if (App.bankCard == null) {
            loginCommand.execute();
        } else if (loginCommand.pinCodeCheck(App.bankCard)) {
            Double cash = getAnswer();
            if (cash % 5 == 0) {
                accountReplenishment(cash);
            } else {
                MessageHelper.writeMessage(ContentATM.SUM_EXCEPTION_MESSAGE);
            }
        }
    }

    private Double getAnswer() throws CustomAtmException {
        Double cash = null;
        boolean exit = false;
        while (!exit) {
            MessageHelper.writeMessage(ContentATM.HEADER_MESSAGE);
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

    private boolean accountReplenishment(double sum) throws CustomAtmException {
        if (sum <= App.atm.getTopUpLimit() && sum > 0) {
            boolean result = enrollment(sum);
            if (result) {
                MessageHelper.writeMessage(ContentATM.SUCCSESS_OPERATION_MESSAGE);
            }
            return result;
        } else {
            MessageHelper.writeMessage(ContentATM.SUM_EXCEPTION_MESSAGE);
            return false;
        }
    }

    private boolean enrollment(double sum) throws CustomAtmException {
        double cardBalance = App.bankCard.getCardBalance();
        cardBalance = cardBalance + sum;
        App.bankCard.setCardBalance(cardBalance);
        boolean result = saveResult.saveResultOperation(App.bankCard);
        if (result) {
            double remainingBalance = App.atm.getRemainingBalance();
            double newRemainingBalance = remainingBalance + sum;
            App.atm.setRemainingBalance(newRemainingBalance);
        }
        return result;
    }
}
