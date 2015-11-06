/* global malarkey:false, moment:false */

import { config } from './index.config';
import { routerConfig } from './index.route';
import { runBlock } from './index.run';
import { MainController } from './main/main.controller';
import { EventController } from './event/event.controller';
import { GithubContributorService } from '../webapp/components/githubContributor/githubContributor.service';
import { WebDevTecService } from '../webapp/components/webDevTec/webDevTec.service';
import { NavbarDirective } from '../webapp/components/navbar/navbar.directive';
import { MalarkeyDirective } from '../webapp/components/malarkey/malarkey.directive';

angular.module('pickAName', ['ngAnimate', 'ngTouch', 'ngSanitize', 'ngMessages', 'ngAria', 'restangular', 'ui.router', 'ngMaterial', 'toastr'])
  .constant('malarkey', malarkey)
  .constant('moment', moment)
  .config(config)
  .config(routerConfig)
  .run(runBlock)
  .service('githubContributor', GithubContributorService)
  .service('webDevTec', WebDevTecService)
  .controller('MainController', MainController)
  .controller('EventController', EventController)
  .directive('acmeNavbar', NavbarDirective)
  .directive('acmeMalarkey', MalarkeyDirective);
