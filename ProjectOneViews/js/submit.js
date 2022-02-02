
form.addEventListener("submit", (e) =>{
	e.preventDefault();
	var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
	var apiUrl = "http://localhost:7070/login";
	

	fetch(apiUrl, {
		method: "post",
		body: JSON.stringify({
			username: username,
			password: password
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