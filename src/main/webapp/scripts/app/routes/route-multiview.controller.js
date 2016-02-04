angular.module('analyserApp').controller('RouteMultiViewController',
		function($scope, $stateParams, DataClassByRoute) {
		
		$scope.routes = $stateParams.routelist;
		$scope.route = {};
		$scope.data = [];
		$scope.init = function() {
			_.each($scope.routes, function(route) {
				if(route.type == 5) {
	    			route.typeString = "Control";
	    		} else if(route.type == 6) {
	    			route.typeString = "Audio Mute";
	    		} else if(route.type == 7) {
	    			route.typeString = "Warning Lights";
	    		} else if(route.type == 8) {
	    			route.typeString = "Audio Warning";
	    		}
				DataClassByRoute.query({routeId : route.routeId}, function(result) {
					route.dataClasses = result;
				}).$promise.then(function() {
					formatData(route.dataClasses, route.typeString);
					$scope.api.update();
				});
				
			});
		}
		
		$scope.xType = "distanceTravelled";
		$scope.yType = "speed";
	
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
		
		$scope.init();
	
		function formatData(array, typeString) {
			var counter = 0;
			var total = 0;
			var data = {
					values: [],
					key: typeString
			};
			
			_.each(array, function(entry) {
				if(counter % 10 === 0) {
					total += entry.speed;
					data.values.push( {x: entry[$scope.xType], y: entry[$scope.yType]} );
				}
				counter++;
        	});
			data.values.sort(sortFunction);
			$scope.data.push(data);
		}
	
		function sortFunction(a, b) {
		    if (a.x === b.x) {
		        return 0;
		    }
		    else {
		        return (a.x < b.x) ? -1 : 1;
		    }
		}
});