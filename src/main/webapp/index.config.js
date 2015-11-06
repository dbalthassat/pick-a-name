export function config ($logProvider, toastrConfig, $locationProvider) {
  'ngInject';
  // Enable log
  $logProvider.debugEnabled(true);

  // use the HTML5 history API
  $locationProvider.html5Mode(true);

  // Set options third-party lib
  toastrConfig.allowHtml = true;
  toastrConfig.timeOut = 3000;
  toastrConfig.positionClass = 'toast-top-right';
  toastrConfig.preventDuplicates = true;
  toastrConfig.progressBar = true;
}
