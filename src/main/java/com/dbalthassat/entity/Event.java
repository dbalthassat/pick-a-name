package com.dbalthassat.entity;

import com.dbalthassat.entity.listener.SlugEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "event", schema = "public")
@EntityListeners(SlugEntityListener.class)
public class Event implements Serializable, Slugable {
	public interface Update {}
	public interface Create {}
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotBlank(groups = Update.class)
	@Null(groups = Create.class)
    private Long id;

    @NotBlank(groups = { Create.class, Update.class })
    private String name;

    @NotBlank
    private String slug;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.event", cascade = CascadeType.ALL)
	@JsonIgnore
    private Set<EventPerson> eventPersons = new HashSet<>();

	@Transient
	@Valid
	private List<String> persons = new LinkedList<>();

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

	public List<String> getPersons() {
		return persons;
	}

	public void setPersons(List<String> persons) {
		this.persons = persons;
	}

	@Override
	public String itemToSlug() {
		return name;
	}
}
