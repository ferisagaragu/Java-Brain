/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabrain.run;

import com.vdurmont.emoji.EmojiParser;

/**
 *
 * @author Fernando García
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String emostring ="😂😍🎉👍";
        String a = new StringBuilder().appendCodePoint(emostring.codePointAt(emostring.offsetByCodePoints(0, 1))).toString();
        
        System.out.println(a);
        String str = "An :smiley:string &#128516;with a few :wink:emojis!";
        String result = EmojiParser.parseToUnicode(str);
        System.out.println(result);
        // Prints:
        // "An 😀awesome 😃string 😄with a few 😉emojis!"
    }
    
}
