# 关于mvn配置问题

## 下载安装

在maven官网下载即可。https://maven.apache.org/download.cgi

![1676400493854](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250217173526363.png)



下载完，解压到你需要的位置即可。

## 配置环境

和java一样，配置环境变量

- 新建系统变量名MAVEM_HOME, 变量值是你maven安装的路径。初学者需要配置两个变量名 MAVEN_HOME 和 M2_HOME  变量名都是你存放maven的路径。
- **新建系统变量名path , 变量值是%MAVEN_HOME%\bin;%M2_HOME%\bin;**

> 错误

**The JAVA_HOME  environment variable is not defined correctly**

**This environment variable is needed to run this propram**



> 解决方案

在用户变量中配置JAVA_HOME，一定和系统变量JAVA_HOME一定要相同。





## 配置文件

我们需要着重关注 Maven 的核心配置文件：**conf/settings.xml**

### 1、指定本地仓库

本地仓库默认值：用户家目录/.m2/repository。由于本地仓库的默认位置是在用户的家目录下，而家目录往往是在 C 盘，也就是系统盘。将来 Maven 仓库中 jar 包越来越多，仓库体积越来越大，可能会拖慢 C 盘运行速度，影响系统性能。所以建议将 Maven 的本地仓库放在其他盘符下。配置方式如下：

```xml
<!-- localRepository
| The path to the local repository maven will use to store artifacts.
|
| Default: ${user.home}/.m2/repository
<localRepository>/path/to/local/repo</localRepository>
-->
<localRepository>D:\JetBrains\apache-maven-3.9.0\mvn-repo</localRepository>
```



### 2、配置阿里云镜像

Maven 下载 jar 包默认访问境外的中央仓库，而国外网站速度很慢。改成阿里云提供的镜像仓库，**访问国内网站**，可以让 Maven 下载 jar 包的时候速度更快。配置的方式是：

> 将原先的注释掉

```xml
<!-- <mirror>
  <id>maven-default-http-blocker</id>
  <mirrorOf>external:http:*</mirrorOf>
  <name>Pseudo repository to mirror external repositories initially using HTTP.</name>
  <url>http://0.0.0.0/</url>
  <blocked>true</blocked>
</mirror> -->
```



> 加入阿里云镜像

```xml
<mirror>
      <id>nexus-aliyun</id>
      <mirrorOf>central</mirrorOf>
      <name>Nexus aliyun</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public</url>
	</mirror>
```



### 3、配置Maven工程的基础的JDK版本

如果按照默认配置运行，Java 工程使用的默认 JDK 版本是 1.5，而我们熟悉和常用的是 JDK 1.8 版本。修改配置的方式是：将 profile 标签整个复制到 settings.xml 文件的 profiles 标签内。

~~~xml
<profile>
     <id>jdk-1.8</id>
     <activation>
        <activeByDefault>true</activeByDefault>
        <jdk>1.8</jdk>
     </activation>
     <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
     </properties>
	</profile>
  </profiles>
~~~



## 在IDEA 中使用maven

### 创建maven工程

![1682934000216](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250217173622219.png)



![1682934305694](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250217173638898.png)

> 这三个量代表的是 Maven 中的坐标

使用三个量 在『Maven的仓库』中**唯一的定位**到一个『jar』包。

- groupId：公司或组织的 id
- artifactId：一个项目或者是项目中的一个模块的 id
- version：版本号

> 三个量的取值方式

- groupId：公司或组织域名的倒序，通常也会加上项目名称 
  - 例如：com.atguigu.maven
- artifactId：模块的名称，将来作为 Maven 工程的工程名
- version：模块的版本号，根据自己的需要设定 
  - 例如：SNAPSHOT 表示快照版本，正在迭代过程中，不稳定的版本
  - 例如：RELEASE 表示正式版本

> 举例：

- groupId：com.atguigu.maven
- artifactId：pro01-atguigu-maven
- version：1.0-SNAPSHOT



### 工程中导入maven

![1682934573141](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250217173645266.png)



> 设置新建maven工程自动配置

![1682934646552](C:\Users\93988\Desktop\studynotes\学习笔记\maven\img\1682934646552.png)

点击之后与上图一致



> maven的工程结构

![1682934739535](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250217173651476.png)



### 创建JavaWeb工程

![1682935042160](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250217173655643.png)



> JavaWeb工程结构图

![1683019258994](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/20250217173702059.png)