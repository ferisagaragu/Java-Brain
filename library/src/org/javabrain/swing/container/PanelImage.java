package org.javabrain.swing.container;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author Fernando García
 * @version 0.0.1
 */
public class PanelImage extends JPanel{
    
    private Image image;
    private Icon icon;

    public PanelImage() {
        image = new ImageIcon(getClass().getResource("/res/component/noimg.png")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 =(Graphics2D) g;
        if(image!=null)
            g2.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

  
    public void setImage(String fileName){
        setIcon(new ImageIcon(getClass().getResource(fileName))); 
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon){
        this.icon=icon;
        if(icon!=null)
            image=((ImageIcon)icon).getImage();
    } 
     
}
