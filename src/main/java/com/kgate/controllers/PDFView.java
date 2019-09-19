package com.kgate.controllers;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kgate.entity.Salary;
import com.kgate.entity.User;
import com.kgate.entity.UserLeaves;
 
public class PDFView extends AbstractITextPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) model.get("user");
		Salary sal = (Salary) model.get("list");
		UserLeaves leave = (UserLeaves) model.get("leave");
		String month =(String) model.get("month");
		String year = (String) model.get("year");
		
		PdfPTable table1 = new PdfPTable(1);
		Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
		font1.setColor(BaseColor.WHITE);
		
		PdfPCell cell1 = new PdfPCell();
		cell1.setBackgroundColor(BaseColor.BLUE);
		cell1.setPadding(8);
		
		cell1.setPhrase(new Phrase("Salary Slip For The Month of "+month+"-"+year, font1));
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(cell1);
		
		PdfPCell cell2 = new PdfPCell();
		cell2.setBackgroundColor(BaseColor.CYAN);
		cell2.setPadding(8);
		
		cell2.setPhrase(new Phrase(" "));
		table1.addCell(cell2);
		
		document.add(table1);
		
		PdfPTable table = new PdfPTable(4);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(BaseColor.WHITE);
		
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.BLUE);
		cell.setPadding(8);
		
		cell.setPhrase(new Phrase("Employee Name", font));
		table.addCell(cell);
		table.addCell(user.getFname()+" "+user.getLname());
		
		cell.setPhrase(new Phrase("Working Days", font));
		table.addCell(cell);
		String wd = Integer.toString(leave.getWorkingDays());
		table.addCell(wd);

		cell.setPhrase(new Phrase("Designation", font));
		table.addCell(cell);
		table.addCell(user.getDesignation());
 
		cell.setPhrase(new Phrase("Total Days Worked", font));
		table.addCell(cell);
		String tdw = Float.toString(leave.getTotalDaysWorked());		
		table.addCell(tdw);
		
		cell.setPhrase(new Phrase("Employee ID", font));
		table.addCell(cell);
		table.addCell(user.getEmpCode());
		
		cell.setPhrase(new Phrase("Total Leave", font));
		table.addCell(cell);
		String tl = Float.toString(leave.getTotalLeaves());		
		table.addCell(tl);
		
		cell.setPhrase(new Phrase("Date of Joining", font));
		table.addCell(cell);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String jd = dateFormat.format(user.getJoiningDate());
		table.addCell(jd);
		
		cell.setPhrase(new Phrase("Paid Leave", font));
		table.addCell(cell);
		String pl = Float.toString(leave.getPaidLeaves());		
		table.addCell(pl);
		
		cell.setPhrase(new Phrase("", font));
		table.addCell(cell);
		table.addCell("");
		
		cell.setPhrase(new Phrase("Balance Leave", font));
		table.addCell(cell);
		String bl = Float.toString(leave.getBalanceLeaves());		
		table.addCell(bl);
		
		document.add(table);

		PdfPTable table6 = new PdfPTable(1);
		Font font4 = FontFactory.getFont(FontFactory.HELVETICA);
		font4.setColor(BaseColor.WHITE);
		PdfPCell cell4 = new PdfPCell();
		cell4.setBackgroundColor(BaseColor.CYAN);
		cell4.setPadding(8);
		
		cell4.setPhrase(new Phrase(" "));
		table6.addCell(cell4);
		
		document.add(table6);
		
		PdfPTable table2 = new PdfPTable(2);
		Font font2 = FontFactory.getFont(FontFactory.HELVETICA);
		font2.setColor(BaseColor.WHITE);
		
		PdfPCell cell3 = new PdfPCell();
		cell3.setBackgroundColor(BaseColor.BLUE);
		cell3.setPadding(8);
		
		cell3.setPhrase(new Phrase("Earnings", font));
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell3);
		cell3.setPhrase(new Phrase("Deductions", font));
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell3);
		
		document.add(table2);

		PdfPTable table3 = new PdfPTable(4);
		
		Font font3 = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(BaseColor.WHITE);
		
		PdfPCell cell5 = new PdfPCell();
		cell5.setBackgroundColor(BaseColor.BLUE);
		cell5.setPadding(8);
		
		cell5.setPhrase(new Phrase("Basic Salary", font));
		table3.addCell(cell5);
		String bs = Float.toString(sal.getBasicSalary());
		table3.addCell(bs);
		
		cell5.setPhrase(new Phrase("Professional Tax", font));
		table3.addCell(cell5);
		String pt = Float.toString(sal.getProfessionalTax());
		table3.addCell(pt);

		
		cell5.setPhrase(new Phrase("HRA", font));
		table3.addCell(cell5);
		String hra = Float.toString(sal.getHra());
		table3.addCell(hra);

		cell5.setPhrase(new Phrase("", font));
		table3.addCell(cell5);
		table3.addCell("");
		
		cell5.setPhrase(new Phrase("Conveyance Allowances", font));
		table3.addCell(cell5);
		String ca = Float.toString(sal.getConveyanceAllowances());
		table3.addCell(ca);

		cell5.setPhrase(new Phrase("", font));
		table3.addCell(cell5);
		table3.addCell("");
		
		cell5.setPhrase(new Phrase("Medical Allowances", font));
		table3.addCell(cell5);
		String ma = Float.toString(sal.getMedicalAllowances());
		table3.addCell(ma);

		cell5.setPhrase(new Phrase("", font));
		table3.addCell(cell5);
		table3.addCell("");
		
		cell5.setPhrase(new Phrase("Other Allowances", font));
		table3.addCell(cell5);
		String oa = Float.toString(sal.getOtherAllowances());
		table3.addCell(oa);

		cell5.setPhrase(new Phrase("", font));
		table3.addCell(cell5);
		table3.addCell("");
		
		cell5.setPhrase(new Phrase("", font));
		table3.addCell(cell5);
		table3.addCell("");
		
		cell5.setPhrase(new Phrase("", font));
		table3.addCell(cell5);
		table3.addCell("");
		
		cell5.setPhrase(new Phrase("Earnings for the month", font));
		table3.addCell(cell5);
		String em = Float.toString(sal.getTotalMonthlySalary());
		table3.addCell(em);
		
		cell5.setPhrase(new Phrase("", font));
		table3.addCell(cell5);
		table3.addCell("");
		
		cell5.setPhrase(new Phrase("", font));
		table3.addCell(cell5);
		table3.addCell("");
		
		cell5.setPhrase(new Phrase("", font));
		table3.addCell(cell5);
		table3.addCell("");
		
		cell5.setPhrase(new Phrase("", font));
		table3.addCell(cell5);
		table3.addCell("");
		
		cell5.setPhrase(new Phrase("", font));
		table3.addCell(cell5);
		table3.addCell("");
		
		cell5.setPhrase(new Phrase("Total Earnings", font));
		table3.addCell(cell5);
		String te = Float.toString(sal.getNetSalary());
		table3.addCell(te);
		
		cell5.setPhrase(new Phrase("Total Deductions", font));
		table3.addCell(cell5);
		String td = Float.toString(sal.getTotalDeduction());
		table3.addCell(td);
		  
		document.add(table3);

	} 
}