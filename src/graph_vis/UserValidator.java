package graph_vis;

import javax.swing.*;

public class UserValidator {

    //checking if checkbox is marked and if both aren't marked at once
    public static boolean isGraphTypeSelected(JCheckBox b1, JCheckBox b2){
        if( (b1.isSelected() || b2.isSelected()) && !(b1.isSelected() && b2.isSelected()) )
            return true;
        else
            return false;
    }

}
