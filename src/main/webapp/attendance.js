
	let table = document.getElementById("table");

getData();
 




async function getData(){
 let headers = { "Content-Type": "application/json" };
let data = [];
  await axios
    .get("http://192.168.141.155:8080/attendance/ac/empAtt", headers)
    .then(
      (response) => {
        console.log(response.data);
        data = [...response.data.DATA];
      },
      (error) => {
        console.log(error);
      }
    );
console.log(data);
for(let i = 0; i<data.length; i++){

  console.log(data[i]);
  console.log(i);
           let row = document.createElement("tr");

           row.innerHTML = '<td>'+data[i].EMPLOYEE_ID + '</td>'+
                            '<td>'+data[i].EMPLOYEE_NAME+'</td>'+
                            '<td>'+data[i].FATHERS_NAME+'</td>'+
                            '<td>'+data[i].GENDER+'</td>'+
                            '<td>'+data[i].MOBILE_NO+'</td>'+
                            '<td>'+data[i].DEPARTMENT+'</td>'+
                            '<td>'+data[i].DATE_OF_JOINING+'</td>'+
                            '<td>'+data[i].LOCATION+'</td>'+
                            '<td>'+data[i].DOB+'</td>';
          table.appendChild(row);
};

    // function addAll(item,index){
    //     let row = table.addRow(index);

    //        row.innerHTML = '<td>'+item.EMPLOYEE_ID + '</td>'+
    //                         '<td>'+item.EMPLOYEE_NAME+'</td>'+
    //                         '<td>'+item.FATHERS_NAME+'</td>'+
    //                         '<td>'+item.GENDER+'</td>'+
    //                         '<td>'+item.MOBILE_NO+'</td>'+
    //                         '<td>'+item.DEPARTMENT+'</td>'+
    //                         '<td>'+item.DOJ+'</td>'+
    //                         '<td>'+item.LOCATION+'</td>'+
    //                         '<td>'+item.DOB+'</td>';
    // }
}
