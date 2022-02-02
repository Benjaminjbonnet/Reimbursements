alert("manager login")
var apiUrl = "http://localhost:7070/login";

document.getElementById('getData').onclick = getData;
var username = document.getElementById("username").value;
var password = document.getElementById("password").value;
function getData() {


	fetch(apiUrl)

		//Handle success // Promise
		.then(response => response.json()) //convert json
		.then(json => login(json))//pass data to pupulateData() OR print data to console
		.catch(err => console.log('Request Failed', err));
}

function populateData(response) {

	var dataSection = document.getElementById('restData');
	dataSection.innerHTML = '';
	for (let i = 0; i < response.length; i++) {
		var idTag = document.createElement('h3');
		idTag.innerHTML = response[i].lastName

		var nameTag = document.createElement('h3');
		nameTag.innerHTML = response[i].firstName

		dataSection.appendChild(idTag);
		dataSection.appendChild(nameTag);
	}
}
function login(response) {

	for (let i = 0; i < response.length; i++) {
		var lUsername = response[i].firstName;
		var lPassword = response[i].lastName;
		if ((this.username == lUsername && this.username == lPassword)) {
			window.location = "managerHome.html"
		} else {
			alert("try again please")

		}
	}
}
