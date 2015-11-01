(function () {
    angular
        .module('school.factory', [])
        .factory('schoolFactory', schoolFactory);

    schoolFactory.$inject = ['$cookies', 'restFactory', '$http', 'constantsFactory'];

    function schoolFactory(rest, $http, constants) {
        var service = {
            newSchool: newSchool,
            getAllSchools: getAllSchools,
            getSchool: getSchool,
            updateSchool: updateSchool
        };

        return service;

        function newSchool(school) {
            return $http.post('schools/new', school, constants.headers);
        }

        function getAllSchools() {
            return $http.get('schools', constants.headers);
        }
        
        function getSchool(id){
            return $http.get('schools/'+id, constants.headers);
        }
       
       function updateSchool(school){
           return $http.post('schools/update', school, constants.headers);
       }
    }
})();