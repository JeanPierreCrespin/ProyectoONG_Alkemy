package alkemy.challenge.Challenge.Alkemy.service;

import alkemy.challenge.Challenge.Alkemy.service.imp.AWSS3Service;
import alkemy.challenge.Challenge.Alkemy.service.imp.UploadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadImageServiceImp {

    private UploadImageService uploadImageService;

    @Autowired
    public UploadImageServiceImp(AWSS3Service imgService) {
        this.uploadImageService = imgService;
    }

    public String uploadFile(MultipartFile multipartFile) {
        return uploadImageService.uploadFile(multipartFile);
    }

    public String deleteFile(String url) {
        return uploadImageService.deleteFile(url);
    }
}
