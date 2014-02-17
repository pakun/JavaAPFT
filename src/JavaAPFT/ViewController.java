package JavaAPFT;

import XControls.ScoreTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 *
 * @author Patrick Michalina
 */
public class ViewController implements Initializable {
    @FXML private RadioButton    rbMale;
    @FXML private RadioButton    rbFemale;
    @FXML private Group          grpGender;
    @FXML private Group          grpPushUps;
    @FXML private Group          grpSitUps;
    @FXML private Group          grpRun;
    @FXML private Slider         sliderAge;
    @FXML private Slider         sliderPushUps;
    @FXML private Slider         sliderSitUps;
    @FXML private Slider         sliderRun;
    @FXML private ScoreTextField txtAge;        
    @FXML private ScoreTextField txtPushUps;    
    @FXML private ScoreTextField txtSitUps;     
    @FXML private TextField      txtRun;          
    @FXML private TextField      txtScorePushUps;
    @FXML private TextField      txtScoreSitUps;
    @FXML private TextField      txtScoreRun;
    @FXML private Label          lblScoreTotal;
        
    private final APFT apft = new APFT();       // apft data model
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        
        // attempt to setup FXML GUI
        try {
            
            lockScoreInputFields(false);
            
            // input field constraints based on APFT model
            txtAge.minProperty().setValue(APFT.MIN_AGE);
            txtAge.maxProperty().setValue(APFT.MAX_AGE);
            sliderAge.minProperty().setValue(APFT.MIN_AGE);
            sliderAge.maxProperty().setValue(APFT.MAX_AGE);
            txtPushUps.minProperty().setValue(APFT.MIN_PU);
            txtPushUps.maxProperty().setValue(APFT.MAX_PU);
            sliderPushUps.minProperty().setValue(APFT.MIN_PU);
            sliderPushUps.maxProperty().setValue(APFT.MAX_PU);
            
            
            // bind GUI components to model properties
            rbMale.setSelected(true);
            updateGender();

            // bind sliders to data model
            sliderPushUps.valueProperty().bindBidirectional(apft.rawPUProperty());
            sliderSitUps.valueProperty().bindBidirectional(apft.rawSUProperty());
            sliderAge.valueProperty().bindBidirectional(apft.ageProperty());
            sliderRun.valueProperty().bindBidirectional(apft.rawRunProperty());
            
            // bind text fields to sliders
            txtAge.textProperty().bindBidirectional(sliderAge.valueProperty(), new IntStringConverter());
            txtPushUps.textProperty().bindBidirectional(sliderPushUps.valueProperty(), new IntStringConverter());
            txtSitUps.textProperty().bindBidirectional(sliderSitUps.valueProperty(), new IntStringConverter());
            
            // bind score fields to data model
            txtScorePushUps.textProperty().bind(apft.scorePU().asString());
            txtScoreSitUps.textProperty().bind(apft.scoreSU().asString());
            txtScoreRun.textProperty().bind(apft.scoreRun().asString());
            lblScoreTotal.textProperty().bind(apft.score().asString());
    
        } catch(NullPointerException ex) {
            //TODO error logging
            System.out.println(ex.toString());
        }    
    }    
    
        // handles slider Number conversion to a simple integer
        private class IntStringConverter extends StringConverter<Number> {

        @Override
        public String toString(Number t) {
            return t.intValue() + "";
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Number fromString(String string) {
            return Double.parseDouble(string);
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        }
        
        private void lockScoreInputFields(boolean bool) {
            grpGender.disableProperty().setValue(bool);
            grpPushUps.disableProperty().setValue(bool);
            grpSitUps.disableProperty().setValue(bool);
            grpRun.disableProperty().setValue(bool);
        }
        
//        private void resetApft() {
//            apft.rawPUProperty().setValue(APFT.MIN_PU);
//        }
        
        @FXML private void updateGender() {
            if (rbFemale.isSelected())
                apft.setGender("F"); 
            else 
                apft.setGender("M");
        }
 }