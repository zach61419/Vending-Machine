BEGIN TRANSACTION;

DROP TABLE IF EXISTS items, money;

DROP SEQUENCE IF EXISTS seq_item_id;

CREATE SEQUENCE seq_item_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE TABLE items (
  item_id INT DEFAULT nextval('seq_item_id'::regclass) NOT Null,
  location VARCHAR(3),
  name VARCHAR(25) NOT NULL,
  price DECIMAL(13, 2) NOT NULL,
  type VARCHAR(10) NOT NULL,
  inventory INT DEFAULT 5
);

CREATE TABLE money (
  money DECIMAL(13,2) DEFAULT 0
);
  
INSERT INTO items (location, name, price, type) VALUES ('A1', 'Potato Crisps', '3.05', 'Chip');
INSERT INTO items (location, name, price, type) VALUES ('A2', 'Stackers', '1.45', 'Chip');
INSERT INTO items (location, name, price, type) VALUES ('A3', 'Grain Waves', '2.75', 'Chip');
INSERT INTO items (location, name, price, type) VALUES ('A4', 'Cloud Popcorn', '3.65', 'Chip');
INSERT INTO items (location, name, price, type) VALUES ('B1', 'Moonpie', '1.8', 'Candy');
INSERT INTO items (location, name, price, type) VALUES ('B2', 'Cowtales', '1.5', 'Candy');
INSERT INTO items (location, name, price, type) VALUES ('B3', 'Wonka Bar', '1.5', 'Candy');
INSERT INTO items (location, name, price, type) VALUES ('B4', 'Crunchie', '1.75', 'Candy');
INSERT INTO items (location, name, price, type) VALUES ('B5', 'Gummy Rats', '1.75', 'Candy');
INSERT INTO items (location, name, price, type) VALUES ('C1', 'Cola', '1.25', 'Drink');
INSERT INTO items (location, name, price, type) VALUES ('C2', 'Dr. Salt', '1.5', 'Drink');
INSERT INTO items (location, name, price, type) VALUES ('C3', 'Mountain Melter', '1.5', 'Drink');
INSERT INTO items (location, name, price, type) VALUES ('C4', 'Heavy', '1.5', 'Drink');
INSERT INTO items (location, name, price, type) VALUES ('D1', 'U-Chews', '0.85', 'Gum');
INSERT INTO items (location, name, price, type) VALUES ('D2', 'Little League Chew', '0.95', 'Gum');
INSERT INTO items (location, name, price, type) VALUES ('D3', 'Chiclets', '0.75', 'Gum');
INSERT INTO items (location, name, price, type) VALUES ('D4', 'Triplemint', '0.75', 'Gum');
INSERT INTO items (location, name, price, type) VALUES ('D5', 'Zebra Stripes', '0.35', 'Gum');

INSERT INTO money (money) VALUES (0);

--ROLLBACK;
COMMIT TRANSACTION;