export function ExistsInEventDirective(Restangular) {
	'ngInject';

	return {
		restrict: 'A',
		require: 'ngModel',
		link: function (scope, element, attr, ctrl) {
			function validate() {
				var id = attr['existsInEvent'];
				console.log(id);
			}

			return {
				link: validate
			}
		}
	};
}
