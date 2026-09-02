# Git常用命令

| 命令                                 | 作用             |
| ------------------------------------ | ---------------- |
| git config --global user.name 用户名 | 设置用户签名     |
| git config --global user.email 邮箱  | 设置用户签名     |
| git init                             | 初始化本地库     |
| git status                           | 查看本地库状态   |
| git add 文件名                       | 添加到暂存区     |
| git commit -m "日志信息" 文件名      | 提交到本地库     |
| git reflog                           | 查看简洁历史记录 |
| git log                              | 查看详细历史记录 |
| git reset --hard 版本号              | 版本穿梭         |





> Git的配置文件

正常情况在 `C:\Users\你的用户`这个目录下。`.gitconfig`就是Git的配置文件，里面储存了用户设置的**用户名**和**邮箱**。



> 设置签名的作用

签名的作用是区分不同操作者身份。用户的签名信息在每一个版本的提交信息中能够看到，以此确认本次提交是谁做的。*Git 首次安装必须设置一下用户签名，否则无法提交代码。*

==※注意：==这里设置用户签名和将来登录 GitHub（或其他代码托管中心）的账号没有任何关系。



# Git分支

> 分支命令

| 命令                | 作用                         |
| ------------------- | ---------------------------- |
| git branch 分支名   | 创建分支                     |
| git branch -v       | 查看分支                     |
| git checkout 分支名 | 切换分支                     |
| git merge 分支名    | 把指定的分支合并到当前分支上 |





# Git提交规范

| 类型     | 说明                           |
| -------- | ------------------------------ |
| feat     | 新功能                         |
| fix      | 修复                           |
| refactor | 重构，不增加新功能，也不修bug  |
| docs     | 文档，如README                 |
| style    | 改代码风格、样式，不影响功能   |
| test     | 测试                           |
| chore    | 杂项，比如.gitignore、构建脚本 |
| perf     | 性能优化                       |
| ci       | CI/CD 相关改动                 |
| build    | 改构建系统或者修改依赖         |
| revert   | 回滚某个提交                   |



# Git提交流程

在开始代码提交之前，我们需要确保本地代码是最新的。这可以通过`git pull`命令来实现，以避免代码冲突。

~~~bash
git pull origin master --rebase
~~~



~~~bash
# 添加所有更改的文件到暂存区
git add .
# 或者只添加指定文件
git add temp.txt

# 查看状态
git status
# 取消暂存
git reset HEAD <file>

#执行提交
git commit -m "xxx"

# 跳过暂存区直接提交，会提交所有已跟踪的修改
git commit -a -m "xxx"

# 修正上一次的提交信息
git commit --amend -m "修正后的提交信息"

# 推送到远程仓库的master分支
git push origin master
# 通常情况下，可以省略远程分支名，直接使用
git push

~~~

