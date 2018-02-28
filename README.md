## README

### 简介

本项目获取了十万级的亚马逊电影数据，以及其对应的百万级评论数据，选择星型模型的基础上，建立混合模型，用于存储和分析数据。

我们选择MySQL和Hive分别作为关系型、分布式数据库。利用Spring boot + angular.js 搭建了查询、展示程序。

### 架构

**文件结构**

1. ETL Scripts：数据获取、清洗、导入脚本
2. E-R Diagram：数据库模型E-R图、设计出的混合模型图以及最终的数据库表
3. Schema：数据库的Schema定义文件
4. Database Output File：数据库的所有表导出的csv文件
5. Model Explain Document：混合模型选型原因和比较方案及结论
6. Search & Statistics Programs：查询、展示程序

**环境及架构**

- 关系型数据库
  - OS：Ubuntu 14.04.1 LTS (GNU/Linux 3.13.0-32-generic x86_64)
  - Hardware：
    - CPU：Core i5 & RAM：2Gb
  - Database：MySQL 5.6.25 
  - Interface： JDBC
- 分布式文件型数据库
  - OS：Ubuntu 14.04.5 LTS (GNU/Linux 4.4.0-31-generic i686)  x3
  - Hardware：
    - CPU：Core i5 & RAM：4Gb  x1
    - CPU：Core i7 & RAM：4Gb  x2
  - Database：Hadoop 2.8.2 & Hive 2.1.1
  - Interface：JDBC
- 查询、展示程序
  - Database Driver：Mybatis & JDBC
  - Backend： Spring Boot
  - Frontend：Angular.js & HTML5

<div  align="center">  

<img src="http://ac-deijvnqa.clouddn.com/65aa98654413837ef5d7.png" style="zoom:50%" />

</div>

### 项目展示

<div  align="center"> 

<img src="http://ac-deijvnqa.clouddn.com/64e8215cc65374441e83.png" style="zoom:50%" />

</div>

<div  align="center"> 

<img src="http://ac-deijvnqa.clouddn.com/b2248791f0ef0f5597ea.png" style="zoom:50%" />

</div>

<div  align="center"> 

<img src="http://ac-deijvnqa.clouddn.com/79ab95d655e29cba8e34.png" style="zoom:50%" />

</div>