# 1、Lambda表达式

## 1.1、什么是*Lambda*表达式

​		Lambda表达式，也可称为闭包。类似于JavaScript中的闭包，它是推动Java8发布的最重要的新特性。其本质就是一个代码块，它必须要表明传入代码的变量规范，也就是变量类型。

> 如何理解Lambda表达式？

​		Lambda表达式简化的是一个方法，可以把整个的Lambda表达式的体看成一个**方法体**，方法需要带返回值，所以有些情况需要包含显示的 ***return*** 语句。

​		既然看成了一个方法体，那么方法则是用来调用的。Lambda表达式下面的语句，就是对 *Lambda表达式的体* 的调用。

```Java
public void staticMethodTest() {
    Comparator<Integer> com = (x, y) -> {
        return Integer.min(x, y);
    };
    //对象是com 通过接口的抽象方法，调用里面的方法体
    System.out.println(com.compare(1, 3));
}
```

​		建议：如果实在觉得不好理解，那就自己写一个与之对应的匿名内部类对比一下。这样可以直观的感受出为什么必须是 函数式接口 才能提供 Lambda 表达式。如果有多个抽象方法，那么 Lambda 表达式就不知道该指向哪一个方法了。

```Java
public void test() {
    //原来的匿名内部类
    Comparator<Integer> com = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1, o2);
        }
    };

    //Lambda表达式
    Comparator<Integer> com1 = (x, y) -> Integer.compare(x, y);
}
```





## 1.2、*Lambda*表达式基本语法

​		Java8 中引入了一个新的操作符 `->` ，该操作符称为 lambda操作符。

~~~Java
//(参数列表) -> 代码块/表达式
    
(int x, int y) -> return x - y
~~~

​		如上，这是一个非常简单的 *lambda表达式* 。如果代码要处理的计算无法放在一个表达式中，就可以想写方法一样，把这些代码放在 {} 中，并包含显式的 ***return*** 语句。

​		即使 *lambda表达式* 没有参数，也仍然需要提供空括号，就像无参方法一样。

~~~Java
() -> {System.out.println("就算没有参数，也要提供空括号");}
~~~



> 什么情况下可以省略参数类型？

​		如果在可以推导出一个 *lambda表达式* 的参数类型的情况下，则可以省略其参数类型。

~~~Java
Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
~~~



> 什么情况下可以省略小括号

​		如果只有一个参数，并且这个参数类型可以推导得出，那么就可以省略小括号。



> 省略return

​		如果不指定 Lambda 表达式的返回类型，则会由上下文推导得出返回值类型。



> 省略花括号

​		Lambda 体中只有一条语句时，可以省略花括号和 return。   



## 1.3、为什么要使用*Lambda*表达式

​		我们可以把Lambda表达式理解为一段可以传递的代码（将代码像数据一样进行传递）。Lambda允许把函数作为一个方法的参数，使用Lambda表达式可以写出更简洁、更灵活的代码。



## 1.4、*Lambda*表达式的使用场景

> 替代匿名内部类

​		Lambda 表达式可以用来替代单方法接口（如 Runnable、Callable、Comparator 等）的匿名内部类，从而使代码更加简洁明了。

```java
// 使用匿名内部类
new Thread(new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello, world!");
    }
}).start();

// 使用 Lambda 表达式
new Thread(() -> System.out.println("Hello, world!")).start();
```



> 集合框架中的使用

​		在 Java 的集合框架中，Lambda 表达式可以用于集合操作，如排序、过滤、映射等。

~~~Java
List<String> list = Arrays.asList("Apple", "Orange", "Banana", "Pear");

// 使用 Lambda 表达式进行排序
list.sort((a, b) -> a.compareTo(b));

// 使用方法引用进行排序
list.sort(String::compareTo);
~~~



> Stream API

​		Lambda 表达式在 Stream API 中有广泛应用，可以用于对集合进行复杂操作，例如过滤、映射、归约等。

~~~Java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// 使用 Stream API 和 Lambda 表达式求和
int sum = numbers.stream()
                 .filter(n -> n % 2 == 0)
                 .mapToInt(n -> n * n)
                 .sum();
System.out.println(sum); // 输出 20
~~~



> 并行处理

​		Lambda 表达式与并行流（Parallel Stream）结合使用，可以更容易地实现并行处理，从而提高性能。

~~~java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// 使用并行流和 Lambda 表达式
int sum = numbers.parallelStream()
                 .filter(n -> n % 2 == 0)
                 .mapToInt(n -> n * n)
                 .sum();
System.out.println(sum); // 输出 220
~~~



## 1.5、函数式接口

​		函数式接口必须是 **有且仅有一个抽象方法**，Object的public方法除外。



### 1.5.1、函数式接口的特征

- 接口中标注了@FunctionalInterface注解
- 接口中只有一个抽象方法会被编译器自动认识成函数式接口
- 接口中有一个抽象方法，同时包含了Object类的其他抽象方法也会被识别成抽象接口





### 1.5.2、四大核心函数式接口

| 函数式接口      | 别名       | 参数类型 | 用途                                                         |
| --------------- | ---------- | -------- | ------------------------------------------------------------ |
| Consumer< T >   | 消费型接口 | T        | 对类型为 T 的对象应用操作，包含方法：`void accept(T t)`      |
| Supplier< T >   | 供给型接口 | 无       | 返回类型为 T 的对象，包含方法：`T get()`                     |
| Function< T,R > | 函数型接口 | T        | 对类型为 T 的对象应用操作，并返回结果。结果是 R 类型的对象。包含方法：`R apply(T t)` |
| Predicate< T >  | 判断型接口 | T        | 确定类型为 T 的对象是否满足某约束，并返回 boolean 值。包含方法：`boolean test(T t)` |



### 1.5.3、其他函数式接口

![image-20240525174855701](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240525174855701.png)



## 1.6、方法引用

​		方法引用可以看作是基于 Lambda 表达式的进一步简化。

​		方法引用分为三种：

- 静态方法引用
- 实例方法引用
- 参数实例方法 引用



> 注意事项：

​		只有当 *lambda表达式* 的体只调用一个方法而不做其他操作时，才能把 *lambda表达式* 重写为方法引用。

​		需要实现的方法中的参数列表与返回值类型，要与当前调用方法的参数列表与返回值类型保持一致（或者是其父类，满足多态）。



### 1.6.1、静态方法引用

​		所有参数都传递到静态方法，例如 `Integer::sum` 等价于

 `(x,y) -> Integer::sum (x,y)`。

​		示例

~~~java
//类名::静态方法名
/**
 * 静态方法引用
 */
@Test
public void staticMethodTest() {
    Comparator<Integer> com = (x, y) -> Integer.min(x, y);
    System.out.println(com.compare(1, 3));

    Comparator<Integer> com1 = Integer::sum;
    System.out.println(com1.compare(1, 1));
    
    Function<Double,Long> fun = aDouble -> Math.round(aDouble);
    System.out.println(fun.apply(66.7));

    Function<Double,Long> fun1 = Math::round;
    System.out.println(fun1.apply(33.4));
}
~~~



### 1.6.2、实例方法引用

​		实例方法引用等价于向方法传递参数的 *lambda表达式* 。对于 `System.out::println`，对象是 `System.out`。所以该方法引用等价于 

`x -> System.out.println(x)`。

​		示例

```Java
//实例对象名::实例方法名
/**
 * 实例方法引用
 */
@Test
public void instanceMethodTest(){
    Consumer<String> con = x -> System.out.println(x);
    con.accept("hello");

    Consumer<String> con1 = System.out::println;
    con1.accept("hello");
}
```





### 1.6.3、特定对象的实例方法引用

​		函数式接口中的抽象 **方法a** 与其内部实现时调用的对象的某个 **方法b** 的返回值类型相同。

​		其第一个参数会成为方法的隐式参数。例如：`String::compareToIgnoreCase` 等同于 `(x,y) -> x.compareToIgnoreCase(y)`。

~~~Java
//类名::实例方法名
/**
 * 参数实例方法引用
 */
@Test
public void paramInstanceMethodTest(){
    //compareTo(str) 返回参与比较的前后两个字符串的asc码的差值,
    Comparator<String> com = (String str1, String str2) -> str1.compareTo(str2);
    //在能比较长度内完全相同，返回两个字符串的长度差
    System.out.println(com.compare("abc", "abcAa"));
    System.out.println(com.compare("abcAa", "abc"));

    Comparator<String> com1 = String::compareTo;
    //遇到首个不同字符，返回同一位置两个字符的asc差值
    System.out.println(com1.compare("abc", "abC"));
}
~~~



## 1.7、构造器引用

​		调用类对象中的某一个确定的构造器，具体调用了哪一个构造器取决于函数式接口的抽象方法的形参。

~~~Java
//类名::new
//Supplier 中的 T get()
@Test
public void demo1() {
    Supplier<Emp> sup = new Supplier<Emp>() {
        @Override
        public Emp get() {
            return new Emp();
        }
    };
    System.out.println(sup.get());

    Supplier<Emp> sup1 = () -> new Emp();
    System.out.println(sup1.get());

    Supplier<Emp> sup2 = Emp::new;
    System.out.println(sup2.get());
}

/**
 * function中的 R apply(T t)
 */
@Test
public void demo2() {
    Function<String, Emp> fun = new Function<String, Emp>() {
        @Override
        public Emp apply(String s) {
            return new Emp(s);
        }
    };
    System.out.println(fun.apply("叮咛"));

    Function<String, Emp> fun1 = s -> new Emp(s);
    System.out.println(fun1.apply("叮咛"));

    Function<String, Emp> fun2 = Emp::new;
    System.out.println(fun2.apply("叮咛"));
}

/**
 * BiFunction 中的 R apply(T t, U u);
 * 用法同上
 */
@Test
public void demo3() {
    BiFunction<String, Integer, Emp> bif1 = new BiFunction<String, Integer, Emp>() {
        @Override
        public Emp apply(String s, Integer integer) {
            return new Emp(s,integer);
        }
    };
    System.out.println(bif1.apply("江澈", 25));

    BiFunction<String, Integer, Emp> bif2 = (s, integer) -> new Emp(s,integer);
    System.out.println(bif2.apply("叮咛", 400));

    BiFunction<String, Integer, Emp> bif3 = Emp::new;
    System.out.println(bif3.apply("团团", 160));
}
~~~



## 1.8、数组引用

​	与构造器引用相似。

~~~Java
// 数组名[] :: new
/**
 * 数组引用
 */
@Test
public void demo4(){
    Function<Integer,Emp[]> fun1 = new Function<Integer, Emp[]>() {
        @Override
        public Emp[] apply(Integer len) {
            return new Emp[len];
        }
    };
    System.out.println(fun1.apply(4).length);


    Function<Integer,Emp[]> fun2 = len -> new Emp[len];
    System.out.println(fun2.apply(5).length);


    Function<Integer,Emp[]> fun3 = Emp[]::new;
    System.out.println(fun3.apply(6).length);
}
~~~



# 2、Stream API

​		流库把真正的函数式编程引入了Java中，这是目前为止对Java类库最好的补充。因为 Stream API 可以极大提供Java程序员的生产力，让代码变得更高效、简洁。

​		Stream 时Java8中处理集合的关键抽象概念，它可以指定你希望对集合进行的操作，可以执行非常复杂的 **排序、查找、过滤、映射、遍历数据等操作**。

​		使用 Stream API 对集合数据进行操作，类似于使用 SQL 执行的数据库查询。Stream API 提供了一种高效且易于处理数据的方式。其核心思想是 ==做什么而非怎么做==。



## 2.1、为什么要使用Stream API

​		在实际开发中，项目中多数数据源来自于MySQL、Oracle等关系型数据库。但现在数据源可以更多了，有MongDB、Radis等非关系型数据库，而这些NoSQL的数据就需要Java层面去处理了。



## 2.2、Stream和集合框架的区别

- 集合是一种静态的内存数据结构，本质是数据，其面向的的内存。
- Stream是有关计算的，本质是计算，其面向的是CPU。



> - 流不会存储元素。
> - 流的操作不会修改其数据源，而是会生成一个新的流。例如`filter`方法不会从流中移除元素，而是产生一个新的流，其中包含过滤出来的元素。
> - 流的操作是惰性执行的，这说明其等到需要结果的时候才会执行。即一旦执行终止操作，就执行中间操作链，产生结果。
> - 流一旦执行了终止操作，就不能再调用其它中间操作和终止操作了。



## 2.3、流的工作流程

​		这是流的典型流程，包含三个阶段的操作管道。

1. 创建一个流对象。
2. 指定将初始流转化为其他流的中间操作（流方法的调用），可能包含多个步骤。
3. 执行终止操作，从而产生结果。这个操作会强制执行之前的惰性操作。在此之后，这个流就不能使用了。



## 2.4、流的创建

​		流的创建可以通过 `Collection`接口的流相关方法将任何集合转换成一个流。

~~~Java
/**
 * 创建流的方式一：通过集合
 */
@Test
public void demo1(){
    List<Emp> list = EmpData.getEmp();

    //default Stream<E> stream()
    Stream<Emp> stream = list.stream();

    //default Stream<E> parallelStream()
    Stream<Emp> parallelStream = list.parallelStream();

}

default Stream<E> stream()			//产生当前集合中所有元素的顺序流
default Stream<E> parallelStream()	//产生当前集合中所有元素的并行流  
~~~

​		顺序流，即按照集合的顺序进行操作。当数据量大时，可以采用并行流提高对数据计算的效率。

​		上面是通过`Collection`接口中的相关方法创建流，如果你有一个数组，那么可以使用 `Array.stream()`。

~~~Java
/**
 * 创建流的方式二：通过数组
 */
@Test
public void demo2(){
    int[] ints = new int[] { 1, 2, 3, 4, 5};
    //基本数据类型有专门对应的类，不会使用泛型的 如：IntStream、LongStream、DoubleStream
    IntStream intStream = Arrays.stream(ints);

    Integer[] arr = new Integer[] { 1, 2, 3, 4, 5};
    Stream<Integer> stream = Arrays.stream(arr,2,6);
    stream.forEach(System.out::print);
}

//源码部分
public static <T> Stream<T> stream(T[] array) {
    return stream(array, 0, array.length);
}
//startInclusive：第几位开始
//endExclusive：第几位结束
//是正常数数的位数，不是下标数
public static <T> Stream<T> stream(T[] array, int startInclusive, int endExclusive) {
    return StreamSupport.stream(spliterator(array, startInclusive, endExclusive), false);
}
~~~



​		你还可以使用 `Stream.of()`来创建一个流，***of方法*** 具有可变长参数，因此我们可以构建具有任意数量引元的流。

​		也可以用 `Stream.empty()` 来创建一个不包含任何元素的流。

```Java
/**
 * 创建流的方式三：通过stream.of()
 */
@Test
public void demo3(){
    Stream<String> stream = Stream.of("叮咛", "团团", "江澈");

    Stream<Object> stream1 = Stream.empty();
}

//部分源码
public static<T> Stream<T> of(T... values) {
    return Arrays.stream(values);
}
```



## 2.5、中间操作

​		每次处理都会返回一个新的持有结果的 Stream，在终止操作执行前不会真正执行。

### 2.5.1、filter

```Java
//流的过滤方法，其参数是一个判断型接口。只要是函数式接口的参数，都可以使用lambda表达式
Stream<T> filter(Predicate<? super T> predicate);

/**
 * filter 过滤器
 */
@Test
public void demo4(){
    List<Emp> emps = EmpData.getEmp();
    emps.stream()
            .filter(emp -> emp.getSalary() > 5000.0)
            .forEach(System.out::println);

    System.out.println("--------------------------------");
    
    emps.stream()
            .filter(new Predicate<Emp>() {
                @Override
                public boolean test(Emp emp) {
                    return emp.getSalary() > 7000;
                }
            })
            .forEach(System.out::println);
}




<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
```



### 2.5.2、map和flatMap

​		如果需要转换流中的值，可以使用 *map* 方法。如下例：需要将单词转换为小写。

```Java
<R> Stream<R> map(Function<? super T, ? extends R> mapper);

/**
 * map 替换作用，例如将单词全部转换为小写的
 */
@Test
public void demo7(){
    List<String> list = Arrays.asList("HELLO", "JAVA", "CHINA");
    list.stream().map(String::toLowerCase).forEach(System.out::println);

    System.out.println("--------------------------------");

    List<String> list1 = Arrays.asList("HELLO", "JAVA", "CHINA");
    list1.stream().map(str -> str.toLowerCase()).forEach(System.out::println);
    
	System.out.println("--------------------------------");
    
    //练习：获取女员工姓名
    List<Emp> emps = EmpData.getEmp();
    emps.stream()
            .filter(emp -> emp.getGender().equals("女"))
            .map(Emp::getName)
            .forEach(System.out::println);
}
```





### 2.5.3、limit 和 skip

​		调用 `tream.limit(n)` 会返回一个新的流，它在 **n** 个元素之后结束（如果原来的流比 n 短，~~那么就会在该流结束时结束~~ 则获取原来的流）。这个方法对于裁剪无限流的尺寸特别有效。

​		调用 `stream.skip(n)` 则正好相反，它会丢弃前 **n** 个元素。

```Java
/**
 * limit and skip
 */
@Test
public void demo5(){
    List<Emp> emps = EmpData.getEmp();
    emps.stream().limit(4).forEach(System.out::println);
    System.out.println("--------------------------------");
    emps.stream().limit(8).forEach(System.out::println);

    System.out.println("--------------------------------");
    emps.stream().skip(4).forEach(System.out::println);
}
```



### 2.5.4、distinct

​		该方法的作用就是去重，通过流生成元素的 `hashCode() 和 equals()` 去除重复元素。

```Java
/**
 * distinct
 */
@Test
public void demo6(){
    List<Emp> emps = RepeatedEmp.getEmp();
    emps.stream().forEach(System.out::println);
    System.out.println("--------------------------------");

    emps.stream().distinct().forEach(System.out::println);
}
```



### 2.5.5、sorted

​		该方法是一个排序方法。默认是升序排序，当然也可以定制排序。

```java
/**
 * 排序 sorted
 */
@Test
public void demo8() {
    Integer[] arr = new Integer[]{458, 99, 31, 532, 483, 9904, 12, 354, 133, 2};
    //默认为升序排序
    Arrays.stream(arr).sorted().forEach(System.out::println);

    System.out.println("--------------------------------");

    //练习：根据员工工资进行排序
    List<Emp> emps = EmpData.getEmp();
    emps.stream()
            .sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))
            .forEach(System.out::println);

    System.out.println("--------------------------------");

    List<Emp> emp = EmpData.getEmp();
    emp.stream()
            .sorted(Comparator.comparingDouble(Emp::getSalary))
            .forEach(System.out::println);

}
```



## 2.6、终止操作

​		终止操作的方法返回值就不再是 Stream 了，一旦执行终止操作，就会执行中间操作链。最终返回结果结束 Stream。



### 2.6.1、匹配与查找

| 方法                                       | 描述                     |
| :----------------------------------------- | :----------------------- |
| boolean allMatch(Predicate<? super T> p)   | 检查是否匹配所有元素     |
| boolean anyMatch(Predicate<? super T> p)   | 检查是否匹配至少一个元素 |
| boolean noneMatch(Predicate<? super T> p)  | 检查是否没有匹配的元素   |
| Optional< T >  findFirst()                 | 返回第一个元素           |
| Optional< T > findAny()                    | 返回当前流中任意元素     |
| count()                                    | 返回流中元素总数         |
| Optional< T > min(Comparator<? super T> c) | 返回流中最小值           |
| Optional< T > max(Comparator<? super T> c) | 返回流中最大值           |
| forEach(Consumer<? super T> action)        | 迭代                     |



```Java
@Test
public void demo9() {
    List<Emp> emps = EmpData.getEmp();
    //练习：判断所有员工工资是否都大于5000
    boolean allMatch = emps.stream().allMatch(emp -> emp.getSalary() > 5000);
    System.out.println(allMatch);

    System.out.println("--------------------------------");

    //练习：判断是否存在女员工
    boolean anyMatch = emps.stream().anyMatch(emp -> emp.getGender().equals("女"));
    System.out.println(anyMatch);

    System.out.println("--------------------------------");

    //练习：获取第一个员工
    emps.stream().findFirst().ifPresent(System.out::println);

    System.out.println("--------------------------------");

    //练习：获取任意员工
    emps.stream().findAny().ifPresent(System.out::println);

    System.out.println("--------------------------------");

    //练习：统计员工数量
    long count = emps.stream().count();
    System.out.println(count);

    System.out.println("--------------------------------");

    //练习：统计员工工资大于5000的数量
    long count1 = emps.stream().filter(emp -> emp.getSalary() > 5000).count();
    System.out.println(count1);

    System.out.println("--------------------------------");

    //练习：返回最高的工资
    Double max = emps.stream().map(Emp::getSalary).max(Double::compare).get();
    System.out.println(max);

    System.out.println("--------------------------------");

    //练习：返回最低工资的员工
    System.out.println(emps.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
    System.out.println(emps.stream().max(Comparator.comparingDouble(Emp::getSalary)));
}
```



### 2.6.2、归约

| 方法                                                  | 描述                                                        |
| ----------------------------------------------------- | ----------------------------------------------------------- |
| T reduce(T identity, BinaryOperator< T > accumulator) | 可以将流中的元素反复结合起来，得到一个值。返回T             |
| Optional< T > reduce(BinaryOperator< T > accumulator) | 可以将流中的元素反复结合起来，得到一个值，返回Optional< T > |



```Java
@Test
public void demo10() {

    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    //求和
    Integer sum = list.stream().reduce(0, Integer::sum);
    System.out.println(sum);

    System.out.println("--------------------------------");

    //求乘积
    Integer product = list.stream().reduce(1, (a, b) -> a * b);
    System.out.println(product);

    System.out.println("--------------------------------");

    //Optional<T> reduce(BinaryOperator<T> accumulator): 将流中的元素反复结合起来得到一个值，返回 Optional<T>
    //计算女员工工资总和
    List<Emp> emps = EmpData.getEmp();
    Optional<Double> sum1 = emps
            .stream()
            .filter(emp -> emp.getGender().equals("女"))
            .map(Emp::getSalary)
            .reduce(Double::sum);

    System.out.println(sum1.get());
}
```

​		map-reduce模式，因谷歌用来进行网络搜索出名。



### 2.6.3、收集

​		`Collector` 接口中方法的实现决定了如何对流执行收集的操作，如`toList、toSet、toMap`。另外，`Collectors` 实用类提供了很多静态方法，可以方便的创建常见收集器实例。

| 方法                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| <R, A> R collect(Collector<? super T, A, R> collector)       | 将流转换为其他形式。接收一个 Collector接口的实现，用于给流中元素做汇总的方法 |
| Collector<T, ?, Set< T >> toSet()                            | 收集成一个set集合                                            |
| Collector<T, ?, List< T >> toList()                          | 收集成一个list集合                                           |
| Collector<T, ?, C> toCollection(Supplier< C > collectionFactory) | 把流中元素收集到创建的集合                                   |
| Collector<T, ?, Long> counting()                             | 计算流中元素的个数                                           |
| Collector<T, ?, Integer> summingInt(ToIntFunction<? super T> mapper) | 对流中元素的整数属性求和                                     |



```Java
@Test
    public void demo11() {
        List<Emp> emps = EmpData.getEmp();
        //收集成一个list集合
        List<String> list = emps.stream().map(Emp::getName).collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("--------------------------------");

        //收集成一个set集合
        Set<String> set = emps.stream().map(Emp::getName).collect(Collectors.toSet());
        set.forEach(System.out::println);

        System.out.println("--------------------------------");

        //收集成一个map集合
        Map<String, Emp> map = emps.stream().collect(Collectors.toMap(Emp::getName, emp -> emp));
        map.forEach((k, v) -> System.out.println(k + ":" + v));

        System.out.println("--------------------------------");

        //收集到一个新创建的集合
        Collection<Emp> emps1 = emps.stream().collect(Collectors.toCollection(ArrayList::new));
        emps1.forEach(System.out::println);

        System.out.println("--------------------------------");

        //收集成一个字符串
        String str = emps.stream().map(Emp::getName).collect(Collectors.joining(","));
        System.out.println(str);

        System.out.println("--------------------------------");

        //计算流中元素个数
        Long count = emps.stream().collect(Collectors.counting());
        System.out.println(count);

        System.out.println("--------------------------------");

        //对流中元素的整数属性求和
        //只有年龄是整型，所以是对年领的求和
        Integer sum = emps.stream().collect(Collectors.summingInt(Emp::getAge));
        System.out.println(sum);
    }
```
