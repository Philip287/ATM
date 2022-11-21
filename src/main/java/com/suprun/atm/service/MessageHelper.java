package com.suprun.atm.service;

import com.suprun.atm.exception.CustomAtmException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code MessageHelper} class is used to display messages in ATM monitor
 *
 * @author Philip Suprun
 */
public class MessageHelper {

    private static BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
    private static final Logger LOGGER = LogManager.getLogger();

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws CustomAtmException {
        String line = ContentATM.LINE;
        try {
            line = bufferReader.readLine();
        } catch (IOException e) {
            LOGGER.error("Exception in MessageHelper.readString() method find ", e);
            throw new CustomAtmException(e);
        }

        return line;
    }

    public static Operation askOperation() {
        boolean exit = false;
        while (!exit) {
            try {
                writeMessage(ContentATM.MESSAGE);
                int operationType = Integer.parseInt(readString());
                exit = true;
                return Operation.getAllowableOperationByOrdinal(operationType);
            } catch (NumberFormatException | CustomAtmException e) {
                LOGGER.error("Exception in MessageHelper.askOperation() method find ", e);
                MessageHelper.writeMessage(ContentATM.UNKNOWN_OPERATION_MESSAGE);
            }
        }
        return askOperation();
    }
}
