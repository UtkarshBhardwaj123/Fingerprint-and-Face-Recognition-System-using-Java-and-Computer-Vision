function onSubmit() {
  const employeeName = document.getElementById("Employee_Name").value;
  // let probationPeriod = document.getElementById("Probation_Period");
  const dob = document.getElementById("Dob").value;
  const mobileNo = document.getElementById("Mobile_Number").value;
  const workingStatus = document.getElementById("Working_Status").value;
  const fathersName = document.getElementById("Father_Name").value;
  const doj = document.getElementById("Doj").value;
  const gender = document.getElementById("Gender").value;
  const department = document.getElementById("Department").value;
  const location = document.getElementById("Location").value;
  try {
    let sessionStorage = window["sessionStorage"];
    if (employeeName != null && employeeName.trim().length != 0)
      sessionStorage.setItem("employeeName", employeeName);

    if (dob != null && dob.trim().length != 0)
      sessionStorage.setItem("dob", dob);

    if (workingStatus != null && workingStatus != "select")
      sessionStorage.setItem("workingStatus", workingStatus);

    if (fathersName != null && fathersName.trim().length != 0)
      sessionStorage.setItem("fathersName", fathersName);

    if (doj != null && doj.trim().length != 0)
      sessionStorage.setItem("doj", doj);

    if (gender != null && gender != "Select Gender")
      sessionStorage.setItem("gender", gender);

    if (department != null && department.trim().length != 0)
      sessionStorage.setItem("department", department);

    if (mobileNo != null && mobileNo.trim().length != 0)
      sessionStorage.setItem("mobileNo", mobileNo);

    if (location != null && location.trim().length != 0)
      sessionStorage.setItem("location", location);
  } catch (e) {
    console.error(e);
  }
}
// employeeName.addEventListener("change", () => {
//   sessionStorage.setItem("employeeName", employeeName.value);
// });

// dob.addEventListener("change", () => {
//   sessionStorage.setItem("dob", dob.value);
// });

// mobileNo.addEventListener("change", () => {
//   sessionStorage.setItem("mobileNo", mobileNo.value);
// });

// workingStatus.addEventListener("change", () => {
//   sessionStorage.setItem("workingStatus", workingStatus.value);
// });

// fathersName.addEventListener("change", () => {
//   sessionStorage.setItem("fathersName", fathersName.value);
// });

// doj.addEventListener("change", () => {
//   sessionStorage.setItem("doj", doj.value);
// });

// gender.addEventListener("change", () => {
//   sessionStorage.setItem("gender", gender.value);
// });

// department.addEventListener("change", () => {
//   sessionStorage.setItem("department", department.value);
// });

// location.addEventListener("change", () => {
//   sessionStorage.setItem("Location", location.value);
// });
// employeeName = "Utka";
// probationPeriod = "aas";
// dob = "33-33-3333";
// mobileNo = "9928928";
// workingStatus = "WOF";
// gender = "male";
// department = "sdd";
// location = "gurgaon";
// fathersName = "Mr. Sushil Bhardwaj";
// doj = "22-22-2222";
