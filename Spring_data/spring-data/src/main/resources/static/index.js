angular.module('app', [])
    .controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/app';


    $scope.loadProducts = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                p: $scope.currentPage,
                sortColumn: $scope.currentSort,
                sortASC: $scope.ASC,
                minPrice: $scope.filter ? $scope.filter.minPrice : null,
                maxPrice: $scope.filter ? $scope.filter.maxPrice : null
            }
        }).then(function (response) {
            $scope.ProductList = response.data.content;
            $scope.PageList = Array.from({length: response.data.totalPages}, (_, i) => i + 1);
            $scope.showActivePage();
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

    $scope.changePage = function (numberPage) {
        if (numberPage > $scope.PageList.length) {
            numberPage = $scope.PageList.length;
        }
        if (numberPage < 1) {
            numberPage = 1;
        }

        $scope.currentPage = numberPage;
        $scope.loadProducts();
    }

    $scope.showActivePage = function () {
        for (let e of document.getElementsByClassName('page-item')) {
            e.classList.remove('active');
        }
        let element = document.getElementById('button_page_' + $scope.currentPage);
        if (element != null){
            element.classList.add('active');
        }
        if ($scope.currentPage === 1) {
            document.getElementById('buttonBefore').classList.add('disabled');
        } else {
            document.getElementById('buttonBefore').classList.remove('disabled');
        }
        if ($scope.currentPage === $scope.PageList.length) {
            document.getElementById('buttonAfter').classList.add('disabled');
        } else {
            document.getElementById('buttonAfter').classList.remove('disabled');
        }
    }

    $scope.changeSort = function (column){
        if (column === $scope.currentSort){
            $scope.ASC = !$scope.ASC;
        } else {
            $scope.currentSort = column;
            $scope.ASC = true;
        }
        $scope.loadProducts();
    }

    $scope.currentPage = 1;
    $scope.currentSort = 'title';
    $scope.ASC = true;
    $scope.loadProducts();
}).directive("checkActivePage", function () {
    return function (scope, element, attrs) {
        if (scope.$first){
            scope.showActivePage();
        }
        let numberPage = attrs["checkActivePage"];
        if (numberPage == scope.currentPage) {
            element.addClass("active");
        }else {
            element.removeClass("active");
        }
    }
});