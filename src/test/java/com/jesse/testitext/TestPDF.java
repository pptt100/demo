package com.jesse.testitext;

import com.google.common.collect.Lists;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.jesse.util.CustomCell;
import com.jesse.util.MyFooter;
import com.jesse.util.PDFUtil;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static com.itextpdf.text.pdf.PdfName.DEST;

/**
 * Created by Jesse on 2018/5/24.
 */
public class TestPDF {

    @Test
    public void test01() throws FileNotFoundException, DocumentException {
        Document document = new Document();

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Jesse\\Desktop\\打印输出.pdf"));

        document.open();

        document.add(new Paragraph("some content here"));

        //创建3列得表
        PdfPTable table = new PdfPTable(3);
        //宽度100%填充
        table.setWidthPercentage(100);
        // 前后间距
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        ArrayList<PdfPRow> rows = table.getRows();
        //设置列宽
        float[] columnWidths = {1f, 2f, 3f};
        table.setWidths(columnWidths);

        //行1
        PdfPCell[] pdfPCells1 = new PdfPCell[3];
        PdfPRow pdfPRow1 = new PdfPRow(pdfPCells1);

        //单元格
        pdfPCells1[0] = new PdfPCell(new Paragraph("111"));
        pdfPCells1[0].setBorderColor(BaseColor.BLUE);
        pdfPCells1[0].setPaddingLeft(20);
        pdfPCells1[0].setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCells1[0].setVerticalAlignment(Element.ALIGN_MIDDLE);

        pdfPCells1[1] = new PdfPCell(new Paragraph("222"));
        pdfPCells1[2] = new PdfPCell(new Paragraph("333"));

        //行2
        PdfPCell[] pdfPCells2 = new PdfPCell[3];
        PdfPRow pdfPRow2 = new PdfPRow(pdfPCells2);

        pdfPCells2[0] = new PdfPCell(new Paragraph("444"));

        rows.add(pdfPRow1);
        rows.add(pdfPRow2);

        document.add(table);

        document.close();

        writer.close();
    }

    @Test
    public void test02() throws DocumentException, FileNotFoundException {
        Document document = new Document();

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Jesse\\Desktop\\打印输出.pdf"));

        document.open();

        document.add(new Paragraph("some content here"));

        //添加有序列表
        List list = new List(List.ORDERED);
        list.add(new ListItem("Item one"));
        list.add(new ListItem("Item two"));
        list.add(new ListItem("Item three"));
        document.add(list);

        document.close();

        writer.close();
    }

    @Test
    public void test03() throws DocumentException, IOException {
        Document document = new Document();

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Jesse\\Desktop\\打印输出.pdf"));

        document.open();

        //中文字体，解决中文不能显示问题
        BaseFont font = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

        //蓝色字体
        Font blueFont = new Font(font);
        blueFont.setColor(BaseColor.BLUE);

        //段落文本
        Paragraph paragraphOne_blue_front = new Paragraph("paragraphOne blue front", blueFont);
        document.add(paragraphOne_blue_front);

        //绿色字体
        Font greenFont = new Font(font);
        greenFont.setColor(BaseColor.GREEN);

        //创建章节
        Paragraph paragraph = new Paragraph("段落标题xxxx", greenFont);
        Chapter chapter = new Chapter(paragraph, 1);
        chapter.setNumberDepth(0);

        Paragraph sectionTitle = new Paragraph("部分标题", greenFont);
        Section section1 = chapter.addSection(sectionTitle);

        Paragraph paragraph1 = new Paragraph("部分内容", blueFont);
        section1.add(paragraph1);

        //将章节添加到文章中
        document.add(chapter);

        document.close();

        writer.close();
    }

    @Test
    public void test04() throws FileNotFoundException, DocumentException {

        Document document = new Document();

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Jesse\\Desktop\\hello.pdf"));

        String userPassWord = "123456";
        //拥有者密码
        String ownerPassword = "hd";
        writer.setEncryption(userPassWord.getBytes(), ownerPassword.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);

        document.open();

        document.add(new Paragraph("password!"));

        document.close();

        writer.close();

    }

    @Test
    public void test05() throws IOException, DocumentException {
        //读取pdf文件
        PdfReader pdfReader = new PdfReader("C:\\Users\\Jesse\\Desktop\\打印输出.pdf");

        //修改器
        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("C:\\Users\\Jesse\\Desktop\\hello1.pdf"));

        Image image = Image.getInstance("C:\\Users\\Jesse\\Desktop\\1.png");
        image.scaleAbsolute(50, 50);
        image.setAbsolutePosition(0, 700);

        for (int i=1; i<=pdfReader.getNumberOfPages(); i++) {
            PdfContentByte underContent = pdfStamper.getUnderContent(i);
            underContent.addImage(image);
        }
        pdfReader.close();

    }

    @Test
    public void test06() throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Jesse\\Desktop\\ydtj.pdf"));


        document.open();
        //设置显示中文
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

        Font boldFont = new Font(bfChinese, 14, Font.BOLD);

        Font normalFont = new Font(bfChinese, 12, Font.NORMAL);


        /*Font italicFont = new Font(bfChinese, 12, Font.ITALIC);
        Font bolditalicFont = new Font(bfChinese, 12, Font.BOLDITALIC);
        Font underlineFont = new Font(bfChinese, 12, Font.UNDERLINE);
        Font strikethruFont = new Font(bfChinese, 12, Font.STRIKETHRU);
        Font defaultsizeFont = new Font(bfChinese, 12, Font.DEFAULTSIZE);
        Font undefinedFont = new Font(bfChinese, 12, Font.UNDEFINED);*/

        int chNum = 1;

        Paragraph bt = new Paragraph("增值税电子普通发票金税设备资料统计", boldFont);
        bt.setAlignment(Paragraph.ALIGN_CENTER);

        //Chapter chapter = new Chapter(bt, chNum++);
        Paragraph p1 = new Paragraph();
        Phrase elements = new Phrase("制表日期：", normalFont);
        Phrase elements1 = new Phrase("2015-05-25", normalFont);

        p1.add(elements);
        p1.add(elements1);
        p1.setSpacingAfter(5);
        p1.setSpacingBefore(5);
        Paragraph p2 = new Paragraph("所属期间：5月第1期", normalFont);
        p2.setSpacingAfter(5);
        Paragraph p3 = new Paragraph("金税设备2018年5月资料统计", normalFont);
        p3.setSpacingAfter(5);
        Paragraph p4 = new Paragraph("纳税人登记号：140301192501041066", normalFont);
        p4.setSpacingAfter(5);
        Paragraph p5 = new Paragraph("企业名称：140301192501041066", normalFont);
        p5.setSpacingAfter(5);
        Paragraph p6 = new Paragraph("地址电话：18544468995", normalFont);
        //p6.setSpacingAfter(5);

        //实线分割线
        LineSeparator line = new LineSeparator(0.5f, 100, new BaseColor(204, 204,
                204), Element.ALIGN_CENTER, -2);

        Paragraph p_line = new Paragraph("分割线");
        p_line.add(line);
        p_line.setSpacingAfter(5);

        //chapter.add(p_line);
        document.add(bt);
        document.add(p1);
        document.add(p2);
        document.add(p3);
        document.add(p4);
        document.add(p5);
        document.add(p6);
        document.add(p_line);

        Paragraph p7 = new Paragraph("★    发票领用存情况    ★", normalFont);
        // p7.setSpacingBefore(5);
        p7.setSpacingAfter(5);
        document.add(p7);

        float[] widths = {25f,25f,25f,25f,25f,25f};

        final PdfPTable table = new PdfPTable(widths);
        //table.setSpacingBefore(20f);
        //设置表格默认没有边框
        //table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        //table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        /*ArrayList<PdfPRow> rows1 = table.getRows();
        PdfPCell[] pdfPCells = new PdfPCell[5];
        PdfPRow pdfPRow = new PdfPRow(pdfPCells);*/



        PdfPCell cell = null;

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        linkedHashMap.put("1", "期初库存份数");
        linkedHashMap.put("2", "3333");
        linkedHashMap.put("3", "正数发票份数");
        linkedHashMap.put("4", "3333");
        linkedHashMap.put("5", "负数发票份数");
        linkedHashMap.put("6", "3333");
        linkedHashMap.put("7", "购进发票份数");
        linkedHashMap.put("8", "3333");
        linkedHashMap.put("9", "正数废票份数");
        linkedHashMap.put("10", "3333");
        linkedHashMap.put("11", "负数废票份数");
        linkedHashMap.put("12", "3333");
        linkedHashMap.put("13", "退回发票份数");
        linkedHashMap.put("14", "3333");
        linkedHashMap.put("15", "期末库存份数");
        linkedHashMap.put("16", "3333");
        linkedHashMap.put("17", "分配发票份数");
        linkedHashMap.put("18", "3333");
        linkedHashMap.put("19", "收回发票份数");
        linkedHashMap.put("20", "3333");
        linkedHashMap.put("21", "");
        linkedHashMap.put("22", "");
        linkedHashMap.put("23", "");
        linkedHashMap.put("24", "");

        linkedHashMap.forEach((k,v) -> createCell(v, cell, table));
        //第一行
       /* cell = new PdfPCell(new Paragraph("期初库存份数", normalFont));
        cell.setMinimumHeight(20f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.disableBorderSide(2);
        cell.disableBorderSide(8);*/
        //cell = new PdfPCell(PDFUtil.createNormalParagraph("期初存货份数"));
        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("期初存货份数"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/
        /*Paragraph qcch = new Paragraph("期初存货份数", normalFont);
        qcch.setFirstLineIndent(14);
        qcch.setSpacingAfter(5);*/

        /*cell =  new PdfPCell(new Paragraph("996", normalFont));
        cell.setMinimumHeight(20f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);*/
        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("333"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/

        /*cell = new PdfPCell(new Paragraph("正数发票份数", normalFont));
        cell.setMinimumHeight(20f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);*/
        //table.addCell(new Paragraph("正数发票份数", normalFont));

        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("正数发票份数"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/

        /*cell = new PdfPCell(new Paragraph("888", normalFont));
        cell.setMinimumHeight(20f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);*/
        //table.addCell(new Paragraph("", normalFont));

        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("555"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/

        //table.addCell(new Paragraph("负数发票份数", normalFont));

        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("负数发票份数"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/

        //table.addCell(new Paragraph("3333" ,normalFont));

        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("3333"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/

        //table.addCell(new Paragraph("购进发票份数",normalFont));

        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("购进发票份数"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/

        //table.addCell(new Paragraph("333", normalFont));

        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("333"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/

        //table.addCell(new Paragraph("正数废票份数",normalFont));
        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("正数废票份数"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/
        //table.addCell(new Paragraph("333", normalFont));
        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("333"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/
        //table.addCell(new Paragraph("负数废票份数",normalFont));
        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("负数废票份数"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/
        // table.addCell(new Paragraph("333", normalFont));
        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("333"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/
        //table.addCell(new Paragraph("退回发票份数",normalFont));
       /* cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("退回发票份数"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/
        //table.addCell(new Paragraph("333", normalFont));
        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("333"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/
        //table.addCell(new Paragraph("期末库存份数",normalFont));
        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("期末库存份数"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/
        //table.addCell(new Paragraph("333", normalFont));
        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("333"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/
        // table.addCell(new Paragraph("分配发票份数",normalFont));
        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("分配发票份数"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/
        //table.addCell(new Paragraph("333", normalFont));
        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("333"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/
        //table.addCell(new Paragraph("收回发票份数",normalFont));
        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("收回发票份数"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/
        //table.addCell(new Paragraph("333", normalFont));
        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph("333"));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/

        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph(""));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/

        /*cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph(""));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);

        cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph(""));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);

        cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph(""));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);*/

        document.add(table);

        //分割线
        /*LineSeparator lineSeparator = new LineSeparator(0.5f, 100, new BaseColor(204, 204,
                204), Element.ALIGN_CENTER, -2);
        Paragraph p_line1 = new Paragraph("分割线");
        p_line1.add(lineSeparator);
        p_line1.setSpacingAfter(5);
        document.add(p_line1);*/

        /*PdfPTable tt = new PdfPTable(2);
        tt.setWidthPercentage(100f);
        tt.getDefaultCell().setBorderWidthLeft(0);
        tt.getDefaultCell().setBorderWidthRight(0);
        tt.getDefaultCell().setBorderWidthTop(0);
        tt.addCell("column1");
        tt.addCell("column2");
        document.add(tt);*/
        //虚线分割线
        CustomCell border = new CustomCell();
        PdfPTable table1 = new PdfPTable(1);
        table1.setWidthPercentage(100);
        PdfPCell cell2;

        /*for (int i = 1; i <= 6; i++) {
            cell2 = new PdfPCell(new Phrase("test"));

            cell2.setBorder(Rectangle.NO_BORDER);

            cell2.setCellEvent(border);
            table1.addCell(cell2);
        }*/
        cell2 = new PdfPCell(new Phrase("表格作为分割线"));
        cell2.setFixedHeight(10f);
        cell2.setBorder(Rectangle.NO_BORDER);
        //cell2.setBorderColorBottom(BaseColor.RED);
        cell2.setCellEvent(border);
        table1.addCell(cell2);

        document.add(table1);

        Paragraph elements2 = new Paragraph("★    消项情况    ★", normalFont);
        elements2.setSpacingAfter(5);
        document.add(elements2);
        Paragraph jedwPar = new Paragraph("金额单位：元", normalFont);
        document.add(jedwPar);

        java.util.List<SL> sls = new ArrayList<>();
        sls.add(new SL(0.17,222, 333,333,444,444,555,666,777));
        sls.add(new SL(0.10,222, 333,333,444,444,555,666,777));
        sls.add(new SL(0.13,222, 333,333,444,444,555,666,777));
        sls.add(new SL(0.03,222, 333,333,444,444,555,666,777));
        sls.add(new SL(0.15,222, 333,333,444,444,555,666,777));
        sls.add(new SL(0.08,222, 333,333,444,444,555,666,777));
        sls.add(new SL(0.08,222, 333,333,444,444,555,666,777));
        sls.add(new SL(0.08,222, 333,333,444,444,555,666,777));
        sls.add(new SL(0.08,222, 333,333,444,444,555,666,777));

        BigDecimal[] bigDecimal = new BigDecimal[8];
        bigDecimal[0] = new BigDecimal(0.00);
        bigDecimal[1] = new BigDecimal(0.00);
        bigDecimal[2] = new BigDecimal(0.00);
        bigDecimal[3] = new BigDecimal(0.00);
        bigDecimal[4] = new BigDecimal(0.00);
        bigDecimal[5] = new BigDecimal(0.00);
        bigDecimal[6] = new BigDecimal(0.00);
        bigDecimal[7] = new BigDecimal(0.00);
        for (int i=0;i<sls.size();i++) {
            bigDecimal[0] = bigDecimal[0].add(new BigDecimal(sls.get(i).getVal()));
            bigDecimal[1] = bigDecimal[1].add(new BigDecimal(sls.get(i).getZval()));
            bigDecimal[2] = bigDecimal[2].add(new BigDecimal(sls.get(i).getIval()));
            bigDecimal[3] = bigDecimal[3].add(new BigDecimal(sls.get(i).getOval()));
            bigDecimal[4] = bigDecimal[4].add(new BigDecimal(sls.get(i).getPval()));
            bigDecimal[5] = bigDecimal[5].add(new BigDecimal(sls.get(i).getTval()));
            bigDecimal[6] = bigDecimal[6].add(new BigDecimal(sls.get(i).getUval()));
            bigDecimal[7] = bigDecimal[7].add(new BigDecimal(sls.get(i).getYval()));
        }

        //3个一组

        java.util.List<java.util.List<SL>> partition = Lists.partition(sls, sls.size());


        float[] wirths = new float[3 + sls.size()];

        for (int i=0; i<3+sls.size(); i++) {
            if (i == 0) {
                wirths[i] = 10f;
            } else if (i == 1) {
                wirths[i] = 30f;
            } else {
                wirths[i] = 20f;
            }
        }

        PdfPTable[] xxqkTable = new PdfPTable[partition.size()];;

        for (int k=0; k<partition.size(); k++) {
            if (k==0) {
                xxqkTable[k] = new PdfPTable(3+partition.get(k).size());
            } else {
                xxqkTable[k] = new PdfPTable(2+partition.get(k).size());
            }
            xxqkTable[k].setSpacingBefore(20f);
            xxqkTable[k].setHorizontalAlignment(Element.ALIGN_LEFT);
            ArrayList<PdfPRow> rows = xxqkTable[k].getRows();
            int numberOfColumns = xxqkTable[k].getNumberOfColumns();
            final PdfPCell[] pdfPCell1 = new PdfPCell[numberOfColumns];
            PdfPRow pdfPRow = new PdfPRow(pdfPCell1);
            LinkedHashMap<String, String> slhm = new LinkedHashMap<>();

            slhm.put("0","序号");
            slhm.put("1","项目名称");
            int z = 2;
            if (k==0) {
                slhm.put("2","合计");
                z++;
            }
            for (int i=0; i<partition.get(k).size(); i++) {
                slhm.put((z+i)+"",(partition.get(k).get(i).getSl() * 100)+"%");
            }


            slhm.forEach((key,v) -> createCell(pdfPCell1,key,v,normalFont));

            rows.add(pdfPRow);

            for (int i=0; i<8; i++) {
                PdfPCell[] pdfPCell2 = new PdfPCell[numberOfColumns];
                pdfPRow = new PdfPRow(pdfPCell2);
                pdfPRow.setMaxHeights(20f);
                createCell(pdfPCell2,0,(i+1)+"");
                String text = "";

                double[] sdd = new double[partition.get(k).size()];
                switch (i) {
                    case 0:
                        text = "消项正废金额";

                        for (int j=0; j<partition.get(k).size(); j++) {
                            sdd[j] = partition.get(k).get(j).getVal();
                        }
                        break;
                    case 1:
                        text = "消项正数金额";
                        for (int j=0; j<partition.get(k).size(); j++) {
                            sdd[j] = partition.get(k).get(j).getZval();
                        }
                        break;
                    case 2:
                        text = "消项正数金额";
                        for (int j=0; j<partition.get(k).size(); j++) {
                            sdd[j] = partition.get(k).get(j).getIval();
                        }
                        break;
                    case 3:
                        text = "消项正数金额";
                        for (int j=0; j<partition.get(k).size(); j++) {
                            sdd[j] = partition.get(k).get(j).getOval();
                        }
                        break;
                    case 4:
                        text = "消项正数金额";
                        for (int j=0; j<partition.get(k).size(); j++) {
                            sdd[j] = partition.get(k).get(j).getPval();
                        }
                        break;
                    case 5:
                        text = "消项正数金额";
                        for (int j=0; j<partition.get(k).size(); j++) {
                            sdd[j] = partition.get(k).get(j).getTval();
                        }
                        break;
                    case 6:
                        text = "消项正数金额";
                        for (int j=0; j<partition.get(k).size(); j++) {
                            sdd[j] = partition.get(k).get(j).getUval();
                        }
                        break;
                    case 7:
                        text = "消项正数金额";
                        for (int j=0; j<partition.get(k).size(); j++) {
                            sdd[j] = partition.get(k).get(j).getYval();
                        }
                        break;
                }
                createCell(pdfPCell2,1, text);
                if (k==0){
                    createCell(pdfPCell2,2, bigDecimal[i].toString());
                    for (int j=0; j<partition.get(k).size(); j++) {
                        createCell(pdfPCell2,j+3,sdd[j]+"");
                    }
                } else {
                    for (int j=0; j<partition.get(k).size(); j++) {
                        createCell(pdfPCell2,j+2,sdd[j]+"");
                    }
                }
                rows.add(pdfPRow);
            }

            document.add(xxqkTable[k]);
            if (k == 0 && partition.size()>1) {
                document.newPage();
            }
        }

        //xxqkTable.setTotalWidth(wirths);
        //xxqkTable.setLockedWidth(true);
        //xxqkTable.setWidthPercentage(100);









        /*PdfPCell xxqkCell = null;
        BaseColor titalColor = new BaseColor(221, 221, 221);
        pdfPCell1[0] = new PdfPCell(new Paragraph("序号", normalFont));
        pdfPCell1[0].setFixedHeight(27f);
        pdfPCell1[0].setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell1[0].setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell1[0].setBackgroundColor(titalColor);


        pdfPCell1[1] = new PdfPCell(new Paragraph("项目名称", normalFont));
        pdfPCell1[1].setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell1[1].setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell1[1].setBackgroundColor(titalColor);


        pdfPCell1[2] = new PdfPCell(new Paragraph("合计", normalFont));
        pdfPCell1[2].setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell1[2].setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell1[2].setBackgroundColor(titalColor);

        pdfPCell1[3] = new PdfPCell(new Paragraph("17%", normalFont));
        pdfPCell1[3].setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell1[3].setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell1[3].setBackgroundColor(titalColor);

        pdfPCell1[4] = new PdfPCell(new Paragraph("3%", normalFont));
        pdfPCell1[4].setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell1[4].setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell1[4].setBackgroundColor(titalColor);*/




        /*xxqkCell = new PdfPCell(new Paragraph("1", normalFont));
        xxqkCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        xxqkCell.setFixedHeight(27f);
        xxqkCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xxqkTable.addCell(xxqkCell);

        xxqkCell = new PdfPCell(new Paragraph("消项正废金额", normalFont));
        xxqkCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        xxqkCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xxqkTable.addCell(xxqkCell);

        xxqkCell = new PdfPCell(new Paragraph("0.00", normalFont));
        xxqkCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        xxqkCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xxqkTable.addCell(xxqkCell);

        xxqkCell = new PdfPCell(new Paragraph("0.00", normalFont));
        xxqkCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        xxqkCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xxqkTable.addCell(xxqkCell);

        xxqkCell = new PdfPCell(new Paragraph("0.00", normalFont));
        xxqkCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        xxqkCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xxqkTable.addCell(xxqkCell);

        xxqkCell = new PdfPCell(new Paragraph("2", normalFont));
        xxqkCell.setFixedHeight(27f);
        xxqkCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        xxqkCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xxqkTable.addCell(xxqkCell);

        xxqkCell = new PdfPCell(new Paragraph("消项正数金额", normalFont));
        xxqkCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        xxqkCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xxqkTable.addCell(xxqkCell);

        xxqkCell = new PdfPCell(new Paragraph("0.00", normalFont));
        xxqkCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        xxqkCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xxqkTable.addCell(xxqkCell);

        xxqkCell = new PdfPCell(new Paragraph("0.00", normalFont));
        xxqkCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        xxqkCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xxqkTable.addCell(xxqkCell);

        xxqkCell = new PdfPCell(new Paragraph("0.00", normalFont));
        xxqkCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        xxqkCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        xxqkTable.addCell(xxqkCell);*/






        document.close();

        writer.close();

    }


    public void createCell(PdfPCell[] cell, int i, String text) {
        cell[i] = new PdfPCell(PDFUtil.createNormalParagraph(text));
        cell[i].setFixedHeight(27f);
        cell[i].setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell[i].setHorizontalAlignment(Element.ALIGN_CENTER);
    }

    public void createCell(PdfPCell[] cels,String len,String text, Font normalFont) {
        int i = Integer.parseInt(len);
        cels[i] = new PdfPCell(new Paragraph(text, normalFont));
        //cels[i].setFixedHeight(27f);
        cels[i].setMinimumHeight(25f);
        cels[i].setHorizontalAlignment(Element.ALIGN_CENTER);
        cels[i].setVerticalAlignment(Element.ALIGN_MIDDLE);
        BaseColor titalColor = new BaseColor(221, 221, 221);
        cels[i].setBackgroundColor(titalColor);
    }

    public void createCell(String v, PdfPCell cell, PdfPTable table) {
        cell = PDFUtil.createPdfPCell();
        cell.addElement(PDFUtil.createNormalParagraph(v));
        PDFUtil.disableBorderSide(cell);
        table.addCell(cell);
    }

    class SL {
        private double sl;
        private double val;
        private double zval;
        private double tval;
        private double yval;
        private double uval;
        private double ival;
        private double oval;
        private double pval;

        public SL(double sl, double val, double zval) {
            this.sl = sl;
            this.val = val;
            this.zval = zval;
        }

        public SL(double sl, double val, double zval, double tval, double yval, double uval, double ival, double oval, double pval) {
            this.sl = sl;
            this.val = val;
            this.zval = zval;
            this.tval = tval;
            this.yval = yval;
            this.uval = uval;
            this.ival = ival;
            this.oval = oval;
            this.pval = pval;
        }

        public double getTval() {
            return tval;
        }

        public void setTval(double tval) {
            this.tval = tval;
        }

        public double getYval() {
            return yval;
        }

        public void setYval(double yval) {
            this.yval = yval;
        }

        public double getUval() {
            return uval;
        }

        public void setUval(double uval) {
            this.uval = uval;
        }

        public double getIval() {
            return ival;
        }

        public void setIval(double ival) {
            this.ival = ival;
        }

        public double getOval() {
            return oval;
        }

        public void setOval(double oval) {
            this.oval = oval;
        }

        public double getPval() {
            return pval;
        }

        public void setPval(double pval) {
            this.pval = pval;
        }

        public double getSl() {
            return sl;
        }

        public void setSl(double sl) {
            this.sl = sl;
        }

        public double getVal() {
            return val;
        }

        public void setVal(double val) {
            this.val = val;
        }

        public double getZval() {
            return zval;
        }

        public void setZval(double zval) {
            this.zval = zval;
        }
    }

    @Test
    public void test13() {
        java.util.List<SL> sls = new ArrayList<>();
        sls.add(new SL(0.17,222, 333,333,444,444,555,666,777));
        sls.add(new SL(0.10,222, 333,333,444,444,555,666,777));
        sls.add(new SL(0.13,222, 333,333,444,444,555,666,777));
        sls.add(new SL(0.03,222, 333,333,444,444,555,666,777));
        sls.add(new SL(0.15,222, 333,333,444,444,555,666,777));
        sls.add(new SL(0.08,222, 333,333,444,444,555,666,777));
        sls.add(new SL(0.08,222, 333,333,444,444,555,666,777));
        sls.add(new SL(0.08,222, 333,333,444,444,555,666,777));

        LinkedHashMap<String, java.util.List<SL>> linkedHashMap1 = new LinkedHashMap<String, java.util.List<SL>>();

        //3个一组

        //Map<Double, java.util.List<SL>> collect = sls.stream().collect(Collectors.groupingBy());
        //System.out.println(collect);
        java.util.List<java.util.List<SL>> partition = Lists.partition(sls, 3);
        System.out.println(partition);
    }

    @Test
    public void test07() throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Jesse\\Desktop\\cs.pdf"));

        BaseFont bfCN =
                BaseFont.createFont("C:/Windows/Fonts/simhei.ttf",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // 章的字体
        Font chFont = new Font(bfCN, 12, Font.NORMAL, BaseColor.BLUE);
        // 节的字体
        Font secFont = new Font(bfCN, 12, Font.NORMAL, new BaseColor(0, 204,
                255));
        // 正文的字体
        Font textFont = new Font(bfCN, 12, Font.NORMAL, BaseColor.BLACK);

        PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(DEST)));
        document.open();

        int chNum = 1;
        Chapter chapter = new Chapter(new Paragraph("Michael介绍", chFont),
                chNum++);

        Section section = chapter.addSection(new Paragraph("基本信息", secFont));
        section.setIndentation(10);
        section.setIndentationLeft(10);
        section.setBookmarkOpen(true);
        section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
        section.add(new Paragraph("苦逼的码农一枚。。。", textFont));

        Section section2 = chapter.addSection(new Paragraph("SNS", secFont));
        section2.setIndentation(10);
        section2.setIndentationLeft(10);
        section2.setBookmarkOpen(false);
        section2.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
        section2.add(new Paragraph("SNS地址分类：", textFont));

        section = section2.addSection(new Paragraph(new Chunk("我的博客", secFont)
                .setUnderline(0.2f, -2f).setAnchor("http://www.cnblogs.com/xiaoSY-learning")));
        section.setBookmarkOpen(false);
        section.setIndentation(10);
        section.setIndentationLeft(10);
        section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
        section.add(new Paragraph(new Chunk("我的blog地址：http://www.cnblogs.com/xiaoSY-learning/",
                textFont).setUnderline(0.2f, -2f).setAnchor(
                "http://www.cnblogs.com/xiaoSY-learning/")));
        section.add(new Paragraph("分享自己的技术心得。", textFont));

        section = section2.addSection(new Paragraph(new Chunk("我的weibo",
                secFont).setUnderline(0.2f, -2f).setAnchor(
                "http://weibo.com/u/2772113512")));
        section.setIndentation(10);
        section.setIndentationLeft(10);
        section.setBookmarkOpen(false);
        section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
        section.add(new Paragraph(new Chunk("我的weibo：http://weibo.com/u/2772113512",
                textFont).setUnderline(0.2f, -2f).setAnchor(
                "http://weibo.com/u/2772113512")));
        section.add(new Paragraph("发表下心情，分享下技术，转转乱七八糟的新闻。", textFont));

        section = section2.addSection(new Paragraph(new Chunk("twitter",
                secFont)));
        section.setIndentation(10);
        section.setIndentationLeft(10);
        section.setBookmarkOpen(false);
        section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
        section.add(new Paragraph(new Chunk("twitter：@suncto", textFont)
                .setUnderline(0.2f, -2f).setAnchor("twitter：twitter：twitter：")));
        section.add(new Paragraph("一个常常被墙的地方", textFont));

        LineSeparator line = new LineSeparator(1, 100, new BaseColor(204, 204,
                204), Element.ALIGN_CENTER, -2);

        Paragraph p_line = new Paragraph("分割线");
        p_line.add(line);
        chapter.add(p_line);
        document.add(chapter);

        chapter = new Chapter(new Paragraph("Miu的介绍", chFont), chNum++);
        section = chapter.addSection(new Paragraph("基本信息", secFont));
        section.setIndentation(10);
        section.setIndentationLeft(10);
        section.setBookmarkOpen(false);
        section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
        section.add(new Paragraph("90后一枚，喜欢美食和旅游。。。", textFont));

        document.add(chapter);

        document.close();

        writer.close();


    }

    @Test
    public void test08 () {
        //创建文本
        Document document = new Document();
        try {
            //写入文本到文件中
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Jesse\\Desktop\\Paragraph.pdf"));
            //打开文本
            document.open();
            //定义段落
            Paragraph paragraph = new Paragraph();
            //插入十条文本块到段落中
            int i=0;
            for(i=0; i<10; i++){
                Chunk chunk = new Chunk("This is a sentence which is long " + i + ". ");
                paragraph.add(chunk);
            }
            //添加段落
            document.add(paragraph);
            //关闭文本
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test09() throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Jesse\\Desktop\\cs.pdf"));
        MyFooter event = new MyFooter();
        writer.setPageEvent(event);

        document.open();

        for (int i = 0; i < 3; ) {
            i++;
            document.add(new Paragraph("Test " + i));
            document.newPage();
        }
        document.close();
    }

    @Test
    public void test10() throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Jesse\\Desktop\\cs.pdf"));
        MyFooter event = new MyFooter();
        writer.setPageEvent(event);

        document.open();
        float[] columnWidths = {1, 5, 5};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        Font f = new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
        PdfPCell cell = new PdfPCell(new Phrase("This is a header", f));
        cell.setBackgroundColor(GrayColor.GRAYBLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(3);
        table.addCell(cell);
        table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
        for (int i = 0; i < 2; i++) {
            table.addCell("#");
            table.addCell("Key");
            table.addCell("Value");
        }
        table.setHeaderRows(3);
        table.setFooterRows(1);
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        for (int counter = 1; counter < 101; counter++) {
            table.addCell(String.valueOf(counter));
            table.addCell("key " + counter);
            table.addCell("value " + counter);
        }
        document.add(table);
        document.close();
    }

    @Test
    public void test11() throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Jesse\\Desktop\\cs.pdf"));
        MyFooter event = new MyFooter();
        writer.setPageEvent(event);

        document.open();

        PdfPTable table = new PdfPTable(35);
        table.setTotalWidth(document.getPageSize().getWidth() - 80);
        table.setLockedWidth(true);
        PdfPCell contractor = new PdfPCell(new Phrase("XXXXXXXXXXXXX"));
        contractor.setColspan(5);
        table.addCell(contractor);
        PdfPCell workType = new PdfPCell(new Phrase("Refractory Works"));
        workType.setColspan(5);
        table.addCell(workType);
        PdfPCell supervisor = new PdfPCell(new Phrase("XXXXXXXXXXXXXX"));
        supervisor.setColspan(4);
        table.addCell(supervisor);
        PdfPCell paySlipHead = new PdfPCell(new Phrase("XXXXXXXXXXXXXXXX"));
        paySlipHead.setColspan(10);
        table.addCell(paySlipHead);
        PdfPCell paySlipMonth = new PdfPCell(new Phrase("XXXXXXX"));
        paySlipMonth.setColspan(2);
        table.addCell(paySlipMonth);
        PdfPCell blank = new PdfPCell(new Phrase(""));
        blank.setColspan(9);
        table.addCell(blank);
        document.add(table);
        document.close();
    }



}
