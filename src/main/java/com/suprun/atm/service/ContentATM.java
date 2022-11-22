package com.suprun.atm.service;

public class ContentATM {
    public static final String LINE = "";
    public static final String MESSAGE = "Enter operation type, please: \n 1 - INFO. \n 2 - DEPOSIT." +
            "\n 3 - WITHDRAW.\n 4 - EXIT.";
    public static final String NOT_VALID = "Data are not valid";
    public static final String PATH = "src/main/resources/data/data.txt";
    public static final String PATH_ERR = "File is not exist or is empty or incorrect path";
    public static final String READING_FAIL = "Reading file is fail ";
    public static final String HEADER_MESSAGE = "Deposit (enter) the amount you want to add to the account. " +
            "The ATM doesn't give you change. The minimum bill accepted by an ATM is 5 rubles.";
    public static final String SUM_EXCEPTION_MESSAGE = "The amount you entered is incorrect. Enter the amount to withdraw again.";
    public static final String SUCCSESS_OPERATION_MESSAGE = "The money has been successfully credited to your card account.";
    public static final String QUESTION_EXIT_MESSAGE = "Do you want to exit o continue? \n" +
            "Enter: \n 1 if you want to exit. \n 2 if you want to continue.";
    public static final String CHOICE_1 = "1";
    public static final String CHOICE_2 = "2";
    public static final String WRONG_MESSAGE = "Incorrect input. Try again.";
    public static String CURRENCY = " BY";
    public static String MESSAGE_HEADER = "The amount in the account: ";
    public static final String CARD_NUMBER_MESSAGE = "Enter credit card number, please: ";
    public static final String PIN_CODE_MESSAGE = "Enter your PIN-code, please: ";
    public static final String WRONG_CREDIT_CARD_MESSAGE = "This card is not a card of our bank and is not serviced. "
            + "Please use our bank card to continue the operation.";
    public static final String WRONG_PIN_CODE_MESSAGE = "The pin code entered is incorrect. There are only attempts " +
            "left to block the card for 24 hours: ";
    public static final String BLOCK_CARD_MESSAGE = "Your card has been blocked for 24 hours.";
    public static final String INFO_BLOCK_CARD_MESSAGE = "Your card has been blocked for 24 hours. " +
            "There is time left to unlock the card: ";
    public static final String UNBLOCK_CARD_MESSAGE = "Your card has been unblocked.";
    public static final String QUESTION_CASH_WITHDRAWAL_MESSAGE = "CASH WITHDRAWAL  \n" +
            "Choose an amount, BYN: \n 1 - 5.00. \n 2 - 10.00.  \n 3 - 25.00. \n 4 - 50.00." +
            "\n 5 - 100.00. \n 6 - 200.00. \n 7 - Cancel. \n 8 - Other amount";
    public static final String SUM_QUESTION_MESSAGE = "\n Available banknotes 5, 10, 20, 100 \n Enter the BYN amount: \n";
    public static final String TAKE_THE_MONEY_MESSAGE = "Take the money.";
    public static final String UNKNOWN_OPERATION_MESSAGE = "Unknown operation, try again";
    public static final String HOURS_MESSAGE = " hours";
    
    private ContentATM(){
        
    }

}
