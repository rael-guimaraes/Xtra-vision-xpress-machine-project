/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilityClasses;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author raelg
 */
public class DialogMessage {
    
        public String getTitle() {

        String title = "Xtra-vision xpress";
        return title;
    }
        
        public int getSuccessMessage(String message, String buttonLabel) {
        int click = JOptionPane.showOptionDialog(new JFrame(), message,
                getTitle(), JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE,
                null,new Object[]{buttonLabel}, JOptionPane.YES_OPTION);
        return click;
    }
    public int getSuccessMessage(String message){
       return getSuccessMessage(message,"OK");
    }
}
