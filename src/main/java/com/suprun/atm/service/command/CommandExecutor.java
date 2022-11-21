package com.suprun.atm.service.command;

import com.suprun.atm.exception.CustomAtmException;
import com.suprun.atm.service.Operation;
import com.suprun.atm.service.command.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code CommandExecutor} class provides logic for executing command.
 *
 * @author Philip Suprun
 */
public class CommandExecutor {
    private static final Map<Operation, Command> commandMap = new HashMap<>();

    static {
        commandMap.put(Operation.LOGIN, new LoginCommand());
        commandMap.put(Operation.INFO, new InfoCommand());
        commandMap.put(Operation.DEPOSIT, new DepositCommand());
        commandMap.put(Operation.WITHDRAW, new WithdrawCommand());
        commandMap.put(Operation.EXIT, new ExitCommand());
    }

    private CommandExecutor() {

    }

    public static void execution(Operation operation) throws CustomAtmException {
        commandMap.get(operation).execute();
    }

}
