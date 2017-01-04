(function() {
	'use strict';

	angular.module('pouchdbMD').controller('ActionEditorCT', ActionEditorCT);

	ActionEditorCT.$inject = [ '$scope', '$log', 'pouchdbSV' ];

	/**
	 * 
	 * @param $scope
	 * @param $log
	 * @returns
	 */
	function ActionEditorCT($scope, $log, pouchdbSV) {
		$log.debug('[ActionEditorCT] Inicializando...');
		var self = this;

		
		init();

		function init() {
		}

	}
})();
