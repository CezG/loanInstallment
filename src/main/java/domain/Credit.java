package domain;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Credit {

	private BigDecimal amountOfCredit;
	private double theNumberOfLoanInstallmentsInMonths;
	private int typeOfCredit;
	private double loanIntterestRate ;
	
	
	public Credit(BigDecimal amountOfCredit, double theNumberOfLoanInstallmentsInMonths, int typeOfCredit,double loanIntterestRate) {
		this.amountOfCredit = amountOfCredit;
		this.theNumberOfLoanInstallmentsInMonths = theNumberOfLoanInstallmentsInMonths;
		this.typeOfCredit = typeOfCredit;
		this.loanIntterestRate = loanIntterestRate;
	}
	
	
	public BigDecimal getAmountOfCredit() {
		return amountOfCredit;
	}

	public void setAmountOfCredit(BigDecimal amountOfCredit) {
		this.amountOfCredit = amountOfCredit;
	}
	public int getTypeOfCredit() {
		return typeOfCredit;
	}

	public void setTypeOfCredit(int typeOfCredit) {
		this.typeOfCredit = typeOfCredit;
	}

	public double getTheNumberOfLoanInstallmentsInMonths() {
		return theNumberOfLoanInstallmentsInMonths;
	}

	public void setTheNumberOfLoanInstallmentsInMonths(double theNumberOfLoanInstallmentsInMonths) {
		this.theNumberOfLoanInstallmentsInMonths =  theNumberOfLoanInstallmentsInMonths;
	}

	public double getLoanIntterestRate() {
		return loanIntterestRate;
	}

	public void setLoanIntterestRate(double loanIntterestRate) {
		this.loanIntterestRate = loanIntterestRate;
	}
	
	
	public boolean checkInsertValues() {
		
		boolean check = false;
		BigDecimal zero = new BigDecimal(0);
		
		if (getAmountOfCredit().compareTo(zero) <= 0 || getTheNumberOfLoanInstallmentsInMonths() <= 0 || getLoanIntterestRate() <=0 ) {
			System.out.println("Wartoœci musz¹ byæ wiêksze od zera");
		}else {check = true;}
		return check ;
	}


	public ArrayList <BigDecimal> listOfCalculatedLoanInstallmentForPernamentCredit() {
		
		double q=1+ getLoanIntterestRate()/12;
		double qPow = Math.pow( q, getTheNumberOfLoanInstallmentsInMonths());
		double x= 	qPow*((q-1)/(qPow-1));
		
		BigDecimal xNew = new BigDecimal(x);
		BigDecimal installment =  getAmountOfCredit().multiply(xNew) ;
		installment = installment.setScale(2, RoundingMode.FLOOR);
			
		ArrayList<BigDecimal> listOfInstallments = new ArrayList<>();
		
		for(int i=0;i < getTheNumberOfLoanInstallmentsInMonths() ; i++) {		
			listOfInstallments.add(installment);
		}
		return  listOfInstallments;
	}
	
	public ArrayList<BigDecimal> listOfCalculatedLoanInstallmentForDecreasingCredit() {
		
		ArrayList<BigDecimal> listOfInstallments = new ArrayList<>();
		
		for(int theNumberOfTheInstallmentYouAreLookingFor = 0; theNumberOfTheInstallmentYouAreLookingFor < getTheNumberOfLoanInstallmentsInMonths(); theNumberOfTheInstallmentYouAreLookingFor++ ) {
			
			double value = 1+(getTheNumberOfLoanInstallmentsInMonths()-theNumberOfTheInstallmentYouAreLookingFor+1)*getLoanIntterestRate()/12 ;
			BigDecimal	installment = getAmountOfCredit().divide(new BigDecimal (getTheNumberOfLoanInstallmentsInMonths()), 2,RoundingMode.FLOOR ).multiply(new BigDecimal(value));
			installment = installment.setScale(2, RoundingMode.FLOOR);
			listOfInstallments.add(installment);
		} 
		return listOfInstallments;
	}
	
	public BigDecimal checkFullValueOfLoan (ArrayList<BigDecimal> listOfInstallments) {
		BigDecimal fullValueOfLoan = new BigDecimal(0);
		for( BigDecimal installment : listOfInstallments ) {
			 fullValueOfLoan =  fullValueOfLoan.add(installment) ;   
		}
		
		return fullValueOfLoan;
	}
	
	public ArrayList<BigDecimal> showChosenTypeOfCredit (Credit applicationForCredit) {
		
		ArrayList<BigDecimal> chosenTypeOfCredit = new ArrayList<>();	
		
		switch(applicationForCredit.getTypeOfCredit()) {
			case 1:   chosenTypeOfCredit = applicationForCredit.listOfCalculatedLoanInstallmentForDecreasingCredit(); break;		
			case 2:   chosenTypeOfCredit = applicationForCredit.listOfCalculatedLoanInstallmentForPernamentCredit(); break;
			}	
		return chosenTypeOfCredit;
	}
	
	
}
