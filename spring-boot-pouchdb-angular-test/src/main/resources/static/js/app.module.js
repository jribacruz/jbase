(function() {
	'use strict';

	startup.$inject = [ '$log' ];

	angular.module("pouchdbMD", [ 'ngResource', 'ngAnimate', 'ngMaterial' ]).run(
			startup);

	function startup($log) {
		$log.debug('[pouchdbMD] Startup App...');

	}

})();