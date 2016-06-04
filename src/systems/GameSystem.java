/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems;

import java.awt.Point;
import view.ViewsMediator;

/**
 *
 * @author Caio Cesar
 */
public class GameSystem {
    
    private CactusSystem cactusSystem;
    private ViewsMediator viewsMediator;
    
    public GameSystem(){
        cactusSystem = new CactusSystem();
    }
    
    public void initialize (ViewsMediator viewsMediator){
        this.viewsMediator = viewsMediator;
        
        this.cactusSystem.initialize(viewsMediator);
        //Teste
        cactusSystem.createCactus(new Point(450, 300));
    }
    
}
