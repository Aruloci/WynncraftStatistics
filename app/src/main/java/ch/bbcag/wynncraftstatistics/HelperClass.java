package ch.bbcag.wynncraftstatistics;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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

    public static int findItemIcon(String itemCat){
        Map<String , Integer> icons = new HashMap<String , Integer>();
        icons.put("Shovel", R.drawable.shovel);
        icons.put("Stick", R.drawable.stick2);
        icons.put("Bow", R.drawable.bow);
        icons.put("Shears", R.drawable.shears);

        icons.put("Diamond Boots", R.drawable.diamond_boots);
        icons.put("Diamond Helmet", R.drawable.diamond_helmet);
        icons.put("Diamond Leggings", R.drawable.diamond_leggings);
        icons.put("Diamond Chestplate", R.drawable.diamond_chestplate);

        icons.put("Dyed Leather Boots", R.drawable.leather_boots);
        icons.put("Dyed Leather Cap", R.drawable.leather_helmet);
        icons.put("Dyed Leather Pants", R.drawable.leather_leggings);
        icons.put("Dyed Leather Tunic", R.drawable.leather_chestplate);

        icons.put("Leather Boots", R.drawable.leather_boots);
        icons.put("Leather Cap", R.drawable.leather_helmet);
        icons.put("Leather Pants", R.drawable.leather_leggings);
        icons.put("Leather Tunic", R.drawable.leather_chestplate);

        icons.put("Golden Boots", R.drawable.gold_boots);
        icons.put("Golden Helmet", R.drawable.gold_helmet);
        icons.put("Golden Leggings", R.drawable.gold_leggings);
        icons.put("Golden Chestplate", R.drawable.gold_chestplate);

        icons.put("Iron Boots", R.drawable.iron_boots);
        icons.put("Iron Helmet", R.drawable.iron_helmet);
        icons.put("Iron Leggings", R.drawable.iron_leggings);
        icons.put("Iron Chestplate", R.drawable.iron_chestplate);

        icons.put("Chain Boots", R.drawable.chain_boots);
        icons.put("Chain Helmet", R.drawable.chain_helmet2);
        icons.put("Chain Leggings", R.drawable.chain_leggings);
        icons.put("Chain Chestplate", R.drawable.chain_chestplate2);
        icons.put("Creeper Head", R.drawable.creeper_head);


        return icons.get(itemCat);
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
