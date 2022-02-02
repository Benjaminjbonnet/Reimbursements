var apiUrl = "http://localhost:4000/pending";


fetch(apiUrl)
		.then(response => response.json()) 
		.then(json => populateData(json))
		.catch(err => console.log('Request Failed', err));


  function populateData(response){ 
     var dataSection = document.getElementById('responseData');
    

     for(i=0;i<response.length;i++){
      var row= `
      		 <tr>
      		   <td>${response[i].reimbursementId}</td>
      		   <td>${response[i].status}</td>
      		   <td>${response[i].amount}</td>
      		   <td>${response[i].description}</td>
      		      <td>${response[i].type}</td>
      		   <td>${response[i].resolvetime}</td>
      		   <td>${response[i].submittime}</td>
      		   
      		 </tr> `
      		 dataSection.innerHTML += row
     

}

}
    
    