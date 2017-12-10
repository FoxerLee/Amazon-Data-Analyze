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

