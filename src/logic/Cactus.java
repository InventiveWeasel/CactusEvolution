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
    
    private CactusSystem cactusSystem;
    
    //private CactusSystem cactusSystem;
    
    public Cactus (Point position, CactusSystem.CactusSpecies specie, CactusSystem.CactusState state){
        this.position = position;
        this.specie = specie;
        this.state = state;
    }
    
    public void initialize(ViewsMediator viewsMediator, CactusSystem cactusSystem){
        viewsMediator.attachNewCactusViewToCactus(this);
        this.cactusSystem = cactusSystem;
    }
    
    public int getX(){
        return (int)position.getX();
    }
    
    public int getY(){
        return (int)position.getY();
    }
    
    public CactusSystem.CactusSpecies getSpecie(){
        return specie;
    }
    
    public CactusSystem.CactusState getState(){
        return state;
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
            //Sem explicacao nenhuma, sem esse print os cactus nao comecam a se mexer
            //apos a abertura da caixa.
            System.out.print ("");
            if (state == CactusSystem.CactusState.FREE){
                teta = (gen.nextInt(360))*Math.PI/180;
                nextPosition = new Point(getX()+(int)(25*cos(teta)),getY()+(int)(25*sin(teta)));
                if (nextPosition.x > 46 && nextPosition.y > 46
                        && nextPosition.x < cactusSystem.getWidth() - 46 && nextPosition.y < cactusSystem.getHeight()- 46)
                    setPosition(nextPosition);
                try{
                    Thread.sleep(1000);
                }   catch (InterruptedException ex) {
                    Logger.getLogger(Cactus.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
