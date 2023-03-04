package com.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="new")
public class NewEntity extends BaseEntity {
	@Column
	private String title;
	@Column
	private String thumnail;
	@Column(name="shortdescription")
	private String shortDescription;
	@Column
	private String content;
	@ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
	 
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumnail() {
		return thumnail;
	}
	public void setThumnail(String thumnail) {
		this.thumnail = thumnail;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getContent() {
		return content;
	}
	public CategoryEntity getCategory() {
		return category;
	}
	public void setCategory(CategoryEntity category) {
		this.category = category;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
