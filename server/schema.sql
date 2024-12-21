-- Drop Todo table
DROP TABLE IF EXISTS todo;

-- Create Todo table
CREATE TABLE IF NOT EXISTS todo (
    todo_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(20) NOT NULL,
    contents VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    CONSTRAINT todo_title_contents_check CHECK (LENGTH(title) <= 20 AND LENGTH(contents) <= 255)
);

-- Drop User table
DROP TABLE IF EXISTS user;

-- Create User table
CREATE TABLE IF NOT EXISTS user (
    user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(50) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(50),
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    CONSTRAINT user_username_check CHECK (LENGTH(username) <= 20),
    CONSTRAINT user_name_check CHECK (LENGTH(name) <= 50),
    CONSTRAINT user_phone_check CHECK (LENGTH(phone) <= 20),
    CONSTRAINT user_email_check CHECK (LENGTH(email) <= 50)
);
