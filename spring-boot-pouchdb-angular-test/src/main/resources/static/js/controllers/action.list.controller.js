(function() {
	'use strict';

	angular.module('pouchdbMD').controller('ActionListCT', ActionListCT);

	ActionListCT.$inject = [ '$scope', '$log', 'pouchdbSV', '$mdDialog', '$rootScope' ];

	/**
	 * 
	 * @param $scope
	 * @param $log
	 * @returns
	 */
	function ActionListCT($scope, $log, pouchdbSV, $mdDialog, $rootScope) {
		$log.debug('[ActionListCT] Inicializando...');
		var self = this;

		self.actions = [];

		self.pull = pull;

		self.push = push;

		self.newAction = newAction;

		init();

		function init() {
			pouchdbSV.db.allDocs({
				include_docs : true
			}, function(err, response) {
				if (err) {
					return console.log(err);
				}
				$scope.$apply(function() {
					self.actions = response.rows;
				});
			});
			pouchdbSV.db.changes({
				live : true,
				include_docs : true
			}).on('change', function(change) {
				console.log('Alterações realizadas');
			}).on('complete', function(info) {
			})
		}

		function push() {
			pouchdbSV.push();
		}

		function pull() {
			pouchdbSV.pull();
		}

		function newAction() {
			$log.debug('New Action...')
			$mdDialog.show({
				parent : angular.element(document.body),
				templateUrl : "/partial/new.action.dialog.html",
				clickOutsideToClose : false,
				controller : 'ActionEditorCT',
				controllerAs : 'actionEditorCT',
				locals : {
					doc : {
						_id : 'action_' + pouchdbSV.newID()
					}
				}
			});
		}

		function loadAction(doc) {
			$log.debug('New Action...')
			$mdDialog.show({
				parent : angular.element(document.body),
				templateUrl : "/partial/new.action.dialog.html",
				clickOutsideToClose : false,
				controller : 'ActionEditorCT',
				controllerAs : 'actionEditorCT',
				locals : {
					doc : doc
				}
			});
		}

		/*
		 * $rootScope.$on('action.edit.save', function(event, data) {
		 * console.log('action.edit.save'); console.log(data);
		 * self.actions.push(data); })
		 */

	}
})();
