CREATE TABLE users (
	user_id serial PRIMARY KEY,
	account_name VARCHAR(30) NOT NULL,
	first_name VARCHAR(20),
	last_name VARCHAR(20),
	email VARCHAR(120) NOT NULL,
	phone_number VARCHAR(12),
	facebookID VARCHAR(120),
	twitterHandle VARCHAR(100)
);

CREATE TABLE ads (
	ad_id serial PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	description text,
	category_id integer NOT NULL REFERENCES categories(category_id),
	owner integer NOT NULL REFERENCES users(user_id),
	time_published timestamp NOT NULL,
	price money,
	trade_item VARCHAR(100)	
);

CREATE TABLE categories (
	category_id serial PRIMARY KEY,
	name VARCHAR(255),
	description text,
	parent_category_id integer REFERENCES categories(category_id),
);

CREATE TABLE review (
	review_id serial PRIMARY KEY,
	rating integer NOT NULL,
	comments text,
	reviewer integer NOT NULL REFERENCES users(user_id),
	reviewee integer NOT NULL REFERENCES users(user_id)	
);