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
        
        $scope.toggleSelect = function(route) {
        	if(_.contains($scope.multiSelected, route)) {
        		$scope.multiSelected = _.without($scope.multiSelected, _.findWhere($scope.multiSelected, route));
        		route.selected = false;
        	} else {
        		$scope.multiSelected.push(route);
        		route.selected = true;
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
