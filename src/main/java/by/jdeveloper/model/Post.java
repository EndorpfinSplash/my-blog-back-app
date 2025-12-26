package by.jdeveloper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
@AllArgsConstructor
public class Post {
    private Long id;
    private String title;
    private String text;
    private List<String> tags;
    private Long likesCount = 0L;
    private Long commentCount = 0L;
}
