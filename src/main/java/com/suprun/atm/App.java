package com.suprun.atm;

import com.suprun.atm.entity.Atm;
import com.suprun.atm.entity.BankCard;
import com.suprun.atm.exception.CustomAtmException;
import com.suprun.atm.service.ContentATM;
import com.suprun.atm.service.MessageHelper;
import com.suprun.atm.service.Operation;
import com.suprun.atm.service.command.CommandExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code App} the main class in application.
 *
 * @author Philip Suprun
 */

public class App {

    private static final Logger LOGGER = LogManager.getLogger();
    public static BankCard bankCard;
    public static Atm atm = new Atm("1", 9999000000.0, 1000000.0);
    public static boolean exit = false;

    public static void main(String[] args) {

        try {
            Operation operation = Operation.LOGIN;
            CommandExecutor.execution(operation);
            do {
                if(bankCard == null){
                    CommandExecutor.execution(operation);
                }
                operation = MessageHelper.askOperation();
                CommandExecutor.execution(operation);
                if (operation != Operation.EXIT) {
                    CommandExecutor.execution(Operation.EXIT);
                }
            } while (!exit);
        } catch (CustomAtmException e) {
            bankCard = null;
            LOGGER.error("Exception in App.execution(operation) method find ", e);
        }
        bankCard = null;
        MessageHelper.writeMessage(ContentATM.GOODBYE_MESSAGE);
    }

}
