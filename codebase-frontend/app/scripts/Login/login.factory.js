(function () {
    angular
        .module('login.factory', [])
        .factory('loginFactory', loginFactory);

    loginFactory.$inject = ['$cookies', 'restFactory', 'hello'];

    function loginFactory($cookies, rest, hello) {
        var service = {
            signUp: signUp,
           
        }

        return service;

        function signUp(authObj) {
            var data = rest.post('api/auth2/register', authObj);
        }

       
    }
})();