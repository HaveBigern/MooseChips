'use strict';

angular.module('analyserApp').controller('DataUserDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'DataUser', 'DataClass',
        function($scope, $stateParams, $modalInstance, entity, DataUser, DataClass) {

        $scope.dataUser = entity;
        $scope.dataclasss = DataClass.query();
        $scope.load = function(id) {
            DataUser.get({id : id}, function(result) {
                $scope.dataUser = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('analyserApp:dataUserUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.dataUser.id != null) {
                DataUser.update($scope.dataUser, onSaveSuccess, onSaveError);
            } else {
                DataUser.save($scope.dataUser, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
