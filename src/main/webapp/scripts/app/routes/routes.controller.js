'use strict';

angular.module('analyserApp').controller('RouteListController', function ($scope, DataUser, Route, AvgRoute) {
    	$scope.dataUsers = [];
    	$scope.controlRoutes = [];
    	$scope.multiSelected = [];
    	$scope.step = {
    		select: true
    	}
        $scope.loadAll = function() {
            DataUser.query(function(result) {
               $scope.dataUsers = result;
            }).$promise.then(function() {
            	Route.query(function(result) {
                    $scope.routes = result;  
                }).$promise.then(function() {
                	setExpType();
                	removeControl();
                	setControlRoutes();
                });
            });
            
        };
        
        $scope.loadAverageRoutes = function() {
        	AvgRoute.query(function(result) {
        		$scope.averageRoutes = result;
        	}).$promise.then(function() {
        		setAverages();
        	});
        }
        
        $scope.nextStep = function(type) {
        	$scope.step.select = false;
        	if(type == "single") {
        		$scope.step.single = true;
        		$scope.step.multiple = false;
        		$scope.loadAll();
        	}
        	if(type == "multiple") {
        		$scope.loadAverageRoutes();
        		$scope.step.single = false;
        		$scope.step.multiple = true;
        	}
        };
        
        $scope.toggleSelect = function(route, select) {
        	console.log($scope.multiSelected);
        	if(_.contains($scope.multiSelected, route)) {
        		$scope.multiSelected = _.without($scope.multiSelected, _.findWhere($scope.multiSelected, route));
        	} else {
        		$scope.multiSelected.push(route);
        	}
        	if(select == 1) {
        		$scope.controlSelect = !$scope.controlSelect;
        	} else if(select == 2) {
        		$scope.audioMuteSelect = !$scope.audioMuteSelect;
        	} else if(select == 3) {
        		$scope.warningLightsSelect = !$scope.warningLightsSelect;
        	} else if(select == 4) {
        		$scope.audioWarningSelect = !$scope.audioWarningSelect;
        	}
        };

        function setAverages() {
        	_.each($scope.averageRoutes, function(route) {
        		if(route.type == 5) {
        			$scope.controlAvg = route;
        		} else if(route.type == 6) {
        			$scope.audioMuteAvg = route;
        		} else if(route.type == 7) {
        			$scope.warningLightsAvg = route;
        		} else if(route.type == 8) {
        			$scope.audioWarningAvg = route;
        		}
        	});
        }
        
        function setControlRoutes() {
        	console.log($scope.controlRoutes);
        	_.each($scope.routes, function(route) {
        		_.each($scope.controlRoutes, function(controlRoute) {
	        		if(controlRoute.dataUser && route.dataUser && controlRoute.dataUser.dataUserId == route.dataUser.dataUserId) {
	        			route.controlRoute = controlRoute;
	        		}
        		});
        	});
        }
        
        function setExpType() {
        	_.each($scope.routes, function(route) {
        		if(route.type == 1) {
        			route.typeString = "Audio Mute";
        		} else if(route.type == 2) {
        			route.typeString = "Warning Lights";
        		} else if(route.type == 3) {
        			route.typeString = "Audio Warning";
        		} else {
        			$scope.controlRoutes.push(route);
        		}
        	});
        }
        
        function removeControl() {
        	$scope.routes = $scope.routes.filter(function(route) { return route.type < 4; });
        }
     
    });
