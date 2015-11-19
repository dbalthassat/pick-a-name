export function ValidationEventFirstnameDirective($q, Restangular) {
	'ngInject';

	return {
		restrict: 'A',
		require: 'ngModel',
		link: function(scope, elem, attr, model) {
			var _person;
			var previousValidator;

			model.$asyncValidators['exists-in-event'] = function(modelValue, viewValue) {
				previousValidator = null;
				_person = null;
				var def = $q.defer();
				if(!viewValue) {
					def.resolve();
				} else {
					var restEvent = Restangular.all('events/' + attr.validationEventFirstname + '/persons');
					var promise = restEvent.get(viewValue);
					promise.then(function(event) {
						if(!event) {
							def.reject();
						} else {
							_person = event.person;
							def.resolve();

						}
					}, function() {
						def.reject();
					});
				}
				previousValidator = def.promise;
				return def.promise;
			};

			model.$asyncValidators['already-has-friend'] = function(modelValue) {
				var def = $q.defer();
				if(!previousValidator) {
					def.resolve();
				} else {
					previousValidator.then(function() {
						if(_person && _person.hasFriend || modelValue !== _person.name) {
							def.reject();
						} else {
							def.resolve();
						}
					}, function() {
						def.resolve();
					});
				}
				return def.promise;
			};
		}
	};
}
