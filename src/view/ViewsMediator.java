/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import logic.Cactus;
import systems.GameSystem;

/**
 *
 * @author Caio Cesar
 */
public class ViewsMediator extends JPanel{
    
    public final int SCREEN_WIDTH = 965;
    public final int SCREEN_HEIGHT = 611;
    
    private GameSystem gameSystem;
    private Map<Cactus, CactusView> cactusViews;
    
    public ViewsMediator(){
        cactusViews = new HashMap<>();
    }
    
    public void initialize (GameSystem gameSystem){
        
        this.gameSystem = gameSystem;
        
        //Mudar para um background maroto.
        setBackground(Color.BLACK);
        setFocusable(true);
        
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    }
    
    public void attachNewCactusViewToCactus (Cactus cactus){
        CactusView view = new CactusView(cactus, this);
        view.loadResources();
        
        cactusViews.put(cactus, view);
    }
    
    public void updateCactusView (Cactus cactus){
        CactusView view = cactusViews.get(cactus);
        view.loadResources();
    }
    
    public void removeCactusView (Cactus cactus){
        cactusViews.remove(cactus);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (Map.Entry<Cactus, CactusView> entry : cactusViews.entrySet())
        {
            entry.getValue().draw(g);
        }
    }
    
    public Image makeColorTransparent(Image im) {
        ImageFilter filter = new RGBImageFilter() {
            @Override
            public final int filterRGB(int x, int y, int rgb) {
                Color c = new Color(rgb);

                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();

                int alpha = (red == 34 && green == 177 && blue == 76 ? 0 : 255);
                c = new Color(red, green, blue, alpha);
                
                return c.getRGB();
            }
        };

        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }
    
}
