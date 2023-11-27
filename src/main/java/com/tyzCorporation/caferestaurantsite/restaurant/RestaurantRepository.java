package com.tyzCorporation.caferestaurantsite.restaurant;

import com.tyzCorporation.caferestaurantsite.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

    RestaurantEntity findByAppUser(AppUser appUser);
}
