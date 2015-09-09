"use strict";
(function () {
    var $groups = function () {
        var factory = {};
        //GET GROUPS
        factory.getAllGroups = function () {
            var allGroups = [{name:"Group 1", id:0}, {name:"Group 2", id:1}, {name:"Group 4", id:2}];   
            return allGroups;    
        };
        //GET GROUP
        factory.getGroups = function () {
           
        };
        //UPDATE GROUPS
        factory.updateGroup = function () {
           
        };
        //NEW GROUPS
        factory.newGroup = function () {
           
        };
        //DELETE GROUPS
        factory.deleteGroup = function () {
         
        };
        
        return factory;
    };

    $groups.$inject = ['$http'];
    module.exports = $groups;
    //angular.module("codebase").factory("$groups", $groups);

}());