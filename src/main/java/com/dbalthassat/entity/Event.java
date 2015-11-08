package com.dbalthassat.entity;

import com.dbalthassat.entity.listener.SlugEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "event", schema = "public")
@EntityListeners(SlugEntityListener.class)
public class Event implements Serializable, Slugable {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String slug;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.event", cascade = CascadeType.ALL)
	@JsonIgnore
    private Set<EventPerson> eventPersons = new HashSet<>();

    public Event() {
    }

    public Event(long id) {
        this.id = id;
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

	public String getSlug() {
      return slug;
    }

	public void setSlug(String slug) {
      this.slug = slug;
    }

	@Override
	public String itemToSlug() {
		return name;
	}
}
