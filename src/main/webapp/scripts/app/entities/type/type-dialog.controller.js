'use strict';

angular.module('analyserApp').controller('TypeDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Type', 'Route',
        function($scope, $stateParams, $modalInstance, entity, Type, Route) {

        $scope.type = entity;
        $scope.routes = Route.query();
        $scope.load = function(id) {
            Type.get({id : id}, function(result) {
                $scope.type = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('analyserApp:typeUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.type.id != null) {
                Type.update($scope.type, onSaveSuccess, onSaveError);
            } else {
                Type.save($scope.type, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
