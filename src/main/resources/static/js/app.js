/*
 * Création d'un module pour pouvoir utiliser angularjs
 * Tout ce qu'on faisait avec mvc coté serveur avec thymeleaf;
 * On va le faire coté client.
 * On a donc besoin de créer un controleur (cette fois ci en javascript)
 * Dans le controleur on peut injecter des services fournis par angular
 * Parmis les services il y a le modèle, dans angularjs le modele 
 * s'appelle $scope (on va stocker toutes les données qui vont s'afficher
 * dans la vue). $scope est équivalent de model pour spring mvc.
 * Ensuite j'ai besoin d'envoyer des reqûetes vers le serveur.
 * Pour cela j'utiliser un service qui s'appelle $http
 * 
 * Pour l'exemple avec get ci-dessous :
 * On envoie la requête http et on entend la réponse, angular en 
 * recuperant la réponse, regarde le code status.
 * Si status = 200 il appelle le fonction sucess sinon s'il est différent
 * de 200 il appelle la fonction error.
 * Dans le cas ou c'est success il appelle une fonction dans lequel il 
 * lui transmet les données, qu'il stocke dans l'objet data.
 * Ces données sont le corps de la réponse http (ce sont les données 
 * json qu'il va stocker dans l'objet data). data est un objet json.
 * Ensuite on envoie les données (data) vers le modéle
 */
var app=angular.module('MyApp',['ui.router']); //j'ajoute un module router pour gérer les routes

/*
 * Gestion des routes
 * On utiliser les services $stateProvider et$urlRouterProvider pour configurer les routes
 */
app.config(function($stateProvider, $urlRouterProvider) {
	/*
	 * On définit les routes : 
	 *  home : on lui associe un objet javascript qui contient 
	 *  différents actions(url, templateUrl, controller)
	 *  les routes s'appellent ici entreprises et taxes
	 */
	$stateProvider.state('entreprises', {
		url:'/entreprise',
		templateUrl:'views/entreprises.html',
		controller: 'MyController'
	});
	
	$stateProvider.state('taxes', {
		url:'/taxes',
		templateUrl:'views/taxes.html',
		controller: 'TaxeController'
	});
	$urlRouterProvider.otherwise('/entreprises')
});
	
app.controller('TaxeController', function() {
	
});

//On utilise les services $scope et $http
app.controller('MyController', function($scope, $http) {
	
	$scope.pageEntreprises = [];
	$scope.mc = "";
	$scope.pageCourante = 0;
	$scope.size = 4;
	$scope.pages = [];
	
	$scope.chercher=function() {
		//envoie d'une requête http
		$http.get('http://localhost:8080/listEntreprises?mc='
				+$scope.mc+'&page='
				+$scope.pageCourante+'&size='
				+$scope.size+'')
			/*recuperation de la raquête( dans méthodes success et error)
			 * Le corps de la reponse(format JSON) est transféré dans 
			 * l'objet data
			 */
			.success(function(data) {
				/*
				 * Envoie des données vers le modèle avec $scope
				 * On crée une variable, et on lui transmet data
				 */
				$scope.pageEntreprises = data;
				
				$scope.pages = new Array(data.totalPages);
			})
			
			.error(function() {
				console.log(err);
			});
	}
	
	//Cette fonction va initialiser la page courante
	$scope.gotoPage = function(p){
		$scope.pageCourante = p;
		$scope.chercher();
	}
	
});