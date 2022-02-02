alert("all employee")
var apiUrl =  "http://localhost:7070/employee";

document.getElementById('getData').onclick = getData;

function getData(){
	
	
	fetch(apiUrl)
	
	//Handle success // Promise
	.then(response => response.json()) //convert json
	.then(json => populateData(json))//pass data to pupulateData() OR print data to console
	.catch(err => console.log('Request Failed',err));
}

function populateData(response) {
	
    var dataSection = document.getElementById('restData');
    dataSection.innerHTML='';
    for(let i=0;i<response.length;i++){
    var idTag = document.createElement('h3');
    idTag.innerHTML=response[i].lastName

    var nameTag = document.createElement('h3');
    nameTag.innerHTML=response[i].firstName

    dataSection.appendChild(idTag);
    dataSection.appendChild(nameTag);
    }
}



