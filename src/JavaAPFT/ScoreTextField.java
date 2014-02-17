package JavaAPFT;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TextField;

/**
* This class ....
*
* @author  Patrick Michalina <patrickmichalina@mac.com>
* @version 0.7
* @since   0.1
*/
public class ScoreTextField extends TextField {
    private final IntegerProperty min = new SimpleIntegerProperty();
    private final IntegerProperty max = new SimpleIntegerProperty();
    private final SimpleBooleanProperty valid = new SimpleBooleanProperty();
    
    public ScoreTextField() {
        super();
        this.min.set(0);            // defaults
        this.max.set(0);            // defaults
        this.valid.set(true);
    }
    
    public ScoreTextField(int min, int max) {
        super();
        this.min.set(min);
        this.max.set(max);
    }
    
    public int getMax() {
        return max.get();
    }

    public void setMax(int value) {
        max.set(value);
    }

    public IntegerProperty maxProperty() {
        return max;
    }
    
    public int getMin() {
        return min.get();
    }

    public void setMin(int value) {
        min.set(value);
    }

    public IntegerProperty minProperty() {
        return min;
    }
    
  
    
    public BooleanProperty validProperty() {
        return valid;
    }
   
    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[0-9]*") && Integer.parseInt(text) >= this.getMin()
                                   && Integer.parseInt(text) <= this.getMax()) {
            super.replaceText(start, end, text);
        } else {
            super.replaceText(start - 1, end, text);
        }
       
       //verify();
    }

    @Override
    public void replaceSelection(String text) {
        if(text.matches("[0-9]*") && Integer.parseInt(text) >= this.getMin()
                                   && Integer.parseInt(text) <= this.getMax()) {
            super.replaceSelection(text);
    //        verify();
        }
    }

    public boolean isValid() {        
        if(!getText().isEmpty()) {
            if(getText().matches("[0-9]+")) {
                if (Integer.parseInt(getText()) >= min.get() && Integer.parseInt(getText()) <= max.get()) {
                    this.validProperty().setValue(true);
                    return true;
                } else if(getText().equals("00")) {
                    this.validProperty().setValue(false);
                    return false;
                }   
            }
        }
        return false;
    }
}