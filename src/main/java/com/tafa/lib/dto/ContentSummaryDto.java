package com.tafa.lib.dto;

public class ContentSummaryDto {

	private Long id;
    private String title;
    private String thumbnailUrl;

    public ContentSummaryDto(Long id, String title, String thumbnailUrl) {
        this.setId(id);
        this.setTitle(title);
        this.setThumbnailUrl(thumbnailUrl);
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
    
    

}
