package com.zhaojy.xmlparse;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Admin
 * @date 2019/6/13.
 * description：
 */
public class PullXml {

    public final static List<Student> pull2xml(InputStream is) throws Exception {
        List<Student> list = null;
        Student student = null;
        //创建xmlPull解析器
        XmlPullParser parser = Xml.newPullParser();
        ///初始化xmlPull解析器
        parser.setInput(is, "utf-8");
        //读取文件的类型
        int type = parser.getEventType();
        //无限判断文件类型进行读取
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                //开始标签
                case XmlPullParser.START_TAG:
                    if ("students".equals(parser.getName())) {
                        list = new ArrayList<>();
                    } else if ("student".equals(parser.getName())) {
                        student = new Student();
                    } else if ("name".equals(parser.getName())) {
                        //获取sex属性
                        String sex = parser.getAttributeValue(null, "sex");
                        student.setSex(sex);
                        //获取name值
                        String name = parser.nextText();
                        student.setName(name);
                    } else if ("nickName".equals(parser.getName())) {
                        //获取nickName值
                        String nickName = parser.nextText();
                        student.setNickName(nickName);
                    }
                    break;
                //结束标签
                case XmlPullParser.END_TAG:
                    if ("student".equals(parser.getName())) {
                        list.add(student);
                    }
                    break;
                default:
                    break;
            }
            //继续往下读取标签类型
            type = parser.next();
        }
        return list;
    }
}
