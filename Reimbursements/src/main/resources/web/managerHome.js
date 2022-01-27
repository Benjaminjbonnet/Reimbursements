var apiUrl = "http://localhost:4000/manager/home";


fetch(apiUrl)
		.then(response => response.json()) 
		.then(json => populateData(json))
		.catch(err => console.log('Request Failed', err));


  function populateData(response){
     var dataSection = document.getElementById('responseData');
     dataSection.innerHTML='';

     for(i=0;i<response.length;i++){
        //alert(response[i].id)
        var idTag = document.createElement('h3');
            var data=response[i].firstName +"  "+response[i].lastName+"  "+response[i].email+"  "+response[i].authorId 
           
            idTag.innerHTML=data;
             dataSection.appendChild(idTag);
    }



}
    
    
