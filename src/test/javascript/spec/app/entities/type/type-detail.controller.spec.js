'use strict';

describe('Type Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockType, MockRoute;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockType = jasmine.createSpy('MockType');
        MockRoute = jasmine.createSpy('MockRoute');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'Type': MockType,
            'Route': MockRoute
        };
        createController = function() {
            $injector.get('$controller')("TypeDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'analyserApp:typeUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
