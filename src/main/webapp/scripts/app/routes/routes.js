'use strict';

angular.module('analyserApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('routes', {
                parent: 'site',
                url: '/routes',
                data: {
                    authorities: [],
                    pageTitle: 'View Routes'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/routes/routes.html',
                        controller: 'RouteListController'
                    }
                },
                resolve: {
                }
            })
            .state('routes.view', {
                parent: 'routes',
                url: '/routes/view',
                data: {
                    authorities: [],
                },
                params: { 
                    exp: null
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/routes/route-view.html',
                        controller: 'RouteViewController'
                    }
                }
            })
            .state('routes.multiview', {
                parent: 'routes',
                url: '/routes/multiview',
                data: {
                    authorities: [],
                },
                params: { 
                    routelist: null
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/routes/route-multiview.html',
                        controller: 'RouteMultiViewController'
                    }
                }
            });
    });
