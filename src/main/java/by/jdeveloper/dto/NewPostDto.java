package by.jdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPostDto {
    private String title;
    private String text;
    private List<String> tags;
}
