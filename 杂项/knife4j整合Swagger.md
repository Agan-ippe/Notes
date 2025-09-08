# knife4j的yml配置文件模板

~~~yaml
# Knife4j配置
knife4j:
  # 开启增强配置
  enable: true
  #开启Basic登录认证功能
  openapi:
    title: "文档名称"
    description: "文档描述"
    email: "邮箱"
    concat: "作者信息"  # 作者
    version: 1.0 # 版本号
    license: Apache 2.0
    license-url: https://fire100.top
    terms-of-service-url: https://fire100.top #API服务条款
    # API分组配置
    group:
      user:
        group-name: 用户管理 # 组名
        api-rule: package # 扫描规则，是根据包路径还是请求路径
        api-rule-resources: # 扫描的资源
          - com.springboot101.controller.user
      buy:
        group-name: 商家管理
        api-rule: package
        api-rule-resources:
          - com.springboot101.controller.buy
      #user:
#        group-name: 用户管理
#        api-rule: annotation
#        api-rule-resources:
#          - io.swagger.annotations.Api   # 扫描带有 @Api 注解的接口
#      buy:
#        group-name: 商家管理
#        api-rule: annotation
#        api-rule-resources:
#          - io.swagger.annotations.Api   # 扫描带有 @Api 注解的接口

~~~

