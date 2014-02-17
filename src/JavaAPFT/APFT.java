package JavaAPFT;

import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
* This class ....
*
* @author  Patrick Michalina <patrickmichalina@mac.com>
* @version 0.7
* @since   0.1
*/
public final class APFT {
    private final StringProperty  gender   = new SimpleStringProperty();
    private final IntegerProperty rawPU    = new SimpleIntegerProperty();
    private final IntegerProperty rawSU    = new SimpleIntegerProperty();
    private final IntegerProperty rawRun   = new SimpleIntegerProperty();
    private final IntegerProperty age      = new SimpleIntegerProperty();
    private final IntegerBinding  scorePU;
    private final IntegerBinding  scoreSU;
    private final IntegerBinding  scoreRun;
    private final IntegerBinding  score;
    
    // static variables defning army physical fitness test constraints
    public static final int MIN_PU  = 0;
    public static final int MAX_PU  = 100;
    public static final int MIN_SU  = 0;
    public static final int MAX_SU  = 100;
    public static final int MIN_RUN = 0;
    public static final int MAX_RUN = 2000;
    public static final int MIN_AGE = 17;
    public static final int MAX_AGE = 56;
    
    // becuase APFT scores are based on raw inputs it is sensible to use
    // bindings so scores are always up-to-date regardless of what a view 
    // controller might be doing.
    public APFT() {
        
        scorePU = new IntegerBinding() {
            {
                 // changes in any of these values will update push-up the score
                super.bind(age, gender, rawPU);
            }
            
            @Override
            protected int computeValue() {
                if(gender.isNotNull().getValue()) {
                    switch(gender.get()) {
                        case "M":
                            int[][] scores = { // create male score lookup table
                            // index 0 is age 17-21
                            {  0,  0,  0,  0,  0,  9, 10, 12, 13, 14, 16, 17, 
                              19, 20, 21, 23, 24, 26, 27, 28, 30, 31, 32, 34,
                              35, 37, 38, 39, 41, 42, 43, 45, 46, 48, 49, 50, 
                              52, 53, 54, 56, 57, 59, 60, 61, 63, 64, 66, 67, 
                              68, 70, 71, 72, 74, 75, 77, 78, 79, 81, 82, 83,
                              85, 86, 88, 89, 90, 92, 93, 94, 96, 97, 99,100},
                            // index 1 is age 22-26
                            {  0,  0,  0,  0,  0, 20, 21, 22, 23, 25, 26, 27,
                              28, 29, 30, 31, 33, 34, 35, 36, 37, 38, 39, 41,
                              42, 43, 44, 45, 46, 47, 49, 50, 51, 52, 53, 54, 
                              55, 57, 58, 59, 60, 61, 62, 63, 65, 66, 67, 68, 
                              69, 70, 71, 73, 74, 75, 76, 77, 78, 79, 81, 82,
                              83, 84, 85, 86, 87, 89, 90, 91, 92, 93, 94, 95, 
                              97, 98, 99,100},
                            // index 2 is age 27-31
                            {  0,  0,  0,  0,  0, 24, 25, 26, 27, 28, 29, 31,
                              32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 
                              44, 45, 46, 47, 48, 49, 50, 52, 53, 54, 55, 56,
                              57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68,
                              69, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81,
                              82, 83, 84, 85, 86, 87, 88, 89, 91, 92, 93, 94,
                              95, 96, 97, 98, 99,100},
                            // index 3 is age 32-36
                            {  0,  0,  0,  0, 28, 29, 30, 31, 32, 33, 34, 35, 
                              36, 37, 38, 39, 41, 42, 43, 44, 45, 46, 47, 48, 
                              49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
                              61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72,
                              73, 74, 75, 76, 77, 78, 79, 81, 82, 83, 84, 85,
                              86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97,
                              98, 99,100},
                            // index 4 is age 37-41
                            {  0,  0,  0,  0,  0, 30, 31, 32, 33, 34, 35, 36, 
                              37, 38, 39, 41, 42, 43, 44, 45, 46, 47, 48, 49, 
                              50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 
                              62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73,
                              74, 75, 76, 77, 78, 79, 81, 82, 83, 84, 85, 86,
                              87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 
                              99,100},
                            // index 5 is age 42-46
                            {  0,  0,  0,  0, 32, 33, 34, 36, 37, 38, 39, 40, 
                              41, 42, 43, 44, 46, 47, 48, 49, 50, 51, 52, 53, 
                              54, 56, 57, 58, 59, 60, 61, 62, 63, 64, 66, 67, 
                              68, 69, 70, 71, 72, 73, 74, 76, 77, 78, 79, 80,
                              81, 82, 83, 84, 86, 87, 88, 89, 90, 91, 92, 93, 
                              94, 96, 97, 98, 99,100},
                            // index 6 is age 47-51
                            {  0,  0,  0,  0,  0, 36, 38, 39, 40, 41, 42, 45,
                              46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 58, 
                              59, 60, 61, 62, 64, 65, 66, 67, 68, 69, 71, 72, 
                              73, 74, 75, 76, 78, 79, 80, 81, 82, 85, 86, 87, 
                              88, 89, 90, 91, 92, 93, 94, 95, 96, 98, 99,100},
                            // index 7 is age 52-56
                            {  0,  0,  0,  0,  0, 43, 44, 46, 47, 48, 49, 50,
                              51, 52, 53, 54, 56, 57, 58, 59, 60, 61, 62, 63,
                              64, 66, 67, 68, 69, 70, 71, 72, 73, 74, 76, 77, 
                              78, 79, 80, 81, 82, 83, 84, 86, 87, 88, 89, 90, 
                              91, 92, 94, 95, 96, 97, 98, 99, 100}};

                            switch(age.get()) {
                                case 17: case 18: case 19:
                                case 20: case 21:
                                    if(rawPU.get() < scores[0].length)
                                        return scores[0][rawPU.get()];
                                    else
                                        return 100;
                                case 22: case 23: case 24:
                                case 25: case 26:
                                    if(rawPU.get() < scores[1].length)
                                        return scores[1][rawPU.get()];
                                     else
                                        return 100;
                                case 27: case 28: case 29:
                                case 30: case 31:
                                    if(rawPU.get() < scores[2].length)
                                        return scores[2][rawPU.get()];
                                    else
                                        return 100;
                                case 32: case 33: case 34:
                                case 35: case 36:
                                    if(rawPU.get() < scores[3].length)
                                        return scores[3][rawPU.get()];
                                    else
                                        return 100;
                                case 37: case 38: case 39:
                                case 40: case 41:
                                    if(rawPU.get() < scores[4].length)
                                        return scores[4][rawPU.get()];
                                    else
                                        return 100;
                                case 42: case 43: case 44:
                                case 45: case 46:
                                    if(rawPU.get() < scores[5].length)
                                        return scores[5][rawPU.get()];
                                    else
                                        return 100;
                                case 47: case 48: case 49:
                                case 50: case 51:
                                    if(rawPU.get() < scores[6].length)
                                        return scores[6][rawPU.get()];
                                    else
                                        return 100;
                                case 52: case 53: case 54:
                                case 55: case 56:
                                    if(rawPU.get() < scores[7].length)
                                        return scores[7][rawPU.get()];
                                    else
                                        return 100;
                                default: // outside age bounds - set score to 0
                                    return 0;
                            }
 
                        case "F":
                            int[][] femaleScores = { // score lookup table
                            // index 0 is ages 17-21
                            {  0,  0,  0,  0,  0, 36, 37, 39, 41, 43, 44, 46,
                              50, 51, 53, 55, 57, 58, 58, 60, 62, 63, 65, 67,
                              69, 70, 72, 74, 76, 77, 79, 81, 83, 84, 86, 88,
                              90, 91, 93, 95, 97, 98,100},
                            // index 1 is ages 22-26
                            {  0,  0,  0,  0,  0, 43, 45, 45, 48, 49, 49, 50,
                              52, 54, 56, 57, 59, 60, 61, 63, 64, 66, 67, 68,
                              70, 71, 72, 74, 75, 77, 78, 79, 81, 82, 83, 85,
                              86, 88, 89, 90, 92, 93, 94, 96, 97, 99,100},
                            // index 2 is ages 27-31
                            {  0,  0,  0,  0,  0, 45, 47, 48, 49, 49, 50, 52,
                              54, 55, 56, 58, 59, 60, 61, 62, 64, 65, 66, 67,
                              68, 70, 71, 72, 73, 75, 76, 77, 78, 79, 81, 82,
                              83, 84, 85, 87, 88, 89, 90, 92, 93, 94, 95, 96,
                              98, 99,100},
                            {  0,  0,  0,  0,  0, 47, 48, 49, 49, 50, 52, 54,
                              58, 58, 59, 60, 61, 63, 64, 65, 67, 68, 69, 71,
                              72, 73, 75, 76, 77, 79, 80, 81, 83, 84, 85, 87,
                              88, 89, 91, 92, 93, 95, 96, 97, 99,100},
                            {  0,  0,  0,  0,  0, 48, 50, 51, 53, 54, 56, 57,
                              59, 60, 61, 63, 64, 66, 67, 69, 70, 72, 73, 75,
                              76, 78, 79, 81, 82, 84, 85, 87, 88, 90, 91, 93,
                              94, 96, 97, 99,100},
                            {  0,  0,  0,  0,  0, 49, 50, 52, 54, 55, 57, 58,
                              50, 62, 63, 65, 66, 68, 70, 71, 73, 64, 76, 78,
                              79, 81, 82, 84, 86, 87, 89, 90, 92, 94, 95, 97,
                              98,100},
                            {  0,  0,  0,  0,  0, 52, 53, 55, 57, 58, 60, 62,
                              63, 65, 67, 68, 70, 72, 73, 75, 77, 78, 80, 82,
                              83, 85, 87, 88, 90, 92, 93, 95, 97, 98,100},
                            {  0,  0,  0,  0,  0, 53, 55, 56, 58, 60, 62, 64,
                              65, 67, 69, 71, 73, 75, 76, 78, 80, 82, 84, 85,
                              87, 89, 91, 93, 95, 96, 89,100}
                            };
                        
                            switch(age.get()) {
                                case 17: case 18: case 19:
                                case 20: case 21:
                                    if(rawPU.get() < femaleScores[0].length)
                                        return femaleScores[0][rawPU.get()];
                                    else
                                        return 100;
                                case 22: case 23: case 24:
                                case 25: case 26:
                                    if(rawPU.get() < femaleScores[1].length)
                                        return femaleScores[1][rawPU.get()];
                                    else
                                        return 100;
                                case 27: case 28: case 29:
                                case 30: case 31:
                                    if(rawPU.get() < femaleScores[2].length)
                                        return femaleScores[2][rawPU.get()];
                                    else
                                        return 100;
                                case 32: case 33: case 34:
                                case 35: case 36:
                                    if(rawPU.get() < femaleScores[3].length)
                                        return femaleScores[3][rawPU.get()];
                                    else 
                                        return 100;
                                case 37: case 38: case 39:
                                case 40: case 41:
                                    if(rawPU.get() < femaleScores[4].length)
                                        return femaleScores[4][rawPU.get()];
                                    else 
                                        return 100;
                                case 42: case 43: case 44:
                                case 45: case 46:
                                    if(rawPU.get() < femaleScores[5].length)
                                        return femaleScores[5][rawPU.get()];
                                    else 
                                        return 100;
                                case 47: case 48: case 49:
                                case 50: case 51:
                                    if(rawPU.get() < femaleScores[6].length)
                                        return femaleScores[6][rawPU.get()];
                                    else 
                                        return 100;
                                case 52: case 53: case 54:
                                case 55: case 56:
                                    if(rawPU.get() < femaleScores[6].length)
                                        return femaleScores[6][rawPU.get()];
                                    else 
                                        return 100;
                            };
                    
                        default: // gender error set score to 0
                            return 0;
                    }
                } else {
                    return 0;
                }
            }
        };
        
        scoreSU = new IntegerBinding() {
            {
                super.bind(age, gender, rawSU);
            }

            @Override
            protected int computeValue() {
                int[][] scores = { // situp score lookup table
                    // index 0 is ages 17-21
                    {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
                       0,  0,  0,  0,  0,  0,  0,  9, 10, 12, 14, 15, 17, 18,
                      20, 22, 23, 25, 26, 28, 30, 31, 33, 34, 36, 38, 39, 41,
                      42, 44, 45, 47, 49, 50, 52, 54, 55, 57, 58, 60, 62, 63,
                      65, 66, 68, 70, 71, 73, 74, 76, 78, 79, 81, 82, 84, 87,
                      88, 89, 90, 92, 94, 95, 97, 98,100},
                    // index 1 is ages 22-26
                    {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
                       0,  0,  0,  0,  0,  0,  0, 21, 23, 24, 25, 27, 28, 29,
                      31, 32, 33, 35, 36, 37, 39, 40, 41, 43, 44, 45, 47, 48,
                      49, 50, 52, 53, 55, 56, 57, 59, 60, 61, 63, 64, 65, 67,
                      68, 69, 71, 72, 73, 75, 76, 77, 79, 80, 81, 83, 84, 85,
                      87, 88, 89, 91, 92, 93, 95, 96, 97, 99,100},
                    // index 2 is ages 27-31
                    {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
                       0,  0,  0,  0,  0,  0,  0, 34, 35, 36, 37, 38, 39, 41,
                      42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 54, 55, 56,
                      57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 68, 69, 70, 71,
                      72, 73, 74, 75, 76, 77, 78, 79, 81, 82, 83, 84, 85, 86,
                      87, 88, 89, 90, 91, 92, 94, 95, 96, 97, 98, 99,100},
                    // index 3 is ages 
                    {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
                       0,  0,  0,  0,  0,  0,  0, 35, 36, 38, 39, 40, 41, 42,
                      44, 45, 46, 47, 48, 49, 50, 52, 53, 54, 55, 56, 58, 59,
                      60, 61, 62, 64, 65, 66, 67, 68, 69, 71, 72, 73, 74, 75,
                      76, 78, 79, 80, 81, 82, 84, 85, 86, 87, 88, 89, 91, 92,
                      93, 94, 95, 96, 98, 99,100},
                    // index 4 is ages 
                    {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
                       0,  0,  0,  0,  0,  0,  0, 42, 43, 44, 45, 46, 47, 48,
                      49, 50, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63,
                      64, 65, 66, 67, 68, 69, 71, 72, 73, 74, 75, 76, 77, 78,
                      79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 91, 92, 93,
                      94, 95, 96, 97, 98, 99,100},
                    // index 5 is ages 
                    {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
                       0,  0,  0,  0,  0,  0,  0, 49, 50, 51, 52, 53, 54, 55,
                      56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69,
                      70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83,
                      84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97,
                      98, 99,100},
                    // index 6 is ages 
                    {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
                       0,  0,  0,  0,  0,  0,  0, 50, 51, 52, 53, 54, 56, 57,
                      58, 59, 60, 61, 62, 63, 64, 66, 67, 68, 69, 70, 71, 72,
                      73, 74, 76, 77, 78, 79, 80, 81, 82, 83, 84, 86, 87, 88,
                      89, 90, 91, 92, 93, 94, 96, 97, 98, 99,100},
                    // index 7 is ages 52-56
                    {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
                       0,  0,  0,  0,  0,  0,  0, 53, 54, 55, 56, 57, 58, 59,
                      60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 71, 72, 73, 74,
                      75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88,
                      89, 91, 92, 93, 94, 95, 96, 97, 98, 99,100}
                };
            
                switch(age.get()) {
                    case 17: case 18: case 19:
                    case 20: case 21:
                        if(rawSU.get() < scores[0].length)
                            return scores[0][rawSU.get()];
                        else
                            return 100;
                    case 22: case 23: case 24:
                    case 25: case 26:
                        if(rawSU.get() < scores[1].length)
                            return scores[1][rawSU.get()];
                        else
                            return 100;
                    case 27: case 28: case 29:
                    case 30: case 31:
                        if(rawSU.get() < scores[2].length)
                            return scores[2][rawSU.get()];
                        else
                            return 100;
                    case 32: case 33: case 34:
                    case 35: case 36:
                        if(rawSU.get() < scores[3].length)
                            return scores[3][rawSU.get()];
                        else
                            return 100;
                    case 37: case 38: case 39:
                    case 40: case 41:
                        if(rawSU.get() < scores[4].length)
                            return scores[4][rawSU.get()];
                        else
                            return 100;
                    case 42: case 43: case 44:
                    case 45: case 46:
                        if(rawSU.get() < scores[5].length)
                            return scores[5][rawSU.get()];
                        else
                            return 100;
                    case 47: case 48: case 49:
                    case 50: case 51:
                        if(rawSU.get() < scores[6].length)
                            return scores[6][rawSU.get()];
                        else
                            return 100;
                    case 52: case 53: case 54:
                    case 55: case 56:
                        if(rawSU.get() < scores[7].length)
                            return scores[7][rawSU.get()];
                        else
                            return 100;
                    default:
                        return 0;
                }
            }
        };
        
        scoreRun = new IntegerBinding() {
            {
                super.bind(age, gender, rawRun);
            }
            @Override
            protected int computeValue() {
                return 0;
            }
        };
        
        score = new IntegerBinding() {
            {
                super.bind(age, gender, scorePU, scoreSU, scoreRun);
            }

            @Override
            protected int computeValue() {
                return scorePU.get() + scoreSU.get() + scoreRun.get();
            }
            
        };      
    }
    
    
   
    public int getAge() {
        return age.get();
    }

    public void setAge(int value) {
        age.set(value);
    }

    public final IntegerProperty ageProperty() {
        return age;
    }    

    public int getRawRun() {
        return rawRun.get();
    }

    public final void setRawRun(int value) {
        rawRun.set(value);
    }

    public IntegerProperty rawRunProperty() {
        return rawRun;
    }

    public int getRawSU() {
        return rawSU.get();
    }

    public final void setRawSU(int value) {
        rawSU.set(value);
    }

    public IntegerProperty rawSUProperty() {
        return rawSU;
    }

    public int getRawPU() {
        return rawPU.get();
    }

    public final void setRawPU(int value) {
        if(value >= MIN_PU && value <= MAX_PU) {
            rawPU.set(value);
        } else {
            rawPU.set(0);
            System.err.println("Error: setRawPU() - parameter must be an "
                             + "integer between " + MIN_PU + " and " + MAX_PU);
        }
    }
    
    public IntegerProperty rawPUProperty() {
        return rawPU;
    }
    
    public String getGender() {
        return gender.get();
    }

    public final void setGender(String value) {
        if(value.compareTo("M") == 0 || value.compareTo("F") == 0) {
            gender.set(value);
        } else {
            System.out.println("Error: setGender() - parameter must be M or F");
        }
    }

    public StringProperty genderProperty() {
        return gender;
    }
   
    public IntegerBinding scorePU() {
        return scorePU;
    }
 
    public IntegerBinding scoreSU() {
        return scoreSU;
    }
    
    public IntegerBinding scoreRun() {
        return scoreRun;
    }
    
    public IntegerBinding score() {
        return score;
    }
}