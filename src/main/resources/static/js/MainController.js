/* MainController - the controller for the "Sudoku view" with methods createBoardDefault and editBoard
 * relies on Angular injector to provide:
 *     $scope - Is available for the access the var of the view and the controller 
 *     MainService - the application data access service (methods get).
 */
(function () {
  'use strict';

  angular
    .module('app')
    .controller('MainController', MainController);

  MainController.$inject = ['$scope', 'MainService'];

  function MainController($scope, MainService){
    var vm = this;

    $scope.message = "";
    $scope.getData = getData;
    $scope.search = "https://en.wikipedia.org/wiki/Main_Page";

    function getData() {
      return MainService.getData($scope.search)
        .then(function(data) {
            //console.log($scope.data);
            $scope.data = data;
        })
        .catch(function(error) {
            if( error !== 'undefined' ){
                if(error.status == 404 || error.status == 500){
                    $scope.message = "In this moment, we can't process your request , please try again later";
                } 
            }
        })
    }


  }
})();