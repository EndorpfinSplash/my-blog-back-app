package mapper;

import dto.NewPostDto;
import model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PostMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "likesCount", constant = "0L")
    @Mapping(target = "commentCount", constant = "0L")
    Post toEntity(NewPostDto newPostDto);
}
