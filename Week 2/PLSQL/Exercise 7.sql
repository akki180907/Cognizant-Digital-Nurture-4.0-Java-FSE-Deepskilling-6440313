SET SERVEROUTPUT ON;

-- PACKAGE 1: CustomerManagement
CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE, p_balance NUMBER);
    PROCEDURE UpdateCustomerName(p_id NUMBER, p_new_name VARCHAR2);
    FUNCTION GetCustomerBalance(p_id NUMBER) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
    PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE, p_balance NUMBER) IS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);
        DBMS_OUTPUT.PUT_LINE('✅ Customer added: ' || p_name);
    END;

    PROCEDURE UpdateCustomerName(p_id NUMBER, p_new_name VARCHAR2) IS
    BEGIN
        UPDATE Customers SET Name = p_new_name, LastModified = SYSDATE
        WHERE CustomerID = p_id;
        DBMS_OUTPUT.PUT_LINE('✅ Customer name updated for ID: ' || p_id);
    END;

    FUNCTION GetCustomerBalance(p_id NUMBER) RETURN NUMBER IS
        v_balance NUMBER;
    BEGIN
        SELECT Balance INTO v_balance FROM Customers WHERE CustomerID = p_id;
        RETURN v_balance;
    END;
END CustomerManagement;
/

-- PACKAGE 2: EmployeeManagement
CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_position VARCHAR2, p_salary NUMBER, p_dept VARCHAR2);
    PROCEDURE UpdateEmployeePosition(p_id NUMBER, p_new_position VARCHAR2);
    FUNCTION GetAnnualSalary(p_id NUMBER) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS
    PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_position VARCHAR2, p_salary NUMBER, p_dept VARCHAR2) IS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_id, p_name, p_position, p_salary, p_dept, SYSDATE);
        DBMS_OUTPUT.PUT_LINE('✅ Employee hired: ' || p_name);
    END;

    PROCEDURE UpdateEmployeePosition(p_id NUMBER, p_new_position VARCHAR2) IS
    BEGIN
        UPDATE Employees SET Position = p_new_position WHERE EmployeeID = p_id;
        DBMS_OUTPUT.PUT_LINE('✅ Position updated for Employee ID: ' || p_id);
    END;

    FUNCTION GetAnnualSalary(p_id NUMBER) RETURN NUMBER IS
        v_salary NUMBER;
    BEGIN
        SELECT Salary INTO v_salary FROM Employees WHERE EmployeeID = p_id;
        RETURN v_salary * 12;
    END;
END EmployeeManagement;
/

-- PACKAGE 3: AccountOperations
CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(p_id NUMBER, p_customer_id NUMBER, p_type VARCHAR2, p_balance NUMBER);
    PROCEDURE CloseAccount(p_id NUMBER);
    FUNCTION GetTotalBalance(p_customer_id NUMBER) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS
    PROCEDURE OpenAccount(p_id NUMBER, p_customer_id NUMBER, p_type VARCHAR2, p_balance NUMBER) IS
    BEGIN
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_id, p_customer_id, p_type, p_balance, SYSDATE);
        DBMS_OUTPUT.PUT_LINE('✅ Account opened for Customer ID: ' || p_customer_id);
    END;

    PROCEDURE CloseAccount(p_id NUMBER) IS
    BEGIN
        DELETE FROM Accounts WHERE AccountID = p_id;
        DBMS_OUTPUT.PUT_LINE('✅ Account ID ' || p_id || ' closed.');
    END;

    FUNCTION GetTotalBalance(p_customer_id NUMBER) RETURN NUMBER IS
        v_total NUMBER;
    BEGIN
        SELECT NVL(SUM(Balance), 0) INTO v_total FROM Accounts WHERE CustomerID = p_customer_id;
        RETURN v_total;
    END;
END AccountOperations;
/

-- Sample calls and output
BEGIN
    CustomerManagement.AddCustomer(701, 'Package Test', TO_DATE('1990-01-01', 'YYYY-MM-DD'), 4000);
    CustomerManagement.UpdateCustomerName(701, 'Package Test Updated');
    DBMS_OUTPUT.PUT_LINE('Customer Balance: ' || CustomerManagement.GetCustomerBalance(701));
END;
/

BEGIN
    EmployeeManagement.HireEmployee(701, 'Emp Package', 'Analyst', 30000, 'Finance');
    EmployeeManagement.UpdateEmployeePosition(701, 'Senior Analyst');
    DBMS_OUTPUT.PUT_LINE('Annual Salary: ' || EmployeeManagement.GetAnnualSalary(701));
END;
/

BEGIN
    AccountOperations.OpenAccount(701, 701, 'Savings', 10000);
    AccountOperations.OpenAccount(702, 701, 'Checking', 5000);
    DBMS_OUTPUT.PUT_LINE('Total Balance: ' || AccountOperations.GetTotalBalance(701));
    AccountOperations.CloseAccount(702);
END;
/
