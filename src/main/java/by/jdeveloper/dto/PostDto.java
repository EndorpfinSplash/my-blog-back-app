package by.jdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private String id;
    private String title;
    private String text;
    private List<String> tags;
    private Long likesCount;
    private Long commentCount;
}
