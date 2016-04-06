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
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.orders.Trans;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mahal
 */
public class Reciept {
    private ArrayList<Trans> orders = new ArrayList<Trans>();
    public DBClass db = new DBClass(); 
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
    private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 28,Font.BOLD,BaseColor.BLACK );
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
    
    
    public Reciept(ArrayList<Trans> orders, String lastid){
        this.orders = orders;
        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, YYYY_HHmmss");
        String dateStr =format.format(curDate);
        String FILE = "c:/temp/reciepts/Reciept_"+dateStr+".pdf";
        
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
          paragraph = new Paragraph("Order #"+lastid);
          paragraph.setAlignment(Element.ALIGN_LEFT);
          document.add(paragraph);
          Paragraph subPara = new Paragraph("Subcategory 1", subFont);
           // Section subCatPart = catPart.addSection(subPara);
            //subCatPart.add(new Paragraph("Hello"));
          
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(80);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        float[] width = {1f, 1f, 1f, 1f};
        table.setWidths(width);
        PdfPCell cell1 = new PdfPCell(new Paragraph("Product", catFont));
        PdfPCell cell2 = new PdfPCell(new Paragraph("Unit Price", catFont));
        PdfPCell cell3 = new PdfPCell(new Paragraph("Quantity",catFont));
        PdfPCell cell4 = new PdfPCell(new Paragraph("Payable",catFont));
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        for(int i = 0 ; i < orders.size() ; i++){
            
            PdfPCell cel1 = new PdfPCell(new Paragraph(getProductName(orders.get(i).getProduct_id())));
            PdfPCell cel2 = new PdfPCell(new Paragraph(orders.get(i).getPrice()));
            PdfPCell cel3 = new PdfPCell(new Paragraph(orders.get(i).getQty_ordered()));
            Float f = Float.parseFloat(orders.get(i).getQty_ordered()) * Float.parseFloat(orders.get(i).getPrice());
            PdfPCell cel4 = new PdfPCell(new Paragraph(f.toString()));
            
            table.addCell(cel1);
            table.addCell(cel2);
            table.addCell(cel3);
            table.addCell(cel4);
        }
        
        document.add(table);
        
        document.close();
        openAdobeReader(FILE);
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    public Reciept() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void openAdobeReader(String FILE) {
        //if (Desktop.isDesktopSupported()) {
            try {
                //File myFile = new File("C:\\Users\\Guest\\Documents\\Monthly Reports.pdf");
                //File myFile = new File("C:\\Users\\chinchan\\Desktop\\Monthly Sold Stocks Reports.pdf");
                 File myFile = new File(FILE);
                if(myFile.toString().endsWith(".pdf"))
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ myFile);
                else
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        //}
        
    }
    
    public ArrayList<Trans> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Trans> orders) {
        this.orders = orders;
    }
    
    public String getProductName(String ID) throws SQLException, ClassNotFoundException{
       String n="";
       com.mysql.jdbc.Connection conn = db.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT name from products where ID = "+ID);
        while (rs.next()) {
            n = rs.getString("name");
        }
        return n;
   }
    
    
}
