package cd.bensmile.springredditclone.repo;

import cd.bensmile.springredditclone.model.Post;
import cd.bensmile.springredditclone.model.Subreddit;
import cd.bensmile.springredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
