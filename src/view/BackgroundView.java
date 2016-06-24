/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 *
 * @author Lucas
 */
public class BackgroundView {
    
    private Image bg;
    
    private ViewsMediator viewsMediator;
    
    public BackgroundView (ViewsMediator viewsMediator){
        this.viewsMediator = viewsMediator;
    }
    
    public void loadResources (){
        ImageIcon icon = new ImageIcon("src/tiles/bg.png");
        bg = viewsMediator.makeColorTransparent(icon.getImage());
    }
    
    public void draw (Graphics g){
        
        Point position = new Point (0,0);
        
        try{
            g.drawImage(bg, position.x, position.y, viewsMediator);
        } catch (Exception e){
            System.out.println("Error printing cactus.");
        }
    }
    
}
