
<head>
<style type="text/css">
ul.dropdown  {
 top: 206px;
   width: 470px;
   
background-color: #fff;
color: #fff;
font-size: 11px;
text-transform: none;
filter: alpha(opacity=90);
-moz-opacity: .9;
KhtmlOpacity: .9;
opacity: 1;

position: absolute;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Angular JS</title>


<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.23/angular.min.js"></script>
<script>
var app = angular.module('myApp', []);
//app.controller("MyController",MyController);
function MyController($scope, $http) {

 $scope.sortType     = 'name'; // set the default sort type
 $scope.sortReverse  = false;  // set the default sort order
 $scope.search   = '';     // set the default search/filter term

       $scope.getDataFromServer = function() {
               $http({
                       method : 'GET',
                       url : 'productsangular'
               }).success(function(data, status, headers, config) {
               	/* alert(data); */ $scope.Blogs = data; 
               }).error(function(data, status, headers, config) {
                       
               });

               $scope.hideDropdown = function(){
                   $scope.dropDown = false;
                 };
       };
       

       
}; 
</script>


</head>
<body>
<div class="container" ng-app="myApp" ng-controller="MyController" style="width:500px;float: right;">
 
 
 
 <form>
   <div class="form-group">
     <div class="input-group">
       <div class="input-group-addon"><i class="fa fa-search"></i></div>
       <input type="search" class="form-control" ng-model="search"  ng-change="getDataFromServer()" placeholder="Search Product Name" >
      <!--  <button  ng-click="getDataFromServer()">List of products</button> &nbsp; --> 
    
      </div>      
   </div>
   <div ng-show="search" class="auto-close="outsideClick">
      <ul class="dropdown">
 	<li style="font-size: 15px;border-top:2px  black;" ng-repeat="roll in Blogs | orderBy:sortType:sortReverse | filter:search">



 	<a href="display_product/get/{{ roll.id }}">{{ roll.name }}</a><br/><br/> 
 	
<!--   <a  href="edit?id={{roll.id}}">like</a> <br/><br/>-->
 	
 	</li>
 </ul>
 </div>
 </form>
 <!--   <div ng-change="getDataFromServer()" >
    <div > -->

 

</div>
   </div>
   </div>