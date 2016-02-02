'use strict';

angular.module('analyserApp')
	.controller('DataUserDeleteController', function($scope, $modalInstance, entity, DataUser) {

        $scope.dataUser = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            DataUser.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });