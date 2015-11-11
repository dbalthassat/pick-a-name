/* global malarkey:false, moment:false */

import { config } from './index.config';
import { routerConfig } from './index.route';
import { EventController } from './event/event.controller';
import { ValidationEventFirstnameDirective } from './components/validation/event/firstname.directive';

angular.module('pickAName', ['ngMessages', 'restangular', 'ui.router'])
	.config(config)
	.config(routerConfig)
	.directive('validationEventFirstname', ValidationEventFirstnameDirective)
	.controller('EventController', EventController);
