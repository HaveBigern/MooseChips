'use strict';

angular.module('analyserApp')
    .factory('DataClassByRoute', function ($resource) {
        return $resource('api/dataClass/routes/:routeId', {}, {
            'query': { method: 'GET', isArray: true}
        });
    })
    .factory('DataClassByType', function ($resource) {
        return $resource('api/dataClass/type/:type', {}, {
            'query': { method: 'GET', isArray: true}
        });
    });