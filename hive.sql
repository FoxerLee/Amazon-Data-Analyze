CREATE TABLE movie(id string, actors string, binding string, directors string, genres string, languages string, starrings string, studios string, title string, r_date string, products string, year int, month int, week int)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
   "separatorChar" = "\,",   
   "quoteChar"     = "\""   
)  
STORED AS TEXTFILE;
load data local inpath '/home/hadoop/final_data/movies.csv' overwrite into table movie;

CREATE TABLE binding(binding string, count int)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
   "separatorChar" = "\,",   
   "quoteChar"     = "\""   
)  
STORED AS TEXTFILE;
load data local inpath '/home/hadoop/final_data/re_binding.csv' overwrite into table binding;

CREATE TABLE r_date(id int, year int, month int, week int, count int, movies string)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
   "separatorChar" = "\,",   
   "quoteChar"     = "\""   
)
STORED AS TEXTFILE;
load data local inpath '/home/hadoop/final_data/real_re_date.csv' overwrite into table r_date;

CREATE TABLE genre(genre string, count int, movies string)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
   "separatorChar" = "\,",   
   "quoteChar"     = "\""   
)  
STORED AS TEXTFILE;
load data local inpath '/home/hadoop/final_data/re_genre.csv' overwrite into table genre;

CREATE TABLE language(language string, count int)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
   "separatorChar" = "\,",   
   "quoteChar"     = "\""   
)  
STORED AS TEXTFILE;
load data local inpath '/home/hadoop/final_data/re_language.csv' overwrite into table language;

CREATE TABLE product(id string, sales_rank int, price double, languages string, binding string, studios string, running_time int)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
   "separatorChar" = "\,",   
   "quoteChar"     = "\""   
)  
STORED AS TEXTFILE;
load data local inpath '/home/hadoop/final_data/dw_product1.csv' overwrite into table product;

CREATE TABLE starring(name string, count int, movies string)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
   "separatorChar" = "\,",   
   "quoteChar"     = "\""   
)  
STORED AS TEXTFILE;
load data local inpath '/home/hadoop/final_data/re_starring.csv' overwrite into table starring;

CREATE TABLE studio(studio string, count int)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
   "separatorChar" = "\,",   
   "quoteChar"     = "\""   
)  
STORED AS TEXTFILE;
load data local inpath '/home/hadoop/final_data/re_studio.csv' overwrite into table studio;

CREATE TABLE actor(name string, movies string, count int, style string, director string, corporation string)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
   "separatorChar" = "\,",   
   "quoteChar"     = "\""   
)  
STORED AS TEXTFILE;
load data local inpath '/home/hadoop/final_data/final_actor.csv' overwrite into table actor;

CREATE TABLE director(name string, movies string, count int, style string, actors string, corporation string)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
   "separatorChar" = "\,",   
   "quoteChar"     = "\""   
)  
STORED AS TEXTFILE;
load data local inpath '/home/hadoop/final_data/final_director.csv' overwrite into table director;

CREATE TABLE ranking(id string, product string, ranking double)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
   "separatorChar" = "\,",   
   "quoteChar"     = "\""   
)  
STORED AS TEXTFILE;
load data local inpath '/home/hadoop/final_data/hive_rank.csv' overwrite into table ranking;
