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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Caio Cesar
 */
public class CactusSystem implements MouseListener, MouseMotionListener{
    
    public enum CactusState{
        BOX, FREE, HOLD
    }
    
    public enum CactusSpecies{
        BABY, ADULT, JACKMAN, YANO, GABI, EGRET, MEXICAN, WEASEL, GOD
    }
    
    private ArrayList<Cactus> cactusList;
    
    private int mouse_x = 0;
    private int mouse_y = 0;
    
    private Cactus holdCactus;
    private ViewsMediator viewsMediator;
    
    private Map<Cactus, Thread> cactusThreads;
    private Map<CactusSpecies, CactusSpecies> nextSpecie;
    
    private Random gen = new Random();
    private long instant;
    private final int cactusRadius = 46;
    
    public CactusSystem(){
        this.instant = System.currentTimeMillis();
        cactusList = new ArrayList<Cactus>();
        holdCactus = null;
        cactusThreads = new HashMap<>();
        nextSpecie = new HashMap<>();
        nextSpecie.put(CactusSpecies.BABY, CactusSpecies.ADULT);
        nextSpecie.put(CactusSpecies.ADULT, CactusSpecies.WEASEL);
        nextSpecie.put(CactusSpecies.WEASEL, CactusSpecies.GABI);
        nextSpecie.put(CactusSpecies.GABI, CactusSpecies.MEXICAN);
        nextSpecie.put(CactusSpecies.MEXICAN, CactusSpecies.EGRET);
        nextSpecie.put(CactusSpecies.EGRET, CactusSpecies.YANO);
        nextSpecie.put(CactusSpecies.YANO, CactusSpecies.JACKMAN);
        nextSpecie.put(CactusSpecies.JACKMAN, CactusSpecies.GOD);
    }
    
    public void initialize (ViewsMediator viewsMediator){
        holdCactus = null;
        this.viewsMediator = viewsMediator;
        
        viewsMediator.addMouseListener(this);
        viewsMediator.addMouseMotionListener(this);
    }
    
    public int getWidth(){
        return viewsMediator.getWidth();
    }
    
    public int getHeight(){
        return viewsMediator.getHeight();
    }
    
    public void createCactus (Point position, CactusSystem.CactusSpecies specie, CactusSystem.CactusState state){
        Cactus cactus = new Cactus(position, specie, state);
        cactusList.add(cactus);
        cactus.initialize(viewsMediator, this);
        Thread thread = new Thread(cactus);
        thread.start();
        cactusThreads.put(cactus, thread);
    }
    
    public void destroyCactus(Cactus cactus){
        cactusList.remove(cactus);
        cactusThreads.remove(cactus);
        viewsMediator.removeCactusView(cactus);
    }
    
    public void fusion (Cactus cactus1, Cactus cactus2){
        int x = (cactus1.getX()+cactus2.getX())/2;
        int y = (cactus1.getY()+cactus2.getY())/2;
        createCactus(new Point (x,y), nextSpecie.get(cactus1.getSpecie()), CactusSystem.CactusState.FREE);
        destroyCactus(cactus1);
        destroyCactus(cactus2);
    }
    
    public void createBox (){
        if (cactusList.size() < 8 && System.currentTimeMillis()-instant > 5000){
            createCactus(new Point(gen.nextInt(viewsMediator.getWidth()-2*cactusRadius)+cactusRadius,
                    gen.nextInt(viewsMediator.getHeight()-2*cactusRadius)+cactusRadius), CactusSystem.CactusSpecies.BABY, CactusSystem.CactusState.BOX);
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
            
            if ((dist2 < 0 || newDist2 < dist2) && cactus.getState() != CactusState.BOX){
                next = cactus;
                dist2 = newDist2;
            }
        }
        
        if (next != null && dist2 < cactusRadius*cactusRadius){
            holdCactus = next;
            holdCactus.setState(CactusState.HOLD);
        }
    }
    
    public void mouseClicked (MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        
        Cactus next = null;
        int dist2 = -1;
        
        for (Cactus cactus : cactusList){
            int cx = cactus.getX();
            int cy = cactus.getY();
            int newDist2 = (cx-x)*(cx-x) + (cy-y)*(cy-y);
            
            if ((dist2 < 0 || newDist2 < dist2) && cactus.getState() == CactusState.BOX){
                next = cactus;
                dist2 = newDist2;
            }
        }
        
        if (next != null && dist2 < cactusRadius*cactusRadius){
            next.setState(CactusState.FREE);
        }
    }
    
    public void mouseReleased (MouseEvent e){
        if (holdCactus != null){
            int x = holdCactus.getX();
            int y = holdCactus.getY();
            
            Cactus next = null;
            int dist2 = -1;
            
            for (Cactus cactus : cactusList){
                int cx = cactus.getX();
                int cy = cactus.getY();
                int newDist2 = (cx-x)*(cx-x) + (cy-y)*(cy-y);

                if ((dist2 < 0 || newDist2 < dist2) && cactus.getState() != CactusState.BOX
                        && cactus.getSpecie() == holdCactus.getSpecie() && cactus != holdCactus){
                    next = cactus;
                    dist2 = newDist2;
                }
            }
            
            if (next != null && dist2 < cactusRadius*cactusRadius){
                fusion(holdCactus,next);
            }
            else{
                holdCactus.setState(CactusState.FREE);
            }
            holdCactus = null;
        }
    }
    
    public void mouseDragged (MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        int dx = x - mouse_x;
        int dy = y - mouse_y;
        
        if (holdCactus != null){
            int cx = holdCactus.getX();
            int cy = holdCactus.getY();
            
            if (cx+dx > cactusRadius && cx+dx < viewsMediator.getWidth() - cactusRadius
                    && cy+dy > cactusRadius && cy+dy < viewsMediator.getHeight()- cactusRadius)
                holdCactus.translate(dx, dy);
            else{
                holdCactus.setState(CactusState.FREE);
                holdCactus = null;
            }
        }
        
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
    
    public void mouseEntered (MouseEvent e){}
    public void mouseExited (MouseEvent e){}
    
}
