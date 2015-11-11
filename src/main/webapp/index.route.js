export function routerConfig ($stateProvider, $urlRouterProvider) {
	'ngInject';
	$stateProvider
		.state('events', {
			url: '/events/{id:int}/{slug:(?:[a-z]{1}[a-z-]*)?}',
			templateUrl: 'webapp/event/event.html',
			controller: 'EventController',
			controllerAs: 'event'
		})
		.state('eventsWithoutSlug', {
			url: '/events/{id:int}',
			templateUrl: 'webapp/event/event.html',
			controller: 'EventController',
			controllerAs: 'event'
		});
	$urlRouterProvider.otherwise('/');
}
