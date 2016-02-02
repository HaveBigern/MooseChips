'use strict';

angular.module('analyserApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('upload', {
                parent: 'site',
                url: '/upload',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/upload/upload.html',
                        controller: 'UploadController'
                    }
                },
                resolve: {
                    
                }
         })
        .state('upload.newUser', {
            parent: 'upload',
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
                    $state.go('upload', null, { reload: true });
                }, function() {
                    $state.go('upload');
                })
            }]
        });
    });
