export function routerConfig ($stateProvider, $urlRouterProvider) {
  'ngInject';
  $stateProvider
    .state('home', {
      url: '/',
      templateUrl: 'webapp/main/main.html',
      controller: 'MainController',
      controllerAs: 'main'
    })
    .state('event', {
      url: '/event/{id:int}/{slug:(?:[a-z]{1}[a-z-]*)?}',
      templateUrl: 'webapp/event/event.html',
      controller: 'EventController',
      controllerAs: 'event'
    });

  $urlRouterProvider.otherwise('/');
}
