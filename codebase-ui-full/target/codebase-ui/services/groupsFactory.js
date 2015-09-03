(function () {
    var $groups = function ($http) {
        var factory = {};
        //GET GROUPS
        factory.getAllGroups = function () {
            var allGroups = [{name:"Group 1", id:0}, {name:"Group 2", id:1}, {name:"Group 4", id:2}];   
            return allGroups;    
        }
        //GET GROUP
        factory.getGroups = function (id) {
           
        }
        //UPDATE GROUPS
        factory.updateGroup = function (data) {
           
        };
        //NEW GROUPS
        factory.newGroup = function (data) {
           
        };
        //DELETE GROUPS
        factory.deleteGroup = function (id) {
         
        };
        
        return factory;
    };

    $groups.$inject = ['$http'];
    angular.module("codebase").factory("$groups", $groups);

}());