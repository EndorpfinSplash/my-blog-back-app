package model;

import lombok.Data;

import java.util.List;

@Data
public class Post {
    private String id;
    private String title;
    private String text;
    List<String> tags;
    private Integer likesCount;
    private Integer commentCount;
}
