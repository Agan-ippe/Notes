> 参考：
>
> - [学习JavaScript这一篇就够了](http://t.csdnimg.cn/kmXTM)
> - [【狂神说Java】JavaScript最新教程通俗易懂](https://www.bilibili.com/video/BV1JJ41177di/?share_source=copy_web&vd_source=f8340c4026115fdc3a178ce1879c37ab)

# 1、JavaScript简介	

JavaScript 是一种轻量级、解释型或即时编译型的编程语言，具有函数优先的特性。它是Web开发的核心技术之一，与HTML和CSS共同构成了现代Web应用的三大基石。其源代码在发往客户端之前不需要结果编译，而是将文本格式的字符代码发送给浏览器，由浏览器解释运行。



## 1.1、JavaScript的使用

==需要注意的是JavaScript 是严格区分大小写的。==



### 1.1.1、标签引用

```JavaScript
<script>
    alert('Hello JavaScript!');
	//your code.......
</script>
```



### 1.1.2、文件引入

hello.js

```javascript
alert('Hello, world!')
```



hello.html

```javascript
<script src="hello.js"></script>
```



## 1.2、JavaScript的输出



### 1.2.1、页面输出

使用JavaScript向页面输出一行信息。

```JavaScript
<script>document.write("Hello,JavaScript!")</script>
```



# 2、JavaScript的基本语法

> JavaScript语法是相对不严谨的，并且在idea中不会自动检查。可以使用`'use strict'`来检查JS代码。
>
> ```JavaScript
> <script>
>     'use strict'
>     //your code...
> </script>
> ```



## 2.1标识符

标识符的命名规范：

- 第一个字符必须是一个字母、下划线（ _ ）或一个美元符号（ $ ）。
- 其它字符可以是字母、下划线、美元符号或数字。
- 标识符采用驼峰命名法。
- 不能是关键字和保留字符。



> 关键字

![关键字](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/%E5%85%B3%E9%94%AE%E5%AD%97.png)



> 保留字

![保留字](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/%E4%BF%9D%E7%95%99%E5%AD%97.png)



> 其他不建议使用的标识符

![不建议使用的标识符](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/%E4%B8%8D%E5%BB%BA%E8%AE%AE%E4%BD%BF%E7%94%A8%E7%9A%84%E6%A0%87%E8%AF%86%E7%AC%A6.png)



> 标识符的命名举例

```javascript
//单个单词小写命名
name、age、gender

//多个单词驼峰命名
empName、empAge、empGender
```



## 2.2、变量

在ES5之前，不管是什么类型的变量，都使用关键字 **var** 来声明。而在ES5之后的规范中分为了局部变量、全局变量、常量。

- 局部变量：let
- 全局变量：var
- 常量：const



```JavaScript
//定义全局变量
var name = '张三';

//定义局部变量
let age = 20;

//定义常量
const gender = '男';
```



定义了变量之后，系统会根据赋给的值自动判断变量类型。

![image-20240607180935329](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240607180935329.png)

## 2.3、注释

JavaScript有两种注释，一种是单行注释，一种是多行注释。

- 单行注释：//
- 多行注释：/* .... */



## 2.4、运算符

### 2.4.1、基本的运算符

大多数运算符与其它编程语言无异，这里做一点简单的介绍，就不过多赘述了。

- 算术运算符：（+，-，*，/）加减乘除、（%）取余、（++）自增、（--）自减。
- 关系运算符：（>）大于、（<）小于、（>=）大于等于、（<=）小于等于。
- 赋值运算符：=、+=、-=、*=、/=、%=。
- 逻辑运算符：与或非（&&，||，！）。
- 三目（元）运算符：也叫条件运算符。写法：`条件表达式 ? 为真返回值 : 为假返回值`



下面详细介绍一下比较运算符。

### 2.4.2、比较运算符

比较运算符中，除了相等的比较运算，还有一个全等的比较运算。

- 使用 **==** 来做相等运算
  - 当使用 == 来比较两个值时，如果值的类型不同，则会自动进行类型转换，将其转换为相同的类型，然后在比较。
- 使用 **!=** 来做不相等运算
  - 不相等用来判断两个值是否不相等，如果不相等返回true，否则返回false，不相等也会对变量进行自动的类型转换，如果转换后相等它也会返回false。
- 使用 **===** 来做全等运算
  - 用来判断两个值是否全等，它和相等类似，不同的是它不会做自动的类型转换，如果两个值的类型不同，直接返回false。
- 使用 **!==** 来做不全等运算
  - 用来判断两个值是否不全等，它和不等类似，不同的是它不会做自动的类型转换，如果两个值的类型不同，直接返回true。



> 总结

相等运算符和全等运算符的区别

- 使用相等运算符，如果值得类型不同，会自动进行类型转换，然后再比较。
- 使用全等运算符，如果值得类型不同，不会自动进行类型转换，直接返回 false。
- 通常使用全等运算符（=== 或  !\==），如果不想程序出现一些诡异的现象就尽可能不要用 ==运算符。



### 2.4.3、typeof运算符

使用 typeof 运算符，可以用来检查一个变量的数据类型。

```JavaScript
<script>
    //typeof 运算符,判断一个值的数据类型
    console.log("日志：123 的数据类型是" + typeof 123);
    console.log("日志：\"123\" 的数据类型是" + typeof "123");
    console.log("日志：true 的数据类型是" + typeof true);
    console.log("日志：null 的数据类型是" + typeof null);
    console.log("日志：undefined 的数据类型是" + typeof undefined);
</script>
```

运行结果：

![typeof运算符](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240614155340935.png)



## 2.5、数据类型

### 2.5.1、六种数据类型

JavaScript中一共有5种基本数据类型：

- 字符串型（String）
- 数值型（Number）
- 布尔型（Boolean）
- undefined型（Undefined）
- null型（Null）

上述5种之外的类型，都为 Object类型。



### 2.5.2、String

在JavaScript中，表示字符串既可以用 单引号，也可以用双引号。

在字符串中使用一些符号需要用转义字符。

> 以下是ES6的新特性
>
> 模板字符串：
>
> ​		模板字符串（template string）是增强版的字符串，用反引号（`）标识，特点：
>
> - 字符串中可以出现换行符
>
> ```JavaScript
> let str = `hello
> Java
> Script`
> console.log(str);
> ```
>
> ![image-20240614210434552](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240614210434552.png)
>
> - 可以使用 ${xxx} 形式输出变量
>
> ~~~JavaScript
> let name = "Aip";
> let msg = `hello ${name}`;
> console.log(msg);
> ~~~
>
> ![hello_Aip](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240614210646277.png)



### 2.5.3、Number

JavaScript不区分整数和小数，所有的数值都用 Number 类型来表示。但是Number表示的数字大小是有限的，如果超过了这个范围，则会返回 ±Infinity。

- 最大值：+1.7976931348623157e+308
- 最小值：-1.7976931348623157e+308
- 0以上的最小值：5e-324



> 特殊的数字

- Infinity：正无穷
- -Infinity：负无穷
- NaN：非法数字（Not A Number）
  - NaN与所有的数值做全等判断，都不相等，包括它自己（NaN===NaN）。
  - 通过 `isNaN() `方法来判断一个值是不是一个非法数字。



> 进制

- 二进制：0b 开头表示二进制
- 八进制：0 开头表示八进制
- 十六进制：0x 开头表示十六进制

但是，并不是所有的浏览器都支持

> 注意：
>
> 使用typeof检查一个Number类型的数据时（包括NaN 和 Infinity），会返回"number"。



### 2.5.4、Undefined

Undefined 类型只有一个值，即特殊的 undefined。

在使用 var 声明变量但未对其加以初始化时，这个变量的值就是 undefined。

> 注意：使用typeof对没有初始化和没有声明的变量，会返回“undefined”。



### 2.5.5、Null

Null 类型是第二个只有一个值的数据类型，这个特殊的值是 null。

undefined值实际上是由null值衍生出来的，所以如果比较undefined和null是否相等，会返回true。

> 注意：从语义上看null表示的是一个空的对象，所以使用typeof检查null会返回一个Object。



## 2.6、类型转换

强制类型转换指将一个数据类型强制转换为其它的数据类型。一般是指，将其它的数据类型转换为String、Number、Boolean。



### 2.6.1、转String类型

 将其它数值转换为字符串有三种方式：toString()、String()、 拼串。

---

方式一：调用被转换数据类型的toString()方法，该方法不会影响到原变量，它会将转换的结果返回，但是注意：null和undefined这两个值没有toString()方法，如果调用它们的方法，会报错。

~~~JavaScript
var a = 123;
a = a.toString();
console.log(a);
console.log(typeof a);
~~~



方式二：调用String()函数，并将被转换的数据作为参数传递给函数，使用String()函数做强制类型转换时，对于Number和Boolean实际上就是调用的toString()方法，但是对于null和undefined，就不会调用toString()方法，它会将 null 直接转换为 “null”，将 undefined 直接转换为 “undefined”。

~~~JavaScript
var a = 123;
a = String(a);
console.log(a);
console.log(typeof a);

var b = undefined;
b = String(b);
console.log(b);
console.log(typeof b);

var c = null;
c = String(c);
console.log(c);
console.log(typeof c);
~~~



方式三：为任意的数据类型 +""

~~~JavaScript
var a = 123;
a = a + "";
console.log(a);
console.log(typeof a);
~~~



### 2.6.2、转Number类型

有三个函数可以把非数值转换为数值：Number()、parseInt() 和parseFloat()。Number()可以用来转换任意类型的数据，而后两者只能用于转换字符串。parseInt()只会将字符串转换为整数，而parseFloat()可以将字符串转换为浮点数。

---

方式一：使用Number()函数

- 字符串 --> 数字
  - 如果是纯数字的字符串，则直接将其转换为数字。
  - 如果字符串中有非数字的内容，则转换为NaN。
  - 如果字符串是一个空串或者是一个全是空格的字符串，则转换为0。
- 布尔 --> 数字
  - true 转成 1。
  - false 转成 0。
- null --> 数字
  - null 转成 0。
- undefined --> 数字
  - undefined 转成 NaN。



方式二：这种方式专门用来对付字符串，parseInt() 把一个字符串转换为一个整数。

~~~JavaScript
var a = "123";
a = parseInt(a);
console.log(a);
console.log(typeof a);
~~~



方式三：这种方式专门用来对付字符串，parseFloat() 把一个字符串转换为一个浮点数。

~~~JavaScript
var a = "123.456";
a = parseFloat(a);
console.log(a);
console.log(typeof a);
~~~



> 注意：如果对非String使用parseInt()或parseFloat()，它会先将其转换为String然后在操作。



### 2.6.3、转Boolean类型

将其它的数据类型转换为Boolean，只能使用Boolean()函数。

- 数字 —> 布尔
  - 除了0和NaN，其余的都是true
- 字符串 —> 布尔
  - 除了空串，其余的都是true
- null和undefined都会转换为false
- 对象会转换为true



## 2.7、条件语句

通过其他编程语言的学习中得知，有条件语句 **if...else**，也有选择语句 **switch...case**。这类语句在JavaScript中也适用。



### 2.7.1、if...else

if…else语句是一种最基本的控制语句，分为三种形式。

- 第一种形式：单 if 语句

```JavaScript
if (age > 18){
    console.log("成年人");
}
```



- 第二种情况：if...else

```JavaScript
if (gender === "男"){
    alert("你是男的");
}else {
    alert("你是女的");
}
```



- 第三章情况：if...else if

```JavaScript
if (salary > 0 && salary < 5000){
    alert("菜鸟");
}else if (salary >= 5000 && salary < 10000){
    alert("学徒");
}else {
    alert("大神");
}
```



### 2.7.2、switch...case

switch...case也就是我们说的选择语句。
