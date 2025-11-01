package com.sahooexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sahooexpress.dto.FavouriteDTO;
import com.sahooexpress.service.FavouriteService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/favourites")
@Slf4j
public class FavouriteController {
	
	@Autowired
	private FavouriteService favouriteService;
	
	@PostMapping("/user/{userId}")
	public FavouriteDTO addProductToWishlist(@PathVariable Integer userId,@RequestParam Long productId) {
		log.info("FavouriteController addProductToWishlist");
		return favouriteService.addProductToWishList(userId, productId);
	}
	
	@DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<Void> removeProductFromWishlist(
            @PathVariable Integer userId, 
            @PathVariable Long productId) {
    	log.info("FavouriteController removeProductFromWishlist");
        favouriteService.removeProductFromWishList(userId, productId);
        return ResponseEntity.noContent().build();
    }
	
	

}
