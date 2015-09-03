(function (){
    
    var navigationController = function($scope, $location, $rootScope){
        
        
        $scope.isActive = function (route) {
            return route === $location.path();
        };

        
    };
    
    navigationController.$inject = ['$scope','$location', '$rootScope'];
    angular.module( "codebase" ).controller("navigationController", navigationController);
    
}());
