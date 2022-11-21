# COMPUTER DEVICE SHOP #
An application that simulates the operation of an ATM. It simulates the basic functions of an ATM. 

The user can enter the card number corresponding to the template and the correct PIN code to access the account. After successful authorization, the user can:
check the card balance;
Withdraw funds from the account, not exceeding the amount of the current account or exceeding the limit of funds at the ATM;
Top up the balance (the top up amount must not exceed 1 000 000).

Requirements taken into account when designing the ATM:
-data are stored in a text file in JSON format;
-The card number when entering the data should be of the form: "ХХХХХХХХХХХХХХХХ";
-the program must save its state after completion (the data file must be updated);
-All success and error messages must be output to the console.
