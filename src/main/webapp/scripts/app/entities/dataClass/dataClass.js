'use strict';

angular.module('analyserApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('dataClass', {
                parent: 'entity',
                url: '/dataClass',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DataClasss'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/dataClass/dataClasss.html',
                        controller: 'DataClassController'
                    }
                },
                resolve: {
                }
            })
            .state('dataClass.detail', {
                parent: 'entity',
                url: '/dataClass/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DataClass'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/dataClass/dataClass-detail.html',
                        controller: 'DataClassDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'DataClass', function($stateParams, DataClass) {
                        return DataClass.get({id : $stateParams.id});
                    }]
                }
            })
            .state('dataClass.new', {
                parent: 'dataClass',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/dataClass/dataClass-dialog.html',
                        controller: 'DataClassDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    distanceTravelled: null,
                                    timeElapsed: null,
                                    speedLimit: null,
                                    speed: null,
                                    longAccelThrottle: null,
                                    longAccelBrake: null,
                                    crashCount: null,
                                    steeringRate: null,
                                    collisionTimeSameDir: null,
                                    distanceSameDir: null,
                                    collisionOppDir: null,
                                    distanceOppDir: null,
                                    longitudeAccel: null,
                                    lateralAccel: null,
                                    speedLimitFtSec: null,
                                    longitudeVelocity: null,
                                    lateralVelocity: null,
                                    lateralLanePosition: null,
                                    vehicleCurvature: null,
                                    roadCurvature: null,
                                    steeringCount: null,
                                    throttleCount: null,
                                    brakingCount: null,
                                    gear: null,
                                    trafficLightState: null,
                                    yawRate: null,
                                    operatorMarker: null,
                                    totalPitchingAngle: null,
                                    totalRollingAngle: null,
                                    routeName: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('dataClass', null, { reload: true });
                    }, function() {
                        $state.go('dataClass');
                    })
                }]
            })
            .state('dataClass.edit', {
                parent: 'dataClass',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/dataClass/dataClass-dialog.html',
                        controller: 'DataClassDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['DataClass', function(DataClass) {
                                return DataClass.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('dataClass', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('dataClass.delete', {
                parent: 'dataClass',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/dataClass/dataClass-delete-dialog.html',
                        controller: 'DataClassDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['DataClass', function(DataClass) {
                                return DataClass.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('dataClass', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
