/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cactusevolution;

import javax.swing.JFrame;
import systems.GameSystem;
import view.ViewsMediator;

/**
 *
 * @author Caio Cesar
 */
public class GameApp extends JFrame{
    
    private GameSystem gameSystem;
    private ViewsMediator viewsMediator;
    
    public void run(){
        gameSystem = new GameSystem();
        viewsMediator = new ViewsMediator();
        
        gameSystem.initialize(viewsMediator);
        viewsMediator.initialize(gameSystem);
        
        this.add(viewsMediator);
        
        setResizable(false);
        pack();
        setTitle("Cactus Evolution");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        while(true){
            viewsMediator.repaint();
            try{
                Thread.sleep(100);
            } catch (Exception e){}
        }
    }
    
}
