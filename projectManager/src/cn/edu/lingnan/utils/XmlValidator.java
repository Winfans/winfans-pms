package cn.edu.lingnan.utils;

import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * @author 杨炜帆
 * @description Xml验证器
 */
public class XmlValidator {

    public static boolean validate(String xsdPath, String xmlPath) {

        boolean flag = false;

        try {

            // 1.创建模式工厂SchemaFactory
            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

            // 2.通过XSD文件创建模式Schema
            File file = new File(xsdPath);
            Schema schema = schemaFactory.newSchema(file);
            ;

            // 3.由模式创建验证器Validator
            Validator validator = schema.newValidator();

            // 4.使用验证器验证XML文件
            Source source = new StreamSource(xmlPath);
            validator.validate(source);
            flag = true;
        } catch (SAXException e) {
            System.out.println("创建Schema时失败");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("验证XML文件时失败");
            e.printStackTrace();
        }

        return flag;
    }
}
