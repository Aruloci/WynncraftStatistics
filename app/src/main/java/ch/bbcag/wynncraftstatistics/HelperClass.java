package ch.bbcag.wynncraftstatistics;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Yevgen on 07.07.2015.
 */
public class HelperClass {

    static public boolean isNetworkConnectionAvailable(ConnectivityManager connectivityManager) {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return null != networkInfo && networkInfo.isConnected();
    }

    public static String readInput(InputStream inputStream) throws IOException {
        StringBuilder resultBuilder = new StringBuilder();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

        String line;
        while (null != (line = bufferedReader.readLine())) {
            resultBuilder.append(line);
        }

        return resultBuilder.toString();
    }

    public static WynncraftClass genereateWynnClass(String classname){
       // {"Warrior", "Archer", "Mage", "Assassin"}
        WynncraftClass result = null;
        if (classname.equals("Warrior")){
            result = new WynncraftClass(R.drawable.warrior, classname,
                    "The sworn defenders of the citadel at Ragni, " +
                    "warriors are a violent class that often lead battles against the undead." +
                    " Using their great strength he can rocket enemies into the air, or even push them back." +
                    " Their great defense and multiple crowd control abilities make them perfect for front line combat.",
                    16, 52, 82);
            return result;
        } else if (classname.equals("Archer")){
            result = new WynncraftClass(R.drawable.archer, classname,
                    "Trained hunters that stalk pray in Time Valley, " +
                            "archers are a ranged class that are essential for scouting out the road ahead for a party of adventurers." +
                            " Thanks to their escape spell, they are able to retreat from a battle if their foes are too great. ",
                    45, 94, 11);
            return result;
        } else if (classname.equals("Mage")) {
            result = new WynncraftClass(R.drawable.mage, classname,
                    "Trained on the Mage Island in the vast Ocean," +
                            " mages are a powerful class that can use powerful magic to heal members of a party." +
                            " Mages are able to travel large distances within minutes thanks to their teleport ability," +
                            " learnt from the magic of the people of the Dern Province. ",
                    42, 60, 48);
            return result;
        } else if (classname.equals("Assassin")) {
            result = new WynncraftClass(R.drawable.assassin, classname,
                    "Hailing from the depths of the Nivla Woods: assassins uses their various stealth spells to elegantly" +
                            " slay their opponents. Forever in the shadows they can quickly kill an enemy by using their" +
                            " multihits ability. But beware when assassins venture to the Nether; they can use their vanish" +
                            " spell in order to evade attacks.",
                    75, 30, 45);
            return result;
        } else {
            return result;
        }

    }

}
