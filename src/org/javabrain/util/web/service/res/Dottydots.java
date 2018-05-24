package org.javabrain.util.web.service.res;

import java.net.URL;

/**
 * @author Fernando García
 * @version 0.0.1
 */
public class Dottydots {

    public static URL paint_bucket; 
    
    public static URL alphabet(String name){
        String[] duo = name.split("_");
        String outLink = "";
        if(duo.length > 0){
            
            for(String data:duo){
                data = String.valueOf(data.charAt(0)).toUpperCase() + data.substring(1,data.length());
                outLink += data+"_"; 
            }
            
            outLink = outLink.substring(0,outLink.length()-1);
            
            try{return new URL("https://raw.githubusercontent.com/ferisagaragu/Java-Brain/master/icon/dottydots/DIY/icons8_"+outLink+"_100px.png");}catch(Exception e){}
        }else{
            try{return new URL("https://raw.githubusercontent.com/ferisagaragu/Java-Brain/master/icon/dottydots/DIY/icons8_"+name+"_100px.png");}catch(Exception e){}
        }

        return null;
    }
    
    public static URL animals(String name){return null;}
    public static URL arrows(String name){return null;}
    public static URL astrology(String name){return null;}
    public static URL baby(String name){return null;}
    public static URL beauty(String name){return null;}
    public static URL business(String name){return null;}
    public static URL cinema(String name){return null;}
    public static URL city(String name){return null;}
    public static URL clothing(String name){return null;}
    public static URL computerHardware(String name){return null;}
    public static URL crime(String name){return null;}
    public static URL culture(String name){return null;}
    public static URL diy(String name){return null;}
    public static URL data(String name){return null;}
    public static URL drinks(String name){return null;}
    public static URL ecommerce(String name){return null;}
    public static URL editing(String name){return null;}
    public static URL files(String name){return null;}
    public static URL finance(String name){return null;}
    public static URL folders(String name){return null;}
    public static URL food(String name){return null;}
    public static URL gamming(String name){return null;}
    public static URL hands(String name){return null;}
    public static URL healthcare(String name){return null;}
    public static URL holidays(String name){return null;}
    public static URL household(String name){return null;}
    public static URL industry(String name){return null;}
    public static URL maps(String name){return null;}
    public static URL mediaControls(String name){return null;}
    public static URL messaging(String name){return null;}
    public static URL military(String name){return null;}
    public static URL mobile(String name){return null;}
    public static URL music(String name){return null;}
    public static URL network(String name){return null;}
    public static URL operatingSystem(String name){return null;}
    public static URL people(String name){return null;}
    public static URL photoVideo(String name){return null;}
    public static URL plants(String name){return null;}
    public static URL popularIcons(String name){return null;}
    public static URL printing(String name){return null;}
    public static URL profile(String name){return null;}
    public static URL programming(String name){return null;}
    public static URL science(String name){return null;}
    public static URL security(String name){return null;}
    public static URL shopping(String name){return null;}
    public static URL socialMedia(String name){return null;}
    public static URL sports(String name){return null;}
    public static URL timeDate(String name){return null;}
    public static URL transport(String name){return null;}
    public static URL travel(String name){return null;}
    public static URL userInterface(String name){return null;}
    public static URL weather(String name){return null;}
    
    /*
    $alphabet/
    $animals/
    $arrows/
    $astrology/
    $baby/
    $beauty/
    $business/
    $cinema/
    $city/
    $clothing/
    $computerHardware/
    $crime/
    $culture/
    $diy/
    $data/
    $drinks/
    $ecommerce/
    $editing/
    $files/
    $finance/
    $folders/
    $food/
    $gamming/
    $hands/
    $healthcare/
    $holidays/
    $household/
    $industry/
    $maps/
    $mediaControls/
    $messaging/
    $military/
    $mobile/
    $music/
    $network/
    $operatingSystem/
    $people/
    $photoVideo/
    $plants/
    $popularIcons/
    $printing/
    $profile/
    $programming/
    $science/
    $security/
    $shopping/
    $socialMedia/
    $sports/
    $timeDate/
    $transport/
    $travel/
    $userInterface/
    $weather/
    */
    
}
