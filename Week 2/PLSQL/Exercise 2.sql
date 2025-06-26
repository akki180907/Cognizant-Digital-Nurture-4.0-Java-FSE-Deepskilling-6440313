-- Enable DBMS Output
SET SERVEROUTPUT ON;

-- Create Error Log Table
CREATE TABLE ErrorLog (
    LogID NUMBER GENERATED ALWAYS AS IDENTITY,
    ErrorMessage VARCHAR2(4000),
    LogDate DATE DEFAULT SYSDATE
);

-- Create Procedure: SafeTransferFunds
CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_from_account_id IN NUMBER,
    p_to_account_id IN NUMBER,
    p_amount IN NUMBER
) AS
    v_from_balance NUMBER;
    v_error_msg VARCHAR2(4000);
BEGIN
    SELECT Balance INTO v_from_balance
    FROM Accounts
    WHERE AccountID = p_from_account_id;

    IF v_from_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in source account.');
    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_amount
    WHERE AccountID = p_from_account_id;

    UPDATE Accounts
    SET Balance = Balance + p_amount
    WHERE AccountID = p_to_account_id;

    DBMS_OUTPUT.PUT_LINE('✅ Transfer successful from Account ' || p_from_account_id || ' to Account ' || p_to_account_id);

EXCEPTION
    WHEN OTHERS THEN
        v_error_msg := SQLERRM;
        INSERT INTO ErrorLog(ErrorMessage) VALUES(v_error_msg);
        DBMS_OUTPUT.PUT_LINE('❌ Transfer failed: ' || v_error_msg);
        ROLLBACK;
END;
/

-- Create Procedure: UpdateSalary
CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_employee_id IN NUMBER,
    p_percentage IN NUMBER
) AS
    v_error_msg VARCHAR2(4000);
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_percentage / 100)
    WHERE EmployeeID = p_employee_id;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Employee ID not found.');
    END IF;

    DBMS_OUTPUT.PUT_LINE('✅ Salary updated for Employee ID: ' || p_employee_id);

EXCEPTION
    WHEN OTHERS THEN
        v_error_msg := SQLERRM;
        INSERT INTO ErrorLog(ErrorMessage) VALUES(v_error_msg);
        DBMS_OUTPUT.PUT_LINE('❌ Salary update failed: ' || v_error_msg);
END;
/

-- Create Procedure: AddNewCustomer
CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_customer_id IN NUMBER,
    p_name IN VARCHAR2,
    p_dob IN DATE,
    p_balance IN NUMBER
) AS
    v_error_msg VARCHAR2(4000);
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);

    DBMS_OUTPUT.PUT_LINE('✅ Customer added: ' || p_name);

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        v_error_msg := 'Duplicate Customer ID: ' || p_customer_id;
        INSERT INTO ErrorLog(ErrorMessage) VALUES(v_error_msg);
        DBMS_OUTPUT.PUT_LINE('❌ ' || v_error_msg);

    WHEN OTHERS THEN
        v_error_msg := SQLERRM;
        INSERT INTO ErrorLog(ErrorMessage) VALUES(v_error_msg);
        DBMS_OUTPUT.PUT_LINE('❌ Failed to add customer: ' || v_error_msg);
END;
/

-- Sample Data for Testing
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (10, 1, 'Savings', 5000, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (20, 2, 'Savings', 1000, SYSDATE);

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (99, 'Test Emp', 'Dev', 30000, 'IT', SYSDATE);

COMMIT;

-- Call: Valid Transfer
BEGIN
    SafeTransferFunds(10, 20, 1000);
END;
/

-- Call: Invalid Transfer (Insufficient Funds)
BEGIN
    SafeTransferFunds(10, 20, 100000);
END;
/

-- Call: Valid Salary Update
BEGIN
    UpdateSalary(99, 10);
END;
/

-- Call: Invalid Salary Update
BEGIN
    UpdateSalary(999, 5);
END;
/

-- Call: Valid Customer Add
BEGIN
    AddNewCustomer(200, 'New User', TO_DATE('2000-01-01', 'YYYY-MM-DD'), 3000);
END;
/

-- Call: Duplicate Customer Add
BEGIN
    AddNewCustomer(1, 'Duplicate User', TO_DATE('1990-01-01', 'YYYY-MM-DD'), 5000);
END;
/

-- View Logged Errors
SELECT * FROM ErrorLog ORDER BY LogDate DESC;
