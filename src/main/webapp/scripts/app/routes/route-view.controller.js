'use strict';

angular.module('analyserApp').controller('RouteViewController',
		function($scope, $stateParams, DataClassByRoute ) {
			$scope.expRoute = $stateParams.exp;
			$scope.controlRoute = $scope.expRoute.controlRoute;
			
			$scope.xType = "distanceTravelled";
			$scope.yType = "speed";
			
			var expData = [];
			var controlData = [];
	        $scope.loadAll = function() {
	        	DataClassByRoute.query({routeId : $scope.expRoute.routeId}, function(result) {
	               expData = result;
	            }).$promise.then(function() {
	            	formatData(expData, "exp");
	            	DataClassByRoute.query({routeId : $scope.controlRoute.routeId}, function(result) {
	 		           controlData = result;
	 		        }).$promise.then(function() {
	 		        	formatData(controlData, "control");
	 		        	$scope.api.update();
	 		        	console.log($scope.data);
	 		        });
	            });
	        	
	        };
	        $scope.loadAll();
	        $scope.sidebar = {
	        		show: false
	        };
			$scope.options = {
				chart : {
					type : 'lineChart',
					height : 450,
					margin : {
						top : 20,
						right : 20,
						bottom : 60,
						left : 65
					},
					x : function(d) { return d.x; },
					y : function(d) { return d.y; },
					average: function(d) { return d.mean; },
					color : d3.scale.category10().range(),
					transitionDuration: 300, 
					useInteractiveGuideline: true,
					xAxis : {
						axisLabel: 'Distance Travelled',
						showMaxMin: false
					},
					yAxis : {
						axisLabel : 'Speed',
						tickFormat: function(d){
	                        return d3.format('.02f')(d);
	                    },
	                    showMaxMin: false
					}
				}
			};
			
			function formatData(array, type) {
				var counter = 0;
				var total = 0;
				var data = {
						values: [],
						key: "",
						mean: 25
				};
				
				var meanData = {
						values: [],
						key: "",
						mean: 25
				};
				_.each(array, function(entry) {
						if(counter % 10 === 0) {
							total += entry.speed;
							data.values.push( {x: entry[$scope.xType], y: entry[$scope.yType]} );
							meanData.values.push( {x: entry[$scope.xType], y: total/data.values.length} );
						}
						counter++;
	        	});
				
				data.mean = total/data.values.length;
				data.values.sort(sortFunction);
				meanData.values.sort(sortFunction);
				
				if(type == "exp") {
					data.key = "Experiment"
					meanData.key = "Experiment Average";
				} else {
					data.key = "Control"
					meanData.key = "Control Average";
				}
				$scope.data.push(data);
				$scope.data.push(meanData);
			}
			
			function sortFunction(a, b) {
			    if (a.x === b.x) {
			        return 0;
			    }
			    else {
			        return (a.x < b.x) ? -1 : 1;
			    }
			}
			
			$scope.data = [];
		});
