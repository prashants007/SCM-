package com.scm.scm.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImgService {

    String uploadImage(MultipartFile image);
    public String getImgUrl(String publicId);

}
