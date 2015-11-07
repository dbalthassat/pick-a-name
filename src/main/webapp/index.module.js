/* global malarkey:false, moment:false */

import { config } from './index.config';
import { routerConfig } from './index.route';
import { MainController } from './main/main.controller';
import { EventController } from './event/event.controller';
import { ExistsInEventDirective } from './components/event/event.directive';

angular.module('pickAName', ['ngAnimate', 'ngTouch', 'ngSanitize', 'ngMessages', 'ngAria', 'restangular', 'ui.router', 'ngMaterial', 'toastr'])
  .constant('malarkey', malarkey)
  .constant('moment', moment)
  .config(config)
  .config(routerConfig)
  .controller('MainController', MainController)
  .controller('EventController', EventController)
  .directive('existsInEvent', ExistsInEventDirective);
