# 前端解决方案

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



# 后端解决方案

在SpringBoot中创建一个JSON配置类，自定义序列化规则，将Long类型的数据自动转换为字符串

```Java
/**
 * Spring MVC Json 配置
 */
@JsonComponent
public class JsonConfig {

    /**
     * 添加 Long 转 json 精度丢失的配置
     */
    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Long.class, ToStringSerializer.instance);
        module.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(module);
        return objectMapper;
    }
}
```