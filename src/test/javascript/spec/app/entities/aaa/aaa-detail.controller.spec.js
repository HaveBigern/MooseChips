'use strict';

describe('Aaa Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockAaa, MockBbb;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockAaa = jasmine.createSpy('MockAaa');
        MockBbb = jasmine.createSpy('MockBbb');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'Aaa': MockAaa,
            'Bbb': MockBbb
        };
        createController = function() {
            $injector.get('$controller')("AaaDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'analyserApp:aaaUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
