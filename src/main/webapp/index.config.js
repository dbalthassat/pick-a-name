export function config ($logProvider, $locationProvider, RestangularProvider) {
  'ngInject';
  // Enable log
  $logProvider.debugEnabled(true);

  // use the HTML5 history API
  $locationProvider.html5Mode(true);

  // RestAngular
  RestangularProvider.setBaseUrl('./api/');
}
