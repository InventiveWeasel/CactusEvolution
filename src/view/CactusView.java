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
import logic.Cactus;

/**
 *
 * @author Caio Cesar
 */
public class CactusView {
    
    private Image cactus;
    
    private Cactus attachedCactus;
    private ViewsMediator viewsMediator;
    
    public CactusView (Cactus cactus, ViewsMediator viewsMediator){
        attachedCactus = cactus;
        this.viewsMediator = viewsMediator;
    }
    
    public void loadResources(){
        ImageIcon icon = new ImageIcon("src/tiles/error.png");
        cactus = icon.getImage();
    }
    
    public void draw (Graphics g){
        Point position = new Point (attachedCactus.getX(), attachedCactus.getY());
        Point center = new Point (cactus.getWidth(viewsMediator)/2, cactus.getHeight(viewsMediator)/2);
        
        g.drawImage(cactus, position.x-center.x, position.y-center.y, viewsMediator);
    }
    
}
