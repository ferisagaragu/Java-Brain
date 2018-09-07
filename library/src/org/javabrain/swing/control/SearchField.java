package org.javabrain.swing.control;

import org.javabrain.swing.tools.AutoSuggestor;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Fernando García
 * 
 * @version 0.0.1
 */
public class SearchField extends JTextField{
    
    private JFrame searchWindow;
    
    private Color suggestionTextColor = Color.BLACK; 
    private Color suggestionSelectionColor = Color.BLUE;
    private Color suggestionPanelColor = Color.WHITE;
            
    private ArrayList searchList;
    private AutoSuggestor suggestor;

    public SearchField() {
        searchList = new ArrayList();
    }
    
    //ACCIONES
    public void addSearchable(String searchable){
        try {
            searchList.add(searchable);
            suggestor.setDictionary(searchList);
        } catch (Exception e) {
            System.err.println("Please first set search window");
        }
    }
    
    public void removeSearchable(String searchable){
        try {
            searchList.remove(searchable);
            suggestor.setDictionary(searchList);
        } catch (Exception e) {
            System.err.println("Please first set search window");
        }
    }
    
    public void removeSearchable(int searchable){
        try {
            searchList.remove(searchable);
            suggestor.setDictionary(searchList);
        } catch (Exception e) {
            System.err.println("Please first set search window");
        }
    }
    
    public void removeSearchable(Object searchable){
        try {
            searchList.remove(searchable);
            suggestor.setDictionary(searchList);
        } catch (Exception e) {
            System.err.println("Please first set search window");
        }
    }
    
    public void removeAllSearchable(){
        try {
            searchList.clear();
            suggestor.setDictionary(searchList);
        } catch (Exception e) {
            System.err.println("Please first set search window");
        }
    }
    
    //GETS AND SETS

    public ArrayList getSearchList() {
        return searchList;
    }

    public void setSearchList(ArrayList searchList) {
        this.searchList = searchList;
        suggestor.setDictionary(searchList);
    }
    
    public JFrame getSearchWindow() {
        return searchWindow;
    }

    public void setSearchWindow(JFrame searchWindow) {
        this.searchWindow = searchWindow;
        suggestor = new AutoSuggestor(this, searchWindow, null, suggestionPanelColor, suggestionTextColor, suggestionSelectionColor, 1);
    }

    public Color getSuggestionTextColor() {
        return suggestionTextColor;
    }

    public void setSuggestionTextColor(Color suggestionTextColor) {
        this.suggestionTextColor = suggestionTextColor;
        suggestor.setSuggestionsTextColor(suggestionTextColor);
    }

    public Color getSuggestionSelectionColor() {
        return suggestionSelectionColor;
    }

    public void setSuggestionSelectionColor(Color suggestionSelectionColor) {
        this.suggestionSelectionColor = suggestionSelectionColor;
        suggestor.setSuggestionFocusedColor(suggestionSelectionColor);
    }

    public Color getSuggestionPanelColor() {
        return suggestionPanelColor;
    }

    public void setSuggestionPanelColor(Color suggestionPanelColor) {
        this.suggestionPanelColor = suggestionPanelColor;
        suggestor.getSuggestionsPanel().setBackground(suggestionPanelColor);
    }
        
}
