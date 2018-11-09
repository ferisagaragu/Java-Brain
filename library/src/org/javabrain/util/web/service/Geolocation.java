package org.javabrain.util.web.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.javabrain.util.alert.Log;
import org.javabrain.util.data.Date;
import org.javabrain.util.data.Json;
import org.javabrain.util.event.GeolocationListener;
import org.javabrain.util.event.SearchGeolocationListener;

/***
 * 
 * @author Fernando García
 * @version 0.0.2
 */
public class Geolocation {

    private GeolocationListener listener;
    private SearchGeolocationListener geolocationListener;
    
    public void requestGeoLocation() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                if (Petition.getStatusConnectionCode("https://www.google.com/") == 200) {
                    try {
                        String token = org.javabrain.util.codify.Security.encrypt(Date.getCompleteDate()).replace("+", "_");
                        Petition.openURL("https://javabrain.webcindario.com/location/index.html?token=" + token);
                        int i = 1;
                        Location out = null;
                        while (true) {
                            Json js = Petition.doGet("https://javabrain.webcindario.com/location/service/getlocations.php").JSON;
                            if (js != null) {
                                js = js.getJSONArray("resp");
                                for (Json o : js.values()) {
                                    if (o.getString("token") != null ? o.getString("token").equals(token) : false) {
                                        out = new Location();
                                        try {
                                            out.setCompleteGeolocation(o.getJSONArray("detail"));
                                        } catch (Exception e) {
                                            out.setCompleteGeolocation(new Json("{\"error\":\"undefine road\"}"));
                                        }
                                        try {
                                            out.setFormatAddress(o.getString("address"));
                                        } catch (Exception e) {
                                            out.setFormatAddress("undefine road");
                                        }
                                        try {
                                            out.setLatitude(o.getDouble("lat"));
                                        } catch (Exception e) {
                                            out.setLatitude(0);
                                        }
                                        try {
                                            out.setLongitude(o.getDouble("lng"));
                                        } catch (Exception e) {
                                            out.setLongitude(0);
                                        }
                                        try {
                                            out.setState(o.getJSONArray("detail").getJSONArray(4).getString("long_name"));
                                        } catch (Exception e) {
                                            out.setState("undefine state");
                                        }
                                        try {
                                            out.setMunicipality(o.getJSONArray("detail").getJSONArray(3).getString("long_name"));
                                        } catch (Exception e) {
                                            out.setMunicipality("undefine municipality");
                                        }
                                        try {
                                            out.setCountry(o.getJSONArray("detail").getJSONArray(5).getString("long_name"));
                                        } catch (Exception e) {
                                            out.setCountry("undefine country");
                                        }
                                        try {
                                            out.setStreetNumber(o.getJSONArray("detail").getJSONArray(0).getString("long_name"));
                                        } catch (Exception e) {
                                            out.setStreetNumber("undefine street number");
                                        }
                                        try {
                                            out.setStreet(o.getJSONArray("detail").getJSONArray(1).getString("long_name"));
                                        } catch (Exception e) {
                                            out.setStreet("undefine street");
                                        }
                                        try {
                                            out.setColony(o.getJSONArray("detail").getJSONArray(2).getString("long_name"));
                                        } catch (Exception e) {
                                            out.setColony("undefine colony");
                                        }
                                        try {
                                            out.setPostalCode(o.getJSONArray("detail").getJSONArray(6).getString("long_name"));
                                        } catch (Exception e) {
                                            out.setPostalCode("undefine postal code");
                                        }

                                        Thread thread = new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Map map = new LinkedHashMap<Object, Object>();
                                                map.put("id", o.getInteger("id"));
                                                Petition.doPost("https://javabrain.webcindario.com/location/service/deletelocation.php", map);
                                            }
                                        });
                                        thread.start();

                                        if (listener != null) {
                                            listener.success(out);
                                        }
                                        return;
                                    }
                                }
                            }
                            Thread.sleep(1000);
                            i++;
                            if (i == 15) {
                                return;
                            }
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                } else {
                    Log.error("The request could not be made because there was a problem connecting to the Network.");
                }

                if (listener != null) {
                    listener.error();
                }
            }
        });
        t.start();
    }
    
    public void requestGeoLocation(double lat, double lng) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Json js = Petition.doGet("https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "&key=AIzaSyDOjlPSMtw99Jh4mK1LeicXE692Sh7liWI").JSON;
                    Location location = new Location();
                    Json jsS = js.getJSONArray("results").getJSONArray(0).getJSONArray("address_components");
                    location.setLongitude(lng);
                    location.setLatitude(lat);
                    location.setCompleteGeolocation(jsS);
                    try {
                        location.setFormatAddress(js.getJSONArray("results").getJSONArray(0).getString("formatted_address"));
                    } catch (Exception e) {
                        location.setFormatAddress("");
                    }
                    try {
                        location.setStreetNumber(jsS.getJSONArray(0).getString("long_name"));
                    } catch (Exception e) {
                        location.setStreetNumber("");
                    }
                    try {
                        location.setStreet(jsS.getJSONArray(1).getString("long_name"));
                    } catch (Exception e) {
                        location.setStreet("");
                    }
                    try {
                        location.setColony(jsS.getJSONArray(2).getString("long_name"));
                    } catch (Exception e) {
                        location.setColony("");
                    }
                    try {
                        location.setMunicipality(jsS.getJSONArray(3).getString("long_name"));
                    } catch (Exception e) {
                        location.setMunicipality("");
                    }
                    try {
                        location.setState(jsS.getJSONArray(4).getString("long_name"));
                    } catch (Exception e) {
                        location.setState("");
                    }
                    try {
                        location.setCountry(jsS.getJSONArray(5).getString("long_name"));
                    } catch (Exception e) {
                        location.setCountry("");
                    }
                    try {
                        location.setPostalCode(jsS.getJSONArray(6).getString("long_name"));
                    } catch (Exception e) {
                        location.setPostalCode("");
                    }

                    if (listener != null) {
                        listener.success(location);
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        listener.error();
                    }
                }
            }
        });
        t.start();

    }
    
    public void searchGeoLocation(String hint) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Json out = Petition.doGet("https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + URLEncoder.encode(hint, "UTF-8") + "&location=20.6380308,-103.3836039&radius=30000&key=AIzaSyDOjlPSMtw99Jh4mK1LeicXE692Sh7liWI").JSON;
                    List<Location> location = null;

                    if (out != null) {
                        location = new ArrayList();
                        for (Json js : out.getJSONArray("results").values()) {
                            location.add(requestLocation(js.getJSON("geometry").getJSON("location").getDouble("lat"), js.getJSON("geometry").getJSON("location").getDouble("lng")));
                        }
                    }

                    if (geolocationListener != null) {
                        geolocationListener.success(location);
                    }
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Geolocation.class.getName()).log(Level.SEVERE, null, ex);
                    if (geolocationListener != null) {
                        geolocationListener.error();
                    }
                }
            }
        });
        t.start();
    }

    public void fastSearchGeoLocation(String hint) {
        
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Json out = Petition.doGet("https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + URLEncoder.encode(hint, "UTF-8") + "&location=20.6380308,-103.3836039&radius=30000&key=AIzaSyDOjlPSMtw99Jh4mK1LeicXE692Sh7liWI").JSON;
                    List<Location> location = null;

                    if (out != null) {
                        location = new ArrayList();
                        for (Json js : out.getJSONArray("results").values()) {
                            Location l = new Location();
                            l.setCompleteGeolocation(js);
                            l.setFormatAddress(js.getString("formatted_address"));
                            l.setLatitude(js.getJSON("geometry").getJSON("location").getDouble("lat"));
                            l.setLongitude(js.getJSON("geometry").getJSON("location").getDouble("lng"));
                            location.add(l);
                        }
                    }

                    if (geolocationListener != null) {
                        geolocationListener.success(location);
                    }
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Geolocation.class.getName()).log(Level.SEVERE, null, ex);
                    if (geolocationListener != null) {
                        geolocationListener.error();
                    }
                }
            }
        });

    }
    
    public void setOnRequestLocation(GeolocationListener geolocationListener) {
        this.listener = geolocationListener;
    }
    
    public void setOnSearchLocation(SearchGeolocationListener geolocationListener) {
        this.geolocationListener = geolocationListener;
    }
    
    //Metodos estaticos no asincronos
    
    public static Location requestLocation() {
        if (Petition.getStatusConnectionCode("https://www.google.com/") == 200) {
            try {
                String token = org.javabrain.util.codify.Security.encrypt(Date.getCompleteDate()).replace("+", "_");
                Petition.openURL("https://javabrain.webcindario.com/location/index.html?token=" + token);
                int i = 1;
                Location out = null;
                while (true) {
                    Json js = Petition.doGet("https://javabrain.webcindario.com/location/service/getlocations.php").JSON;
                    if (js != null) {
                        js = js.getJSONArray("resp");
                        for (Json o : js.values()) {
                            if (o.getString("token") != null ? o.getString("token").equals(token) : false) {
                                out = new Location();
                                try {
                                    out.setCompleteGeolocation(o.getJSONArray("detail"));
                                } catch (Exception e) {
                                    out.setCompleteGeolocation(new Json("{\"error\":\"undefine road\"}"));
                                }
                                try {
                                    out.setFormatAddress(o.getString("address"));
                                } catch (Exception e) {
                                    out.setFormatAddress("undefine road");
                                }
                                try {
                                    out.setLatitude(o.getDouble("lat"));
                                } catch (Exception e) {
                                    out.setLatitude(0);
                                }
                                try {
                                    out.setLongitude(o.getDouble("lng"));
                                } catch (Exception e) {
                                    out.setLongitude(0);
                                }
                                try {
                                    out.setState(o.getJSONArray("detail").getJSONArray(4).getString("long_name"));
                                } catch (Exception e) {
                                    out.setState("undefine state");
                                }
                                try {
                                    out.setMunicipality(o.getJSONArray("detail").getJSONArray(3).getString("long_name"));
                                } catch (Exception e) {
                                    out.setMunicipality("undefine municipality");
                                }
                                try {
                                    out.setCountry(o.getJSONArray("detail").getJSONArray(5).getString("long_name"));
                                } catch (Exception e) {
                                    out.setCountry("undefine country");
                                }
                                try {
                                    out.setStreetNumber(o.getJSONArray("detail").getJSONArray(0).getString("long_name"));
                                } catch (Exception e) {
                                    out.setStreetNumber("undefine street number");
                                }
                                try {
                                    out.setStreet(o.getJSONArray("detail").getJSONArray(1).getString("long_name"));
                                } catch (Exception e) {
                                    out.setStreet("undefine street");
                                }
                                try {
                                    out.setColony(o.getJSONArray("detail").getJSONArray(2).getString("long_name"));
                                } catch (Exception e) {
                                    out.setColony("undefine colony");
                                }
                                try {
                                    out.setPostalCode(o.getJSONArray("detail").getJSONArray(6).getString("long_name"));
                                } catch (Exception e) {
                                    out.setPostalCode("undefine postal code");
                                }

                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Map map = new LinkedHashMap<Object, Object>();
                                        map.put("id", o.getInteger("id"));
                                        Petition.doPost("https://javabrain.webcindario.com/location/service/deletelocation.php", map);
                                    }
                                });
                                thread.start();

                                return out;
                            }
                        }
                    }
                    Thread.sleep(1000);
                    i++;
                    if (i == 15) {
                        return null;
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return null;
            }
        } else {
            Log.error("The request could not be made because there was a problem connecting to the Network.");
        }
        return null;
    }

    public static Location requestLocation(double lat,double lng) {
        Json js = Petition.doGet("https://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+lng+"&key=AIzaSyDOjlPSMtw99Jh4mK1LeicXE692Sh7liWI").JSON;
        Location location = new Location();
        Json jsS = js.getJSONArray("results").getJSONArray(0).getJSONArray("address_components");
        location.setLongitude(lng);
        location.setLatitude(lat);
        location.setCompleteGeolocation(jsS);
        try {
            location.setFormatAddress(js.getJSONArray("results").getJSONArray(0).getString("formatted_address"));
        } catch (Exception e) { location.setFormatAddress(""); }
        try {
            location.setStreetNumber(jsS.getJSONArray(0).getString("long_name"));
        } catch (Exception e) {
            location.setStreetNumber("");
        } 
        try {
            location.setStreet(jsS.getJSONArray(1).getString("long_name"));
        } catch (Exception e) {
            location.setStreet("");
        }
        try {
            location.setColony(jsS.getJSONArray(2).getString("long_name"));
        } catch (Exception e) {
            location.setColony("");
        }
        try {
            location.setMunicipality(jsS.getJSONArray(3).getString("long_name"));
        } catch (Exception e){
            location.setMunicipality("");
        }
        try {
            location.setState(jsS.getJSONArray(4).getString("long_name"));
        } catch (Exception e) {
            location.setState("");
        }
        try {
            location.setCountry(jsS.getJSONArray(5).getString("long_name"));
        }catch (Exception e) {
            location.setCountry("");
        }
        try {
            location.setPostalCode(jsS.getJSONArray(6).getString("long_name"));
        } catch (Exception e) {
            location.setPostalCode("");
        }
        return location;
    }

    public static List<Location> searchLocation(String hint) {
        try {
            Json out = Petition.doGet("https://maps.googleapis.com/maps/api/place/textsearch/json?query="+URLEncoder.encode(hint, "UTF-8")+"&location=20.6380308,-103.3836039&radius=30000&key=AIzaSyDOjlPSMtw99Jh4mK1LeicXE692Sh7liWI").JSON;
            List<Location> location = null;
            
            if (out != null) {
                location = new ArrayList();
                for (Json js : out.getJSONArray("results").values()) {
                    location.add(requestLocation(js.getJSON("geometry").getJSON("location").getDouble("lat"),js.getJSON("geometry").getJSON("location").getDouble("lng")));
                }
            }
            
            return location;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Geolocation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static List<Location> fastSearchLocation(String hint) {
        try {
            Json out = Petition.doGet("https://maps.googleapis.com/maps/api/place/textsearch/json?query="+URLEncoder.encode(hint, "UTF-8")+"&location=20.6380308,-103.3836039&radius=30000&key=AIzaSyDOjlPSMtw99Jh4mK1LeicXE692Sh7liWI").JSON;
            List<Location> location = null;
            
            if (out != null) {
                location = new ArrayList();
                for (Json js : out.getJSONArray("results").values()) {
                    Location l = new Location();
                    l.setCompleteGeolocation(js);
                    l.setFormatAddress(js.getString("formatted_address"));
                    l.setLatitude(js.getJSON("geometry").getJSON("location").getDouble("lat"));
                    l.setLongitude(js.getJSON("geometry").getJSON("location").getDouble("lng"));
                    location.add(l);
                }
            }
            
            return location;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Geolocation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

}
