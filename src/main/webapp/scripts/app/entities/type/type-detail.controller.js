'use strict';

angular.module('analyserApp')
    .controller('TypeDetailController', function ($scope, $rootScope, $stateParams, entity, Type, Route) {
        $scope.type = entity;
        $scope.load = function (id) {
            Type.get({id: id}, function(result) {
                $scope.type = result;
            });
        };
        var unsubscribe = $rootScope.$on('analyserApp:typeUpdate', function(event, result) {
            $scope.type = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
