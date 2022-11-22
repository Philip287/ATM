# COMPUTER DEVICE SHOP #
An application that simulates the operation of an ATM. It simulates the basic functions of an ATM. 

The user can enter the card number corresponding to the template and the correct PIN code to access the account. After successful authorization, the user can:

1. Check the card balance;

2. Withdraw funds from the account, not exceeding the amount of the current account or the limit of funds at the ATM;

3. Top up the balance (the top up amount must not exceed 1 000 000);

4. Complete the work with the ATM.

Requirements taken into account when designing the ATM:

- data are stored in a text file in JSON format;

- card number is automatically checked for compliance with the template consisting of only digits: "ХХХХХХХХХХХХХХХХХХХХ";

- the pincot used to access the card is also checked for consistency with the pattern consisting of only digits: "XXXX";

- The program saves its state when it terminates (the data file is updated in this case);

- All success and error messages appear on the console, error messages are written in the logs;

- All success and error messages are logged in the console; 

- List processing uses collections;

- The PIN-code can be entered incorrectly 3 times, after that the card is blocked for 24 hours;

- The card is unlocked automatically after a 24-hour period and re-use of the card after that time.

