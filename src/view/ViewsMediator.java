/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import systems.GameSystem;

/**
 *
 * @author Caio Cesar
 */
public class ViewsMediator extends JPanel{
    
    public final int SCREEN_WIDTH = 965;
    public final int SCREEN_HEIGHT = 611;
    
    private GameSystem gameSystem;
    
    public void initialize (GameSystem gameSystem){
        
        this.gameSystem = gameSystem;
        
        //Mudar para um background maroto.
        setBackground(Color.BLACK);
        setFocusable(true);
        
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    }
    
}
