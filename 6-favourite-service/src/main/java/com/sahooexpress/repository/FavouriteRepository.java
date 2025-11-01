package com.sahooexpress.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahooexpress.models.Favourite;

public interface FavouriteRepository extends JpaRepository<Favourite, Long>{
	
	Optional<Favourite> findByUserIdAndProductId(Integer userId, Long productId);

}
