'use strict';

angular.module('analyserApp')
    .controller('RouteController', function ($scope, $state, $modal, Route) {
      
        $scope.routes = [];
        $scope.loadAll = function() {
            Route.query(function(result) {
               $scope.routes = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.route = {
                name: null,
                id: null
            };
        };
    });
