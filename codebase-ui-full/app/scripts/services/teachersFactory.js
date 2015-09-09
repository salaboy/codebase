"use strict";
(function () {
    var $teachers = function () {
        var factory = {};
        //GET TEACHERS
        factory.getAllTeachers = function () {
           var allteachers = [{name: "Teacher 1", groups: ["Group 1", "Group 10"], id: 0 }, {name: "Teacher 2", groups: ["Group 1"], id: 1 }, {name: "Teacher 3", groups: ["Group 2"], id: 2 }];
           
           return allteachers;
           
        };
        //GET TEACHER
        factory.getTeacher = function () {
           
        };
        //UPDATE TEACHER
        factory.updateTeacher = function () {
           
        };
        //NEW TEACHER
        factory.newTeacher = function () {
           
        };
        //DELETE TEACHER
        factory.deleteTeacher = function () {
         
        };
        
        return factory;
    };

    $teachers.$inject = ['$http'];
    module.exports = $teachers;
    //angular.module("codebase").factory("$teachers", $teachers);

}());