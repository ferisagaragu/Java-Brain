package org.javabrain.swing.container;

import java.awt.Image;
import java.beans.SimpleBeanInfo;

/**
 *@author Fernando García
 */
public class PanelImageBeanInfo extends SimpleBeanInfo{
    
    private final Image icon16;
    private final Image icon32;
    private final Image icon16M;
    private final Image icon32M;

    public PanelImageBeanInfo() {
        icon16 = loadImage("/res/beaninfo/panelimage16.png");
        icon32 = loadImage("/res/beaninfo/panelimage32.png");
        icon16M = loadImage("/res/beaninfo/panelimage16.png");
        icon32M = loadImage("/res/beaninfo/panelimage32.png");
    }

    @Override
    public Image getIcon(int iconKind) {
         
        switch(iconKind){
            case 1:return icon16;
            case 2:return icon32;
            case 3:return icon16M;
            case 4:return icon32M;
        }
        return null;
    }
    
}
