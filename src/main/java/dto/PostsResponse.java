package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostsResponse {
    private List<PostDto> posts;
    private boolean hasPrev;
    private boolean hasNext;
    private int lastPage;

}
