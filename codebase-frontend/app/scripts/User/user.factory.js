(function () {
    angular
        .module('user.factory', [])
        .factory('userFactory', userFactory);

    userFactory.$inject = ['$cookies', 'restFactory', 'hello'];

    function userFactory($cookies, rest, hello) {
        var service = {
            signUp: signUp,
            getString: getString,
            logout: logout,
            login: login,
            search: search,
            getString: getString
        }

        return service;

        function signUp(authObj) {
            var data = rest.post('rest/auth2/register', authObj);
        }

        function getString() {
            return 'hello';
        }

        function logout() {
            var data = rest.authPost('rest/auth2/logout', {});
        }

        function login() {
            var data = rest.post('rest/auth2/login', authObj);
            /*  $cookies.put('user_email', result.data.email);
              $cookies.put('user_token', result.data.auth_token);
              alert('result: ' + JSON.stringify(result));
              userFactory.search()  */
        }

        function search() {
            var searchString = '?lon=0&lat=0&interests=[]&lookingFors=[]&categories=[]&range=NA&offset=0&limit=10&excludes=["grogdj"]';
            var data = rest.get('rest/users/search' + searchString, {});
        }
    }
})();