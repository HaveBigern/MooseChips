angular.module('analyserApp').controller('RouteMultiViewController',
		function($scope, $stateParams, DataClassByRoute) {
		
	$scope.routes = $stateParams.routelist;
	$scope.route = {};
	$scope.init = function() {
		_.each($scope.routes, function(route) {
			if(route.type == 5) {
    			$scope.typeString = "Control";
    		} else if(route.type == 6) {
    			$scope.typeString = "Audio Mute";
    		} else if(route.type == 7) {
    			$scope.typeString = "Warning Lights";
    		} else if(route.type == 8) {
    			$scope.typeString = "Audio Warning";
    		}
			DataClassByRoute.query({routeId : route.routeId}, function(result) {
				$scope.route[route.type].dataClasses = result;
			});
		});
	}
	
});