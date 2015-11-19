export class EventController {
	// TODO create a service instead of calling Restangular here.
	constructor ($state, $stateParams, Restangular, $timeout) {
		'ngInject';
		var vm = this;
		vm.$timeout = $timeout;
		vm.id = $stateParams.id;
		vm.Restangular = Restangular;
		vm.state = 'form';
		var restEvent = Restangular.all('events');
		var promise = restEvent.get(vm.id);

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

	register() {
		var vm = this;
		vm.state = 'wait';
		vm.$timeout(function() {
			vm.Restangular.all('events/'+vm.id+'/persons/'+vm.firstname).customPUT().then(function(data) {
				vm.friend = data.friend;
				vm.state = 'done';
			}, function() {
				// do nothing, errors are handle by validators
				console.log('error');
			});
		}, 2000);
	}
}
