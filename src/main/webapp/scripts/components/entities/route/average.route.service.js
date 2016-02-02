'use strict';

angular.module('analyserApp')
    .factory('AvgRoute', function ($resource) {
        return $resource('api/routes/averages', {}, {
            'query': { method: 'GET', isArray: true}
        });
    });