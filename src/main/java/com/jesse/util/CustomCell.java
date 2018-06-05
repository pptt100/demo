package com.jesse.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * Created by Jesse on 2018/5/29.
 */
public class CustomCell implements PdfPCellEvent{
/*public void cellLayout(PdfPCell cell, Rectangle rect,
PdfContentByte[] canvas) {
PdfContentByte cb = canvas[PdfPTable.LINECANVAS];
cb.setLineDash(new float[] { 3.0f, 3.0f }, 0);
cb.stroke();
}*/

    @Override
    public void cellLayout(PdfPCell cell, Rectangle position,
                           PdfContentByte[] canvases) {

        // TODO Auto-generated method stub
        PdfContentByte cb = canvases[PdfPTable.LINECANVAS];
        cb.saveState();
        //cb.setLineCap(PdfContentByte.LINE_CAP_ROUND);
        //cb.setLineDash(0, 1, 1);
        cb.setLineWidth(0.5f);
        cb.setColorStroke(new BaseColor(136, 136, 136));
        cb.setLineDash(new float[] { 6.0f, 2.0f }, 0);
        cb.moveTo(position.getLeft(), position.getBottom());
        cb.lineTo(position.getRight(), position.getBottom());
        cb.stroke();
        cb.restoreState();

    }
}
