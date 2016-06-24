/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import logic.Cactus;
import sun.awt.image.ToolkitImage;
import systems.CactusSystem;

/**
 *
 * @author Caio Cesar
 */
public class CactusView {
    
    private Image cactus;
    private Image previousCactus;
    
    private Image box;
    private Image baby;
    private Image adult;
    private Image weasel;
    private Image mexican;
    private Image gabi;
    private Image jackman;
    private Image yano;
    private Image god;
    private Image egret;
    
    private BufferedImage bufexplosion;
    private Image explosion;
    
    private Cactus attachedCactus;
    private ViewsMediator viewsMediator;
    
    private long instant;
    
    private final int explosionWidth = 128;
    private final int explosionHeight = 128;
    
    public CactusView (Cactus cactus, ViewsMediator viewsMediator){
        attachedCactus = cactus;
        this.viewsMediator = viewsMediator;
        
        cactus = null;
        previousCactus = null;
    }
    
    public void loadResources() {
        ImageIcon icon = new ImageIcon("src/tiles/box.png");
        box = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("src/tiles/baby.png");
        baby = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("src/tiles/adult.png");
        adult = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("src/tiles/weasel.png");
        weasel = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("src/tiles/mexican.png");
        mexican = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("src/tiles/gabi.png");
        gabi = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("src/tiles/jackman.png");
        jackman = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("src/tiles/yano.png");
        yano = viewsMediator.makeColorTransparent(icon.getImage());

        icon = new ImageIcon("src/tiles/god.png");
        god = viewsMediator.makeColorTransparent(icon.getImage());
        
        icon = new ImageIcon("src/tiles/egret.png");
        egret = viewsMediator.makeColorTransparent(icon.getImage());
        
        
        try {
            bufexplosion = ImageIO.read(new File("src/tiles/explosion.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

       // icon = new ImageIcon("src/tiles/explosion.png");
        //explosion = icon.getImage();
        //explosion = viewsMediator.makeColorTransparent(icon.getImage());
        
        /*bufexplosion = new BufferedImage(5*explosionWidth, explosionHeight, BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bufexplosion.createGraphics();
        bGr.drawImage(explosion, null, null);
        bGr.dispose();*/
        
       /* File outputfile = new File("explosion.png");
        ImageIO.write(explosion, "png", outputfile); 
        
        bufexplosion = ((ToolkitImage) explosion).getBufferedImage();
        if (bufexplosion == null) System.out.println("befexplosion is null");*/
        
    }
    
    public void draw (Graphics g){
        
        if (attachedCactus.getState() == CactusSystem.CactusState.BOX)
            cactus = box;
        else if (attachedCactus.getSpecie() == CactusSystem.CactusSpecies.BABY)
            cactus = baby;
        else if (attachedCactus.getSpecie() == CactusSystem.CactusSpecies.ADULT)
            cactus = adult;
        else if (attachedCactus.getSpecie() == CactusSystem.CactusSpecies.WEASEL)
            cactus = weasel;
        else if (attachedCactus.getSpecie() == CactusSystem.CactusSpecies.MEXICAN)
            cactus = mexican;
        else if (attachedCactus.getSpecie() == CactusSystem.CactusSpecies.GABI)
            cactus = gabi;
        else if (attachedCactus.getSpecie() == CactusSystem.CactusSpecies.JACKMAN)
            cactus = jackman;
        else if (attachedCactus.getSpecie() == CactusSystem.CactusSpecies.YANO)
            cactus = yano;
        else if (attachedCactus.getSpecie() == CactusSystem.CactusSpecies.GOD)
            cactus = god;
        else if (attachedCactus.getSpecie() == CactusSystem.CactusSpecies.EGRET)
            cactus = egret;
        
        Point position = new Point (attachedCactus.getX(), attachedCactus.getY());
        Point center = new Point (cactus.getWidth(viewsMediator)-46, cactus.getHeight(viewsMediator)-46);
        
        try{
            g.drawImage(cactus, position.x-center.x, position.y-center.y, viewsMediator);
        } catch (Exception e){
            System.out.println("Error printing cactus.");
        }
        
        if (previousCactus != cactus){
            instant = System.currentTimeMillis();
        }
        
        long dt = System.currentTimeMillis() - instant;
        if (dt < 500){
            dt /= 50;
            center.x = explosionWidth/2;
            center.y = explosionHeight/2;
            g.drawImage(bufexplosion.getSubimage(Math.toIntExact(dt)*explosionWidth, 0, explosionWidth, explosionHeight), position.x-center.x, position.y-center.y, viewsMediator);
        }
        
        previousCactus = cactus;
    }
    
}
