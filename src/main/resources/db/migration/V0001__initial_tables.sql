

create schema if not exists shareboard;

CREATE TYPE ad_type AS ENUM ('offer', 'seek');

CREATE TABLE shareboard.images (
    image_id serial PRIMARY KEY,
    image_mime_type VARCHAR(128) NOT NULL,
    image_data bytea NOT NULL,
    description text
);

CREATE TABLE shareboard.app_users (
	user_id serial PRIMARY KEY,
	account_name VARCHAR(30) UNIQUE NOT NULL,
	password_hash CHARACTER(128) NOT NULL,
	password_salt CHARACTER(64) NOT NULL,
	first_name VARCHAR(20),
	last_name VARCHAR(20),
	email VARCHAR(100) UNIQUE NOT NULL,
	phone_number VARCHAR(30),
	facebookID VARCHAR(120),
	twitterHandle VARCHAR(100),
	image_id integer REFERENCES images(image_id) NOT NULL
);

CREATE TABLE shareboard.unverified_users (
    unverified_user_id serial PRIMARY KEY,
    email VARCHAR(120) UNIQUE NOT NULL,
    password_hash CHARACTER(128) NOT NULL,
    password_salt CHARACTER(64) NOT NULL,
    verification_code integer NOT NULL
);

CREATE TABLE shareboard.categories (
	category_id serial PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	color CHARACTER(10) NOT NULL,
	description text,
	parent_category_id integer REFERENCES categories(category_id)
);

CREATE TABLE shareboard.ads (
	ad_id serial PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	description text,
	category_id integer NOT NULL REFERENCES categories(category_id),
	owner integer NOT NULL REFERENCES users(user_id),
	time_published timestamp NOT NULL,
	expiration_date timestamp NOT NULL,
	ad_type ad_type NOT NULL,
	price money,
	trade_item VARCHAR(100)
);

CREATE TABLE shareboard.reviews (
	review_id serial PRIMARY KEY,
	rating integer NOT NULL,
	comments text,
	reviewer integer NOT NULL REFERENCES users(user_id),
	reviewee integer NOT NULL REFERENCES users(user_id)
);

CREATE TABLE shareboard.ad_image_xref (
    ad_image_xref_id serial PRIMARY KEY,
    ad_id integer REFERENCES ads(ad_id) NOT NULL,
    image_id integer REFERENCES images(image_id) NOT NULL
);