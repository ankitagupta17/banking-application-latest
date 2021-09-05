
    if(name==='confirmation-token')
    {
      
      fetch("http://localhost:9000/users/confirm-reset?token="+name, {
        method: "GET",
      }  
      )
      .then(function(response){ console.log("hello"+response.status);
      if(response.status===200)
      {
        resetPassword();
        // fetch("http://localhost:9000/users/reset-password/"+txt+"/"+)
  
      }
    // location.href="hellopage.html";
  })
    }
 