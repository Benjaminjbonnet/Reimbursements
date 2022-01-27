var apiUrl = "http://localhost:4000/reimbursements";


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
            var data="  Reimbursement Id--  "+response[i].reimbursementId+" --Status--  "+response[i].status+"-- Amount--  "+response[i].amount+"-- Description--  "+response[i].description+"--Type--"+ 
            response[i].type +" --Resolve Time -- "+response[i].resolvetime+"-- Submit Time--  "+response[i].submittime
           
            idTag.innerHTML=data;
             dataSection.appendChild(idTag);
    }



}
    
    