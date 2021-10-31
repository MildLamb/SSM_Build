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
- Maven依赖
```xml
    <!-- 依赖 junit,数据库驱动，连接池，servlet，jsp，mybatis，mybatis-spring，spring -->
    <dependencies>
        <!-- junit -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
        <!-- 数据库驱动 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.48</version>
        </dependency>
        <!-- 数据库连接池 -->
        <dependency>
            <groupId>com.mchange</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.5.5</version>
        </dependency>

        <!-- Servlet JSP -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.2</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>

        <!-- Mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.2</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.6</version>
        </dependency>

        <!-- Spring -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.3.11</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.3.11</version>
        </dependency>
    </dependencies>
```
