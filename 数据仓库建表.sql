CREATE TABLE movie
(
    movie_id VARCHAR(30) PRIMARY KEY,
    title VARCHAR(50),
    price DOUBLE,
    review DOUBLE,
    r_count INT(8),
    date DATE
);
CREATE TABLE m_product
(
    product_id VARCHAR(30) PRIMARY KEY,
    price DOUBLE,
    sales_rank DOUBLE,
    binding INT(6),
    running_time DATE
);
CREATE TABLE date
(
	movie_id VARCHAR(30) PRIMARY KEY,
	date DATE,
	genre VARCHAR(30)
);
CREATE TABLE starring
(
    name VARCHAR(30),
    movie_id VARCHAR(30)
    
);

CREATE TABLE actor
(
    name VARCHAR(30),
    movie_id VARCHAR(30)
    
);

CREATE TABLE director
(
    name VARCHAR(30),
    movie_id VARCHAR(30)
    
);
ALTER TABLE director ADD PRIMARY KEY (name, movie_id);
CREATE TABLE genre
(
    genre VARCHAR(30),
    movie_id VARCHAR(30)
);
ALTER TABLE genre ADD PRIMARY KEY (genre, movie_id);
CREATE TABLE language
(
    language VARCHAR(30),
    movie_id VARCHAR(30)
);
ALTER TABLE language ADD PRIMARY KEY (language, movie_id);
CREATE TABLE binding
(
    binding VARCHAR(30),
    movie_id VARCHAR(30)
);
ALTER TABLE binding ADD PRIMARY KEY (binding, movie_id);
CREATE TABLE studio
(
    studio VARCHAR(30),
    movie_id VARCHAR(30)
);
ALTER TABLE studio ADD PRIMARY KEY (studio, movie_id);
ALTER TABLE m_product MODIFY binding VARCHAR(30);
ALTER TABLE m_product MODIFY running_time DOUBLE;
ALTER TABLE m_product MODIFY sales_rank INT(6);
ALTER TABLE m_product MODIFY sales_rank INT(11);
ALTER TABLE date MODIFY date VARCHAR(20);