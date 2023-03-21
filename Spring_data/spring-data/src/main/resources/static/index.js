angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/app';

    $scope.loadProducts = function () {
        $http({
            url: contextPath + '/products/',
            method: 'GET'
        }).then(function (response) {
            $scope.ProductList = response.data;
        });
    }

    $scope.deleteProduct = function (id) {
        $http({
            url: contextPath + '/products/delete/' + id,
            method: 'GET'
        }).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.loadProducts();
})