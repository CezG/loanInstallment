<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="txt/html; charset= UTF-8">
<link href="style.css" rel="stylesheet" type= "text/css">
	
<title>Formularz rat kredytowych</title>
</head>
<body>
	<div id= "container"> 
		<form action="loanInstallment"  method="post">
			<label> Kwota kredytu: <input type="number" min="50" id= "amountOfCredit" name="amountOfCredit" required/> PLN </label><br/>		
			<label> Ilość  
				<select name= "loanRepaymentUnit">
					<option value="months"> miesięcy </option>
					<option value= "years" > lat </option>
				</select>
					spłaty kredytu: <input type="number" min="1" max="480" id= "theNumberOfLoanInstallments" name="theNumberOfLoanInstallments" required/> </label><br/>
			<label> Oprocentowanie: <input type="number"  step ="0.01" min= "1" max="100" id= "loanIntterestRateInPercent" name="loanIntterestRateInPercent" required/> % </label><br/>	
			<label> Wybierz rodzaj kredytu: </label><br/>
			<label> malejacy 		<input type ="radio" name="typeOfCredit" value="1" required> </label><br/>
			<label> stały 			<input type ="radio" name="typeOfCredit" value="2" required> </label><br/>
			<input type="submit" name ="send" value="wyślij"/>
			<input type="submit" name= "pdf" value="pokaż w PDF"/>
		</form>
	</div>

</body>
</html>