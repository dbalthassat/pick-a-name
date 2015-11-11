package com.dbalthassat.dto;

public class PersonDTO {
	private Long id;
	private String name;
	private Boolean hasFriend;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getHasFriend() {
		return hasFriend;
	}

	public void setHasFriend(Boolean hasFriend) {
		this.hasFriend = hasFriend;
	}
}
