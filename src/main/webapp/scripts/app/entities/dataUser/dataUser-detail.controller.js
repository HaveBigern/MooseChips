'use strict';

angular.module('analyserApp')
    .controller('DataUserDetailController', function ($scope, $rootScope, $stateParams, entity, DataUser, DataClass) {
        $scope.dataUser = entity;
        $scope.load = function (id) {
            DataUser.get({id: id}, function(result) {
                $scope.dataUser = result;
            });
        };
        var unsubscribe = $rootScope.$on('analyserApp:dataUserUpdate', function(event, result) {
            $scope.dataUser = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
