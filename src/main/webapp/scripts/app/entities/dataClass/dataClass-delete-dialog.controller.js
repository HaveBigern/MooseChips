'use strict';

angular.module('analyserApp')
	.controller('DataClassDeleteController', function($scope, $modalInstance, entity, DataClass) {

        $scope.dataClass = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            DataClass.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });