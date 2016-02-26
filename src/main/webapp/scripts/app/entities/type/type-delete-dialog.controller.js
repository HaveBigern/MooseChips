'use strict';

angular.module('analyserApp')
	.controller('TypeDeleteController', function($scope, $modalInstance, entity, Type) {

        $scope.type = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Type.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });