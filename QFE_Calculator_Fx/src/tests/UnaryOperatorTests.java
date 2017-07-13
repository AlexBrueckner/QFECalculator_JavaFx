package tests;

import static org.junit.Assert.*;

import java.util.function.UnaryOperator;

import org.junit.Test;

public class UnaryOperatorTests {

	@Test
	public void test() {
		
		
		UnaryOperator<Double> feetToMeters, fahrenheitToCelsius;
		
		
		feetToMeters = x -> x / 3.2808;
		fahrenheitToCelsius = x -> ((x-32.0) * (5.0/9.0));
		assertNotNull(feetToMeters);
		assertNotNull(fahrenheitToCelsius);
		
		try{
		System.out.println("------ ft -> m ------");
		System.out.println(feetToMeters.apply(1.0));
		System.out.println(feetToMeters.apply(10.0));
		System.out.println(feetToMeters.apply(100.0));
		System.out.println(feetToMeters.apply(1000.0));
		System.out.println(feetToMeters.apply(10000.0));
		System.out.println(feetToMeters.apply(-10000.0));
		System.out.println(feetToMeters.apply(-1000.0));
		System.out.println(feetToMeters.apply(-100.0));
		System.out.println(feetToMeters.apply(-10.0));
		System.out.println(feetToMeters.apply(-1.0));
		System.out.println("------ F -> C ------");
		System.out.println(fahrenheitToCelsius.apply(1.0));
		System.out.println(fahrenheitToCelsius.apply(10.0));
		System.out.println(fahrenheitToCelsius.apply(100.0));
		System.out.println(fahrenheitToCelsius.apply(1000.0));
		System.out.println(fahrenheitToCelsius.apply(10000.0));
		System.out.println(fahrenheitToCelsius.apply(-10000.0));
		System.out.println(fahrenheitToCelsius.apply(-1000.0));
		System.out.println(fahrenheitToCelsius.apply(-100.0));
		System.out.println(fahrenheitToCelsius.apply(-10.0));
		System.out.println(fahrenheitToCelsius.apply(-1.0));		
		//Test OK
		}
		catch(Exception e){
			e.printStackTrace();
			fail();
		}
		
	}

}
