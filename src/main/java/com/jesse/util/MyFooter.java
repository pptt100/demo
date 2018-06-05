package com.jesse.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.IOException;

/**
 * Created by Jesse on 2018/6/1.
 */
public class MyFooter extends PdfPageEventHelper {
    private static BaseFont bfChinese;

    static {
        try {
            bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Font ffont = new Font(bfChinese, 12, Font.NORMAL);

    private static int pageSize = 0;

    public MyFooter() throws IOException, DocumentException {
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        //Phrase header = new Phrase("this is a header", ffont);
        Phrase footer = new Phrase("第" + (++pageSize) + "页", ffont);
        /*ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                header,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.top() + 10, 0);*/
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                footer,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.bottom() - 10, 0);
    }
}
