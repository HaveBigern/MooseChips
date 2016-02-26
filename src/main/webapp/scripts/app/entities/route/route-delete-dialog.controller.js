'use strict';

angular.module('analyserApp')
	.controller('RouteDeleteController', function($scope, $modalInstance, entity, Route) {

        $scope.route = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Route.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });