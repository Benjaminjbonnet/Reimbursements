
var form = document.getElementById("form");
form.addEventListener("submit", (e) =>{
	e.preventDefault();
	var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
	var apiUrl = "http://localhost:4000/employeelogin";
	

	fetch(apiUrl, {
		method: "post",
		body: JSON.stringify({
			username: username,
			password: password
		}),
	})
	.then(response => {myFunct(response),console.log(response)})
	.catch((err) => {console.log("Request Failed", err)});
});


function myFunct(response) {
	
	if(response.password == password) {
		window.location.href="employeeHome.html" 
	} else {
		alert("Try Again ")
	}
	
}