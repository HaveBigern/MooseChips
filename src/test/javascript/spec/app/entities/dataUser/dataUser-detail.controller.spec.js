'use strict';

describe('DataUser Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockDataUser, MockDataClass;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockDataUser = jasmine.createSpy('MockDataUser');
        MockDataClass = jasmine.createSpy('MockDataClass');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'DataUser': MockDataUser,
            'DataClass': MockDataClass
        };
        createController = function() {
            $injector.get('$controller')("DataUserDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'analyserApp:dataUserUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
