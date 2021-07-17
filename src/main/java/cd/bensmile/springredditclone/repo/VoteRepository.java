package cd.bensmile.springredditclone.repo;


import cd.bensmile.springredditclone.model.Post;
import cd.bensmile.springredditclone.model.User;
import cd.bensmile.springredditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
