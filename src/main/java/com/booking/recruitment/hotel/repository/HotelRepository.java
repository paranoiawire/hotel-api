package com.booking.recruitment.hotel.repository;

import com.booking.recruitment.hotel.model.Hotel;
import javax.transaction.*;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

public interface HotelRepository extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {
	
	@Query(value = "SELECT h FROM Hotel h WHERE h.id = ?1", nativeQuery = true)
	Hotel getHotelById(Long hotelId);
	
	@Query(value = "SELECT h FROM Hotel h")
	List<Hotel> findAll();


	@Modifying
	@Transactional
	@Query(value = "UPDATE hotel SET deleted = 'true' WHERE id = ?1", nativeQuery = true)
	int updateDeleteFlag (Long hotelId);
	
	@Query(value = "SELECT count(*) FROM HOTEL", nativeQuery = true)
	BigDecimal count();
	
}
