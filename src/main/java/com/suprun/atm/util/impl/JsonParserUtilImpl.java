package com.suprun.atm.util.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.suprun.atm.entity.DataCard;
import com.suprun.atm.exception.CustomAtmException;
import com.suprun.atm.util.JsonParserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonParserUtilImpl implements JsonParserUtil {

    private static JsonParserUtil instance;
    private static Gson gson = new GsonBuilder().create();
    private static final Logger LOGGER = LogManager.getLogger();


    public static JsonParserUtil getInstance() {
        if (instance == null) {
            instance = new JsonParserUtilImpl();
        }
        return instance;
    }

    @Override
    public DataCard parseFromJson(String jsonBankCards) throws CustomAtmException {
        DataCard cards = null;
        try {
            cards = gson.fromJson(jsonBankCards, DataCard.class);
        } catch (JsonParseException e) {
            LOGGER.error("Could not parse");
            throw new CustomAtmException("Could not parse" + e);
        }
        return cards;
    }

    @Override
    public String parseToJson(DataCard dataCard) throws CustomAtmException {
        String jsonString;
        try {
            jsonString = gson.toJson(dataCard, DataCard.class);
        } catch (JsonParseException e) {
            LOGGER.error("Could not parse");
            throw new CustomAtmException("Could not parse" + e);
        }
        return jsonString;
    }
}
