package frontend;

import java.io.StringWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;
import com.sun.javafx.application.HostServicesDelegate;

import backend.QfeCalculatorDouble;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class QfeCalculatorController {
	private Stage myStage;

	public QfeCalculatorController(Stage stage) {
		myStage = stage;
	}

	private QfeCalculatorDouble calc = new QfeCalculatorDouble();

	@FXML
	private ImageView f99thLogo;
	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="textfieldTemperature"
	private TextField textfieldTemperature; // Value injected by FXMLLoader

	@FXML // fx:id="buttonCalc"
	private Button buttonCalc; // Value injected by FXMLLoader

	@FXML // fx:id="checkboxFeet"
	private CheckBox checkboxFeet; // Value injected by FXMLLoader

	@FXML // fx:id="checkboxCelsius"
	private CheckBox checkboxCelsius; // Value injected by FXMLLoader

	@FXML // fx:id="textfieldRunwayElevation"
	private TextField textfieldRunwayElevation; // Value injected by FXMLLoader

	@FXML // fx:id="textfieldRunwayQfe"
	private TextField textfieldRunwayQfe; // Value injected by FXMLLoader

	@FXML // fx:id="checkboxMeters"
	private CheckBox checkboxMeters; // Value injected by FXMLLoader

	@FXML // fx:id="checkboxFahrenheit"
	private CheckBox checkboxFahrenheit; // Value injected by FXMLLoader

	@FXML // fx:id="textfieldTargetQfe"
	private TextField textfieldTargetQfe; // Value injected by FXMLLoader

	@FXML // fx:id="textfieldTargetElevation"
	private TextField textfieldTargetElevation; // Value injected by FXMLLoader

	@FXML // This method is called by the FXMLLoader when initialization is
			// complete
	void initialize() {
		assert textfieldTemperature != null : "fx:id=\"textfieldTemperature\" was not injected: check your FXML file 'qfecalculator.fxml'.";
		assert buttonCalc != null : "fx:id=\"buttonCalc\" was not injected: check your FXML file 'qfecalculator.fxml'.";
		assert checkboxFeet != null : "fx:id=\"checkboxFeet\" was not injected: check your FXML file 'qfecalculator.fxml'.";
		assert checkboxCelsius != null : "fx:id=\"checkboxCelsius\" was not injected: check your FXML file 'qfecalculator.fxml'.";
		assert textfieldRunwayElevation != null : "fx:id=\"textfieldRunwayElevation\" was not injected: check your FXML file 'qfecalculator.fxml'.";
		assert textfieldRunwayQfe != null : "fx:id=\"textfieldRunwayQfe\" was not injected: check your FXML file 'qfecalculator.fxml'.";
		assert checkboxMeters != null : "fx:id=\"checkboxMeters\" was not injected: check your FXML file 'qfecalculator.fxml'.";
		assert checkboxFahrenheit != null : "fx:id=\"checkboxFahrenheit\" was not injected: check your FXML file 'qfecalculator.fxml'.";
		assert textfieldTargetQfe != null : "fx:id=\"textfieldTargetQfe\" was not injected: check your FXML file 'qfecalculator.fxml'.";
		assert textfieldTargetElevation != null : "fx:id=\"textfieldTargetElevation\" was not injected: check your FXML file 'qfecalculator.fxml'.";
		checkboxMeters.setSelected(true);
		checkboxCelsius.setSelected(true);
		textfieldTargetQfe.setEditable(false);
		f99thLogo.setOnMouseClicked(e -> {
			try {

				String url = "http://f99th.com";
				if (Desktop.isDesktopSupported()) {
					// Windows
					Desktop.getDesktop().browse(new URI(url));
				} else {
					// Ubuntu
					Runtime runtime = Runtime.getRuntime();
					runtime.exec("/usr/bin/firefox -new-window " + url);
				}

			} catch (IOException ioe) {
				ioe.printStackTrace();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error");
				alert.setHeaderText(
						"Oh no! I couldn't get the browser to show you our awesome site. Please send this stacktrace to the dev so he can fix it!");
				StringWriter sW = new StringWriter();
				PrintWriter pW = new PrintWriter(sW);
				ioe.printStackTrace(pW);
				pW.flush();
				alert.setContentText(sW.toString());
				pW.close();
				alert.showAndWait();

			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error");
				alert.setHeaderText(
						"Oh no! I couldn't get the browser to show you our awesome site. Please send this stacktrace to the dev so he can fix it!");
				StringWriter sW = new StringWriter();
				PrintWriter pW = new PrintWriter(sW);
				e1.printStackTrace(pW);
				pW.flush();
				alert.setContentText(sW.toString());
				pW.close();
				alert.showAndWait();
			}
		});

		// Onclick Routines for the Selection boxes
		checkboxMeters.setOnAction(e -> {
			checkboxFeet.setSelected(false);
			calc.useMeters();
		});

		checkboxFeet.setOnAction(e -> {
			checkboxMeters.setSelected(false);
			calc.useFeet();
		});

		checkboxFahrenheit.setOnAction(e -> {
			checkboxCelsius.setSelected(false);
			calc.useFahrenheit();
		});

		checkboxCelsius.setOnAction(e -> {
			checkboxFahrenheit.setSelected(false);
			calc.useCelsius();
		});
		// Handler Routine for the button
		buttonCalc.setOnAction(e -> {
			System.out.println("Button clicked");
			double targetElevation = Float.NaN, startElevation = Float.NaN, startQfe = Float.NaN,
					temperature = Float.NaN, targetQfe = Float.NaN;
			// First try/catch Block. This will catch anything that is not
			// supposed to happen. Any Input parsing happens inside it,
			// Blocks will be catching the corresponding exception themselves!
			try {
				// Parse Runway Elevation
				try {
					System.out.println("Parsing start Elevation from: " + textfieldRunwayElevation.getText());
					startElevation = Double.parseDouble(textfieldRunwayElevation.getText());
					System.out.println("Parsing succesful, new value: " + startElevation);
				} catch (NullPointerException npe) {
					npe.printStackTrace();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Error");
					alert.setHeaderText("Check your inputs mate!");
					alert.setContentText("Found no text for the runway elevation, you sure you put it in?");
					alert.showAndWait();
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Error");
					alert.setHeaderText("Check your inputs mate!");
					alert.setContentText("Could not parse the runway elevation, you sure you got the format right?");
					alert.showAndWait();
				}
				// Parse target elevation
				try {
					System.out.println("Parsing Target Elevation from: " + textfieldTargetElevation.getText());
					targetElevation = Double.parseDouble(textfieldTargetElevation.getText());
					System.out.println("Parsing succesful, new value: " + targetElevation);
				} catch (NullPointerException npe) {
					npe.printStackTrace();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Error");
					alert.setHeaderText("Check your inputs mate!");
					alert.setContentText("Found no text for the target elevation, you sure you put it in?");
					alert.showAndWait();
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Error");
					alert.setHeaderText("Check your inputs mate!");
					alert.setContentText("Could not parse the target elevation, you sure you got the format right?");
					alert.showAndWait();
				}
				// Parse Temperature
				try {
					System.out.println("Parsing Temperature from: " + textfieldTemperature.getText());
					temperature = Double.parseDouble(textfieldTemperature.getText());
					System.out.println("Parsing succesful, new value: " + temperature);
				} catch (NullPointerException npe) {
					npe.printStackTrace();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Error");
					alert.setHeaderText("Check your inputs mate!");
					alert.setContentText("Found no text for the temperature, you sure you put it in?");
					alert.showAndWait();
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Error");
					alert.setHeaderText("Check your inputs mate!");
					alert.setContentText("Could not parse the temperature, you sure you got the format right?");
					alert.showAndWait();
				}
				// Parse Start QFE
				try {
					System.out.println("Parsing QFE from: " + textfieldRunwayQfe.getText());
					startQfe = Double.parseDouble(textfieldRunwayQfe.getText());
					System.out.println("Parsing succesful, new value: " + startQfe);
				} catch (NullPointerException npe) {
					npe.printStackTrace();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Error");
					alert.setHeaderText("Check your inputs mate!");
					alert.setContentText("Found no text for the QFE, you sure you put it in?");
					alert.showAndWait();
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Error");
					alert.setHeaderText("Check your inputs mate!");
					alert.setContentText("Could not parse the QFE, you sure you got the format right?");
					alert.showAndWait();
				}

				// Calculate target QFE and set Text
				targetQfe = calc.calculateQfe(startElevation, targetElevation, temperature, startQfe);
				textfieldTargetQfe.setText(new Double(targetQfe).toString());

			}
			// This REALLY should not happen at this point! Inner Catches will
			// check if the strings exist!
			catch (Exception ex) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error");
				alert.setHeaderText(
						"An Exception has occured :( This shouldn't happen! Please send this StackTrace to the dev so he can fix me!");
				StringWriter sW = new StringWriter();
				PrintWriter pW = new PrintWriter(sW);
				ex.printStackTrace(pW);
				pW.flush();
				alert.setContentText(sW.toString());
				pW.close();
				ex.printStackTrace();
				alert.showAndWait();

			}

		});

	}

}
