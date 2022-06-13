package com.booking.recruitment.hotel.controller;

import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
  private final HotelService hotelService;
  
  private static final Gson gson = new Gson();

  @Autowired
  public HotelController(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Hotel> getAllHotels() {
    return hotelService.getAllHotels();
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public Hotel createHotel(@RequestBody Hotel hotel) {
    return hotelService.createNewHotel(hotel);
  }
  
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public @ResponseBody ResponseEntity<String> getHotelById(@PathVariable String id) {
	  
	  Long hotelId;
	  try {
		  hotelId = Long.parseLong(id);
	  } catch ( NumberFormatException e) {
		  
		  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		  
	  }
    return new ResponseEntity<>(gson.toJson(hotelService.getHotelById(hotelId)), HttpStatus.OK);
  }
  
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public @ResponseBody ResponseEntity<String> deleteHotelById(@PathVariable String id) {
	  
	  Long hotelId;
	  try {
		  hotelId = Long.parseLong(id);
	  } catch ( NumberFormatException e) {
		  
		  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		  
	  }
	  if (hotelService.deleteHotelById(hotelId)) return new ResponseEntity<>(HttpStatus.OK);
	  return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	  
  }
  
  @GetMapping("/search/{cityId}")
  @ResponseStatus(HttpStatus.OK)
  public @ResponseBody ResponseEntity<String> searchHotelByCityId(@PathVariable String cityId, @RequestParam String sortBy) {
	  
	  Long cityIdNum;
	  try {
		  cityIdNum = Long.parseLong(cityId);
	  } catch ( NumberFormatException e) {
		  
		  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		  
	  }
	  
    return new ResponseEntity<>(gson.toJson(hotelService.getHotelsByCity(cityIdNum)), HttpStatus.OK);
  }
  
}
