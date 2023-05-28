package com.fisher.myssm.io;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 实现BeanFactory接口
 * @author fisher
 * @version 1.0.1 2023/5/28 - 14:53
 */
public class ClassPathXmlApplicationContext implements BeanFactory{

    private Map<String,Object> beanMap = new HashMap();

    public ClassPathXmlApplicationContext(){
        //解析applicationContext.xml 配置文件
        try {
            //1.获取类加载器
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            //创建DocumentBuilderFactory对象
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //2.创建DocumentBuilder对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //3.创建Document对象
            Document document = documentBuilder.parse(inputStream);
            //获取所有的bean节点
            NodeList beanNodeList = document.getElementsByTagName("bean");
            //4.遍历bean节点的每一个节点
            for(int i = 0; i < beanNodeList.getLength(); i++){
                Node beanNode = beanNodeList.item(i);
                if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");

                    Class<?> beanClass = Class.forName(className);
                    //创建bean对象，并实例
                    Object beanObject = beanClass.newInstance();
                    //将bean对象放入map容器中
                    beanMap.put(beanId, beanObject);
                    //bean和bean之间的依赖关系还未设置
                }
            }
            //5.组装bean与bean之间的依赖关系
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node node = beanNodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){

                    Element beanElement = (Element) node;
                    String beanId = beanElement.getAttribute("id");

                    //获取beanElement的所有子节点
                    NodeList beanChildNodes = beanElement.getChildNodes();
                    for (int j = 0; j < beanChildNodes.getLength(); j++) {
                        Node beanChildNode = beanChildNodes.item(j);
                        if (beanChildNode.getNodeType()== Node.ELEMENT_NODE
                                && "property".equals(beanChildNode.getNodeName())) {
                            Element property = (Element) beanChildNode;
                            String propertyName = property.getAttribute("name");
                            String propertyRef = property.getAttribute("ref");
                            //1) 找到property对应的实例
                            Object refObject = beanMap.get(propertyRef);
                            //2) 将refObject设置到当前bean对应的实例的property属性上
                            Object beanObject = beanMap.get(beanId);
                            Class<?> beanClass = beanObject.getClass();
                            Field propertyField = beanClass.getDeclaredField(propertyName);
                            propertyField.setAccessible(true);
                            propertyField.set(beanObject,refObject);
                        }
                    }


                }
            }
        } catch (ParserConfigurationException | ClassNotFoundException | IllegalAccessException | InstantiationException | SAXException | IOException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
