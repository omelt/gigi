package eleven.lc.gigi.myutil;

import java.util.Random;

public class CodeUtil {

    private static final char[] strDigits ={'1','2','3','4','5','6','7','8','9','0','Q','W','E','R','T',
            'Y','U','I','O','P','A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M'};

    private static final Random rand=new Random();

    public static String CheckCode(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++)
            stringBuilder.append(strDigits[rand.nextInt(strDigits.length)]);
        return stringBuilder.toString();
    }
}

