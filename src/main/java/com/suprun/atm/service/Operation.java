package com.suprun.atm.service;

import com.suprun.atm.exception.CustomAtmException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code Operation} enum is used to choose type of operation
 *
 * @author Philip Suprun
 */
public enum Operation {
    LOGIN,   //to do for user identify
    INFO,    //to look at information about bank account
    DEPOSIT, //to put money to bank account
    WITHDRAW,//to take money
    EXIT;    //to exit from atm emulator; user may finish application by writing word "exit" before any command

    private static final Logger LOGGER = LogManager.getLogger();

    public static Operation getAllowableOperationByOrdinal(Integer i) throws CustomAtmException {
        switch (i) {
            case 1:
                return Operation.INFO;
            case 2:
                return Operation.DEPOSIT;
            case 3:
                return Operation.WITHDRAW;
            case 4:
                return Operation.EXIT;
            default:
                LOGGER.info(ContentATM.MESSAGE);
                throw new CustomAtmException(ContentATM.MESSAGE);
        }
    }

}
