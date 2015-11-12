export function ValidationEventFirstnameDirective($q, $timeout, Restangular) {
	'ngInject';

	return {
		restrict: 'A',
		require: 'ngModel',
		link: function(scope, elem, attr, model) {
			var _event;
			var previousValidator;

			model.$asyncValidators['exists-in-event'] = function(modelValue, viewValue) {
				previousValidator = null;
				_event = null;
				var def = $q.defer();
				if(!viewValue) {
					def.resolve();
				} else {
					var restEvent = Restangular.all('events/1/persons');
					var promise = restEvent.get(viewValue);
					promise.then(function(event) {
						if(!event) {
							def.reject();
						} else {
							_event = event;
							def.resolve();
						}
					}, function() {
						def.reject();
					});
				}
				previousValidator = def.promise;
				return previousValidator;
			};

			model.$asyncValidators['already-has-friend'] = function(modelValue) {
				var def = $q.defer();
				if(!previousValidator) {
					def.resolve();
				} else {
					previousValidator.then(function() {
						if(_event && _event.hasFriend || modelValue !== _event.name) {
							def.reject();
						} else {
							def.resolve();
						}
					});
				}

				return def.promise;
			};
		}
	};
}
