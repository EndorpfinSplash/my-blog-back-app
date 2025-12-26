package by.jdeveloper.controller;

import by.jdeveloper.service.FilesService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FilesController {

    private final FilesService filesService;

    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    // POST эндпоинт для загрузки файла
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return filesService.upload(file);
    }

    // GET эндпоинт для скачивания файла
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable(name = "filename") String filename) {
        Resource file = filesService.download(filename);
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(file);
    }
}