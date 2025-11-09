CREATE DATABASE LIBMANG_K1405
GO
USE LIBMANG_K1405
GO
CREATE TABLE books(
    id INT PRIMARY KEY IDENTITY,
    book_code NVARCHAR(20) UNIQUE,
    title NVARCHAR(100),
    author NVARCHAR(100),
    category NVARCHAR(100),
    remaining INT
)
GO
CREATE TABLE account(
    id INT PRIMARY KEY IDENTITY,
    username NVARCHAR(100) UNIQUE,
    password NVARCHAR(255),
    role NVARCHAR(20) NOT NULL CHECK (role IN ('USER','ADMIN')),
    email NVARCHAR(100) UNIQUE,
    create_date DATETIME2
)
GO
CREATE TABLE borrow_record(
    account_id INT FOREIGN KEY REFERENCES account(id) ON DELETE CASCADE,
    book_id INT FOREIGN KEY REFERENCES books(id) ON DELETE CASCADE,
    start_date DATETIME2,
    end_date DATETIME2,
    PRIMARY KEY(account_id,book_id)
)
GO
SELECT * FROM books
GO
SELECT * FROM account
GO
SELECT * FROM borrow_record
GO
INSERT INTO account
VALUES
('Khanh207','$2a$12$C/QsRF2bvJtkVcdddB5y5.kyA.yZzuskPFV.KWj9tMPT8JXxIN/Rq','ADMIN','doanbaokhanh14052004@gmail.com',CURRENT_TIMESTAMP),
('Khanh207user','$2a$12$C/QsRF2bvJtkVcdddB5y5.kyA.yZzuskPFV.KWj9tMPT8JXxIN/Rq','USER','doanbaokhanh14052007@gmail.com',CURRENT_TIMESTAMP)
-- K1405207
GO
