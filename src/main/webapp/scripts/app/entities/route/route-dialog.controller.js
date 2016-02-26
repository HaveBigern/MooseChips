'use strict';

angular.module('analyserApp').controller('RouteDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Route', 'DataUser', 'Type', 'DataClass',
        function($scope, $stateParams, $modalInstance, entity, Route, DataUser, Type, DataClass) {

        $scope.route = entity;
        $scope.datausers = DataUser.query();
        $scope.types = Type.query();
        $scope.dataclasss = DataClass.query();
        $scope.load = function(id) {
            Route.get({id : id}, function(result) {
                $scope.route = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('analyserApp:routeUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.route.id != null) {
                Route.update($scope.route, onSaveSuccess, onSaveError);
            } else {
                Route.save($scope.route, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
