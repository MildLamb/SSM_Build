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
- Spring配置文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/tx
        http://www.springframework.org/schema/tx/spring-tx.xsd">
</beans>
```
- Mybatis配置文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!-- mybatis核心配置文件 -->
<configuration>
    <!-- mybatis配置数据源，但与spring整合后可以给spring去做 -->

    <typeAliases>
        <package name="com.mildlamb.pojo"/>
    </typeAliases>

    <mappers>
        <mapper class="com.mildlamb.dao.BookMapper"></mapper>
    </mappers>
</configuration>
```
- mapper映射文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.mildlamb.dao.BookMapper">
    <insert id="addBook" parameterType="books">
        insert into ssmbuild.books(bookName, bookCounts, detail) values (#{bookName},#{bookCounts},#{detail})
    </insert>

    <delete id="delBook" parameterType="int">
        delete from ssmbuild.books
        <where>
            bookId = #{bookid}
        </where>
    </delete>

    <update id="updateBook" parameterType="books">
        update ssmbuild.books set bookName=#{bookName},bookCounts=#{bookCounts},detail=#{detail}
        <where>
            bookId = #{bookId}
        </where>
    </update>

    <select id="selectBooks" resultType="books">
        select * from ssmbuild.books
    </select>
    
    <select id="selectBookById" parameterType="int" resultType="books">
        select * from ssmbuild.books
        <where>
            bookId = #{bid}
        </where>
    </select>
</mapper>
```
- dao(mapper)接口
```java
public interface BookMapper {
    //增加一本书
    int addBook(Books books);
    //删除一本书
    int delBook(@Param("bookid") Integer id);
    //修改一本书
    int updateBook(Books books);
    //查询书籍
    List<Books> selectBooks();
    //查询一本书
    Books selectBookById(@Param("bid") Integer id);
}
```
- service实现类(接口省略)
```java
public class BookServiceImpl implements BookService {

    //service调用dao层
    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public int addBook(Books books) {
        return bookMapper.addBook(books);
    }

    public int delBook(Integer id) {
        return bookMapper.delBook(id);
    }

    public int updateBook(Books books) {
        return bookMapper.updateBook(books);
    }

    public List<Books> selectBooks() {
        return bookMapper.selectBooks();
    }

    public Books selectBookById(Integer id) {
        return bookMapper.selectBookById(id);
    }
}
```
