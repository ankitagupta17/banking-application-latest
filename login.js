var form = document.getElementById('myform').addEventListener('submit',function(e){
  e.preventDefault();
  var email=document.getElementById('email').value;
  var password=document.getElementById('password').value;

  // main.js

// POST request using fetch()
// http://localhost:8082/account-reference-data-service/users/login
fetch("http://localhost:8082/users/login", {
	
	// Adding method type
	method: "POST",
	// mode :"no-cors",
	// Adding body or contents to send
  body: JSON.stringify({
    email:email,
    password: password
  }),
	
	// Adding headers to the request
	headers: {
		"Content-type": "application/json"
	}
})

// Converting to JSON
.then(function(response){ console.log(response.headers.forEach(function(value, name) {
  console.log(name + ": " + value);
  if(name==='token')
  {
    
    fetch("http://localhost:8082/users/128", {
      method: "GET",
      // mode :"no-cors",
      // Adding body or contents to send
     
      
      // Adding headers to the request
      headers: {
        "Authorization": "Bearer "+value
      },
    }  
    )
    .then(function(response){ console.log("helo");
  location.href="hellopage.html";})
  }
})
)})

// Displaying results to console
.then(function(json) { console.log(json)});

})

function myFunction() {
  var txt;
  var person = prompt("Please enter your complete email id:", "Enter here");
  if (person == null || person == "") {
    txt = "User cancelled the prompt.";
  } else {
    txt =person;
  }
  // document.getElementById("demo").innerHTML = txt;
  jsfunction(txt)
}

function resetPassword() {
  var pass;
  var person = prompt("Please enter the new password:", "Enter here");
  if (person == null || person == "") {
    pass = "User cancelled the prompt.";
  } else {
    pass =person;
  }
}


function jsfunction(txt){
  console.log("forget link"+txt);
  fetch("http://localhost:9000/users/forgot-password/"+txt, {
	
	// Adding method type
	method: "POST",
	// mode :"no-cors",
	// Adding body or contents to send
})

// Converting to JSON
.then(function(response){ console.log(response.headers.forEach(function(value, name) {
  console.log(name + ": " + value);
  if(name==='confirmation-token')
  {
    
    fetch("http://localhost:9000/users/confirm-reset?token="+value, {
      method: "GET",
    }  
    )
    .then(function(response){ console.log("hello"+response.status);
    console.log("ankita"+name);
    if(response.status===200)
    {
      resetPassword();
      // fetch("http://localhost:9000/users/reset-password/"+txt+"/"+)

    }
  // location.href="hellopage.html";
})
  }
})
)});

  
  }