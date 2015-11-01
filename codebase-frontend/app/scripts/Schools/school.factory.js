(function () {
    angular
        .module('school.factory', [])
        .factory('schoolFactory', schoolFactory);

    schoolFactory.$inject = ['$http', 'constantsFactory'];

    function schoolFactory( $http, constants) {
        var service = {
            newSchool: newSchool,
            getAllSchools: getAllSchools,
            getSchool: getSchool,
            updateSchool: updateSchool,
            deleteSchool: deleteSchool
        };

        return service;

        function newSchool(school) {
            return $http.post(constants.server + 'schools/new', school, constants.headers);
        }

        function getAllSchools() {
            return $http.get(constants.server + 'schools', constants.headers);
        }
        
        function getSchool(id){
            return $http.get(constants.server + 'schools/'+id, constants.headers);
        }
       
       function updateSchool(school){
           return $http.post(constants.server + 'schools/update', school, constants.headers);
       }
       
       function deleteSchool(id){
           return $http.delete(constants.server + 'schools/'+id, constants.headers);
       }
    }
})();