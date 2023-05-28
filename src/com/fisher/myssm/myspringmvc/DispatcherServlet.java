package com.fisher.myssm.myspringmvc;

import com.fisher.myssm.io.BeanFactory;
import com.fisher.myssm.io.ClassPathXmlApplicationContext;
import com.fisher.myssm.utils.StringUtil;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 调度器--处理所有以".do"结尾的请求
 *
 * @author fisher
 * @version 1.2.1 2023年5月28日14:57:16
 */
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet1{

    /*
      TODO:
          1.获取请求路径  getServletPath(); e.g. /fruit.do
          2.去除请求后缀，将请求路径拆分为请求名 e.g. /fruit.do -> fruit
          3.加载配置文件并解析
          3.根据请求名，获取相应的Controller，e.g. fruit -> fruitController
          4.创建配置文件，说明对用关系
    */
//    private Map<String,Object> beanMap = new HashMap<>();
    private BeanFactory beanFactory ;

    public DispatcherServlet() throws IOException, SAXException { }

    public void init() throws ServletException {
        super.init();
        beanFactory = new ClassPathXmlApplicationContext();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int lastDotIndex = request.getServletPath().lastIndexOf(".do");
        String servletName = request.getServletPath().substring(1, lastDotIndex);

        Object controllerBeanObject = beanFactory.getBean(servletName);

        //获取客户端需求,默认为"index"
        String method = request.getParameter("method");
        if(StringUtil.isEmpty(method)){
            method = "index";
        }
        //获取当前类中对应的方法（method）
        try {
            //通过反射调用方法
            Method[] methods = controllerBeanObject.getClass().getDeclaredMethods();
            for (Method m : methods) {
                if(m.getName().equals(method)){
                    //1、统一获取请求参数
                    //获取当前方法的参数，返回参数数组
                    Parameter[] parameters = m.getParameters();
                    //用来存放参数的值
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        //获取参数值，若参数名是request、response、session。则不通过请求获取参数
                        if(parameterName.equals("request")){
                            parameterValues[i] = request;
                        }else if(parameterName.equals("response")){
                            parameterValues[i] = response;
                        }else {
                            //从请求中获取参数值
                            String parameterValue = request.getParameter(parameterName);
                            String typeName = parameter.getType().getName();
                            Object parameterObject = parameterValue;
                            if ("java.lang.Integer".equals(typeName) && parameterValue != null) {
                                parameterObject = Integer.parseInt(parameterValue);
                            }
                            parameterValues[i] = parameterObject;
                        }
                    }
                    //2、controller组件中的方法调用
                    m.setAccessible(true);
                    Object methodReturnObj = m.invoke(controllerBeanObject,parameterValues);
                    //3、视图处理
                    String methodReturnStr = (String) methodReturnObj;
                    if(methodReturnStr.startsWith("redirect:")){
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        response.sendRedirect(redirectStr);
                    }else {
                        super.processTemplate(methodReturnStr,request,response);
                    }
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
