package com.dbalthassat.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person", schema = "public")
public class Person implements Serializable {
    @Id
    private Long id;

    @NotBlank
    private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.person", cascade = CascadeType.ALL)
	@JsonManagedReference("person")
	private Set<EventPerson> eventPersons = new HashSet<>();

    public Person() {
    }

    public Person(long id, String name) {
        this.id = id;
        this.name = name;
    }

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

	public Set<EventPerson> getEventPersons() {
		return eventPersons;
	}

	public void setEventPersons(Set<EventPerson> eventPersons) {
		this.eventPersons = eventPersons;
	}
}
