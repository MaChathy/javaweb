thymeleaf-dev

1.保存作用域
    
    原始情况下，有四个保存作用域：
    1) page         : 页面级别
    2) request      : 一次请求响应范围
客户端重定向：

![img_1.png](img_1.png)
![img_2.png](img_2.png)
服务器内部转发：

![img_3.png](img_3.png)
![img.png](img.png)

    3) session      : 一次会话范围
客户端重定向：

![img_4.png](img_4.png)

服务器内部转发：

![img_5.png](img_5.png)


    4) application  : 整个应用范围

![img_6.png](img_6.png)

![img_7.png](img_7.png)

2.路径问题
    
    1)相对路径
    2)绝对路径