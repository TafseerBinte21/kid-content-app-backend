package com.tafa.lib.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tafa.lib.dto.ContentDetailDto;
import com.tafa.lib.dto.ContentSummaryDto;
import com.tafa.lib.service.ContentService;

@RestController
@RequestMapping("/rest/api/contents")
public class ContentRestController {
	
	 @Autowired
	 private ContentService contentService;
	 
	 
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
