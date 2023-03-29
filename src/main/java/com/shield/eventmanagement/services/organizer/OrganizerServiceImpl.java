package com.shield.eventmanagement.services.organizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.eventmanagement.dao.organizer.OrganizerDao;
import com.shield.eventmanagement.entities.Event;
import com.shield.eventmanagement.entities.Organizer;
import com.shield.eventmanagement.entities.user.User;
import com.shield.eventmanagement.exceptions.InvalidException;
import com.shield.eventmanagement.exceptions.organizer.OrganizerNotFoundException;
import com.shield.eventmanagement.exceptions.user.UserNotFoundException;
import com.shield.eventmanagement.repositories.location.LocationRepository;
import com.shield.eventmanagement.repositories.user.UserRepository;
import com.shield.eventmanagement.request.organizer.OrganizerRequest;
import com.shield.eventmanagement.request.organizer.OrganizerUpdateRequest;
import com.shield.eventmanagement.services.event.EventService;

@Service
public class OrganizerServiceImpl implements OrganizerService {

	@Autowired
	OrganizerDao organizerDao;

	@Autowired
	UserRepository userRepository;

	@Autowired
	EventService eventService;

	@Autowired
	LocationRepository locationRepository;

	@Override
	public Organizer create(OrganizerRequest organizerRequest) throws InvalidException, UserNotFoundException {

//		Optional<Event> event = eventRepository.findByEventId(organizerRequest.getEventId());

		Optional<User> userOptional = userRepository.findById(organizerRequest.getId());
		if (!userOptional.isPresent()) {
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

		if (!isValid(organizer)) {
			throw new InvalidException("Invalid Mobile Number");
		}

		organizer = organizerDao.save(organizer);

		return organizer;
	}

	private boolean isValid(Organizer organizer) {

		if (organizer.getPhoneNumber() != null) {
			String regexPattern = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[6789]\\d{9}$";
			String phoneNumber = organizer.getPhoneNumber();

			return phoneNumber.matches(regexPattern);
		}
		return true;
	}

	@Override
	public String delete(Long organizerId) throws OrganizerNotFoundException {

		Optional<Organizer> organizerOptional = organizerDao.fetchById(organizerId);
		if (!organizerOptional.isPresent()) {
			throw new OrganizerNotFoundException("Organizer with the given Id is not found on Backend");
		}
		Organizer organizer = organizerOptional.get();

		organizer.setDeleted(true);
		organizerDao.save(organizer);
		return "Organizer Deleted Successfully";
	}

	@Override
	public Optional<Organizer> fetchById(Long organizerId) throws OrganizerNotFoundException {

		Optional<Organizer> organizer = organizerDao.fetchById(organizerId);

		if (!organizer.isPresent()) {
			throw new OrganizerNotFoundException("Organizer with requested Id not found!!");
		}
		return organizer;
	}

	@Override
	public Organizer fetchByUsername(String username) throws OrganizerNotFoundException {

		Organizer organizer = organizerDao.fetchByUsername(username);
		if (organizer == null) {
			throw new OrganizerNotFoundException("Organizer with the given username not found");
		}

		return organizer;
	}

	@Override
	public List<Organizer> fetchAll() {
		
		List<Organizer> organizers = organizerDao.fetchAll();
		return organizers;
	}

	@Override
	public List<Event> fetchEventByOrganizerId(Long id) {
		List<Event> events = eventService.fetchAllEvents();

		List<Event> eventByOrganizerList = new ArrayList<>();

		for (Event e : events) {
			if (e.getOrganizer().getOrganizerId() == id) {
				eventByOrganizerList.add(e);
			}
		}

		return eventByOrganizerList;
	}

	@Override
	public Organizer update(OrganizerUpdateRequest organizerUpdateRequest) throws OrganizerNotFoundException, InvalidException {
		
		Optional<Organizer> organizerOptional = fetchById(organizerUpdateRequest.getOrganizerId()); 
		
		if(!organizerOptional.isPresent())
		{
			throw new OrganizerNotFoundException("Organizer with given details not found");
		}
		
		Organizer organizer = organizerOptional.get();
		
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
			organizer.setWebsite(organizerUpdateRequest.getWebsite());
		}
		
		boolean returnValue = isValid(organizer);
		
		if(!returnValue)
		{
			throw new InvalidException("Invalid mobile number");
		}
		
		organizer = organizerDao.save(organizer);
		return organizer;
	}

}
