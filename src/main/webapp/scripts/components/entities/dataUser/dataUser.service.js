'use strict';

angular.module('analyserApp')
    .factory('DataUser', function ($resource, DateUtils) {
        return $resource('api/dataUsers/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
