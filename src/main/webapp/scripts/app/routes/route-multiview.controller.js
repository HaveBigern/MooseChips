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
		};
	
		$scope.xType = "timeElapsed";
		$scope.yType = "speed";
		
		$scope.changeData = function() {
			$scope.api.update();
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
				x : function(d) { return d[$scope.xType]; },
				y : function(d) { return d[$scope.yType]; },
				color : d3.scale.category10().range(),
				transitionDuration: 300, 
				useInteractiveGuideline: true,
				xAxis : {
					axisLabel: 'Distance Travelled',
					tickFormat: function(d){
                        return d3.format('.02f')(d);
                    },
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
			var data = {
					values: [],
					key: typeString
			};
			
			_.each(array, function(entry) {
				if(counter % 10 === 0) {
					data.values.push( 
						{
							distanceTravelled: entry.distanceTravelled,
							timeElapsed: entry.timeElapsed,
							speedLimit: entry.speedLimit,
							speed: entry.speed,
							longAccelThrottle: entry.longAccelThrottle,
							longAccelBrake: entry.longAccelBrake,
							crashCount: entry.crashCount,
							steeringRate: entry.steeringRate,
							collisionTimeSameDir: entry.collisionTimeSameDir,
							distanceSameDir: entry.distanceSameDir,
							collisionOppDir: entry.collisionOppDir,
							distanceOppDir: entry.distanceOppDir,
							longitudeAccel: entry.longitudeAccel,
							lateralAccel: entry.lateralAccel,
							speedLimitFtSec: entry.speedLimitFtSec,
							longitudeVelocity: entry.longitudeVelocity,
							lateralVelocity: entry.lateralVelocity,
							lateralLanePosition: entry.lateralLanePosition,
							vehicleCurvature: entry.vehicleCurvature,
							roadCurvature: entry.roadCurvature,
							steeringCount: entry.steeringCount,
							throttleCount: entry.throttleCount,
							brakingCount: entry.brakingCount,
							gear: entry.gear,
							trafficLightState: entry.trafficLightState,
							yawRate: entry.yawRate,
							operatorMarker: entry.operatorMarker,
							totalPitchingAngle: entry.totalPitchingAngle,
							totalRollingAngle: entry.totalRollingAngle
						});
				}
				counter++;
        	});
			data.values.sort(sortFunction);
			$scope.data.push(data);
		}
	
		function sortFunction(a, b) {
		    if (a[$scope.xType] === b[$scope.xType]) {
		        return 0;
		    }
		    else {
		        return (a[$scope.xType] < b[$scope.xType]) ? -1 : 1;
		    }
		}
});