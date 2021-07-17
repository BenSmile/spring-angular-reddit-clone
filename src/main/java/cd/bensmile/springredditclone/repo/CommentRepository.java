package cd.bensmile.springredditclone.repo;

import cd.bensmile.springredditclone.model.Comment;
import cd.bensmile.springredditclone.model.Post;
import cd.bensmile.springredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
