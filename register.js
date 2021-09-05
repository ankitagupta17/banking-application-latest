var form = document.getElementById('myform').addEventListener('submit',function(e){
  e.preventDefault();
  var account = document.getElementById('account').value;
  var firstname = document.getElementById('firstname').value;
  var lastname =document.getElementById('lastname').value;
  var email=document.getElementById('email').value;
  var contact=document.getElementById('contact').value;
  var password=document.getElementById('password').value;

  // main.js

// POST request using fetch()
fetch("http://localhost:8082/users/", {
	
	// Adding method type
	method: "POST",
	// mode :"no-cors",
	// Adding body or contents to send
  body: JSON.stringify({
    account_no:account,
    firstname:firstname,
    lastname:lastname,
    email:email,
    contact:contact,
    password: password
  }),
	
	// Adding headers to the request
	headers: {
		"Content-type": "application/json"
	}
})

// Converting to JSON
.then(function(response){ console.log(response)})

// Displaying results to console
.then(function(json) { console.log(json)});

})