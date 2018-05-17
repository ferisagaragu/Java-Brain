package org.javabrain.util.web.service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Fernando García
 */
public class Valuta {

    private static Scraping s = Scraping.getInstance();
    
    //
    public static final float USDBUY = s.map.get("usd-buy");
    public static final float USDSELL = s.map.get("usd");
    //
    public static final float EURBUY = s.map.get("eur-buy");
    public static final float EURSELL = s.map.get("eur");
    //
    public static final float GBPBUY = s.map.get("gbp-buy");
    public static final float GBPSELL = s.map.get("gbp");
    //
    public static final float JPYBUY = s.map.get("jpy-buy");
    public static final float JPYSELL = s.map.get("jpy");
    
    public static final float CNYBUY = s.map.get("cny-buy");
    public static final float CNYSELL = s.map.get("cny");
    
    public static final float BRLBUY = s.map.get("brl-buy");
    public static final float BRLSELL = s.map.get("brl");
    
    public static final float CADBUY = s.map.get("cad-buy");
    public static final float CADSELL = s.map.get("cad");
    
    // <editor-fold defaultstate="collapsed" desc="Pesos"> 
    public static String mnxToUsdType(float mnx) {
        DecimalFormat df = new DecimalFormat("#.00 USD");
        float a = mnx / USDSELL;
        return df.format(a);
    }

    public static float mnxToUsd(float mnx) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format(mnx / USDSELL));
    }

    public static String mnxToEurType(float mnx) {
        DecimalFormat df = new DecimalFormat("#.00 EUR");
        float a = mnx / EURSELL;
        return df.format(a);
    }

    public static float mnxToEur(float mnx) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format(mnx / EURSELL));
    }

    public static String mnxToGbpType(float mnx) {
        DecimalFormat df = new DecimalFormat("#.00 GBP");
        float a = mnx / GBPSELL;
        return df.format(a);
    }

    public static float mnxToGbp(float mnx) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format(mnx / GBPSELL));
    }

    public static String mnxToJryType(float mnx) {
        DecimalFormat df = new DecimalFormat("#.00 JRY");
        float a = mnx / JPYSELL;
        return df.format(a);
    }

    public static float mnxToJry(float mnx) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format(mnx / JPYSELL));
    }

    public static String mnxToCnyType(float mnx) {
        DecimalFormat df = new DecimalFormat("#.00 CNY");
        float a = mnx / CNYSELL;
        return df.format(a);
    }

    public static float mnxToCny(float mnx) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format(mnx / CNYSELL));
    }

    public static String mnxToBrlType(float mnx) {
        DecimalFormat df = new DecimalFormat("#.00 BRL");
        float a = mnx / BRLSELL;
        return df.format(a);
    }

    public static float mnxToBrl(float mnx) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format(mnx / BRLSELL));
    }

    public static String mnxToCadType(float mnx) {
        DecimalFormat df = new DecimalFormat("#.00 CAD");
        float a = mnx / CADSELL;
        return df.format(a);
    }

    public static float mnxToCad(float mnx) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format(mnx / CADSELL));
    }
    // </editor-fold>   
    
    // <editor-fold defaultstate="collapsed" desc="Dolar"> 
    //Dolares
    public static String usdToMnxType(float usd){
        DecimalFormat df = new DecimalFormat("#.00 MNX");
        float a = usd * USDSELL;
        return df.format(a);
    }

    public static float usdToMnx(float usd){
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format(usd * USDSELL));
    }

    public static String usdToEurType(float usd){
        DecimalFormat df = new DecimalFormat("#.00 EUR");
        float a = (usd*USDSELL) / EURSELL;
        return df.format(a);
    }

    public static float usdToEur(float usd){
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((usd*USDSELL) / EURSELL));
    }

    public static String usdToGbpType(float usd){
        DecimalFormat df = new DecimalFormat("#.00 GBP");
        float a = (usd*USDSELL) / GBPSELL;
        return df.format(a);
    }

    public static float usdToGbp(float usd){
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((usd*USDSELL) / GBPSELL));
    }

    public static String usdToJpyType(float usd){
        DecimalFormat df = new DecimalFormat("#.00 JPY");
        float a = (usd*USDSELL) / JPYSELL;
        return df.format(a);
    }

    public static float usdToJpy(float usd){
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((usd*USDSELL) / JPYSELL));
    }

    public static String usdToCnyType(float usd){
        DecimalFormat df = new DecimalFormat("#.00 CNY");
        float a = (usd*USDSELL) / CNYSELL;
        return df.format(a);
    }

    public static float usdToCny(float usd){
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((usd*USDSELL) / CNYSELL));
    }

    public static String usdToBrlType(float usd){
        DecimalFormat df = new DecimalFormat("#.00 BRL");
        float a = (usd*USDSELL) / BRLSELL;
        return df.format(a);
    }

    public static float usdToBrl(float usd){
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((usd*USDSELL) / BRLSELL));
    }

    public static String usdToCadType(float usd){
        DecimalFormat df = new DecimalFormat("#.00 CAD");
        float a = (usd*USDSELL) / CADSELL;
        return df.format(a);
    }

    public static float usdToCad(float usd){
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((usd*USDSELL) / CADSELL));
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Euro"> 
    //Euros
    public static String eurToMnxType(float eur) {
        DecimalFormat df = new DecimalFormat("#.00 MNX");
        float a = eur * EURSELL;
        return df.format(a);
    }

    public static float eurToMnx(float eur) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format(eur * EURSELL));
    }

    public static String eurToUsdType(float eur) {
        DecimalFormat df = new DecimalFormat("#.00 USD");
        float a = (eur * EURSELL) / USDSELL;
        return df.format(a);
    }

    public static float eurToUsd(float eur) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((eur * EURSELL) / USDSELL));
    }

    public static String eurToGbpType(float eur) {
        DecimalFormat df = new DecimalFormat("#.00 GBP");
        float a = (eur * EURSELL) / GBPSELL;
        return df.format(a);
    }

    public static float eurToGbp(float eur) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((eur * EURSELL) / GBPSELL));
    }

    public static String eurToJpyType(float eur) {
        DecimalFormat df = new DecimalFormat("#.00 JPY");
        float a = (eur * EURSELL) / JPYSELL;
        return df.format(a);
    }

    public static float eurToJpy(float eur) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((eur * EURSELL) / JPYSELL));
    }

    public static String eurToCnyType(float eur) {
        DecimalFormat df = new DecimalFormat("#.00 CNY");
        float a = (eur * EURSELL) / CNYSELL;
        return df.format(a);
    }

    public static float eurToCny(float eur) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((eur * EURSELL) / CNYSELL));
    }

    public static String eurToBrlType(float eur) {
        DecimalFormat df = new DecimalFormat("#.00 BRL");
        float a = (eur * EURSELL) / BRLSELL;
        return df.format(a);
    }

    public static float eurToBrl(float eur) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((eur * EURSELL) / BRLSELL));
    }

    public static String eurToCadType(float eur) {
        DecimalFormat df = new DecimalFormat("#.00 CAD");
        float a = (eur * EURSELL) / CADSELL;
        return df.format(a);
    }

    public static float eurToCad(float eur) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((eur * EURSELL) / CADSELL));
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Libra Esterlina"> 
    public static String gbpToMnxType(float gbp) {
        DecimalFormat df = new DecimalFormat("#.00 MNX");
        float a = gbp * GBPSELL;
        return df.format(a);
    }

    public static float gbpToMnx(float gbp) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format(gbp * GBPSELL));
    }

    public static String gbpToUsdType(float gbp) {
        DecimalFormat df = new DecimalFormat("#.00 USD");
        float a = (gbp * GBPSELL) / USDSELL;
        return df.format(a);
    }

    public static float gbpToUsd(float gbp) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((gbp * GBPSELL) / USDSELL));
    }

    public static String gbpToEurType(float gbp) {
        DecimalFormat df = new DecimalFormat("#.00 EUR");
        float a = (gbp * GBPSELL) / EURSELL;
        return df.format(a);
    }

    public static float gbpToEur(float gbp) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((gbp * GBPSELL) / EURSELL));
    }

    public static String gbpToJpyType(float gbp) {
        DecimalFormat df = new DecimalFormat("#.00 JPY");
        float a = (gbp * GBPSELL) / JPYSELL;
        return df.format(a);
    }

    public static float gbpToJpy(float gbp) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((gbp * GBPSELL) / JPYSELL));
    }

    public static String gbpToCnyType(float gbp) {
        DecimalFormat df = new DecimalFormat("#.00 CNY");
        float a = (gbp * GBPSELL) / CNYSELL;
        return df.format(a);
    }

    public static float gbpToCny(float gbp) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((gbp * GBPSELL) / CNYSELL));
    }

    public static String gbpToBrlType(float gbp) {
        DecimalFormat df = new DecimalFormat("#.00 BRL");
        float a = (gbp * GBPSELL) / BRLSELL;
        return df.format(a);
    }

    public static float gbpToBrl(float gbp) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((gbp * GBPSELL) / BRLSELL));
    }

    public static String gbpToCadType(float gbp) {
        DecimalFormat df = new DecimalFormat("#.00 CAD");
        float a = (gbp * GBPSELL) / CADSELL;
        return df.format(a);
    }

    public static float gbpToCad(float gbp) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((gbp * GBPSELL) / CADSELL));
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Yen japonés"> 
    public static String jpyToMnxType(float jpy) {
        DecimalFormat df = new DecimalFormat("#.00 MNX");
        float a = jpy * JPYSELL;
        return df.format(a);
    }

    public static float jpyToMnx(float jpy) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format(jpy * JPYSELL));
    }

    public static String jpyToUsdType(float jpy) {
        DecimalFormat df = new DecimalFormat("#.00 USD");
        float a = (jpy * JPYSELL) / USDSELL;
        return df.format(a);
    }

    public static float jpyToUsd(float jpy) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((jpy * JPYSELL) / USDSELL));
    }

    public static String jpyToEurType(float jpy) {
        DecimalFormat df = new DecimalFormat("#.00 EUR");
        float a = (jpy * JPYSELL) / EURSELL;
        return df.format(a);
    }

    public static float jpyToEur(float jpy) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((jpy * JPYSELL) / EURSELL));
    }

    public static String jpyToGbpType(float jpy) {
        DecimalFormat df = new DecimalFormat("#.00 GBP");
        float a = (jpy * JPYSELL) / GBPSELL;
        return df.format(a);
    }

    public static float jpyToGbp(float jpy) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((jpy * JPYSELL) / GBPSELL));
    }

    public static String jpyToCnyType(float jpy) {
        DecimalFormat df = new DecimalFormat("#.00 CNY");
        float a = (jpy * JPYSELL) / CNYSELL;
        return df.format(a);
    }

    public static float jpyToCny(float jpy) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((jpy * JPYSELL) / CNYSELL));
    }

    public static String jpyToBrlType(float jpy) {
        DecimalFormat df = new DecimalFormat("#.00 BRL");
        float a = (jpy * JPYSELL) / BRLSELL;
        return df.format(a);
    }

    public static float jpyToBrl(float jpy) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((jpy * JPYSELL) / BRLSELL));
    }

    public static String jpyToCadType(float jpy) {
        DecimalFormat df = new DecimalFormat("#.00 CAD");
        float a = (jpy * JPYSELL) / CADSELL;
        return df.format(a);
    }

    public static float jpyToCad(float jpy) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format((jpy * JPYSELL) / CADSELL));
    }
    // </editor-fold>
    
    
}

// <editor-fold defaultstate="collapsed" desc="Clase Scraping"> 
class Scraping{

    public final String url = "https://www.hsbc.com.mx/1/2/es/personas/divisas";
    public final int maxPages = 2;
    public Map<String,Float> map = new HashMap();

    public Scraping() {

        for (int i=1; i<maxPages; i++){

            String urlPage = String.format(url, i);

            if (getStatusConnectionCode(urlPage) == 200) {

                Document document = getHtmlDocument(urlPage);
                Elements entradas = document.select("div.grid.grid_24");
                String data = entradas.get(1).getElementsByClass("vdp_tableStyle").text();
                String div = data
                .replace("Divisas A la compra A la Venta Dólar Americano $","")
                .replace("Euro","")
                .replace("Libra Esterlina","")
                .replace("Yen Japonés","")
                .replace("Yuan Chino","")
                .replace("Real Brasileño","")
                .replace("Real Brasileño","")
                .replace("Dólar Canadiense","")
                .replace(" ","")
                .replace("$",",");
                String[] divs = div.split(",");

                map.put("usd-buy",Float.parseFloat(divs[0]));
                map.put("usd",Float.parseFloat(divs[1]));
                map.put("eur-buy",Float.parseFloat(divs[2]));
                map.put("eur",Float.parseFloat(divs[3]));
                map.put("gbp-buy",Float.parseFloat(divs[4]));
                map.put("gbp",Float.parseFloat(divs[5]));//Libra esterlina
                map.put("jpy-buy",Float.parseFloat(divs[6]));
                map.put("jpy",Float.parseFloat(divs[7]));
                map.put("cny-buy",Float.parseFloat(divs[8]));
                map.put("cny",Float.parseFloat(divs[9]));
                map.put("brl-buy",Float.parseFloat(divs[10]));
                map.put("brl",Float.parseFloat(divs[11]));
                map.put("cad-buy",Float.parseFloat(divs[12]));
                map.put("cad",Float.parseFloat(divs[13]));
 
            }else{
                System.out.println("El Status Code no es OK es: "+getStatusConnectionCode(urlPage));
                break;
            }
        }
    }

    public int getStatusConnectionCode(String url) {

        Response response = null;

        try {
            response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
        }
        return response.statusCode();
    }

    private Document getHtmlDocument(String url) {

        Document doc = null;

        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
        }

        return doc;

    }
    
    public static Scraping getInstance() {
        return ScrapingHolder.INSTANCE;
    }
    
    private static class ScrapingHolder {

        private static final Scraping INSTANCE = new Scraping();
    }
    
    

}
// </editor-fold>