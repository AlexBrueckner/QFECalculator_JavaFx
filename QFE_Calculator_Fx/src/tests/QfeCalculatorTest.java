package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import backend.QfeCalculatorDouble;
public class QfeCalculatorTest {
	

	@Test
	public void test() {
		try{
			
		
		QfeCalculatorDouble calc = new QfeCalculatorDouble();
		assertNotNull(calc);
		
		ArrayList<Double> qfeResults = new ArrayList<Double>();
		//qfeResults.add(calc.calculateQfe(startElevation, targetElevation, temperature, startQfe))
		//Expected result: ~806 
		calc.useCelsius();
		calc.useMeters();
		qfeResults.add(calc.calculateQfe(700, 2000, 20, 960));
		
		
		//Expected result: ~905
		calc.useFahrenheit();
		calc.useFeet();
		//thanks for nothing autoboxing
		qfeResults.add(calc.calculateQfe(700, 2000, 20, 960));
		//Put them all out
		qfeResults.forEach(System.out::println);
		}
		
		catch(Throwable e){
			e.printStackTrace();
			fail();
		}
	}

}
