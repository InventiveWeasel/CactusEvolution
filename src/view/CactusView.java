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
import systems.CactusSystem;

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
        if (attachedCactus.getState() == CactusSystem.CactusState.BOX)
            icon = new ImageIcon("src/tiles/box.png");
        else if (attachedCactus.getSpecie() == CactusSystem.CactusSpecies.BABY)
            icon = new ImageIcon("src/tiles/baby.png");
        else if (attachedCactus.getSpecie() == CactusSystem.CactusSpecies.ADULT)
            icon = new ImageIcon("src/tiles/adult.png");
        else if (attachedCactus.getSpecie() == CactusSystem.CactusSpecies.WEASEL)
            icon = new ImageIcon("src/tiles/weasel.png");
        else if (attachedCactus.getSpecie() == CactusSystem.CactusSpecies.MEXICAN)
            icon = new ImageIcon("src/tiles/mexican.png");
        else if (attachedCactus.getSpecie() == CactusSystem.CactusSpecies.GABI)
            icon = new ImageIcon("src/tiles/gabi.png");
        else if (attachedCactus.getSpecie() == CactusSystem.CactusSpecies.JACKMAN)
            icon = new ImageIcon("src/tiles/jackman.png");
        else if (attachedCactus.getSpecie() == CactusSystem.CactusSpecies.YANO)
            icon = new ImageIcon("src/tiles/yano.png");
        else if (attachedCactus.getSpecie() == CactusSystem.CactusSpecies.GOD)
            icon = new ImageIcon("src/tiles/god.png");
        else if (attachedCactus.getSpecie() == CactusSystem.CactusSpecies.EGRET)
            icon = new ImageIcon("src/tiles/egret.png");
        
        cactus = icon.getImage();
    }
    
    public void draw (Graphics g){
        Point position = new Point (attachedCactus.getX(), attachedCactus.getY());
        Point center = new Point (cactus.getWidth(viewsMediator)/2, cactus.getHeight(viewsMediator)/2);
        
        g.drawImage(cactus, position.x-center.x, position.y-center.y, viewsMediator);
    }
    
}
