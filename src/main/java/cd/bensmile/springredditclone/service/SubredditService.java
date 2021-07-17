package cd.bensmile.springredditclone.service;


import cd.bensmile.springredditclone.dto.SubredditDTO;
import cd.bensmile.springredditclone.model.Subreddit;
import cd.bensmile.springredditclone.repo.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private SubredditRepository subredditRepository;

    public SubredditDTO save(SubredditDTO subredditRequest) {
        Subreddit subreddit = mapSubredditDTO(subredditRequest);
        subreddit.setCreatedDate(Instant.now());
        subreddit = subredditRepository.save(subreddit);
        subredditRequest.setId(subreddit.getId());
        return subredditRequest;
    }

    private Subreddit mapSubredditDTO(SubredditDTO subredditRequest) {

        return Subreddit.builder()
            .description(subredditRequest.getDescpription())
            .name(subredditRequest.getName())
            .build();
    }

    public List<Subreddit> findAll() {
        return subredditRepository.findAll();
    }
}
