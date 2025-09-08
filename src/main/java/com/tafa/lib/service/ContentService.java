package com.tafa.lib.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tafa.lib.dto.ContentDetailDto;
import com.tafa.lib.dto.ContentSummaryDto;
import com.tafa.lib.entity.Contents;
import com.tafa.lib.repository.ContentRepository;

@Service
public class ContentService {
	
	
	 @Autowired
	 private ContentRepository contentRepository;
	 
	 
	 public List<ContentSummaryDto> getAllContents() {
	        List<Contents> contents = contentRepository.findAll();
	        return contents.stream()
	                .map(content -> new ContentSummaryDto(
	                        content.getId(),
	                        content.getTitle(),
	                        content.getThumbnailUrl()
	                ))
	                .collect(Collectors.toList());
	    }
	 
	 
	 public Optional<ContentDetailDto> getContentDetailsById(Long id) {
	        Optional<Contents> contentOpt = contentRepository.findById(id);
	        return contentOpt.map(content -> new ContentDetailDto(
	                content.getId(),
	                content.getTitle(),
	                content.getFullImageUrl(),
	                content.getVideoLink(),
	                content.getDescription()
	        ));
	    }
	 
	   public Contents saveContent(String title, String description, String thumbnailUrl, String fullImageUrl, String videoUrl) {
	        Contents content = new Contents();
	        content.setTitle(title);
	        content.setDescription(description);
	        content.setThumbnailUrl(thumbnailUrl);
	        content.setFullImageUrl(fullImageUrl);
	        content.setVideoLink(videoUrl);  
	        return contentRepository.save(content);
	    }
	   
	   public Contents save(Contents content) {
	        return contentRepository.save(content);
	    }

}
