package ch.bbcag.wynncraftstatistics;

/**
 * Created by zpfisd on 24.06.2015.
 */
public class Holder {
    private final String TAG = "Holder";
    private String error = "-";

    public Holder() {
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
