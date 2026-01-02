package by.jdeveloper.service;

import by.jdeveloper.dao.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class FilesService {

    private final PostRepository postRepository;

    public void uploadImage(Long postId, MultipartFile image) throws IOException {
        String imageName = image.getOriginalFilename();
        postRepository.saveByteArray(postId, imageName, image.getBytes());
    }

    public byte[] downloadFile(Long postId) {
        return postRepository.getFileByPostId(postId);
    }

}
