alert("all employee")
var apiUrl =  "http://localhost:4000/em";

document.getElementById('getData').onclick = getData;

function getData(){
	
	
	fet(apiUrl)
	
	//Handle success // Promise
	.then(response => response.json()) //convert json
	.then(json => populateData(json))//pass data to pupulateData() OR print data to console
	.catch(err => console.log('Request Failed',err));
}

function populateData(response){
	var dataSection = document.getElementById('restData');
	dataSection.innerHTML='';
	var nameTag = document.createElement('h3');
	idTag.innerHTML=response.data.first_name
	dataSection.appendChild(idTag);
	dataSection.appendChild(nameTag);
	
	
};




