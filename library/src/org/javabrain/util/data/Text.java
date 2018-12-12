package org.javabrain.util.data;

import com.vdurmont.emoji.EmojiParser;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipFile;
import org.dts.spell.SpellChecker;
import org.dts.spell.dictionary.openoffice.OpenOfficeSpellDictionary;
import org.javabrain.Neuron;
import org.javabrain.util.alert.Log;
import org.javabrain.util.resource.Archive;

public class Text {

    private static boolean warning = true;
    private static Spell spell = Spell.getInstance();

    private String text;

    public Text(String text) {
        this.text = text;
        this.text = Text.check(Text.emojify(text));
    }
    
    public Text(String text,int paragraphs) {
        this.text = Text.check(Text.emojify(text));
        String[] format = this.text.split(" ");
        int originalPara = paragraphs;
        this.text = "";
        
        for (int i = 0; i < format.length; i++){
            if( paragraphs == (i + 1)) {
                this.text += format[i] + "\n";
                paragraphs = paragraphs + originalPara;
            } else {
                this.text += format[i] + " ";
            }
        }
    }

    @Override
    public String toString() {
        return text; //To change body of generated methods, choose Tools | Templates.
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public static void setDiccionary(String dicName) {
        try {
            spell.getChecker().setDictionary(new OpenOfficeSpellDictionary(new ZipFile(Archive.PROYECT_PATH + "\\dic\\" + dicName + ".zip")));
        } catch (IOException ex) {
            Logger.getLogger(Text.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    para escribir ' se usa /´
     */
    public static String stringify(String text) {
        if (text != null) {
            return text.replace("'","\"").replace("/´","'");
        }
        return "";
    }
    
    public static boolean isNumber(String text) {
        return text.matches("[-+]?[0-9]*\\.?[0-9]*");
    }
    
    public static String check(String text){

        String texts[] = text.replace(".","").split(" ");
        String out = "";

            SpellChecker checker = spell.getChecker();
            checker.setCaseSensitive(false);
            String word = "";
            String suggetion[];
            String words = "";

            for (int i = 0; i < texts.length; i++) {

                words = "";
                try{
                    word = checker.hasSpellErrors(texts[i]).getFirstSuggestion();
                    suggetion = checker.hasSpellErrors(texts[i]).getSuggestions();
                    for (int j = 0; j < suggetion.length; j++){

                        if( j == 0){
                            words = words +suggetion[j]+"\n";
                        }else{
                            words = words +" - "+suggetion[j]+"\n";
                        }
                    }

                    if (warning) {
                        Log.alert(texts[i] + " fue cambiado por: " + words);
                    }

                }catch (Exception e){
                    word = texts[i];
                }

                if(word != null) {
                    out = out +" "+ word;
                }

            }

        
        return upperFirst(out.substring(1,out.length()) + ".");
    }

    public static String upperFirst(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }

    public static void addIgnore(String text){
        spell.getChecker().addIgnore(text);
    }

    public static boolean isCorrect(String text){
        return spell.getChecker().isCorrect(text);
    }

    public static String emojify(String text) {        
        return EmojiParser.parseToUnicode(text);
    }
}

class Spell {

    private SpellChecker checker;

    private static final Spell ourInstance = new Spell();

    public static Spell getInstance() {
        return ourInstance;
    }

    private Spell() {
        try {
            checker = new SpellChecker(new OpenOfficeSpellDictionary(new ZipFile(Archive.PROYECT_PATH + "\\dic\\" + Neuron.defaultDiccionary() + ".dic")));
        } catch (IOException ex) {
            Logger.getLogger(Spell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SpellChecker getChecker() {
        return checker;
    }

    public void setChecker(SpellChecker checker) {
        this.checker = checker;
    }
}
