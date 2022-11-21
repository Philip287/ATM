package com.suprun.atm.util;

import com.suprun.atm.entity.DataCard;
import com.suprun.atm.exception.CustomAtmException;

/**
 * {@code JsonParserUtil} interface provides functionality for parse JSON data.
 *
 * @author Philip Suprun
 */
public interface JsonParserUtil {
    /**
     * @param jsonBankCards containing json string BankCard object
     * @return object class DataCard
     */
    DataCard parseFromJson(String jsonBankCards) throws CustomAtmException;

    /**
     * @param dataCard containing DataCard object
     * @return json String from that object
     */
    String parseToJson(DataCard dataCard) throws CustomAtmException;
}
