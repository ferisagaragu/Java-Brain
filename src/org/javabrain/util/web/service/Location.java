package org.javabrain.util.web.service;


import maps.java.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Location {

    private static String street;
    private static String streetNumber;
    private static String city;
    private static String postalCode;
    private static String municipality;
    private static String state;
    private static String country;
    private static String direction;

    public static void setLocation(String hintDirection) {
        MapsJava.setKey("AIzaSyCzWaJYw_MW87ganzyaVlxB9igfGMTTrW8");
        Geocoding ObjGeocoding=new Geocoding();
        try {
            Point2D.Double resultado = ObjGeocoding.getCoordinates(hintDirection);
            if (resultado.x != 0.0 && resultado.y != 0.0) {
                String direcction = "";
                try {
                    //Toda la direccion
                    direcction = ObjGeocoding.getAddressFound();
                    direction = direcction;
                } catch (Exception ex){
                    direction = "NO DATA";
                }

                try {
                    //Calle
                    String outStreet = direcction.split(",")[0];
                    int streetConunt = outStreet.split(" ").length - 1;
                    String outStree = "";
                    for (int i = 0; i < streetConunt; i++){
                        outStree += " " + outStreet.split(" ")[i];
                    }
                    street = outStree.substring(1,outStree.length());
                } catch (Exception ex){
                    street = "NO DATA";
                }

                try {
                    //Numero de la calle
                    String outStreetNumber = direcction.split(",")[0];
                    outStreetNumber = outStreetNumber.split(" ")[outStreetNumber.split(" ").length - 1];
                    streetNumber = outStreetNumber;
                } catch (Exception ex){
                    streetNumber = "NO DATA";
                }

                try {
                    //Esto te da la colonia
                    String outCity = direcction.split(",")[1];
                    city = outCity.substring(1, outCity.length());
                } catch (Exception ex){
                    city = "NO DATA";
                }

                try {
                    //Esto te da el codigo postal
                    String outPostalCode = direcction.split(",")[2];
                    postalCode = outPostalCode.split(" ")[1];
                } catch (Exception ex){
                    postalCode = "NO DATA";
                }

                try {
                    //Esto te da el municipio
                    String outmunicipal = direcction.split(",")[2];
                    municipality = outmunicipal.split(" ")[2];
                } catch (Exception ex){
                    municipality = "NO DATA";
                }

                try {
                    //Obtiene el estado
                    ArrayList list = ObjGeocoding.getAddress(resultado.x, resultado.y);
                    String stat = list.get(list.size() - 2).toString().split(",")[0];
                    state = stat;
                } catch (Exception ex){
                    state = "NO DATA";
                }

                try {
                    //Obtiene el estado
                    ArrayList list2 = ObjGeocoding.getAddress(resultado.x, resultado.y);
                    String stat2 = list2.get(list2.size() - 1).toString().split(",")[0];
                    country = stat2;
                } catch (Exception ex){
                    country = "NO DATA";
                }

                /*
                StaticMaps maps = new StaticMaps();
                Image imagenMapa = maps.getStaticMap("fraccionamiento valle de las flores",
                        14, new Dimension(500, 500), 4, StaticMaps.Format.png,
                        StaticMaps.Maptype.roadmap);
                if (imagenMapa != null) {
                    ImageIcon imgIcon = new ImageIcon(imagenMapa);
                    Icon iconImage = (Icon) imgIcon;
                    Console.viewer(iconImage);
                }*/
            }
        }catch (Exception e){}
    }

}
