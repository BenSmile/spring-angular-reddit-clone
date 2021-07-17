package cd.bensmile.springredditclone.controller;

import cd.bensmile.springredditclone.dto.SubredditDTO;
import cd.bensmile.springredditclone.model.Subreddit;
import cd.bensmile.springredditclone.service.SubredditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
@Slf4j
public class SubredditController {

    private SubredditService subredditService;

    @PostMapping("/")
    public ResponseEntity<?> createSubreddit(@RequestBody SubredditDTO subredditRequest){

        return ResponseEntity.status(HttpStatus.CREATED).body(subredditService.save(subredditRequest));
    }

    @GetMapping("/")
    public ResponseEntity<List<Subreddit>> getSubreddits(){
        return ResponseEntity.status(HttpStatus.OK).body(subredditService.findAll());
    }

}
