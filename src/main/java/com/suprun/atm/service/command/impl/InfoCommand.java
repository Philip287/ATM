package com.suprun.atm.service.command.impl;

import com.suprun.atm.App;
import com.suprun.atm.exception.CustomAtmException;
import com.suprun.atm.service.ContentATM;
import com.suprun.atm.service.MessageHelper;
import com.suprun.atm.service.command.Command;

/**
 * {@code InfoCommand} class that implements the account status view function.
 *
 * @author Philip Suprun
 */
public class InfoCommand implements Command {

    private LoginCommand loginCommand;

    public InfoCommand() {
        loginCommand = new LoginCommand();
    }

    @Override
    public void execute() throws CustomAtmException {
        if (App.bankCard == null) {
            loginCommand.execute();
        } else if (loginCommand.pinCodeCheck(App.bankCard)) {
            MessageHelper.writeMessage(ContentATM.MESSAGE_HEADER);
            Double cash = App.bankCard.getCardBalance();
            MessageHelper.writeMessage(cash + ContentATM.CURRENCY);
        }
    }
}
