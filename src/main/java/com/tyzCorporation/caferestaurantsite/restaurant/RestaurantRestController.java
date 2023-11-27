package com.tyzCorporation.caferestaurantsite.restaurant;

import com.tyzCorporation.caferestaurantsite.exception.NoPhotoException;
import com.tyzCorporation.caferestaurantsite.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/photo")
public class RestaurantRestController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/upload")
    public String uploadPhoto(@AuthenticationPrincipal AppUser appUser,
                              @RequestParam("photo") MultipartFile photo) {
        if (photo.isEmpty() || photo.isEmpty()) {
            return "File is empty";
        }
        try {
            restaurantService.savePhoto(appUser, photo);
            return "Photo uploaded successfully";
        } catch (IOException e) {
            return "Failed to upload photo";
        }
    }

    @GetMapping("/{type}/{photoId}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable String type, @PathVariable Long photoId) throws NoPhotoException {
        RestaurantEntity restaurant = restaurantService.getPhotoById(photoId);
        if (restaurant == null) {
            throw new NoPhotoException("no photo");
        }

        if (type.equals("rectangle")) {
            return ResponseEntity.ok()
                    .body(restaurant.getPhoto());
        } else if (type.equals("circle")) {
            return ResponseEntity.ok()
                    .body(restaurant.getPhoto());
        } else {
            throw new IllegalArgumentException("Invalid photo type");
        }
    }
}
