package org.javabrain.util.data;

import java.util.Calendar;

public class Date {

    public static int DAY;
    public static int MONTH;
    public static int YEAR;

    public static int HOUR;
    public static int MINUTE;
    public static int SECOND;

    public static int AM_PM;

    public static String getDate() {
        Calendar c1 = Calendar.getInstance();
        DAY = c1.get(Calendar.DATE);
        MONTH = c1.get(Calendar.MONTH);
        YEAR = c1.get(Calendar.YEAR);

        String out = formatTime(c1.get(Calendar.DATE)) + "/"
                +formatMonth(c1.get(Calendar.MONTH)) + "/"
                +c1.get(Calendar.YEAR);
        return out;
    }

    public static String getDate(String token) {
        Calendar c1 = Calendar.getInstance();
        DAY = c1.get(Calendar.DATE);
        MONTH = c1.get(Calendar.MONTH);
        YEAR = c1.get(Calendar.YEAR);

        String out = formatTime(c1.get(Calendar.DATE)) + token
                +formatMonth(c1.get(Calendar.MONTH)) + token
                +c1.get(Calendar.YEAR);
        return out;
    }

    public static String getTimeAMPM(){
        Calendar c1 = Calendar.getInstance();
        HOUR = c1.get(Calendar.HOUR);
        MINUTE = c1.get(Calendar.MINUTE);
        SECOND = c1.get(Calendar.SECOND);
        AM_PM = c1.get(Calendar.AM_PM);

        String out = formatHour(c1.get(Calendar.HOUR)) + ":"
                +formatTime(c1.get(Calendar.MINUTE)) + ":"
                +formatTime(c1.get(Calendar.SECOND)) + " " +
                formatAMPM(c1.get(Calendar.AM_PM));
        return out;
    }

    public static String getTime(){
        Calendar c1 = Calendar.getInstance();
        HOUR = c1.get(Calendar.HOUR_OF_DAY);
        MINUTE = c1.get(Calendar.MINUTE);
        SECOND = c1.get(Calendar.SECOND);

        String out = formatHour(c1.get(Calendar.HOUR_OF_DAY)) + ":"
                +formatTime(c1.get(Calendar.MINUTE)) + ":"
                +formatTime(c1.get(Calendar.SECOND));
        return out;
    }

    public static String getCompleteDate() {
        return getDate() + " " + getTimeAMPM();
    }

    public static String getCompleteDate(String token){
        return getDate(token) + " " + getTimeAMPM();
    }

    public static String sumDate(int days){
        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.DAY_OF_YEAR, days);
        DAY = c1.get(Calendar.DATE);
        MONTH = c1.get(Calendar.MONTH);
        YEAR = c1.get(Calendar.YEAR);

        String out = formatTime(c1.get(Calendar.DATE)) + "/"
                +formatMonth(c1.get(Calendar.MONTH)) + "/"
                +c1.get(Calendar.YEAR);
        return out;
    }

    public static String sumDate(int days,String token){
        Calendar c1 = Calendar.getInstance();
        DAY = c1.get(Calendar.DATE);
        MONTH = c1.get(Calendar.MONTH);
        YEAR = c1.get(Calendar.YEAR);

        c1.add(Calendar.DAY_OF_YEAR, days);
        String out = formatTime(c1.get(Calendar.DATE)) + token
                +formatMonth(c1.get(Calendar.MONTH)) + token
                +c1.get(Calendar.YEAR);
        return out;
    }

    //METODOS PRIVADOS
    private static String formatMonth(int number){
        switch (number) {
            case 0: return "01";
            case 1: return "02";
            case 2: return "03";
            case 3: return "04";
            case 4: return "05";
            case 5: return "06";
            case 6: return "07";
            case 7: return "08";
            case 8: return "09";
            case 9: return "10";
            case 10: return "11";
            case 11: return "12";
        }
        return "";
    }

    private static String formatHour(int number){
        switch (number) {
            case 0: return "12";
            case 1: return "01";
            case 2: return "02";
            case 3: return "03";
            case 4: return "04";
            case 5: return "05";
            case 6: return "06";
            case 7: return "07";
            case 8: return "08";
            case 9: return "09";
            default: return number + "";
        }
    }

    private static String formatTime(int number){
        switch (number) {
            case 0: return "00";
            case 1: return "01";
            case 2: return "02";
            case 3: return "03";
            case 4: return "04";
            case 5: return "05";
            case 6: return "06";
            case 7: return "07";
            case 8: return "08";
            case 9: return "09";
            default: return number + "";
        }
    }

    private static  String formatAMPM(int number){
        if (number == 0) {
            return "a.m.";
        } else {
            return "p.m.";
        }
    }
}
