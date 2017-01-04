(function() {
	'use strict';

	angular.module('pouchdbMD').controller('ActionEditorCT', ActionEditorCT);

	ActionEditorCT.$inject = [ '$scope', '$log', 'pouchdbSV', '$mdDialog', 'doc', '$rootScope' ];

	/**
	 * 
	 * @param $scope
	 * @param $log
	 * @returns
	 */
	function ActionEditorCT($scope, $log, pouchdbSV, $mdDialog, doc, $rootScope) {
		$log.debug('[ActionEditorCT] Inicializando...');
		var self = this;

		self.save = save;
		
		self.doc = doc;
		
		init();

		function init() {
		}
		
		function save() {
			$log.debug('[ActionEditorCT] save')
			pouchdbSV.db.put(doc, function(err, response) {
				if(err) {
					return console.log(err);
				}
				$mdDialog.hide();
				$rootScope.$emit('action.edit.save', self.doc);
			})
		}

	}
})();
