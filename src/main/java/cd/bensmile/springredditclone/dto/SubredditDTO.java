package cd.bensmile.springredditclone.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubredditDTO {
    private Long id;
    private String name;
    private String descpription;
    private Integer numberOfPosts;

}
