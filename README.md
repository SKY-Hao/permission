### SKY--Permission
    基于springboot+mybatis进行开发
    基于最流行RBAC拓展模型，构建分布式权限管理系统
    本系统适用于中小企业作为后台管理权限系统
#### 本项目所使用的技术
    权限框架：Spring Security 、 Apache Shiro
    
    项目框架：Spring Boot、Spring MVC、Mybatis、Redis
    
    基础工具：Maven、Tomcat、MySQL、JDK1.8
    
    前端技术：jQuery、Bootstrap、Mustache、zTree、Duallistbox
    
    其他技术：Guava、Druid、 Jackson

#### 项目功能
      Java权限管理系统功能列表
      1
      部门模块
      
      新增部门
      
      更新部门
      
      删除部门
      
      部门树形结构展示
      
      2
      用户模块
      
      新增用户
      
      更新用户
      
      根据部门分页获取用户列表并展示
      
      3
      权限模块
      
      新增权限模块
      
      更新权限模块
      
      删除权限模块
      
      权限模块树形结构展示
      
      4
      权限点模块
      
      新增权限点
      
      更新权限点
      
      根据权限模块分页获取权限点列表并展示
      
      5
      角色模块
      
      新增角色
      
      更新角色
      
      删除角色
      
      获取角色列表并展示
      
      6
      权限关系维护
      
      角色-权限树形结构列表
      
      更新角色-权限关系
      
      7
      用户关系维护
      
      获取指定角色已分配用户列表
      
      获取指定角色未分配用户列表
      
      更新角色-用户关系
      
      8
      日志模块
      
      带查询条件分页展示权限日志
      
      根据权限日志撤销之前的操作
    
    9
    Redis缓存模块
    
    Redis缓存用户权限
    
    Redis缓存系统权限
    
    已缓存权限清理
    
    10
    其他
    
    在切面判断是否允许当前用户访问某个URL
    
    查询指定用户已分配的权限（树形结构）
    
    查询指定权限被哪些人拥有

