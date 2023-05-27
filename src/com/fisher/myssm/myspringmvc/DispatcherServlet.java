package com.fisher.myssm.myspringmvc;

import com.fisher.myssm.utils.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * 调度器--处理所有以".do"结尾的请求
 * 常见错误：
 * @author fisher
 * @version 1.1.1 2023/5/28 00:30:36
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
    private Map<String,Object> beanMap = new HashMap<>();


    /**
     * Constructor
     * 在构造其中解析applicationContext.xml 配置文件
     */
    public DispatcherServlet() throws IOException, SAXException { }

    public void init() throws ServletException {
        super.init();
        //解析applicationContext.xml 配置文件
        try {
            //获取类加载器
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            //创建DocumentBuilderFactory对象
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //创建DocumentBuilder对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //创建Document对象
            Document document = documentBuilder.parse(inputStream);
            //获取所有的bean节点
            NodeList beanNodeList = document.getElementsByTagName("bean");
            //遍历bean节点的每一个元素
            for(int i = 0; i < beanNodeList.getLength(); i++){
                Node beanNode = beanNodeList.item(i);
                if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");

                    Class<?> controllerBeanClass = Class.forName(className);

                    Object beanObject = controllerBeanClass.newInstance();

                    beanMap.put(beanId, beanObject);
                }
            }
        } catch (ParserConfigurationException | ClassNotFoundException | IllegalAccessException | InstantiationException
                | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int lastDotIndex = request.getServletPath().lastIndexOf(".do");
        String servletName = request.getServletPath().substring(1, lastDotIndex);
        Object controllerBeanObject = beanMap.get(servletName);

        //获取客户端需求,默认为"index"
        String method = request.getParameter("method");
        if(StringUtil.isEmpty(method)){
            method = "index";
        }
        //获取当前类中对应的方法（method）
//        Method methods = null;
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
                        switch (parameterName) {
                            case "request":
                                parameterValues[i] = request;
                                break;
                            case "response":
                                parameterValues[i] = response;
                                break;
                            case "session":
                                parameterValues[i] = request.getSession();
                                break;
                            default:
                                //从请求中获取参数值
                                String parameterValue = request.getParameter(parameterName);
                                parameterValues[i] = parameterValue;
                                break;
                        }
                    }
                    //2、controller组件中的方法调用
                    m.setAccessible(true);
                    Object methodReturnObj = m.invoke(controllerBeanObject,parameterValues);
                    //3、视图处理
                    String methodReturnStr = (String) methodReturnObj;
                    if(methodReturnStr.startsWith("redirect:")){
                        String redirectStr = methodReturnStr.substring("redirect:".length(), methodReturnStr.length());
                        response.sendRedirect(redirectStr);
                }
            }
//            methods = controllerBeanObject.getClass().getDeclaredMethod(method,HttpServletRequest.class);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
