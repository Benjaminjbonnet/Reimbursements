var apiUrl = "http://localhost:7070/employees";


fetch(apiUrl)
		.then(response => response.json()) 
		.then(json => populateData(json))
		.catch(err => console.log('Request Failed', err));


  function populateData(response){
     var dataSection = document.getElementById("responseData");
     dataSection.innerHTML='';

     for(i=0;i<response.length;i++){
            
        var idTag = document.createElement("tr");
            var data=response[i].firstName +"   "+response[i].lastName
           
            idTag.innerHTML=data;
             dataSection.appendChild(idTag);
    }



}
    
    
