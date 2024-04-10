# Java I/O流与文件学习笔记

# 1、File类

​		file 类是一个与流无关的类。File 类对象表示一个磁盘文件或目录，其对象属性包含了文件或目录的相关信息，调用File 类的方法可以完成对文件或目录的管理操作。File 类仅描述文件本身的属性，不具有从文件读取信息或向文件存储信息的能力。*需要的可以使用 Files 类。*



> File 类常用方法

~~~Java
//创建一个文件对象，通过当前路径名
File(String pathname)
// 创建一个文件对象，通过上一级路径名，文件名
File(String parent, String child)
file.getParent();           //获取文件的上一级路径
file.getPath();             //获取文件的相对路径
file.getAbsolutePath();     //获取绝对路径
file.exists();              //判断文件是否存在
file.canRead();             //是否可读
file.isFile();              //是否是一个文件，而非目录
file.canWrite();            //是否可写
file.isDirectory();         //是否是文件夹类型
file.isAbsolute();          //是否是绝对路径
file.isHidden();            //是否是隐藏文件
file.delete();              //删除文件或 空文件夹
file.mkdir();               //创建文件夹
file.mkdirs();              //创建路径中包含的上一级文件夹和下一级文件夹，如果都创建成功，返回 true
file.createNewFile();       //创建一个文件，非文件对象
file.length();              //文件的长度
file.lastModified();        //最后修改日期
~~~





# 2、IO流的概念

​		Java把所有的输入/输出以流的形式进行处理。发送数据流的过程称为写，接受数据流的过程称为读。当程序需要读取数据的时候，就会开启一个通向数据源的流；需要写入数据的时候，就会开启一个通向目的地的流。

​		Java中定义了字节流和字符流以及其他的流类来实现输入/输出处理。

> 字节流

​		从 `InputStream 和 OutputStream` 类派生出来的一系列类称为 **字节流 **类，这类流以字节（byte）为基本处理单位。

​		通常用来操作二进制文件。

> 字符流

​		从 `Reader 和 Writer` 类派生出的一系列类称之为 **字符流** 类，这类流以 16 位的Unicode 编码表示的字符位基本处理单位。

​		通常用来操作文本文件。



# 3、IO流类结构图

> `InputStream` 类结构图

![InputStream家族](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240329195936006.png)



> `OutputStream` 类结构图

![OutputStream类结构图](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240329205357750.png)



> `Reader` 类结构图

![Reader类结构图](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240329213355182.png)



> `Writer` 类结构图

![Writer类结构图](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20240329214150592.png)



# 4、IO流常用方法

## 4.1、InputStream类常用方法

~~~java
int read()				//从输入流中读取一个字节，若到流尾，返回-1
int read(byte b[])		//从输入流中读取字节，并存入字节数组中
int read(byte b[], int off, int len)//从输入流中读取 len个字节，存入数组
long skip(long n)		//跳过并丢输入流的 n个字节
int available()			//返回当前输入流中数据读取方法可以读取的有效字节数量
void close()			//关闭当前输入流，释放资源
void mark(int readlimit)//在输入流数据中打标记
void reset()			//将输入流重新定位到最后一次标记位（即调用mark()）
boolean markSupported()	//测试输入流是否支持标记
~~~



```Java
/**
 * @author Aip
 * @description 使用 FileInputStream 读取文件，并将内容显示到控制台
 */
@Test
public void readFiles() throws IOException {
    String filePath = "D:\\MyNotes\\README.md";
    FileInputStream fileInputStream = new FileInputStream(filePath);
    byte[] bytes = new byte[512];
    int readLen;
    while ((readLen = fileInputStream.read(bytes)) != -1) {
        System.out.println("实际读取字节数: " + readLen);
        System.out.print(new String(bytes,0,readLen));
    }
    fileInputStream.close();
}
```



## 4.2、OutputStream类常用方法

~~~Java
void write(int b)		//将指定的字节写入输出流
void write(byte b[])	//将字节数组写入输出流
void write(byte b[], int off, int len)	//从指定的字节数组写入 len个字节，从偏移 off开始输出到此输出流
void flush()			//刷新此输出流并强制写出所有缓冲的输出字节
void close()			//关闭流，释放资源
~~~



```Java
/**
 * @author Aip
 * @description 使用 FileOutputStream 在文件中写入，"Hello,Java"。如果文件不存在就创建
 */
@Test
public void writeFiles() throws IOException {
    String filePath = "D:\\MyNotes\\test.txt";
    String content = "Hello,Java 你好，Java";
    FileOutputStream fileOutputStream = null;
    fileOutputStream = new FileOutputStream(filePath,true);
    fileOutputStream.write(content.getBytes(StandardCharsets.UTF_8),4,4);
    fileOutputStream.close();

}

```



## 4.3、Reader类常用方法

~~~Java
int read()				//读入一个字符，结束或失败返回-1
int read(char cbuf[])	//读取字符数组
long skip(long n)		//跳过 n个字符数量
void reset()			//将输入流重新定位到最后一次标记位（即调用mark()）
void close()			//关闭流，释放资源
~~~





## 4.4、Writer类常用方法

~~~Java
void write(int c)							//写入整型数据
void write(char cbuf[])						//写入字符数组
void write(char cbuf[], int off, int len)	//写入字符数组的指定部分
void write(String str)						//写入字符串
void write(String str, int off, int len)	//写入字符串的指定部分
void flush()								//刷新，强制写入所有缓冲的字符数据
void close()								//关闭流，释放资源
~~~



## 4.5、字节流子类概述

> FileInputStream 与 FileOutputStream

​		适用于较为简单的文件读取和写入。



> DataInputStream 与 DataOutputStream

​		该类提供了对 *基本数据类型* 进行 *直接读写* 的方法，在读写过程中，不必关心它的实际长度是多少字节。



> BufferedInputStream 与 BufferedOutputStream

​		该类是带缓存的输入输出流。使用缓存，就是在实例化对象时，会在内存中开辟一个字节数组，用来存放数据流中的数据。借助字节数组，在读取或者存储数据时可以以字节数组为单位把数据 *读入内存或写入指定的文件中*。从而提高数据的读写效率。

​		`FileInputStream` 与 `BufferedInputStream ` 的区别：

​		`FileInputStream` 是字节流，`BufferedInputStream ` 是字节缓冲流。

​		使用 `BufferedInputStream ` 读取资源比 `FileInputStream` 读取资源的效率高。

​		`BufferedInputStream ` 的 read 方法能读取尽可能多的字节，而  `FileInputStream` 的 read 方法会出现阻塞。

```Java
/**
 * @author Aip
 * @description 字节包装流,使用字节包装流可以对二进制文件进行操作，例如：图像，视频，音频。
 *              字符包装流不能操作二进制文件，会导致文件损坏。
 */
public class BufferedOutputStreamTest {

    public static void main(String[] args) throws IOException {
        String srcFilePath = "C:\\Users\\93988\\Desktop\\video\\壁纸\\饿饿.gif";
        String destFilePath = "C:\\Users\\93988\\Desktop\\video\\壁纸\\ee.gif";

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        bis =new BufferedInputStream(new FileInputStream(srcFilePath));
        bos = new BufferedOutputStream(new FileOutputStream(destFilePath));

        byte[] buff = new byte[1024];
        int readLen = 0;
        while ((readLen = bis.read(buff)) != -1) {
            bos.write(buff,0,readLen);
        }
        if (bis != null)
            bis.close();
        if (bos != null)
            bos.close();
    }
}
```

~~~Java
//System.in 中 in 是标准输入流，源码在下。
//public final static InputStream in = null;
//System.in 编译类型 InputStream
//System.in 运行类型 BufferedInputStream
//用法
@Test
public void ScannerTest(){
    Scanner scanner = new Scanner(System.in);
    System.out.println("请输入: ");
    String s = scanner.next();
    System.out.println(s);
}
~~~



> ObjectInputStream 与 ObjectOutputStream 

​		Java语言提供了在中直接读取或者写入一个对象的方法。

​		使用对象输入/输出流实现对象序列化可以直接存取对象。将对象存入一个流称之为序列化，将流中的对象读取称之为反序列化。

​		序列化需要满足一下条件：

- 要求保存的对象对应的类型必须实现 `Serializable`接口
- 保存的对象所有属性对应的类型都必须实现 `Serializable`接口
- 如果某些属性不想序列化到硬盘，那么这些属性可以用 `transient`来修饰。用 `transient`修饰的属性，在反序列化的时候需要重新构造对象。
- 如果要保存的对象有集合属性，要求该集合属性存放的对象类型也要实现 序列化接口。
- 如果父类没有实现 序列化接口，则不会把父类对象序列化到硬盘中，反序列化的时候需要重新构造父类对象。



> PrintStream

​		一直用的 `System.out.println()` 就是这个输出流。

​		该类是打印输出流，可以直接输出各种类型的数据，常用方法如下：

~~~Java
void print(char s[])	//打印一个字符数组
void print(String s)	//打印字符串
void print(Object obj)	//打印对象
void println(char x[])	//打印字符数组结束该行
void println(String x)	//打印字符串结束该行
void println(Object x)	//打印对象结束该行
~~~

```Java
/**
 * 用 PrintStream 模仿日志文件原理
 */
@Test
public void LogPrinciple() throws FileNotFoundException {
    System.setOut(new PrintStream("D:\\" + System.currentTimeMillis() + "log.txt"));
    System.out.println("这是一个日志测试文件");
}
```

![image-20240410150048420](C:/Users/93988/AppData/Roaming/Typora/typora-user-images/image-20240410150048420.png)



## 4.6、字符流子类概述

​		关于流操作，操作完成之后一定要关闭流或者调用 `flush` 方法，以确保内容写入文件。

> FileReader 与 FileWriter

​		`FileReader`：进行读取字符串和关闭流等操作

​		`FileWriter`：输出字符串，强制输出和关闭流操作

```Java
/**
 * @author Aip
 * @description 使用 FileWriter 写一句话到文件中
 */
public class FWTest {
    String str = "计算机专业毕业生，为餐饮行业添砖Java";
    String filePath = "D:\\MyNotes\\node.txt";
    
    @Test
    public void WritToFileByMethod() throws IOException {
        FileWriter fileWriter = null;
        char[] chars = {'a','b','c'};
        //true 拼接模式
        //false 覆写模式，默认为 false。
        fileWriter = new FileWriter(filePath,true);

        //write(int c): 为什么int，却可以输入字符，因为流编码器底层会帮我们转换为字符型
        fileWriter.write('H');
        fileWriter.write('\n');

        //write(char cbuf[])
        fileWriter.write(chars);
        fileWriter.write('\n');

        //write(char cbuf[], int off, int len)
        fileWriter.write(str.toCharArray(),1,4);
        fileWriter.write('\n');

        //write(String str)
        fileWriter.write(str);
        fileWriter.write('\n');

        //write(String str, int off, int len)
        fileWriter.write(str,0,5);
        fileWriter.write('\n');
        
        fileWriter.close();
    }
}
```



> InputStreamReader 与 OutputStreamWriter

​		是字节流通向字符流的桥梁，可以根据指定的编码方式 输入 和 输出。

```Java
/**
 * @author Aip
 * @description 通过 转换流 Input/OutputStreamReader 解决乱码
 */
public class InputStreamReaderTest {
    static String filePath = "D:\\MyCode\\a.txt";
    public static void main(String[] args) throws IOException {
        String str;

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        while ((str = reader.readLine()) != null) {
            System.out.println(str);
        }
        reader.close();
    }

    @Test
    public void TestInputStreamReader() throws IOException {
        String str;
        //转换流
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath), "gbk");
        //将转换流放入包装流
        BufferedReader bufferedReader = new BufferedReader(reader);
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }
        bufferedReader.close();
    }
}
```



```java 
/**
 * @author Aip
 * @version 1.0
 * @date 2024/4/9  18:04
 * @description
 */
public class OutputStreamWriterTest {
    static String filePath = "D:\\MyCode\\b.txt";
    static String content = "测试文本。 Test Text.";

    @Test
    public void OSWTest() throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "utf8"));
        writer.write(content);
        writer.close();
        ReadByInputStreamReaderTest();
    }

    @Test
    public void ReadTest() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        System.out.println(reader.readLine());
        reader.close();
    }
    
    public void ReadByInputStreamReaderTest() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"gbk"));
        System.out.println(reader.readLine());
        reader.close();
    }
}
```



> BufferedReader 与 BufferedWriter

​		可以以 row（行）为单位 *读取或写入* 数据。

```Java
/**
 * @author Aip
 * @description
 */
public class BufferedReaderTest {
    private final String filePath = "D:\\MyNotes\\node.txt";

    @Test
    public void ReadFileByBufferedReader() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }
    
 	@Test
    public void CopyFileByBuffered() throws IOException {
        String destFilePath = "D:\\MyNotes\\node_copy.txt";

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(destFilePath));
        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.newLine();
        }
        writer.write("这是拷贝的文件");
        reader.close();
        writer.close();
    }   
}
```



```Java
/**
 * @author Aip
 * @description
 */
public class BufferedWriterTest {
    public static void main(String[] args) throws IOException {
        String filePath = "D:\\MyNotes\\node1.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (int i = 0; i < 5; i++) {
            writer.write("Hello Java, 这是第 " + (i + 1) + " 遍输出");
            writer.newLine();
        }
        writer.close();
    }
}
```



> PrintWriter

​		打印输出流，常用方法如下：

~~~Java
void print(String s)		//打印字符串
void print(int i)			//打印整型
void println(String x)		//打印字符串，换行
void println(int x)			//整型，换行
void println()				//换行
void flush()				//刷新，强制输出
void close()				//关闭流
~~~







 

