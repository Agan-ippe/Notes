#  1、Redis基本概念

Redis是一个基于内存的 K-V结构数据库。

| KEY  | VALUE |
| ---- | ----- |
| id   | 1     |
| name | ***   |

Redis的优点

- 基于内存存储，读写性能高
- 适合存储热点数据，使用频繁且量少



[学习Redis点我](https://redis.com.cn/)。

# 2、String命令

String是Redis的基本数据类型之一，存储的是 String，常用命令如下：

| 命令                                 | 描述                                 |
| ------------------------------------ | ------------------------------------ |
| set key value                        | 存储一个键值对                       |
| mset key1 value1 [key2 value2 ...]   | 一次存储多个键值对                   |
| get key                              | 获取指定key的值                      |
| mget key1 [key2 ...]                 | 获取多个指定key的值                  |
| setex key seconds value              | 存储一个键值对，并加上有效期，单位秒 |
| setnx key value                      | 当key不存在时，存储这个键值对        |
| msetnx key1 value1 [key2 value2 ...] | 当key不存在时，存储这个键值对        |



# 3、Hash命令

![hash数据结构图](https://raw.githubusercontent.com/Agan-ippe/typora_pic/main/imgs/202411281221131.png)

Hash是Redis基本数据类型之一，存储的是 Hash表。适合用于存储对象。常用的命令如下：

| 命令                                       | 描述                           |
| ------------------------------------------ | ------------------------------ |
| hset key field1 value1 [field2 value2 ...] | 存储一个Hash数据               |
| hget key field                             | 获取指定key中 field 对应的值   |
| hdel key field1 [field2 ...]               | 删除指定key中 field 及对应的值 |
| hkeys key                                  | 获取Hash中的所有 field         |
| hvals key                                  | 获取Hash中的所有值             |



# 4、List命令

List是Redis中最常用数据类型。存储的是列表。常用命令如下：

| 命令                                    | 描述                                  |
| --------------------------------------- | ------------------------------------- |
| lpush key value1 [value2]               | 将一个或多个值插入到列表头部          |
| rpush key value1 [value2]               | 将一个或多个值插入到列表尾部          |
| linsert key BEFORE\|AFTER pivot element | 在列表的 pivot 元素前或者后插入元素   |
| lrange key start stop                   | 获取列表指定范围内的元素              |
| rpop                                    | 移除并获取列表最后一个元素，r代表右边 |
| lpop                                    | 移出并获取列表的第一个元素，l代表左边 |
| lset  key index value                   | 通过索引设置列表元素的值              |
| lrem key count value                    | 移除 count个 指定的 value             |
| lindex key index                        | 通过索引获取列表中的元素              |
| llen                                    | 获取列表长度                          |



# 5、Set命令

Set是Redis中基本数据类型之一。存储的是集合。常用命令如下：

| 命令                           | 描述                                                         |
| ------------------------------ | ------------------------------------------------------------ |
| sadd key member1 [member2 ...] | 向集合添加一个或多个成员                                     |
| srem key member1 [member2 ...] | 移除集合中一个或多个成员，<font color='red'>自测得：如果所有成员都被移除了，这个key也会被删除</font> |
| scard key                      | 获取集合的成员数                                             |
| smembers key                   | 返回集合中的所有成员                                         |
| sinter key1 [key2 ...]         | 返回给定所有集合的交集                                       |
| sunion key1 [key2 ...]         | 返回所有给定集合的并集                                       |



# 6、Zset命令

Zset存储的是有序集合。常用命令如下：

| 命令                                         | 描述                                                         |
| -------------------------------------------- | ------------------------------------------------------------ |
| zadd key score1 member1 [score2 member2 ...] | 向有序集合添加一个或多个成员，或者更新已存在成员的分数，score是分数，<font color='red'>分数最好是设置为整数，否则的话下面的命令就会有问题</font> |
| zrange key min max                           | 通过索引区间返回有序集合成指定区间内的成员                   |
| zincrby key increment member                 | 对指定成员的分数加上 increment 的值                          |
| zrem key member [member ...]                 | 移除有序集合中的一个或多个成员                               |



# 7、Key命令

该命令是Redis中管理键的命令。

| 命令                 | 描述                                    |
| -------------------- | --------------------------------------- |
| del key [key ...]    | 删除 key                                |
| exists key [key ...] | 检查 key 是否存在                       |
| expire key seconds   | 给 key 设定有效期                       |
| persist key          | 移除 key 的有效期，使其永久有效         |
| ttl key              | 以秒为单位，返回给定 key 的剩余生存时间 |
| pttl key             | 以毫秒为单位返回 key 的剩余的过期时间   |
| type key             | 返回 key 所储存的值的类型               |
| rename key newkey    | 改名字                                  |

