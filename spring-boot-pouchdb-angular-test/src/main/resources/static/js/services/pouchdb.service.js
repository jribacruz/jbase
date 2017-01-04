(function() {
	'use strict';

	angular.module("pouchdbMD").factory('pouchdbSV', pouchdbSV);

	pouchdbSV.$inject = [ '$log', '$http', '$mdToast' ];

	/**
	 * 
	 * @param $log
	 * @param $http
	 * @param $location
	 * @returns
	 */
	function pouchdbSV($log, $http, $mdToast) {
		$log.debug('[pouchdbSV] Inicializando... ');

		var db = new PouchDB('test-skdevdb');
		
		var options = {
			remotedb: 'http://10.13.29.32:5984/test-skdevdb'	
		}

		var service = {
			db : db,
			pull : pull,
			push : push
		};

		function pull() {
			PouchDB.replicate(db, options.remotedb)
				.on('complete', function(info) {
					var toast = $mdToast.simple()
				      				textContent('Dados baixados com sucesso.');
					$mdToast.show(toast);
				})
				.on('error', function(info) {
					var toast = $mdToast.simple()
				      				textContent('Erro ao baixar dados.');
					$mdToast.show(toast);
				});
		}

		function push() {

		}

		return service;

	}

})();