package com.project.isc.iscdbserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.isc.iscdbserver.entity.Announcement;
import com.project.isc.iscdbserver.repository.AnnouncementRepository;

@Service
public class ManagementService {
	@Autowired
	private AnnouncementRepository announcementRepository;
	
	public Announcement getNewAnnouncement() {
		Announcement annoment = announcementRepository.findFirstByOrderByCreateTimeDesc();
//		return annoment;
//		Announcement announcement = new Announcement();
//		announcement.setTitle("标题");
//		announcement.setContent("内容");
//		announcement.setCreateTime(new Date());
//		announcement = announcementRepository.save(announcement);
		return annoment;
	}
}
