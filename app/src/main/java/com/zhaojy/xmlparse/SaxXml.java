package com.zhaojy.xmlparse;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Admin
 * @date 2019/6/13.
 * description：
 */
public class SaxXml {

    public static List<Student> sax2xml(InputStream is) throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        //初始化Sax解析器
        SAXParser sp = spf.newSAXParser();
        //新建解析处理器
        MyHandler handler = new MyHandler();
        //将解析交给处理器
        sp.parse(is, handler);
        //返回List
        return handler.getList();
    }

    public static class MyHandler extends DefaultHandler {

        private List<Student> list;
        private Student student;
        //用于存储读取的临时变量
        private String tempString;

        /**
         * 解析到文档开始调用，一般做初始化操作
         *
         * @throws SAXException
         */
        @Override
        public void startDocument() throws SAXException {
            list = new ArrayList<>();
            super.startDocument();
        }

        /**
         * 解析到文档末尾调用，一般做回收操作
         *
         * @throws SAXException
         */
        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        /**
         * 每读到一个元素就调用该方法
         *
         * @param uri
         * @param localName
         * @param qName
         * @param attributes
         * @throws SAXException
         */
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if ("student".equals(qName)) {
                //读到student标签
                student = new Student();
            } else if ("name".equals(qName)) {
                //获取name里面的属性
                String sex = attributes.getValue("sex");
                System.out.println("sex-----" + sex);
                student.setSex(sex);
            }
            super.startElement(uri, localName, qName, attributes);
        }

        /**
         * 读到元素的结尾调用
         *
         * @param uri
         * @param localName
         * @param qName
         * @throws SAXException
         */
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("student".equals(qName)) {
                list.add(student);
            }
            if ("name".equals(qName)) {
                student.setName(tempString);
            } else if ("nickName".equals(qName)) {
                student.setNickName(tempString);
            }
            super.endElement(uri, localName, qName);
        }

        /**
         * 读到属性内容调用
         *
         * @param ch
         * @param start
         * @param length
         * @throws SAXException
         */
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            tempString = new String(ch, start, length);

            super.characters(ch, start, length);
        }

        /**
         * 获取该List
         *
         * @return
         */
        public List<Student> getList() {
            return list;
        }
    }

}
