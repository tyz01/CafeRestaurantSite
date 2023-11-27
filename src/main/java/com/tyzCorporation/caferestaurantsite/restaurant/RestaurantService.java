package com.tyzCorporation.caferestaurantsite.restaurant;

import com.tyzCorporation.caferestaurantsite.user.AppUser;
import com.tyzCorporation.caferestaurantsite.user.AppUserRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.io.*;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class RestaurantService {
    private final AppUserRepository appUserRepository;

    private final RestaurantRepository restaurantRepository;

    private byte[] compressImage(byte[] imageBytes) throws IOException {
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));

        ByteArrayOutputStream compressedStream = new ByteArrayOutputStream();

        ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();

        writer.setOutput(ImageIO.createImageOutputStream(compressedStream));
        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.7f);

        writer.write(null, new IIOImage(image, null, null), param);
        writer.dispose();

        byte[] compressedImageBytes = compressedStream.toByteArray();
        compressedStream.close();

        return compressedImageBytes;
    }

    public void savePhoto(AppUser appUser, MultipartFile photo) throws IOException {

        appUser = appUserRepository.findById(appUser.getId()).orElse(appUser);
        RestaurantEntity restaurant = restaurantRepository.findByAppUser(appUser);
        byte[] photoBytes = photo.getBytes();
        restaurant.setPhoto(compressImage(photoBytes));
        restaurant.setDateCreated(LocalDateTime.now());
        restaurantRepository.save(restaurant);
    }

    public RestaurantEntity getPhotoById(Long photoId) {
        return restaurantRepository.findById(photoId).orElse(null);
    }

}
