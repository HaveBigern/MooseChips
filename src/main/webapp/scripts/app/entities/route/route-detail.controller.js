'use strict';

angular.module('analyserApp')
    .controller('RouteDetailController', function ($scope, $rootScope, $stateParams, entity, Route, DataUser, Type, DataClass) {
        $scope.route = entity;
        $scope.load = function (id) {
            Route.get({id: id}, function(result) {
                $scope.route = result;
            });
        };
        var unsubscribe = $rootScope.$on('analyserApp:routeUpdate', function(event, result) {
            $scope.route = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
