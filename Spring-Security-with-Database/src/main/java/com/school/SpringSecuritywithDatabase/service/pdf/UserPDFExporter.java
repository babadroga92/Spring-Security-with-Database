package com.school.SpringSecuritywithDatabase.service.pdf;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.school.SpringSecuritywithDatabase.model.Student;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import javax.servlet.http.HttpServletResponse;

public class UserPDFExporter {

    private List<Student> listStudents;

    public UserPDFExporter(List<Student> listStudents) {
        this.listStudents = listStudents;
    }
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Student ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);  // Add an empty cell below the "Name" column header

        cell.setPhrase(new Phrase("User Id", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Student student : listStudents) {
            table.addCell(String.valueOf(student.getId()));
            table.addCell(student.getName());
            table.addCell(String.valueOf(student.getUser().getId()));
        }
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        //Creating the doc object
        Document document = new Document(PageSize.A4);
        //Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());
        //opening the doc to modify it
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
        // creating paragraph
        Paragraph p = new Paragraph("List of Students", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);
        // this is where the table is created with a custom number of columns
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f});
        table.setSpacingBefore(5);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }
}
