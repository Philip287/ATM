package com.suprun.atm.service.command.impl;

import com.suprun.atm.App;
import com.suprun.atm.entity.BankCard;
import com.suprun.atm.exception.CustomAtmException;
import com.suprun.atm.reader.DataReader;
import com.suprun.atm.reader.impl.DataReaderImpl;
import com.suprun.atm.service.ContentATM;
import com.suprun.atm.service.MessageHelper;
import com.suprun.atm.service.SaveResult;
import com.suprun.atm.service.command.Command;
import com.suprun.atm.validator.Validator;
import com.suprun.atm.validator.imp.PinCodeValidator;
import com.suprun.atm.validator.imp.SerialNumberValidator;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * {@code LoginCommand} class that implements the user authorization function.
 *
 * @author Philip Suprun
 */
public class LoginCommand implements Command {

    private final Long DAY_IN_MILLISECONDS = 86400000L;
    private SaveResult saveResult;
    private Validator pinValidator;
    private Validator serialNumberValidator;
    private DataReader reader;

    public LoginCommand() {
        saveResult = new SaveResult();
        pinValidator = PinCodeValidator.getInstance();
        serialNumberValidator = SerialNumberValidator.getInstance();
        reader = new DataReaderImpl();
    }

    @Override
    public void execute() throws CustomAtmException {
        BankCard tempBankCard = cardNumberVerification();
        if (pinCodeCheck(tempBankCard) && tempBankCard != null) {
            App.bankCard = tempBankCard;
        }
    }

    private BankCard cardNumberVerification() throws CustomAtmException {
        boolean flag = false;
        BankCard tempBankCard = null;
        while (!flag) {
            MessageHelper.writeMessage(ContentATM.CARD_NUMBER_MESSAGE);
            String creditNumber = MessageHelper.readString().trim();
            Map<String, BankCard> cardList = reader.readFile().getCardList();
            if (serialNumberValidator.validate(creditNumber)) {
                tempBankCard = cardList.get(creditNumber);
                if (tempBankCard != null) {
                    flag = true;
                } else {
                    MessageHelper.writeMessage(ContentATM.WRONG_CREDIT_CARD_MESSAGE);
                }
            } else {
                MessageHelper.writeMessage(ContentATM.WRONG_CREDIT_CARD_MESSAGE);
            }
        }
        return tempBankCard;
    }

    public boolean pinCodeCheck(BankCard tempBankCard) throws CustomAtmException {
        int count = 0;
        boolean flag = false;
        while (count <= 2 && !flag) {
            if (blockingCheck(tempBankCard)) {
                break;
            }
            MessageHelper.writeMessage(ContentATM.PIN_CODE_MESSAGE);
            String pinNumber = MessageHelper.readString().trim();
            if (pinValidator.validate(pinNumber)) {
                if (tempBankCard.isActive()) {
                    if (tempBankCard.getPinCode().equals(pinNumber)) {
                        flag = true;
                    } else {
                        count += 1;
                        MessageHelper.writeMessage(ContentATM.WRONG_PIN_CODE_MESSAGE + (4 - count));
                    }
                } else {
                    App.exit = true;
                    break;
                }
            } else {
                count += 1;
                MessageHelper.writeMessage(ContentATM.WRONG_PIN_CODE_MESSAGE + (4 - count));
            }
        }
        cardBlock(tempBankCard, count);
        return flag;
    }

    private void cardBlock(BankCard tempBankCard, int count) throws CustomAtmException {
        if (count == 3) {
            Calendar calendar = new GregorianCalendar();
            Long date = calendar.getTimeInMillis();
            tempBankCard.setBlockTime(date);
            tempBankCard.setActive(false);
            saveResult.saveResultOperation(tempBankCard);
            App.bankCard = null;
            MessageHelper.writeMessage(ContentATM.BLOCK_CARD_MESSAGE);
        }
    }

    private boolean blockingCheck(BankCard tempBankCard) throws CustomAtmException {
        if (!tempBankCard.isActive()) {
            return cardUnblock(tempBankCard);
        } else {
            return false;
        }
    }

    private boolean cardUnblock(BankCard tempBankCard) throws CustomAtmException {
        Calendar calendar = new GregorianCalendar();
        Long now = calendar.getTimeInMillis();
        if (now >= (tempBankCard.getBlockTime() + DAY_IN_MILLISECONDS)) {
            tempBankCard.setActive(true);
            tempBankCard.setBlockTime(null);
            saveResult.saveResultOperation(tempBankCard);
            MessageHelper.writeMessage(ContentATM.UNBLOCK_CARD_MESSAGE);
            return false;
        } else {
            Long remainingTimeToUnlocking = (tempBankCard.getBlockTime() + DAY_IN_MILLISECONDS) - now;
            MessageHelper.writeMessage(ContentATM.INFO_BLOCK_CARD_MESSAGE +
                    TimeUnit.MILLISECONDS.toMinutes(remainingTimeToUnlocking) / 60 + ContentATM.HOURS_MESSAGE);
            App.bankCard = null;
            return true;
        }
    }
}
