export function routerConfig ($stateProvider, $urlRouterProvider) {
	'ngInject';
	$stateProvider
		.state('events', {
			url: '/events/{id:int}/{slug:(?:[a-z]{1}[a-z-]*)?}',
			templateUrl: 'event/event.html',
			controller: 'EventController',
			controllerAs: 'event'
		})
		.state('eventsWithoutSlug', {
			url: '/events/{id:int}',
			templateUrl: 'event/event.html',
			controller: 'EventController',
			controllerAs: 'event'
		});
	$urlRouterProvider.otherwise('/');
}
