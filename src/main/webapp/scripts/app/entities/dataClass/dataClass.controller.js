'use strict';

angular.module('analyserApp')
    .controller('DataClassController', function ($scope, $state, $modal, DataClass) {
      
        $scope.dataClasss = [];
        $scope.loadAll = function() {
            DataClass.query(function(result) {
               $scope.dataClasss = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.dataClass = {
                distanceTravelled: null,
                timeElapsed: null,
                speedLimit: null,
                speed: null,
                longAccelThrottle: null,
                longAccelBrake: null,
                crashCount: null,
                steeringRate: null,
                collisionTimeSameDir: null,
                distanceSameDir: null,
                collisionOppDir: null,
                distanceOppDir: null,
                longitudeAccel: null,
                lateralAccel: null,
                speedLimitFtSec: null,
                longitudeVelocity: null,
                lateralVelocity: null,
                lateralLanePosition: null,
                vehicleCurvature: null,
                roadCurvature: null,
                steeringCount: null,
                throttleCount: null,
                brakingCount: null,
                gear: null,
                trafficLightState: null,
                yawRate: null,
                operatorMarker: null,
                totalPitchingAngle: null,
                totalRollingAngle: null,
                routeName: null,
                id: null
            };
        };
    });
