**1.设置编码**
    
    1)tomcat8之前，设置编码：

        ①get请求方式：
        e.g. String name = request.getParameter("name")");
             //将字符串打散成字节数组
             byte[] bytes = name.getBytes("ISO-8859-1");
             //将字节数组按照设定的编码重新组装成字符串
             name = new String(bytes,"UTF-8");

        ②post请求方式：
        e.g.  //post方式下设置编码，防止中文乱码
              request.setCharacterEncoding("utf-8");
    
    2)tomcat8开始，设置编码时只需对post请求方式设置编码
        e.g.  request.setCharacterEncoding("utf-8");

    **设置编码必须在所有的获取参数动作之前**

**2.servlet的继承关系**

    javax.servlet.Servlet接口
        javax.servlet.GenericServlet抽象类 
            javax.servlet.http.HttpServlet
            

**3.servlet的生命周期**

**4.http协议**

**5.会话**

**6.Themeleaf**