package com.suprun.atm.service;

import com.suprun.atm.entity.BankCard;
import com.suprun.atm.entity.DataCard;
import com.suprun.atm.exception.CustomAtmException;
import com.suprun.atm.reader.DataReader;
import com.suprun.atm.reader.impl.DataReaderImpl;
import com.suprun.atm.util.JsonParserUtil;
import com.suprun.atm.util.impl.JsonParserUtilImpl;

import static com.suprun.atm.entity.BankCard.builder;

/**
 * {@code SaveResult} class is used to save information about DataCard object
 *
 * @author Philip Suprun
 */
public class SaveResult {

    private JsonParserUtil jsonParserUtil;
    private DataReader reader;

    public SaveResult() {
        jsonParserUtil = new JsonParserUtilImpl();
        reader = new DataReaderImpl();
    }

    public boolean saveResultOperation(BankCard bankCard) throws CustomAtmException {

        DataCard dataCard = reader.readFile();
        BankCard card = builder()
                .setCardBalance(bankCard.getCardBalance())
                .setIsActive(bankCard.isActive())
                .setSerialNumber(bankCard.getSerialNumber())
                .setPinCode(bankCard.getPinCode())
                .setBlockTime(bankCard.getBlockTime())
                .build();
        dataCard.getCardList().put(card.getSerialNumber(), card);
        String jsonString = jsonParserUtil.parseToJson(dataCard);
        return reader.saveFile(jsonString);
    }
}
