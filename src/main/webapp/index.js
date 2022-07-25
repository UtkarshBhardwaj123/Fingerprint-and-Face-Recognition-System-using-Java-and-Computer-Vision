const isAdmin = true
const open = true
   async function checkUser(){
       let response =   await fetch('https://localhost:8080/checkLoginIDAndPassword', {
         headers:{
            'Content-Type':'application/json'
        },
         body: JSON.stringify( 
             {
               LOGIN_ID:'abc',
               PASSWORD:'ut_ut'
             })
       })
       consol.log(response.json());
       return response.json();
       response.then(resp=>{
          if(resp.ok)
            console.log("success")
          else
            console.log("failed")
        return resp.json();
      }).then(data=> {Console.log(data);
        return data;}).catch(err=>{
            console.log(err);})
    }

    function 
// checkUser().then(response=>{
//       console.log(response)
//   })
// if(checkUser().get(0))
//    open = true
// else 
   // open = false