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
import java.util.Random;
import java.util.ArrayList;

/**
 *
 * @author Caio Cesar
 */
public class CactusSystem implements MouseListener, MouseMotionListener{
    
    public enum CactusState{
        FREE, HOLD
    }
    
    public enum CactusSpecies{
        BABY, ADULT, JACKMAN, YANO, GABI, EGRET, MEXICAN, WEASEL, GOD
    }
    
    private ArrayList<Cactus> cactusList;
    
    private int mouse_x = 0;
    private int mouse_y = 0;
    
    private Cactus holdCactus;
    private ViewsMediator viewsMediator;
    
    private Random gen = new Random();
    private long instant;
    
    public CactusSystem(){
        this.instant = System.currentTimeMillis();
        cactusList = new ArrayList<Cactus>();
        holdCactus = null;
    }
    
    public void initialize (ViewsMediator viewsMediator){
        holdCactus = null;
        this.viewsMediator = viewsMediator;
        
        viewsMediator.addMouseListener(this);
        viewsMediator.addMouseMotionListener(this);
    }
    
    public void createCactus (Point position, CactusSystem.CactusSpecies specie){
        Cactus cactus = new Cactus(position, specie);
        cactusList.add(cactus);
        cactus.initialize(viewsMediator);
        Thread thread = new Thread(cactus);
        thread.start();
    }
    
    public void createBox (){
        if (cactusList.size() < 16 && System.currentTimeMillis()-instant > 1000){
            createCactus(new Point(gen.nextInt(916)+25, gen.nextInt(562)+25), CactusSystem.CactusSpecies.BABY);
            instant = System.currentTimeMillis();
        }
        
        
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
