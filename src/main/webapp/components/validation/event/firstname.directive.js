export function ValidationEventFirstnameDirective($q, $timeout, Restangular) {
	'ngInject';

	return {
		restrict: 'A',
		require: 'ngModel',
		link: function(scope, elem, attr, model) {
			var _event;
			var previousValidator;

			model.$asyncValidators['exists-in-event'] = function(modelValue) {
				previousValidator = null;
				_event = null;
				var def = $q.defer();
				if(!modelValue) {
					def.resolve();
				} else {
					var restEvent = Restangular.all('events/1/persons');
					var promise = restEvent.get(modelValue);
					promise.then(function(event) {
						console.log(event);
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
				if(!_event || !previousValidator) {
					def.resolve();
				} else if(modelValue !== _event.name) {
					def.reject();
				} else {
					previousValidator.then(function() {
						if(_event.hasFriend) {
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
