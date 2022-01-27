fetch("http://localhost:4000/em")
.then(res => res.json)
.then(data => console.log(data))