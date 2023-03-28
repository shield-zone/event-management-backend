package com.shield.eventmanagement.services.organizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.eventmanagement.dao.organizer.OrganizerDao;
import com.shield.eventmanagement.entities.Event;
import com.shield.eventmanagement.entities.Location;
import com.shield.eventmanagement.entities.Organizer;
import com.shield.eventmanagement.entities.user.User;
import com.shield.eventmanagement.exceptions.InvalidException;
import com.shield.eventmanagement.exceptions.organizer.OrganizerNotFoundException;
import com.shield.eventmanagement.exceptions.user.UserNotFoundException;
import com.shield.eventmanagement.repositories.event.EventRepository;
import com.shield.eventmanagement.repositories.location.LocationRepository;
import com.shield.eventmanagement.repositories.user.UserRepository;
import com.shield.eventmanagement.request.organizer.OrganizerRequest;
import com.shield.eventmanagement.request.organizer.OrganizerUpdateRequest;

@Service
public class OrganizerServiceImpl implements OrganizerService {

	@Autowired
	OrganizerDao organizerDao;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	LocationRepository locationRepository;

	@Override
	public Organizer create(OrganizerRequest organizerRequest) throws InvalidException, UserNotFoundException {

//		Optional<Event> event = eventRepository.findByEventId(organizerRequest.getEventId());
		
		
		Optional<User> userOptional = userRepository.findById(organizerRequest.getId());
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User with giver user Id not found");
		}
		User user = userOptional.get();
		
		Organizer organizer = new Organizer();
		
		organizer.setEmailId(user.getUserName());
		organizer.setOrganizerId(user.getUserId());
		organizer.setOrganizerName(user.getName());
		organizer.setPhoneNumber(organizerRequest.getPhoneNumber());
		organizer.setDeleted(false);
		organizer.setRating(organizerRequest.getRating());
		organizer.setPresentSince(organizerRequest.getPresentSince());
		organizer.setWebsite(organizerRequest.getPresentSince());
//		organizer.setEvents(Collections.singletonList(event.get()));
		
		
		if(!isValid(organizer))
		{
			throw new InvalidException("Invalid Mobile Number");
		}
		
		organizer = organizerDao.save(organizer);

		return organizer;
	}

	private boolean isValid(Organizer organizer) {
		
		if(organizer.getPhoneNumber()!=null)
		{
			String regexPattern = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[6789]\\d{9}$";
			String phoneNumber = organizer.getPhoneNumber();

			return phoneNumber.matches(regexPattern);
		}
		return true;
	}

	@Override
	public Organizer update(OrganizerUpdateRequest organizerUpdateRequest) {
	
		Organizer organizer = organizerDao.fetchByUsername(organizerUpdateRequest.getEmail());
		
		
		if(organizerUpdateRequest.getOrganizerName()!=null)
		{
			organizer.setOrganizerName(organizerUpdateRequest.getOrganizerName());
		}
		
		if(organizerUpdateRequest.getPhoneNumber()!=null)
		{
			organizer.setPhoneNumber(organizerUpdateRequest.getPhoneNumber());
		}
		
		if(organizerUpdateRequest.getPresentSince()!=null)
		{
			organizer.setPresentSince(organizerUpdateRequest.getPresentSince());
		}
		
		if(organizerUpdateRequest.getRating()!=null)
		{
			organizer.setRating(organizerUpdateRequest.getRating());
		}
		
		if(organizerUpdateRequest.getWebsite()!=null)
		{
			organizer.setRating(organizerUpdateRequest.getWebsite());
		}
		
		if(organizerUpdateRequest.getEvents()!=null)
		{
			List<Event> events = organizerUpdateRequest.getEvents();
			List<Event> newList = new ArrayList<>();
			
			Event currentEvent = null;
			
			for(Event event: events)
			{
				if(event.getEventId()!=null)
				{
					currentEvent = eventRepository.findById(event.getEventId()).get();
				}
				else
				{
					currentEvent = new Event();
				}
				
				if(event.getEndDate()!=null)
				{
					currentEvent.setEndDate(event.getEndDate());
				}
				
				if(event.getEventName()!=null)
				{
					currentEvent.setEventName(event.getEventName());
				}
				
				if(event.getStartDate()!=null)
				{
					currentEvent.setStartDate(event.getStartDate());
				}
				
				if(event.getEventType()!=null)
				{
					currentEvent.setEventType(event.getEventType());
				}
				
				if(event.getEventPrice()!=null)
				{
					currentEvent.setEventPrice(event.getEventPrice());
				}
				
				if(event.getLocation()!=null)
				{
					Location currentLocation = null;
					if(event.getLocation().getLocationId()!=null)
					{
						currentLocation = locationRepository.findById(event.getLocation().getLocationId()).get();
					}
					else
					{
						currentLocation = new Location();
					}
					
					if(event.getLocation().getAddress()!=null)
					{
						currentLocation.setAddress(event.getLocation().getAddress());
					}
					
					if(event.getLocation().getCountry()!=null)
					{
						currentLocation.setCountry(event.getLocation().getCountry());
					}
					
					if(event.getLocation().getLocationName()!=null)
					{
						currentLocation.setLocationName(event.getLocation().getLocationName());
					}
					
					if(event.getLocation().getPincode()!=null)
					{
						currentLocation.setPincode(event.getLocation().getPincode());
					}
					
					if(event.getLocation().getState()!=null)
					{
						currentLocation.setState(event.getLocation().getState());
					}
					
					event.setLocation(currentLocation);
				}
				
				newList.add(currentEvent);
			}
			organizer.setEvents(newList);
		}
		
		organizer = organizerDao.save(organizer);
		
		return organizer;
	}

	@Override
	public String delete(Long organizerId) throws OrganizerNotFoundException {
		
		Optional<Organizer> organizerOptional = organizerDao.fetchById(organizerId);
		if(!organizerOptional.isPresent()) {
			throw new OrganizerNotFoundException("Organizer with the given Id is not found on Backend");
		}
		Organizer organizer = organizerOptional.get();

		organizer.setDeleted(true);
	    organizerDao.save(organizer);
		return "Organizer Deleted Successfully";
	}

	@Override
	public Optional<Organizer> fetchById(Long organizerId) throws OrganizerNotFoundException {
		return organizerDao.fetchById(organizerId);
	}

	@Override
	public Organizer fetchByUsername(String username) throws OrganizerNotFoundException {
		
		Organizer organizer = organizerDao.fetchByUsername(username);
		if(organizer==null)
		{
			throw new OrganizerNotFoundException();
		}
		return organizer;
	}

	@Override
	public Organizer fetchByWebsite(String website) throws OrganizerNotFoundException {
		
		Organizer organizer = organizerDao.fetchByWebsite(website);
		if(organizer==null)
		{
			throw new OrganizerNotFoundException();
		}
		
		return organizer;
	}

	@Override
	public List<Organizer> fetchByRating(String rating) {
		
		List<Organizer> organizerList = organizerDao.fetchByRating(rating);
		return organizerList;
	}

	@Override
	public List<Organizer> fetchAll() {
		List<Organizer> organizerList = organizerDao.fetchAll();
		return organizerList;
	}

	@Override
	public List<Event> fetchAllEvent() {
		List<Organizer> organizerList = fetchAll();
		List<Event> eventList = new ArrayList<>();
		
		for(Organizer organizer: organizerList)
		{
			eventList.addAll(organizer.getEvents());
		}
		
		return eventList;
	}

}
