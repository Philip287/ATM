package com.suprun.atm.reader;

import com.suprun.atm.entity.DataCard;
import com.suprun.atm.exception.CustomAtmException;


public interface DataReader {
    DataCard readFile() throws CustomAtmException;

    boolean saveFile(String content) throws CustomAtmException;
}
