(function() {
	'use strict';

	angular.module('pouchdbMD').controller('ActionListCT', ActionListCT);

	ActionListCT.$inject = [ '$scope', '$log', 'pouchdbSV'];

	/**
	 * 
	 * @param $scope
	 * @param $log
	 * @returns
	 */
	function ActionListCT($scope, $log, pouchdbSV) {
		$log.debug('[ActionListCT] Inicializando...');
		var self = this;

		var actions = {};

		init();

		function init() {
			pouchdbSV.db.allDocs({
				 include_docs: true
			}, function(err, response) {
				if(err) {
					return console.log(err);
				}
				self.actions = response.rows;
			});
		}

	}
})();
