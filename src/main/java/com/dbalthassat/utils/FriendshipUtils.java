package com.dbalthassat.utils;

import com.dbalthassat.entity.EventPerson;
import com.dbalthassat.entity.Person;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FriendshipUtils {
	public static Person findFriend(EventPerson eventPerson, Collection<EventPerson> eventPersons) {
		Objects.requireNonNull(eventPerson);
		Objects.requireNonNull(eventPersons);
		if(eventPersons.size() < 2) {
			throw new IllegalStateException();
		}
		List<Person> attributedPeople = eventPersons.stream()
				.map(EventPerson::getFriend)
				.filter(e -> e != null)
				.collect(Collectors.toList());
		/* On peut sélectionner un ami parmi tout le monde sauf :
			- Ceux qui ont déjà été sélectionnés par quelqu'un d'autre (attributedPeople)
			- Soi-même
		 */
		List<EventPerson> availableEventPerson = eventPersons.stream()
				.filter(ep -> !attributedPeople.contains(ep.getPerson()))
				.filter(ep -> !eventPerson.getPerson().equals(ep.getPerson()))
				.collect(Collectors.toList());
		/* On cherche d'abord parmi ceux qui n'ont sélectionné personne */
		List<EventPerson> futureFriends = availableEventPerson.stream()
				.filter(ep -> ep.getFriend() == null)
				.collect(Collectors.toList());
		if(futureFriends.isEmpty()) {
			/* Si on n'a pas trouvé, on cherche parmi :
				- Les personnes qui ne nous ont pas nous-mêmes sélectionnés
				- Les personnes qui ont déjà sélectionné quelqu'un
			 */
			futureFriends = availableEventPerson.stream()
					.filter(ep -> !eventPerson.getPerson().equals(ep.getFriend()))
					.filter(ep -> ep.getFriend() != null)
					.collect(Collectors.toList());
		}
		if(futureFriends.isEmpty()) {
			/* Si toujours personne, on récupère la personne qui nous a sélectionné en tant qu'ami (pas d'autre solution) */
			futureFriends = eventPersons.stream()
					.filter(ep -> eventPerson.getPerson().equals(ep.getFriend()))
					.collect(Collectors.toList());
		}
		Collections.shuffle(futureFriends);
		Person person = futureFriends.get(0).getPerson();
		if(attributedPeople.contains(person)) {
			throw new IllegalStateException();
		}
		eventPersons.stream()
				.filter(ep -> ep.getPerson().equals(eventPerson.getPerson()))
				.findAny().get().setFriend(person);
		return person;
	}
}
