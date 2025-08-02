package com.boomliapps.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boomliapps.dto.DtoHome;
import com.boomliapps.dto.DtoRoom;
import com.boomliapps.entities.Home;
import com.boomliapps.entities.Room;
import com.boomliapps.repository.HomeRepository;
import com.boomliapps.service.IHomeService;


@Service
public class HomeServiceImpl implements IHomeService{

	@Autowired
	private HomeRepository homeRepository;


    
	
	@Override
	public DtoHome getDtoHomeById(Long id) {
		Optional<Home> optHome = homeRepository.findById(id);
		if (optHome.isEmpty()||optHome==null) {
			return null;
		}
		Home dbHome = optHome.get();
		DtoHome dtoHome = new DtoHome();
	    List<DtoRoom> rooms = new ArrayList<>();
		BeanUtils.copyProperties(dbHome, dtoHome);
		BeanUtils.copyProperties(dbHome.getRoom(), rooms);
		dtoHome.setRooms(rooms);
		return dtoHome;
		/*DtoHome dtoHome = new DtoHome();
		List<DtoRoom> dtoRooms = new ArrayList<>();
		List<Room> dbRooms = dbHome.getRoom();
		BeanUtils.copyProperties(dbHome, dtoHome);
		for (Room room : dbRooms) {
			DtoRoom dtoRoom = new DtoRoom();
			BeanUtils.copyProperties(room, dtoRoom);
			dtoRooms.add(dtoRoom);
		}
		dtoHome.setRooms(dtoRooms);
		return dtoHome;*/
		
	}

}
