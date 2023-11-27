package com.tyzCorporation.caferestaurantsite.restaurant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tyzCorporation.caferestaurantsite.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantEntity implements Comparable<RestaurantEntity>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @Column
    private double ratingPhoto;
    @Column(name = "date_created")
    private LocalDateTime dateCreated;
    @Column
    private long topPhoto;

    @Override
    public int compareTo(RestaurantEntity other) {
        return this.dateCreated.compareTo(other.dateCreated);
    }
}