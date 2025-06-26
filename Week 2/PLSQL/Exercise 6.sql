SET SERVEROUTPUT ON;

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (601, 'Cursor User A', TO_DATE('1990-01-01', 'YYYY-MM-DD'), 3000, SYSDATE);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (602, 'Cursor User B', TO_DATE('1995-01-01', 'YYYY-MM-DD'), 5000, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (601, 601, 'Savings', 3000, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (602, 602, 'Savings', 5000, SYSDATE);

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (601, 601, SYSDATE, 500, 'Deposit');

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (602, 601, SYSDATE, 200, 'Withdrawal');

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (603, 602, SYSDATE, 1000, 'Deposit');

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (601, 601, 20000, 7, SYSDATE, ADD_MONTHS(SYSDATE, 60));

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (602, 602, 30000, 6.5, SYSDATE, ADD_MONTHS(SYSDATE, 60));

COMMIT;

BEGIN
    FOR txn_rec IN (
        SELECT t.AccountID, t.TransactionDate, t.Amount, t.TransactionType
        FROM Transactions t
        WHERE TO_CHAR(t.TransactionDate, 'MM-YYYY') = TO_CHAR(SYSDATE, 'MM-YYYY')
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('📄 Statement - Account: ' || txn_rec.AccountID ||
                             ', Date: ' || TO_CHAR(txn_rec.TransactionDate, 'DD-MON-YYYY') ||
                             ', Amount: ' || txn_rec.Amount ||
                             ', Type: ' || txn_rec.TransactionType);
    END LOOP;
END;
/

BEGIN
    FOR acc IN (
        SELECT AccountID, Balance FROM Accounts
    ) LOOP
        UPDATE Accounts
        SET Balance = Balance - 100
        WHERE AccountID = acc.AccountID;

        DBMS_OUTPUT.PUT_LINE('💸 Annual fee of 100 applied to Account ID: ' || acc.AccountID);
    END LOOP;
END;
/

BEGIN
    FOR loan IN (
        SELECT LoanID, InterestRate FROM Loans
    ) LOOP
        UPDATE Loans
        SET InterestRate = InterestRate + 0.5
        WHERE LoanID = loan.LoanID;

        DBMS_OUTPUT.PUT_LINE('📈 Updated Interest Rate for Loan ID: ' || loan.LoanID);
    END LOOP;
END;
/
