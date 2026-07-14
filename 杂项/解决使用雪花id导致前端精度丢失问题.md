~~~
yarn add json-bigint
~~~

~~~js
import axios from 'axios';
   import JSONBig from 'json-bigint';

   // 创建自定义解析器
   const jsonParser = JSONBig({
     storeAsString: false, // false：转为BigInt；true：转为字符串
   });

   // 配置axios
   const myAxios = axios.create({
     baseURL: '/api',
     // 重写响应数据的解析方式
     transformResponse: [function (data) {
       try {
         // 用json-bigint解析数据
         return jsonParser.parse(data);
       } catch (e) {
         // 解析失败时用默认方式
         return JSON.parse(data);
       }
     }]
   });

   export default myAxios;
~~~