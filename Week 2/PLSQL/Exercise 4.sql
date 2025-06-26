SET SERVEROUTPUT ON;

CREATE OR REPLACE FUNCTION CalculateAge(p_dob DATE)
RETURN NUMBER
IS
    v_age NUMBER;
BEGIN
    v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
    RETURN v_age;
END;
/

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount NUMBER,
    p_interest_rate NUMBER,
    p_years NUMBER
)
RETURN NUMBER
IS
    v_monthly_rate NUMBER := p_interest_rate / 12 / 100;
    v_months NUMBER := p_years * 12;
    v_installment NUMBER;
BEGIN
    v_installment := p_loan_amount * v_monthly_rate /
                     (1 - POWER(1 + v_monthly_rate, -v_months));
    RETURN ROUND(v_installment, 2);
END;
/

CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_account_id IN NUMBER,
    p_amount IN NUMBER
)
RETURN BOOLEAN
IS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_account_id;
    RETURN v_balance >= p_amount;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END;
/

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (301, 'Customer Age Test', TO_DATE('1980-01-01', 'YYYY-MM-DD'), 5000, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (301, 301, 'Savings', 8000, SYSDATE);

COMMIT;

BEGIN
    DBMS_OUTPUT.PUT_LINE('Age of Customer 301: ' || CalculateAge(TO_DATE('1980-01-01', 'YYYY-MM-DD')));
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('Monthly Installment: ' ||
        CalculateMonthlyInstallment(100000, 8, 5));
END;
/

BEGIN
    IF HasSufficientBalance(301, 6000) THEN
        DBMS_OUTPUT.PUT_LINE('✅ Account 301 has sufficient balance.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('❌ Account 301 does NOT have sufficient balance.');
    END IF;
END;
/

BEGIN
    IF HasSufficientBalance(301, 10000) THEN
        DBMS_OUTPUT.PUT_LINE('✅ Account 301 has sufficient balance.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('❌ Account 301 does NOT have sufficient balance.');
    END IF;
END;
/
