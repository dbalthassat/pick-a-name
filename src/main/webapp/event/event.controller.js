export class EventController {
	// TODO create a service instead of calling Restangular here.
	constructor ($state, $stateParams, Restangular) {
		'ngInject';
		var vm = this;
		this.id = $stateParams.id;
		this.Restangular = Restangular;
		var restEvent = Restangular.all('events');
		var promise = restEvent.get(this.id);

		promise.then(function(event) {
			if($stateParams.slug !== event.slug) {
				$state.go('events', {
					id: event.id,
					slug: event.slug
				})
			} else {
				vm.eventName = event.name;
			}
		}, function() {
			$state.go('home');
		});
	}

	register(form) {
		console.log('register');
	}
}
