package servlets;
import domain.Credit;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/loanInstallment")
public class LoanInstallmentServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Credit applicationForCredit = retriveApplicationForCreditFromRequest(request);
		ArrayList<BigDecimal> chosenCredit = applicationForCredit.showChosenTypeOfCredit(applicationForCredit);
		
		if (request.getParameter("send") !=null){	
			showOnWebPage(request, response, applicationForCredit, chosenCredit);
	
		}else if (request.getParameter("pdf") !=null){
			makePdf(request, response, applicationForCredit, chosenCredit);
		}	
	}
	
	private Credit retriveApplicationForCreditFromRequest (HttpServletRequest request) {
	
		BigDecimal amountOfCredit = new BigDecimal(request.getParameter("amountOfCredit"));
		String loanRepaymnetUnit = request.getParameter("loanRepaymentUnit");
		Double theNumberOfLoanInstallments  =Double.parseDouble(request.getParameter("theNumberOfLoanInstallments"));
		Double loanIntterestRateInPercentToRate = (Double.parseDouble(request.getParameter("loanIntterestRateInPercent"))/100);
		int typeOfCredit = Integer.parseInt (request.getParameter("typeOfCredit"));
		
		Double theNumberOfLoanInstallmentsInMonths = null;
		if(loanRepaymnetUnit.equals("months")) theNumberOfLoanInstallmentsInMonths= theNumberOfLoanInstallments;
		else if(loanRepaymnetUnit.equals("years")) theNumberOfLoanInstallmentsInMonths= theNumberOfLoanInstallments*12;
		
		Credit result = new Credit(amountOfCredit, theNumberOfLoanInstallmentsInMonths, typeOfCredit,loanIntterestRateInPercentToRate);	
		
		return result;
	}
	
	
	private void showOnWebPage(HttpServletRequest request, HttpServletResponse response, Credit applicationForCredit, 
			ArrayList<BigDecimal> chosenCredit ) throws IOException, ServletException {
		
		response.setContentType("text/html");  // text/plaintext	
		
		PrintWriter out= response.getWriter();
		out.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() +  "/style.css' />");

		
		out.println("<h1>Wielkosc kredytu " + applicationForCredit.getAmountOfCredit() + "</h1>");
		out.println("<table><caption>"+"Twoj kredyt"  +"</caption>"
									+ "<tr><th>Nr Raty </th> <th> Wielkosc raty </th> ");
		int installmentNumber= 1 ;
		for (BigDecimal installment : chosenCredit) {
			out.println("<tr><td>"+ installmentNumber+ "</td><td>"+ installment +"</td></tr>" );
			installmentNumber++;
		}
		out.println("<tr> Laczna suma rat kredytu wynosi: "+ applicationForCredit.checkFullValueOfLoan(chosenCredit)+"</tr></table>");
	}
	
	
	private void makePdf(HttpServletRequest request, HttpServletResponse response, Credit applicationForCredit, 
			ArrayList<BigDecimal> chosenCredit ) throws IOException, ServletException {
		
		response.setContentType("application/pdf");
	        try {
	            Document document = new Document();
	            PdfWriter.getInstance(document, response.getOutputStream());
	            document.open();
	            document.add(new Paragraph("Wielkosc kredytu " + applicationForCredit.getAmountOfCredit() +"\n"));
	                  
	            int installmentNumber= 1 ;
				for (BigDecimal installment : chosenCredit) {
					  document.add(new Paragraph( installmentNumber+ ". "+ installment +"\n" ));
					installmentNumber++;
				}
				  document.add(new Paragraph("\n Laczna suma rat kredytu wynosi: "
				     + applicationForCredit.checkFullValueOfLoan(chosenCredit)+"\n"));
	            
	            document.close();
	        } catch (DocumentException de) {
	            throw new IOException(de.getMessage());
	        }
	              
			/*
			PdfPTable table = new PdfPTable(3);	
			PdfPCell cell = new PdfPCell(new Phrase("Wielkosc kredytu " + applicationForCredit.getAmountOfCredit() ));
	        cell.setColspan(3);
	        table.addCell(cell);
	      */
	}	
}

