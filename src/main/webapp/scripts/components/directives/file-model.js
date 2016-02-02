angular.module('analyserApp')
.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}])
.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, route, uploadUrl){
        var fd = new FormData();
        fd.append('route', JSON.stringify(route));
        var oBlob = new Blob([file], { type: "application/vnd.ms-excel"});
        fd.append('file', oBlob, 'temp.xls');
        $http.post(uploadUrl, fd, {
            withCredentials : false,
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(){
            console.log("success 1");
        })
        .error(function(){
            console.log("error");
        });
    }
}]);