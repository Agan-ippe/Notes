#  放在开头

 Linux的命令是有通配符的 ，在大多数文件操作时，都是可以使用通配符来匹配文件。

- `*`：可以匹配任意数量的字符。
  - 例：`*.*`匹配所有文件，`*.txt`匹配所有后缀是txt的文件
- `?`：匹配任意单个字符。
  - 例：`file?.txt`匹配`filea.txt`或者`file1.txt`等文件。
- `[]`：匹配一组字符串中的任意一个字符。
  - 例：`file[123].md`匹配`file1.md、file2.md、file3.md`
  - `[ - ]`范围匹配，`[1-3]`效果同上例。
- `{}`：匹配多个字符串中的任意一个字符串。
  - 例：`{file,log}*.txt`匹配 file和log 开头的所有txt文件。
- `\`：转义字符

# 1、Linux命令语法

~~~shell
$ command [-options] [parameter] 
#command: 命令本身
#-options: 可选，命令的一些选项，通过选项控制命令的细节
#parameter: 命令参数，多数用于命令的指向目标等
#如果有多个命令选项可以分开写，也可以组合写
$ ls -l -a
#等价于
$ ls -la(或al,顺序无所谓)
~~~



# 2、基础命令

| 序号 | 命令          | 描述                       |
| :--: | ------------- | -------------------------- |
|  01  | ls            | 查看当前文件夹下的内容     |
|  02  | pwd           | 查看当前所在文件夹         |
|  03  | cd[目录名]    | 切换文件夹                 |
|  04  | touch[文件名] | 如果文件不存在，则新建文件 |
|  05  | mkdir[目录名] | 创建目录                   |
|  06  | rm[文件名]    | 删除指定文件               |
|  07  | clear         | 清屏                       |
|  08  | history       | 查看历史命令               |



## 2.1、其他符号

- `|`：管道符，作用是将左边的命令结果，作为右边命令的输入。效果图如4.3章所示
- `>`：重定向符，表示输出，将一个命令的结果输出到指定文件中。会覆盖原有的内容
- `>>`：作用同上，覆盖变为追加。效果图如5.2章所示





# 3、文件常用命令

Linux目录的特点

- 以`.`开头的文件为隐藏文件
- `.`代表当前目录
- `..`代表上一级目录
- `~`代表HOME目录

![image-20241015171114980](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241015171114980.png)

![image-20241015171334501](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241015171334501.png)



## 3.1、ls   显示内容

作用：显示目录内容

~~~bash
$ ls -l #列表显示目录内容
$ ls -a #显示目录下的所有子目录和文件，包含隐藏文件
$ ls -lh #以易于阅读的方式，展示文件大小，如K\M\G
$ ls -lSr #大小升序查看文件和目录列表
$ ls -R #递归列出指定目录下的所有内容，包括子目录里的内容
~~~

*lh效果展示*

![image-20241015173434601](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241015173434601.png)



*lSr效果展示*

![image-20241015173940177](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241015173940177.png)



*R效果展示*

![image-20241015174635286](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241015174635286.png)



## 3.2、cd 切换目录

作用：切换目录

~~~bash
$ cd /  	#返回根目录
$ cd /xxx 	#进入 xxx目录
$ cd ./xx 	#切换当前目录下的xx目录
$ cd .. 	#返回上一级目录
$ cd ../.. 	#返回上两级目录，以此类推
$ cd - 		#返回上一次所在目录
$ cd ~		#切换HOME目录
$ cd ~/xx	#切换HOME目录下的xx目录
~~~



## 3.3、pwd 工作路径

作用：显示当前工作路径，该命令没有选项没有参数，直接使用即可

![image-20241015181933584](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241015181933584.png)



> 绝对路径与相对路径
>
> - 绝对路径是以根目录为起点，每个路径前面要加上/
> - 相对路径，只需写出目录名，无需在前面加上/，需确保相对路径的目录名是唯一的
>
> ![image-20241015182330336](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241015182330336.png)



## 3.4、mkdir 创建目录

作用：创建目录，需要注意的是只能在home目录下创建，其他目录没有权限。

~~~bash
$ mkdir 目录名 						  #创建目录
$ mkdir dir1 dir2				   		#同时创建名为dir1，dir2的两个目录
$ mkdir -p /指定文件夹/dir1/dir2 		 #递归创建目录树，经尝试这种方法不能同时创建两个文件夹
~~~

![image-20241015180938296](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241015180938296.png)



## 3.5、touch 创建文件

作用：如果文件不存在，创建一个空白文件；如果文件已存在，修改文件最后操作时间。

~~~bash
$ touch -t xxxxxxxxxx file	#修改一个文件或目录的时间戳(YYMMDDhhmm)
~~~



![image-20241015223012438](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241015223012438.png)

![image-20241015224536275](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241015224536275.png)



## 3.6、cat 查看文件

作用：可以查看文件内容。使用cat会一次显示所有内容，适合查看内容较少的文本文件。

~~~bash
$ cat -b	#对非空输出行编号
$ cat -n	#对所有输出行编号
$ cat --h 	#查看其他用法
~~~



![image-20241015230418076](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241015230418076.png)

![image-20241015230539423](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241015230539423.png)



## 3.7、more 查看文件

作用：more可以分页查看内容，适用于内容较多的文本文件。

more的查看操作：

- 空格键：下一页
- 回车键：下一行
- b：上一页
- f：下一页
- q：退出



## 3.8、cp 复制

作用：文件或目录的拷贝

~~~bash
$ cp -r	目标目录 复制目录	#目标文件必须是一个目录(文件夹)，递归复制该目录下的所有子目录和文件。
$ cp -a 	#将文件特性一起复制
$ cp -p 	#将文件属性一起复制，常用于备份
$ cp -i 	#若文件已经存在时，覆盖前会先询问
$ cp -u 	#目标文件与源文件有差异时才会复制
~~~

![image-20241015233303686](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241015233303686.png)

![image-20241015233324163](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241015233324163.png)



## 3.9、mv 剪切

作用：移动（剪切）文件或目录。

~~~bash
$ mv -f		#强制移动文件
$ mv -i		#如果文件已存在，先询问
$ mv -u		#若文件存在，保存最新的文件
$ mv old_dir new_dir	#重命名/移动目录
~~~

![image-20241017135138953](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241017135138953.png)



## 3.10、rm 删除

作用：删除文件或目录，需谨慎使用，命令行操作删除不可恢复。

该命令参数不限，可添加任意参数

管理员进行删除操作时会总是先询问。

~~~bash
$ rm -f		#强制删除无需提示，忽略不存在的文件
$ rm -r		#删除目录及目录中的内容，谨慎使用
$ rm -i		#删除前询问，删除操作最好带这个选项，以免误删。按y/yes确认删除
~~~

![image-20241017140651792](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241017140651792.png)



## 3.11、find 查找文件

作用：查找指定的文件

~~~bash
$ find 起始位置 -name 文件名		#按照文件名从起始位置开始查找
$ find 起始位置 -iname 文件名		#按照文件名从起始位置开始查找，忽略大小写

$ find 起始位置 -user 用户名		#查找指定用户的文件
$ find 起始位置 -group 组名		#查找指定组的文件

$ find 起始位置 -size -数字[kMG]	 #查找小于指定大小的文件
$ find 起始位置 -size +数字[kMG]	 #查找大于指定大小的文件

$ find 起始位置 -type d 文件名		#查找目录，需要和其他选项组合使用
$ find 起始位置 -type f 文件名	 	#查找文件，需要和其他选项组合使用

$ find 起始位置 -perm 权限名		#查找有某某权限的文件
$ find 起始位置 ! -perm 权限名		#查找没有某某权限的文件

$ find 起始位置 -atime -数字		 #查找指定天数内修改的文件
$ find 起始位置 -atime +数字 -mtime -数字		#查找xx-xx天之内修改的文件
~~~

按文件名查找

![image-20241017174211752](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241017174211752.png)

按大小查找

![image-20241017175410408](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241017175410408.png)

按修改时间查找

![image-20241017181529106](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241017181529106.png)



# 4、文本处理命令

## 4.1、grep 过滤文本内容 

作用：通过关键字显示文件行，该命令通常与管道命令一起使用，用于对一些命令的输出进行筛选加工等等。

~~~bash
$ grep -n 关键字 文件路径	#过滤结果显示行号
$ grep -i 关键字 文件路径	#忽略关键字大小写
$ grep -c 关键字 文件路径	#统计匹配行数
$ grep -l 关键字 文件路径	#从多个文件中查找包含的关键字
$ grep -v 关键字 文件路径	#查找不包含关键字的行
$ grep -r 关键字 文件路径	#搜索包含子目录
$ grep -d skip 关键字 文件路径	#忽略子目录

#在文件 '/var/log/messages’中查找关键词"Aug"
$ grep Aug /var/log/messages
#在文件 '/var/log/messages’中查找以"Aug"开始的词汇
$ grep ^Aug /var/log/messages
#选择 ‘/var/log/messages’ 文件中所有包含数字的行
$ grep [0-9] /var/log/messages
~~~

![image-20241018180719310](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241018180719310.png)



## 4.2、wc 数量统计

作用：统计文件行数、单词数量等

~~~bash
$ wc -c		#统计bytes数量
$ wc -m		#统计字符数量
$ wc -l		#统计行数
$ wc -w		#统计单词数量
~~~

![image-20241018211842681](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241018211842681.png)



## 4.3、| 管道符

作用：将左边命令的结果，作为右边命令的输入

![image-20241018213323779](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241018213323779.png)



## 4.4、paste 合并文件

作用：合并文件

~~~bash
$ paste file1 file2				#合并两个文件，并返回合并后的内容
$ paste -d '+' file1 file2		#合并两个文件，中间用 + 区分
~~~



## 4.5、sort 排序

作用：对文件内容排序

~~~bash
$ sort file1 file2				#排序两个文件的内容
$ sort file1 file2 | uniq		#取并集
$ sort file1 file2 | uniq -u	#删除交集
$ sort file1 file2 | uniq -d	#取交集
~~~



## 4.6、comm 对比删除

作用：对文件对比，选择性的删除内容。

~~~bash
$ comm -1 file1 file2	#比较两个文件的内容，只删除前一个文件所包含的内容
$ comm -2 file1 file2	#比较两个文件的内容，只删除后一个文件所包含的内容
$ comm -3 file1 file2	#比较两个文件的内容，只删除两个文件共有的部分
~~~



# 5、echo 输出内容

作用：可以显示彩色，输出指定内容。

~~~bash
$ echo "要展示的内容"		#输出指定内容
$ echo -e "\033[颜色1;颜色2m 要展示的内容 \033[0m"		#彩色显示
~~~

![image-20241018231758553](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241018231758553.png)

| 作用范围 | 黑   | 红   | 绿   | 黄   | 蓝   | 洋红 | 青   | 白   |
| -------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| 字体色   | 30   | 31   | 32   | 33   | 34   | 35   | 36   | 37   |
| 背景色   | 40   | 41   | 42   | 43   | 44   | 45   | 46   | 47   |



## 5.1、反引号

作用：被反引号包围的字符串，会当作命令去执行。

![image-20241019114105768](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241019114105768.png)



## 5.2、重定向符

![image-20241019115120482](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241019115120482.png)



## 5.3、tail 尾部查看文件

作用：从文件末尾查看内容

~~~bash
$ tail -f 文件路径		#循环读取文件的最新内容，可以做到实时监控的效果
$ tail -n数 文件路径		#需要读取的行数 -n5表示最后5行， -n+5表示从第5行开始。默认为读取最后10行
$ tail -c数 文件路径		#显示的字节数，-c 10 显示最后10个字符
$ tailf					#等同于 tail -f -n10，如果文件不变，就不会去访问磁盘。
~~~



![image-20241021152012070](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241021152012070.png)

![image-20241021152422513](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241021152422513.png)

# 6、vim专题

vim可以理解为能执行命令的文本编辑器。

~~~bash
$ vim 文件路径		#进入vim，如果路径的文件不存在，就会新建文件。
~~~

![vim工作模式](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/vim%E5%B7%A5%E4%BD%9C%E6%A8%A1%E5%BC%8F.png)



## 6.1、vim常用快捷键

### 6.1.1、常用

yy：复制光标所在行的内容

行数yy：复制光标所在行和下面的指定行的内容

p：粘贴

dd：删除光标所在的当前行

u：撤销

ctrl+r：恢复

i：进入输入模式

I：在光标所在行 开头 位置输入

A：在光标所在行 结尾 位置输入

o：在光标所在行的 下一行 输入

O：在光标所在行的 上一行 输入



### 6.1.2、跳转光标

G：跳到最后一行

gg：跳到首行

数字G：跳到指定数字行

pageup：上一页

pagedown：下一页

hjkl：相当于方向键 左下上右，当然方向键也可以按

0：去行首

$：去行尾



### 6.1.3、删除

x：删除光标所在字符

X：删除光标所在的前一个字符

dw：从当前光标所在字符串，删除到下一个字符串的开头

de：删除当前光标所在字符串末尾

dE：删除当前光标所在字符串末尾，包括标点符号

dgg：从当前光标所在位置开始，上文全删

dG：从当前光标所在位置开始，下文全删

db：如果光标在一个字符串开头位置，则删除前一个字符串，如果在字符串中间则删除前一个字符

dB：同上，多删个标点

d$：从光标所在位置开始，删除到本行末尾

d0：从光标所在位置开始，删除到本行开头



### 6.1.4、搜索内容

/内容：搜索指定内容

n：搜索下一条

N：搜索上一条



## 6.2、vim指令

`:数字`：跳到指定行，相当于 数字G 快捷键

`:行数,.d`：从指定行开始删除到光标所在行

`:%s/字符串/&/gn`：统计指定字符串在文件中出现的次数

`:行首,行末s/字符串//gn`：在指定行中统计字符串出现的次数

`:wq`：保存并退出，w是保存，q是退出

`:q!`：强制退出

`:set nu`：显示行号

`:set nu!`：取消行号

`:set paste`：设置粘贴模式，适用于从外部复制内容，这样不乱码，同样加感叹号取消



# 7、用户和权限

## 7.1、切换用户

~~~bash
$ su - 用户名	#切换用户，-表示在切换用户后加载环境变量，建议带上，用户名可省略，默认切换为root
$ exit		#回退到上一用户，也可以快捷键 ctrl+d
~~~

 

## 7.2、普通用户获取root权限

长期使用root用户，容易给系统造成损坏。所以可以用以下命令获取临时root权限

为普通用户配置sudo认证。

笔者可以直接用sudo，如果不能直接用的话，就加一下认证，(大概知道了什么原因了，可能是创建用户的时候勾选了这是一个管理员用户)

~~~bash
先切换root用户
$ visudu
在文件最后添加
需要授权的普通用户 ALL=(ALL)	按一个tab NOPASSWD: ALL
保存退出
~~~



~~~bash
$ sudo 原本想要执行的命令	#让普通用户获取临时权限，第一次获取需要输入密码
~~~

![image-20241023163205777](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241023163205777.png)



## 7.3、用户组

在Linux中可以有多个用户，也可以有多个用户组，一个用户也可以加入多个组中。



Linux中关于权限的管控有两个级别

- 针对用户的权限控制
- 针对用户组的权限控制

```bash
$ groupadd 用户组名		#创建用户组
$ groupdel 用户组名		#删除用户组

$ useradd 用户名 -g 用户组名 -d 用户路径	#创建用户，不加-g的话，会自动创建同名的组，并自动加入。不指定-d，默认/hemo/用户名
$ userdel -r 用户名	#删除用户，-r:删除hemo下的用户目录
$ id 用户名			#查看用户信息
$ usermod -aG 用户组 用户名	#将用户添加到用户组

$ passwd --stdin 用户名	#root执行，强制更新用户密码
```



## 7.4、getent 查看系统中的用户组及用户

~~~bash
$ getent passwd		#查看当前系统中有哪些用户
$ getent group		#查看当前系统中有哪些用户组
~~~



## 7.5、权限的认知

![image-20241023180517961](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241023180517961.png)

关于权限的组成如下：

权限一共有10位字符组成

![image-20241023180837327](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241023180837327.png)



> 权限的数字序号
>
> ​		权限的数字序号可以方便快捷的修改权限，该方法是通过二进制来表示是否有rwx权限，因为有三组用户权限，所以是由三个数字组成。具体数字代表如下：
>
> - 0：表示无任何权限，---
> - 1：表示只在x位上有权限：--x（二进制表示 001）
> - 2：表示只在w位上有权限：-w-（二进制表示 010）
> - 3：表示在wx位上有权限：-wx（二进制表示 011，以下以此类推，不做赘述）
> - 4：r--
> - 5：r-x
> - 6：rw-
> - 7：rwx
>
> 一个完整权限的表示举例 "751"
>
> - 7表示所属用户权限为 rwx
> - 5表示所属用户组权限为 r-x
> - 1表示其他用户权限为 --x
> - 组合起来为 -rwxr-x--x



## 7.6、chmod 修改权限

~~~bash
$ chmod u=rwx,g=rwx,o=x 文件或目录名	  #将文件或目录按照指定权限修改
$ chmod -R 771 目录名					#-R表示将目录内的所有内容的权限都修改。
~~~

![image-20241101174224853](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241101174224853.png)



## 7.7、chown 修改文件所属用户(组)

该命令需要root用户才能使用，或者普通用户获取临时root权限。

~~~bash
$ chown -R 用户名:用户组名 文件或目录名	#-R用法同上，修改文件或目录所属的用户和用户组
$ chown -R 用户名 文件或目录名	#-R用法同上，修改文件或目录所属的用户
$ chown -R :用户组名 文件或目录名	#-R用法同上，修改文件或目录所属的用户组
~~~

![image-20241104160053757](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241104160053757.png)



# 8、包安装与卸载

## 8.1、yum 适用Redhat系统及其衍生分支

如果是学习CentOS系统的小伙伴，建议换一个系统，因为CentOS系统已经停止维护不再更新了，各大镜像站也删除了相关文件，除非你在Linux上挂魔法，直接使用官方镜像。

如果还要使用基于 红帽系统 的小伙伴，推荐使用rockyLinux系统，使用体验比CentOS只好不坏，其创始人就是CentOS的创始人之一。

这下面的命令就不逐一去试了，我已经换Debian（大便）的Linux了。

~~~bash
$ yum -y	#省略安装询问
$ yum -q	#安装时减少输出信息
$ yum -v	#安装时显示详细信息
$ yum -C	#执行命令前检查包的完整性，避免损坏的包

$ yum install 包名	#下载安装一个rpm包
$ yum remove 包名		#删除一个rpm包
$ yum update 包名		#更新一个rpm包，不带包名则更新已安装的所有包
$ yum search 包名		#在仓库中搜索是否有rpm包

$ yum list installed	#列出已安装的包
$ yum list avalibale	#列出可用的包
$ yum clean				#清除缓存，释放空间
$ yum check -update		#检查可更新的包
~~~



## 8.2、apt 适用于Rebian系统及其衍生分支

~~~bash
$ apt install 包名	#安装/更新指定deb包
$ apt remove 包名		#删除一个deb包
$ apt search 包名		#搜索包
$ apt update 包名		#更新指定包，不带包名则更新所有包
$ apt upgrade		 #升级所有可升级的包
$ apt safe-upgrade	 #安全升级所有可升级的包
$ apt autoremove	 #自动删除不需要的包
$ apt show			 #显示安装细节
$ apt purge			 #移除软件包及配置文件

$ apt list			 #列出
$ apt edit-sources	 #编辑源列表

$ apt-get source --compile 包名	#下载指定包的源码并构建二进制软件包
~~~



# 9、解压与压缩

Linux系统中有两种压缩格式

- gzip：使用gzip压缩算法，将文件体积大大减小
- tar：只是简单的打包，并没有将文件体积减小

当然也有用zip格式的压缩包

## 9.1、tar 解压与压缩

常用参数如下：

- -c ：新建打包文件
- -t ：查看打包文件的内容含有哪些文件名
- -x ：解打包或解压缩的功能，可以搭配-C（大写）指定解压的目录
- -j ：通过bzip2的支持进行压缩/解压缩
- -z ：通过gzip的支持进行压缩/解压缩
- -v ：在压缩/解压缩过程中，将正在处理的文件名显示出来
- -f filename ：filename为要处理的文件
- -C dir ：指定压缩/解压缩的目录dir

<font color='red'>注意：-c，-t，-x不能同时出现在一条命令中</font>

<font color='red'>-z选项一般放在开头，-f选项放在结尾</font>

~~~shell
$ tar -cvf 压缩包名 需要处理的文件或目录名		#压缩
$ tar -zcvf 压缩包名 需要处理的文件或目录名	#使用gzip方式压缩
$ tar -xvf 压缩包名						   #解压到当前目录下
$ tar -zxvf 压缩包名					   #解压.gz格式的压缩包
$ tar -xvf 压缩包名 -C 欲要解压的目录		   #解压到指定目录下
~~~

![image-20241115181421388](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241115181421388.png)



## 9.2、zip/unzip 解压与压缩

~~~shell
$ zip -r 压缩包名 文件1 文件2...	#压缩文件，-r用于压缩目录
$ unzip -d 指定目录 压缩包名		#解压文件到指定目录
~~~



![image-20241117173637270](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241117173637270.png)

![image-20241117175129291](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241117175129291.png)



## 9.3、gzip/gunzip 

~~~shell
$ gzip test.txt	#它会将文件压缩为文件 test.txt.gz，原来的文件则没有了，解压缩也一样 

# 它会将文件解压缩为文件 test.txt，原来的文件则没有了，为了保留原有的文件，
$ gunzip test.txt.gz 

# 我们可以加上 -c 选项并利用 linux 的重定向 
$ gzip -c test.txt > /root/test.gz		#这样不但可以将原有的文件保留，而且可以将压缩包放到任何目录中，解压缩也一样 
$ gunzip -c /root/test.gz > ./test.txt 		#解压缩
~~~

![image-20241117175328328](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241117175328328.png)



# 10、ln 软链接

软链接相当于Windows系统中的快捷方式

~~~shell
$ ln [选项] 源文件或目录 目标文件
$ ln -s [参数1] [参数2]		#进行软链接，如果不带-s参数，就会进行硬链接
$ ln -f [参数1] [参数2]		#删除任何已存在的目标文件
$ ln -i [参数1] [参数2]		#遇到与目标文件同名时，先询问操作
$ ln -n [参数1] [参数2]		#在进行软链接时，将目标文件视为普通文件
$ ln -v [参数1] [参数2]		#列出链接的文件名
$ ln -b [参数1] [参数2]		#链接将已存在的目标文件进行备份
~~~

![image-20241108155920359](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241108155920359.png)



# 11、systemctl 控制服务

~~~bash
$ systemctl status 服务名		#查看服务状态
$ systemctl start 服务名		#启动指定服务
$ systemctl stop 服务名		#停止
$ systemctl enable 服务名		#设置开机自启
$ systemctl disable 服务名		#关闭开机自启
~~~

> 常见的服务名

- `NetWorkManager`：主网络服务
- `network`：副网络服务
- `firewalld`：防火墙
- `sshd`：ssh



# 12、网络和时区

## 12.1、date 查看时间

~~~shell
$ date				#显示当前时间
$ date +"时间格式"	 #按照格式显示时间
$ date -d "±时间标记" +"时间格式"	#计算时间，按照指定格式显示
~~~

时间格式：

- %Y：显示年，如2024
- %y：显示年的后两位，如24
- %m：显示月份
- %d：显示日期
- %H：显示小时
- %M：显示分钟
- %S：显示秒
- %s：时间戳



时间标记：

- year：年，如±1year
- month：月
- day：日
- hour：时
- minute：分
- second：秒

![image-20241108174246636](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241108174246636.png)



## 12.2、修改时区

~~~shell
$ rm -f /etc/localtime
$ sudo ln -s /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
~~~

当然如果方便在图形化界面修改也可以。

![image-20241108175133416](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241108175133416.png)



## 12.3、ntp 自动校准时间

ntp包已经被弃用了，需要自动同步时间的请参考[Rocky Linux系统中 chrony配置参考](https://blog.csdn.net/weixin_43573768/article/details/136245565?fromshare=blogdetail&sharetype=blogdetail&sharerId=136245565&sharerefer=PC&sharesource=m0_59059137&sharefrom=from_link)。



## 12.4、IP和主机名

~~~shell
$ hostnamectl set-hostname 主机名	#修改主机名
~~~



## 12.5、ping 检查网络是否联通

~~~shell
$ ping -c 次数 IP地址/域名	#ping指定次数
~~~



## 12.6、wget 下载文件

~~~shell
$ wget url		#下载文件
$ wget -b url	#后台下载文件
~~~



## 12.7、curl 下载文件与发送请求

curl命令可用于发送http请求，和下载文件

~~~shell
$ curl -O url	#下载文件
$ curl url		#发送请求
~~~



# 13、端口与进程

## 13.1、netstat 端口

[netstat命令参考](https://laker.blog.csdn.net/article/details/129431321?fromshare=blogdetail&sharetype=blogdetail&sharerId=129431321&sharerefer=PC&sharesource=m0_59059137&sharefrom=from_link)。

公认端口：1~1023，为程序内置或预留端口，不得个人设置占用

注册端口：1024~49151，可随意使用，用于绑定额外的程序或服务

动态端口：49152~65535，不会绑定固定的程序，当程序对外进行网络连接时，会临时使用



> netstat命令常用选项
>
> `netstat [-a] [-c] [-e] [-f] [-i] [-n] [-o] [-p] [-r] [-s] [-t] [-u] [interval]`

- -a：显示所有连接状态，包括TCP、UDP 和Unix域套接字
  - 包含 -t -u -x
- -c：显示网络状态信息
- -e：显示网络统计信息，包括网络接口、协议等
- -f：显示外部地址的完全限定域名
- -i：显示网络接口信息
- -n：以数字形式显示地址和端口号
- -o：显示与每个连接相关的进程ID
- -p：显示PID（进程ID）
- -r：显示路由表
- -s：显示网络统计信息，包括网络接口、协议等
- -t：显示TCP协议的连接
- -u：显示UDP协议的连接
- interval：指定连续显示网络状态信息的时间间隔



~~~shell
$ netstat -anp				#显示所有连接和监听端口，并显示每个连接相关的进程ID
$ netstat -anp | grep 进程ID	#查询进程所占用的端口号
$ netstat -anp | grep 端口号	#查询端口号的使用情况
$ netstat -anp | grep 网络状态	#按网络状态过滤
~~~

![image-20241111181851913](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241111181851913.png)



## 13.2、ps 查看进程

- -a ： 显示除控制进程与无端进程外的所有进程
- -d ：显示除控制进程外的所有进程
- -e ：显示所有进程
- -f ：显示完整格式的输出
- -g ：显示会话或组ID在grplist列表中的进程
- -j ：显示任务信息
- -l ：显示长列表
- -M ：显示进程的安全信息
- -o ：仅显示由format指定的列
- -p ：显示PID在pidlist列表中的进程
- -s ：显示会话ID在sesslist列表中的进程
- -t ：显示终端ID在ttylist列表中的进程
- -u ：显示有效用户ID在userlist列表中的进程
- -x ：以用户为中心组织进程状态信息显示
- -y ：不要显示进程标记
- -L ：显示进程中的线程

![image-20241112165010732](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241112165010732.png)

UID：进程所属用户ID。

PID：进程ID。

PPID：进程的父ID（启动此进程的其他进程）。

C：CPU占用率。

STIME：进程的启动时间。

TTY：启动此进程的终端序号，？表示飞终端启动。

TIME：CPU占用的总时长。

CMD：进程对应的名称 或 启动路径 或 启动命令。



## 13.3、kill 关闭进程

~~~shell
$ kill -1 -2 -9 -15 -17 PID
~~~

> 参数

- 1：SIGHUP，启动被终止的进程
- 2：SIGINT，相当于输入ctrl+c，中断一个程序的进行
- 9：SIGKILL，强制中断一个进程的进行
- 15：SIGTERM，以正常的结束进程方式来终止进程
- 17：SIGSTOP，相当于输入ctrl+z，暂停一个进程的进行



> kill -15 PID和kill -9 PID的区别

kill -9 PID 是操作系统从内核级别强制杀死一个进程。

kill -15 PID 可以理解为操作系统发送一个通知告诉应用主动关闭。效果是正常退出进程，退出前可以被阻塞或回调处理。并且它是Linux缺省的程序中断信号。

尽量使用kill -15 PID而不要使用kill -9 PID。

kill -9 PID没有给进程留下善后的机会：

1. 关闭socket链接
2. 清理临时文件
3. 将自己将要被销毁的消息通知给子进程
4. 重置自己的终止状态

一些磁盘操作多的程序更是不要使用kill -9 PID，会导致数据的丢失，如ES，kafka等。



# 14、内存与磁盘

## 14.1、top 查看系统的运行情况

~~~shell
选项
-p PID		#只显示某个进程的信息
-d			#设置刷新时间
-c			#显示产生进程的完整命令，默认是进程名
-n 次数	   #指定刷新次数 top -n 3 刷新3次后退出
-b			#以批处理模式操作
-i			#不显示任何闲置的(idle)进程或 僵尸进程
-u 用户名    #显示指定用户启动的进程
~~~



> 交互式命令

top交互命令如下：

- h：显示帮助画面，给出一些简短的命令总结说明
- c：显示完整命令
- f：选择需要展示的项，带星号表示选中，使用空格选择或取消
- M：根据驻留内存大小（RES）进行排序
- P：根据CPU使用百分比大小进行排序
- T：根据时间/累计时间进行排序
- E：切换顶部内存显示单位
- e：切换进程内存显示单位
- l：切换显示平均负载和启动时间信息
- i：忽略闲置和僵死进程，这是一个开关式命令
- k：终止一个进程
- t：切换显示进程和CPU状态信息
- s：设置刷新时间（单位为s），如果有小数，就换算成ms。输入0值则系统将不断刷新，默认值是5s
- q：退出程序
- r：重新安排一个进程的优先级别
- S：切换到累计模式
- o或者O：改变显示项目的顺序；
- m：切换显示内存信息
- w：将当前设置写入~/.toprc文件中



## 14.2、df 磁盘信息

~~~shell
$ df -h
~~~

![image-20241112181236460](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241112181236460.png)



## 14.3、iostat 查看CPU和磁盘信息

该命令需要sysstat包

~~~shell
$ iostat -x num1 num2	#-x显示更多信息 num1刷新间隔 num2刷新次数
~~~

![image-20241112182046369](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241112182046369.png)



## 14.4、sar 查看网络状态

该命令需要sysstat包

~~~shell
$ sar -n DEV 刷新间隔 刷新次数	#-n 表示查看网络 DEV表示查看网络接口
~~~



# 15、上传和下载

sz和rz命令都需要下载 lrzsz 包

如果不想下也可以通过ssh连接工具进行可视化操作，点击对应的按钮或者直接拖拽文件到指定位置即可

![image-20241115172824769](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241115172824769.png)

但是如果在 Mobaxterm 中使用 lrzsz 包会有点麻烦，个人建议直接推拽文件方便

如果非要使用命令请参考：[Mobaxterm如何使用 lrzsz 包](https://qiuqin.blog.csdn.net/article/details/134591006?fromshare=blogdetail&sharetype=blogdetail&sharerId=134591006&sharerefer=PC&sharesource=m0_59059137&sharefrom=from_link)。



# 、Linux快捷键

~~~bash
CTRL+c		#强制退出，也可以退出当前命令的输入
CTRL+d		#登出当前用户，也可以退出程序页面
CTRL+r		#关键字搜索历史命令，并执行
CTRL+l		#清屏

#光标控制快捷键
CTRL+a		#跳到命令开头
CTRL+e		#跳到命令结尾
CTRL+←		#向左跳一个单词
CTRL+→		#向右跳一个单词
~~~



# 、查找命令的程序文件

which命令：作用就是找到Linux命令的程序文件的位置

![image-20241017172242630](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/image-20241017172242630.png)



# 、随手小记

## 、端口



