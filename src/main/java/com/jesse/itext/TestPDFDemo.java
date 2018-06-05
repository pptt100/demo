package com.jesse.itext;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Jesse on 2018/5/24.
 */
public class TestPDFDemo {

    public static void main(String[] args) throws IOException, DocumentException {
        //新建document对象
        Document document = new Document();
        PdfWriter instance = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Jesse\\Desktop\\打印输出.pdf"));
        //打开文档
        document.open();
        //添加内容段落
        document.add(new Paragraph("Some content here"));

        //设置属性
        //标题
        document.addTitle("this is a title");
        //作者
        document.addAuthor("H_D");
        //主题
        document.addSubject("this is subject");
        //关键字
        document.addKeywords("Keywords");
        //创建时间
        document.addCreationDate();
        //应用程序
        document.addCreator("hd.com");

        //图片
        Image image = Image.getInstance("C:\\Users\\Jesse\\Desktop\\1.png");
        //设置图片位置得X轴Y轴
        image.setAbsolutePosition(100f,500f);
        //设置图片得宽度和高度
        image.scaleAbsolute(200, 200);
        //将图片添加到pdf中
        document.add(image);

        //关闭文档
        document.close();
        //关闭书写器
        instance.close();
    }

}
