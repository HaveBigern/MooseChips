'use strict';

angular.module('analyserApp').controller('RouteListController', function ($scope, $state, DataUser, Route, AvgRoute, spinnerService, Type) {
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
        	spinnerService.show('averageSpinner');
        	Type.query(function(result) {
        		$scope.averageRoutes = result;
        	}).$promise.then(function() {
        		spinnerService.hide('averageSpinner');
        	});
        };
        
        $scope.nextStep = function(type) {
        	$scope.step.select = false;
        	if(type == "single") {
        		$scope.step.single = true;
        		$scope.step.multiple = false;
        		$scope.loadAll();
        	}
        	if(type == "multiple") {
        		$scope.step.single = false;
        		$scope.step.multiple = true;
        	}
        };
        
        $scope.toggleSelect = function(type) {
        	if(_.contains($scope.multiSelected, type)) {
        		$scope.multiSelected = _.without($scope.multiSelected, _.findWhere($scope.multiSelected, type));
        		type.selected = false;
        	} else {
        		$scope.multiSelected.push(type);
        		type.selected = true;
        	}
        };
        
        function setControlRoutes() {
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
