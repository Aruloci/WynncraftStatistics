package ch.bbcag.wynncraftstatistics;

/**
 * Created by zdomaa on 03.07.2015.
 */
public class Server {
    private String servername;
    private String curPlayerCount;

    public Server(String servername, String curPlayerCount){
        this.servername = servername;
        this.curPlayerCount = curPlayerCount;
    }

    public String getServername() {
        return servername;
    }

    public String getCurPlayerCount() {
        return curPlayerCount;
    }
}
