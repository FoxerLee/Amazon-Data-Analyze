CREATE TABLE id(

	product_id VARCHAR(50) NOT NULL,
  	PRIMARY KEY (product_id)
);
CREATE TABLE id_1(
	product_id VARCHAR(50) NOT NULL,
  	PRIMARY KEY (product_id)
);
CREATE TABLE id_2(

	product_id VARCHAR(50) NOT NULL,
  	PRIMARY KEY (product_id)
);
CREATE TABLE id_3(

	product_id VARCHAR(50) NOT NULL,
  	PRIMARY KEY (product_id)
);
CREATE TABLE id_4(

	product_id VARCHAR(50) NOT NULL,
  	PRIMARY KEY (product_id)
);
CREATE TABLE id_5(

	product_id VARCHAR(50) NOT NULL,
  	PRIMARY KEY (product_id)
);

CREATE TABLE error(

	product_id VARCHAR(50) NOT NULL,
  	PRIMARY KEY (product_id)
);

CREATE TABLE product(
	id VARCHAR(50) NOT NULL,
	title VARCHAR(500) NULL,
	actors VARCHAR(500) NULL,
	directors VARCHAR(500) NULL,
	sales_rank INT NULL,
	price DOUBLE NULL,
	genre VARCHAR(500) NULL,
	languages VARCHAR(500) NULL,
	binding VARCHAR(500) NULL,
	studio VARCHAR(500) NULL,
	browse_node VARCHAR(500) NULL,
	running_time VARCHAR(500) NULL,
	publication_date DATE NULL,
	release_date DATE NULL,

  	PRIMARY KEY (id)
);

CREATE TABLE warehouse.connect_id(
	id_1 VARCHAR(50) NOT NULL,
	id_2 VARCHAR(50) NOT NULL,
	PRIMARY KEY (id_1,id_2)
);

CREATE TABLE warehouse.no_page(
	product_id VARCHAR(50) NOT NULL,
  	PRIMARY KEY (product_id)
);

CREATE TABLE warehouse.product_two(
	id VARCHAR(50) NOT NULL,
	IMDb DOUBLE NULL,
	genres VARCHAR(100) NULL,
	starring VARCHAR(100) NULL,
	actors VARCHAR(500) NULL,
	rating VARCHAR(20) NULL,
	review_count INT NULL,
	review DOUBLE NULL,
	PRIMARY KEY (id)
);

