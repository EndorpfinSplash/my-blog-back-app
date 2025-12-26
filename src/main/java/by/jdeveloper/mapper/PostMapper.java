package by.jdeveloper.mapper;

import by.jdeveloper.dto.NewPostDto;
import by.jdeveloper.dto.PostDto;
import by.jdeveloper.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "likesCount", constant = "0L")
    @Mapping(target = "commentCount", constant = "0L")
    Post toEntity(NewPostDto newPostDto);

    PostDto toDto(Post post);
}
