/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems;

import java.awt.Point;
import logic.Cactus;
import view.ViewsMediator;

/**
 *
 * @author Caio Cesar
 */
public class CactusSystem {
    
    public enum CactusSpecies{
        BABY, ADULT
    }
    
    private ViewsMediator viewsMediator;
    
    public void initialize (ViewsMediator viewsMediator){
        this.viewsMediator = viewsMediator;
    }
    
    public void createCactus (Point position){
        Cactus cactus = new Cactus(position);
        cactus.initialize(viewsMediator);
    }
    
}
