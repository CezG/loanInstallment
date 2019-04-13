package domain;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestCredit extends Mockito {

		Credit decreasingCredit = new Credit(new BigDecimal(100000),48, 1, 0.18 );
		Credit pernamentCredit = new Credit(new BigDecimal(100000),48, 2, 0.18);
	
	@Test
	public void testCredit() {
		assertNotNull(decreasingCredit);
		
	}


	@Test
	public void testCheckInsertValues_goodValues() {
		assertTrue(decreasingCredit.checkInsertValues());
	}
	
	@Test
	public void testCheckInsertValues_wrongAmountOfCredit() {
		Credit credit = new Credit(new BigDecimal(0),48, 1, 0.18 );
		assertFalse(credit.checkInsertValues());
	}
	
	
	@Test
	public void testCheckFullValueOfLoan_forDecreasingCredit() {
		ArrayList<BigDecimal> decreasingLoan = decreasingCredit.showChosenTypeOfCredit(decreasingCredit);
		BigDecimal actual = decreasingCredit.checkFullValueOfLoan(decreasingLoan);
		assertEquals("138249.36" ,actual.toString());
	}
	
	@Test
	public void testShowChosenTypeOfCredit_shouldBeDecreasing() {
		ArrayList<BigDecimal> actual = decreasingCredit.showChosenTypeOfCredit(decreasingCredit);
		
		assertEquals(decreasingCredit.listOfCalculatedLoanInstallmentForDecreasingCredit(), actual);
	}

	@Test
	public void testCheckFullValueOfLoan_forPernamentCredit() {
		ArrayList<BigDecimal> pernamentLoan = pernamentCredit.showChosenTypeOfCredit(pernamentCredit);
		BigDecimal actual = pernamentCredit.checkFullValueOfLoan(pernamentLoan);
		assertEquals("140999.52" ,actual.toString());
	}

	@Test
	public void testShowChosenTypeOfCredit_shouldBePernament() {
		ArrayList<BigDecimal> actual = pernamentCredit.showChosenTypeOfCredit(pernamentCredit);
		assertEquals(pernamentCredit.listOfCalculatedLoanInstallmentForPernamentCredit(), actual);
	}
	
	
}
