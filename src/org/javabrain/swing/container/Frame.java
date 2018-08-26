package org.javabrain.swing.container;

import org.javabrain.util.data.Json;

import java.awt.Color;
import java.awt.Component;
import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * @author Fernando García
 * @version 0.0.1
 */
public class Frame extends JFrame{

    public Frame() {
        
        getContentPane().setBackground(Color.white);
        setSize(600,400);
    }

    //METODOS PUBLICOS

    public void showOnCenter(){
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public boolean clearTextComponets(){
        try{clearText(this.getContentPane().getComponents());}catch(Exception e){return false;}
        return true;
    }

    public boolean setEnabledComponets(boolean in){
        try{enabledComponents(this.getContentPane().getComponents(),in);}catch(Exception e){return false;}
        return true;
    }

    public void fillData(Json json){
        try {fillDataIn(getContentPane().getComponents(),json); }catch (Exception e){}
    }

    //==================================================================

    //METODOS PRIVADOS

    private void clearText(Component[] components){
        for (Component component : components) {

            try{
                JTextComponent jtc = (JTextComponent) component;
                jtc.setText("");
            }catch(Exception e){}
            
            try{
                JScrollPane j = (JScrollPane) component;
                clearText(j.getViewport().getComponents());
            }catch(Exception e){}
            
            try{
                JPanel panel = (JPanel) component;
                clearText(panel.getComponents());
                
            }catch(Exception e){}

        }
    }

    private void enabledComponents(Component[] components,boolean in){
        for (Component component : components) {
            component.setEnabled(in);

            try{
                JScrollPane j = (JScrollPane) component;
                enabledComponents(j.getViewport().getComponents(),in);
            }catch(Exception e){}

            try{
                JPanel panel = (JPanel) component;
                enabledComponents(panel.getComponents(),in);

            }catch(Exception e){}

        }
    }

    private void fillDataIn(Component[] components,Json json){
        for (Component component : components) {

            try{
                JTextComponent jtc = (JTextComponent) component;
                if(json.existKey(jtc.getName())){
                    jtc.setText(json.getObject(jtc.getName()).toString());
                }
            }catch(Exception e){}

            try{
                AbstractButton jtc = (AbstractButton) component;
                if(json.existKey(jtc.getName())){
                    jtc.setText(json.getObject(jtc.getName()).toString());
                }
            }catch(Exception e){}

            try{
                JLabel jtc = (JLabel) component;
                if(json.existKey(jtc.getName())){
                    jtc.setText(json.getObject(jtc.getName()).toString());
                }
            }catch(Exception e){}

            try{
                JScrollPane j = (JScrollPane) component;
                fillDataIn(j.getViewport().getComponents(),json);
            }catch(Exception e){}

            try{
                JPanel panel = (JPanel) component;
                fillDataIn(panel.getComponents(),json);

            }catch(Exception e){}

        }
    }

    //===================================================================

    //HACER
    /*
    -Soporte para Listas,Tablas,Combobox y Popups
    -Usar nomenclaturas como L,T,C,P para distinguir que se refiere
    a una lista y a que componente se dirije
    -Hacer la tabla al final ya que es la mas compleja
     */
    
}
