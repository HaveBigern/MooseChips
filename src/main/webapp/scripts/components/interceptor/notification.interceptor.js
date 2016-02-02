 'use strict';

angular.module('analyserApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-analyserApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-analyserApp-params')});
                }
                return response;
            }
        };
    });
