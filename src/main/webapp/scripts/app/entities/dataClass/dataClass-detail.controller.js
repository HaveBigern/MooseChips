'use strict';

angular.module('analyserApp')
    .controller('DataClassDetailController', function ($scope, $rootScope, $stateParams, entity, DataClass) {
        $scope.dataClass = entity;
        $scope.load = function (id) {
            DataClass.get({id: id}, function(result) {
                $scope.dataClass = result;
            });
        };
        var unsubscribe = $rootScope.$on('analyserApp:dataClassUpdate', function(event, result) {
            $scope.dataClass = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
