package com.jesse.testitext;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Jesse on 2018/6/4.
 */
public class TestPdf2 {

    public static final String DEST1 = "C:\\Users\\Jesse\\Desktop\\column_width_example1.pdf";
    public static final String DEST2 = "C:\\Users\\Jesse\\Desktop\\column_width_example2.pdf";
    public static final String DEST3 = "C:\\Users\\Jesse\\Desktop\\column_width_example3.pdf";
    public static final String DEST4 = "C:\\Users\\Jesse\\Desktop\\column_width_example4.pdf";

    @Test
    public void test01() {
        File file1 = new File(DEST1);
        File file2 = new File(DEST1);
        File file3 = new File(DEST1);
        File file4 = new File(DEST1);
        file1.getParentFile().mkdirs();
        file2.getParentFile().mkdirs();
        file3.getParentFile().mkdirs();
        file4.getParentFile().mkdirs();
        try {
            createPdf1(DEST1);
            createPdf2(DEST2);
            createPdf3(DEST3);
            createPdf4(DEST4);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void createPdf1(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(dest));
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

    public void createPdf2(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4, 0, 0, 0, 0);
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(10);

        table.setWidthPercentage(100);
        table.setSpacingBefore(0f);
        table.setSpacingAfter(0f);

        // first row
        PdfPCell cell = new PdfPCell(new Phrase("DateRange"));
        cell.setColspan(10);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        cell.setBackgroundColor(new BaseColor(140, 221, 8));
        table.addCell(cell);

        table.addCell("Calldate");
        table.addCell("Calltime");
        table.addCell("Source");
        table.addCell("DialedNo");
        table.addCell("Extension");
        table.addCell("Trunk");
        table.addCell("Duration");
        table.addCell("Calltype");
        table.addCell("Callcost");
        table.addCell("Site");

        for (int i = 0; i < 100; i++) {
            table.addCell("date" + i);
            table.addCell("time" + i);
            table.addCell("source" + i);
            table.addCell("destination" + i);
            table.addCell("extension" + i);
            table.addCell("trunk" + i);
            table.addCell("dur" + i);
            table.addCell("toc" + i);
            table.addCell("callcost" + i);
            table.addCell("Site" + i);
        }
        document.add(table);
        document.close();
    }

    public void createPdf3(String dest) throws IOException, DocumentException {
        Rectangle pagesize = new Rectangle(300, 300);
        Document document = new Document(pagesize, 0, 0, 0, 0);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(1);
        table.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.setWidthPercentage(30);
        Font white = new Font();
        white.setColor(BaseColor.WHITE);
        PdfPCell cell = new PdfPCell(new Phrase(" Date" , white));
        cell.setBackgroundColor(BaseColor.BLACK);
        cell.setBorderColor(BaseColor.GRAY);
        cell.setBorderWidth(2f);
        table.addCell(cell);
        PdfPCell cellTwo = new PdfPCell(new Phrase("10/01/2015"));
        cellTwo.setBorderWidth(2f);
        table.addCell(cellTwo);
        document.add(table);
        document.newPage();
        table.setTotalWidth(90);
        PdfContentByte canvas = writer.getDirectContent();
        table.writeSelectedRows(0, -1, document.right() - 90, document.top(), canvas);
        document.close();
    }

    public void createPdf4(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A3.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(dest));
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
