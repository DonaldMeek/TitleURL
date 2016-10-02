/* Generates text box with name auto-incrimenting */
class URL() {
	let URLName;
	constructor()
	{
		URLName = 0;
	}
	var getURL = function () {
		return URLName;
	}
	var incrementURL = function() {
		URLName++;
	}
}
var getNewURL = function () {
	var url = new URL();
	document.getElementById("testing").innerHTML = url.getURL();
	url.incrementURL();
	console.log(url.getURL);
	
	
	
	
	exit(0);
	
	
	
	
	
}
