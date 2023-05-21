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

    1)继承关系：
        javax.servlet.Servlet接口
            javax.servlet.GenericServlet抽象类 
                javax.servlet.http.HttpServlet
    
    public abstract class HttpServlet extends GenericServlet
    public abstract class GenericServlet implements Servlet, ServletConfig, Serializable
    public interface Servlet
    
    2)相关核心方法：
        
        ①javax.servlet.Servlet接口
        public interface Servlet {
            **初始方法**
            void init(ServletConfig var1) throws ServletException;
            
            ****
            ServletConfig getServletConfig();
            
            **服务方法**
            void service(ServletRequest var1, ServletResponse var2) throws ServletException, IOException;
            **若服务端有请求发出，tomcat容器会调用执行service方法**
            
            ****
            String getServletInfo();
            **销毁方法**
            void destroy();
        }
        
        ②javax.servlet.GenericServlet抽象类
            **服务方法**
            public abstract void service(ServletRequest var1, ServletResponse var2) throws ServletException, IOException;
            
        ③javax.servlet.http.HttpServlet抽象子类(extends GenericServlet)        
            **服务方法**
            protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException { 
                //1.获取请求方式
                String method = req.getMethod();
                //2.判断请求方式类型,调用不同的doXX方法
            }
                **在HttpServlet类中，doXX方法都类似**
                e.g. doPost方法
                protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    String msg = lStrings.getString("http.method_post_not_supported");
                    this.sendMethodNotAllowed(req, resp, msg);
                }
    
    
**3.servlet的生命周期**

**4.http协议**

**5.会话**

**6.Themeleaf**