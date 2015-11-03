(function () {

    /**
     * @ngdoc overview
     * @name codebaseFrontendApp
     * @description
     * # codebaseFrontendApp
     *
     * Main module of the application.
     */

    angular
        .module('codebaseFrontendApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ngHello',
    'ui.router',
    'rest.factory',
    'constants.factory',
    'login.factory',
    'login.controller',
    'school.factory',
    'school.list.controller',
    'school.new.controller',
    'school.details.controller',
    'school.edit.controller',
    'course.factory',
    'course.list.controller',
    'course.new.controller',
    'course.details.controller',
    'course.edit.controller',
    'student.factory',
    'student.list.controller',
    'student.new.controller',
    'student.details.controller',
    'student.edit.controller'
  ])
        .config(function ($stateProvider, $urlRouterProvider, helloProvider) {
            helloProvider.init({
                facebook: '163194807355490'
            });

            $urlRouterProvider.otherwise('/');
            $stateProvider
                .state('login', {
                    url: '/',
                    templateUrl: 'scripts/Login/login.html',
                    controller: 'loginController'
                })
                .state('schools', {
                    url: '/schools',
                    templateUrl: 'scripts/Schools/List/list.html',
                    controller: 'schoolListController'
                })
                .state('schools/new', {
                    url: '/schools/new',
                    templateUrl: 'scripts/Schools/New/new.html',
                    controller: 'newSchoolController'
                })
                .state('schools/edit', {
                    url: '/schools/edit',
                    templateUrl: 'scripts/Schools/Edit/edit.html',
                    controller: 'editSchoolController'
                })
                .state('schools/details', {
                    url: '/schools/details',
                    templateUrl: 'scripts/Schools/Details/details.html',
                    controller: 'schoolDetailsController'
                }).state('courses', {
                    url: '/courses',
                    templateUrl: 'scripts/Courses/List/list.html',
                    controller: 'coursesListController'
                })
                .state('courses/new', {
                    url: '/courses/new',
                    templateUrl: 'scripts/Courses/New/new.html',
                    controller: 'newCourseController'
                })
                .state('courses/edit', {
                    url: '/courses/edit',
                    templateUrl: 'scripts/Courses/Edit/edit.html',
                    controller: 'editCourseController'
                })
                .state('courses/details', {
                    url: '/courses/details',
                    templateUrl: 'scripts/Courses/Details/details.html',
                    controller: 'courseDetailsController'
                }).state('students', {
                    url: '/students',
                    templateUrl: 'scripts/Students/List/list.html',
                    controller: 'studentsListController'
                })
                .state('students/new', {
                    url: '/students/new',
                    templateUrl: 'scripts/Students/New/new.html',
                    controller: 'newStudentController'
                })
                .state('students/edit', {
                    url: '/students/edit',
                    templateUrl: 'scripts/Students/Edit/edit.html',
                    controller: 'editStudentController'
                })
                .state('students/details', {
                    url: '/students/details',
                    templateUrl: 'scripts/Students/Details/details.html',
                    controller: 'studentDetailsController'
                });
        }).run(function () {});
})();