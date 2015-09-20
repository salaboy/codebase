'use strict';

describe('Directive: profileList', function () {

  // load the directive's module
  beforeEach(module('codebaseFrontendApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<profile-list></profile-list>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the profileList directive');
  }));
});
