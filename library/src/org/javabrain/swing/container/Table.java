package org.javabrain.swing.container;


import org.javabrain.util.data.Json;

import java.awt.Color;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;

/**
 *
 * @author Fernny
 */
public class Table extends JTable {

    private JTableHeader th = getTableHeader();
    private Color headerBackground = Color.GRAY;
    private Color headerForeground = Color.WHITE;
    private Border headerBorder;
    private String titles[];
    private TableModel sqlModel;
    private ResultSet rs;
    private int specificSize;
    private DefaultTableModel dm;

    private Object header[];
    private Object dataMap[];
    private Json data;

    public Table() {
        super();
        this.setBorder(null);
        this.setShowHorizontalLines(false);
        this.setShowVerticalLines(false);
        
        th.setBorder(null);
        setHeaderBackground(headerBackground);
        setHeaderForeground(headerForeground);
        
        setSelectionBackground(Color.orange);
        setBorder(null);
    }

    public void setSQL(String[] titles,ResultSet rs)
    {
        this.titles = titles;
        
        
        DefaultTableModel model = null;
        int num = titles.length;
        
        try
        {
            model = new DefaultTableModel(null,titles);

            String array[] = new String[num];

            while (rs.next()) 
            {  
                for (int i = 0; i < num; i++) {
                    array[i] = rs.getString(i+1);
                }
                
               model.addRow(array);
            }

            rs.close();

            }
            catch(Exception e)
            {
                System.err.println(e.getMessage());
            }
        
        setModel(model);
        
        sqlModel= model;
    }
    
    public void SQL()
    {
        DefaultTableModel model = null;
        int num = titles.length;
        
        try
        {
            model = new DefaultTableModel(null,titles);

            String array[] = new String[num];

            while (rs.next()) 
            {  
                for (int i = 0; i < num; i++) {
                    array[i] = rs.getString(i+1);
                }
                
               model.addRow(array);
            }

            rs.close();

            }
            catch(Exception e)
            {
                System.err.println(e.getMessage());
            }
        
        setModel(model);
        
        sqlModel= model;
    }
    
    public boolean update(ResultSet resultSet)
    {
        this.rs = resultSet;
        
        DefaultTableModel model = null;
        int num = titles.length;
        
        try
        {
            model = new DefaultTableModel(null,titles);

            String array[] = new String[num];
            
            while (rs.next()) 
            {  
                for (int i = 0; i < num; i++) 
                {
                    array[i] = rs.getString(i+1);
                }

               model.addRow(array);
            }

            rs.close();
            
            }
            catch(Exception e)
            {
                System.err.println(e.getMessage());
                return false;
            }
        
        setModel(model);
        
        return true;
        
    }
    
    public void hideColumn(int row)
    {
        setRowSelectionInterval(row,row);
        getColumnModel().getColumn(row).setMaxWidth(row);
        getColumnModel().getColumn(row).setMinWidth(row);
        getColumnModel().getColumn(row).setPreferredWidth(row);
    }
    
    public void setSpecificSize(int specificSize)
    {
        this.specificSize = specificSize;
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = getColumnModel();
        
        for (int i = 0; i < columnModel.getColumnCount(); i++) 
        {
            columnModel.getColumn(i).setPreferredWidth(specificSize);
        }
    }

    public int getSpecificSize() {
        return specificSize;
    }
    
    public Color getHeaderBackground() {
        return headerBackground;
    }

    public void setHeaderBackground(Color headerBackground) {
        this.headerBackground = headerBackground;
        th.setBackground(headerBackground);
    }

    public Color getHeaderForeground() {
        return headerForeground;
    }

    public void setHeaderForeground(Color headerForeground) {
        this.headerForeground = headerForeground;
        th.setForeground(headerForeground);
    }

    public Border getHeaderBorder() {
        return headerBorder;
    }

    public void setHeaderBorder(Border headerBorder) {
        this.headerBorder = headerBorder;
        th.setBorder(headerBorder);
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public TableModel getSqlModel() {
        return sqlModel;
    }

    public void setSqlModel(TableModel sqlModel) {
        this.sqlModel = sqlModel;
    }

    public void fillTable(Object[] header,Object[] dataMap,Json data){

        this.header = header;
        this.dataMap = dataMap;
        this.data = data;

        DefaultTableModel dtm = new DefaultTableModel(null,header);
        ArrayList<Integer> phamtomColums = new ArrayList<>();

        if (data != null) {
            data.values().stream().map((value) -> {
                ArrayList<String> al = new ArrayList<>();
                for (Object dataMap1 : dataMap) {

                    al.add(value.getString(dataMap1));
                }
                return al;
            }).forEachOrdered((al) -> {
                dtm.addRow(al.toArray());
            });
        }

        int i = 0;
        for(Object dataMap1 : header) {
            if(dataMap1.toString().contains("{") &&
                    dataMap1.toString().contains("&") &&
                    dataMap1.toString().contains("}")){
                phamtomColums.add(i);
            }
            i++;
        }

        this.setModel(dtm);

        phamtomColums.forEach((io) -> {
            this.getColumn(this.getColumnName(io)).setWidth(0);
            this.getColumn(this.getColumnName(io)).setMinWidth(0);
            this.getColumn(this.getColumnName(io)).setMaxWidth(0);
        });
    }

    public void search(String consulta){
        dm = (DefaultTableModel) this.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
        this.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
}
