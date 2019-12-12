package cn.edu.lingnan.utils;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


/**
 * @author 杨炜帆
 * @description Xml解析器
 */
public class XmlParser {

    public static HashMap<String, String> parser(String xmlPath) {
        HashMap<String, String > hashMap = new HashMap<>();


        try {
            // 1.实例化一个SAXParserFactory对象
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

            // 2.通过factory获得一个SAXParser对象，即SAX解析器
            SAXParser saxParser = saxParserFactory.newSAXParser();

            // 3.saxParser对象调用parse方法解析XML文件
            File file = new File(xmlPath);
            XmlHandler xmlHandler = new XmlHandler();
            saxParser.parse(file, xmlHandler);
            hashMap = xmlHandler.getHashMap();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

//    public static void main(String[] args) {
//        String xmlPath = "src/database.conf.xml";
//        HashMap<String, String> hashMap = XmlParser.parser(xmlPath);
//        System.out.println(hashMap.get("driver"));
//        System.out.println(hashMap.get("url"));
//        System.out.println(hashMap.get("user"));
//        System.out.println(hashMap.get("password"));
//    }


}
