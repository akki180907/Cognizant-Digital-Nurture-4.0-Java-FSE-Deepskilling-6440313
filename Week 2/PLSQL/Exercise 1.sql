-- Sample customer above 60 years old
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (101, 'Senior User', TO_DATE('1950-01-01', 'YYYY-MM-DD'), 20000, SYSDATE);

-- Sample loan for senior customer
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (201, 101, 50000, 8, SYSDATE, SYSDATE + 15); -- due in 15 days

COMMIT;

-- Add IsVIP column if not exists
BEGIN
    EXECUTE IMMEDIATE 'ALTER TABLE Customers ADD (IsVIP VARCHAR2(5))';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE = -01430 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
END;
/

-- Scenario 1: Discount for customers over 60
BEGIN
    FOR rec IN (
        SELECT c.CustomerID, l.LoanID, c.DOB
        FROM Customers c
        JOIN Loans l ON c.CustomerID = l.CustomerID
    ) LOOP
        IF MONTHS_BETWEEN(SYSDATE, rec.DOB) / 12 > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE LoanID = rec.LoanID;

            DBMS_OUTPUT.PUT_LINE('✅ 1% discount applied to Loan ID ' || rec.LoanID ||
                                 ' (Customer ID ' || rec.CustomerID || ')');
        END IF;
    END LOOP;
END;
/

-- Scenario 2: Promote VIP
BEGIN
    FOR rec IN (
        SELECT CustomerID, Balance
        FROM Customers
        WHERE Balance > 10000
    ) LOOP
        UPDATE Customers
        SET IsVIP = 'TRUE'
        WHERE CustomerID = rec.CustomerID;

        DBMS_OUTPUT.PUT_LINE('🌟 Customer ID ' || rec.CustomerID || ' promoted to VIP.');
    END LOOP;
END;
/

-- Scenario 3: Loan reminders
BEGIN
    FOR rec IN (
        SELECT LoanID, CustomerID, EndDate
        FROM Loans
        WHERE EndDate BETWEEN SYSDATE AND SYSDATE + 30
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('📢 Reminder: Loan ID ' || rec.LoanID ||
                             ' for Customer ID ' || rec.CustomerID ||
                             ' is due on ' || TO_CHAR(rec.EndDate, 'DD-MON-YYYY'));
    END LOOP;
END;
/
