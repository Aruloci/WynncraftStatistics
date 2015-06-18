package ch.bbcag.wynncraftstatistics;

/**
 * Created by zdomaa on 18.06.2015.
 */
public class MenuLine {
    private int imageId;
    private String text;

    public  MenuLine(int image, String text){
        this.imageId = image;
        this.text = text;
    }

    public int getImageId() {
        return imageId;
    }

    public String getText() {
        return text;
    }
}
