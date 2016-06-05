/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import logic.Cactus;
import view.ViewsMediator;

/**
 *
 * @author Caio Cesar
 */
public class CactusSystem implements MouseListener, MouseMotionListener{
    
    public enum CactusState{
        FREE, HOLD
    }
    
    public enum CactusSpecies{
        BABY, ADULT
    }
    
    private int mouse_x = 0;
    private int mouse_y = 0;
    
    private ArrayList<Cactus> cactusList;
    
    private Cactus holdCactus;
    private ViewsMediator viewsMediator;
    
    public CactusSystem(){
        cactusList = new ArrayList<Cactus>();
        holdCactus = null;
    }
    
    public void initialize (ViewsMediator viewsMediator){
        holdCactus = null;
        this.viewsMediator = viewsMediator;
        
        viewsMediator.addMouseListener(this);
        viewsMediator.addMouseMotionListener(this);
    }
    
    public void createCactus (Point position){
        Cactus cactus = new Cactus(position);
        cactusList.add(cactus);
        cactus.initialize(viewsMediator);
    }
    
    public void mousePressed (MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        
        Cactus next = null;
        int dist2 = -1;
        
        for (Cactus cactus : cactusList){
            int cx = cactus.getX();
            int cy = cactus.getY();
            int newDist2 = (cx-x)*(cx-x) + (cy-y)*(cy-y);
            
            System.out.println ((cx-x) + " " + (cy-y));
            
            if (dist2 < 0 || newDist2 < dist2){
                next = cactus;
                dist2 = newDist2;
            }
        }
        
        if (dist2 != -1 && dist2 < 4000){
            System.out.println ("HOLD!");
            holdCactus = next;
            holdCactus.setState(CactusState.HOLD);
        }
    }
    
    public void mouseReleased (MouseEvent e){
        if (holdCactus != null){
            holdCactus.setState(CactusState.FREE);
            holdCactus = null;
        }
    }
    
    public void mouseDragged (MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        int dx = x - mouse_x;
        int dy = y - mouse_y;
        
        if (holdCactus != null)
            holdCactus.translate(dx, dy);
        
        mouse_x = x;
        mouse_y = y;
    }
    
    public void mouseMoved (MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        int dx = x - mouse_x;
        int dy = y - mouse_y;
        
        mouse_x = x;
        mouse_y = y;
    }
    
    public void mouseClicked (MouseEvent e){}
    public void mouseEntered (MouseEvent e){}
    public void mouseExited (MouseEvent e){}
    
}
