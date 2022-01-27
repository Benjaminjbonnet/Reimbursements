
form.addEventListener("submit", (e) =>{
	e.preventDefault();
	var usernameInput = document.getElementById("username").value;
    var passwordInput = document.getElementById("password").value;
	var apiUrl = "http://localhost:4000/managerlogin";
	
	console.log(usernameInput, passwordInput);
	console.log("JSON: ", JSON.stringify({
			username: usernameInput,
			password: passwordInput
		}))

	fetch(apiUrl, {
		method: "post",
		body: JSON.stringify({
			username: usernameInput,
			password: passwordInput
		}),
	})
	.then(response => {myFunct(response.status)})
	.catch((err) => {console.log("Request Failed", err)});
});


function myFunct(status) {
	if(status == 200) {
		window.location.href="employeeHome.html" 
	} else {
		alert("Try Again ")
	}
	
}