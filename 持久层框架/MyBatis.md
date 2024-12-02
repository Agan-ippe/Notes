# 1、初识MyBaits

> 官方帮助手册

https://mybatis.org/mybatis-3/zh/index.html

## 1.1、MyBatis的作用

MyBatis是用来封装`JDBC`，负责访问数据库，完成持久化操作的。



## 1.2、MyBatis的特性

- MyBatis 是支持定制化 SQL、存储过程以及高级映射的优秀的持久层框架。
- MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。
- MyBatis可以使用简单的XML或注解用于配置和原始映射，将接口和Java的POJO（Plain Old Java
  Objects，普通的Java对象）映射成数据库中的记录。
- MyBatis 是一个 半自动的ORM（Object Relation Mapping）框架。



## 1.3、MyBatis和其他持久层技术对比

- JDBC
  - SQL 夹杂在Java代码中耦合度高，导致硬编码内伤
  - 维护不易且实际开发需求中 SQL 有变化，频繁修改的情况多见
  - 代码冗长，开发效率低
- Hibernate 和 JPA
  - 操作简便，开发效率高
  - 程序中的长难复杂 SQL 需要绕过框架
  - 内部自动生产的 SQL，不容易做特殊优化
  - 基于全映射的全自动框架，大量字段的 POJO 进行部分映射时比较困难。
  - 反射操作太多，导致数据库性能下降
- MyBatis
  - 轻量级，性能出色
  - SQL 和 Java 编码分开，功能边界清晰。Java代码专注业务、SQL语句专注数据
  - 开发效率稍逊于HIbernate，但是完全能够接受



# 2、MyBatis的使用

## 2.1、创建MyBatis的配置文件

>习惯上命名为mybatis-config.xml，非强制要求。将来整合Spring之后，这个配置文件可以省略。
>
>核心配置文件主要用于配置连接数据库的环境以及MyBatis的全局配置信息
>
>核心配置文件存放的位置是src/main/resources目录下

**模板**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties resource="jdbc.properties"/>
    <typeAliases>
        <typeAlias type=""/>
        <!-- <package name=""/> -->
    </typeAliases>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driver}"/>
                <property name="url" value="${jdbc.url}"/>
                <property name="username" value="${jdbc.username}"/>
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <mapper resource=""/>
<!--        <package name=""/>-->
    </mappers>
</configuration>
```



~~~properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/ssm?serverTimezone=UTC
jdbc.username=root
jdbc.password=123456
~~~



## 2.2、创建mapper接口

>MyBatis中的mapper接口相当于以前的dao。但是区别在于，mapper仅仅是接口，我们不需要
>提供实现类。

~~~java
public interface UserMapper {
/**
* 添加用户信息
*/
int insertUser();
}
~~~



## 2.3、创建MyBatis的映射文件

**相关概念：ORM（Object Relationship Mapping）对象关系映射。**

- 对象：Java的实体类对象
- 关系：关系型数据库
- 映射：二者之间的对应关系



>1、映射文件的命名规则：
>
>表所对应的实体类的类名+Mapper.xml
>
>例如：表t_user，映射的实体类为User，所对应的映射文件为UserMapper.xml
>
>因此一个映射文件对应一个实体类，对应一张表的操作
>
>MyBatis映射文件用于编写SQL，访问以及操作表中的数据
>
>MyBatis映射文件存放的位置是src/main/resources/mappers目录下
>
>2、 MyBatis中可以面向接口操作数据，要保证两个一致：
>
>mapper接口的全类名和映射文件的命名空间（namespace）保持一致
>
>mapper接口中方法的方法名和映射文件中编写SQL的标签的id属性保持一致

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.aip.mybatis.user.mapper.UserMapper">

    <!--int insertUser();-->
    <insert id="insertUser">
        insert into t_user values(0,'aip','123456',21,'男','aip@163.com')
    </insert>

</mapper>
```



## 2.4、创建SqlSession工具类

```java
public static SqlSession getSqlSession(){
    SqlSession sqlSession = null;
    try {
        //获取核心配置文件的输入流
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        //获取SqlSession对象
        sqlSession = sqlSessionFactory.openSession(true);
    }catch (IOException e){
        e.printStackTrace();
    }
    return sqlSession;
}
```



# 3、MyBatis的配置讲解

> 官方文档中Mybatis的配置文件结构如下：

- configuration（配置）
  - properties（属性）：引入properties配置文件
  - settings（设置）
  - typeAliases（类型别名）：类型别名可为 Java 类型设置一个缩写名字。 它仅用于 XML 配置，意在降低冗余的全限定类名书写。
  - typeHandlers（类型处理器）
  - objectFactory（对象工厂）
  - plugins（插件）
  - environments（环境配置）
    - environment（环境变量）
      - transactionManager（事务管理器）：设置使用的是JDBC中原生的事务管理方式，还是例如Spring一样被管理。
      - dataSource（数据源）：配置数据库连接，一般会放在properties配置文件里面
  -   databaseIdProvider（数据库厂商标识）
  - mappers（映射器）：引入映射文件

> 举例讲解

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-config.dtd">
<!--配置数据库连接环境-->
<configuration>

    <!--引入properties文件
    引入后,在进行数据源配置时
    可以直接使用键值对的方式配置，例如  value="${url}"
    修改时也只需要修改properties文件即可
    -->
    <properties resource="jdbc.properties"/>

    <!--设置类型别名
    在当前的Mybatis范围内，都可以用别名代替访问全类名，别名不区分大小写
    用法：
    1、在此处设置别名后
    2、像映射文件中  resultType属性就可以用别名代替
        <select id="getAllUser" resultType="com.aip.mybatis.user.pojo.User">
            select *
            from t_user
        </select>
    3、如果不设置 alias的话，默认的别名就是全类名的最后一个单词
    -->
    <typeAliases>
        <typeAlias type="com.aip.mybatis.user.pojo.User" alias="aip"/>
    <!--在真正的项目中对应数据库的可能会有上百个实体类，这时候不可能为它们一一设置实体类
    我们可以采用包的方式，将包中的实体类统一设置默认别名
    例如我们将所有的实体类放在 com.aip.po 包下-->
        <package name="com.aip.po"/>
    </typeAliases>

    <!--environments 父属标签  environment：环境    environments：多个环境-->
    <environments default="development">
        <environment id="development">
            <!--事务管理器
            属性：type="JDBC/MANAGED"
            JDBC：表示当前环境中，执行SQL时，使用的是JDBC中原生的事务管理方式，事务的提交或回滚需要手动处理
            MANAGED：被管理，例如Spring-->
            <transactionManager type="JDBC"/>

            <!--数据源
            属性：type="POOLED/UNPOOLED/JNDI"
            POOLED：表示使用数据库连接池缓存数据库连接
            UNPOOLED：表示不使用数据库连接池
            JNDI：表示使用上下文中的数据源-->
            <dataSource type="POOLED">
                <!--数据库驱动类-->
                <property name="driver" value="${jdbc.driver}"/>
                <!--数据库连接地址-->
                <property name="url" value="${jdbc.url}"/>
                <!--数据库用户名-->
                <property name="username" value="${jdbc.username}"/>
                <!--数据库连接密码-->
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>

    <!--引入映射文件
    以包为单位引入映射文件
    映射文件和别名同样，可以以包的形式引入映射文件
    要求：
    1、mapper接口所在的包要和映射文件所在的包一致
    2、mapper接口要和映射文件的名字一致-->
    <mappers>
        <mapper resource="mappers/UserMapper.xml"/>
<!--        <package name="com.aip.mapper"/>-->
    </mappers>
</configuration>
```



# 4、MyBatis获取参数值的两种方式

MyBatis获取参数值的两种方式：`${}`和`#{}`

`${}`的本质就是字符串拼接，但是会造成SQL注入。

`#{}`的本质就是占位符赋值，可以避免SQL注入。

`${}`使用字符串拼接的方式拼接sql，若为字符串类型或日期类型的字段进行赋值时，==需要手动加单引号。==

`#{}`使用占位符赋值的方式拼接sql，此时为字符串类型或日期类型的字段进行赋值时，可以自动添加单引。

## 4.1、单个参数实例

```xml
<select id="getUserByUsername" resultType="User">
    <!--对应的SQL语句
	select * from t_user where username = ?-->
    select * from t_user where username = #{username}

    <!--对应的SQL语句
	select * from t_user where username = 'aip'-->
    <!--select * from t_user where username = '${username}'-->
</select>
```



## 4.2、多个参数实例

若mapper接口中的方法参数为多个时，此时MyBatis会自动将这些参数放在一个map集合中，

以arg0,arg1...为键，以参数为值；以param1,param2...为键，以参数为值；

因此只需要通过${}和#{}访问map集合的键就可以获取相对应的值，注意${}需要手动加单引号。

```Java
User checkLogin(String userName, String password);
```

```xml
<select id="getUserByNameAndPwd" resultType="com.aip.po.User">
    select * from t_user where username= '${arg0}' and password='${arg1}'
</select>
```

```java
@Test
public void testCheckLogin() {
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    User user = mapper.checkLogin("aip", "123456");
    System.out.println(user);
}
```



## 4.3、map集合类型的参数

若mapper接口中的方法需要的参数为多个时，此时可以手动创建map集合。

将这些数据放在map中只需要通过\${}和\#{}访问map集合的键就可以获取相对应的值，注意\${}需要手动加单引号。

```java
User checkLoginByMap(Map<String, Object> map);
```

~~~xml
<select id="checkLoginByMap" resultType="com.aip.po.User">
     select * from t_user where username=#{username} and password=#{password}
</select>
~~~

```java
@Test
public void testCheckLoginByMap() {
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    Map<String, Object> map = new HashMap<>();
    map.put("username", "aip");
    map.put("password", "123456");
    User user = mapper.checkLoginByMap(map);
    System.out.println(user);
}
```



## 4.4、实体类类型的参数

若mapper接口中的方法参数为实体类对象时，通过访问实体类对象中的属性名获取属性值，注意${}需要手动加单引号。

```Java
void insertUser(User user);
```

```xml
<insert id="insertUser">
    insert into t_user values (null,#{userName},#{password},#{age},#{gender},#{email})
</insert>
```

```Java
@Test
public void testInsertUser() {
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    mapper.insertUser(new User(null,"Aip","123456",21,"女","bind@163.com"));
}
```



## 4.5、@Param注解

> 官方文档中的说明
>
> 如果你的映射方法接受多个参数，就可以使用这个注解自定义每个参数的名字。否则在默认情况下，除 `RowBounds` 以外的参数会以 "param" 加参数位置被命名。例如 `#{param1}`, `#{param2}`。如果使用了 `@Param("person")`，参数就会被命名为 `#{person}`。

可以通过@Param注解标识mapper接口中的方法参数此时，会将这些参数放在map集合中，以@Param注解的value属性值为键，以参数为值；以param1,param2...为键，以参数为值；只需要通过${}和#{}访问map集合的键就可以获取相对应的值。

```java
User checkLoginByParam(@Param("name") String userName, @Param("pwd") String password);
```

```xml
<select id="checkLoginByParam" resultType="com.aip.po.User">
    select * from t_user where username = #{name} and password = #{pwd}
</select>
```



# 5、MyBatis的各种操作

## 5.1、查询一个实体类对象

```Java
User getUserById(@Param("id") Integer id);
```

```xml
<select id="getUserById" resultType="com.aip.po.User">
    select * from t_user where id = #{id}
</select>
```

```Java
@Test
public void testGetUserById(){
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
    User user = mapper.getUserById(1);
    System.out.println("user = " + user);
}
```



## 5.2、查询一个list集合

```Java
List<User>getAllUser();
```

```xml
<select id="getAllUser" resultType="com.aip.po.User">
    select * from t_user
</select>
```

```Java
@Test
public void testGetAllUser(){
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
    List<User> allUser = mapper.getAllUser();
    allUser.forEach(System.out::println);
}
```



## 5.3、查询单个数据

```java
Integer getCount();
```

```xml
<select id="getCount" resultType="java.lang.Integer">
    select count(*) from t_user
</select>
```



## 5.4、查询一条数据为Map集合

```Java
Map<String,Object> getUserByIdToMap(@Param("id")int id);
```

```xml
<select id="getUserByIdToMap" resultType="java.util.Map">
    select * from t_user where id= #{id}
</select>
```

> 注意，这种方式只能查询一条数据。一条数据对应一个map；若有多条数据，就会产生多个map集合。
>
> 当查询多条数据时会报以下错误
>
> org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 4
>
> 可见该方法只能查询一条数据   `selectOne()`
>
> 思考：用Map集合如何查询多条数据呢？



## 5.5、查询多条数据为Map集合

> 方法一：`@MapKey()`注解
>
> 该方法也是阿里巴巴规范里的，非常的简单粗暴
>
> 对于`@MapKey()`注解的个人理解：会将查询到的一整个Map集合变为一个值，以注解中的内容为键，形成一个键值对。这样就可以存放多条数据了。大概意思就是套娃。
>
> 抽象点就是：`{Key=(@Mapkey())，V=({之前查询的一整条数据的所有键值对})}`

```java
@MapKey("id")
Map<String,Object> getAllUserToMap();
```

```xml
<select id="getAllUserToMap" resultType="java.util.Map">
    select * from t_user
</select>
```



> 方法二：手动将查询结果装到List集合里面去。非常的不方便

~~~Java
List<Map<String, Object>> getAllUserToMap();
~~~



# 6、MyBatis的特殊SQL执行

> 以下SQL语句，不能使用`#{}`来获取参数值
>
> 除模糊查询外，其他SQL操作都必须使用`${}`进行操作

## 6.1、模糊查询

```xml
<select id="testMoHu" resultType="com.aip.po.User">
    <!--Preparing: select * from t_user where username like '%?%'-->
    select * from t_user where username like '%#{mohu}%'
</select>
```

> Preparing: select * from t_user where username like '%?%' (BaseJdbcLogger.java:137) 
>
> 模糊查询如果用`#{}`的话，转化的SQL语句则会变成这样。此时`#{}`会变成一个占位符`?`，形成`'%?%'`这样一个字符串。使得占位符失效。
>
> 并抛出异常：
>
> Caused by: java.sql.SQLException: Parameter index out of range (1 > number of parameters, which is 0).

> 解决方法

```xml
<select id="testMoHu" resultType="com.aip.po.User">
    <!--Preparing: select * from t_user where username like '%?%'-->
    <!--select * from t_user where username like '%#{mohu}%'-->
    方法一：使用${}。但是${}会造成SQL注入
    <!--select * from t_user where username like '%${mohu}%'-->
    方法二：使用concat（）字符串拼接函数，比较麻烦，不常用
    <!--select * from t_user where username like concat('%',#{mohu},'%')-->
    方法三：常用方法，"%"#{}"%"。面板会报错，但是可运行
    select * from t_user where username like "%"#{mohu}"%"
</select>
```



## 6.2、批量删除

```xml
<delete id="delUsers">
    delete from t_user where id in(#{ids})
</delete>
```

> Preparing: delete from t_user where id in(?) (BaseJdbcLogger.java:137) 
>
> 因为`#{}`是占位符赋值，转换为SQL是会自动添加一对`单引号''`。
>
> 当前执行的SQL相当于：
>
> `delete from t_user where id in('5,8') `
>
> 正确的SQL语句应该是：
>
> `delete from t_user where id in(5,8)`
>
> 应该使用`${}`



## 6.3、动态表名设置

> 应用场景，例如：用来查询该用户是普通用户还是会员用户

```java
List<User> getUserList(@Param("tableName")String tableName);
```

```xml
<select id="getUserList" resultType="com.aip.po.User">
    select * from #{tableName}
</select>
```

> 如果用`#{}`的话，转换的SQL是这样的
>
> `select * from 't_user' `
>
> `#{}`中的字符串，转换后会自动加上`单引号''`
>
> 表名是不能加单引号的。
>
> 应该使用`${}`



## 6.4、添加功能获取自增主键

> 为主键添加自增后，每添加一条数据，主键会自动递增
>
> 在有些时候，开发需要获取自增的主键，进而实现其他功能
>
> 在开发业务的时候会用的很多

`useGeneratedKeys`：是否使用自增主键

`keyProperty`：将添加的数据的自增主键为实体类类型的参数的属性赋值

```xml
<insert id="insertUser" useGeneratedKeys="true" keyProperty="id">
    insert into t_user values(0,#{userName},#{password},#{age},#{gender},#{email})
</insert>
```



# 7、MyBatis映射之resultMap

## 7.1、引入

> 来自官方文档中的介绍
>
> `resultMap` 元素是 MyBatis 中最重要最强大的元素。它可以让你从 90% 的 JDBC `ResultSets` 数据提取代码中解放出来，并在一些情形下允许你进行一些 JDBC 不支持的操作。实际上，在为一些比如连接的复杂语句编写映射代码的时候，一份 `resultMap` 能够代替实现同等功能的数千行代码。ResultMap 的设计思想是，对简单的语句做到零配置，对于复杂一点的语句，只需要描述语句之间的关系就行了。

> 说通俗点就是数据库的字段命名规范和JavaBean的属性命名规范不一致，从而导致这两个代表同一对象的名称不一致。这时候如果按照上文的那种方式去获取参数值，是获取不到的。
>
> 解决方法：起别名即可。

这种起别名的方式就是，显式指定的 `resultMap`

```xml
<select id="getEmpByEmpId" resultType="com.aip.po.Employee">
    select employee_id   as empId,
           employee_name as empName,
           age,
           gender
    from t_employees
    where employee_id = #{empId}
</select>
```

这种就是，没有显式指定的 `resultMap`

这种情况就是，数据库表中的字段，和JavaBean对象中的字段名称对的上的。

```xml
<select id="selectUsers" resultType="map">
  select id, username, hashedPassword
  from some_table
  where id = #{id}
</select>
```



## 7.2、配置resultMap

> 由于在实际开发中，需要写的SQL语句肯定不止一两条。那么如果多条SQL语句呢？再按照上述方法，在每条SQL语句中都添加别名太过麻烦。
>
> 这时候可以通过MyBatis的核心配置文件，来配置`resultMap`。提高开发效率

`mapUnderscoreToCamelCase`是否开启驼峰命名自动映射，即从经典数据库列名 A_COLUMN 映射到经典 Java 属性名 aColumn。`默认值为：false`

```xml
<settings>
    <setting name="mapUnderscoreToCamelCase" value="true"/>
</settings>
```



## 7.3、自定义映射

> 官方文档中给的`resultMap`概念视图
>
> - `constructor`：用于在实例化类时，注入结果到构造方法中
>   - `idArg`：ID 参数；标记出作为 ID 的结果可以帮助提高整体性能
>   - `arg`：将被注入到构造方法的一个普通结果
> - `id`：一个 ID 结果；标记出作为 ID 的结果可以帮助提高整体性能
> - `result`：注入到字段或 JavaBean 属性的普通结果
> - `association`：一个复杂类型的关联；许多结果将包装成这种类型
>   - 嵌套结果映射 – 关联可以是 `resultMap` 元素，或是对其它结果映射的引用
> - `collection`：一个复杂类型的集合
>   - 嵌套结果映射 – 集合可以是 `resultMap` 元素，或是对其它结果映射的引用
> - `discriminator`：– 使用结果值来决定使用哪个`resultMap`
>   - `case`： 基于某些值的结果映射
>     - 嵌套结果映射 ：`case` 也是一个结果映射，因此具有相同的结构和元素；或者引用其它的结果映射

在mapper映射文件里面可以设置`resultMap`实现自定义映射，自定义映射的优先级高于在配置文件中设置的`mapUnderscoreToCamelCase(驼峰命名自动映射)`

每个`resultMap`对应一张表，`id`最好以对应的表命名，表对应的实体类的类型。

![1685093728144](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/1685093728144.png)

`resultMap`的子属性介绍：

- `id`：用来处理主键和属性的映射关系
- `result`：对应的是普通字段和属性的对应关系
- `collection`：处理一对多的字段映射关系（集合）
- `association`：处理多对一、一对一

![1685093791157](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/1685093791157.png)

`id和result`中的属性介绍：

- `property`：JavaBean对象中的属性
- `column`：数据库中的字段
- `javaType`：属性的类型
- `jdbcType`：数据库中字段的类型

```xml
<resultMap id="EmployeesMap" type="Employee">
    <id property="empId" column="employee_id"/>
    <result property="empName" column="employee_name"/>
    <result property="age" column="age"/>
    <result property="gender" column="gender"/>
</resultMap>

<select id="getEmpByEmpId" resultMap="EmployeesMap">
        select employee_id,
               employee_name,
               age,
               gender
        from t_employees
        where employee_id = #{empId}
    </select>
```



## 7.4、MyBatis的联表查询

### 7.4.1、级联

> 什么是级联

级联是指多个对象之间的映射关系，建立数据之间的级联关系提高管理效率。

级联是信息聚合的方式！！

- **一对一**
- **一对多**
- **多对多**



首先要确定两个实体类对象之间的关系，以员工和所属部门为例子。

当我们查询员工时，员工为主体。那么此时一个员工对应一个部门，是一对一的关系，所以在`Employee`实体类中创建一个`Department`属性。

```Java
public class Employee {
    private Integer empId;
    private String empName;
    private Integer age;
    private String gender;
    private Department department;
```

这时即可在`Employee`类的`resultMap`中，通过`department`属性，选择需要的`Department`属性去映射数据库中的字段。

```xml
<resultMap id="empResult" type="Employee">
    <id property="empId" column="employee_id"/>
    <result property="empName" column="employee_name"/>
    <result property="age" column="age"/>
    <result property="gender" column="gender"/>
    <result property="department.deptID" column="dept_id"/>
    <result property="department.deptName" column="dept_name"/>
</resultMap>
```



### 7.4.2、关联 （association）

专门处理一对一、多对一的映射关系。

使用方法和方式一基本一致，相比方式一，`association`更易读，更直观。

```xml
<resultMap id="empResult" type="employee">
    <id property="empId" column="employee_id"/>
    <result property="empName" column="employee_name"/>
    <result property="age" column="age"/>
    <result property="gender" column="gender"/>
    <association property="department" javaType="Department">
        <id property="deptID" column="dept_id"/>
        <result property="deptName" column="dept_name"/>
    </association>
</resultMap>
```



### 7.4.3、分步查询

> 分步查询的优点：可以实现延迟加载。（下文介绍）

在`association`标签中引入分步查询，和对应的数据库字段。

- `property`：属性
- `select`：分步查询SQL的标识
- `column`：字段名，作为分步查询SQL的条件

```Java
List<Employee> getEmpAndDeptByStep(@Param("empId")Integer empId);
```

```xml
<resultMap id="getEmpAndDeptByStepResult" type="employee">
    <id property="empId" column="employee_id"/>
    <result property="empName" column="employee_name"/>
    <result property="age" column="age"/>
    <result property="gender" column="gender"/>
    <association property="department" select="com.aip.mapper.DeptMapper.getEmpAndDeptByStepTwo" column="dept_Id"></association>
</resultMap>

<select id="getEmpAndDeptByStep" resultMap="getEmpAndDeptByStepResult">
    select * from t_employees where employee_id = #{empId}
</select>
```

`DeptMapper`

```java
Department getEmpAndDeptByStepTwo(@Param("deptId") Integer deptId);
```

```xml
<select id="getEmpAndDeptByStepTwo" resultType="com.aip.po.Department">
    select * from t_department where dept_id = #{deptId}
</select>
```

### 7.4.4、**级联的缺陷：**

**性能缺陷**：级联操作会降低性能，增加程序的执行时间；

**复杂度缺陷**：关联较多造成复杂度的增加，不利于他人理解和维护；

**在使用上建议：**根据实际情况增加级联关系；多层关联，超过三层关联时建议尽量少用级联；



### 7.4.5、集合（collection）

> 处理多对一的关系

需要注意的是，`关联(association)`中用的是`javaType`，而`集合(collection)`中用的是`ofType`。

```Java
Department getDeptAndEmpByDeptId(@Param("deptId") Integer deptId);
```

```xml
<resultMap id="deptAndEmpResult" type="department">
    <id property="deptID" column="dept_id"/>
    <result property="deptName" column="dept_name"/>
    <collection property="employees" ofType="employee">
        <id property="empId" column="employee_id"/>
        <result property="empName" column="employee_name"/>
        <result property="age" column="age"/>
        <result property="gender" column="gender"/>
    </collection>
</resultMap>
<select id="getDeptAndEmpByDeptId" resultMap="deptAndEmpResult">
    select *
    from t_department td
             left join t_employees te on td.dept_id = te.dept_id
    where td.dept_id = #{deptId }
</select>
```



> 分步查询

`Department`

```Java
Department getEmpInDeptByStep(@Param("deptId") Integer deptId);
```

```xml
<resultMap id="getEmpInDeptByStepResult" type="department">
    <id property="deptID" column="dept_id"/>
    <result property="deptName" column="dept_name"/>
    <collection property="empList" select="com.aip.mapper.EmployeeMapper.getEmpInDeptByStep" column="dept_id">
    </collection>
</resultMap>
<select id="getEmpInDeptByStep" resultMap="getEmpInDeptByStepResult">
    select *
    from t_department
    where dept_id = #{deptId}
</select>
```

`Employee`

```java
List<Employee> getEmpInDeptByStep(@Param("deptId")Integer deptId);
```

```xml
<select id="getEmpInDeptByStep" resultType="com.aip.po.Employee">
    select *
    from t_employees
    where dept_id = #{deptId}
</select>
```



## 7.5、延迟加载

实现延迟加载必须在核心配置文件中设置全局配置信息：

`lazyLoadingEnabled`：延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。

`aggressiveLazyLoading`：当开启时，任何方法的调用都会加载该对象的所有属性。否则，每个属性会按需加载。

此时就可以实现按需加载，获取的数据是什么，就只会执行相应的sql。此时可通过`association`和`collection`中的`fetchType`属性设置当前的分步查询是否使用延迟加载， `fetchType="lazy(延迟加载)|eager(立即加载)"`。

人话就是：只查询你当前需要的，不需要的查询不会执行，以达到节约资源的目的。



# 8、动态SQL

> Mybatis框架的动态SQL技术是一种根据特定条件动态拼装SQL语句的功能，它存在的意义是为了解决拼接SQL语句字符串时的痛点问题。

- if
- choose (when, otherwise)
- trim (where, set)
- foreach



## 8.1、if

> if标签可通过test属性的表达式进行判断，若表达式的结果为true，则标签中的内容会执行；反之标签中的内容不会执行

```xml
<resultMap id="empResult" type="emp">
    <id property="empId" column="employee_id"/>
    <result property="empName" column="employee_name"/>
</resultMap>
<select id="getEmpByCondition" resultMap="empResult">
    select * from t_employees where
    <if test="empName != null and empName!=''">
        employee_name=#{empName}
    </if>
    <if test="age != '' and age != null">
        and age=#{age}
    </if>
    <if test="gender != '' and gender != null">
        and gender=#{gender}
    </if>
</select>
```

**==注意：上述写法实际上是存在大问题的。==**

如果第一个`if`标签为假时，此时的SQL语句会变成`select * from ... where and...`这样就会产生SQL语法错误。

> 解决方法

在where后面加上一个必要条件，例如官方文档中的。博客的状态为`ACTIVE`的。

```xml
<select id="findActiveBlogWithTitleLike"
     resultType="Blog">
  SELECT * FROM BLOG
  WHERE state = ‘ACTIVE’
  <if test="title != null">
    AND title like #{title}
  </if>
</select>
```

或者在where后面加上一个`1=1`这样的恒成立条件。

```xml
<select id="getEmpByCondition" resultMap="empResult">
    select * from t_employees where 1=1
    <if test="empName != null and empName!=''">
        and employee_name=#{empName}
    </if>
    <if test="age != '' and age != null">
        and age=#{age}
    </if>
    <if test="gender != '' and gender != null">
        and gender=#{gender}
    </if>
</select>
```

==**核心思想就是，if标签里面的语句前，需要有个and拼接**==

但是上述方法，如果是所有的`if标签`都不成立，SQL语句又会多一个where。像这样`select * from...where`。这种问题怎么解决呢？



## 8.2、where（trim、set）

还有一种方法就是，用`where标签`，例如

```xml
<select id="getEmpByCondition" resultMap="empResult">
    select * from t_employees
    <where>
        <if test="empName != null and empName!=''">
            employee_name=#{empName}
        </if>
        <if test="age != '' and age != null">
            and age=#{age}
        </if>
        <if test="gender != '' and gender != null">
            and gender=#{gender}
        </if>
    </where>
</select>
```

`where标签`会删掉额外的*and 和 or*。若`if标签`都不成立那就查询整张表。



> 如果 *where* 元素与你期望的不太一样，你也可以通过自定义 trim 元素来定制 *where* 元素的功能。

*trim*元素有以下属性：

- *prefix*：前缀
- *prefixOverrides*：前缀去除
- *suffix*：后缀
- *suffixOverrides*：后缀去除

> 和 *where* 元素等价的自定义 trim 元素为：

```xml
<trim prefix="WHERE" prefixOverrides="AND |OR ">
  ...
</trim>
```

值得注意的是 *prefixOverrides* 属性会忽略通过管道符分隔的文本序列（注意此例中的空格是必要的）。上述例子会移除所有 *prefixOverrides* 属性中指定的内容，并且插入 *prefix* 属性中指定的内容。



> *set* 常用于更新的SQL。*set* 元素会动态地在行首插入 SET 关键字，并会删掉额外的逗号。

```xml
<update id="updateAuthorIfNecessary">
  update Author
    <set>
      <if test="username != null">username=#{username},</if>
      <if test="password != null">password=#{password},</if>
      <if test="email != null">email=#{email},</if>
      <if test="bio != null">bio=#{bio}</if>
    </set>
  where id=#{id}
</update>
```



> 同样的，如果与你的期望不符，你也可以通过自定义 trim 元素来定制 *set* 元素的功能。
>
> 和 *set* 元素等价的自定义 trim 元素为：

```xml
<trim prefix="SET" suffixOverrides=",">
  ...
</trim>
```



## 8.3、choose (when, otherwise)

> when、 otherwise分别对应   *if / else if*、else*

既然*choose*对应*逻辑 if*，那为什么不直接用 *if标签* 呢？

两者的区别：

- *if标签* ：每一个标签，都会进行判断。条件符合就执行，不符合就跳过。
- *choose标签* ：只执行第一个符合条件的SQL，且之后的条件不再执行。（相当于Java中的*if、else if、else*）。

```xml
<select id="getEmpByChoose" resultMap="empResult">
    select * from t_employees
    <where>
        <choose>
            <when test="empName != null and empName != ''">
                employee_name = #{empName}
            </when>
            <when test="age != null and age != ''">
                age = #{age}
            </when>
            <when test="gender != null and gender != ''">
                gender = #{gender}
            </when>
        </choose>
    </where>
</select>
```



## 8.4、foreach

```Java
如果当前参数是一个集合或者是数组时，Mybatis会把数据放入map中，以集合或数组为键，以当前的参数为值
void insertEmp(@Param("empList") List<Emp> empList);
```

```xml
<insert id="insertEmp">
    insert into t_employees values
    <foreach collection="empList" item="emp" separator=",">
        (null,#{emp.empName},#{emp.age},#{emp.gender},1)
    </foreach>
</insert>
```

> - *collection*：集合，一对多关系
> - *item*：查询的值的类型
> - *separator*：分隔符，如下：值与值之间的*逗号*
>   - insert into t_employees values (null,?,?,?,1) , (null,?,?,?,1) , (null,?,?,?,1) , (null,?,?,?,1)
>   - 执行完之后长这样。
> - *open*：当前循环，以指定内容开头。
> - *close*：以指定内容结束。

动态 SQL 的另一个常见使用场景是对集合进行遍历（尤其是在构建 IN 条件语句的时候）。比如：

```xml
<delete id="deleteByEmpId">
    delete from t_employees where employee_id in
    <foreach collection="empId" item="empId" separator="," open="(" close=")">
        #{empId}
    </foreach>
</delete>
```

*foreach* 元素的功能非常强大，它允许你指定一个集合，声明可以在元素体内使用的集合项（item）和索引（index）变量。它也允许你指定开头与结尾的字符串以及集合项迭代之间的分隔符。这个元素也不会错误地添加多余的分隔符。

**`提示`** 你可以将任何可迭代对象（如 List、Set 等）、Map 对象或者数组对象作为集合参数传递给 *foreach*。当使用可迭代对象或者数组时，index 是当前迭代的序号，item 的值是本次迭代获取到的元素。当使用 Map 对象（或者 Map.Entry 对象的集合）时，index 是键，item 是值。



## 8.5、sql标签（补充）

正常来说，在具体业务开发的查询操作中，是不允许使用`select * from`的。因为这样做会查询出不需要的字段，增加系统的负担。阿里规范中也有提到。

当然，你也可以在每一条SQL语句中，输入你需要查询的字段。

也可以使用*sql标签* ，来插入需要的字段。

```xml
<sql id="empColumns">
    employee_id,employee_name,age,gender,dept_id
</sql>

<select id="getEmpByCondition" resultMap="empResult">
        select <include refid="empColumns"></include> from t_employees where 1=1
        <if test="empName != null and empName!=''">
            and employee_name=#{empName}
        </if>
        <if test="age != '' and age != null">
            and age=#{age}
        </if>
        <if test="gender != '' and gender != null">
            and gender=#{gender}
        </if>
    </select>
```

> 执行结果
>
> select employee_id,employee_name,age,gender,dept_id from t_employees where 1=1 and employee_name=? and age=? and gender=?



# 9、Mybatis的缓存

MyBatis 内置了一个强大的事务性查询缓存机制，它可以非常方便地配置和定制。默认情况下，只启用了本地的会话缓存，它仅仅对一个会话中的数据进行缓存。



## 9.1、Mybatis的一级缓存

一级缓存是SqlSession级别的，通过同一个SqlSession查询的数据会被缓存，下次查询相同的数据，就会从缓存中直接获取，不会从数据库重新访问。

> 举个例子

```Java
@Test
    public void testGetEmpById(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp emp = mapper.getEmpById(7);
        System.out.println("emp = " + emp);
        Emp emp1 = mapper.getEmpById(7);
        System.out.println("emp1 = " + emp1);

        System.out.println();

        SqlSession sqlSession1 = SqlSessionUtil.getSqlSession();
        CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
        Emp emp2 = mapper1.getEmpById(7);
        System.out.println("emp2 = " + emp2);
    }
```

从这段代码的执行结果就可以看出，当查询添加相同时，Mybatis会从缓存中读取，不会查询访问数据库。

但如果是不同的`SqlSession`，查找同一条数据时，第二个`SqlSession`还是会重新访问数据库。因为一个`SqlSession`对应一个缓存。

![1686214672048](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/1686214672048.png)



> 一级缓存失效的四种情况：

- 不同的SqlSession对应不同的一级缓存

- 同一个SqlSession但是查询条件不同

- 同一个SqlSession两次查询期间执行了任何一次增删改操作

- 同一个SqlSession两次查询期间手动清空了缓存

  - ~~~java
    sqlSession.clearCache();
    ~~~



## 9.2、Mybatis的二级缓存

二级缓存是SqlSessionFactory级别，通过同一个SqlSessionFactory创建的SqlSession查询的结果会被缓存；此后若再次执行相同的查询语句，结果就会从缓存中获取。

> 如何开启二级缓存

- 二级缓存必须在SqlSession关闭或提交之后有效
- 查询的数据所转换的实体类类型必须实现序列化的接口

```Java
@Test
public void testCache() throws IOException {
    InputStream is = Resources.getResourceAsStream("mb-config.xml");
    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
    SqlSession sqlSession = sessionFactory.openSession(true);
    CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
    Emp emp = mapper.getEmpById(8);
    System.out.println("emp = " + emp);
    sqlSession.close();

    System.out.println();

    SqlSession sqlSession1 = sessionFactory.openSession(true);
    CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
    Emp emp1 = mapper1.getEmpById(8);
    System.out.println("emp1 = " + emp1);
    sqlSession1.close();
}
```

![1686274030518](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/1686274030518.png)

`注意`二级缓存是事务性的。这意味着，当 SqlSession 完成并提交时，或是完成并回滚，但没有执行 flushCache=true 的 insert/delete/update 语句时，缓存会获得更新。

> 要启用全局的二级缓存，只需要在你的 SQL 映射文件中添加一行：

```xml
<cache/>
```

这个简单语句的效果如下:

- 映射语句文件中的所有 select 语句的结果将会被缓存。
- 映射语句文件中的所有 insert、update 和 delete 语句会刷新缓存。
- 缓存会使用最近最少使用算法（LRU, Least Recently Used）算法来清除不需要的缓存。
- 缓存不会定时进行刷新（也就是说，没有刷新间隔）。
- 缓存会保存列表或对象（无论查询方法返回哪种）的 1024 个引用。
- 缓存会被视为读/写缓存，这意味着获取到的对象并不是共享的，可以安全地被调用者修改，而不干扰其他调用者或线程所做的潜在修改。



## 9.3、二级缓存的配置

`提示` 缓存只作用于 cache 标签所在的映射文件中的语句。如果你混合使用 Java API 和 XML 映射文件，在共用接口中的语句将不会被默认缓存。你需要使用 @CacheNamespaceRef 注解指定缓存作用域。

这些属性可以通过 cache 元素的属性来修改。比如：

~~~xml
<cache
  eviction="FIFO"
  flushInterval="60000"
  size="512"
  readOnly="true"/>
~~~

缓存的属性：

- *eviction*：缓存回收策略，默认是*LRU*
  - *LRU（Least Recently Used）*：最近最少使用的。缓存会清除一段时间内，最长时间没有使用的对象。
  - *FIFO（First in First out）*：先进先出，队列的原则。
  - *SOFT（软引用）*：移除基于垃圾回收器状态和软引用规则的对象。
  - *WFAK（弱引用）*：更积极的移除基于垃圾回收器状态和弱引用规则的对象。
- *flushInterval*：刷新间隔，单位是*毫秒* 。
  - 默认情况是不设置，也就是没有刷新间隔，缓存仅仅调用语句是刷新。
- *size*：引用数目。代表缓存最多可以存储的对象个数，太大容易导致内存溢出。
- *readOnly*：是否只读。（true/false）
  - *true*：只读缓存。会给所有调用者返回缓存对象的相同实例。因此这些对象不能被修改。这提供了很重
    要的性能优势。
  - *false*：读写缓存；会返回缓存对象的拷贝（通过序列化）。这会慢一些，但是安全，因此默认是false。



## 9.4、Mybatis的缓存顺序

先查询二级缓存，因为二级缓存中可能会有其他程序已经查出来的数据，可以拿来直接使用。

如果二级缓存没有命中，再查询一级缓存。

如果一级缓存也没有命中，则查询数据库。

SqlSession关闭之后，一级缓存中的数据会写入二级缓存。



## 9.5、第三方缓存

第三方缓存是针对二级缓存的

### 9.5.1、第三方缓存的配置

```xml
<!-- Mybatis EHCache整合包 -->
<dependency>
    <groupId>org.mybatis.caches</groupId>
    <artifactId>mybatis-ehcache</artifactId>
    <version>1.2.3</version>
</dependency>

<!-- slf4j日志门面的一个具体实现 -->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.4.7</version>
</dependency>
```

| jar包名称       | 作用                            |
| :-------------- | :------------------------------ |
| mybatis-ehcache | Mybatis和EHCache的整合包        |
| ehcache         | EHCache核心包                   |
| slf4j-api       | SLF4J日志门面包                 |
| logback-classic | 支持SLF4J门面接口的一个具体实现 |

 

> 创建EHCache的配置文件ehcache.xml（强制名称）

```xml
<?xml version="1.0" encoding="utf-8" ?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="../config/ehcache.xsd">
    <!-- 磁盘保存路径 -->
    <diskStore path="D:\JetBrains\ehcache"/>
    <defaultCache
            maxElementsInMemory="1000"
            maxElementsOnDisk="10000000"
            eternal="false"
            overflowToDisk="true"
            timeToIdleSeconds="120"
            timeToLiveSeconds="120"
            diskExpiryThreadIntervalSeconds="120"
            memoryStoreEvictionPolicy="LRU">
    </defaultCache>
</ehcache>
```



### 9.5.2、配置文件的说明

| 属性名                            | 是否必须 | 作用                                                         |
| --------------------------------- | -------- | :----------------------------------------------------------- |
| *maxElementsInMemory*             | 是       | 在内存中缓存的 *element* 的最大数目                          |
| *maxElementsOnDisk*               | 是       | 在磁盘上缓存的 *element* 的最大数目，若是0表示无穷大         |
| *eternal*                         | 是       | 设定缓存的 *elements* 是否永远不过期。 如果为*`true`*，则缓存的数据始终有效， 如果为*`false`*那么还要根据*timeToIdleSeconds*、*timeToLiveSeconds*判断 |
| *overflowToDisk*                  | 是       | 设定当内存缓存溢出的时候是否将过期的 *element* 缓存到磁盘上  |
| *timeToIdleSeconds*               | 否       | 当缓存在*EhCache*中的数据前后两次访问的时间超过*timeToIdleSeconds*的属性取值时， 这些数据便会删除，默认值是0,也就是可闲置时间无穷大 |
| *timeToLiveSeconds*               | 否       | 缓存*element*的有效生命期，默认是0，也就是*element*存活时间无穷大 |
| *diskSpoolBufferSizeMB*           | 否       | *DiskStore*(磁盘缓存)的缓存区大小。默认是30MB。每个Cache都应该有自己的一个缓冲区 |
| *diskPersistent*                  | 否       | 在VM重启的时候是否启用磁盘保存EhCache中的数据，默认是false。 |
| *diskExpiryThreadIntervalSeconds* | 否       | 磁盘缓存的清理线程运行间隔，默认是120秒。每个120s， 相应的线程会进行一次EhCache中数据的清理工作 |
| *memoryStoreEvictionPolicy*       | 否       | 当内存缓存达到最大，有新的*element*加入的时候， 移除缓存中element的策略。 默认LRU （最近最少使用），可选的有LFU （最不常使用）和FIFO （先进先出） |



### 9.5.3、设置二级缓存的类型

```xml
<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
```



### 9.5.4、加入logback日志

> 存在SLF4J时，作为简易日志的log4j将失效，此时我们需要借助SLF4J的具体实现logback来打印日志。 创建logback的配置文件logback.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration debug="true">
    <!-- 指定日志输出的位置 -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <filter class="com.wang.cloud.store.common.LogFilter"/>
        <withJansi>false</withJansi>
        <encoder>
            <!-- 日志输出的格式 -->
            <!-- 按照顺序分别是： 时间、日志级别、线程名称、打印日志的类、日志主体内容、换行-->
            <pattern>%white(%d{yyyy-MM-dd HH:mm:ss}) %highlight(%lsn) %green([%thread]) %highlight(%-5level) %boldMagenta(%logger{10}) - %cyan(%msg%n)</pattern>
            <charset>utf8</charset>
        </encoder>
    </appender>
    <!-- 设置全局日志级别。日志级别按顺序分别是： DEBUG、INFO、WARN、ERROR -->
    <!-- 指定任何一个日志级别都只打印当前级别和后面级别的日志。 -->
    <root level="DEBUG">
        <!-- 指定打印日志的appender，这里通过“STDOUT”引用了前面配置的appender -->
        <appender-ref ref="STDOUT"/>
    </root>
    <!-- 根据特殊需求指定局部日志级别 -->
    <logger name="com.aip.mapper" level="DEBUG"/>
</configuration>
```



# 10、MyBatis逆向工程

逆向工程是指通过已有的数据库表结构生成对应的 Java 实体类、Mapper 接口以及 XML 映射文件的过程。MyBatis 提供了一个逆向工程工具，可以根据数据库表结构自动生成相关的代码，大大提高了开发效率。



## 10.1、为什么要学习Mybatis逆向工程

学习*MyBatis* 逆向工程可以提高开发效率、减少错误、提高代码质量和可维护性，同时简化数据库变更的过程。它可以作为开发过程中的一个强大工具，帮助开发者更高效地进行数据库访问层的开发。

如果你在项目中需要频繁进行数据库结构的变更或者希望提高开发效率和代码的一致性，那么学习逆向工程是值得的。



## 10.2、Mybatis逆向工程有什么好处

1. 提高开发效率：逆向工程工具能够自动生成基础的代码，避免手动编写重复且繁琐的代码，节省了开发时间。
2. 保持数据一致性：逆向工程工具能够根据数据库表结构生成实体类和映射文件，确保代码和数据库之间的一致性，减少了手动编写代码时的错误。
3. 易于维护：通过逆向工程生成的代码具有一致的风格和结构，易于维护和阅读。
4. 减少人为错误：手动编写代码时容易出现拼写错误、字段类型不匹配等问题。逆向工程生成的代码可以保证与数据库结构的一致性，减少了手动编写代码时的错误。
5. 提高代码质量：逆向工程生成的代码通常遵循一致的命名规范和代码结构，使得代码更易于理解和维护。同时，由于代码是由数据库表结构自动生成的，可以确保数据库和代码之间的一致性。
6. 简化数据库变更：当数据库结构发生变化时，逆向工程可以自动更新生成的代码，而不需要手动修改现有代码。这样可以减少数据库变更对代码的影响，提高代码的可维护性。
7. 提供基础代码框架：逆向工程生成的代码提供了基础的增删改查操作，可以作为开发的起点，减少从头编写数据库访问层代码的工作量。
8. 学习和理解数据库设计：通过逆向工程生成的代码，可以深入了解数据库表结构以及表之间的关系，加深对数据库设计的理解。



## 10.3、逆向工程和正常使用有什么区别

- 正向工程：先创建Java实体类，由框架负责根据实体类生成数据库表。 Hibernate是支持正向工程的。
- 逆向工程：先创建数据库表，由框架负责根据数据库表，反向生成如下资源：
  - Java实体类
  - Mapper接口
  - Mapper映射文件



## 10.4、逆向工程的缺点

生成的文件太过冗余，不必要的代码过多。尤其是sql映射文件，里面的配置内容太多，对于dao层，提供的方法比较有限，需要自行扩展。



## 10.5、创建Maven工程

```xml
<dependencies>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.23</version>
    </dependency>

    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.7</version>
    </dependency>

    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>1.2.17</version>
    </dependency>
</dependencies>

<!-- 控制Maven在构建过程中相关配置 -->
<build>
    <!-- 构建过程中用到的插件 -->
    <plugins>
        <!-- 具体插件，逆向工程的操作是以构建过程中插件形式出现的 -->
        <plugin>
            <groupId>org.mybatis.generator</groupId>
            <artifactId>mybatis-generator-maven-plugin</artifactId>
            <version>1.3.1</version>
            <!-- 插件的依赖 -->
            <dependencies>
                <!-- 逆向工程的核心依赖 -->
                <dependency>
                    <groupId>org.mybatis.generator</groupId>
                    <artifactId>mybatis-generator-core</artifactId>
                    <version>1.3.2</version>
                </dependency>
                <!-- MySQL驱动 -->
                <dependency>
                    <groupId>mysql</groupId>
                    <artifactId>mysql-connector-java</artifactId>
                    <version>8.0.23</version>
                </dependency>
            </dependencies>
        </plugin>
    </plugins>
</build>
```



## 10.6、创建generator配置文件

> 文件名必须是：generatorConfig.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <!--
    targetRuntime: 执行生成的逆向工程的版本
    MyBatis3Simple: 生成基本的CRUD（清新简洁版）
    MyBatis3: 生成带条件的CRUD（奢华尊享版）
    -->
    <context id="DB2Tables" targetRuntime="MyBatis3">
        <!-- 数据库的连接信息 -->
        <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/ssm?serverTimezone=UTC"
                        userId="root"
                        password="123456">
        </jdbcConnection>
        <!-- javaBean的生成策略-->
        <javaModelGenerator targetPackage="com.aip.pojo"
                            targetProject=".\src\main\java">
            <property name="enableSubPackages" value="true" />
            <property name="trimStrings" value="true" />
        </javaModelGenerator>
        <!-- SQL映射文件的生成策略 -->
        <sqlMapGenerator targetPackage="com.aip.mapper"
                         targetProject=".\src\main\resources">
            <property name="enableSubPackages" value="true" />
        </sqlMapGenerator>
        <!-- Mapper接口的生成策略 -->
        <javaClientGenerator type="XMLMAPPER"
                             targetPackage="com.aip.mapper" targetProject=".\src\main\java">
            <property name="enableSubPackages" value="true" />
        </javaClientGenerator>
        <!-- 逆向分析的表 -->
        <!-- tableName设置为*号，可以对应所有表，此时不写domainObjectName -->
        <!-- domainObjectName属性指定生成出来的实体类的类名 -->
        <table tableName="t_employees" domainObjectName="Emp"/>
        <table tableName="t_department" domainObjectName="Dept"/>
    </context>
</generatorConfiguration>
```



## 10.7、执行MBG插件的generate目标

![1686364194535](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/1686364194535.png)



## 10.8、Query By Criteria查询

Query By Criteria（QBC）是一种用于构建动态查询的方法，通常用于对象关系映射（Object-Relational Mapping，ORM）框架中。QBC允许开发人员以面向对象的方式编写查询语句，而无需直接使用SQL语句。

即直接在方法中加入条件即可。



> 举个例子

```Java
public class TestDemo {
    @Test
    public void test(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.selectByPrimaryKey(9);
        System.out.println("emp = " + emp);
    }

    /**
     * 没有条件就是查询全表
     * @author Aganippe
     * @version v1.0
     * @date 2023/6/10
     * @name testSelectDemo01
     * @return void
     */
    @Test
    public void testSelectDemo01(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> empList = mapper.selectByExample(null);
        empList.forEach(System.out::println);
    }

    /**
     * 根据条件查询数据
     * @author Aganippe
     * @version v1.0
     * @date 2023/6/10
     * @name testSelectDemo02
     * @return void
     */
    @Test
    public void testSelectDemo02(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        EmpExample example = new EmpExample();
        example.createCriteria().andEmployeeNameLike("%a%").andGenderEqualTo("男");
        example.or().andDeptIdEqualTo(4);
        List<Emp> empList = mapper.selectByExample(example);
        empList.forEach(System.out::println);
    }

    /**
     * 修改测试
     * @author Aganippe
     * @version v1.0
     * @date 2023/6/10
     * @name testUpdateDemo01
     * @return void
     */
    @Test
    public void testUpdateDemo01(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = new Emp(12,"User01",null,"男");
        mapper.updateByPrimaryKey(emp);
        Emp emp1 = mapper.selectByPrimaryKey(12);
        System.out.println("emp1 = " + emp1);
    }

    /**
     * 测试    ***Selective 与 *** 的区别
     * @author Aganippe
     * @version v1.0
     * @date 2023/6/10
     * @name testUpdateDemo02
     * @return void
     * @note ***Selective 是选择性的，如果需要操作的值为 null，那就不会修改。
     *       而上面那个方法，值为null，则会修改为null。
     */
    @Test
    public void testUpdateDemo02(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp2 = mapper.selectByPrimaryKey(11);
        System.out.println("emp2 = " + emp2);
        Emp emp = new Emp(11,"User",null,"男");
        mapper.updateByPrimaryKeySelective(emp);
        Emp emp1 = mapper.selectByPrimaryKey(11);
        System.out.println("emp1 = " + emp1);
    }
}
```



# 11、分页插件

可以搭配web项目使用。



## 11.1、依赖

```xml
<!--分页查询插件-->
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper</artifactId>
    <version>5.2.0</version>
</dependency>
```



## 11.2、Mybatis核心配置

```xml
<plugins>
    <plugin interceptor="com.github.pagehelper.PageInterceptor"></plugin>
</plugins>
```



## 11.3、使用

```Java
@Test
public void testPage(){
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
    //在查询之前开启分页功能,该方法会在SQL语句中自动添加分页功能
    PageHelper.startPage(1,4);
    List<Emp> empList = mapper.selectByExample(null);
    empList.forEach(System.out::println);
}
```



## 11.4、分页的相关数据

> PageInfo{
>
> pageNum=8, pageSize=4, size=2, startRow=29, endRow=30, total=30, pages=8,
>
> list=Page{count=true, pageNum=8, pageSize=4, startRow=28, endRow=32, total=30,
>
> pages=8, reasonable=false, pageSizeZero=false},
>
> prePage=7, nextPage=0, isFirstPage=false, isLastPage=true, hasPreviousPage=true,
>
> hasNextPage=false, navigatePages=5, navigateFirstPage4, navigateLastPage8,
>
> navigatepageNums=[4, 5, 6, 7, 8]
> }

- pageNum：当前页的页码
- pageSize：每页显示的条数
- size：当前页显示的真实条数
- total：总记录数
- pages：总页数
- prePage：上一页的页码
- nextPage：下一页的页码
- isFirstPage/isLastPage：是否为第一页/最后一页
- hasPreviousPage/hasNextPage：是否存在上一页/下一页
- navigatePages：导航分页的页码数
- navigatepageNums：导航分页的页码，[1,2,3,4,5]
- 



# MyBatis错误总结

## 常见问题

### 1、2023.05.07 配置文件路径错误问题

> org.apache.ibatis.exceptions.PersistenceException: 
>
> Error building SqlSession.
>
> The error may exist in mapper/UserMapper.xml
>
> Cause: org.apache.ibatis.builder.BuilderException: Error parsing SQL Mapper Configuration. Cause: java.io.IOException: Could not find resource mapper/UserMapper.xml

![1683435296094](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412021636870.png)

问题来源是`mybatis-config.xml`配置文件中的`UserMapper`路径写错了导致的。

```xml
<mappers>
    <mapper resource="mapper/UserMapper.xml"/>
</mappers>
```

解决办法，将路径更正。

```xml
<mappers>
    <mapper resource="mappers/UserMapper.xml"/>
</mappers>
```



### 2、Expected one result (or null) to be returned by selectOne(), but found:

> 时间：2023.05.18
>
> 问题：Expected one result (or null) to be returned by selectOne(), but found: 2
>
> 翻译：selectOne（）应返回一个结果（或空值），但返回2个结果。
>
> 解决办法：将接口中的查询方法，改成List集合，用集合接收多条查询结果即可。

![1684469239102](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412021636883.png)

```Java
User checkLoginByParam(@Param("name") String userName, @Param("pwd") String password);
```

改成

```Java
List<User> checkLoginByParam(@Param("name") String userName, @Param("pwd") String password);
```

