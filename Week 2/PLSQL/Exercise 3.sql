-- Enable DBMS Output
SET SERVEROUTPUT ON;

-- Create procedure: ProcessMonthlyInterest
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
    CURSOR savings_cur IS
        SELECT AccountID, Balance
        FROM Accounts
        WHERE AccountType = 'Savings';
BEGIN
    FOR acc IN savings_cur LOOP
        UPDATE Accounts
        SET Balance = Balance + (acc.Balance * 0.01)
        WHERE AccountID = acc.AccountID;

        DBMS_OUTPUT.PUT_LINE('✅ Interest applied to Account ID: ' || acc.AccountID);
    END LOOP;
END;
/

-- Create procedure: UpdateEmployeeBonus
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department IN VARCHAR2,
    p_bonus_percent IN NUMBER
) AS
    CURSOR emp_cur IS
        SELECT EmployeeID, Salary
        FROM Employees
        WHERE Department = p_department;
BEGIN
    FOR emp IN emp_cur LOOP
        UPDATE Employees
        SET Salary = Salary + (Salary * p_bonus_percent / 100)
        WHERE EmployeeID = emp.EmployeeID;

        DBMS_OUTPUT.PUT_LINE('✅ Bonus applied to Employee ID: ' || emp.EmployeeID);
    END LOOP;
END;
/

-- Create procedure: TransferFunds
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account_id IN NUMBER,
    p_to_account_id IN NUMBER,
    p_amount IN NUMBER
) AS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = p_from_account_id;

    IF v_balance < p_amount THEN
        DBMS_OUTPUT.PUT_LINE('❌ Transfer failed: Insufficient funds in Account ID ' || p_from_account_id);
        RETURN;
    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_amount
    WHERE AccountID = p_from_account_id;

    UPDATE Accounts
    SET Balance = Balance + p_amount
    WHERE AccountID = p_to_account_id;

    DBMS_OUTPUT.PUT_LINE('✅ Transfer of ' || p_amount || ' from Account ' || p_from_account_id ||
                         ' to Account ' || p_to_account_id || ' successful.');
END;
/

-- Sample Data Insertion
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (101, 1, 'Savings', 10000, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (102, 2, 'Savings', 5000, SYSDATE);

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (201, 'Alice HR', 'Manager', 70000, 'HR', SYSDATE);

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (202, 'Bob HR', 'Executive', 60000, 'HR', SYSDATE);

COMMIT;

-- Execute ProcessMonthlyInterest
BEGIN
    ProcessMonthlyInterest;
END;
/

-- Execute UpdateEmployeeBonus
BEGIN
    UpdateEmployeeBonus('HR', 5);
END;
/

-- Execute valid TransferFunds
BEGIN
    TransferFunds(101, 102, 1000);
END;
/

-- Execute invalid TransferFunds (insufficient funds)
BEGIN
    TransferFunds(102, 101, 100000);
END;
/
