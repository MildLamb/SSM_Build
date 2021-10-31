# SSM_Build
整合SSM

## 环境搭建
- 数据库
```sql
CREATE DATABASE `ssmbuild`;

CREATE TABLE `books`(
	`bookId` INT(10) PRIMARY KEY AUTO_INCREMENT COMMENT '图书id',
	`bookName` VARCHAR(60) NOT NULL COMMENT '图书名称',
	`bookCounts` INT(10) NOT NULL COMMENT '图书数量',
	`detail` VARCHAR(200) NOT NULL COMMENT '描述'
	)ENGINE=INNODB DEFAULT CHARSET=utf8;
	
INSERT INTO `books`(`bookId`,`bookName`,`bookCounts`,`detail`) VALUES
(1,'Java',10,'从入门到入土'),
(2,'Mysql',20,'从删库到跑路'),
(3,'Linux',40,'从进门到进牢');
```
- 
