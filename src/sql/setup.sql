-- =========================
-- CLEAN START (optional)
-- =========================
DROP TABLE Sale CASCADE CONSTRAINTS;
DROP TABLE Purchase CASCADE CONSTRAINTS;
DROP TABLE Stock CASCADE CONSTRAINTS;
DROP TABLE Product CASCADE CONSTRAINTS;
DROP TABLE Supplier CASCADE CONSTRAINTS;

DROP SEQUENCE sale_seq;
DROP SEQUENCE purchase_seq;
DROP SEQUENCE stock_seq;
DROP SEQUENCE product_seq;
DROP SEQUENCE supplier_seq;

-- =========================
-- SUPPLIER
-- =========================
CREATE TABLE Supplier (
    supplier_id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    contact_info VARCHAR2(150)
);

CREATE SEQUENCE supplier_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER supplier_trigger
BEFORE INSERT ON Supplier
FOR EACH ROW
BEGIN
    IF :NEW.supplier_id IS NULL THEN
        SELECT supplier_seq.NEXTVAL INTO :NEW.supplier_id FROM dual;
    END IF;
END;
/

-- =========================
-- PRODUCT
-- =========================
CREATE TABLE Product (
    product_id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    price NUMBER(10,2) CHECK (price >= 0)
);

CREATE SEQUENCE product_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER product_trigger
BEFORE INSERT ON Product
FOR EACH ROW
BEGIN
    IF :NEW.product_id IS NULL THEN
        SELECT product_seq.NEXTVAL INTO :NEW.product_id FROM dual;
    END IF;
END;
/

-- =========================
-- STOCK
-- =========================
CREATE TABLE Stock (
    stock_id NUMBER PRIMARY KEY,
    product_id NUMBER UNIQUE,
    quantity NUMBER DEFAULT 0 CHECK (quantity >= 0),

    CONSTRAINT fk_stock_product
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

CREATE SEQUENCE stock_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER stock_trigger
BEFORE INSERT ON Stock
FOR EACH ROW
BEGIN
    IF :NEW.stock_id IS NULL THEN
        SELECT stock_seq.NEXTVAL INTO :NEW.stock_id FROM dual;
    END IF;
END;
/

-- =========================
-- PURCHASE
-- =========================
CREATE TABLE Purchase (
    purchase_id NUMBER PRIMARY KEY,
    supplier_id NUMBER,
    product_id NUMBER,
    quantity NUMBER CHECK (quantity > 0),
    price NUMBER(10,2) CHECK (price >= 0),
    purchase_date DATE DEFAULT SYSDATE,

    CONSTRAINT fk_purchase_supplier
    FOREIGN KEY (supplier_id) REFERENCES Supplier(supplier_id),

    CONSTRAINT fk_purchase_product
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

CREATE SEQUENCE purchase_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER purchase_trigger
BEFORE INSERT ON Purchase
FOR EACH ROW
BEGIN
    IF :NEW.purchase_id IS NULL THEN
        SELECT purchase_seq.NEXTVAL INTO :NEW.purchase_id FROM dual;
    END IF;
END;
/

-- =========================
-- SALE
-- =========================
CREATE TABLE Sale (
    sale_id NUMBER PRIMARY KEY,
    product_id NUMBER,
    quantity NUMBER CHECK (quantity > 0),
    price NUMBER(10,2) CHECK (price >= 0),
    sale_date DATE DEFAULT SYSDATE,

    CONSTRAINT fk_sale_product
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

CREATE SEQUENCE sale_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER sale_trigger
BEFORE INSERT ON Sale
FOR EACH ROW
BEGIN
    IF :NEW.sale_id IS NULL THEN
        SELECT sale_seq.NEXTVAL INTO :NEW.sale_id FROM dual;
    END IF;
END;
/
