'use strict';

describe('Route Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockRoute, MockDataUser, MockType, MockDataClass;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockRoute = jasmine.createSpy('MockRoute');
        MockDataUser = jasmine.createSpy('MockDataUser');
        MockType = jasmine.createSpy('MockType');
        MockDataClass = jasmine.createSpy('MockDataClass');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'Route': MockRoute,
            'DataUser': MockDataUser,
            'Type': MockType,
            'DataClass': MockDataClass
        };
        createController = function() {
            $injector.get('$controller')("RouteDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'analyserApp:routeUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
