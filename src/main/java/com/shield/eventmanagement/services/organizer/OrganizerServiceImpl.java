package com.shield.eventmanagement.services.organizer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.eventmanagement.dao.organizer.OrganizerDao;
import com.shield.eventmanagement.entities.Organizer;
import com.shield.eventmanagement.exceptions.organizer.OrganizerNotFoundException;

@Service
public class OrganizerServiceImpl implements OrganizerService {

	@Autowired
	OrganizerDao organizerDao;

	@Override
	public String create(Organizer organizer) {

		if(!isValid(organizer))
		{
			return "Invalid fields";
		}
		organizer = organizerDao.save(organizer);

		if (organizer == null) {
			return "Insufficient data";
		}

		return "Organizer create Successfully";
	}

	private boolean isValid(Organizer organizer) {
		
		if(organizer.getPhoneNumber()!=null)
		{
			String regexPattern = "^(\\+91|0)?[6789]\\d{9}$";
			String phoneNumber = organizer.getPhoneNumber();
			
			if(!phoneNumber.matches(regexPattern))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public String update(Organizer organizer) {
		// TODO Auto-generated method stub
		Organizer currentOrganizer = organizerDao.fetchById(organizer.getOrganizerId());

		// if email is updated, then it will not be an empty field
		if (organizer.getEmailId() != null) {
			currentOrganizer.setEmailId(organizer.getEmailId());
		}

		if (organizer.getOrganizerName() != null) {
			currentOrganizer.setOrganizerName(organizer.getOrganizerName());
		}

		if (organizer.getPhoneNumber() != null) {
			currentOrganizer.setPhoneNumber(organizer.getPhoneNumber());
		}

		if (organizer.getPresentSince() != null) {
			currentOrganizer.setPresentSince(organizer.getPresentSince());
		}

		if (organizer.getRating() != null) {
			currentOrganizer.setRating(organizer.getRating());
		}

		if (organizer.getWebsite() != null) {
			currentOrganizer.setWebsite(organizer.getWebsite());
		}

		if (organizer.getEvents() != null && organizer.getEvents().size() > 0) {
			currentOrganizer.setEvents(organizer.getEvents());
		}

		// TODO: FIX THIS
//		if (organizer.getLocations(:) != null && organizer.getLocations().size() > 0) {
//			currentOrganizer.setLocations(organizer.getLocations());
//		}

		// we have updated the required fields now we have to push the data
		organizer = organizerDao.save(currentOrganizer);

		return "Successfully updated";
	}

	@Override
	public String delete(Long organizerId) throws OrganizerNotFoundException {
		
		Organizer organizer = organizerDao.fetchById(organizerId);
		if(organizer==null)
		{
			throw new OrganizerNotFoundException("Organizer with the given Id is not found on Backend");
		}
		organizer.setDeleted(true);
	    organizer = organizerDao.save(organizer);
		return "Organizer Deleted Successfully";
	}

	@Override
	public Organizer fetchById(Long organizerId) throws OrganizerNotFoundException {
		Organizer organizer = organizerDao.fetchById(organizerId);
		if(organizer==null)
		{
			throw new OrganizerNotFoundException();
		}
		return organizer;
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
	public List<Organizer> fetchByIsDeleted(boolean isDeleted) {
		List<Organizer> organizerList = organizerDao.fetchByIsDeleted(isDeleted);
		return organizerList;
	}

	@Override
	public List<Organizer> fetchAll() {
		List<Organizer> organizerList = organizerDao.fetchAll();
		return organizerList;
	}

}
