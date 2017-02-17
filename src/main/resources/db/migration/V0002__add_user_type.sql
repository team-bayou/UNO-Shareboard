DROP TYPE IF EXISTS user_type;
IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'user_type') THEN
   CREATE TYPE user_type AS ENUM ('admin', 'standard');
END IF;

ALTER TABLE users ADD COLUMN user_type user_type NOT NULL DEFAULT 'standard';