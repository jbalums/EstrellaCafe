/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reports;

import com.DBCon.DBClass;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.orders.Trans;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mahal
 */
public class OrderReports {
     private ArrayList<OrderTableReport> orders = new ArrayList<OrderTableReport>();
     
     
    public DBClass db = new DBClass(); 
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
    private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 28,Font.BOLD,BaseColor.BLACK );
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
    
    public OrderReports(ArrayList<OrderTableReport> orders, String from, String to){
        this.orders = orders;
        
         Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, YYYY_HHmmss");
        String dateStr =format.format(curDate);
        String FILE = "c:/temp/orders/OrderReport_"+dateStr+".pdf";
        
        try {
          Document document = new Document();
          PdfWriter.getInstance(document, new FileOutputStream(FILE));
          document.open();
          // Left
          Paragraph paragraph = new Paragraph();
          // Centered
          paragraph = new Paragraph("Offical Reciept");
          paragraph.setAlignment(Element.ALIGN_CENTER);
          document.add(paragraph);
          // Left
          paragraph = new Paragraph("DATE RANGE : "+from+"~to~"+to);
          paragraph.setAlignment(Element.ALIGN_LEFT);
          document.add(paragraph);
          Paragraph subPara = new Paragraph("Subcategory 1", subFont);
           // Section subCatPart = catPart.addSection(subPara);
            //subCatPart.add(new Paragraph("Hello"));
          
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(80);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        float[] width = {1f, 1f, 1f, 1f, 1f};
        table.setWidths(width);
        PdfPCell cell1 = new PdfPCell(new Paragraph("Order No.", catFont));
        PdfPCell cell2 = new PdfPCell(new Paragraph("Date Ordered", catFont));
        PdfPCell cell3 = new PdfPCell(new Paragraph("Product Ordered",catFont));
        PdfPCell cell4 = new PdfPCell(new Paragraph("Qty. Ordered",catFont));
        PdfPCell cell5 = new PdfPCell(new Paragraph("Payable",catFont));
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
        
        for(int i = 0 ; i < orders.size() ; i++){
            
            PdfPCell cel1 = new PdfPCell(new Paragraph(orders.get(i).getID()));
            PdfPCell cel2 = new PdfPCell(new Paragraph(orders.get(i).getDate_ordered()));
            PdfPCell cel3 = new PdfPCell(new Paragraph(orders.get(i).getProduct_order()));
            //Float f = Float.parseFloat(orders.get(i).getQty_ordered()) * Float.parseFloat(orders.get(i).getPrice());
            PdfPCell cel4 = new PdfPCell(new Paragraph(orders.get(i).getQty_ordered()));
            PdfPCell cel5 = new PdfPCell(new Paragraph(orders.get(i).getPayable()));
            
            table.addCell(cel1);
            table.addCell(cel2);
            table.addCell(cel3);
            table.addCell(cel4);
            table.addCell(cel5);
        }
        
        document.add(table);
        
        document.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    public ArrayList<OrderTableReport> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<OrderTableReport> orders) {
        this.orders = orders;
    }
    
    
}
