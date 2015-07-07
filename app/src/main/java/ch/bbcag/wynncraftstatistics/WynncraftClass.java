package ch.bbcag.wynncraftstatistics;

import android.graphics.drawable.Drawable;

/**
 * Created by Yevgen on 07.07.2015.
 */
public class WynncraftClass {
    private int icon;
    private String classTitle;
    private String description;
    private int speed;
    private int strength;
    private int health;

    public int getIcon() {
        return icon;
    }

    public String getClassTitle() {
        return classTitle;
    }

    public String getDescription() {
        return description;
    }

    public int getSpeed() {
        return speed;
    }

    public int getStrength() {
        return strength;
    }

    public int getHealth() {
        return health;
    }

    public WynncraftClass(int icon, String classTitle, String description, int speed, int strength, int health){
        this.icon = icon;
        this.classTitle = classTitle;
        this.description = description;
        this.health = health;
        this.speed = speed;
        this.strength = strength;

    }

}
