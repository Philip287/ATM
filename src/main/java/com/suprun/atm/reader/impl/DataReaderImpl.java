package com.suprun.atm.reader.impl;

import com.suprun.atm.entity.DataCard;
import com.suprun.atm.exception.CustomAtmException;
import com.suprun.atm.reader.DataReader;
import com.suprun.atm.service.ContentATM;
import com.suprun.atm.util.JsonParserUtil;
import com.suprun.atm.util.impl.JsonParserUtilImpl;
import com.suprun.atm.validator.Validator;
import com.suprun.atm.validator.imp.FilePathValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@code DataReaderImpl} class for read and save class DataCard to file data.txt.
 *
 * @author Philip Suprun
 */
public class DataReaderImpl implements DataReader {

    private static JsonParserUtil jsonParserUtil = JsonParserUtilImpl.getInstance();
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public DataCard readFile() throws CustomAtmException {
        Validator fileValidator = FilePathValidator.getInstance();
        if (!fileValidator.validate(ContentATM.PATH)) {
            LOGGER.fatal(ContentATM.PATH_ERR);
            throw new CustomAtmException(ContentATM.PATH_ERR);
        }
        ArrayList<String> lines;
        Path pathFile = Paths.get(ContentATM.PATH);
        try (Stream<String> lineStream = Files.lines(pathFile)) {
            lines = lineStream.collect(Collectors.toCollection(ArrayList::new));

        } catch (IOException e) {
            LOGGER.error(ContentATM.READING_FAIL);
            throw new CustomAtmException(ContentATM.READING_FAIL, e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : lines) {
            stringBuilder.append(s).append("\n");
        }
        return jsonParserUtil.parseFromJson(stringBuilder.toString());
    }

    @Override
    public boolean saveFile(String content) throws CustomAtmException {
        Path pathFile = Paths.get(ContentATM.PATH);
        boolean flag = false;
        try {
            Path path = Files.writeString(pathFile, content);
            if (path != null) {
                flag = true;
            }
        } catch (IOException e) {
            LOGGER.error(ContentATM.READING_FAIL);
            throw new CustomAtmException(ContentATM.READING_FAIL, e);
        }
        return flag;
    }
}
