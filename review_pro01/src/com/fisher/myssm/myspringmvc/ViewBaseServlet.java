package com.fisher.myssm.myspringmvc;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This program demonstrates webfisher
 *
 * @author fisher
 * @version 1.0.1 2023/5/28 - 23:52
 */
public class ViewBaseServlet extends HttpServlet {

    private TemplateEngine templateEngine;

    @Override
    public void init() throws ServletException {
        //获取ServletContext对象
        ServletContext servletContext = this.getServletContext();

        //创建thymeleaf解析器对象
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);

        //解析器对象设置参数
        //设置模板加载路径
        templateResolver.setTemplateMode(TemplateMode.HTML);
        //设置前缀
        String viewPrefix = servletContext.getInitParameter("viewPrefix");
        templateResolver.setPrefix(viewPrefix);
        //设置后缀
        String viewSuffix = servletContext.getInitParameter("viewSuffix");
        templateResolver.setSuffix(viewSuffix);
        //设置是否缓存
        templateResolver.setCacheable(true);
        //设置模板缓存时间
        templateResolver.setCacheTTLMs(60000L);
        //设置服务器端编码方式
        templateResolver.setCharacterEncoding("UTF-8");

        //创建模板引擎对象
        templateEngine = new TemplateEngine();

        //给模板引擎对象设置模板解析器
        templateEngine.setTemplateResolver(templateResolver);
    }

    //处理模板，完成资源转发和数据渲染
    protected void processTemplate(String templateName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        //创建webContext对象
        WebContext webContext = new WebContext(request, response,getServletContext());

        //处理模板数据
        templateEngine.process(templateName, webContext,response.getWriter());
    }
}
