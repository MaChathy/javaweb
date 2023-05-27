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

![img_8.png](img_8.png)
    
    1)相对路径
    上图中的login.html若引用login.css，路径为：../css/login.css
        ../表示上一级目录
        /表示当前目录等等
    2)绝对路径
    绝对路径的根目录：http://localhost:8080/webrrestart
        (协议:url:端口号/context-root)
    上图中的index.html的绝对路径为:http://localhost:8080/webrrestart/index.html
    
    html中的base标签：
        <base href="http://localhost:8080/webrrestart/" />
        作用当前页面上的所有路径都以href的值为基础。
    thymeleaf中 th:href="@{}"


当前业务逻辑：

![img_9.png](img_9.png)

改进业务逻辑_01：(优化Servlet，多个转化为一个)

![img_10.png](img_10.png)

改进业务逻辑_02 (mvc reflect)

    通过反射(getClass().getDeclaredMethods())
