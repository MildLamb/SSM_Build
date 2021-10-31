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

- spring-dao.xml
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 关联数据库配置文件 -->
    <context:property-placeholder location="classpath:database.properties"/>

    <!-- 连接池
        dbcp: 半自动化操作  不能自动链接
        c3p0: 自动化操作(自动化的加载配置文件，并且可以自动配置到对象中)
        druid:
        hikari:
     -->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"></property>
        <property name="jdbcUrl" value="${jdbc.url}"></property>
        <property name="user" value="${jdbc.username}"></property>
        <property name="password" value="${jdbc.password}"></property>
    </bean>

    <!-- SqlSessionFactory -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!-- 绑定数据源 -->
        <property name="dataSource" ref="dataSource"></property>
        <!-- 绑定mybatis配置文件 -->
	<!-- 一定要用classpath: 不让就报错-->
        <property name="configLocation" value="classpath:mybatis-config.xml"></property>
    </bean>


    <!-- 配置dao接口扫描包 动态的实现了Dao接口可以注入到Spring容器中 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!-- 注入sqlSessionFactory -->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"></property>
        <!-- 配置要扫描的包 -->
        <property name="basePackage" value="com.mildlamb.dao"></property>
    </bean>

</beans>
```

- spring-service.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 扫描service下的包 -->
    <context:component-scan base-package="com.mildlamb.service"/>

    <!-- 将所有的业务类，注入到spring -->
    <bean id="bookService" class="com.mildlamb.service.BookServiceImpl">
        <property name="bookMapper" ref="bookMapper"></property>
    </bean>


    <!-- 声明式事务 -->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!-- 注入数据源 -->
        <property name="dataSource" ref="dataSource"></property>
    </bean>

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
- web.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!-- DispatcherServlet -->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:applicationContext.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!-- 乱码过滤器 -->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <session-config>
        <session-timeout>15</session-timeout>
    </session-config>

</web-app>
```
- spring-mvc.xml
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc
       http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!-- 注解驱动 -->
    <mvc:annotation-driven></mvc:annotation-driven>
    <!-- 静态资源过滤 -->
    <mvc:default-servlet-handler></mvc:default-servlet-handler>
    <!-- 扫描包 -->
    <context:component-scan base-package="com.mildlamb.controller"></context:component-scan>
    <!-- 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/"></property>
        <property name="suffix" value=".jsp"></property>
    </bean>

</beans>
```
- BookController
```java
@Controller
@RequestMapping("/book")
public class BookController {
    //Controller调用Service层
    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;

    //查询所有的书籍
    @GetMapping("/list")
    public String book_list(Model model){
        List<Books> books = bookService.selectBooks();
        model.addAttribute("allBooks",books);
        return "booklist";
    }
}
```
