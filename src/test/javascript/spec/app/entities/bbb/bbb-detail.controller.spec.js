'use strict';

describe('Bbb Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockBbb, MockAaa;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockBbb = jasmine.createSpy('MockBbb');
        MockAaa = jasmine.createSpy('MockAaa');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'Bbb': MockBbb,
            'Aaa': MockAaa
        };
        createController = function() {
            $injector.get('$controller')("BbbDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'analyserApp:bbbUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
