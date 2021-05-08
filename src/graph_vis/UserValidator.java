/**
 * graph visualisation tool
 * @author lenamiz
 * github.com/lenamiz/
 */
package graph_vis;

import javax.swing.*;

/**
 * Class for validating user's graph choice
 */
public class UserValidator {

    /**
     * checks if checkbox is marked and if both aren't marked at once
     * @param b1 checkbox 1
     * @param b2 checkbox 2
     * @return true if only one checkbox selected
     */
    public static boolean isGraphTypeSelected(JCheckBox b1, JCheckBox b2){
        if( (b1.isSelected() || b2.isSelected()) && !(b1.isSelected() && b2.isSelected()) )
            return true;
        else
            return false;
    }

}
