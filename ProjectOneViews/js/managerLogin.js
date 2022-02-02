const form = document.getElementById("form");
form.addEventListener("submit", (e) =>{
	e.preventDefault();
	var usernameInput = document.getElementById("username").value;
    var passwordInput = document.getElementById("password").value;
	var apiUrl = "http://localhost:7070/login";

	fetch(apiUrl, {
		method: "post",
			headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			username: usernameInput,
			password: passwordInput
		}),
	})
	.then(response => response.json()) 
	.then(json => verifyLogin(json))
	.catch((err) => {console.log("Request Failed", err)});
});


function verifyLogin(response) {
	var passwordInput = document.getElementById("password").value;
	for(i=0;i<response.length;i++){

	if(response[i].password == passwordInput) {
		window.location.href="managerHome.html" 
	} else {
		console.log(response[i].password)
		console.log(passwordInput)
	}
}
}