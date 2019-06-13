package com.zhaojy.xmlparse;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * @author Admin
 * @date 2019/6/13.
 * description：
 */
public class DomXml {

    public final static List<Student> dom2xml(InputStream is) throws Exception {
        //一系列的初始化
        List<Student> list = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        //获得Document对象
        Document document = builder.parse(is);
        //获得student的List
        NodeList studentList = document.getElementsByTagName("student");
        //遍历student标签
        for (int i = 0; i < studentList.getLength(); i++) {
            //获得student标签
            Node node_student = studentList.item(i);
            //获得student标签里面的标签
            NodeList childNodes = node_student.getChildNodes();
            //新建student对象
            Student student = new Student();
            //遍历student标签里面的标签
            for (int j = 0; j < childNodes.getLength(); j++) {
                //获得name和nickName标签
                Node childNode = childNodes.item(j);
                //判断是name还是nickName
                if ("name".equals(childNode.getNodeName())) {
                    String name = childNode.getTextContent();
                    student.setName(name);
                    //获取name的属性
                    NamedNodeMap nnm = childNode.getAttributes();
                    //获取sex属性，由于只有一个属性，所以取0
                    Node n = nnm.item(0);
                    student.setSex(n.getTextContent());
                } else if ("nickName".equals(childNode.getNodeName())) {
                    String nickName = childNode.getTextContent();
                    student.setNickName(nickName);
                }
            }
            //加到List中
            list.add(student);
        }
        return list;
    }

}
