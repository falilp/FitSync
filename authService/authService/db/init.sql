-- Database: authService

-- DROP DATABASE IF EXISTS "authService";

CREATE DATABASE "authService"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'es-ES'
    LC_CTYPE = 'es-ES'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- SCHEMA: public

-- DROP SCHEMA IF EXISTS public ;

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA public
    IS 'standard public schema';

GRANT USAGE ON SCHEMA public TO PUBLIC;

GRANT ALL ON SCHEMA public TO pg_database_owner;

CREATE TYPE userRole AS ENUM ('ADMIN', 'ATHLETE', 'TRAINER');
CREATE TYPE userGender AS ENUM ('MALE', 'FEMALE', 'OTHER');

CREATE TABLE IF NOT EXISTS public.users(
    id SERIAL PRIMARY KEY,
	userName VARCHAR(64) UNIQUE NOT NULL,
    name VARCHAR(64) NOT NULL,
    surName VARCHAR(128),
	gender userGender NOT NULL DEFAULT 'OTHER',
	height INTEGER NOT NULL,
	weight INTEGER NOT NULL,
	birthDate DATE NOT NULL,
	password TEXT,
	verifyToken TEXT,
    email VARCHAR(128) UNIQUE NOT NULL,
    role userRole NOT NULL DEFAULT 'ATHLETE',
    isActive BOOLEAN NOT NULL DEFAULT true,
    logicalErase BOOLEAN NOT NULL DEFAULT false,
    createdDate DATE NOT NULL DEFAULT CURRENT_DATE,
    lastModifiedDate DATE NOT NULL DEFAULT CURRENT_DATE
);

ALTER TABLE IF EXISTS public.users
OWNER to postgres;

CREATE OR REPLACE FUNCTION updateLastModified()
RETURNS TRIGGER AS $$
BEGIN
    NEW.lastModifiedDate = CURRENT_DATE;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS triggerUpdateLastModified ON users;

CREATE TRIGGER triggerUpdateLastModified
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE FUNCTION updateLastModified();