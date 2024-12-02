# 1、SpringMVC

## 1.1、什么是MVC

MVC架构是一种软件设计模式，用于将应用程序的逻辑和用户界面分离，以便更好地组织和管理代码。MVC代表模型（Model）、视图（View）和控制器（Controller）。

- 模型（Model）：模型表示应用程序的数据和业务逻辑。它负责处理数据的存储、检索和更新，并提供对数据的操作接口。模型可以是数据库、文件或其他数据源。
  - 数据和业务逻辑都是JavaBean对象
  - 数据一般是指实体类对象，例如*POJO*。
  - 业务逻辑指的是 *Service* 或 *Dao*对象。
- 视图（View）：视图是用户界面的可视化表示。它负责显示数据和与用户进行交互。视图可以是一个网页、一个窗体或其他用户界面元素。
  - 常见的就是web开发中的前端可视页面，如：*html、jsp*等。
- 控制器（Controller）：控制器是模型和视图之间的桥梁。它接收用户的输入并根据输入更新模型或调整视图的显示。控制器处理用户交互，并根据模型的状态更新视图。
  - 一般是对应*Servlet* 用来接受请求和响应浏览器。



## 1.2、MVC的优点

MVC架构的优势在于它的分离性，它允许开发人员分别处理应用程序的不同方面。模型和视图的分离使得可以独立地修改数据处理逻辑或用户界面，而不会影响到其他部分。这种分离性提高了代码的可维护性和重用性，使开发过程更加灵活和高效。



## 1.3、什么是SpringMVC

`Spring MVC`是一个基于Java的Web应用程序开发框架，它是`Spring Framework`的一部分。

SpringMVC的本质就是***Servlet*** 

SpringMVC 是 Spring 为表述层开发提供的一整套完备的解决方案。在表述层框架历经 Strust、WebWork、Strust2 等诸多产品的历代更迭之后，目前业界普遍选择了 SpringMVC 作为 Java EE 项目表述层开发的首选方案。

> 三层架构分为表述层（或表示层）、业务逻辑层、数据访问层，表述层表示前台页面和后台servlet



## 1.4、SpringMVC的特点

- 强大的请求处理：基于原生的Servlet，通过了功能强大的前端控制器DispatcherServlet（也可以理解为中央控制器），对请求和响应进行统一处理。
- 松耦合和模块化的设计，提高代码的可维护性和重用性。
- 灵活的请求处理机制，可以根据具体需求实现灵活的请求路由和处理逻辑。
- 提供多种视图技术支持，使开发人员能够选择适合的视图技术，提供良好的用户体验。
- 易于与其他Spring框架和第三方库集成，提高开发效率和系统的可扩展性。
- 强大的表单处理和数据验证支持，简化表单数据的处理和验证，增强数据的安全性和完整性。
- 高度可配置，通过配置文件或注解提供丰富的配置选项，能够灵活地定制和控制应用程序的行为。



# 2、SpringMVC——Hello Word

## 2.1、搭建环境

> 本人使用的环境如下

- IDEA  2020.1.2
- maven 3.6.3
- tomcat 9.0.71
- Spring 5.3.20



## 2.2、添加依赖

```xml
<packaging>war</packaging>

<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.20</version>
    </dependency>

    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.2.3</version>
    </dependency>

    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.1.0</version>
    </dependency>

    <dependency>
        <groupId>org.thymeleaf</groupId>
        <artifactId>thymeleaf-spring5</artifactId>
        <version>3.0.12.RELEASE</version>
    </dependency>
</dependencies>
```



## 2.3、配置web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!--配置SpringMVC的核心servlet-->
    <servlet>
        <servlet-name>SpringMVC</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!--告诉SpringMVC配置文件的位置-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:SpringMVC-servlet.xml</param-value>
        </init-param>
    </servlet>

    <!--拦截请求-->
    <servlet-mapping>
        <servlet-name>SpringMVC</servlet-name>
        <!--留空表示该servlet将处理所有的请求。-->
        <url-pattern></url-pattern>
    </servlet-mapping>

</web-app>
```

`<url-pattern>`标签中使用 `/ `和`/*`的区别：
`/`所匹配的请求可以是`/login`或`/.html`或`/.js`或`/.css`方式的请求路径，但是`/`不能匹配`/.jsp`请求路径的请求。因此就可以避免在访问jsp页面时，该请求被DispatcherServlet处理，从而找不到相应的页面。

`/*`则能够匹配所有请求，例如在使用过滤器时，若需要对所有请求进行过滤，就需要使用`/*`的写法。



## 2.4、核心配置文件

`thymeleaf`动态渲染原理就是：将前端页面的绝对路径拆开，一个前缀和一个后缀。前缀一般是存放在那个文件夹下，后缀是文件格式。再通过`*Controller`将页面的名称返回，从而拼接成完整的路径。实习不同请求访问不同页面的动态切换。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="com.aip.controller" />

    <!-- 配置Thymeleaf视图解析器 -->
    <bean id="viewResolver"
          class="org.thymeleaf.spring5.view.ThymeleafViewResolver">
        <property name="order" value="1"/>
        <property name="characterEncoding" value="UTF-8"/>
        <property name="templateEngine">
            <bean class="org.thymeleaf.spring5.SpringTemplateEngine">
                <property name="templateResolver">
                    <bean
                            class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
                        <!-- 视图前缀 -->
                        <property name="prefix" value="/WEB-INF/templates/"/>
                        <!-- 视图后缀 -->
                        <property name="suffix" value=".html"/>
                        <property name="templateMode" value="HTML5"/>
                        <property name="characterEncoding" value="UTF-8" />
                    </bean>
                </property>
            </bean>
        </property>
    </bean>

</beans>
```



## 2.5、实现helloworld

> index.html

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
<body>
<h1>Hello World！</h1>
<a th:href="@{/blog}">Aip’s Blog</a>
</body>
</html>
```



> HelloController

```java
/**
 * @author Aganippe
 * @version 1.0
 * @date 2023/7/4  15:42
 * @Description 这是一个SpringMVC程序
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String portal(){
        //返回逻辑视图，这里的返回值与servlet.xml中的前后缀拼接起来就是完整的地址
        return "index";
    }

    @RequestMapping("/blog")
    public String hello(){
        return "hello";
    }
}
```



![1688461198688](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412021809709.png)



# 3、请求映射 @RequestMapping

@RequestMapping注解的作用就是将请求和处理请求的控制器方法关联起来，建立映射关系。SpringMVC 接收到指定的请求，就会来找到在映射关系中对应的控制器方法来处理这个请求。



## 3.1、注解的位置

- 标识一个类：设置映射请求的请求路径的初始信息，就是在方法注解前，再加一个前置条件，然后拼接在一起。
- 标识一个方法：设置映射请求请求路径的具体信息。



```Java
/**
 * @author Aip
 * @version 1.0
 * @date 2023/7/4  21:30
 * @description 了解注解标识的位置的作用
 */
@Controller
@RequestMapping("/test")
public class RequestMappingController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
```

![1688478995547](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412021810835.png)



## 3.2、注解的value属性

- @RequestMapping注解的value属性通过请求的请求地址匹配请求映射。
- @RequestMapping注解的value属性是一个字符串类型的数组，表示该请求映射能够匹配多个请求地址所对应的请求。
- @RequestMapping注解的value属性必须设置，至少通过请求地址匹配请求映射。



> 实例

使用数组时，表示两种请求都可以响应到该方法。

```Java
@Controller
public class RequestMappingController {

    @RequestMapping({"/hello","/tba"})
    public String hello(){
        return "hello";
    }
}
```



## 3.3、注解的method属性

其实就是请求的方式：`GET`、`POST`。目前学习的请求，除了表单提交的请求是`post`，其余的都是`get`请求。

- @RequestMapping注解的method属性通过请求的请求方式（get或post）匹配请求映射。
- @RequestMapping注解的method属性是一个RequestMethod类型的数组，表示该请求映射能够匹配
  多种请求方式的请求。
- 若当前请求的请求地址满足请求映射的value属性，但是请求方式不满足method属性，则浏览器报错
  405：Request method 'POST' not supported

*通常情况下，都是一个方法一种请求，不会像下面例子这样写。*

```Java
public class RequestMappingController {
    @RequestMapping(
            value = {"/hello","/tba"},
            method = {RequestMethod.POST,RequestMethod.GET})
    public String hello(){
        return "hello";
    }
}
```

当然方法注解也有被独立出来，形成派生注解。这类注解直接就代表了请求方式，不用自己定义请求方法了。

```Java
@PutMapping
@GetMapping
@PostMapping
@DeleteMapping
```

![1688527027221](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412021810586.png)



## 3.4、注解的params属性

- params属性通过请求的请求参数匹配请求映射
- 的params属性是一个字符串类型的数组，可以通过四种表达式设置请求参数和请求映射的匹配关系
- "param"：要求请求映射所匹配的请求必须携带param请求参数
- "!param"：要求请求映射所匹配的请求必须不能携带param请求参数
- "param=value"：要求请求映射所匹配的请求必须携带param请求参数且param=value
- "param!=value"：要求请求映射所匹配的请求必须携带param请求参数但是param!=value



```Java
@RequestMapping(
        value = {"/hello","/tba"},
        method = {RequestMethod.POST,RequestMethod.GET},
        params = {"username"})
public String hello(){
    return "hello";
}
```



> 两种写法

```html
<a th:href="@{/tba?username=Aip}">测试注解的params属性</a>
<br>
<a th:href="@{/tba(username='Aip')}">测试注解的params属性1</a>
```



## 3.5、注解的headers属性

- headers属性通过请求的请求头信息匹配请求映射
- headers属性是一个字符串类型的数组，可以通过四种表达式设置请求头信息和请求映射的匹配关系
- "header"：要求请求映射所匹配的请求必须携带header请求头信息
- "!header"：要求请求映射所匹配的请求必须不能携带header请求头信息
- "header=value"：要求请求映射所匹配的请求必须携带header请求头信息且header=value
- "header!=value"：要求请求映射所匹配的请求必须携带header请求头信息且header!=value
- 若当前请求满足value和method属性，但是不满足headers属性，此时页面显示404错误，即资源未找到



```Java
@RequestMapping(headers = {"referer"})
```



## 3.6、SpringMVC支持ant风格的路径

- ？：表示任意的单个字符
- *：表示任意的0个或多个字符
- **：表示任意层数的任意目录

> 注意：在使用 \*\*时，只能使用/\**/xxx的方式

```Java
@RequestMapping("/t?a/test/ant")
@RequestMapping("/t*/test/ant")
@RequestMapping("/**/test/ant")
public String testAnt(){
    return "hello";
}
```



## 3.7、SpringMVC支持路径中的占位符

原始方式：/deleteUser?id=1

rest方式：/user/delete/1

SpringMVC路径中的占位符常用于RESTful风格中，当请求路径中将某些数据通过路径的方式传输到服务器中，就可以在相应的@RequestMapping注解的value属性中通过占位符{xxx}表示传输的数据，在通过@PathVariable注解，将占位符所表示的数据赋值给控制器方法的形参。



```Java
@RequestMapping("/test/rest/{username}/{id}")
public String testRest(@PathVariable("id") Integer id,@PathVariable("username") String username){
    System.out.println("id = " + id);
    System.out.println("username = " + username);
    return "hello";
}
```

![1688629818347](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412021810089.png)



# 4、获取请求参数

## 4.1、通过Servlet API获取

这是Java Web中的知识点。

将HttpServletRequest作为控制器方法的形参，此时HttpServletRequest类型的参数表示封装了当前请求的请求报文的对象。

```java
/** 
 * 通过原生的ServletAPI获取请求参数
 * @author Aganippe
 * @version v1.0
 * @date 2023/7/6
 * @name getParamByServletAPI
 * @param request 请求
 * @return java.lang.String
 */
@RequestMapping("/param/servletAPI")
@PostMapping
public String getParamByServletAPI(HttpServletRequest request){
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    System.out.println("username = " + username);
    System.out.println("password = " + password);
    return "hello";
}
```



## 4.2、通过控制器方法的形参获取请求参数

在控制器方法的形参位置，设置和请求参数同名的形参，当浏览器发送请求，匹配到请求映射时，在DispatcherServlet中就会将请求参数赋值给相应的形参。

```Java
/**
 * 控制器方法的形参获取请求参数
 * @author Aganippe
 * @version v1.0
 * @date 2023/7/6
 * @name getParam
 * @param username 用户名
 * @param password 密码
 * @return java.lang.String
 */
@RequestMapping("/param")
public String getParam(String username,String password){
    System.out.println("username = " + username);
    System.out.println("password = " + password);
    return "hello";
}
```

> 注意：
>
> - 若请求所传输的请求参数中有多个同名的请求参数，此时可以在控制器方法的形参中设置字符串数组或者字符串类型的形参接收此请求参数。
> - 若使用字符串数组类型的形参，此参数的数组中包含了每一个数据。
> - 若使用字符串类型的形参，此参数的值为每个数据中间使用逗号拼接的结果。



## 4.3、通过@RequestParam注解

作用就是当你设置的参数名，与形参名不一致时，可以用该注解进行绑定。类似起别名吧。

这种方式只有特殊情况才会用。

- @RequestParam注解一共有三个属性：
  value：指定为形参赋值的请求参数的参数名
- required：设置是否必须传输此请求参数，默认值为true
  - 若设置为true时，则当前请求必须传输value所指定的请求参数，若没有传输该请求参数，且没有设置defaultValue属性，则页面报错400：Required String parameter 'xxx' is not present；
  - 若设置为false，则当前请求不是必须传输value所指定的请求参数，若没有传输，则注解所标识的形参的值为null。
- defaultValue：不管required属性值为true或false，当value所指定的请求参数没有传输或传输的值为""时，则使用默认值为形参赋值

```HTML
<form th:action="@{/request/param}" method="post">
    username:<input type="text" name="userName">
    password:<input type="password" name="password">
    <input type="submit" value="Login up">
</form>
```

```Java
@RequestMapping("/request/param")
public String getParamByRequestParam(@RequestParam("userName") String username, String password){
    System.out.println("username = " + username);
    System.out.println("password = " + password);
    return "hello";
}
```



## 4.4、@RequestHeader

- @RequestHeader是将请求头信息和控制器方法的形参创建映射关系
- @RequestHeader注解一共有三个属性：value、required、defaultValue，用法同@RequestParam

```Java
@RequestMapping("/request/param")
public String getParamByRequestParam(
        @RequestParam("userName") String username,
        String password,
        @RequestHeader("referer") String referer){
    System.out.println("username = " + username);
    System.out.println("password = " + password);
    System.out.println("referer = " + referer);
    return "hello";
}
```

![1688633550946](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412021811088.png)



## 4.5、@CookieValue

- @CookieValue是将cookie数据和控制器方法的形参创建映射关系
- @CookieValue注解一共有三个属性：value、required、defaultValue，用法同@RequestParam



```Java
@RequestMapping("/param")
public String getParam(HttpServletRequest request，String username,String password,
                       @CookieValue("JSESSIONID") String cookie){
    HttpSession session = request.getSession();
    System.out.println("username = " + username);
    System.out.println("password = " + password);
    System.out.println("cookie = " + cookie);
    return "hello";
}
```



## 4.6、通过POJO获取参数

就是将需要获取的参数封装到一个实体类中，例如用户名、密码、邮箱等用户信息可以用一个user实体类来整合。获取时只需要将实体类设置为形参即可。

```Java
@RequestMapping("/pojo")
public String getParamByPOJO(User user){
    System.out.println(user);
    return "hello";
}
```

表单中的参数名要和实体类中的属性名一致，否则会获取不到，返回NULL

```HTML
<form th:action="@{/pojo}" method="post">
    id:<input type="text" name="id">
    username:<input type="text" name="username">
    password:<input type="password" name="password">
    e-mail:<input type="text" name="email">
    <input type="submit" value="提交">
</form>
```



## 4.7、解决乱码问题

如果输入的请求参数是中文，则会出现乱码问题，需要调整编码。

原生的Servlet是在过滤器中设置编码为 *UTF-8*。

在设置编码之前一定不能获取任何请求参数，只要获取了，则编码无效。

SpringMVC提供的编码过滤器CharacterEncodingFilter，但是必须在web.xml中进行注册。

> SpringMVC中处理编码的过滤器一定要配置到其他过滤器之前，否则无效

![1688699913210](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412021811522.png)

~~~xml
<filter>
    <!-- 定义字符编码过滤器 -->
    <filter-name>characterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <!-- 设置过滤器的初始化参数 -->
    <init-param>
        <!-- 设置字符编码为UTF-8 -->
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
        <!-- 强制使用指定的字符编码 -->
        <param-name>forceEncoding</param-name>
        <param-value>true</param-value>
    </init-param>
</filter>

<filter-mapping>
    <!-- 将过滤器映射到所有的URL路径 -->
    <filter-name>characterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
~~~



> 关于无效问题

- 过滤器要在最上面，如果其他过滤器在编码过滤器之上，则会失效。
- 过滤器顺序正确，考虑idea控制台编码是否正确。在VM options中设置

~~~
-Dfile.encoding=UTF-8
~~~

![1688710836731](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412021811362.png)



# 5、域对象共享数据

## 5.1、向request域共享数据的方法

### 5.1.1、Servlet API

```Java
@RequestMapping("/test/servlet-api")
public String testServletAPI(HttpServletRequest request) {
    request.setAttribute("testScope", "hello,servletAPI");
    return "hello";
}
```

```HTML
<p th:text="${testScope}"></p>
```



### 5.1.2、ModelAndView

ModelAndView包含Model和View的功能
*          Model：向请求域中共享数据
*          View：设计逻辑视图，实现页面跳转

```Java
@RequestMapping("/test/mav")
public ModelAndView testMAV() {
    ModelAndView mav = new ModelAndView();
    mav.addObject("testRequestScope","hello,ModelAndView");//前面是共享数据的名称，要和页面上的名称一致，后面是需要共享的内容。
    mav.setViewName("hello");//你要跳转的页面的地址
    return mav;
}
```



```HTML
<p th:text="${testRequestScope}"></p>
```



### 5.1.3、Model

```Java
@RequestMapping("/test/model")
public String testModel(Model model){
    model.addAttribute("testModelScope","hello,Model");
    return "hello";
}
```



### 5.1.4、Map

```Java
@RequestMapping("/test/map")
public String testMap(Map<String,Object> map){
    map.put("testMapScope","hello,Map");
    return "hello";
}
```



### 5.1.5、ModelMap

```Java
@RequestMapping("/test/model/map")
 public String testModelMap(ModelMap modelMap){
     modelMap.addAttribute("testModelMap","hello,ModelMap");
     return "hello";
 }
```



### 5.1.6、Model、Map、ModelMap的关系

Model、ModelMap、Map类型的参数其实本质上都是 BindingAwareModelMap 类型的。

> 值得注意的是：无论是`Model`还是`Map`，其底层都是通过`ModelAndView`实现的。
>
> ![1688807308272](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412021812747.png)



## 5.2、向session域共享数据

建议使用原生的servlet API。因为springMVC提供的方式相较于原生的方式要复杂一些。

基本操作与上述一致，不同的是向session域共享数据。在thymeleaf中的表达式与request域有区别。

```html
<!--请求域，表达式只需要与 controller中的表达式对应即可。-->
<p th:text="${testRequestScope}"></p>
<!--session域，表达式为 session.controller中的表达式-->
<p th:text="${session.testSession}"></p>
```

```Java
@RequestMapping("/test/session")
public String testSession(HttpSession session){
    session.setAttribute("testSession","hello,Session");
    return "hello";
}
```



## 5.3、向application域共享数据

与session域共享数据的方式一样的。因为application也是通过session来获取的。

```Java
@RequestMapping("/test/application")
public String testApplication(HttpSession session){
    ServletContext application = session.getServletContext();
    application.setAttribute("testApplication","hello,Application");
    return "hello";
}
```

```HTML
<p th:text="${application.testApplication}"></p>
```



# 6、SpringMVC的视图

> 在Spring MVC中使用视图技术是可插拔的。无论你决定使用Thymeleaf、Groovy Markup Templates、JSP，还是其他技术，主要都是配置的改变。

SpringMVC中的视图是View接口，视图的作用渲染数据，将模型Model中的数据展示给用户。

SpringMVC视图的种类很多，默认有转发视图和重定向视图

- 若当工程引入jstl的依赖，转发视图会自动转换为JstlView。
- 若使用的视图技术为Thymeleaf，在SpringMVC的配置文件中配置了Thymeleaf的视图解析器，由此视图解析器解析之后所得到的是ThymeleafView。

> 创建什么视图只和视图名称有关系。



## 6.1、Thymeleaf

当控制器方法中所设置的视图名称没有任何前缀时，此时的视图名称会被SpringMVC配置文件中所配置的视图解析器解析，视图名称拼接视图前缀和视图后缀所得到的最终路径，会通过转发的方式实现跳转.

> 也就是说`ThymeleafView`的本质是转发。

```Java
@Controller
public class ViewController {
    @RequestMapping("/test/view/thymeleaf")
    public String testThymeleafView(){
        return "hello";//hello就是视图名称
    }
}
```



## 6.2、转发视图

SpringMVC中默认的转发视图是InternalResourceView。但是转发之后，还会经过视图技术进行渲染。比如`Thymeleaf`。所以一般情况都不会用这个。

> SpringMVC中创建转发视图的情况：
> 当控制器方法中所设置的视图名称以"forward:"为前缀时，创建InternalResourceView视图，此时的视图名称不会被SpringMVC配置文件中所配置的视图解析器解析，而是会将前缀"forward:"去掉，剩余部分作为最终路径通过转发的方式实现跳转。
>
> 例如"forward:/"，"forward:/employee"

```Java
@RequestMapping("/test/view/forward")
public String testInternalResourceView(){
    return "forward:/test/model";
}
```



## 6.3、重定向视图

SpringMVC中默认的重定向视图是RedirectView

> 当控制器方法中所设置的视图名称以"redirect:"为前缀时，创建RedirectView视图，此时的视图名称不会被SpringMVC配置文件中所配置的视图解析器解析，而是会将前缀"redirect:"去掉，剩余部分作为最终路径通过重定向的方式实现跳转。
>
> 例如"redirect:/"，"redirect:/employee"

重定向视图在解析时，会先将redirect:前缀去掉，然后会判断剩余部分是否以/开头，若是则会自动拼接上下文路径。

```Java
@RequestMapping("/test/view/redirect")
public String testRedirectView(){
    return "redirect:/test/model";
}
```



## 6.4、转发和重定向怎么选择？

- 业务逻辑处理失败时用转发。
- 业务逻辑处理成功时用重定向。



## 6.5、视图控制器

> 参考：[Servlet 栈的 Web 应用 (springdoc.cn)](https://springdoc.cn/spring/web.html#mvc-config-view-controller)
>
> 控制器方法中，仅仅用来实现页面跳转，即只需要设置视图名称时，可以将处理器方法使用`view-controller`标签进行表示。

属性：

- path：设置处理的请求地址。
- view-name：设置请求地址所对应的视图名称。

```xml
<!--开启mvc的注解驱动-->
<mvc:annotation-driven />
<!--视图控制器-->
<mvc:view-controller path="/" view-name="index"/>
```



> 注意：
>
> 当SpringMVC中仅设置任何一个view-controller时，其他控制器中的请求映射将全部失效。
>
> 此时需要在SpringMVC的核心配置文件中设置开启mvc注解驱动的标签：`<mvc:annotation-driven />`



# 7、RESTful

> 参考：https://blog.csdn.net/x541211190/article/details/81141459?fromshare=blogdetail

## 7.1、REST

REST（Representational State Transfer）是一种软件架构风格，而非一种标准。意思是表现层资源状态转移。

用于构建分布式系统和网络应用程序。它是一种轻量级的通信协议，常用于设计和开发基于Web的服务。



## 7.2、REST的原则

- 客户端-服务器模型：将系统分为客户端和服务器两个独立的组件，通过HTTP协议进行通信。客户端发送请求，服务器返回响应。这种分离有助于提高系统的可伸缩性和可扩展性，使客户端和服务器的职责清晰明确。
- 资源导向：资源是一种看待服务器的方式，即，将服务器看作是由很多离散的资源组成。每个资源是服务器上一个可命名的抽象概念。系统的功能被抽象为一组资源，每个资源都有一个唯一的标识符（URI）。客户端通过访问资源的URI来进行操作，例如获取、创建、更新或删除资源。这种资源导向的设计使得系统更具可见性和可理解性，同时也提供了灵活性和可扩展性。
- 无状态：服务器不存储客户端的状态信息，每个请求都是独立的。客户端的请求必须包含足够的信息，以便服务器能够理解和处理请求。这样的无状态特性有助于系统的可伸缩性和可靠性，并使得服务器能够更容易地进行水平扩展。
- 统一接口：REST使用统一的接口进行通信。这包括使用标准的HTTP动词（GET、POST、PUT、DELETE等）对资源进行操作，使用标准的媒体类型（如JSON、XML）进行数据交换。通过使用统一的接口，不同的客户端和服务器可以进行互操作，降低了系统的复杂性并提高了可扩展性。

> 总结
>
> - 对网络上所有的资源都有一个资源标志符（URI ）。
> - 对资源的操作不会改变标识符。
> - 同一资源有多种表现形式（xml、json）
> - 所有操作都是无状态的（Stateless）
>
> 符合上述REST原则的架构方式称为RESTful



## 7.3、URI和URL区别

> - URI：http://example.com/users/
> - URL：http://example.com/users/{user} (one for each user)



## 7.4、RESTful的实现

具体说，就是 HTTP 协议里面，四个表示操作方式的动词：GET、POST、PUT、DELETE。

它们分别对应四种基本操作：GET 用来获取资源，POST 用来新建资源，PUT 用来更新资源，DELETE用来删除资源。

REST 风格提倡 URL 地址使用统一的风格设计，从前到后各个单词使用斜杠分开，不使用问号键值对方式携带请求参数，而是将要发送给服务器的数据作为 URL 地址的一部分，以保证整体风格的一致性。



## 7.5、HiddenHttpMethodFilter

上文提到，除了*GET*、*POST*两种请求方式外，还有*PUT*、*DELETE*两种。但是浏览器只支持发送*GET* 和 *POST* 请求。

SpringMVC 提供了 ***HiddenHttpMethodFilter*** 帮助我们将 POST 请求转换为 DELETE 或 PUT 请求。

> 处理put和delete请求的条件

- 当前请求的请求方式必须为post。
- 当前请求必须传输请求参数_method。

满足以上条件，***HiddenHttpMethodFilter*** 就会将当前请求的请求方式转换为请求参数 \_method的值，因此请求参数 \_method的值才是最终的请求方式。

在web.xml中注册HiddenHttpMethodFilter

```xml
<filter>
    <filter-name>hiddenHttpMethodFilter</filter-name>
    <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>hiddenHttpMethodFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

增删改查对应的四个请求为：

- 增：POST
- 删：DELETE
- 改：PUT
- 查：GET



### 7.5.1、一个小案例

```HTML
<a th:href="@{/user}">查询所有用户信息</a><br>
<a th:href="@{/user/1}">根据ID查询用户信息</a><br>
<form th:action="@{/user}" method="post">
    <input type="submit" value="添加">
</form>
<br>
<form th:action="@{/user}" method="post">
    <input type="hidden" name="_method" value="put">
    <input type="submit" value="修改">
</form><br>
<form th:action="@{/user/5}" method="post">
    <input type="hidden" name="_method" value="delete">
    <input type="submit" value="删除">
</form>
```



```Java
@Controller
public class TestRestController {

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String getAllUser(){
        System.out.println("查询所有的用户信息-->/user-->get");
        return "hello";
    }

    @RequestMapping(value = "/user/{ID}",method = RequestMethod.GET)
    public String getUserByID(@PathVariable("ID") Integer ID){
        System.out.println("根据ID查询用户-->/user"+ID+"-->get");
        return "hello";
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String insertUser(){
        System.out.println("添加用户-->/user-->post");
        return "hello";
    }

    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public String updateUser(){
        System.out.println("修改用户-->/user-->put");
        return "hello";
    }

    @RequestMapping(value = "/user/{ID}",method = RequestMethod.DELETE)
    public String deleteUserByID(@PathVariable("ID") String ID){
        System.out.println("删除用户-->/user"+ID+"-->delete");
        return "hello";
    }
}
```



### 7.5.2、源码阅读

> 该源码比较简单，建议新手玩家可以试着自己读一遍，以提高源码阅读的能力。

```Java
public class HiddenHttpMethodFilter extends OncePerRequestFilter {

   private static final List<String> ALLOWED_METHODS =
         Collections.unmodifiableList(Arrays.asList(HttpMethod.PUT.name(),
               HttpMethod.DELETE.name(), HttpMethod.PATCH.name()));

   /** Default method parameter: {@code _method}. */
   public static final String DEFAULT_METHOD_PARAM = "_method";

   private String methodParam = DEFAULT_METHOD_PARAM;


   public void setMethodParam(String methodParam) {
      Assert.hasText(methodParam, "'methodParam' must not be empty");
      this.methodParam = methodParam;
   }

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
         throws ServletException, IOException {

      HttpServletRequest requestToUse = request;
       //当前接收到的请求如果是"POST"类型的，且没有抛出异常，则会进行转换。
      if ("POST".equals(request.getMethod()) && request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE) == null) {
          //获取请求参数，请求参数时键值对，通过键来获取值，这也是为什么隐藏域的名字一定是"_method"的原因，因为该值在这里被定义成了一个常量。  见第8~10行
         String paramValue = request.getParameter(this.methodParam);
          //判断该参数是否有长度，就是判空。
         if (StringUtils.hasLength(paramValue)) {
             //将请求参数的值，全部转换成大写字母。
            String method = paramValue.toUpperCase(Locale.ENGLISH);
             //去第一个方法里面进行判断，看看请求类型是否与上面的匹配
            if (ALLOWED_METHODS.contains(method)) {
                //创建一个请求对象，若此时 "request"=/user ,"method"=DELETE
               requestToUse = new HttpMethodRequestWrapper(request, method);
            }
         }
      }
       //过滤器放行
      filterChain.doFilter(requestToUse, response);
   }

   private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {

      private final String method;

      public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
         super(request);  //  "/user"
         this.method = method;  //  "DELETE"
      }

      @Override
      public String getMethod() {
         return this.method;  //  "DELETE"
      }
   }

}
```



## 7.6、RESTful的案例

### 7.6.1、准备工作

POJO、Dao、Service、Controller

> pojo

```java 
public class Employee {
    private Integer id;
    private String name;
    private String email;
    private String sex;

    public Employee() {
    }

    public Employee(Integer id, String name, String email, String sex) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
```



> dao

```java
@Repository
public class EmpDao {
    private static Map<Integer, Employee> empMap = null;

    static {
        empMap = new HashMap<Integer, Employee>();
        empMap.put(1001, new Employee(1001, "E-AA", "aa@163.com", "男"));
        empMap.put(1002, new Employee(1002, "E-BB", "bb@163.com", "男"));
        empMap.put(1003, new Employee(1003, "E-CC", "cc@163.com", "女"));
        empMap.put(1004, new Employee(1004, "E-DD", "dd@163.com", "女"));
        empMap.put(1005, new Employee(1005, "E-EE", "ee@163.com", "男"));
    }

    private static Integer initId = 1006;

    public void save(Employee emp) {
        if (emp.getId() == null) {
            emp.setId(initId++);
        }
        empMap.put(emp.getId(), emp);
    }

    public Collection<Employee> getAll(){
        return empMap.values();
    }
    public Employee get(Integer id){
        return empMap.get(id);
    }
    public void delete(Integer id){
        empMap.remove(id);
    }

}
```



> controller

```java
@Controller
public class EmpController {
    @Autowired
    private EmpDao empDao;

    @GetMapping("/employee")
    public String getAllEmp(Model model){
        Collection<Employee> employees = empDao.getAll();
        model.addAttribute("employees",employees);
        return "emp";
    }
}
```



# 8、SpringMVC处理Ajax请求

需要先解锁前置知识：***JavaScript***、***Vue***、***Ajax***，作为后端开发来说，至少得知道这些是什么东西，他们发的请求是什么样的。

> Vue可以去看尚硅谷新版JavaWeb教程，后面部分有个一小时的Vue教程，只看这个就行了。当然你也可以选择更好的教程观看。



```HTML
<script type="text/javascript" th:src="@{/js/vue.js}"></script>
<script type="text/javascript" th:src="@{/js/axios.min.js}"></script>
<script type="text/javascript">
    var vue = new Vue({
        el:"#app0",
        methods:{
            testAjax(){
                axios({
                    url:"",   //请求路径
                    method:"",//请求方式
                    params:{},//请求参数  
                    data:{}   //请求参数  
                }).then(response =>{
                    console.log(response.data)
                });
            }
        }
    });
</script>
```

> 两种请求参数的区别

- params:{}
  - `name=value&name=value`的形式发送请求参数，请求参数会拼接到请求地址后。
  - ***get/post***请求方式都可以。
  - 请求参数可以通过`request.getParameter()`获取。
- data:{}
  - json格式发送的请求参数，请求参数会保存到请求报文的请求体传输到服务器。
  - 只能用post请求方式，因为get没有请求体。
  - 不能通过`request.getParameter()`获取请求参数。



### 8.1、常用的Java对象转换为json的结果

实体类：json对象

Map：json对象

List：json数组



## 8.2、@RequestBody获取json格式的请求参数

在SpringMVC中，直接使用@RequestBody注解标识**控制器方法的形参**即可将此类请求参数转换为java对象。

> Json转Java实体类对象

```Java
@PostMapping("/test/request_body/json")
public void testRequestBody(@RequestBody Student student, HttpServletResponse response) throws IOException {
    System.out.println(student);
    response.getWriter().write("hello,LKai This‘s RequestBody!");
}
```

```HTML
<div id="app0">
    <h1>index.html</h1>
    <input type="button" value="使用@RequsetBody处理json格式的请求参数" @click="testRequestBody()">
</div>

<script type="text/javascript" th:src="@{/js/vue.js}"></script>
<script type="text/javascript" th:src="@{/js/axios.min.js}"></script>
<script type="text/javascript">
    var vue = new Vue({
        el:"#app0",
        data:{},
        methods:{
            
            testRequestBody(){
                axios.post(
                    "/test/request_body/json",
                    {username: "LKai", stuID: "4440123", age: 23, sex:"男" }
                ).then(request =>{
                    console.log(request.data)
                });
            }
        }
    });
</script>
```

![返回json格式的数据](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412021815653.png)

图中未获取到的数据，是因为和返回的实体类中的属性对应不上，只有属性名称一致才能获取数据。



> Json转Map集合

当没有相对应的实体类时，可以将数据封装进Map集合

```Java
@PostMapping("/test/request_body/json/map")
public void testRequestBodyMap(@RequestBody Map<String,Object> map,HttpServletResponse response) throws IOException {
    System.out.println(map);
    response.getWriter().write("hello,LKai This‘s RequestBody!");
}
```



![1689403220928](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412021815068.png)

通过Map可以获取到名字属性，使用Map即可在没有具体的实体类时，将数据封装。不需要有属性名对应的要求。



## 8.3、@ResponseBody响应浏览器json数据

@ResponseBody用于标识一个控制器方法，可以将该方法的返回值直接作为响应报文的响应体响应到浏览器。这样就不需要通过在方法加入形参，传输给浏览器，可以直接用返回值即可。

服务器处理ajax请求之后，大多数情况都需要向浏览器响应一个java对象，此时必须将java对象转换为json字符串才可以响应到浏览器，之前我们使用操作json数据的jar包gson或jackson将java对象转换为json字符串。在SpringMVC中，我们可以直接使用@ResponseBody注解实现此功能。

### 8.3.1、作用

可以将方法的返回值作为响应报文的响应体响应到浏览器。

> 小对比

```Java
@GetMapping("/test/response_body")
public String testResponseBody(){
    return "hello";     //会跳转到hello.html
}

@GetMapping("/test/response_body")
@ResponseBody
public String testResponseBody(){
    return "hello";    //此时响应浏览器数据hello
}
```



> 结果
>
> 图一为没有注解，图二为有注解

![1689404502882](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412021815823.png)

![1689404583358](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412021816944.png)

### 8.3.2、举例

> 实体类

```Java
@PostMapping("/test/response_body/object")
@ResponseBody
public Student testResponseStudent(){
    return new Student("Tang", 4440121, "男", 23);
}
```

![1689419691138](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412021815902.png)



> List集合

```Java
@PostMapping("/test/response_body/object")
@ResponseBody
public List<Student> testResponseBodyList(){
    List<Student> list = Arrays.asList(
            new Student("Aip",4440112,"男",22),
            new Student("Xinan",4440136,"女",22),
            new Student("Lkai",4440113,"男",23),
            new Student("Hugh",4440107,"男",24));
    return list;
}
```

![1689417905395](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412021816987.png)



> Map集合

```Java
@PostMapping("/test/response_body/map")
@ResponseBody
public Map<String,Student> testResponseBodyLMap(){
    Student student = new Student("Aip", 4440112, "男", 22);
    Student student1 = new Student("XiNan", 4440136, "女", 22);
    Student student2 = new Student("LKai", 4440113, "男", 23);
    Student student3 = new Student("Hugh", 4440107, "男", 24);
    Map<String,Student> map = new HashMap<>();
    map.put("Aip", student);
    map.put("XiNan", student1);
    map.put("LKai", student2);
    map.put("Hugh", student3);
    return map;
}
```

![1689424873272](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412021816437.png)



## 8.3.3、@RestController注解

``@RestController` 是一个组成的注解，它本身是由 `@Controller` 和 `@ResponseBody` 组成的元注解，表示一个controller的每个方法都继承了类型级的 `@ResponseBody` 注解，因此，直接写入响应体，而不是用HTML模板进行视图解析和渲染。



# 9、文件的上传和下载

## 9.1、文件下载

ResponseEntity用于控制器方法的返回值类型，该控制器方法的返回值就是响应到浏览器的响应报文使用ResponseEntity实现下载文件的功能。

```Java
@Controller
public class FileUpAndDownController {

    @RequestMapping("/test/down")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        //获取ServletContext对象
        ServletContext context = session.getServletContext();
        //获取服务器中文件的真实路径
        String realPath = context.getRealPath("/imgs/1.png");
        //创建输入流
        FileInputStream stream = new FileInputStream(realPath);
        //创建字节数组
        byte[] bytes = new byte[stream.available()];
        //将流读到字节数组中
        stream.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String,String> headers = new HttpHeaders();
        //设置要下载方式以及下载文件的名字
        headers.add("Content-Disposition","attachment;filename=1.png");
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> entity = new ResponseEntity<>(bytes, headers, statusCode);
        //关闭输入流
        stream.close();
        return entity;
    }
}
```

> 小提示：下载功能报错的，可以重新打包一下。因为资源都在war包里面。



## 9.2、文件上传

> 需要添加依赖

```xml
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.5</version>
</dependency>
```



> 必须通过文件解析器的解析才能将文件转换为MultipartFile对象，这个bean对象必须要有*id*，且名字一定得是`multipartResolver`

```xml
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="defaultEncoding" value="UTF-8"/>
    </bean>
```

![1689673713687](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412021816679.png)



> 文件解析器的属性

- `defaultEncoding`：上传文件的默认编码。
- `maxInMemorySize`：设置上传文件的最大内存大小（字节数）。如果上传的文件大小不超过此值，文件将被存储在内存中，否则会被存储在临时文件中。
- `uploadTempDir`：设置上传文件的临时存储目录。如果上传的文件超过了 `maxInMemorySize`，则会将文件存储在这个目录中。
- `maxUploadSize`：上传文件的最大值（字节数），如果请求的内容超过了这个值，将会拒绝上传。如限制20M以内：20\*1024\*1024=52428800。
- `maxUploadSizePerFile`：设置单个上传文件的最大大小（字节数）。如果单个文件大小超过了这个值，将会拒绝上传。
- `preserveFilename`：设置是否保留上传文件的原始文件名。如果设置为 `true`，则保留原始文件名，否则将会使用系统生成的文件名。
- `resolveLazily`：设置是否延迟解析请求内容。如果设置为 `true`，则只在访问请求参数时才解析内容，否则在处理请求时就立即解析。
- `servletContext`：设置用于访问 Servlet 上下文的 `ServletContext` 对象。一般情况下，这个属性会由 Spring 自动设置，您不需要手动配置。
- `supportedMethods`：设置支持文件上传的 HTTP 方法。默认情况下，`POST` 方法是支持文件上传的。



```java
@Controller
public class FileUpAndDownController {

    @PostMapping("/test/up")
    public String testUp(MultipartFile photoFile, HttpSession session) throws IOException {
        //获取上传的文件的文件名
        String photoName = photoFile.getOriginalFilename();
        //处理文件重名问题
        String duplicateName = photoName.substring(photoName.lastIndexOf("."));
        photoName = UUID.randomUUID().toString() + duplicateName;
        //获取服务器中文件目录的路径
        ServletContext servletContext = session.getServletContext();
        //获取当前工程中文件的真实路径
        String photoPath = servletContext.getRealPath("photo");
        File file = new File(photoPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String finalPath = photoPath + File.separator + photoName;
        //实现上传功能
        photoFile.transferTo(new File(finalPath));
        return "hello";
    }
```



# 10、SpringMVC拦截器

> 下为一个简单的拦截器

```Java
public class FistInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
```



- SpringMVC中的拦截器用于拦截控制器方法的执行
- SpringMVC中的拦截器需要实现HandlerInterceptor
- SpringMVC的拦截器必须在SpringMVC的配置文件中进行配置：

## 10.1、配置拦截器

> 方式一

```xml
<mvc:interceptors>
    <bean class="com.aip.interceptor.FistInterceptor"/>
</mvc:interceptors>
```



> 方式二

```xml
<bean id="fistInterceptor" class="com.aip.interceptor.FistInterceptor"/>
<mvc:interceptors>
    <ref bean="fistInterceptor"/>
</mvc:interceptors>
```



> 方式三：注解加扫描

```Java
@Component("interceptor")
public class FistInterceptor implements HandlerInterceptor {
...
}
```



```xml
<mvc:interceptors>
    <ref bean="interceptor"/>
</mvc:interceptors>
```



> 推荐：注解加扫描

- `<mvc:mapping path=""/>`：配置需要拦截的请求路径，/**表示拦截所有请求
- `<mvc:exclude-mapping path=""/>`：排除某个请求（不拦截某个请求），相当于白名单。
- `<ref/>`：配置bean对象。

```xml
<mvc:interceptors>
    <mvc:interceptor>
        <mvc:mapping path="/**"/>
        <mvc:exclude-mapping path="/Aip"/>
        <ref bean="interceptor"/>
    </mvc:interceptor>
</mvc:interceptors>
```



## 10.2、浅读源码

> 拦截器的三个方法

- `preHandle()`：在控制器方法执行之前执行，其返回值表示对控制的方法的拦截（false）或放行（true）。
- `postHandle()`：在控制器方法执行之后执行。
- `afterCompletion`：在控制器方法执行之后，且渲染视图完成之后执行。



```Java
//在控制器方法执行之前，调用拦截器中的 preHandle()
if (!mappedHandler.applyPreHandle(processedRequest, response)) {
         return;
      }

      // Actually invoke the handler.
	  //这里是控制器方法执行的地方
      mv = ha.handle(processedRequest, response, mappedHandler.getHandler());

      if (asyncManager.isConcurrentHandlingStarted()) {
         return;
      }

      applyDefaultViewName(processedRequest, mv);
	  //调用拦截器中的 PostHandle()
      mappedHandler.applyPostHandle(processedRequest, response, mv);
   }
   catch (Exception ex) {
      dispatchException = ex;
   }
   catch (Throwable err) {
      // As of 4.3, we're processing Errors thrown from handler methods as well,
      // making them available for @ExceptionHandler methods and other scenarios.
      dispatchException = new NestedServletException("Handler dispatch failed", err);
   }
   processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
}
	finally {
			if (asyncManager.isConcurrentHandlingStarted()) {
				// Instead of postHandle and afterCompletion
				if (mappedHandler != null) {
					
                 mappedHandler.applyAfterConcurrentHandlingStarted(processedRequest, response);
				}
			}
			else {
				// Clean up any resources used by a multipart request.
				if (multipartRequestParsed) {
					cleanupMultipart(processedRequest);
				}
			}
		}

private void processDispatchResult(HttpServletRequest request, HttpServletResponse response,
			......
		// Did the handler return a view to render?
		if (mv != null && !mv.wasCleared()) {
			render(mv, request, response);
			if (errorView) {
				WebUtils.clearErrorRequestAttributes(request);
			}
		}
		else {
			if (logger.isTraceEnabled()) {
				logger.trace("No view rendering, null ModelAndView returned.");
			}
		}

		if (WebAsyncUtils.getAsyncManager(request).isConcurrentHandlingStarted()) {
			// Concurrent handling started during a forward
			return;
		}

		if (mappedHandler != null) {
			// Exception (if any) is already handled..
            // 调用拦截器中的 AfterConcurrent()
			mappedHandler.triggerAfterCompletion(request, response, null);
		}
	}
```



> 拦截器部分源码

```Java
/**
 * 这部分的for循环，是一个拦截器的迭代器，通过这个for循环来遍历这个拦截器集合中的所有拦截器
 * if条件中，如果拦截器中 preHandle()的返回值是false的话，就会执行if中的代码，如果是true则跳过当前if()语句。
 * 进入if()中，即preHandle()返回值为false时，则当前拦截器会直接执行 triggerAfterCompletion()，随即返回一个 false。
 * 返回 false之后，回到下面方法中，执行 return; 直接结束方法，即不会执行 postHandle()
 if (!mappedHandler.applyPreHandle(processedRequest, response)) {
					return;
				}
*/
boolean applyPreHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
   for (int i = 0; i < this.interceptorList.size(); i++) {
      HandlerInterceptor interceptor = this.interceptorList.get(i);
      if (!interceptor.preHandle(request, response, this.handler)) {
         triggerAfterCompletion(request, response, null);
         return false;
      }
       //为拦截器索引赋值
      this.interceptorIndex = i;
   }
   return true;
}

/**
 * 调用拦截器的 postHandle()，该方法是反序执行的。
 * 通过拦截器集合，来获取其中的拦截器，并调用postHandle()
 */
void applyPostHandle(HttpServletRequest request, HttpServletResponse response, @Nullable ModelAndView mv)
      throws Exception {

   for (int i = this.interceptorList.size() - 1; i >= 0; i--) {
      HandlerInterceptor interceptor = this.interceptorList.get(i);
      interceptor.postHandle(request, response, this.handler, mv);
   }
}

/**
 * 该方法同上，也是反序执行的。
 * 该方法和拦截器长度没有关系，是通过拦截器索引来获取的，且这个拦截器是被PreHandle()放行的拦截器，具体去看上方applyPreHandle()
 * 	
 */
void triggerAfterCompletion(HttpServletRequest request, HttpServletResponse response, @Nullable Exception ex) {
   for (int i = this.interceptorIndex; i >= 0; i--) {
      HandlerInterceptor interceptor = this.interceptorList.get(i);
      try {
         interceptor.afterCompletion(request, response, this.handler, ex);
      }
      catch (Throwable ex2) {
         logger.error("HandlerInterceptor.afterCompletion threw exception", ex2);
      }
   }
}
```



## 10.3、多个拦截器的执行顺序

- ①若每个拦截器的preHandle()都返回true
  - 此时多个拦截器的执行顺序和拦截器在SpringMVC的配置文件的配置顺序有关：
  - preHandle()会按照配置的顺序执行，而postHandle()和afterCompletion()会按照配置的反序执行。





> 测试代码

```Java
@Component("interceptor")
public class FistInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("1st --> preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("1st --> postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("1st --> afterCompletion");
    }
}
```



```Java
@Component
public class SecondInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("2nd --> preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("2nd --> postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("2nd --> afterCompletion");
    }
}
```





# 11、异常处理器

如果在请求映射过程中发生异常或从请求处理程序（如 `@Controller`）抛出异常， `DispatcherServlet` 会委托给处理程序异常解析器（`HandlerExceptionResolver`）Bean链来解析异常并提供替代处理，这通常是一个错误响应。



> 源码

```Java
public interface HandlerExceptionResolver {

    //这个返回值是 ModelAndView类型的，也就是说，处理完异常，在域对象中共享数据，且跳转到一个新的页面
   @Nullable
   ModelAndView resolveException(
         HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex);

}
```



## 11.1、作用

如果控制器方法在执行过程中，出现了指定异常。异常处理器就会解析异常，并且就转到专门的一个错误页面。



## 11.2、使用方法

~~~xml
<!--异常解析器配置-->
    <bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
        <property name="exceptionMappings">
            <props>
                <!--properties的键：表示处理器方法执行过程中出现的异常
                    properties的值：表示若出现指定异常时，设置一个新的视图名称，跳转到指定页面-->
                <prop key="ArithmeticException">error</prop>
            </props>
        </property>
        <!--exceptionAttribute属性设置一个属性名，将出现的异常信息在请求域中进行共享-->
        <property name="exceptionAttribute" value="ex"/>
    </bean>
~~~



> 一个非常简陋的错误页面

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>错误</title>
</head>
<body>
<h1>error.html</h1>
<!--通过 ${} 将请求域中的数据传入前端页面-->
<p th:text="${ex}"></p>
</body>
</html>
```



## 11.3、基于注解的使用方法

- @ControllerAdvice：将当前类标识为异常处理的组件
- @ExceptionHandler：用于设置所标识方法处理的异常
- e：表示当前请求处理中出现的异常对象

```Java
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ArithmeticException.class)
    public String handleArithmeticException(Exception e, Model model){
        model.addAttribute("ex",e);
        return "error";
    }
}
```



# 12、注解配置SpringMVC

该部分，即使不深入学习，也必须提前了解，因为到了SpringBoot里面基本都是基于注解去配置。

> 描述：使用配置类和注解代替web.xml和SpringMVC配置文件的功能



## 12.1、WebInit类代替web.xml

```java
package com.aip.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * @author Aip
 * @version 1.0
 * @date 2023/7/29  17:57
 * @description 通过注解配置SpringMVC，初始化类
 */
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 指定Spring的配置类，这一步相当于web.xml中的配置 DispatcherServlet
     * @author Aganippe
     * @version v1.0
     * @date 2023/7/29
     * @name getRootConfigClasses
     * @return java.lang.Class<?>[]
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    /**
     * 指定SpringMVC的配置类
     * @author Aganippe
     * @version v1.0
     * @date 2023/7/29
     * @name getServletConfigClasses
     * @return java.lang.Class<?>[]
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * 指定DispatcherServlet的映射规则，即url-pattern
     * @author Aganippe
     * @version v1.0
     * @date 2023/7/29
     * @name getServletMappings
     * @return java.lang.String[]
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 添加过滤器
     * @author Aganippe
     * @version v1.0
     * @date 2023/7/29
     * @name getServletFilters
     * @return javax.servlet.Filter[]
     */
    @Override
    protected Filter[] getServletFilters() {
        //创建过滤器编码
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        //创建请求方式过滤器
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        return new Filter[]{encodingFilter, hiddenHttpMethodFilter};
    }
}
```



## 12.2、WebConfig类代替SpringMVC的配置文件

```java
/**
 * @author Aip
 * @version 1.0
 * @date 2023/7/30  9:44
 * @description 代替SpringMVC的配置文件
 * 具体功能：扫描组件:       @ComponentScan("com.aip.controller")、
 *         mvc的注解驱动:  @EnableWebMvc、
 *         默认的servlet: configureDefaultServletHandling()、
 *         视图控制器:     addViewControllers、
 *         拦截器:        addInterceptors、
 *         异常解析器:     configureHandlerExceptionResolvers、
 *         文件上传解析器、
 *         视图解析器、
 */
@Configuration
@ComponentScan("com.aip.controller")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /**
     * 默认的servlet
     * @author Aganippe
     * @version v1.0
     * @date 2023/7/31
     * @name configureDefaultServletHandling
     * @param configurer 配置器
     * @return void
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 添加一个重定向的视图控制器
     * @author Aganippe
     * @version v1.0
     * @date 2023/7/31
     * @name addViewControllers
     * @param registry 视图控制器注册器
     * @return void
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    /**
     * 拦截器，当然也可以在
     * @author Aganippe
     * @version v1.0
     * @date 2023/8/1
     * @name addInterceptors
     * @param registry 拦截器注册器
     * @return void
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        FirstInterceptor firstInterceptor = new FirstInterceptor();
        registry.addInterceptor(firstInterceptor).addPathPatterns("/**");
    }

    /**
     * 异常处理器，个人觉得还是写一个集中的异常处理器比较方便/
     * @author Aganippe
     * @version v1.0
     * @date 2023/8/1
     * @name configureHandlerExceptionResolvers
     * @param resolvers 异常处理器注册器
     * @return void
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        //创建SpringMVC提供自定义的异常处理器
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        //设置 Properties对象
        Properties prop = new Properties();
        //添加具体的异常类型，键为类型，值为逻辑视图
        prop.setProperty("java.lang.ArithmeticException","error");
        //对应配置文件中的exceptionMappings
        exceptionResolver.setExceptionMappings(prop);
        //将出现的异常信息在请求域中进行共享
        exceptionResolver.setExceptionAttribute("ex");
        resolvers.add(exceptionResolver);

    }

    /**
     * 文件上传解析器
     * 在SpringMVC的核心配置文件中，xx解析器都是以bean对象的方式存在的；
     * 所以在配置类中，只需要将该方法用 @Bean 注解将其标识为一个 Bean对象即可
     * 注意其方法的返回值作为Bean对象进行管理，Bean的ID就是方法名
     * @author Aganippe
     * @version v1.0
     * @date 2023/8/1
     * @name multipartResolver
     * @return org.springframework.web.multipart.commons.CommonsMultipartResolver
     */
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        return new CommonsMultipartResolver();
    }


    /**
     * 视图解析器
     * @author Aganippe
     * @version v1.0
     * @date 2023/8/2
     * @name templateResolver
     * @return org.thymeleaf.templateresolver.ITemplateResolver
     */
    @Bean
    public ITemplateResolver templateResolver() {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        // ServletContextTemplateResolver需要一个ServletContext作为构造参数，可通过WebApplicationContext 的方法获得
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(webApplicationContext.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    /**
     * 生成模板引擎并为模板引擎注入模板解析器
     * @author Aganippe
     * @version v1.0
     * @date 2023/8/2
     * @name templateEngine
     * @param templateResolver
     * @return org.thymeleaf.spring5.SpringTemplateEngine
     */
    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    //生成视图解析器并未解析器注入模板引擎
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }
}
```





# 13、SpringMVC源码

## 13.1、DispatcherServlet初始化过程

DispatcherServlet 本质上是一个 Servlet，所以天然的遵循 Servlet 的生命周期。所以宏观上是 Servlet生命周期来进行调度。

![1691563510205](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412022151587.png)、、



# 14、异常汇总

## 400异常

### Parameter conditions "\*\*\*" not met for actual request parameters: \*\*\*

参数异常：请求没有带`***参数`，或者是请求的`***参数`的值与设定值不符。

解决方法：检查参数及参数值是否正确。



### Required cookie 'cookie' for method parameter type String is not present

当前请求消息中的cookie没找到名为`'cookie'`的键。cookie信息是键值对储存的，你要通过键来查询值。

```Java
@RequestMapping("/param")
public String getParam(String username,String password,
                       @CookieValue("cookie") String cookie){
    System.out.println("username = " + username);
    System.out.println("password = " + password);
    System.out.println("cookie = " + cookie);
    return "hello";
```

![1688698298584](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412022152561.png)





## 404异常

表示找不到资源，也有可能是设置了headers属性导致的。



## 405异常

### Request method '***' not supported

请求方式异常：***请求方式被拒绝，给该方法添加对应的请求方式即可。

```Java
@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET})

//或者直接用表示请求方式的注解
@GetMapping
```





## 500异常

### Request processing failed; nested exception is org.thymeleaf.exceptions.TemplateInputException: An error happened during template parsing (template: "ServletContext resource [/WEB-INF/\*\*\*/***.html]")

> 描述：找不到此资源

可能的原因：

- 你的资源路径错了
- 也有可能是你的`Controller`中返回值忘记写，或者写错了。。。