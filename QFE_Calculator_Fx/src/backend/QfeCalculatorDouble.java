package backend;
import java.util.function.*;
public class QfeCalculatorDouble {
	
	private QfeCalculatorUnits elevationUnit = QfeCalculatorUnits.METERS;
	private QfeCalculatorUnits temperatureUnit = QfeCalculatorUnits.CELSIUS;
	private UnaryOperator<Double> feetToMeters;
	private UnaryOperator<Double> fahrenheitToCelsius;
	
	/***
	 * Constructor
	 * Creates Instances of UnaryOperators for Conversions
	 * Defaults to Meters and Celsius!
	 */
	public QfeCalculatorDouble(){
		feetToMeters = x -> x / 3.2808;
		fahrenheitToCelsius = x -> ((x-32.0) * (5.0/9.0));
	}
	
	/***
	 * Calculates a QFE with given elevations, temperature and start QFE.
	 * Make sure you set the proper Unit mode using the methods specified.
	 * Should Feet and/or Fahrenheit be selected, a UnaryUnitConverter is used for conversion.
	 * Computation is ALWAYS done with Meters and Celsius.
	 * 
	 * @param Elevation of runway
	 * @param Elevation of target
	 * @param Atmospheric Temperature
	 * @param QFE on runway
	 * @return computed QFE at target point
	 */
	
	public double calculateQfe(double startElevation, double targetElevation, double temperature, double startQfe){
		
		
		if(elevationUnit.equals(QfeCalculatorUnits.FEET)){
			startElevation = feetToMeters.apply(startElevation);
			targetElevation = feetToMeters.apply(targetElevation);
		}
		
		if(temperatureUnit.equals(QfeCalculatorUnits.FAHRENHEIT)){
			temperature = fahrenheitToCelsius.apply(temperature);
		}
		
		
		double tempRes0, tempRes1, tempRes2, tempRes3, targetQfe;
		tempRes0 = 0.0065 * (targetElevation - startElevation);
		tempRes1 = 237.15 + temperature;
		tempRes2 = 1 - (tempRes0 / tempRes1);
		tempRes3 = Math.pow(tempRes2, 5.225);
		targetQfe = startQfe * tempRes3;
		return targetQfe;
	}

	/***
	 * 
	 */
	public void useFeet(){
		elevationUnit = QfeCalculatorUnits.FEET;
	}
	
	/***
	 * 
	 */
	public void useMeters(){
		elevationUnit = QfeCalculatorUnits.METERS;
	}
	
	/***
	 * 
	 */
	public void useCelsius(){
		temperatureUnit = QfeCalculatorUnits.CELSIUS;
	}
	
	/***
	 * 
	 */
	public void useFahrenheit(){
		temperatureUnit = QfeCalculatorUnits.FAHRENHEIT;
	}
	
	/***
	 * Enum for different types of units
	 * @author Alex
	 *
	 */
	public enum QfeCalculatorUnits {
		FEET,METERS,CELSIUS,FAHRENHEIT
	}

}
