export class EventController {
	constructor ($stateParams, Restangular, $state) {
		'ngInject';
		var mv = this;
		var restEvent = Restangular.all('event');
		var promise = restEvent.get($stateParams.id);
		promise.then(function(event) {
			if($stateParams.slug !== event.slug) {
				$state.go('event', {
					id: event.id,
					slug: event.slug
				})
			} else {
				mv.eventName = event.name;
			}
		}, function(result) {
			console.log(result);
			$state.go('home');
		});
	}

	register() {

	}
}
