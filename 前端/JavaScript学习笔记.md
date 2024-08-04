> 参考：
>
> - [学习JavaScript这一篇就够了](http://t.csdnimg.cn/kmXTM)
> - [【狂神说Java】JavaScript最新教程通俗易懂](https://www.bilibili.com/video/BV1JJ41177di/?share_source=copy_web&vd_source=f8340c4026115fdc3a178ce1879c37ab)

# 1、JavaScript简介	

JavaScript 是一种轻量级、解释型或即时编译型的编程语言，具有函数优先的特性。它是Web开发的核心技术之一，与HTML和CSS共同构成了现代Web应用的三大基石。其源代码在发往客户端之前不需要结果编译，而是将文本格式的字符代码发送给浏览器，由浏览器解释运行。



## 1.1、JavaScript的使用

<font color = 'red'>需要注意的是JavaScript 是严格区分大小写的。</font>



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



## 2.7、流程控制

学过任意一门语言，对流程控制应该都十分熟悉，不做过多的介绍。



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

switch...case也就是我们说的选择语句。更适用于多条分支使用同一条语句的情况。

语法：

~~~JavaScript
switch (语句) {
    case 表达式1:
        语句...
    case 表达式2:
        语句...
    default:
        语句...
}
~~~



demo：

```JavaScript
//switch...case
switch (gender){
    case "男":
        console.log("你是男的");
        break;

    case "女":
        console.log("你是女的");
        break;

    default:
        console.log("性别未知");
}
```



### 2.7.3、for循环

语法：

~~~JavaScript
for(初始化表达式 ; 条件表达式 ; 更新表达式){
    语句...
}
~~~



demo：

```JavaScript
for (let i = 0; i < 10; i++){
    console.log(i);
}
```



### 2.7.4、while和do...while

> while

语法：

~~~JavaScript
while(条件表达式){
    语句...
}
~~~



demo：

```JavaScript
//while循环
let i = 0;
while (i < 10){
    console.log(i);
    i++;
}
```



> do...while

do...while 会在循环的尾部而不是顶部检查表达式的值，因此，do…while循环会至少执行一次。

语法：

~~~JavaScript
do{
    语句...
}while(条件表达式);
~~~



demo：

```JavaScript
let j = 0;
do {
    console.log(j);
    j++;
} while (j < 10);
```



## 2.8、对象

Object类型，我们也称为一个对象，是JavaScript中的引用数据类型。它是一种复合值，它将很多值聚合到一起，可以通过名字访问这些值。对象也可以看做是属性的无序集合，每个属性都是一个名/值对。对象除了可以创建自有属性，还可以通过从一个名为原型的对象那里继承属性。除了字符串、数字、true、false、null和undefined之外，JavaScript中的值都是对象。



### 2.8.1、创建对象

创建对象的两种方式：



- 第一种：

~~~JavaScript
let emp = new Object();
emp.name = "John";
emp.age = 30;
emp.designation = "Software Engineer";

console.log(emp);
~~~



- 第二种：

~~~JavaScript
let emp1 = {
    name: "John",
    age: 30,
    designation: "Software Engineer" //最后一行不用逗号
};
console.log(emp1);
~~~



### 2.8.2、访问属性

两种访问属性的方式：

- 第一种：`对象.属性名`

```JavaScript
console.log(emp1.name);
```



- 第二种：`对象['属性名']`

```JavaScript
console.log(emp1['age']);
```



### 2.8.3、删除属性

删除对象的属性可以使用delete关键字：`delete 对象.属性名`

```JavaScript
//删除属性
delete emp1.designation;
console.log(emp1);
```



### 2.8.4、对象赋值

如果需要给对象重新赋值，直接使用 = 进行赋值

```JavaScript
//赋值
emp1.name = "Mike";
console.log(emp1);
```

![image-20240620134601074](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240620134601074.png)

> 如果赋值的属性是当前对象没有的属性，那么赋值之后会自动将该属性加入对象中。



### 2.8.5、判断属性是否存在

使用 `属性名 in 对象` 可以用来判断对象中是否存在该属性

```JavaScript
//判断属性是否存在
if(emp1.hasOwnProperty('name')){
    console.log('name属性存在');
}

//使用in操作符
if('name' in emp1){
    console.log('name属性存在');
}
```



### 2.8.6、遍历对象

枚举遍历对象中的属性，可以使用for … in语句循环，对象中有几个属性，循环体就会执行几次。

语法格式：

~~~JavaScript
for (var 变量 in 对象) {

}
~~~

演示demo：

```JavaScript
let emp1 = {
    name: "John",
    age: 30,
    designation: "Software Engineer" //最后一行不用逗号
};
//使用for...in循环
for(let key in emp1){
    console.log(key + " : " + emp1[key]);
}
```



## 2.9、函数

### 2.9.1、概述

JavaScript中函数相当于Java中的方法。这里要注意的是JavaScript中的函数也是一个对象，使用typeof检查一个函数对象时，会返回function。



### 2.9.2、创建函数

创建函数有两种方式：

使用 **函数声明** 来创建一个函数（比较常用）语法如下：

~~~JavaScript
function 函数名([形参1,形参2,...,形参N]) {
    语句...
}
~~~

demo：

```JavaScript
function fun() {
    console.log('这是一个函数');
}
```



---

使用 **函数表达式** 来创建一个匿名函数（比较常用）

**相当于Java中的匿名内部类**。语法如下：

~~~JavaScript
var 函数名  = function([形参1,形参2,...,形参N]) {
    语句....
}
~~~

demo：

```JavaScript
let fun3 = function() {
    console.log('这是一个函数');
}
```



---

使用 **函数对象** 来创建一个函数（几乎不用）语法如下：

~~~javascript
var 函数名 = new Function("执行语句");
~~~

demo：

```JavaScript
let fun2 = new Function('console.log("这是一个函数");');
```



### 2.9.3、调用函数

无参函数调用：

```JavaScript
let fun3 = function() {
    console.log('这是一个函数');
}

fun();
```



---

有参函数调用：

```JavaScript
let fun4 = function(a, b) {
    console.log(a + b);
}
fun4(1, 2);
```



### 2.9.4、函数的参数

- JavaScript中，调用函数可以传多个参数，解析器不会检查实参数量。但是多余的实参不会被赋值，如果实参的数量少于形参的数量，则没有对应实参的形参将是undefined。
- 调用函数时，解析器不会检查实参的类型，所以要注意，是否有可能会接收到非法的参数，如果有可能，则需要对参数进行类型的检查，函数的实参可以是任意的数据类型



### 2.9.5、函数返回值

可以使用 return 来设置函数的返回值，return后的值将会作为函数的执行结果返回，可以定义一个变量，来接收该结果。



> return语句后不跟任何值就相当于返回一个undefined，如果函数中不写return，则也会返回undefined，return后可以跟任意类型的值。

```JavaScript
let fun4 = function(a, b) {
    return a + b;
}
let result = fun4(1, 2);
console.log(result);
```



### 2.9.6、嵌套函数

在函数中声明的函数就是嵌套函数，嵌套函数只能在当前函数中可以访问，在当前函数外无法访问。

```JavaScript
function fun5() {
    let fun6 = function() {
        console.log('这是一个嵌套函数');
    }
    fun6();
}
fun5();
```



### 2.9.7、立即执行函数

函数定义完，立即被调用，这种函数叫做立即执行函数，立即执行函数往往只会执行一次。

```JavaScript
(function() {
    console.log('这是一个立即执行函数');
})();
```



### 2.9.8、this对象

解析器在调用函数每次都会向函数内部传递进一个隐含的参数，这个隐含的参数就是this，this指向的是一个对象，这个对象我们称为函数执行的上下文对象，根据函数的调用方式的不同，this会指向不同的对象。

- 以函数的形式调用时，this永远都是window

- 以方法的形式调用时，this就是调用方法的那个对象

- 当以构造函数的形式调用时，this就是新创建的那个对象


```JavaScript
//创建一个全局变量name
var name = "全局变量name";

//创建一个函数
function fun() {
    console.log(this.name);
}
fun(); //全局变量name

//创建一个对象
var obj = {
    name: "孙悟空",
    sayName: fun
};
//我们希望调用obj.sayName()时可以输出obj的名字而不是全局变量name的名字
obj.sayName(); //孙悟空
```



## 2.10、作用域

在JS中一共有两种作用域：

- 全局作用域
- 函数作用域



### 2.10.1、全局作用域

- 直接编写在script标签中的JavaScript代码，都在全局作用域，JavaScript只有一个全局作用域。
- 全局作用域在页面打开时创建，在页面关闭时销毁。
- 在全局作用域中有一个全局对象window，它代表的是一个浏览器的窗口，它由浏览器创建，我们可以直接使用。
- 在全局作用域中：
  - 创建的变量都会作为window对象的属性保存。
  - 创建的函数都会作为window对象的方法保存。
- 全局作用域中的变量都是全局变量，在页面的任意的部分都可以访问的到。



### 2.10.2、函数作用域

- 调用函数时创建函数作用域，函数执行完毕以后，函数作用域销毁。

- 每调用一次函数就会创建一个新的函数作用域，它们之间是互相独立的。
- 在函数作用域中可以访问到全局作用域的变量，在全局作用域中无法访问到函数作用域的变量。
- 在函数中要访问全局变量可以使用window对象，也就是this。



### 2.10.3、声明提前

- 变量的声明提前：使用var关键字声明的变量，会在所有的代码执行之前被声明（但是不会赋值），但是如果声明变量时不使用var关键字，则变量不会被声明提前。
  - 在写JavaScript的代码时，最好将所有变量都写在最前面。

```JavaScript
//声明提前
function test() {
    var x = 'x' + y;
    console.log(x); //xundefined，因为y没有被赋值(定义)
    var y = 'y';
}
test();
```

![image-20240626110236089](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240626110236089.png)



- 函数的声明提前：使用函数声明形式创建的函数 function 函数名(){} ，它会在所有的代码执行之前就被创建，所以我们可以在函数声明前来调用函数。使用函数表达式创建的函数，不会被声明提前，所以不能在声明前调用。



### 2.10.4、作用域链

多个上下级关系的作用域形成的链，它的方向是从下向上的(从内到外)，查找变量时就是沿着作用域链来查找的。

查找一个变量的查找规则：

1. 在当前作用域下的执行上下文中查找对应的属性，如果有直接返回，否则进入2。

2. 在上一级作用域的执行上下文中查找对应的属性，如果有直接返回，否则进入3。
3. 再次执行2的相同操作，直到全局作用域，如果还找不到就抛出找不到的ReferenceError异常。



> 例如：
>
> 当在函数作用域操作一个变量时，它会先在自身作用域中寻找，如果有就直接使用，如果没有则向上一级作用域中寻找，直到找到全局作用域，如果全局作用域中依然没有找到，则会报错ReferenceError。



## 2.11、对象进阶

### 2.11.1、构造多个对象

在JavaScript中如何快速的构造多个对象？

~~~JavaScript
var person1 = {
    name: "Aip",
    age: 18
};

var person2 = {
    name: "***",
    age: 19
};

var person3 = {
    name: "***",
    age: 20
};

console.log(person1);
console.log(person2);
console.log(person3);
~~~

如上代码所示，每构造一个对象，就要写一堆重复的代码，这样敲出来的代码非常冗余臃肿，且不优雅。

> 想要代码变得优雅，我们可以提取代码中相似的功能，构建通用对象或者写一些语法糖。

在当前案例中，通用属性有：姓名、年龄。

---

#### 2.11.1.1、工厂方法创建对象

```JavaScript
'use strict'
function createPerson(name, age) {
    const obj = new Object();
    obj.name = name;
    obj.age = age;
    return obj;
}
for (let i = 1; i <= 20; i++) {
    const person = createPerson("person" + i, 18);
    console.log(person);
}
```

![image-20240630174830994](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240630174830994.png)

如此，我们就批量生成了20个对象。

但是使用工厂模式所创建的对象类型都是 **Object**。

```JavaScript
for (let i = 1; i <= 20; i++) {
    const person = createPerson("person" + i, 18);
    console.log(typeof person);
}
```

![image-20240630175620358](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240630175620358.png)



---

要是既想实现创建对象的功能，同时又能明确所创建出来的对象类型，这就用到了构造函数，每一个构造函数你都可以理解为一个类别，用构造函数所创建的对象我们也成为类的实例。



#### 2.11.1.2、构造函数创建对象

使用构造函数创建对象的关键就是 `new` 关键字。在构造函数中，不用创建对象，也不需要设置返回值。这和 Java 中的构造方法是一样的。

```JavaScript
function Person(name, age) {
    this.name = name;
    this.age = age;
    
    //设置一个方法
    this.sayHello = function(){
        console.log(this.name + ': Hello');
    }
}
const p1 = new Person("张三", 18);
const p2 = new Person("李四", 19);
console.log(p1);
console.log(p2);
```

![image-20240630180706189](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240630180706189.png)



使用同一个构造函数创建的对象，我们称为一类对象，也将一个构造函数称为一个类。我们将通过一个构造函数创建的对象，称为是该类的实例对象。

可以使用 instanceof 运算符检查一个对象是否是一个类的实例，它返回true或false。

```JavaScript
console.log(p1.constructor === Person);
console.log(p2 instanceof Person);
```

![image-20240630181219398](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240630181219398.png)



### 2.11.2、原型对象

上一小节学习了使用构造函数来创建对象，但是存在浪费内存的问题。如果我们创建1000个对象，那就会存在1000个相同的方法。

虽然你可以将方法提出了，写成一个全局函数。但是在多人协作开发中并不是一个好的处理方法。如果别人也定义了一个和你同名的全局函数，那么项目合并时就会导致一系列问题，污染全局作用域。

<font color = 'red'>公共的属性写在构造方法里，公共的方法写在原型对象里。</font>



> 有没有一种方法，我只在Person这个类的全局对象中添加一个函数，然后在类中引用？

```JavaScript
//原型
function Student(name,age) {
    this.name = name;
    this.age = age;
}
Student.prototype.sayHello = function () {
    console.log(this.name + ": hello");
}

const s1 = new Student("张三", 18);
s1.sayHello();
```





> 怎么理解原型？

- JavaScript中规定，每个构造函数都有一个 `prototype` 属性，这个属性指向一个对象，而这个对象称为 **原型对象**（显示原型）。
- 原型对象就相当于一个公共的区域，我们可以把一些实例对象中共有的方法，直接定义在 **原型对象** 上，这样所有该对象的实例就可以共享这些方法。
- <font color = 'red'> 构造函数和 **原型对象** 中的 this 都指向 实例化的对象</font>



以后我们创建构造函数时，可以将这些对象共有的属性和方法，统一添加到构造函数的原型对象中，这样不用分别为每一个对象添加，也不会影响到全局作用域，就可以使每个对象都具有这些属性和方法了。



### 2.11.3、constructor

每个原型对象里面都有一个 `constructor`(构造器) 属性。

<font color = 'red'>作用：指向该原对象的构造函数。</font>

```JavaScript
//constructor
//指向原型对象的构造函数
console.log(Student.prototype);
console.log(Student.prototype.constructor === Student);
//指向实例对象的构造器
console.log(s3.constructor);
```

![image-20240703171156049](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240703171156049.png)



> 使用场景：
>
> 如果有多个公共方法，那就要写多个原型对象。可以采用赋值的形式放入对象中。
>
> ```JavaScript
> Student1.prototype = {
>     read:function (){
>         console.log("读书");
>     },
>     write:function (){
>         console.log("写作业");
>     }
> }
> console.log(Student1.prototype);
> console.log(Student1.prototype.constructor);
> ```
>
> ![image-20240703172002247](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240703172002247.png)
>
> 
>
> 因为是赋值，所以会覆盖构造函数原型对象原来的内容，这样修改后的原型对象的构造器就不再指向当前构造函数了。（没有 `constructor` 属性了）
>
> 可以调用该属性，重新指向原构造函数。
>
> ```JavaScript
> Student1.prototype = {
>     constructor:Student1,
>     read:function (){
>         console.log("读书");
>     },
>     write:function (){
>         console.log("写作业");
>     }
> }
> console.log(Student1.prototype);
> console.log(Student1.prototype.constructor);
> ```
>
> ![image-20240703172101340](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240703172101340.png)



### 2.11.4、对象原型

对象都有一个属性 `__proto__`（隐式原型），该属性指向构造函数的**原型对象**，对象之所以可以使用原型对象属性和方法，就是因为对象中有 `__proto__` 对象的存在。

当函数以构造函数的形式调用  `prototype` 时，它所创建的对象中都会有一个隐含的属性`[[prototype]]`，指向该构造函数的原型对象，我们可以通过`__proto__`（隐式原型）来访问该属性。

![原型关系](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/%E5%8E%9F%E5%9E%8B%E5%85%B3%E7%B3%BB.png)



### 2.11.5、原型继承

先看以下代码

```JavaScript
const Human = {
    eyes: 2,
    ear: 2,
    eat: function () {
        console.log("吃饭");
    },
    look: function () {
        console.log("观察")
    }
}

function Man(){
}
const man = new Man();
console.log(man);
```

![image-20240706183943608](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240706183943608.png)



> 现在我们需要让男人的构造器继承人类对象，如何继承呢

```JavaScript
const Human = {
    eyes: 2,
    ear: 2,
    eat: function () {
        console.log("吃饭");
    },
    look: function () {
        console.log("观察")
    }
}

function Man(){
}
//使用原型对象指向 Human
Man.prototype = Human;
//同时还要将构造函数指向自己
Man.prototype.constructor = Man;
const man = new Man();
console.log(man);
```

![image-20240706184224053](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240706184224053.png)

<font color = 'red'>这么做的弊端：如果我在原型上添加了一个方法，比如男人独有的方法，会导致女人也继承上。因为男人和女人的原型对象都是指向的 Human。</font>

> 解决方法：使用构造函数 new一个对象，这样结构一样，却不是同一个对象

```JavaScript
function Human() {
    this.eyes = 2;
    this.ear = 2;
}
Human.prototype = {
    constructor: Human,
    eat: function () {
        console.log("吃饭");
    },
    look: function () {
        console.log("观察")
    }
}

function Man(){}
Man.prototype =new Human();
Man.prototype.constructor = Man;
const man = new Man();
console.log(man);

function Woman(){}
Woman.prototype =new Human();
Woman.prototype.constructor = Woman;
Woman.prototype.baby = function () {
    console.log("生孩子");
}

const woman = new Woman();
console.log(woman);
```

![image-20240708180029811](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240708180029811.png)



### 2.11.6、原型链

原型链又称隐式原型链，与之前提到的作用域链类似，它的作用是查找对象的属性（方法）。当访问一个对象的属性时，先在自身属性中查找，找到返回。如果没有找到，再沿着 `__proto__` 这条链向上查找，找到返回，如果最终没找到，返回`undefined`。

![image-20240709180936387](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240709180936387.png)

<font color = 'red'>Object对象的原型没有原型，所有Object的原型为 null。</font>

![202407091833](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202407091833.png)

![image-20240711180303145](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240711180303145.png)

参考文档：[彻底搞懂JavaScript原型和原型链](https://www.cnblogs.com/easy1996/p/18208431)

> 这种继承的写法是非常不舒服的，所以再ES6的新特性中加入了 Class 继承





# 3、JavaScript常用对象



## 3.1、String

### 3.1.1、概述

在JS中为我们提供了三个包装类，通过这三个包装类可以将基本数据类型的数据转换为对象。

- String()：可以将基本数据类型字符串转换为String对象
- Number()：可以将基本数据类型的数字转换为Number对象
- Boolean()：可以将基本数据类型的布尔值转换为Boolean对象

但是注意：我们在实际应用中不会使用基本数据类型的对象，如果使用基本数据类型的对象，在做一些比较时可能会带来一些不可预期的结果，在这一章节中，我们重点介绍String()对象的属性和方法。



### 3.1.2、字符串属性

> constructor：返回创建字符串对象的原型函数

~~~JavaScript
var str = "Hello,JavaScript!";
console.log(str.constructor);
~~~

![image-20240618215634001](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240618215634001.png)



> length：获取字符串的长度

```JavaScript
let str = "Hello,JavaScript!";
console.log(str.length);
```

![字符串长度](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240618220018205.png)



### 3.1.3、字符串方法

| 方法名        | 描述                                                         |
| ------------- | ------------------------------------------------------------ |
| charAt()      | 可以根据索引获取指定位置的字符                               |
| charCodeAt()  | 获取指定位置字符的字符编码（Unicode编码）                    |
| concat()      | 拼接字符串                                                   |
| indexOf()     | 检索一个字符串中是否含有指定内容，如果字符串中含有该内容，则会返回其第一次出现的索引，如果没有找到指定的内容，则返回-1，可以指定一个第二个参数，指定开始查找的位置 |
| lastIndexOf() | 方法使用同上，**区别是 indexOf() 是从前往后找，该方法是从后往前找** |
| slice()       | 截取字符串，通过新串返回。**参数可以是负数**，负数从后截取   |
| substring()   | 截取字符串，通过新串返回。**参数不可是负数**                 |
| ~~substr()~~  | 截取字符串，第一个参数为起始位置，第二个参数为截取长度       |
| split()       | 将一个字符串拆分为一个数组，需要一个字符串作为参数，将会根据该字符串去拆分数组 |
| toUpperCase() | 将字符串转换成大写                                           |
| toLowerCase() | 将字符串转换成小写                                           |



> 使用详解

- charAt、charCodeAt：获取字符即字符编码

  - ~~~JavaScript
    let str = "Hello,JavaScript!";
    //charAt
    console.log(str.charAt(0));
    
    //charCodeAt
    console.log(str.charCodeAt(0));
    ~~~

    ![image-20240619134854552](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240619134854552.png)

- concat：拼接串

  - ```JavaScript
    let str = "Hello,JavaScript!";
    console.log(str.concat(" World"));
    ```

    ![image-20240619135118528](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240619135118528.png)

- indexOf、lastIndexOf()：获取字符串索引

  - ```JavaScript
    let str = "Hello,JavaScript!";
    //indexOf
    console.log(str.indexOf("Java"));
    console.log(str.indexOf("a", 8));
    
    //lastIndexOf
    console.log(str.lastIndexOf("a"));
    console.log(str.lastIndexOf("a", 10));
    console.log(str.lastIndexOf("a", 5));
    ```

    ![image-20240619135500951](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240619135500951.png)

- slice：截取串

  - ```JavaScript
    let str = "Hello,JavaScript!";
    //slice
    console.log(str.slice(1, 5));
    console.log(str.slice(0, -1));
    ```

    ![image-20240619135817676](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240619135817676.png)

- substring、substr：截取串

  - ```JavaScript
    let str = "Hello,JavaScript!";
    //substring
    console.log(str.substring(0, 5));
    
    //substr,过时的方法
    console.log(str.substr(0, 5));
    ```

    ![image-20240619140058513](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240619140058513.png)

- split：根据字符串拆分成数组

  - ```JavaScript
    let str = "Hello,JavaScript!";
    //split
    console.log(str.split(","));
    ```

    ![image-20240619140403925](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240619140403925.png)

- toUpperCase()、toLowerCase()：转换大小写

  - ```JavaScript
    let str = "Hello,JavaScript!";
    //toUpperCase、toLowerCase
    console.log(str.toUpperCase());
    console.log(str.toLowerCase());
    ```

    ![image-20240619140456040](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240619140456040.png)







## 3.2、数组

数组也是对象的一种，使用typeof检查一个数组对象时，会返回object。

<font color = 'red'>在JavaScript中是支持数组可以是不同的元素。</font>



### 3.2.1、创建数组

> 使用对象创建

~~~JavaScript
var arr = new Array();
arr[0] = 1;
arr[1] = "2";
arr[2] = 3;
arr[3] = "hello";
arr[4] = 5;
arr[5] = "Aip";
~~~



> 使用字面量创建

~~~JavaScript
var arr = [1, "2", 3, "hello", 5, "Aip"];
~~~



### 3.2.2、数组属性

> constructor：返回创建数组对象的原型函数

```JavaScript
let arr = [1,2,3,4,5];
console.log(arr.constructor);
```

![image-20240618181719814](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240618181719814.png)



> length：数组的长度

```JavaScript
let arr = [1,2,3,4,5];
console.log(arr.length);
```

![image-20240618181827998](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240618181827998.png)



### 3.2.3、数组常用方法

| 方法名    | 描述                                                         |
| --------- | ------------------------------------------------------------ |
| push()    | 向数组的末尾添加一个或多个元素，并返回数组的新的长度         |
| pop()     | 删除数组的最后一个元素，将被删除的元素作为返回值             |
| slice()   | 截取数组的一部分，不改变原数组，返回一个新数组               |
| unshift() | 向数组头部添加一个或多个元素，并返回新的数组长度             |
| shift()   | 删除数组开头的第一个元素，将被删除的元素作为返回值           |
| forEach() | 遍历数组                                                     |
| splice()  | 从原数组中删除（也可以不删）数组中的指定元素，将被删除的元素作为返回值，并可以在该位置插入新的元素 |
| concat()  | 拼接数组，返回新数组                                         |
| join()    | 拼接数组，并转换为字符串返回，如果不指定参数作为拼接符，则默认使用逗号作为拼接符 |
| reverse() | 反转数组，会修改原先的数组                                   |
| sort()    | 默认按照Unicode编码进行排序，影响原数组                      |



> 使用详解

- push、pop：从数组末尾添加和删除元素，插入元素返回值为数组长度，删除元素返回值为被删元素

```JavaScript
//push、pop
let result = arr.push(6,10);
console.log(arr);
console.log(result);

let result1 = arr.pop();
console.log(arr);
console.log(result1);
```

![image-20240619154849353](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240619154849353.png)



- slice：截取字符串
  - 第一个参数：截取开始的位置的索引，包含开始索引
  - 第二个参数：截取结束的位置的索引，不包含结束索引，第二个参数可以省略不写，此时会截取从开始索引往后的所有元素

> 注意：索引可以传递一个负值，如果传递一个负值，则从后往前计算，-1代表倒数第一个，-2代表倒数第二个。

```JavaScript
let arr1 = ["刘备", "关羽", "张飞", "孙权", "曹操"];
let result2 = arr1.slice(1, 4);
console.log(result2);
result2 = arr1.slice(3);
console.log(result2);
result2 = arr1.slice(1, -2);
console.log(result2);
```

![image-20240619160033803](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240619160033803.png)



- unshift、shift：从数组头部插入或删除元素，插入元素返回值为数组长度，删除元素返回值为被删元素

```JavaScript
let arr = [1,2,3,4,5];
//unshift、shift
arr.unshift(0);
console.log(arr);

arr.shift();
console.log(arr);
```

![image-20240619160232679](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240619160232679.png)



- forEach：
  - 该方法需要一个函数作为参数，像这种函数，由我们创建但是不由我们调用的，我们称为回调函数。数组中有几个元素函数就会执行几次，每次执行时，浏览器会将遍历到的元素，以实参的形式传递进来，我们可以来定义形参，来读取这些内容，浏览器会在回调函数中传递三个参数：
    - 第一个参数：就是当前正在遍历的元素
    - 第二个参数：就是当前正在遍历的元素的索引
    - 第三个参数：就是正在遍历的数组
    - 可以允许只有第一个参数

> 这个方法只支持IE8以上的浏览器，如果需要兼容IE8，则不要使用forEach()，还是使用for循环来遍历数组。

```JavaScript
let arr1 = ["刘备", "关羽", "张飞", "孙权", "曹操"];
//forEach
arr1.forEach((item, index) => {
    console.log("当前元素是：" + item + " ，当前元素的索引：" + index);
});
arr1.forEach((item, index, obj) => {
    console.log("当前元素是：" + item + " ，当前元素的索引：" + index + " ，当前元素所属的对象：" + obj);
});
```

只有前两个参数的效果图：

![image-20240619161534068](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240619161534068.png)



三个参数的效果图

![image-20240619161441206](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240619161441206.png)



- splice：删除数组中指定位置的元素（可以不删除，也可以在指定位置插入元素），返回值是被删除的元素
  - 第一个参数：表示开始位置的索引
  - 第二个参数：表示要删除的元素数量
  - 第三个参数及以后参数：可以传递一些新的元素，这些元素将会自动插入到开始位置索引前边

```JavaScript
let arr1 = ["刘备", "关羽", "张飞", "孙权", "曹操"];
//splice
let result3 = arr1.splice(3, 2);
console.log(arr1);
console.log(result3);
result3 = arr1.splice(1, 0, "赵云", "马超", "黄忠");
console.log(arr1);
console.log(result3);
```

![image-20240619170239446](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240619170239446.png)



- concat：拼接数组

```JavaScript
let arr2 = ["刘备", "关羽"];
let arr3 = ["孙权", "孙策"];
let arr4 = ["曹操", "曹植"];
let result4 = arr2.concat(arr3, arr4,"孙悟空","猪八戒");
console.log(result4);
```

![image-20240619170805910](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240619170805910.png)



- join：将数组转换成字符串，并传入一个参数做为指定拼接符，如果不知道默认为逗号

```JavaScript
let arr1 = ["刘备", "关羽", "张飞", "孙权", "曹操"];
let result5 = arr1.join("-");
let result6 = arr1.join();
console.log(result5);
console.log(result6);
```

![image-20240619171120044](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240619171120044.png)



- reverse：反转数组

~~~JavaScript
let arr5 = [1, 2, 3, 4, 5];
arr5.reverse();
console.log(arr5);
~~~

![image-20240619171303186](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240619171303186.png)



- sort：排序，默认按照Unicode编码进行排序

```JavaScript
let arr6 = ["b", "c", "a"];
arr6.sort();
console.log(arr6);
```

![image-20240619171502670](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240619171502670.png)

> 对于纯数字的数组，使用sort()排序时，也会按照Unicode编码来排序，所以对数字进排序时，可能会得到错误的结果。

```JavaScript
let arr7 = [1, 3, 2, 11, 5, 6];
arr7.sort();
console.log(arr7);
```

![image-20240619171616618](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240619171616618.png)

我们可以自己来指定排序的规则，我们可以在sort()添加一个回调函数，来指定排序规则，回调函数中需要定义两个形参，浏览器将会分别使用数组中的元素作为实参去调用回调函数，使用哪个元素调用不确定，但是肯定的是在数组中a一定在b前边，浏览器会根据回调函数的返回值来决定元素的顺序，如下：

- 如果返回一个大于0的值，则元素会交换位置
- 如果返回一个小于0的值，则元素位置不变
- 如果返回一个等于0的值，则认为两个元素相等，也不交换位置



经过上边的规则，我们可以总结下：

- 如果需要升序排列，则返回 a-b
- 如果需要降序排列，则返回 b-a

```JavaScript
let arr7 = [1, 3, 2, 11, 5, 6];
arr7.sort(function (a, b) {
    return a - b;
});
console.log(arr7);
```

![image-20240619172239473](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240619172239473.png)



## 3.3、函数对象

### 3.3.1、this指向

- 以函数形式调用时，this永远都是window
- 以方法的形式调用时，this是调用方法的对象
- 以构造函数的形式调用时，this是新创建的那个对象
- 使用call和apply调用时，this是传入的那个指定对象



```JavaScript
//函数形式调用
function getAge() {
    let now = new Date().getFullYear();
    return now-this.bitrh; //Not a Number
}
//对象形式调用
const person = {
    name: 'Aip',
    bitrh: 2001,
    age: getAge //23
}
//相当于
const person = {
    name: 'Aip',
    bitrh: 2001,
    age: function() {
        let now = new Date().getFullYear();
        return now-this.bitrh;
    }
}
```

![image-20240626164457116](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240626164457116.png)



### 3.3.2、call()和apply()

call()和apply()这两个方法都是函数对象的方法，需要通过函数对象来调用。

作用是： **可以控制 this 的指向。**

当对函数调用call()和apply()都会调用函数执行，在调用call()和apply()可以将一个对象指定为第一个参数，此时这个对象将会成为函数执行时的this，call()方法可以将实参在对象之后依次传递，apply()方法需要将实参封装到一个数组中统一传递，如下演示：

---

> apply

以 3.3.1中的代码为例，如果直接调用 *getAge* 方法，会因为当前 this 指向 window 的原因，抛出 NaN。而通过 *apply* 方法可以将 this 指向 person 对象。

```JavaScript
getAge.apply(person, []);//this指向person，参数为空
```



![image-20240626170724179](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240626170724179.png)



> call

```JavaScript
function getNum(a, b) {
    return a + b;
}

const person = {
    name: 'Aip',
    bitrh: 2001,
    age: getAge,
    count:getNum
}
//call
getNum.call(person, 1, 2); //this指向person，参数为1,2
```

![image-20240626171538747](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240626171538747.png)



> call和apply的不同点

- call：从第二个参数开始，实参将会依次传递
- apply：从第二个参数开始，需要制定一个实参数组进行参数传递



### 3.3.3、arguments参数

当实参多余形参时，使用arguments 参数，可以获取传入的所有实参。arguments是一个类数组对象，它也可以通过索引来操作数据，也可以获取长度，在调用函数时，我们所传递的实参都会在arguments中保存，比如：arguments.length 可以用来获取实参的长度，我们即使不定义形参，也可以通过arguments来使用实参，只不过比较麻烦，例如：

- arguments[0]：表示第一个实参

- arguments[1]：表示第二个实参
- …

它里边有一个属性叫做callee，这个属性对应一个函数对象，就是当前正在指向的函数的对象。

```JavaScript
//arguments对象
function fun7(a, b) {
    console.log(arguments[0]);
    console.log(arguments[1]);
    console.log(arguments[2]);

    console.log(arguments.length);
    //当前指向的函数对象是
    console.log(arguments.callee);
    console.log(arguments.callee === fun7);
    
    //看看arguments对象
    console.log(arguments);
}
fun7(1, 2, 3, 4, 5);
```

![image-20240622204726931](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240622204726931.png)



## 3.4、Date对象

在JavaScript中使用Date对象来表示一个时间，如果直接使用构造函数创建一个Date对象，则会封装为当前代码执行的时间。

```JavaScript
var date = new Date();
console.log(date);

//date对象的方法
//1.获取年月日
console.log("年月日")
console.log(date.getFullYear()); //获取年
console.log(date.getMonth() + 1); //获取月（0-11），0代表1月，11代表12月
console.log(date.getDate()); //获取日

//2.获取时分秒
console.log("时分秒")
console.log(date.getHours()); //获取时
console.log(date.getMinutes()); //获取分
console.log(date.getSeconds()); //获取秒

//3.获取星期，0代表星期天，1代表星期一
console.log("星期")
console.log(date.getDay()); //获取星期（0-6）

//4.获取毫秒
console.log("毫秒")
console.log(date.getMilliseconds());


//5.获取时间戳
console.log("时间戳")
console.log(date.getTime()); //获取时间戳（从1970年1月1日至今的毫秒数）

//6.设置年月日，设置时分秒，时间戳同理
date.setFullYear(2022);
date.setMonth(1);
date.setDate(1);
console.log(date);

//7.日期格式化函数（更简洁）
function formatDate(date) {
    return date.toLocaleString();
}
var formattedDate4 = formatDate(date);
console.log(formattedDate4);
```

![image-20240627153023456](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240627153023456.png)



# 4、:star:JavaScript BOM

## 4.1、什么是BOM

BOM：浏览器对象模型，使 JavaScript 有能力与浏览器"对话"。

BOM 可以使我们通过JS来操作浏览器，在 BOM 中为我们提供了一组对象，用来完成对浏览器的操作，常见的 BOM 对象如下：

- Window：代表浏览器的窗口
- Navigator： 代表当前浏览器的信息
- Location：代表当前页面的URL，通过Location可以获取地址栏信息，或者操作浏览器跳转页面
- History：代表浏览器的历史记录，可以通过该对象来操作浏览器的历史记录，由于隐私原因，该对象不能获取到具体的历史记录，只能操作浏览器向前或向后翻页，而且该操作只在当次访问时有效
- Screen：代表用户的屏幕的信息



## 4.2、Window对象

Window对象主要用作弹出框，JavaScript 有三种类型的弹出框：警告框、确认框、提示框（输入框）。

<font color = 'red'>这三种框均可以不带 window 前缀</font>



### 4.2.1、警告框

如果要确保信息传递给用户，通常会使用警告框。当警告框弹出时，用户将需要单击“确定”来继续。

~~~JavaScript
window.alert("这是一个警告框");
~~~

![image-20240716143045344](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240716143045344.png)



### 4.2.2、确认框

如果您希望用户验证或接受某个东西，则通常使用“确认”框。

当确认框弹出时，用户将不得不单击“确定”或“取消”来继续进行。

如果用户单击“确定”，该框返回 true。如果用户单击“取消”，该框返回 false。

```JavaScript
window.confirm("这是一个确认框");
```

![image-20240716143233820](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240716143233820.png)



使用示例：

~~~JavaScript
let isDelete = confirm("是否删除？");
if (isDelete) {
    //TODO 删除操作
}else {
    //TODO 取消删除操作
}
~~~



### 4.2.3、提示框

如果您希望用户在进入页面前输入值，通常会使用提示框。

当提示框弹出时，用户将不得不输入值后单击“确定”或点击“取消”来继续进行。

如果用户单击“确定”，该框返回输入值。如果用户单击“取消”，该框返回 NULL。

```JavaScript
window.prompt("请输入你的姓名","默认值");
```

![image-20240716144749748](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240716144749748.png)



使用示例：

```JavaScript
let userName= prompt("请输入你的姓名");
if (userName != null)  {
    //TODO 用户名相关方法
}else {
    alert("非法用户名");
}
```



### 4.2.4、定时事件

**JavaScript 可以在时间间隔内执行，这就是所谓的定时事件（ Timing Events）。**

window 对象允许以指定的时间间隔执行代码，这些时间间隔称为定时事件。

通过 JavaScript 使用的有两个关键的方法：

- setTimeout(function, milliseconds)：延时器，在等待指定的毫秒数后执行函数。
  - 第一个参数是要执行的函数。
  - 第二个参数指示执行之前的毫秒数。
- setInterval(function, milliseconds)：定时器，插入时间间隔，重复执行。
  - 第一个参数是要执行的函数。
  - 第二个参数每个执行之间的时间间隔的长度。



## 4.3、Navigator对象

Navigator 封装了当前浏览器的信息，通过该对象可以来识别不同的浏览器，由于历史原因，Navigator对象中的大部分属性都已经不能帮助我们识别浏览器了。

![image-20240716164215192](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240716164215192.png)



一般我们只会使用userAgent来判断浏览器的信息，userAgent是一个字符串，这个字符串中包含有用来描述浏览器信息的内容，不同的浏览器会有不同的userAgent，如下代码：

```JavaScript
console.log(navigator.userAgent);
```

![image-20240716165924935](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240716165924935.png)

```JavaScript
var ua = navigator.userAgent;
if (/firefox/i.test(ua)) {
    alert("你是火狐浏览器");
} else if (/chrome/i.test(ua)) {
    alert("你是谷歌浏览器");
} else if (/msie/i.test(ua)) {
    alert("你是IE5-IE10浏览器");
} else if ("ActiveXObject" in window) {
    alert("你是IE11浏览器");
}
```



> 大多数时候不会使用 `navigator` 对象，因为会被修改



## 4.4、Screen对象

Screen 对象包含有关客户端显示屏幕的信息。

每个 Window 对象的 screen 属性都引用一个 Screen 对象。Screen 对象中存放着有关显示浏览器屏幕的信息。JavaScript 程序将利用这些信息来优化它们的输出，以达到用户的显示要求。例如，一个程序可以根据显示器的尺寸选择使用大图像还是使用小图像，它还可以根据显示器的颜色深度选择使用 16 位色还是使用 8 位色的图形。另外，JavaScript 程序还能根据有关屏幕尺寸的信息将新的浏览器窗口定位在屏幕中间。



4.4.1、Screen对象属性

| 属性                 | 描述                                         |
| -------------------- | -------------------------------------------- |
| availHeight          | 返回显示屏幕的高度 (除 Windows 任务栏之外)。 |
| availWidth           | 返回显示屏幕的宽度 (除 Windows 任务栏之外)。 |
| bufferDepth          | 设置或返回调色板的比特深度。                 |
| colorDepth           | 返回目标设备或缓冲器上的调色板的比特深度。   |
| deviceXDPI           | 返回显示屏幕的每英寸水平点数。               |
| deviceYDPI           | 返回显示屏幕的每英寸垂直点数。               |
| fontSmoothingEnabled | 返回用户是否在显示控制面板中启用了字体平滑。 |
| height               | 返回显示屏幕的高度。                         |
| width                | 返回显示器屏幕的宽度。                       |
| logicalXDPI          | 返回显示屏幕每英寸的水平方向的常规点数。     |
| logicalYDPI          | 返回显示屏幕每英寸的垂直方向的常规点数。     |
| pixelDepth           | 返回显示屏幕的颜色分辨率（比特每像素）。     |
| updateInterval       | 设置或返回屏幕的刷新率。                     |



## 4.5、:star:Location对象

Location对象中封装了浏览器的地址栏的信息，如果直接打印location，则可以获取到地址栏的信息（当前页面的完整路径）

### 4.5.1、常用属性

```JavaScript
//Location常用属性
console.log(location);              //location对象
console.log(location.href);         //当前地址的全路径
console.log(location.origin);       //当前地址的来源
console.log(location.protocol);     //当前地址的协议
console.log(location.host);         //当前地址的主机
console.log(location.hostname);     //当前地址的主机名
console.log(location.port);         //当前地址的端口号
console.log(location.pathname);     //当前地址的路径
console.log(location.search);       //当前地址的?后边的参数部分

location.href = "https://www.baidu.com"; //修改地址
```

![image-20240722160342386](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240722160342386.png)



### 4.5.2、常用方法

```JavaScript
//用来跳转到其它的页面，作用和直接修改location一样
location.assign('https://www.bilibili.com');

//用来跳转到其它的页面，作用和直接修改location一样，但是不会在历史记录中留下原网页的记录
location.replace('https://www.baidu.com');

//用于重新加载当前页面，作用和刷新按钮一样，如果在方法中传递一个true，作为参数，则会强制清空缓存刷新页面
location.reload(true);
```



## 4.6、History对象



### 4.6.1、常用属性

```JavaScript
console.log(history);           //输出history对象
console.log(history.length);    //可以获取到当成访问的链接数量
```

![image-20240722165759652](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240722165759652.png)



### 4.6.2、常用方法

```JavaScript
history.back();    //返回上一页
history.forward(); //前往下一页
history.go(1);     //前进或后退指定的页面数
```

关于go方法的用法：

- 1：表示向前跳转一个页面，相当于forward()
- 2：表示向前跳转两个页面
- -1：表示向后跳转一个页面，相当于back()
- -2：表示向后跳转两个页面



# 5、:star:JavaScript DOM

## 5.1、DOM概述

DOM（**D**ocument **O**bject **M**odel）：当网页被加载时，浏览器会创建页面的文档对象模型。

HTML **DOM** 模型被结构化为 **对象树** ：

![202407241226](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202407241226.png)

浏览器网页就是一个DOM 树形结构，通过这个对象模型，JavaScript 获得创建动态 HTML 的所有力量：

- JavaScript 能改变页面中的所有 HTML 元素

- JavaScript 能改变页面中的所有 HTML 属性
- JavaScript 能改变页面中的所有 CSS 样式
- JavaScript 能删除已有的 HTML 元素和属性
- JavaScript 能添加新的 HTML 元素和属性
- JavaScript 能对页面中所有已有的 HTML 事件作出反应
- JavaScript 能在页面中创建新的 HTML 事件



> 一句话总结就是：HTML DOM 是关于如何获取、更改、添加或删除 HTML 元素的标准。



## 5.2、DOM节点

### 5.2.1、概述

节点是构成网页最基本的组成部分，网页中每一个部分都可以称为一个节点，如上图所示，每一个框框都是一个节点。

常用节点分为四类：

- <font color = 'red'>**文档节点：代表整个HTML文档，网页中的所有节点都是它的子节点。**</font>
  - document对象作为window对象的属性存在的，我们不用获取可以直接使用。
  - 通过该对象我们可以在整个文档访问内查找节点对象，并可以通过该对象创建各种节点对象。
- <font color = 'red'>**元素节点：那些元素标签，这是最常用的一个节点。**</font>
  - 浏览器会将页面中所有的标签都转换为一个元素节点， 我们可以通过document的方法来获取元素节点。
- <font color = 'red'>**属性节点：标签的属性。**</font>
  - 这里要注意的是属性节点并非是元素节点的子节点，而是元素节点的一部分。可以通过元素节点来获取指定的属性节点。
- <font color = 'red'>**文本节点：HTML标签外的任何文本内容，任意非HTML的文本都是文本节点。**</font>
  - 它包括可以字面解释的纯文本内容。文本节点一般是作为元素节点的子节点存在的。获取文本节点时，一般先要获取元素节点，在通过元素节点获取文本节点。



![image-20240724124118884](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240724124118884.png)



### 5.2.2、节点属性

|          | 节点名    | 节点类型 | 节点值   |
| -------- | --------- | -------- | -------- |
| 文档节点 | #document | 9        | null     |
| 元素节点 | 标签名    | 1        | null     |
| 属性节点 | 属性名    | 2        | 属性值   |
| 文本节点 | #text     | 3        | 文本内容 |



## 5.3、DOM操作

文档对象代表您的网页,，如果您希望访问 HTML 页面中的任何元素，那么您总是从访问 document 对象开始。



### 5.3.1、查找 HTML 元素

| 方法                              | 描述                        |
| --------------------------------- | --------------------------- |
| document.getElementById()         | 通过元素 id 查找元素        |
| document.getElementsByTagName()   | 通过 标签名 查找元素        |
| document.getElementsByClassName() | 通过 类名 查找元素          |
| document.querySelector()          | 通过 CSS选择器 选择一个元素 |
| document.querySelectorAll()       | 通过 CSS选择器 选择多个元素 |

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>dom</title>
</head>
<body>
<a href="https://limestart.cn/">青柠起始页</a>
<p class="paragraph">这是一个段落</p>
<button id="btn">开始</button>
<ul class="list">
    <li>1</li>
    <li>2</li>
    <li>3</li>
    <li>4</li>
</ul>


<script>
    document.getElementById("btn")
        .onclick = function () {alert("按钮被点击了")}

    console.log(document.getElementsByTagName('a'));

    console.log(document.getElementsByClassName("paragraph"));

    console.log(document.querySelector(".paragraph"));

    console.log(document.querySelectorAll(".list li"));
</script>

</body>
</html>
```

![image-20240724143711817](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240724143711817.png)



### 5.3.2、获取和改变 HTML 的值

| 方法                                        | 描述                                                  |
| ------------------------------------------- | ----------------------------------------------------- |
| 元素节点.innerText = *new text content*     | 改变元素的文本内容。去掉 = 后面的就是获取值           |
| 元素节点.innerHTML = *new html content*     | 改变元素的标签内容。去掉 = 后面的就是获取值           |
| 元素节点.属性 = *new value*                 | 改变 HTML 元素的属性值。去掉 = 后面的就是获取值       |
| 元素节点.getAttribute(*attribute*)          | 获取 HTML 元素的属性值。                              |
| 元素节点.setAttribute(*attribute*, *value*) | 改变 HTML 元素的属性值。                              |
| 元素节点.style.样式 = *new style*           | 改变 HTML 元素的行内样式值。去掉 = 后面的就是获取样式 |

>新增一个按钮

```HTML
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>dom获取修改值</title>
</head>
<body>
<div id="box">
<!--    在div中插入一个按钮-->
</div>

<script>
    const box = document.getElementById("box");
    box.innerHTML = "<button>开始</button>";
</script>
</body>
</html>
```

![image-20240724152007220](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240724152007220.png)



> 改变元素文本

```HTML
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>dom获取修改值</title>
</head>
<body>
<div id="box">
<!--    改变下面元素文本-->
    <h1 id="h1">这是一个标题</h1>
</div>

<script>
    const h1 = document.getElementById("h1");
    h1.innerText = "这个一个JavaScript标题";
    console.log(h1);
</script>
</body>
</html>
```

![image-20240724152434005](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240724152434005.png)



> 修改样式

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>dom获取修改值</title>
</head>
<body>
<p id="p" style="font-size: ">这是一段测试文本</p>

<script>
    const p = document.getElementById("p");
    p.style.color = 'red';
    p.style.fontSize = '50px';
</script>
</body>
</html>
```

![image-20240724153850157](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240724153850157.png)



### 5.3.3、查找父子节点

| 方法                            | 描述                                                         |
| ------------------------------- | ------------------------------------------------------------ |
| 元素节点.parentNode             | 返回元素的父节点。                                           |
| 元素节点.parentElement          | 返回元素的父元素。                                           |
| 元素节点.childNodes             | 返回元素的一个子节点的数组（包含空白文本Text节点）。         |
| 元素节点.children               | 返回元素的一个子元素的集合（不包含空白文本Text节点）。       |
| 元素节点.firstChild             | 返回元素的第一个子节点（包含空白文本Text节点）。             |
| 元素节点.firstElementChild      | 返回元素的第一个子元素（不包含空白文本Text节点）。           |
| 元素节点.lastChild              | 返回元素的最后一个子节点（包含空白文本Text节点）。           |
| 元素节点.lastElementChild       | 返回元素的最后一个子元素（不包含空白文本Text节点）。         |
| 元素节点.previousSibling        | 返回某个元素紧接之前节点（包含空白文本Text节点）。           |
| 元素节点.previousElementSibling | 返回指定元素的前一个兄弟元素（相同节点树层中的前一个元素节点）。 |
| 元素节点.nextSibling            | 返回某个元素紧接之后节点（包含空白文本Text节点）。           |
| 元素节点.nextElementSibling     | 返回指定元素的后一个兄弟元素（相同节点树层中的下一个元素节点）。 |

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>dom</title>
</head>
<body>
<div id="box">
    <a href="https://limestart.cn/">青柠起始页</a>
    <p class="paragraph">这是一个段落</p>
    <button id="btn">开始</button>
    <ul class="list">
        <li>1</li>
        <li>2</li>
        <li>3</li>
        <li>4</li>
    </ul>
</div>



<script>
    console.log(document.getElementById('box').children);
</script>

</body>
</html>
```

![image-20240724145312215](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240724145312215.png)



### 5.3.4、修改 HTML 元素

| 方法                        | 描述                               |
| --------------------------- | ---------------------------------- |
| document.createElement( )   | 创建 HTML 元素节点。               |
| document.createAttribute( ) | 创建 HTML 属性节点。               |
| document.createTextNode( )  | 创建 HTML 文本节点。               |
| 元素节点.removeChild( )     | 删除 HTML 元素。需要通过父节点操作 |
| 元素节点.appendChild( )     | 添加 HTML 元素。                   |
| 元素节点.replaceChild( )    | 替换 HTML 元素。                   |
| 元素节点.insertBefore( )    | 在指定的子节点前面插入新的子节点。 |



> 删除节点
>
> 先获取父节点，再通过父节点删除自己。

```HTML
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>dom增删改查</title>
</head>
<body>
<div id="box">
    <button id="btn">按钮</button>
</div>

<script>
    const box = document.getElementById("box");
    box.removeChild(document.getElementById("btn"));
    
</script>
</body>
</html>
```



> 新增节点

```HTML
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>dom增删改查</title>
</head>
<body>
<div id="box">
    <button id="btn">按钮</button>
</div>

<script>
    const box = document.getElementById("box");
    const btn = document.getElementById("btn");

    var p = document.createElement("p");
    var text = document.createTextNode('test段落');
    p.appendChild(text);
    box.appendChild(p);

    box.removeChild(btn);
</script>
</body>
</html>
```

![image-20240724180532443](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240724180532443.png)



> 创建节点属性

```HTML
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>dom增删改查</title>
</head>
<body>
<div id="box">
</div>

<script>
    const box = document.getElementById("box");
    //创建一个节点
    var p = document.createElement("p");
    //直接通过节点属性给id赋值
    // p.id = "p1";
    
    //或者通过创建属性对象
    //创建一个属性事件
    var id = document.createAttribute("id");
    //赋属性值
    id.value = "p1";
    //赋予属性
    p.setAttributeNode(id);
</script>
</body>
</html>
```

![image-20240724182244925](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240724182244925.png)

http://t.csdnimg.cn/shVKS



```HTML
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>插入节点</title>
</head>
<body>
<div id="box">
    <p id="text">我是p标签</p>
    想要在这里插入 script 标签
</div>

<script>
    var box = document.getElementById("box");
    var p1 = document.createElement('p');

    p1.innerHTML = "我是新插入的p标签";
    p1.id = 'text1';
    box.appendChild(p1);

    box.insertBefore(p1,box.firstChild);

    var myScript = document.createElement('script');
    //通过setAttribute来设置标签的属性，第一个参数为属性名，第二个为属性值。
    myScript.setAttribute('type','text/javascript');
    box.appendChild(myScript);

</script>
</body>
</html>
```



## 5.4、DOM文档事件

HTML事件可以触发浏览器中的行为，比方说当用户点击某个 HTML 元素时启动一段 JavaScript。



### 5.4.1、窗口事件

由窗口触发该事件 (同样适用于 \<body> 标签)：

| 属性      | 描述                                                         |
| --------- | ------------------------------------------------------------ |
| onblur    | 当窗口失去焦点时运行脚本                                     |
| onfocus   | 当窗口获得焦点时运行脚本                                     |
| onload    | 当文档加载之后运行脚本                                       |
| onresize  | 当调整窗口大小时运行脚本                                     |
| onstorage | 当 Web Storage 区域更新时（存储空间中的数据发生变化时）运行脚本 |



> 失去焦点获得焦点案例
>
> 其他属性写法类似

```HTML
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>窗口事件demo</title>
</head>
<body>
<script>
    //窗口失去焦点时，修改title
window.onblur = function () {
    document.title = "(っ °Д °;)っ人呢？";
}
window.onfocus = function () {
    document.title = "(づ￣ 3￣)づ欢迎回来";
    //延迟2秒后换回原title
    setTimeout(function ()
    {document.title = "焦点事件demo"}, 2000);
};
</script>
</body>
</html>
```



### 5.4.2、表单事件

表单事件在HTML表单中触发 (适用于所有 HTML 元素，但该HTML元素需在form表单内)：

| 属性      | 描述                     |
| --------- | ------------------------ |
| onblur    | 当元素失去焦点时运行     |
| onfocus   | 当元素获得焦点时运行     |
| onchange  | 当元素改变时运行         |
| oninput   | 当元素获得用户输入时运行 |
| oninvalid | 当元素无效时运行         |
| onselect  | 当选取元素时运行         |
| onsubmit  | 当提交表单时运行         |



```HTML
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>表单事件</title>
</head>
<body>
<form action="/login" id="userForm">
    <p>
        <span>username:</span>
        <input type = "text" id="username">
    </p>

    <p>
        <span>gender:</span>
        <input type="radio" name="gender" value="man">男
        <input type="radio" name="gender" value="woman">女
    </p>

    <p>
        <input type="submit" value="提交">
    </p>

</form>


<script>
    var textInput = document.getElementById("username");

    //失去焦点
    textInput.onblur = function (){
        console.log(textInput.value);
        this.style.backgroundColor = 'green';
    }
    //获取焦点
    textInput.onfocus = function (){
        this.style.backgroundColor = 'red';
    }

    //失去焦点后内容发生改变
    textInput.onchange = function (){
        console.log(this.value);
    }

    //内容发生改变，无需在失去焦点后
    textInput.oninput = function (){
        console.log(this.value);
    }

    //选取元素 onselect
    document.getElementsByTagName("input")[0].onselect = function (){
        console.log("你选取了：" + this.value);
    }

    //提交事件
    var userForm = document.getElementById("userForm");

    userForm.onsubmit = function (){
        console.log("表单提交");
        //阻止表单提交，你不写它会跳转请求
        return false;
    }

</script>
</body>
</html>
```



### 5.4.3、键鼠事件

> 键盘事件

通过键盘触发事件：

| 属性       | 描述                   |
| ---------- | ---------------------- |
| onkeydown  | 当按下按键时运行       |
| onkeyup    | 当松开按键时运行       |
| onkeypress | 当按下并松开按键时运行 |



> 鼠标事件

通过鼠标触发事件：

| 属性         | 描述                                       |
| ------------ | ------------------------------------------ |
| onclick      | 单击鼠标时运行                             |
| ondblclick   | 双击鼠标时运行                             |
| onmousedown  | 按下鼠标按钮时运行                         |
| onmouseup    | 松开鼠标按钮时运行                         |
| onmousemove  | 光标指针移动时运行                         |
| onmouseover  | 光标指针移至元素之上时运行，不可以阻止冒泡 |
| onmouseout   | 光标指针移出元素时运行，不可以阻止冒泡     |
| onmouseenter | 光标指针移至元素之上时运行，可以阻止冒泡   |
| onmouseleave | 光标指针移出元素时运行，可以阻止冒泡       |
| onmousewheel | 转动鼠标滚轮时运行                         |
| onscroll     | 滚动元素的滚动条时运行                     |



### 5.4.4、事件冒泡

事件冒泡就是事件的向上传导，当子元素上的事件被触发时，其父元素的相同事件也会被触发。在开发中大部分情况冒泡都是有用的，如果不希望发生事件冒泡可以通过事件对象来取消冒泡。



> 创建两个div，叠放在一起，分别绑定单击事件，点击最里边的div，会触发两个div的单击事件

~~~html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <style>
        #div1 {
            width: 200px;
            height: 200px;
            background: pink;
        }

        #div2 {
            width: 100px;
            height: 100px;
            background: coral;
        }
    </style>
</head>
<body>
<div id="div1">
    我是DIV1
    <div id="div2">
        我是DIV2
    </div>
</div>

<!-- 在这里写JavaScript代码，因为JavaScript是由上到下执行的 -->
<script>
    var div1 = document.getElementById("div1");
    var div2 = document.getElementById("div2");

    // 为div1绑定单击事件
    div1.onclick = function () {
        console.log("div1 的单击事件触发了！");
    };

    // 为div2绑定单击事件
    div2.onclick = function () {
        console.log("div2 的单击事件触发了！");
    };
</script>
</body>
</html>
~~~



> 取消事件冒泡

~~~javascript
// 取消事件冒泡
    function stopBubble(event) {
        // 如果提供了事件对象，则这是一个非IE浏览器
        if (event && event.stopPropagation) {
            // 因此它支持W3C的stopPropagation()方法
            event.stopPropagation();
        } else {
            // 否则，我们需要使用IE的方式来取消事件冒泡
            window.event.cancelBubble = true;
        }
    }
~~~







# 6、JavaScript高级语法

## 6.1、JSON

JSON：JavaScript Object Notation（JavaScript 对象标记法），它是一种存储和交换数据的语法。

当数据在浏览器与服务器之间进行交换时，这些数据只能是文本，JSON 属于文本并且我们能够把任何 JavaScript 对象转换为 JSON，然后将 JSON 发送到服务器。我们也能把从服务器接收到的任何 JSON 转换为 JavaScript 对象。以这样的方式，我们能够把数据作为 JavaScript 对象来处理，无需复杂的解析和转译。



JSON数据图：

![image-20240627161701233](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240627161701233.png)





### 6.1.1、JSON语法

在 JSON 中，每一个数据项，都是由一个键值对组成的。

- 键必须是字符串，且由双引号包围
- 而值必须是以下数据类型之一：
  - 字符串（在 JSON 中，字符串值必须由双引号编写）
  - 数字（必须是整型或者浮点型）
  - 对象（JSON 对象）
  - 数组
  - 布尔
  - null



JSON 的值不可以是以下数据类型：

- 函数
- 日期
- undefined



> 格式

- 对象用**花括号** {}
- 数组用**方括号** []
- 键值对用**冒号** ：



### 6.1.2、JSON转JS对象

JSON.parse()：可以将以JSON字符串转换为JS对象，它需要一个JSON字符串作为参数，会将该字符串转换为JS对象并返回

```JavaScript
//JSON转对象
let jsonStr = '{"name":"Aip","age":23,"isAdmin":true,"skills":["HTML","CSS","JS"]}';

const obj2 = JSON.parse(jsonStr);

console.log(obj2);  // { name: 'Aip', age: 23, isAdmin: true, skills: [ 'HTML', 'CSS', 'JS' ] }
```

![image-20240627161935857](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240627161935857.png)



### 6.1.3、JS对象转JSON

JSON.stringify()：可以将一个JS对象转换为JSON字符串，需要一个js对象作为参数，会返回一个JSON字符串。

```JavaScript
const obj = {
    name: 'Aip',
    age: 23,
    isAdmin: true,
    skills: ['HTML', 'CSS', 'JS']
}

const json = JSON.stringify(obj);

console.log(json);
```

![image-20240627161701233](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240627161701233.png)



### 6.1.4、JS对象和JSON的区别

```JavaScript
//js是对象
const obj = {
    name: 'Aip',
    age: 23,
    isAdmin: true,
    skills: ['HTML', 'CSS', 'JS']
}

//json数据本质是一个字符串
let json = '{"name":"Aip","age":23,"isAdmin":true,"skills":["HTML","CSS","JS"]}';
```



## 6.2、jQuery

[jquery 在线手册 (runoob.com)](https://www.runoob.com/manual/jquery/)

[jQuery 教程 (w3school.com.cn)](https://www.w3school.com.cn/jquery/index.asp)

### 6.2.1、初识jQuery

jQuery 是一个 JavaScript 库，极大地简化了 JavaScript 编程。

<font color = 'red'>jQuery的核心语法：</font>

- $定义 jQuery
- selector：选择器
- action() ：你需要的操作

~~~JavaScript
jQuery(selector).action()
=>
$(selector).action()
~~~

下面来一段实例

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>jQuery入门</title>
    <script type="text/javascript" src="lib/jquery-3.7.1.js"></script>
</head>
<body>
<div id="box"></div>
<script>
    $('#box').html('Hello World!')
</script>
</body>
</html>
```

![image-20240730171808480](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240730171808480.png)



### 6.2.2、jQuery选择器

> 基本的选择器

- id选择器：$('#id')
- 元素选择器：$('元素名')
- 类选择器：$('.类名')
- 通用选择器：$('*')



### 6.2.3、jQuery事件

| 方法         | 描述                                                   |
| :----------- | :----------------------------------------------------- |
| blur()       | 失去焦点时触发                                         |
| change()     | 内容发生改变时触发                                     |
| focus()      | 获取焦点时触发                                         |
| focusin()    | 获取焦点时触发，可以在父元素上检测子元素获取焦点的情况 |
| focusout()   | 失去焦点时触发，可以在父元素上检测子元素失去焦点的情况 |
| keydown()    | 键盘按键按下时触发                                     |
| keypress()   | 键盘按键 **按住** 时触发                               |
| keyup()      | 键盘按键松开时触发                                     |
| click()      | 鼠标单击时触发                                         |
| dblclick()   | 鼠标双击时触发                                         |
| mousedown()  | 鼠标左键按下时触发                                     |
| mouseup()    | 鼠标左键松开时触发                                     |
| mouseenter() | 光标进入目标元素时触发，不会事件冒泡                   |
| mouseover()  | 光标进入目标元素时触发，事件冒泡                       |
| mouseleave() | 光标离开目标元素时触发，不会事件冒泡                   |
| mouseout()   | 光标离开目标元素时触发，事件冒泡                       |
| hover()      | 同时绑定 `mouseenter`和 `mouseleave`事件               |
| mousemove()  | 光标移动时触发                                         |
| resize()     | 元素大小改变时触发                                     |
| scroll()     | 元素滚动时触发                                         |
| select()     | 文本选中时触发                                         |
| submit()     | 表单提交时触发                                         |
| ready()      | html文档加载完毕时触发                                 |



> 测试demo：通过按钮的click事件，隐藏 p 标签

```HTML
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>jQuery事件</title>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.7.1/jquery.js"></script>
</head>
<body>
    <div id="div1">
        <p id="p1">这是一个会隐藏的段落</p>
        <button id="btn1">隐藏</button>
        <button id="btn2">显形</button>
    </div>

    <script>
        $('#btn1').click(function (){$('p').hide()});
        $('#btn2').click(function (){$('p').show()});
    </script>
</body>
</html>
```



### 6.2.4、操作DOM

#### 内部插入

| 方法        | 描述                                                 |
| ----------- | ---------------------------------------------------- |
| text()      | 设置或返回所选元素的文本内容，读写一体               |
| html()      | 设置或返回所选元素的内容（包括 HTML 标记），读写一体 |
| val()       | 设置或返回表单字段的值，读写一体                     |
| attr()      | 设置/改变属性值                                      |
| append()    | 向当前匹配的所有元素内部的最后面插入指定内容         |
| appendTo()  | 将指定的内容追加到当前匹配的所有元素的最后面         |
| prepend()   | 向当前匹配的所有元素内部的最前面插入指定内容         |
| prependTo() | 将指定的内容追加到当前匹配的所有元素的最前面         |



```html
<ul></ul>

<p></p>

<form>
    <input type="text" name="username" value="123">
</form>
<script>
    // text() 用于设置和获取标签中的文本内容
    $('p').text("Hello World");

    // html() 用于设置和获取标签体的内容
    const li = "<li>使用html方法设置标签</li>";
    $('ul').html(li);

    // val() 用于设置和获取表单元素的值
    alert($("input:text").val());  // 读取值
    $("input:text").val("123132"); // 设置值

	// attr() 用于设置和获取属性的值
    alert($('input').attr('name')); // 读取属性值
    $("input:text").attr("value","21213215"); // 设置属性值
</script>
```



```html
<ul>
    <li>列表1</li>
    <li>列表2</li>
</ul>
<script>
    const temp ='<li>新增列表项</li>';
    // $('ul').append(temp); //在最后面追加
    // $('ul').prepend(temp); //在最前面插入
    // $(temp).appendTo($('ul')); //在最后面追加
    $(temp).prependTo($('ul')); //在最前面插入
</script>
```



#### 外部插入

| 方法           | 描述                                                |
| -------------- | --------------------------------------------------- |
| after()        | 在...之后，在匹配元素后面插入指定内容，作为兄弟节点 |
| insertAfter()  | 同上，参数位置放在前面，匹配元素放在后面            |
| before()       | 在...之前，在匹配元素前面插入指定内容，作为兄弟节点 |
| insertBefore() | 同上，参数在前，匹配元素在后                        |



```html
<div>div</div>

<script>
    const after = "<p>after</p>";
    // $("div").after(after); //在div后面插入after

    const before = "<p>before</p>";
    // $("div").before(before); //在div后面插入after

    const insertAfter = "<p>insertAfter</p>";
    $(insertAfter).insertAfter($("div")); //在div后面插入after

    const insertBefore = "<p>insertBefore</p>";
    $(insertBefore).insertBefore($("div")); //在div后面插入after
</script>
```

![image-20240802171003391](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240802171003391.png)

---

![image-20240802170943778](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240802170943778.png)



#### 移除

| 方法     | 描述                                                 |
| -------- | ---------------------------------------------------- |
| empty()  | 删除匹配元素的所有子元素                             |
| remove() | 删除所有匹配元素，同时移除元素上的事件及 jQuery 数据 |

```HTML
<ul>
    <li>列表1</li>
    <li>列表2</li>
    <li>列表3</li>
    <li>列表4</li>
    <a href="https://limestart.cn/">最好用的导航页</a>
</ul>
<script>
    $("ul>a").remove();

    $("ul").empty();
</script>
```



#### 替换

| 方法          | 描述                                                         |
| ------------- | ------------------------------------------------------------ |
| replaceWith() | 用提供的内容替换集合中所有匹配的元素并且返回被删除元素的集合。 |
| replaceAll()  | 功能类似，但是 内容 和 匹配元素 相反。                       |

```HTML
<ul>
    <li>列表1</li>
    <li>列表2</li>
    <a href="https://limestart.cn/">最好用的导航页</a>
</ul>
<script>
    $("ul>li").replaceWith("<p>将 li 标签替换为 p 标签</p>");

    $("<li>将 a 标签替换为 li 标签</li>").replaceAll($("ul>a"));
</script>
```

![image-20240802173619846](C:/Users/93988/AppData/Roaming/Typora/typora-user-images/image-20240802173619846.png)



#### 拷贝

| 方法    | 描述                                                       |
| ------- | ---------------------------------------------------------- |
| clone() | 深拷贝，参数为布尔值，表示是否会复制元素上的事件处理函数。 |

```html
<ul>
    <li>列表1</li>
    <li>列表2</li>
    <a href="https://limestart.cn/">最好用的导航页</a>
</ul>
<script>
    const cloneUl = $("ul").clone();

    $("body").append(cloneUl);
</script>
```

![image-20240802174314298](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240802174314298.png)



#### 遍历

这些返回值都是一个集合，需要获取具体的数据需要加上下标。

| 方法       | 描述                                                         |
| ---------- | ------------------------------------------------------------ |
| parent()   | 获取集合中匹配元素的父元素，可以提供一个可选的选择器来进行筛选 |
| children() | 获取集合中匹配元素的子元素，可以提供一个可选的选择器来进行筛选 |
| prev()     | 获取匹配元素相邻的前一个兄弟元素，可以提供一个可选的选择器来进行筛选 |
| prevAll()  | 获得匹配元素的所有前面的兄弟元素                             |
| next()     | 获取匹配元素紧邻的后一个兄弟元素                             |
| nextAll()  | 获得匹配元素的所有后面的兄弟元素                             |
| siblings() | 获得匹配元素的所有兄弟元素                                   |

> perent()

```HTML
<ul>
    <li>列表1</li>
    <li>列表2</li>
    <a id="navigationPage" href="https://limestart.cn/">最好用的导航页</a>
    <li>列表3</li>
    <li>列表4</li>
</ul>
<script>
    console.log($("#navigationPage").parent());
</script>
```



![image-20240802221745099](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240802221745099.png)



> children()

```HTML
<ul>
    <li>列表1</li>
    <li>列表2</li>
    <a id="navigationPage" href="https://limestart.cn/">最好用的导航页</a>
    <li>列表3</li>
    <li>列表4</li>
</ul>
<script>
    const children = $("ul").children();
    for (let i = 0; i < children.length; i++) {
        console.log(children[i]);
    }
</script>
```

![image-20240802222222495](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240802222222495.png)



其他方法使用方式大差不差，不妨自行编写试一试。



## 6.3 Ajax

### 6.3.1、什么是Ajax

AJAX = 异步 JavaScript 和 XML（Asynchronous JavaScript and XML）。

简短地说，在不重载整个网页的情况下，AJAX 通过后台加载数据，并在网页上进行显示。



### 6.3.2、jQuery 和 Ajax

jQuery是对原生 JavaScript的一个封装实现，所以在 jQuery 中也封装了一些 Ajax 的方法。

通过 jQuery AJAX 方法，您能够使用 HTTP Get 和 HTTP Post 从远程服务器上请求文本、HTML、XML 或 JSON - 同时您能够把这些外部数据直接载入网页的被选元素中。

原生的Ajax编程相对麻烦。



### 6.3.3、$.get方法

使用一个HTTP GET请求从服务器加载数据。

语法：

```js
$.get(URL,callback);
```

- *URL* 参数规定您希望请求的 URL。（这个是必要的参数）
- *callback* 参数是请求成功后所执行的函数名。（这个是可选的参数）



> demo

项目结构

![image-20240802230027713](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240802230027713.png)



```HTML
<button id = "btn">向页面发送 HTTP GET 请求，然后获得返回的结果</button>

<script>
    $().ready(function () {
        $("#btn").click(function () {
            $.get("demo_text.txt", function (data, status) {
                alert("数据: " + data + "\n状态: " + status);
            });
        });
    });
</script>
```



### 6.3.4、$.post方法

使用一个HTTP POST请求从服务器加载数据。

语法：

```js
$.post(URL,data,callback);
```

- *URL* 参数规定您希望请求的 URL。（必要的参数）
- *data* 参数规定连同请求发送的数据。（可选的参数）
- *callback* 参数是请求成功后所执行的函数名。（可选参数）

```HTML
<button id="btn">Login</button>
<script>
    $.ready(function () {
        $("#btn").click(function () {
            $.post("http://localhost:8080/login",
                {
                    username: "********",
                    password: "********"
                },
                function (response)  {
                    alert("返回结果：" + response);
                });
        });
    });
</script>
```



### 6.3.5、$.ajax方法

`$.get()` 和 `$.post()`都是基于 `$.ajax()` 方法的一个简化版本，如果你需要更多的配置选项或更复杂的请求处理逻辑，使用 `$.ajax()` 方法可能更为合适。

~~~js
$.ajax({
    url: '/user/login',
    type: 'get', // 请求方法：GET、POST 等
    data: {
        username: 'zhangsan',
        password: '123456'
    },
    dataType: 'text', // 预期服务器返回的数据类型
    success: function (response) {
        alert(response);
    },
    error: function (response) {
        alert(response);
    }
});
~~~



使用 jQuery Ajax 时，需要注意以下几点：

1. 跨域问题：由于浏览器的同源策略限制，默认情况下，Ajax 请求不能发送到与当前页面不同源的服务器。如果需要跨域请求，可以使用 CORS（跨源资源共享）或其他技术（如 JSONP，但只适用于 GET 请求）来解决。
2. 错误处理：确保在 Ajax 请求中包含错误处理逻辑，以便在网络错误、服务器错误或其他异常情况下能够妥善处理。
3. 加载状态：在发送 Ajax 请求时，可能需要向用户显示加载状态或禁用某些交互元素，以防止用户在数据加载期间进行不必要的操作。
4. 安全性：确保你的 Ajax 请求遵循安全最佳实践，例如验证和清理用户输入，防止 SQL 注入等安全问题。
5. 性能优化：尽量减少不必要的 Ajax 请求，合理设置缓存策略，以及优化请求和响应的数据大小，以提高页面性能和用户体验。



# 7、JavaScript新特性

## 7.1、ES6新特性

### 7.1.1、模板字符串：

​		模板字符串（template string）是增强版的字符串，用反引号（`）标识，特点：

- 字符串中可以出现换行符

```JavaScript
let str = `hello
Java
Script`
console.log(str);
```

![image-20240614210434552](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240614210434552.png)

- 可以使用 ${xxx} 形式输出变量

~~~JavaScript
let name = "Aip";
let msg = `hello ${name}`;
console.log(msg);
~~~

![hello_Aip](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240614210646277.png)



### 7.1.2、Set和Map

> set

ES6 提供了新的数据结构 Set（集合）。它类似于数组，但成员的值都是唯一的，集合实现了 iterator 接口，所以可以使用『扩展运算符』和『for…of…』进行遍历，集合的属性和方法：

- size：返回集合的元素个数

- add()：增加一个新元素，返回当前集合
- delete()：删除元素，返回 boolean 值
- has()：检测集合中是否包含某个元素，返回 boolean 值
- clear()：清空集合，返回 undefined

```JavaScript
//创建一个空集合
let mySet = new Set();

//创建一个非空集合
let set = new Set([1, 2, 3, 4, 5]);

//集合属性与方法
//1.size 返回集合的元素个数
console.log(set.size); //5

//2.add(value) 向集合添加一个新的项
set.add(6);
console.log(set); //Set(6) {1, 2, 3, 4, 5, 6}

//3.delete(value) 从集合移除一个值
set.delete(3);
console.log(set); //Set(5) {1, 2, 4, 5, 6}

//4.has(value) 返回一个布尔值，检查集合中是否包含某个元素
console.log(set.has(2)); //true

//5.clear() 清空集合
set.clear();
console.log(set); //Set(0) {}
```

![image-20240620180129441](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240620180129441.png)



---

> Map

ES6 提供了 Map 数据结构。它类似于对象，也是键值对的集合。但是“键” 的范围不限于字符串，各种类型的值（包括对象）都可以当作键。Map 也实现了 iterator 接口，所以可以使用『扩展运算符』和『for…of…』进行遍历。Map 的属性和方法：

- size：返回 Map 的元素个数
- set()：增加一个新元素，返回当前 Map
- delete()：根据键删除
- get()：返回键名对象的键值
- has()：检测 Map 中是否包含某个元素，返回 boolean 值
- clear()：清空集合，返回 undefined

```JavaScript
//创建一个空的map
let myMap = new Map();

//创建一个非空的map
let map = new Map([
    ['name', '张三'],
    ['title', 'Author']
]);

//方法和属性
//size 属性
console.log(map.size); //2

//set 方法：添加键值对
map.set('age', 35);
console.log(map); //Map(3) { 'name', '张三', 'title', 'Author', 'age', 35 }

//get 方法：获取键对应的值
console.log(map.get('name')); //张三

//has 方法：检查map中是否含有该键
console.log(map.has('name')); //true

//delete 方法：删除键值对
map.delete('name');
console.log(map); //Map(2) { 'title', 'Author', 'age', 35 }

//遍历map
for (let [key, value] of map) {
    console.log(key + ' = ' + value);
}

//forEach 方法
map.forEach(function(value, key) {
    console.log(key + ' = ' + value);

});

//clear 方法：清空map
map.clear();
console.log(map); //Map(0) {}

//keys 方法：获取所有的键
let keys = map.keys();

//values 方法：获取所有的值
let values = map.values();

//entries 方法：获取所有的键值对
let entries = map.entries();

//map转数组
let keyArray = Array.from(map.keys());

let valueArray = Array.from(map.values());

let entriesArray = Array.from(map.entries());
```

![image-20240620181328510](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240620181328510.png)



### 7.1.3、迭代器

遍历器（Iterator）就是一种机制。它是一种接口，为各种不同的数据结构提供统一的访问机制。任何数据结构只要部署 Iterator 接口，就可以完成遍历操作。ES6 创造了一种新的遍历命令 for…of 循环，Iterator 接口主要供 for…of 消费，原生具备 iterator 接口的数据：

- Array

- Arguments
- Set
- Map
- String
- TypedArray
- NodeList

> for...of：得到的是元素
>
> for...in：得到的是元素下标

```JavaScript
//for...of
console.log('for...of:');
for (const item of myArr) {
    console.log(item);
}
//for...in
console.log('for...in:');
for (const index in myArr) {
    console.log(index);
}
```

![image-20240622160810723](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240622160810723.png)

---



> 自定义迭代器

```JavaScript
//自定义迭代器遍历
//声明一个对象
const myObj = {
    name: '数据',
    data: [1, 2, 3, 4, 5],

    // 创建一个迭代器函数
    [Symbol.iterator]() {

        // 保存当前索引
        let index = 0;

        // 返回一个迭代器对象
        return {
            next: () => {
                // 如果索引小于数组长度，返回下一个元素
                if (index < this.data.length) {
                    return { value: this.data[index++], done: false };
                }
                // 如果索引等于数组长度，返回结束状态
                return { value: undefined, done: true };
            }
        };
    }
}

// 使用自定义迭代器遍历对象
console.log('自定义迭代器遍历:');
for (const item of myObj) {
    console.log(item);
}
```

![image-20240622163331510](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240622163331510.png)



### 7.1.4、rest 参数

- rest 参数用于获取函数的实参，用来代替 arguments 参数。
- *rest 参数非常适合不定个数参数函数的场景。*
- rest 参数只能是最后一个，形式是`...形参名`。

```JavaScript
// 作用与 arguments 类似
function add(...rest)  {
    console.log(rest);
}
add(1, 2, 3, 4, 5);

// rest 参数必须是最后一个形参
function minus(a, b, ...args) {
    console.log(a, b, args);
}
minus(100, 1, 2, 3, 4, 5, 19);
```

![image-20240622202919662](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240622202919662.png)



### 7.1.5、let关键字

let 关键字用来声明变量。

> 特点：

- 不允许重复声明
- 块儿级作用域
- 不存在变量提升
- 不影响作用域链

> 以后声明变量使用 let 就对了，这样可以避免过多的全局变量导致意外的冲突。



### 7.1.6、const关键字

const 关键字用来声明常量。

> 特点：

- 不允许重复声明
- 块儿级作用域
- 声明必须赋初始值
- 值不允许修改
- 标识符一般为大写

> 声明对象类型使用 const，非对象类型声明选择 let

```JavaScript
const PI = 3.14;
PI = 3.11;//如果试图修改常量，会直接报错

// 对于基本数据类型，const声明的变量，不能修改，但是可以修改地址指向
const NAME = "张三";
const NAME1 = NAME;

NAME = "李四";//如果试图修改常量，会直接报错


// 对于数组和对象的元素修改, 不算做对常量的修改, 不会报错
const TEAM1 = [1, 2, 3, 4];
const TEAM2 = [1, 2, 3, 4];
TEAM1[0] = 10;
TEAM1 = [1, 2, 3, 4];//如果试图修改常量，会直接报错
// 但是不能修改地址指向
TEAM2 = TEAM1;
```



### 7.1.7、class类

通过 class 关键字，可以定义类。基本上，ES6 的 class 可以看作只是 一个语法糖，它的绝大部分功能，ES5 都可以做到，新的 class 写法只是让对象原型的写法更加清晰、更像面向对象编程的语法而已，它的一些如下：

- class：声明类

- constructor：定义构造函数初始化
- extends：继承父类
- super：调用父级构造方法
- static：定义静态方法和属性

```JavaScript
//ES5原生的继承方式
//构造函数
function Parent(name){
    this.name = name;
}
//对象方法
Parent.prototype.sayName = function(){
    console.log(this.name);
}

//ES6之后的写法
//定义一个 Human 类
class Human{
    //构造函数
    constructor(name) {
        this.name = name;
    }
    //对象方法
    sayName(){
        console.log(this.name);
    }
}

//子类继承
class Student extends Human{
    constructor(name,gender = ''){
        //调用父类构造函数
        super(name);
        this.gender = gender;
    }
    //子类方法
    sayGrade(){
        console.log("我是"+this.gender +"生");
    }
    //重写父类方法
    sayName(){
        console.log('我是子类的方法');
    }

    //静态方法
    static sayHello(){
        console.log('hello');
    }
}
let student = new Student('小明',"男");
student.sayName();
student.sayGrade();
Student.sayHello();
```

![image-20240711174949277](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240711174949277.png)

