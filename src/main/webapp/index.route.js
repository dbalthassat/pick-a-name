export function routerConfig ($stateProvider, $urlRouterProvider) {
  'ngInject';
  $stateProvider
    .state('home', {
      url: '/',
      templateUrl: 'webapp/main/main.html',
      controller: 'MainController',
      controllerAs: 'main'
    });

  $urlRouterProvider.otherwise('/');
}
