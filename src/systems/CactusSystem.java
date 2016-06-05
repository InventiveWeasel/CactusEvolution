/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems;

import java.awt.Point;
import logic.Cactus;
import view.ViewsMediator;
import java.util.Random;
import java.util.ArrayList;

/**
 *
 * @author Caio Cesar
 */
public class CactusSystem {
    
    public enum CactusSpecies{
        BABY, ADULT, JACKMAN, YANO, GABI, EGRET, MEXICAN, WEASEL, GOD
    }
    
    private ArrayList<Cactus> cactusList;
    
    private ViewsMediator viewsMediator;
    private Random gen = new Random();
    private long instant;
    
    public CactusSystem(){
        this.instant = System.currentTimeMillis();
        cactusList =  new ArrayList<Cactus>();
    }
    
    public void initialize (ViewsMediator viewsMediator){
        this.viewsMediator = viewsMediator;
    }
    
    public void createCactus (Point position, CactusSystem.CactusSpecies specie){
        Cactus cactus = new Cactus(position, specie);
        cactusList.add(cactus);
        cactus.initialize(viewsMediator);
    }
    
    public void createBox (){
        if (cactusList.size() < 16 && System.currentTimeMillis()-instant > 1000){
            Cactus box = new Cactus(new Point(gen.nextInt(916)+25, gen.nextInt(562)+25), CactusSystem.CactusSpecies.BABY);
            instant = System.currentTimeMillis();
            cactusList.add(box);
            box.initialize(viewsMediator);
        }
        
        
    }
    
}
