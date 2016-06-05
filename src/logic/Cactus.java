/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.awt.Point;
import systems.CactusSystem;
import view.ViewsMediator;

/**
 *
 * @author Caio Cesar
 */
public class Cactus implements Runnable {
    
    private Point position;
    private CactusSystem.CactusSpecies specie;
    private CactusSystem.CactusState state;
    
    //private CactusSystem cactusSystem;
    
    public Cactus (Point position, CactusSystem.CactusSpecies specie, CactusSystem.CactusState state){
        this.position = position;
        this.specie = specie;
        this.state = state;
    }
    
    public void initialize(ViewsMediator viewsMediator){
        viewsMediator.attachNewCactusViewToCactus(this);
    }
    
    public void update(ViewsMediator viewsMediator){
        viewsMediator.updateCactusView(this);
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
            
        }
    }
    
}
