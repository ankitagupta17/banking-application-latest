var form = document.getElementById('myform').addEventListener('submit',e =>{
    e.preventDefault();
    var account = document.getElementById('account').value;
    var firstname = document.getElementById('firstname').value;
    var lastname =document.getElementById('lastname').value;
    var email=document.getElementById('email').value;
    var contact=document.getElementById('contact').value;
    var formData = new FormData();
    formData.append("account_no",account);
    formData.append("firstname",firstname);
    formData.append("lastname",lastname);
    formData.append("email",email);
    formData.append("contact",contact);
    console.log("hello");
    fetch('http://localhost:8082/account-reference-data-service/users/createAccount/', {
      method: 'POST',
      // Accept : "application/json",
      // contentType: "application/json;charset=utf8",
      // mode:'cors',
      // headers: {
      //   'Accept': 'application/json',
      //   'Content-Type': 'application/json'
      // },
      body: formData
      //  JSON.stringify({
      //   account_no:account,
      //   firstname:firstname,
      //   lastname:lastname,
      //   email:email,
      //   contact:contact
      // })
    }).then(function(response) {
      console.log(response); // returns 200
      // response.blob().then(function(myBlob) {
      //   var objectURL = URL.createObjectURL(myBlob);
      //   myImage.src = objectURL;
      });
    // });
    
    // .then((response) => {
    //   if (response.ok) { // Check for a non 2xx status code
    //     console.log('Network response was ok');
    //   }
    //   console.log("out");
    // }).catch(err=>res.status(200).send('Wrong user name or password.'));
    
    
    // .then(response => response.text()).then(response =>{
    //     if(JSON.parse( response)[0].message==="success"){
    //         location.href="showdata.html";
    //     }
    //     else{
    //         console.log("not");
    //     }
    
    // })
    // .catch(err => console.log(err));
    })