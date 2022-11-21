package com.suprun.atm.service.command;

import com.suprun.atm.exception.CustomAtmException;

/**
 * {@code Command} is a functional interface implementing instances of which
 * execution command.
 *
 * @author Philip Suprun
 */
public interface Command {
    void execute() throws CustomAtmException;
}
