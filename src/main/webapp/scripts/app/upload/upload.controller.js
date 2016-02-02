'use strict';

angular.module('analyserApp').controller('UploadController',
	function($scope, fileUpload, DataUser) {
		$scope.route = {};
		$scope.uploadFile = function() {
			var file = $scope.myFile;
			var uploadUrl = '/upload/file';
			fileUpload.uploadFileToUrl(file, $scope.route, uploadUrl);
		}
		$scope.users = [];
        $scope.loadAll = function() {
            DataUser.query(function(result) {
               $scope.users = result;
            });
        };
        $scope.loadAll();
		$scope.types = [{"label":"Audio Mute", "value":1},
		                {"label":"Warning Lights", "value":2},
		                {"label":"Audio Warning","value":3},
		                {"label":"Control", "value":4}];
	});
