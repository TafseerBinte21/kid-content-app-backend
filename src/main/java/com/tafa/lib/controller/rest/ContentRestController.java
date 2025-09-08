package com.tafa.lib.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tafa.lib.dto.ContentDetailDto;
import com.tafa.lib.dto.ContentSummaryDto;
import com.tafa.lib.entity.Contents;
import com.tafa.lib.service.ContentService;

@RestController
@RequestMapping("/rest/api/contents")
public class ContentRestController {
	
	 @Autowired
	 private ContentService contentService;
	 
	 @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<Map<String, Object>> uploadContent(
	         @RequestParam("title") String title,
	         @RequestParam("description") String description,
	         @RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail,
	         @RequestParam(value = "fullImage", required = false) MultipartFile fullImage,
	         @RequestParam(value = "video", required = false) MultipartFile video
	 ) {
	     Map<String, Object> response = new HashMap<>();
	     try {
	         String thumbnailUrl = null;
	         String fullImageUrl = null;
	         String videoUrl = null;

	         // 1️⃣ Save thumbnail
	         if (thumbnail != null && !thumbnail.isEmpty()) {
	             String uploadDir = "uploads/thumbnails/";
	             java.io.File dir = new java.io.File(uploadDir);
	             if (!dir.exists()) dir.mkdirs();

	             String filePath = uploadDir + thumbnail.getOriginalFilename();
	             thumbnail.transferTo(new java.io.File(filePath));

	             // Replace with CDN URL later
	             thumbnailUrl = "/thumbnails/" + thumbnail.getOriginalFilename();
	         }

	         // 2️⃣ Save full image
	         if (fullImage != null && !fullImage.isEmpty()) {
	             String uploadDir = "uploads/images/";
	             java.io.File dir = new java.io.File(uploadDir);
	             if (!dir.exists()) dir.mkdirs();

	             String filePath = uploadDir + fullImage.getOriginalFilename();
	             fullImage.transferTo(new java.io.File(filePath));

	             // Replace with CDN URL later
	             fullImageUrl = "/images/" + fullImage.getOriginalFilename();
	         }

	         // 3️⃣ Save video
	         if (video != null && !video.isEmpty()) {
	             String uploadDir = "uploads/videos/";
	             java.io.File dir = new java.io.File(uploadDir);
	             if (!dir.exists()) dir.mkdirs();

	             String filePath = uploadDir + video.getOriginalFilename();
	             video.transferTo(new java.io.File(filePath));

	             // Replace with CDN URL later
	             videoUrl = "/videos/" + video.getOriginalFilename();
	         }

	         // 4️⃣ Save metadata in DB
	         Contents content = new Contents();
	         content.setTitle(title);
	         content.setDescription(description);
	         content.setThumbnailUrl(thumbnailUrl);
	         content.setFullImageUrl(fullImageUrl);
	         content.setVideo(videoUrl);
	         contentService.save(content);

	         response.put("status", "success");
	         response.put("data", content);
	         return ResponseEntity.ok(response);

	     } catch (Exception e) {
	         response.put("status", "error");
	         response.put("message", "Upload failed: " + e.getMessage());
	         return ResponseEntity.status(500).body(response);
	     }
	 }


	 
	 
	 @GetMapping("/all")
	    public ResponseEntity<Map<String, Object>> getAllContents() {
	        List<ContentSummaryDto> contents = contentService.getAllContents();
	        Map<String, Object> response = new HashMap<>();
	        response.put("status", "success");
	        response.put("data", contents);
	        return ResponseEntity.ok(response);
	    }
	 
	 
	  @GetMapping("/details/{id}")
	    public ResponseEntity<Map<String, Object>> getContentDetails(@PathVariable Long id) {
	        Optional<ContentDetailDto> contentOpt = contentService.getContentDetailsById(id);
	        Map<String, Object> response = new HashMap<>();

	        if (contentOpt.isPresent()) {
	            response.put("status", "success");
	            response.put("data", contentOpt.get());
	            return ResponseEntity.ok(response);
	        } else {
	            response.put("status", "error");
	            response.put("message", "Content not found with id: " + id);
	            return ResponseEntity.status(404).body(response);
	        }
	    }

}
