/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.awt.Point;
import systems.CactusSystem;

/**
 *
 * @author Caio Cesar
 */
public class Cactus {
    
    private Point position;
    private CactusSystem.CactusSpecies specie;
    
    //private CactusSystem cactusSystem;
    
    public Cactus (Point position){
        this.position = position;
    }
    
}
