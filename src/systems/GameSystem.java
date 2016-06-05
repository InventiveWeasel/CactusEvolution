/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems;

import java.awt.Point;
import view.ViewsMediator;
import java.util.Random;

/**
 *
 * @author Caio Cesar
 */
public class GameSystem {
    
    private CactusSystem cactusSystem;
    private ViewsMediator viewsMediator;
    private Random gen = new Random();
    
    public GameSystem(){
        cactusSystem = new CactusSystem();
    }
    
    public void Box(){
        cactusSystem.createBox();
    }
    
    public void initialize (ViewsMediator viewsMediator){
        this.viewsMediator = viewsMediator;
        
        this.cactusSystem.initialize(viewsMediator);
        //Teste
        //cactusSystem.createCactus(new Point(gen.nextInt(916)+25, gen.nextInt(562)+25), CactusSystem.CactusSpecies.BABY);
    }
    
}
