angular.module('analyserApp').controller('RouteMultiViewController',
		function($scope, $stateParams, DataClassByRoute) {
		
		$scope.routes = $stateParams.routelist;
		$scope.route = {};
		$scope.data = [];
		$scope.init = function() {
			_.each($scope.routes, function(route) {
				DataClassByRoute.query({routeId : route.routeId}, function(result) {
					route.dataClasses = result;
				}).$promise.then(function() {
					formatData(route.dataClasses, route.type.name);
					if(!_.contains($scope.data, barData)) {
						$scope.data.push(barData);
					}
					$scope.api.update();
				});
			});
		};
	
		$scope.xType = "distanceTravelled";
		$scope.yType = "speed";
		$scope.xLabel = "Distance Travelled";
		$scope.yLabel = "Speed";
		
		$scope.changeData = function() {
			$scope.api.update();
		};
		
		$scope.options = {
			chart : {
				type : 'lineWithFocusChart',
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
					axisLabel: $scope.xLabel,
					tickFormat: function(d){
                        return d3.format('.02f')(d);
                    },
                    showMaxMin: false
				},
				yAxis : {
					axisLabel : $scope.yLabel,
					tickFormat: function(d){
                        return d3.format('.02f')(d);
                    },
                    showMaxMin: false
				}
			}
		};
		
		$scope.init();
	
		$scope.print = function() {
			console.log($scope.xIsCollapsed);
		};
		
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
							id: entry.id,
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
		
		var barData = {
				key: "Danger Zones",
				values: [{distanceTravelled: 108.0, speed:0}, 
				         {distanceTravelled: 5539.0, speed:0}, {distanceTravelled: 5540.0, speed:35},
				         {distanceTravelled: 6450.0, speed:35}, {distanceTravelled: 6451.0, speed:0}, 
				         {distanceTravelled: 15949.0, speed:0}, {distanceTravelled: 15950.0, speed:35},
				         {distanceTravelled: 16950.0, speed:35}, {distanceTravelled: 16951.0, speed:0},
				         {distanceTravelled: 24299.0, speed:0}, {distanceTravelled: 24300.0, speed:35},
				         {distanceTravelled: 25300.0, speed:35}, {distanceTravelled: 25301.0, speed:0},
				         {distanceTravelled: 33949.0, speed:0}, {distanceTravelled: 33950.0, speed:35},
				         {distanceTravelled: 34950.0, speed:35}, {distanceTravelled: 34951.0, speed:0},
				         {distanceTravelled: 41539.0, speed:0}, {distanceTravelled: 41540.0, speed:35},
				         {distanceTravelled: 42540.0, speed:35}, {distanceTravelled: 42541.0, speed:0},
				         {distanceTravelled: 49739.0, speed:0}, {distanceTravelled: 49740.0, speed:35},
				         {distanceTravelled: 50740.0, speed:35}, {distanceTravelled: 50741.0, speed:0}, 
				         {distanceTravelled: 55299.0, speed:0}, {distanceTravelled: 55300.0, speed:35},
				         {distanceTravelled: 56300.0, speed:35}, {distanceTravelled: 56301.0, speed:0}, 
				         {distanceTravelled: 62739.0, speed:0}, {distanceTravelled: 62740.0, speed:35},
				         {distanceTravelled: 63740.0, speed:35}, {distanceTravelled: 63741.0, speed:0}]
		};
		
		function sortFunction(a, b) {
		    if (a.distanceTravelled === b.distanceTravelled) {
		        return 0;
		    }
		    else {
		        return (a.distanceTravelled < b.distanceTravelled) ? -1 : 1;
		    }
		}
});