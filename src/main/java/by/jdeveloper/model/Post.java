package by.jdeveloper.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Post {
    private Long id;
    private String title;
    private String text;
    private List<String> tags;
    @Builder.Default
    private Long likesCount = 0L;
    @Builder.Default
    private Long commentCount = 0L;
}
