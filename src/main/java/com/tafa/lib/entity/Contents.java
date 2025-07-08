package com.tafa.lib.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contents")
public class Contents {
	
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String title;

	    @Column(name = "thumbnail_url")
	    private String thumbnailUrl;

	    @Column(name = "full_image_url")
	    private String fullImageUrl;

	    @Column(name = "video_link")
	    private String videoLink;
	    
	    private String video;

	    @Column(columnDefinition = "TEXT")
	    private String description;

	    public Contents() {}

	    public Contents(String title, String thumbnailUrl, String fullImageUrl, String videoLink, String video, String description) {
	        this.title = title;
	        this.thumbnailUrl = thumbnailUrl;
	        this.fullImageUrl = fullImageUrl;
	        this.videoLink = videoLink;
	        this.video = video;
	        this.description = description;
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getThumbnailUrl() {
			return thumbnailUrl;
		}

		public void setThumbnailUrl(String thumbnailUrl) {
			this.thumbnailUrl = thumbnailUrl;
		}

		public String getFullImageUrl() {
			return fullImageUrl;
		}

		public void setFullImageUrl(String fullImageUrl) {
			this.fullImageUrl = fullImageUrl;
		}

		public String getVideoLink() {
			return videoLink;
		}

		public void setVideoLink(String videoLink) {
			this.videoLink = videoLink;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getVideo() {
			return video;
		}

		public void setVideo(String video) {
			this.video = video;
		}
	
	    
	    

}
