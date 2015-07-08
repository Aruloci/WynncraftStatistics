package ch.bbcag.wynncraftstatistics.Player;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpfisd on 03.07.2015.
 */
public class FriendSearcher {
    private ArrayList<Player> friendList;

    public FriendSearcher(ArrayList<Player> friendList) {
        this.friendList = friendList;
    }

    public String readInput(EditText searchText) {
        String input = null;
        if (searchText != null && searchText.getText() != null) {
            input = searchText.getText().toString();
        }
        return input;
    }

    public List<Player> adaptFriendsToInput(String searchInput) {
        List<Player> changedFriends = new ArrayList<Player>();
        for (Player friend : friendList) {
            if (friend.getPlayerName().toLowerCase().contains(searchInput.toLowerCase())) {
                changedFriends.add(friend);
            }
        }
        return changedFriends;
    }
}