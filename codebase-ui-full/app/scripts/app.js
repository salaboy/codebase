'use strict';
(function () {
    var angular = require('angular');
    var mainController = require('./controllers/mainController');
    var groupsController = require('./controllers/groupsController');
    var loginController = require('./controllers/loginController');
    var navController = require('./controllers/navController');
    var studentsController = require('./controllers/studentsController');
    var teachersController = require('./controllers/teachersController');

    var $groups = require('./services/groupsFactory');
    var $students = require('./services/studentsFactory');
    var $teachers = require('./services/teachersFactory');
    var $users = require('./services/usersFactory');
    var $util = require('./services/utilFactory');
    
    var codebase = angular.module('codebase', ['ngRoute', 'ngAnimate', 'ngCookies']);
    codebase.controller('mainController', ['$scope', '$rootScope', '$location', mainController]);
    codebase.controller('groupsController',
            ['$scope', '$rootScope', '$location', '$routeParams',
                '$teachers', '$students', '$groups', '$util', groupsController]);
    codebase.controller('loginController', ['$scope', '$rootScope', '$users', '$location', loginController]);
    codebase.controller('navController', ['$scope', '$location', '$rootScope', '$users', navController]);
    codebase.controller('studentsController',
            ['$scope', '$rootScope', '$location',
                '$routeParams', '$students', '$util', studentsController]);
    codebase.controller('teachersController', ['$scope', '$rootScope', '$location',
        '$routeParams', '$teachers', '$students', '$groups', '$util', teachersController]);

    codebase.factory("$groups", $groups);
    codebase.factory("$students", $students);
    codebase.factory("$teachers", $teachers);
    codebase.factory("$users", $users);
    codebase.factory("$util", $util);

    codebase.constant("appConstants", {
        server: 'http://localhost:8080/',
        address: "localhost",
        port: "8080",
        context: ''

    })

            .config(function ($routeProvider) {
                $routeProvider
                        .when('/', {
                            templateUrl: 'views/login.html',
                            controller: 'loginController'
                        })

                        .when('/home', {
                            templateUrl: 'views/home.html',
                            controller: 'homeController'
                        })


                        .when('/teachers', {
                            templateUrl: 'views/teachers.html',
                            controller: 'teachersController'
                        })
                        .when('/teachers/add', {
                            templateUrl: 'views/teachers_add.html',
                            controller: 'teachersController'
                        })
                        .when('/teachers/:id', {
                            templateUrl: 'views/teachers_view.html',
                            controller: 'teachersController'
                        })

                        .when('/groups', {
                            templateUrl: 'views/groups.html',
                            controller: 'groupsController'
                        })
                        .when('/groups/add', {
                            templateUrl: 'views/groups_add.html',
                            controller: 'groupsController'
                        })
                        .when('/groups/:id', {
                            templateUrl: 'views/groups_view.html',
                            controller: 'groupsController'
                        })



                        .when('/students', {
                            templateUrl: 'app/views/students.html',
                            controller: 'studentsController'
                        })
                        .when('/students/add', {
                            templateUrl: 'app/views/students_add.html',
                            controller: 'studentsController'
                        })
                        .when('/students/:id', {
                            templateUrl: 'app/views/students_view.html',
                            controller: 'studentsController'
                        })


                        .when('/books', {
                            templateUrl: 'app/views/books.html',
                            controller: 'booksController'
                        })



                        .when('/settings', {
                            templateUrl: 'app/views/settings.html',
                            controller: 'settingsController'
                        })

                        .otherwise({
                            redirectto: '/'
                        });

            });


    //HISTORY 
//    codebase.run(function ($rootScope, $location) {
//
//        var history = [];
//
//        $rootScope.$on('$routeChangeSuccess', function () {
//            history.push($location.$$path);
//        });
//
//        $rootScope.back = function () {
//            var prevUrl = history.length > 1 ? history.splice(-2)[0] : "/";
//            $location.path(prevUrl);
//        };
//    });



}());



