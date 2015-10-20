'use strict';

/**
 * @ngdoc directive
 * @name codebaseFrontendApp.directive:profileList
 * @description
 * # profileList
 */
angular.module('codebaseFrontendApp')
  .directive('profileList', function () {
    return {
      template: '<div></div>',
      restrict: 'E',
      link: function postLink(scope, element) {
        element.text('this is the profileList directive');
      }
    };
  });
