(function () {
    angular
        .module('student.factory', [])
        .factory('studentFactory', studentFactory);

    studentFactory.$inject = ['$cookies', 'restFactory', 'hello'];

    function studentFactory($cookies, rest, hello) {
        var service = {
            signUp: signUp
            
        }

        return service;

        function signUp(authObj) {
            var data = rest.post('api/auth2/register', authObj);
        }

       
    }
})();