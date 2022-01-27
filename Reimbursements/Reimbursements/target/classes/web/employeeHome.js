var apiUrl = "http://localhost:4000/viewmyinfo";







fetch(apiUrl)
		.then(response => response.json()) 
		.then(json => populateData(json))
		.catch(err => console.log('Request Failed', err));


  function populateData(response){
     var dataSection = document.getElementById('responseData');
     dataSection.innerHTML='';

     for(i=0;i<response.length;i++){
      var row= `
      		 <tr>
      		   <td>${response[i].firstName}</td>
      		   <td>${response[i].lastName}</td>
      		   <td>${response[i].email}</td>
      		   <td>${response[i].userId}</td>
      		      <td>${response[i].username}</td>

      		   
      		 </tr> `
      		 dataSection.innerHTML += row


}
}    
  
