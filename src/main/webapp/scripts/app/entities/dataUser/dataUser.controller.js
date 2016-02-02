'use strict';

angular.module('analyserApp')
    .controller('DataUserController', function ($scope, $state, $modal, DataUser) {
      
        $scope.dataUsers = [];
        $scope.loadAll = function() {
            DataUser.query(function(result) {
               $scope.dataUsers = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.dataUser = {
                participantNum: null,
                id: null
            };
        };
    });
