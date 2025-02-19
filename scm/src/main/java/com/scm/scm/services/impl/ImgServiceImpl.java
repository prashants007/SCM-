package com.scm.scm.services.impl;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.scm.helper.AppConstants;
import com.scm.scm.services.ImgService;

@Service
public class ImgServiceImpl implements ImgService {

        private Cloudinary cloudinary;
    
        ImgServiceImpl(Cloudinary cloudinary) {
            this.cloudinary = cloudinary;
        }

        @Override
        public String uploadImage(MultipartFile image) {
                try {
                    String imgFileName = UUID.randomUUID().toString();
                    byte[] imgBytes = new byte[image.getInputStream().available()];
                    image.getInputStream().read(imgBytes);
                    cloudinary.uploader().upload(imgBytes, ObjectUtils.asMap("public_id", imgFileName));
                    return getImgUrl(imgFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
           }

           public String getImgUrl(String publicId) {
               return cloudinary.url().transformation(new Transformation<>()
               .width(AppConstants.IMG_WIDTH)
               .height(AppConstants.IMG_HEIGHT)
               .crop(AppConstants.IMG_CROP)).generate(publicId);
           }
}   
