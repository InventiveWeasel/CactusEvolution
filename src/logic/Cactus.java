/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.awt.Point;
import systems.CactusSystem;
import view.ViewsMediator;
import java.util.Random;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Caio Cesar
 */
public class Cactus implements Runnable {
    
    private Point position, nextPosition;
    private CactusSystem.CactusSpecies specie;
    private CactusSystem.CactusState state;
    private Random gen = new Random();
    private double teta;
    
    //private CactusSystem cactusSystem;
    
    public Cactus (Point position, CactusSystem.CactusSpecies specie){
        this.position = position;
        this.specie = specie;
        state = CactusSystem.CactusState.FREE;
    }
    
    public void initialize(ViewsMediator viewsMediator){
        viewsMediator.attachNewCactusViewToCactus(this);
    }
    
    public int getX(){
        return (int)position.getX();
    }
    
    public int getY(){
        return (int)position.getY();
    }
    
    public void setPosition (Point position){
        this.position = position;
    }
    
    public void setState(CactusSystem.CactusState state){
        this.state = state;
    }
    
    public void translate (int x, int y){
        position.x += x;
        position.y += y;
    }
    
    public void run(){
        while (true){
            teta = (gen.nextInt(360))*Math.PI/180;
            nextPosition = new Point(getX()+(int)(25*cos(teta)),getY()+(int)(25*sin(teta)));
            if (nextPosition.x > 24 && nextPosition.y > 24 && nextPosition.x < 941 && nextPosition.y < 587)
                setPosition(nextPosition);
            try{
                Thread.sleep(1000);
            }   catch (InterruptedException ex) {
                Logger.getLogger(Cactus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
