'use strict';

angular.module('analyserApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('dataUser', {
                parent: 'entity',
                url: '/dataUsers',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DataUsers'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/dataUser/dataUsers.html',
                        controller: 'DataUserController'
                    }
                },
                resolve: {
                }
            })
            .state('dataUser.detail', {
                parent: 'entity',
                url: '/dataUser/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'DataUser'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/dataUser/dataUser-detail.html',
                        controller: 'DataUserDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'DataUser', function($stateParams, DataUser) {
                        return DataUser.get({id : $stateParams.id});
                    }]
                }
            })
            .state('dataUser.new', {
                parent: 'dataUser',
                url: '/new',
                data: {
                    authorities: [],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/dataUser/dataUser-dialog.html',
                        controller: 'DataUserDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    participantNum: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('dataUser', null, { reload: true });
                    }, function() {
                        $state.go('dataUser');
                    })
                }]
            })
            .state('dataUser.edit', {
                parent: 'dataUser',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/dataUser/dataUser-dialog.html',
                        controller: 'DataUserDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['DataUser', function(DataUser) {
                                return DataUser.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('dataUser', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('dataUser.delete', {
                parent: 'dataUser',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/dataUser/dataUser-delete-dialog.html',
                        controller: 'DataUserDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['DataUser', function(DataUser) {
                                return DataUser.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('dataUser', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
