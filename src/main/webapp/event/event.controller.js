export class EventController {
	// TODO create a service instead of calling Restangular here.
	constructor ($stateParams, Restangular, $state) {
		'ngInject';
		var mv = this;
		this.id = $stateParams.id;
		this.Restangular = Restangular;
		var restEvent = Restangular.all('event');
		var promise = restEvent.get(this.id);

		promise.then(function(event) {
			if($stateParams.slug !== event.slug) {
				$state.go('event', {
					id: event.id,
					slug: event.slug
				})
			} else {
				mv.eventName = event.name;
			}
		}, function() {
			$state.go('home');
		});
	}

	register() {
		var restEventPerson = this.Restangular.all('eventPerson/' + this.id);
		var promise = restEventPerson.get(this.firstname);
		promise.then(function(eventPerson) {
			if(!eventPerson) {
				console.log('!todo');
			} else {
				console.log('todo');
			}
		});
	}
}
