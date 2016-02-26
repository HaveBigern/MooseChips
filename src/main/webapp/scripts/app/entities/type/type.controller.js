'use strict';

angular.module('analyserApp')
    .controller('TypeController', function ($scope, $state, $modal, Type) {
      
        $scope.types = [];
        $scope.loadAll = function() {
            Type.query(function(result) {
               $scope.types = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.type = {
                name: null,
                id: null
            };
        };
    });
