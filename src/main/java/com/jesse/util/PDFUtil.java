package com.jesse.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Jesse on 2018/5/25.
 */
public class PDFUtil {

    public static final Logger LOGGER = Logger.getLogger(PDFUtil.class);

    //标准字体
    public static Font NORMALFONT;
    //加粗字体
    public static Font BOLDFONT;

    public static float fixedHeight = 27f;

    static {
        try {
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            NORMALFONT = new Font(bfChinese, 10, Font.NORMAL);
            BOLDFONT = new Font(bfChinese, 14, Font.BOLD);
        } catch (Exception e) {
            LOGGER.error("设置字体错误！错误信息：{}", e);
            e.printStackTrace();
        }

    }

    /**
     * 添加水印
     * @param srcFile   待加水印文件
     * @param destFile  加完水印文件
     * @param text  水印文字
     * @param textWidth 水印宽
     * @param textHeight    高
     * @param imgFile   图片
     * @param imgWidth  图片宽
     * @param imgHeight 图片高
     * @throws IOException
     * @throws DocumentException
     */
    public static void addWaterMark(String srcFile, String destFile, String text,int textWidth,
                             int textHeight, String imgFile,int imgWidth, int imgHeight) throws IOException, DocumentException {

        // 待加水印的文件
        PdfReader reader = new PdfReader(srcFile);
        // 加完水印的文件
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(destFile));

        int total = reader.getNumberOfPages() + 1;
        PdfContentByte content;

        BaseFont font =
                BaseFont.createFont("C:/Windows/Fonts/simhei.ttf",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // BaseFont base2 = BaseFont.createFont(BaseFont.HELVETICA,
        // BaseFont.WINANSI, BaseFont.EMBEDDED);
        // 水印文字
        String waterText = text;
        Image image = null;
        if (imgFile != null && !"".equals(imgFile)) {
            image = Image.getInstance(imgFile);
            image.setAbsolutePosition(imgWidth, imgHeight);
            // 设置图片的显示大小
            image.scaleToFit(100, 125);
        }
        int j = waterText.length(); // 文字长度
        char c = 0;
        int high = 0;// 高度
        // 循环对每页插入水印
        for (int i = 1; i < total; i++) {
            // 水印的起始
            high = 50;
            // 水印在之前文本之上
            content = stamper.getOverContent(i);
            if (image != null) {
                content.addImage(image);
            }

            if (text != null && !"".equals(text)) {
                // 开始
                content.beginText();
                // 设置颜色 默认为蓝色
                content.setColorFill(BaseColor.BLUE);
                // 设置字体及字号
                content.setFontAndSize(font, 38);
                // 设置起始位置
                content.setTextMatrix(textWidth, textHeight);
                // 开始写入水印
                content.showTextAligned(Element.ALIGN_LEFT, text, textWidth, textHeight, 45);
                content.endText();
            }
        }
        stamper.close();
    }


    public static Paragraph createNormalParagraph(String text){
        Paragraph elements = new Paragraph(text, NORMALFONT);
        elements.setSpacingAfter(5);
        return elements;
    }

    public static Paragraph createBoldParagraph(String text) {
        Paragraph elements = new Paragraph(text, BOLDFONT);
        elements.setSpacingAfter(5);
        return elements;
    }

    public static void disableBorderSide(PdfPCell cell){
        if (cell != null) {
            cell.disableBorderSide(1);
            cell.disableBorderSide(2);
            cell.disableBorderSide(4);
            cell.disableBorderSide(8);
        }
    }

    public static PdfPCell createPdfPCell() {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setFixedHeight(fixedHeight);
        return cell;
    }

    public static PdfPCell createCenterPdfPCell() {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(fixedHeight);
        return cell;
    }

    public static PdfPTable createPdfPTable(float[] widths) {
        PdfPTable pdfPTable = new PdfPTable(widths);
        pdfPTable.setHorizontalAlignment(Element.ALIGN_CENTER);
        return pdfPTable;
    }



}
