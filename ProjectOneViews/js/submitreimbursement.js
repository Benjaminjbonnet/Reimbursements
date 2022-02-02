form.addEventListener("submit", (e) => {
	e.preventDefault();
	var amountInput = document.getElementById("amount").value;
	var descriptionInput = document.getElementById("description").value;
	var typeInput = document.getElementById("type").value;
	var resolvetimeInput = document.getElementById("resolvetime").value;
	var submittimeInput = document.getElementById("submittime").value;
	var authoridInput = document.getElementById("authorid").value;
	var apiUrl = "http://localhost:7070/reimbursements/newreimbursement";


	fetch(apiUrl, {
		method: "post",
		body: JSON.stringify({
			amount: amountInput,
			description: descriptionInput,
			type: typeInput,
			resolvetime: resolvetimeInput,
			submittime: submittimeInput,
			authorid: authoridInput,

		}),
	})
		.then(response => { myFunct(response.status) })
		.catch((err) => { console.log("Request Failed", err) });
});


function myFunct(status) {
	if (status == 200) {
		window.location.href = "employeeHome.html"
	} else {
		alert("Try Again ")
	}

}