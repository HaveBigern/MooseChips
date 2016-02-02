'use strict';

angular.module('analyserApp').controller('DataClassDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'DataClass',
        function($scope, $stateParams, $modalInstance, entity, DataClass) {

        $scope.dataClass = entity;
        $scope.load = function(id) {
            DataClass.get({id : id}, function(result) {
                $scope.dataClass = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('analyserApp:dataClassUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.dataClass.id != null) {
                DataClass.update($scope.dataClass, onSaveSuccess, onSaveError);
            } else {
                DataClass.save($scope.dataClass, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
