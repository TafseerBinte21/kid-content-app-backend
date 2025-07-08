package com.tafa.lib.dto;

public class ContentDetailDto {
	
	private Long id;
    private String title;
    private String fullImageUrl;
    private String videoLink;
    private String description;

    public ContentDetailDto(Long id, String title, String fullImageUrl, String videoLink, String description) {
        this.setId(id);
        this.setTitle(title);
        this.setFullImageUrl(fullImageUrl);
        this.setVideoLink(videoLink);
        this.setDescription(description);
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
    
    

}
