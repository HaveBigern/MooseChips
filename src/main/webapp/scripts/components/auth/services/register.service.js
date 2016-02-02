'use strict';

angular.module('analyserApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


