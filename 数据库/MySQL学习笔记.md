# 1、数据库操作

如果你的表名过字段是一个特殊字符，就要用  ``包住，例如

~~~sql
 use `库名`;
~~~

## 1.1、基本操作（了解）

1、创建数据库

```sql
 create database 库名;
 create database if not exists 库名;  --if not exists  如果不存在就创建该数据库
```

2、删除数据库

```sql
 drop database 库名;
 drop database if exists 库名;   --if exists 如果存在就删除该数据库
```

3、使用数据库

```sql
 use 库名;
```

4、查看数据库

```sql
 show databases;  --查看所有的数据库
```

## 1.2、数据库的列类型

> 数值

- tinyint			     十分小的数据            1字节
- smallint              较小的数据                2字节
- mediumint        中等大小的数据         3字节
- **int                       标准的整数                 4字节       常用的        java对应类型   int**
- bigint                  较大的数据                 8字节
- float                    浮点型                         4字节
- double                浮点型                         8字节
- decimal               字符串形式的浮点型  金融计算的时候一般用decimal

> 字符串

- char                     字符串
- **varchar              可变字符串                   常用                java对应类型   string**
- tinytext               微型文本
- **text                      文本串                           保存大文本**



> 时间日期

java.util.Date



- date             YYYY-MM-DD，日期格式
- time             HH：mm： ss，时间格式
- **datetime     YYYY-MM-DD  HH：mm：ss  最常用的时间格式**
- **timestamp    时间戳，   到毫秒数      较为常用**
- year    年份



> null

- 没有值，未知

- ==注意，不要使用null值进行运算，结果为null==



## 1.3数据库的字段属性（重点）

==unsigned：==

- 无符号的整数
- 声明了该列不能声明为负数



==zerofill：==

- 0填充
- 不足的位数，使用0填充。   int（3）    5  ----->   005



==自增：==

- 通常理解为自增，自动在上一条记录的基础上+1（默认）
- 通常用来设计唯一的主键，必须是整数类型
- 可以自定义设计主键自增的起始值和步长



==非空 ：==null 和   not nul

- 假设设置为 not null，如果不给其赋值，就会报错。
- null，如果不填写值，默认就是null。



==默认：==

- 设置默认的值
- sex，默认值为 ==男==，如果不指定该列的值，则默认为 ==男==。



拓展

```sql
/*每一个表，都必须存在以下五个字段！ 实际项目会用到，表示一个记录存在的意义
**阿里巴巴规范**

id          主键
`version`   乐观锁
is_delete   伪删除
gmt_create  创建时间
gmt_update  修改时间
*/
```



## 1.4、创建数据库表

```sql
-- 目标：创建一个school数据库
-- 创建学生表 (列,字段)  使用SQL创建
-- 学号int 登录密码 varchar(20),姓名,性别varchar(2)，出生日期（datatime），家庭住址，email
-- auto_increment  自增
-- comment ''   注释
-- default   默认值
-- primary key  主键
use school;
create table if not exists `student1` (
`id` int(4) not null auto_increment comment '学号',
`name` varchar(30) not null default'匿名' comment'姓名',
`pwd` varchar(20) not null default'123456' comment'密码',
`sex` varchar(2) not null default'男' comment'性别',
`birthday` datetime default null comment'出生日期',
`address` varchar(100) default null comment'家庭住址',
`email` varchar(50) default null comment'邮箱',
primary key(`id`)
)engine = innodb default charset=utf8

```

格式

```sql 
create table `表名`(
	'字段名' 字段类型 ，
    '字段名' 字段类型 ，
    '字段名' 字段类型 ，
    ......
    '字段名' 字段类型 
)[表类型][字符集设置][注释]
```



## 1.5、常用命令

```sql
show create database school;  -- 查看创建数据库语句
show create table student;  -- 查看student数据表的定义语句
desc student;     -- 显示表结构
```



## 1.6、数据表的类型

INNODB    默认使用

MYISAM    早期使用

| 名称         | INNODB        | MYISAM |
| ------------ | ------------- | ------ |
| 事务支持     | 支持          | 不支持 |
| 数据行锁定   | 支持          | 不支持 |
| 外键约束     | 支持          | 不支持 |
| 全文索引     | 不支持        | 支持   |
| 表空间的大小 | 较大，约为2倍 | 较小   |

常规使用操作：

- MYISAM  节约空间，速度较快
- INNODB  安全性高，事务的处理， 多表多用户操作



> 在物理空间存在的位置

所有的数据库文件都存在data目录下，一个文件夹就对应一个数据库。

MySQL8.0存放在C:\ProgramData\MySQL\MySQL Server 8.0\Data 中。

C:\ProgramData是隐藏文件夹。

本质还是文件的储存。



MySQL 引擎在物理文件上的区别

- INNODB在数据库表中只有一个 *.frm文件，以及上级目录下的 ibdata1 文件
- MYISAM 对应文件
  - *.frm      表结构的定义文件
  - *.MYD    数据文件 （data）
  - *.MYI      索引文件 （index）



> 设置数据库表的字符集编码

```sql
charset=utf8
```

不设置的话，会是MySQL默认的字符集编码。（该编码不支持中文）

MySQL的默认编码是 Latin1。

在my.ini 中配置默认的编码

```sql
character-set-server=utf8;
```



## 1.7、修改数据库

> 修改表

```sql
-- 修改表名
alter table 旧表名 rename as 新表名;
-- 添加表的字段
alter table 表名 add 字段名 字段属性;

-- 修改表的字段 (重命名，修改字段属性[修改约束])
alter table 表名 modify 字段名 字段属性;       -- 修改字段属性
alter table 表名 change 旧字段名 新字段名 字段属性;  -- 字段重命名

-- 删除表的字段
alter table 表名 drop 字段名;
```





> 删除表

```sql
-- 删除表
drop table teacher1;

-- 删除表(如果表存在就删除)
drop table if exists teacher1;
```

==所有的创建和删除操作尽量加上判断，以免报错==



**==注意点：==**

- 字段名，最好使用``反引号包裹
- 所有的符号全部用英文！



# 2、MySQL的数据管理

## 2.1、外键（了解）

![外键约束](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412022154301.png)

> 方式1，代码方式，太麻烦
>
> 在创建表的时候，增加约束

```sql
create table if not exists `grade`(
`gradeid` int(10) not null auto_increment comment'年级id',
`gradename` varchar(50) not null comment'年级名称',
primary key(`gradeid`)
)engine=innodb default charset=utf8;


-- 学生表的gradeid 字段 要去引用年级表的gradeid
-- 定义外键key
-- 给这个外键添加约束（执行引用） references 引用
create table if not exists `student` (
`id` int(4) not null auto_increment comment '学号',
`name` varchar(30) not null default'匿名' comment'姓名',
`pwd` varchar(20) not null default'123456' comment'密码',
`sex` varchar(2) not null default'男' comment'性别',
`birthday` datetime default null comment'出生日期',
`gradeid` int(10) not null comment'学生的年级',
`address` varchar(100) default null comment'家庭住址',
`email` varchar(50) default null comment'邮箱',
primary key(`id`),
    
-- 重点在这
key `FK_gradeid` (`gradeid`),
constraint `FK_gradeid` foreign key (`gradeid`) references `grade` (`gradeid`)
--到这
)engine = innodb default charset=utf8
```

> 方式2：创建表成功后，添加外键约束

```sql
create table if not exists `grade`(
`gradeid` int(10) not null auto_increment comment'年级id',
`gradename` varchar(50) not null comment'年级名称',
primary key(`gradeid`)
)engine=innodb default charset=utf8;

-- 学生表的gradeid 字段 要去引用年级表的gradeid
-- 定义外键key
-- 给这个外键添加约束（执行引用） references 引用
create table if not exists `student` (
`id` int(4) not null auto_increment comment '学号',
`name` varchar(30) not null default'匿名' comment'姓名',
`pwd` varchar(20) not null default'123456' comment'密码',
`sex` varchar(2) not null default'男' comment'性别',
`birthday` datetime default null comment'出生日期',
`gradeid` int(10) not null comment'学生的年级',
`address` varchar(100) default null comment'家庭住址',
`email` varchar(50) default null comment'邮箱',
primary key(`id`)
)engine = innodb default charset=utf8;


-- 创建表的时候没有外键关系，需要添加外键约束
alter table `student` 
add constraint `FK_gradeid` foreign key(`gradeid`) references `grade`(`gradeid`);

alter table `表名` 
add constraint `约束名` foreign key(`作为外键的字段`) references `作为外键字段的那个表`(`那个字段`);
```

以上的操作都是物理外键，数据库级别的外键，不介意使用！（避免数据过多造成的困扰）

==最佳实践==

- 数据库就是单纯的表，只用来存数据，只有数据（行）和字段（列）。
- 我们想使用多张表的数据，想使用外键（程序去实现）



## 2.2、DML语言（全部记住）

**数据库意义：**数据存储，数据管理

DML语言：数据操作语言

- insert
- update
- delete



## 2.3、添加

>insert

```sql
-- insert into 表名([列名1,列名2,列名3...]) values ('值1'),('值2'),('值3')...

insert into grade(gradename) values('大四')
insert into grade(gradename) values('大三'),('大二'),('大一')

--不写列名，则按表默认顺序添加信息
INSERT INTO 学生信息表
VALUES('201905','数据库','女','2000-10-09','河南郑州','138756138','2019010')  --按表默认顺序添加信息

--通过列名，可添加指定内容
INSERT INTO 学生信息表(学号,姓名,性别)
VALUES('201909','你大爷','男')

```

语法：`insert into 表名([列名1,列名2,列名3...]) values ('值1'),('值2'),('值3')...`

==**注意事项：**==

1. 字段与字段之间使用 英文逗号 隔开
2. 字段是可以省略的，条件是后面的值要与表内的列名一一对应，不能少
3. 可以同时插入多条数据，values后面的值，需要使用`英文逗号`隔开。



## 2.4、修改

> update   

```sql
UPDATE 学生信息表 SET 地址='湖南长沙' WHERE 学号='201910'

-- 不用 where 指定条件时，会改动表中所有的信息
update student set `name` = '名字';

-- 修改多个值时，每个值之间用 英文逗号 隔开
update student set `name` = 'wjy' , email='123123@qq.com' where id=1;

-- 可以通过多个条件定位数据
update student set `name`='大妹子' where `name`= '名字' and sex= '女';
```

条件：where 子句 运算符 id 等于某个值，大于某个值，在某个区间内修改 。

| 操作符            | 含义         | 范围  | 结果  |
| ----------------- | ------------ | ----- | ----- |
| =                 | 等于         | 5=6   | false |
| <> 或 !=          | 不等于       | 5!=6  | turn  |
| >                 | 大于         |       |       |
| <                 | 小于         |       |       |
| <=                | 小于等于     |       |       |
| >=                | 大于等于     |       |       |
| between... and... | 在某个范围内 | [2,5] |       |
| and               | 与 &&        |       |       |
| or                | 或 \|\|      |       |       |

语法：`UPDATE 表名 SET 列名1='想要改的值1',列名2='想要改的值2'...  WHERE 条件`

注意：

- 条件：筛选的条件，如果没有指定，则会修改所有的列。
- value：是一个具体的值，也可以是一个变量。
- 多个设置的属性之间，使用`英文逗号`隔开。



## 2.5、删除

> delete 

语法：`delete from 表名 where 条件`

```sql
-- 删除数据
delete from student;

-- 删除指定数据
delete from student where id=4;
```

> truncate

作用：保留表结构，清除表内容。

完全清空一个数据库表，表的结构和索引约束不会变。

```sql 
truncate table student;
```

> delete 与 truncate 的区别

- 相同点：都能清空数据，都不会删除表结构
- 不同点：
  - truncate 重新设置，自增列 计数器会归零。
  - truncate 不会影响事务。

**拓展，了解即可：**`delete删除的问题` 重启数据库，现象

- InnoDB：自增列会从1开始（存在内存当中的，断电即失）
- MyISAM：继续从上一个自增量开始（存在文件中的，不会丢失）



# 3、DQL查询数据（重点）

## 3.1、DQL（Date Query Language:数据查询语言）

- 所有的查询操作都用它  select。
- 简单的查询，复杂的查询它可以做。
- ==数据库中最核心的语言，最重要的语句。==
- 使用频率最高的语句

> select 语法

严格的语法规定，顺序不能乱

```sql
SELECT [ALL | DISTINCT]
{* | table.* | [table.field1[as alias1][,table.field2[as alias2]][,...]]}
FROM table_name [as table_alias]
[left | right | inner join table_name2] -- 联合查询
[WHERE ...] -- 指定结果需满足的条件
[GROUP BY ...] -- 指定结果按照哪几个字段来分组
[HAVING] -- 过滤分组的记录必须满足的次要条件
[ORDER BY ...] -- 指定查询记录按一个或多个条件排序
[LIMIT {[offset,]row_count | row_countOFFSET offset}];
-- 指定查询的记录从哪条至哪条
```

**注意 : [ ] 括号代表可选的 , { }括号代表必选得**



## 3.2、指定查询字段

```sql
-- 查询全部的学生  
select * from student;

-- 查询指定字段
select studentno,studentname from student;

-- 别名，给结果起一个名字 as  可以给字段起别名，也可以给表起别名
select studentno as 学号, studentname as 学生名字 from student as 学生表;

-- 函数 concat(a,b)
select concat('姓名:', StudentName) as 新名字 from student;
```

语法：`select 字段1,字段2... from 表名 `

> 有的时候，列名字不是那么顾名思义。我们就可以起别名   `as`    `字段名 as 别名`      `表名 as 别名`



> 去重 distinct

作用：去除select 查询出来的结果中重复的数据，重复的数据只显示一条

```sql
-- 查询一下有哪些同学参加了考试，成绩
select * from result;  -- 查询全部的考试成绩
select studentno from result; -- 查询哪些学生有成绩
select distinct studentno from result; -- 去重
```



> 数据库的列（表达式）

```sql
select version();  -- 查询系统版本
select 100*3-1 as 计算结果;  -- 用来计算
select @@auto_increment_increment;  -- 查询自增的步长 (变量)

-- 学员考试成绩+1分查看
select studentno,studentresult +1 as '提分后' from result;
```

==数据库中的表达式：文本值，列，null，函数，计算表达式，系统变量...==

select `表达式` from 表名;



## 3.3、where 条件子句

作用： 检索数据中 `符合条件` 的值

> 逻辑运算符

| 运算符     | 语法                  | 描述   |
| ---------- | --------------------- | ------ |
| and   &&   | a and b          a&&b | 与运算 |
| or    \|\| | a or b         a\|\|b | 或运算 |
| not    !   | not a           !a    | 非运算 |

```sql
--   =================    where   =======================
select studentno, studentresult from result;

-- 查询考试成绩在95~100分之间
select studentno, studentresult from result
where studentresult>= 95 and studentresult<=100;

-- 模糊查询(区间)
select studentno,studentresult from result
where studentresult between 95 and 100;

-- 除了1000号学生之外同学的成绩
select studentno,studentresult from result where
studentno != 1000;

select studentno,studentresult from result where
studentno not 1000;
```



> 模糊查询：比较运算符

| 运算符      | 语法                 | 描述                                              |
| ----------- | -------------------- | ------------------------------------------------- |
| is null     | a is null            | 如果操作符为null，结果为true                      |
| is not null | a is not null        | 如果操作符不为null，结果为true                    |
| between     | a between b  and  c  | 若a 在 b 和 c 之间，结果为true                    |
| like        | a  like b            | SQL匹配，如果 a 匹配 b ，结果为true               |
| in          | a in (a1,a2,a3.....) | 假设 a 在 a1，或者a2...其中的某个值中，结果为true |

```sql
--   =================    模糊查询   =======================
-- 查询姓刘的同学
-- like结合  %(代表0个到任意个字符)   _ (代表一个字符)
select * from student where studentname like '刘%';

-- 查询姓刘单名的同学
select * from student where studentname like '刘_';

-- 查询姓刘，且名字两个字的同学
select * from student where studentname like '刘__';

-- 查询名字中带有佳的同学
select * from student where studentname like '%佳%';

-- =============  in  ===============
-- 查询 1001，1002,1003号学生
select studentno,studentname from student where
studentno in(1001,1002,1003);

-- 查询指定地址的同学
select studentno,studentname,address from student where
address in ('广东深圳','湖南郴州');

-- 查询地址为null的同学
select studentno,studentname,address from student where
address='' or address is null;

-- 查询地址 不为 null的同学
select studentno,studentname,address from student where
address is not null;
```



## 3.4、联表查询

> join 对比

![1668508161206](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412022156070.png)

> 七种join理论

![1668584105829](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412022156630.png)

![20200727091407770](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412022156566.png)

https://blog.csdn.net/xioayu96/article/details/107604338

| 操作       | 描述                                       |
| ---------- | ------------------------------------------ |
| inner join | 如果表中至少有一个匹配，就返回行           |
| left join  | 会从左表中返回所有的值   取a  +  a∩b的值   |
| right join | 从右表中返回所有的值       取b  +  a∩b的值 |

```sql
-- ===========   联表查询 join  =================
-- 查询参加了考试的同学(学号，姓名，科目编号，分数)
select * from student;
select * from result;


/* 思路
1、需求分析，分析查询的字段来自哪些表，（连接查询）
2、确定使用哪种连接查询？
3、确定交叉点（这两个表中哪个数据是相同的）
4、判断的条件：学生表中的studentno = 成绩表 studentno
*/

select s.studentno,studentname,subjectno,studentresult
from student as s
inner join result as r
where s.`studentno` = r.`studentno`;

-- right join 
select s.studentno,studentname,subjectno,studentresult
from student s
right join result r
on s.`studentno` = r.studentno;

-- left join 
select s.studentno,studentname,subjectno,studentresult
from student s
left join result r
on s.`studentno` = r.studentno;

-- 查询缺考的同学
select s.studentno,studentname,subjectno,studentresult
from student s
left join result r
on s.`studentno` = r.studentno
where studentresult is null;

-- 思考题(查询了参加考试的同学信息：学号、学生姓名、科目名、分数)
select s.studentno,studentname,subjectname,studentresult
from student s
right join result r
on r.`studentno` = s.studentno
inner join `subject` sub
on r.subjectno = sub.subjectno;

-- 我要查询哪些数据 select 列名1,列名2,列名3,.......
-- 从哪几个表中查： from 表1  **** join 表2  on  交叉条件
-- 假设存在一种多张表查询，慢慢来，先查询两种表然后再慢慢增加
```



> 自连接

自己的表和自己的表连接，核心：**一张表拆为两张一样的表**

```sql
create table text.`category`( 
`categoryid` int(3) not null comment 'id', 
`pid` int(3) not null comment '父id 没有父则为1', 
`categoryname` varchar(10) not null comment '种类名字', 
primary key (`categoryid`)
) engine=innodb charset=utf8 collate=utf8_general_ci; 

truncate table category;

insert into `category` (`categoryid`, `pid`, `categoryname`) values 
('2', '1', '信息技术'),
('3', '1', '软件开发'),
('5', '1', '美术设计'),
('4', '3', '数据库'),
('8', '2', '办公信息'),
('6', '3', 'web开发'),
('7', '5', 'ps技术');
```



父类

| categoryid | categoryname |
| ---------- | ------------ |
| 2          | 信息技术     |
| 3          | 软件开发     |
| 5          | 美术设计     |



子类

| pid  | categoryid | categoryname |
| ---- | ---------- | ------------ |
| 3    | 4          | 数据库       |
| 2    | 8          | 办公信息     |
| 3    | 6          | web开发      |
| 5    | 7          | ps技术       |

操作：查询父类对应的子类关系

| 父类     | 子类     |
| -------- | -------- |
| 信息技术 | 办公信息 |
| 软件开发 | 数据库   |
| 软件开发 | web开发  |
| 美术技术 | ps技术   |



```sql
-- 查询父子信息
select a.categoryname as '父栏目',b.categoryname as '子栏目'
from category as a,category as b
where a.`categoryid` = b.`pid`;
```



## 3.5、分页和排序

> 排序  order by

**默认排序为 ==升序 asc==，==降序 desc==**

```sql
-- order by把指定内容升序排列
select 列名1,列名2,列名3... from 表名 order by 需要升降序查询的列名; 

-- 降序排列
-- order by...... desc
select 列名1,列名2,列名3... from 表名 order by 需要升降序查询的列名 desc; 
```



> 分页 limit

**为什么要分页？**

1、缓解数据库压力，给用户的体验更好
2、无分页会导致，无限往下刷数据，瀑布流

语法： `limit (查询起始下标, pageSize )`

```sql
-- 语法： limit       起始值    ,  页面的大小
-- 公式   limit （N-1）*pageSize , pageSize
-- limit 0,5     1~5
-- limit 1,5     2~6
-- limit 6,5     7~11
select s.studentno,studentname,subjectname,studentresult
from student s
inner join result r
on s.`studentno` = r.`studentno`
inner join `subject` sub
on r.`subjectno` = sub.`subjectno`
where subjectname = '数据库结构-1'
order by studentresult desc
limit 2,5;

-- 第一页 limit 0,5      （1-1）*5
-- 第二页 limit 5,5      （2-1）*5
-- 第三页 limit 10,5     （3-1）*5
-- 第N 页 limit ？,5     （N-1）*5
-- 公式     （N-1）*pageSize , pageSize
-- pageSize:页面大小
-- （N-1）*pageSize：起始值
-- N：当前页
-- 数据总页/页面大小 = 总页数
```



## 3.6、子查询

```sql
-- ===============  where  =================
-- 1、查询数据库结构-1 的所有考试结果（学号，科目编号，成绩） 降序排列
-- 方式一：使用连接查询
select `studentno` , r.`subjectno` , studentresult
from result r
inner join `subject` sub
on r.`subjectno` = sub.`subjectno`
where subjectname = '数据库结构-1'
order by studentresult desc;

-- 方式二：使用子查询(由里及外)
select studentno,subjectno,studentresult
from result
where subjectno =(
	select subjectno from `subject`
	where subjectname = '数据库结构-1'
)
order by studentresult desc;

-- 分数不小于80分的学生的学号和姓名
select distinct s.studentno ,studentname
from student s
inner join result r
on r.`studentno` = s.`studentno`
where studentresult>= 80;

-- 在这个基础上增加一个科目，高等数学-2
-- 查询高数-2 的编号
select distinct s.studentno ,studentname,studentresult
from student s
inner join result r
on r.`studentno` = s.`studentno`
where studentresult>= 10 and subjectno = (
select subjectno from `subject` where subjectname = '高等数学-2');

-- 查询课程为，高等数学-2 且分数不小于 60 的同学的学号和姓名
select s.studentno,studentname,studentresult
from student s
inner join result r
on s.`studentno` = r.`studentno`
inner join `subject` sub
on r.`subjectno` = sub.`subjectno`
where subjectname = '高等数学-2' and studentresult >= 10
order by studentresult;



select studentno,studentname from student where studentno in(
	select studentno from result where studentresult >=60 and subjectno = (
		select subjectno from `subject` where subjectname = '高等数学-2'
	)
);
```



## 3.7、分组和过滤

```sql
-- 查询不同课程的平均分,最高分,最低分
-- 前提:根据不同的课程进行分组
select subjectname,avg(studentresult) as 平均分,max(studentresult) as 最高分,min(studentresult) as 最低分
from result r
inner join `subject` sub
on r.`subjectno`=sub.`subjectno`
group by r.subjectno     -- 通过什么字段来分组
having 平均分>80;
/*
where写在group by前面.
要是放在分组后面的筛选
要使用HAVING..
因为having是从前面筛选的字段再筛选，而where是从数据表中的>字段直接进行的筛选的
*/
```



# 4、MySQL函数

官网：[MySQL ：： MySQL 8.0 参考手册 ：： 12 个函数和运算符](https://dev.mysql.com/doc/refman/8.0/en/functions.html)



## 4.1、常用函数

> 数据函数

```sql
-- 数学运算
select abs(-8);    -- 绝对值
select ceiling(9.4);  -- 向上取整
select floor(9.4);  -- 向下取整
select rand();   -- 返回一个 0~1 之间的随机数
select sign(2);  -- 判断一个数的符号   0返回0  负数返回-1  正数返回 1
```

>  字符串函数

```sql
select char_length('呆逼呆逼呆逼');     -- 字符串长度
select concat('你','在搞','什么');    -- 拼接字符串
select insert('我爱编程helloworld',2,1,'超级热爱');     -- 查询，从某个位置开始替换某个长度
select lower('Hello MySQL');     -- 小写字母 
select upper('Hello MySQL');     -- 大写字母
select instr('Hello MySQL','l');  -- 返回第一次出现的子串的索引
select replace('坚持就能成功','坚持','努力');   -- 替换出现的指定字符串
select substr('坚持就能成功一二三四五六七',4,6);     -- 返回指定的子字符串（源字符串，截取位置，截取长度）
select reverse('坚持就能成功');  -- 反转

-- 查询姓赵的同学,改成邹
select replace(studentname,'赵','邹') as 新名字
from student where studentname like '赵%';
```



> 日期和时间函数

```sql
-- 日期和时间函数
select current_date(); -- 获取当前日期
select curdate();      -- 获取当前日期
select now();          -- 获取当前日期和时间
select localtime();    -- 获取当前日期和时间
select sysdate();      -- 获取当前日期和时间

-- 获取年月日,时分秒
select year(now());
select month(now());
select day(now());
select hour(now());
select minute(now());
select second(now());
```



> 系统信息函数

```sql
select VERSION();    -- 版本
select USER(); 
```



## 4.2、聚合函数（常用）

| 函数名称 | 描述                                                         |
| -------- | ------------------------------------------------------------ |
| count()  | 返回满足Select条件的记录总和数，如 select count(*) 【不建议使用 *，效率低】 |
| sum()    | 返回数字字段或表达式列作统计，返回一列的总和。               |
| avg()    | 通常为数值字段或表达列作统计，返回一列的平均值               |
| max()    | 可以为数值字段，字符字段或表达式列作统计，返回最大的值。     |
| min()    | 可以为数值字段，字符字段或表达式列作统计，返回最小的值。     |



```sql
-- COUNT:非空的
select count(studentname) from student;
select count(*) from student;
select count(1) from student;   -- 推荐
-- 从含义上讲，count(1) 与 count(*) 都表示对全部数据行的查询。
-- count(字段) 会统计该字段在表中出现的次数，忽略字段为null 的情况。即不统计字段为null的记录。
-- count(*) 包括了所有的列，相当于行数，在统计结果的时候，包含字段为null 的记录；
-- count(1) 用1代表代码行，在统计结果的时候，包含字段为null 的记录 。
/*
很多人认为count(1)执行的效率会比count(*)高，原因是count(*)会存在全表扫描，而count(1)
可以针对一个字段进行查询。其实不然，count(1)和count(*)都会对全表进行扫描，统计所有记录的
条数，包括那些为null的记录，因此，它们的效率可以说是相差无几。而count(字段)则与前两者不
同，它会统计该字段不为null的记录条数。
下面它们之间的一些对比：
1）在表没有主键时，count(1)比count(*)快
2）有主键时，主键作为计算条件，count(主键)效率最高；
3）若表格只有一个字段，则count(*)效率较高。
*/
select sum(StudentResult) as 总和 from result;
select avg(StudentResult) as 平均分 from result;
select max(StudentResult) as 最高分 from result;
select min(StudentResult) as 最低分 from result;
```



## 4.3、数据库级别的MD5加密（拓展）

**什么是MD5？**

主要增强算法复杂度和不可逆性

MD5不可逆，具体的值得MD5是一样的

MD5破解网站的原理，背后是一个字典，MD5加密后的值，加密的前值

```sql
-- ============   测试MD5  加密   ==============
use `text`;
create table testmd5(
	id  int(4) not null,
	`name` varchar(20) not null,
	pwd varchar(50) not null,
	primary key(id)
)engine= innodb default charset=utf8;

-- 明文密码
insert into testmd5 values(1,'张三','123456'),(2,'李四','123456'),(3,'王五','123456');

-- 加密
update testmd5 set pwd=md5(pwd) where id = 1;

-- 插入的时候加密
insert into testmd5 values(4,'小明',md5('123321'));

-- 如何校验：将用户传递进来的密码，进行md5加密，然后对比加密后的值
select * from testmd5 where `name`='小明' and pwd=md5('123321');
```



# 5、事务

## 5.1、什么是事务

==要么都成功，要么都失败==

————————

1、SQL执行   A  给   B  转账     A  1000    -->200    B  0

2、SQL执行   B  收到  A 的转账      A  800   -->  B   200

————————

将一组SQL放在一个批次中执行



- 事务就是将一组SQL语句放在同一批次内去执行。
- 如果一个SQL语句出错,则该批次内的所有SQL都将被取消执行。
- MySQL事务处理只支持InnoDB和BDB数据表类型。



> 事务原则：ACID原则，原子性、一致性、隔离性、持久性   （脏读、幻读.....）

相关资料：https://blog.csdn.net/dengjili/article/details/82468576/

**原子性(Atomicity)**

要么都成功，要么都失败



**一致性(Consist)**

事务前后数据完整性要保持一致      例总金额为 1000



**持久性(Durable)**    ---事务提交

事务一旦提交，则不可逆，被持久化到数据库中



**隔离性(Isolated)**

事务的隔离性是多个用户并发访问数据库是，数据库为每一个用户 开启的事务，不能被其他事务的操作数据所干扰，多个并发事务直接要相互隔离。



> 隔离所导致的问题

**脏读**：

指一个事务读取了另外一个事务未提交的数据



**不可重复读：**

在一个事务内读取表中的某一行数据，多次读取结果不同。（这个不一定是错误，只是某些场合不对）



**虚读（幻读）：**

是指在一个事务内读取到了别的事务插入的数据，导致前后读取数量总量不一致。



> 执行事务

![事务](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412022158216.png)

## 5.2、事务的实现

```sql
-- =============   事务   ================

-- MySQL 是默认开启事务自动提交的
set autocommit = 0; /*关闭*/
set autocommit = 1; /*开启(默认的)*/

-- 手动处理事务
set autocommit = 0;

-- 事务的开启
start transaction ; -- 标记一个事务的开始，从这之后的sql 都在同一个事务内

insert xx
insert xx

-- 提交：持久化(成功！)
commit;

-- 回滚：回到原来的样子(失败！)
rollback;

-- 事务结束
set autocommit = 1;   -- 开启自动提交


-- 中途保存
savepoint 保存点名;   -- 设置一个事务的保存点
rollback to savepoint 保存点名;  -- 回滚到保存点
release savepoint 保存点名; -- 撤销保存点

-- 转账
create database shop character set utf8 collate utf8_general_ci;
use shop;

create table account(
	id int(3) not null auto_increment,
	`name` varchar(30) not null,
	money decimal(9,2) not null,
	primary key(id)
)engine=innodb default charset=utf8;

insert into  account(`name`,money) values('A',2000.00),('B',10000.00);

-- 模拟转账：事务
set autocommit = 0;   -- 关闭自动提交
start transaction;  -- 开启一个事务

update account set money=money-500 where `name`= 'A';  -- A减500
update account set money=money+500 where `name`= 'B'; -- B加500

commit;
rollback;

set autocommit = 1;
```



# 6、索引

>  索引的作用：

- 提高查询速度。
- 确保数据的唯一性。
- 可以加速表和表之间的连接 , 实现表与表之间的参照完整性。
- 使用分组和排序子句进行数据检索时 , 可以显著减少分组和排序的时间。
- 全文检索字段进行搜索优化.



> 基础语法



```sql
-- 索引的使用
-- 1、在创建表的时候给字段增加索引
-- 2、创建完毕后添加索引

-- 显示所有的索引信息
use `text`;
show index from student;

-- 增加一个全文索引   索引名  列名
alter table text.`student` add fulltext index studentname(studentname);

-- explain 分析sql执行的状态
explain select * from student;   -- 非全文索引

explain select * from student where match(studentname) against('刘');
```



## 6.1、索引的分类

- 主键索引（primary key）
- 唯一索引（unique key）
- 常规索引（key / index）
- 全文索引（fulltext）



## 6.2、主键索引

唯一的标识，主键不可重复，只能有一个列作为主键



## 6.3、唯一索引

避免重复列的出现，唯一索引可以重复，多个列都可以标识为 唯一索引



## 6.4、常规索引

默认的，index、key 关键字来设置



## 6.5、全文索引

在特点的数据库引擎下才有，MyISAM

作用：快速定位数据



## 6.6、索引原则

- 索引不是越多越好
- 不要对进程变动数据索引
- 小数据量的表不需要加索引
- 索引一般加载常用来查询的列上



> 索引的数据结构

Hash 类型的索引

Btree：InnoDB 的默认数据结构



## 6.7、索引的底层原理

http://blog.codinglabs.org/articles/theory-of-mysql-index.html



# 7、数据库的备份与权限管理

## 7.1、用户管理

> 可视化管理：SQLyog

![1668846829355](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412022158803.png)



> SQL命令操作

用户表：mysql.user

本质：读这张表，进行增删改查

```sql
-- 创建用户
-- create user 用户名 identified by '密码'
create user wjy identified by '123456';

-- 修改密码 (修改当前用户)
set password = '123456';

-- 修改指定用户密码
set password for wjy = '123321';

-- 重命名 rename user 旧用户名 to 新用户名;
rename user wjy to wjy1;

-- 用户授权 all privileges 所有权限， 库.表
grant all privileges on *.* to wjy1;

-- 查询权限
show grants for wjy1;    -- 查看指定用户的权限
show grants for root@localhost;

-- 撤销权限
revoke all privileges on *.* from wjy1;

-- 删除用户
drop user wjy1;
```



## 7.2、MySQL备份

为什么要备份：

- 保证主要数据不丢失
- 数据转移

MySQL数据库备份的方式

- 直接拷贝物理文件
- 在可视化工具中直接操作导出
- 使用命令行导出   mysqldump 



# 8、规范数据库设计

## 8.1、为什么需要设计

==**当数据库比较复杂的时候，我们就需要设计了**==

**糟糕的数据库设计：**

- 数据冗余，浪费空间
- 数据库插入和删除都会麻烦、异常【屏蔽使用物理外键】
- 程序的性能差

**良好的数据库设计：**

- 节省内存空间
- 保证数据库的完整性
- 方便我们开发系统



**软件开发中，关于数据库的设计**

- 需求分析：分析业务和需要处理的数据库的需求
- 概要设计：设计关系图 E_R图



## 8.2、数据库的三大范式

**为什么需要数据规范化**

- 信息重复
- 更新异常
- 插入异常
  - 无法正常显示信息
- 删除异常
  - 丢失有效信息



> 三大范式

https://www.cnblogs.com/wsg25/p/9615100.html

**第一范式（1NF）：**

原子性：保证每列不可再分。



**第二范式（2NF）   前提：满足第一范式**

每张表只描述一件事情



**第三范式（3NF）  前提：满足第一第二范式**

第三范式需要确保数据表中每一列诗句都和主键直接相关，而不能间接相关。



**规范性和性能的问题**

关联查询的表不得超过三张表

- 考虑商业化的需求和目标，（成本，用户体验！）数据库的性能更加重要
- 在规范性的问题的时候，需要适当的考虑一下规范性！
- 故意给某些表增加一些冗余的字段。（从多表查询中变为单表查询）
- 故意增加一些计算列（从大数据量降低为小数据量的查询：索引）



# 9、JDBC（重点）

## 9.1、数据库驱动

![jdbc](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412022158073.png)

## 9.2、JDBC

SUN公司为了简化开发人员的操作，提供了一个java操作数据库的规范，俗称JDBC。

jdbc就是使用java语言操作关系型数据库的一套API。



## 9.3、第一个JDBC程序

> 创建测试数据库

```sql
create database `jdbcStudy` character set utf8 collate utf8_general_ci;

use `jdbcStudy`;

create table `users`(
 `id` int primary key,
 `NAME` varchar(40),
 `PASSWORD` varchar(40),
 `email` varchar(60),
 birthday date
);

 insert into `users`(`id`,`NAME`,`PASSWORD`,`email`,`birthday`)
values(1,'zhangsan','123456','zs@sina.com','1980-12-04'),
(2,'lisi','123456','lisi@sina.com','1981-12-04'),
(3,'wangwu','123456','wangwu@sina.com','1979-12-04');
```



1、创建一个普通项目

2、导入数据库驱动

```java
package com.jdbc.text01;


import java.sql.*;

/**
 * @时间: 2022/11/19    23:48
 */
//我的第一个jdbc程序
public class JdbcFirstDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1、加载驱动
        Class.forName("com.mysql.jdbc.Driver");  //固定写法，加载驱动   报错的话在url后面加上：useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC解决

        //2、用户信息和url
        String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSl=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "123456";

        //3、连接成功，数据库对象
        Connection connection = DriverManager.getConnection(url,username,password);
        //4、执行SQL的对象
        Statement statement = connection.createStatement();
        //5、 去执行SQL，可能存在结果，查看返回结果
        String sql = "select * from users";

        ResultSet resultSet = statement.executeQuery(sql);   //返回的结果集,结果集中封装了我们全部的查询出来的结果

        while (resultSet.next()){
            System.out.println("id="+resultSet.getObject("id"));
            System.out.println("name="+resultSet.getObject("name"));
            System.out.println("pwd="+resultSet.getObject("password"));
            System.out.println("email="+resultSet.getObject("email"));
            System.out.println("birth="+resultSet.getObject("birthday"));
            System.out.println("=====================================");
        }

        //6、释放连接
        resultSet.close();
        statement.close();
        connection.close();

    }
}
```

步骤总结：

1. 加载驱动  `Class.forName("com.mysql.jdbc.Driver"); `
2. 连接数据库 `DriverManager`
3. 获取执行sql的对象 `Statement`
4. 获得返回的结果集
5. 释放连接（与释放内存同理）



> DriverManager

```java
 Class.forName("com.mysql.jdbc.Driver");  //固定写法，加载驱动

Connection connection = DriverManager.getConnection(url,username,password);
//connection 代表数据库
//数据库设置自动提交
//事务提交
//事务回滚

connection.rollback();
connection.commit();
connection.setAutoCommit();
```



> URL

```java
String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSl=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
```



> Statement 执行SQL的对象  PrepareStatement 执行SQL的对象

```java
String sql = "select * from users";  //编写SQL

statement.executeQuery(); //返回的ResultSet
statement.execute(); //执行任何SQL
statement.executeUpdate();  // 感谢、插入、删除，都是用这个。返回一个受影响的行数
```



> ResultSet 查询的结果集：封装了所有的查询结果

获得指定的数据类型

```java
resultSet.getObject(); //在不知道列类型的情况下使用
//如果知道列类型就使用指定的类型
resultSet.getString();
resultSet.getInt();
resultSet.getFloat();
resultSet.getDate();
```



遍历，指针

```java
resultSet.beforeFirst();//移动到最前面
resultSet.afterLast();//移动到最后面
resultSet.next(); // 移动到下一行数据
resultSet.previous();// 移动到前一行
resultSet.absolute(row);//移动到指定行
```



> 释放内存

~~~java
//注册了什么，就关什么
resultSet.close();
statement.close();
connection.close();
~~~



## 9.4、statement对象 不安全的

==jdbc中的statement对象用于向数据库发送SQL语句，想完成对数据库的增删改查，只需要通过这个对象向数据库发送增删改查语句即可。==

statement对象的executeUpdate 方法，用于向数据库发送 **增、删、改**  的SQL语句。executeUpdate 执行完后，将会返回一个整数（即增删改语句导致数据库发生数据变化的行数）。



Statement.executeQuery 方法用于向数据库发送**查询语句**，executeQuery 方法返回值代表查询结果的ResultSet对象。



> CRUD操作——create

使用executeUpdate （String sql）方法完成**数据添加**操作，示例操作：

~~~java
Statement st = connection.createStatement();
String sql1 = "insert into user(...) values(...)";
int num = st.executeUpdate(sql1);
if (num > 0) {
    System.out.println("插入成功！");
}
~~~



> CRUD操作——delete

使用executeUpdate （String sql）方法完成**数据删除**操作，示例操作：

```java
Statement st = connection.createStatement();
String sql1 = "delete from user where id=1";
int num = st.executeUpdate(sql1);
if (num > 0) {
    System.out.println("删除成功！");
}
```



> CRUD操作——update

使用executeUpdate （String sql）方法完成**数据修改**操作，示例操作：

```java
Statement st = connection.createStatement();
String sql1 = "update user set name ='新名字' where name ='旧名字' ";
int num = st.executeUpdate(sql1);
if (num > 0) {
    System.out.println("修改成功！");
}
```



> CRUD操作——read

使用executeQuery （String sql）方法完成**数据查询**操作，示例操作：

~~~java
Statement st = connection.createStatement();
String sql1 = "delete from user where id=1";
ResultSet rs = st.executeQuery(sql1);
while (rs.next()){
      //根据获取列的数据类型，分别调用rs.的相应方法映射到java对象中
}
~~~



> 代码实现，真正的jdbc项目

1、提取工具类

~~~java
package com.jdbc.text02.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @时间: 2022/11/20    14:37
 */
public class JdbcUtils {

    private static String driver =null;
    private static String url =null;
    private static String username =null;
    private static String password =null;

    static {

        try {
            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db");
            Properties properties = new Properties();
            properties.load(in);

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            //1、驱动只有加载一次
            Class.forName(driver);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }


    //释放内存
    public static void release(Connection conn, Statement st, ResultSet rs){
        if (rs!=null){
            try {
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if (st!=null){
            try{
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn!=null){
            try{
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
~~~



2、编写增删改的方法 `executeUpdate`

~~~java
package com.jdbc.text02;

import com.jdbc.text02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @时间: 2022/11/20    17:06
 */
public class TextInsert {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();  // 获取数据库连接
            st=conn.createStatement();//获得SQL的执行对象
            
            /*增*/
            String sql = "insert into users value(4,'zhaoliu','123456','zl@sina.com','1982-12-04')";
            
            /*删
            String sql = "delete from users where id=4;";*/
            
            /*改
            String sql = "update users set `NAME` = 'study' ,email='7136@163.com' where id=1";*/

            int i = st.executeUpdate(sql);
            if (i > 0) {
                System.out.println("插入成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
~~~



3、编写查询的方法`executeQuery`

~~~java
package com.jdbc.text02;

import com.jdbc.text02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @时间: 2022/11/20    22:10
 */
public class TextRead {
    public static void main(String[] args) {
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try {
            conn = JdbcUtils.getConnection();
            st=conn.createStatement();

            //SQL
            String sql="select * from users where id=1;";
            rs = st.executeQuery(sql);

            while (rs.next()){
                System.out.println(rs.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
~~~



> SQL注入的问题

sql存在漏洞，会被攻击导致数据泄漏 ==SQL会被拼接 or==

```java
package com.jdbc.text02;

import com.jdbc.text02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @时间: 2022/11/20    23:02
 */
public class SQL注入 {
    public static void main(String[] args) {
        login(" 'or '1=1"," 'or'1=1");

    }

    //登录业务
    public static void login(String username,String password){
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try {
            conn = JdbcUtils.getConnection();
            st=conn.createStatement();

            //select * from users where `NAME` ='study' and `password`='123456'
            //select * from users where `NAME` =''or '1=1' and `password`=''or '1=1'
            String sql="select * from users where `NAME` ='"+username+"' and `password`='"+password+"'";
            rs = st.executeQuery(sql);

            while (rs.next()){
                System.out.println(rs.getString("NAME"));
                System.out.println(rs.getString("password"));
                System.out.println("=====================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
```



## 9.5、PreparedStatement对象

PreparedStatement 可以防止SQL注入。效率更高

防注入原理：把传递进来的参数当做字符

假设其中存在转义字符，就直接忽略



1、增、删、改

~~~Java
public class TextInsert {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;


        try {
            conn= JdbcUtils.getConnection();

            //区别
            //使用? 占位符代替参数
            String sql = "insert into users(id,`NAME`,`password`,email,birthday) values(?,?,?,?,?)";

            pst = conn.prepareStatement(sql);  //预编译SQL，先写SQL，然后不执行

            //手动给参数赋值
            pst.setInt(1,4);  //id
            pst.setString(2,"wjy");
            pst.setString(3,"1234684");
            pst.setString(4,"1568@gg.com");
            //注意点：sql.Date 数据库
            //      util.Date   java的
            pst.setDate(5,new java.sql.Date(new java.util.Date().getTime()));

            //执行
            int i = pst.executeUpdate();
            if (i>0) {
                System.out.println("插入成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,pst,rs);
        }
    }
}
~~~



2、查

~~~java
public class TextRead {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();

            String sql = "select * from  users where id= ?";//SQL语句

            pst= conn.prepareStatement(sql); //预编译

            pst.setInt(1, 2); // 传参

            rs = pst.executeQuery(); // 执行

            if (rs.next()) {
                System.out.println(rs.getString("NAME"));  // 查询返回值
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, pst, rs);// 释放内存
        }
    }
}
~~~



## 9.6、使用IDEA连接数据库

![1669011762292](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412022159266.png)

![1669011802258](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412022159268.png)



如果出现下面报错，则是MySQL时区问题，需要更新时区。

**Server returns invalid timezone. Need to set 'serverTimezone' property.**

解决办法参考：http://t.csdn.cn/ULrZm

![1669011991556](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412022159721.png)



## 9.7、事务

==要么都成功，要么都失败==

> ACID原则

原子性：要么都完成，要么都没完成

一致性：数据总数不变

隔离性：多个进程互不干扰

持久性：一旦提交不可逆，持久化到数据库中了



> 隔离性的问题

脏读：一个事务读取了另一个没有提交的事务

不可重复读：在同一个事务内，重复读取表中的数据，表数据发生了改变

幻读（虚读）：在一个事务内，读取到了别人插入的数据，导致前后读出来的结果不一致



> 代码实现

1、开启事务`conn.setAutoCommit(false);`。

2、一组业务执行完毕，提交事务。

3、可以在抛出异常语句中显示的定义显式回滚，但是默认失败就会自动回滚。

~~~java
public class TextTransaction01 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();

            //关闭数据库的自动提交， 会自动开启事务
            conn.setAutoCommit(false);

            String sql1="update account set money=money-100 where `name` ='用户一'";
            pst = conn.prepareStatement(sql1);
            pst.executeUpdate();

            String sql2="update account set money=money+100 where `name` ='用户二'";
            pst = conn.prepareStatement(sql2);
            pst.executeUpdate();

            //业务完毕，提交事务
            conn.commit();
            System.out.println("成功！");

        } catch (SQLException e) {
            //如果失败，默认回滚
            /*try {
                conn.rollback();  // 回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }*/
            e.printStackTrace();
        }
        finally {
            JdbcUtils.release(conn,pst,rs);
        }
    }
}
~~~



## 9.8、数据库连接池

数据库连接  ----> 执行完毕 ----> 释放内存

连接  ----> 释放 的过程中及其浪费系统资源



**池化技术：准备一些预先的资源，过来就连接预先准备好的**

常用连接数：

最小连接数：

最大连接数：  业务最高承载上限



超过最大连接数，则会排队等待。

等待超时：断开连接



编写连接池，实现一个接口 DataSource



> 开源数据源实现

DBCP

C3P0

Druid：阿里巴巴



使用了这些数据库连接池之后，我们在项目开发中就不需要编写连接数据库的代码了。



> DBCP

需要用到的jar包

commons-dbcp-1.4、     commons-pool-1.6



> c3p0

需要用到的jar包

c3p0-0.9.5.5   、    mchange-commons-java-0.2.19

**==注意！：jdk13的java工程用C3P0连接池会报以下错误，使用jdk1.8却不会==**

![1669047061233](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202412022159562.png)

11月 21, 2022 11:38:02 下午 com.mchange.v2.c3p0.cfg.C3P0Config 
警告: named-config with name 'mysql' does not exist. Using default-config extensions.
11月 21, 2022 11:38:02 下午 com.mchange.v2.c3p0.impl.AbstractPoolBackedDataSource 
信息: Initializing c3p0 pool... com.mchange.v2.c3p0.ComboPooledDataSource [ acquireIncrement -> 3, acquireRetryAttempts -> 30, acquireRetryDelay -> 1000, autoCommitOnClose -> false, automaticTestTable -> null, breakAfterAcquireFailure -> false, checkoutTimeout -> 0, connectionCustomizerClassName -> null, connectionTesterClassName -> com.mchange.v2.c3p0.impl.DefaultConnectionTester, contextClassLoaderSource -> caller, dataSourceName -> mysql, debugUnreturnedConnectionStackTraces -> false, description -> null, driverClass -> null, extensions -> {}, factoryClassLocation -> null, forceIgnoreUnresolvedTransactions -> false, forceSynchronousCheckins -> false, forceUseNamedDriverClass -> false, identityToken -> 1hge136as16yn95dmi6mmz|49e4cb85, idleConnectionTestPeriod -> 0, initialPoolSize -> 3, jdbcUrl -> null, maxAdministrativeTaskTime -> 0, maxConnectionAge -> 0, maxIdleTime -> 0, maxIdleTimeExcessConnections -> 0, maxPoolSize -> 15, maxStatements -> 0, maxStatementsPerConnection -> 0, minPoolSize -> 3, numHelperThreads -> 3, preferredTestQuery -> null, privilegeSpawnedThreads -> false, properties -> {}, propertyCycle -> 0, statementCacheNumDeferredCloseThreads -> 0, testConnectionOnCheckin -> false, testConnectionOnCheckout -> false, unreturnedConnectionTimeout -> 0, userOverrides -> {}, usesTraditionalReflectiveProxies -> false ]

























































































​	