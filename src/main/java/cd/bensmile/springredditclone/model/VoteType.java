package cd.bensmile.springredditclone.model;

//import com.programming.techie.springredditclone.exceptions.SpringRedditException;

import cd.bensmile.springredditclone.exception.SpringRedditException;

import java.util.Arrays;

public enum VoteType {
    UPVOTE(1), DOWNVOTE(-1);

    private int direction;

    VoteType(int direction) {
    }

    public static VoteType lookup(Integer direction) {
        return Arrays.stream(VoteType.values())
            .filter(value -> value.getDirection().equals(direction))
            .findAny()
//            .orElseThrow(() -> new RuntimeException("Vote not found"));
                .orElseThrow(() -> new SpringRedditException("Vote not found"));
    }

    public Integer getDirection() {
        return direction;
    }
}
