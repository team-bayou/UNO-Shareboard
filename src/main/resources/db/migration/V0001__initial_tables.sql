DROP TYPE IF EXISTS ad_type;
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'ad_type') THEN
        CREATE TYPE ad_type AS ENUM ('offer', 'seek');
    END IF;
END$$;

DROP TABLE IF EXISTS schema_version;
DROP TABLE IF EXISTS ad_image_xref;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS ads;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS unverified_users;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS images;

CREATE TABLE images (
    image_id serial PRIMARY KEY,
    image_mime_type VARCHAR(128) NOT NULL,
    image_data bytea NOT NULL,
    description text
);

CREATE TABLE users (
	user_id serial PRIMARY KEY,
	account_name VARCHAR(30) UNIQUE NOT NULL,
	password_hash CHARACTER(128) NOT NULL,
	password_salt CHARACTER(64) NOT NULL,
	first_name VARCHAR(20),
	last_name VARCHAR(20),
	email VARCHAR(100) UNIQUE NOT NULL,
	phone_number VARCHAR(30),
	facebook_id VARCHAR(120),
	twitter_handle VARCHAR(100),
	image_id integer REFERENCES images(image_id)
);

CREATE TABLE unverified_users (
    unverified_user_id serial PRIMARY KEY,
    email VARCHAR(120) UNIQUE NOT NULL,
    password_hash CHARACTER(128) NOT NULL,
    password_salt CHARACTER(64) NOT NULL,
    verification_code integer NOT NULL
);

CREATE TABLE categories (
	category_id serial PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	color CHARACTER(10) NOT NULL,
	description text,
	parent_category_id integer REFERENCES categories(category_id)
);

CREATE TABLE ads (
	ad_id serial PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	description text,
	category_id integer REFERENCES categories(category_id) NOT NULL,
	owner integer REFERENCES users(user_id) NOT NULL,
	time_published timestamp NOT NULL,
	expiration_date timestamp NOT NULL,
	ad_type ad_type NOT NULL,
	price money,
	trade_item VARCHAR(100)
);

CREATE TABLE reviews (
	review_id serial PRIMARY KEY,
	rating integer NOT NULL,
	comments text,
	reviewer integer REFERENCES users(user_id) NOT NULL,
	reviewee integer REFERENCES users(user_id) NOT NULL
);

CREATE TABLE ad_image_xref (
    ad_image_xref_id serial PRIMARY KEY,
    ad_id integer REFERENCES ads(ad_id) NOT NULL,
    image_id integer REFERENCES images(image_id) NOT NULL
);