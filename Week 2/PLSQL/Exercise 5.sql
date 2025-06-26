SET SERVEROUTPUT ON;


CREATE TABLE AuditLog (
    LogID NUMBER GENERATED ALWAYS AS IDENTITY,
    ActionDate DATE DEFAULT SYSDATE,
    AccountID NUMBER,
    Amount NUMBER,
    TransactionType VARCHAR2(10)
);
-- Trigger 1: Update LastModified on customer update
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/

-- Trigger 2: Log new transactions into AuditLog
CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog(AccountID, Amount, TransactionType)
    VALUES (:NEW.AccountID, :NEW.Amount, :NEW.TransactionType);
    
    DBMS_OUTPUT.PUT_LINE('✅ Transaction logged: Account ' || :NEW.AccountID || ', Type: ' || :NEW.TransactionType);
END;
/

-- Trigger 3: Enforce rules for deposits and withdrawals
CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = :NEW.AccountID;

    IF :NEW.TransactionType = 'Withdrawal' AND :NEW.Amount > v_balance THEN
        RAISE_APPLICATION_ERROR(-20001, '❌ Withdrawal exceeds balance.');
    ELSIF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20002, '❌ Deposit amount must be positive.');
    END IF;
END;
/

-- Insert test data
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (401, 'Trigger Test', TO_DATE('1995-05-05', 'YYYY-MM-DD'), 10000, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (401, 401, 'Savings', 5000, SYSDATE);

COMMIT;

-- Trigger 1 test: Update customer name (LastModified should auto-update)
UPDATE Customers
SET Name = 'Updated Trigger Test'
WHERE CustomerID = 401;

-- Trigger 2 test: Insert a valid transaction (also tests Trigger 3: business rule pass)
INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (401, 401, SYSDATE, 1000, 'Deposit');

-- Trigger 3 test: Invalid transaction (withdrawal exceeding balance)
BEGIN
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (402, 401, SYSDATE, 10000, 'Withdrawal');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE(SQLERRM);
END;
/

-- Trigger 3 test: Invalid transaction (negative deposit)
BEGIN
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (403, 401, SYSDATE, -500, 'Deposit');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE(SQLERRM);
END;
/


SELECT LastModified FROM Customers WHERE CustomerID = 401;
SELECT * FROM AuditLog ORDER BY LogID DESC;
