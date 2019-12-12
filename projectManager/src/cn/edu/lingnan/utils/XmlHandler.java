package cn.edu.lingnan.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;


/**
 * @author 杨炜帆
 * @description Xml处理器
 */
public class XmlHandler extends DefaultHandler {

    private StringBuffer stringBuffer = new StringBuffer();

    private HashMap<String, String> hashMap = new HashMap<>();

    public HashMap<String, String> getHashMap() {
        return hashMap;
    }

    /**
     * 初始化，清空
     *
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        stringBuffer.delete(0, stringBuffer.length());
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        hashMap.put(qName.toLowerCase(), stringBuffer.toString().trim());
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        stringBuffer.append(ch, start, length);
    }
}
