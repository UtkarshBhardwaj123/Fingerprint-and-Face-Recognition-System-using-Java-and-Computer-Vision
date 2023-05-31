let video = document.getElementById("video");
let localstream = null;
Promise.all([
  faceapi.nets.tinyFaceDetector.loadFromUri("././models"),
  faceapi.nets.faceLandmark68Net.loadFromUri("././models"),
  faceapi.nets.faceRecognitionNet.loadFromUri("././models"),
  faceapi.nets.faceExpressionNet.loadFromUri("././models"),
]).then(startVideo);

function startVideo() {
  //   //to navigate our camera
  navigator.mediaDevices
    .getUserMedia({ video: {} })
    .then((stream) => {
      video.srcObject = stream;
      localstream = stream;
    })
    .catch((err) => console.error(err));
}

let displaySize = { width: video.width, height: video.height };

// let src = new cv.Mat(video.height, video.width, cv.CV_8UC4);
function createCanvas() {
  //creating and appending canvas element on our page
  let canvas = faceapi.createCanvasFromMedia(video);
  document.body.append(canvas);

  // getting the display of video element and making our canvas element just equal to it's height and width
  faceapi.matchDimensions(canvas, displaySize);
  return canvas;
}

async function saveUserImage(blobObj) {
  let jsonObj = {
    data: {
      "EMPLOYEE_NAME": "",
      "PROBATION_PERIOD": "",
      "DOB": "",
      "MOBILE_NO": "",
      "WORKING_STATUS": "",
      "FATHERS_NAME": "",
      "DOJ": "",
      "GENDER": "",
      "DEPARTMENT": "",
      "LOCATION": "",
      "EMPLOYEE_IMAGE": ""
    }
  };

  let sessionStorage = window["sessionStorage"];

  if (sessionStorage.getItem("employeeName") != null)
    jsonObj.data.EMPLOYEE_NAME = sessionStorage.getItem("employeeName");

  if (sessionStorage.getItem("dob") != null)
    jsonObj.data.DOB = sessionStorage.getItem("dob");

  if (sessionStorage.getItem("mobileNo") != null)
    jsonObj.data.MOBILE_NO = sessionStorage.getItem("mobileNo");

  if (sessionStorage.getItem("workingStatus") != null)
    jsonObj.data.WORKING_STATUS = sessionStorage.getItem("workingStatus");

  if (sessionStorage.getItem("fathersName") != null)
    jsonObj.data.FATHERS_NAME = sessionStorage.getItem("fathersName");

  if (sessionStorage.getItem("doj") != null)
    jsonObj.data.DOJ = sessionStorage.getItem("doj");

  if (sessionStorage.getItem("gender") != null)
    jsonObj.data.GENDER = sessionStorage.getItem("gender");

  if (sessionStorage.getItem("department") != null)
    jsonObj.data.DEPARTMENT = sessionStorage.getItem("department");

  if (sessionStorage.getItem("location") != null)
    jsonObj.data.LOCATION = sessionStorage.getItem("location");

  await readBlob(blobObj).then((val) => {
    console.log(val);
    jsonObj.data.EMPLOYEE_IMAGE = btoa(val);
  });

  let headers = { "Content-Type": "application/json" };

  await axios
    .post("http://192.168.141.155:8080/attendance/ac/scanFingerPrint", jsonObj, headers)
    .then(
      (response) => {
        console.log(response.data.EMPLOYEE_ID);
      },
      (error) => {
        console.log(error);
      }
    );
}

video.addEventListener("playing", () => {
  let canvas = createCanvas();
  var id = setInterval(async () => {
    let detections = await faceapi.detectSingleFace(
      video,
      new faceapi.TinyFaceDetectorOptions()
    );

    //resizing our results according to the video element
    let resizedDetections = faceapi.resizeResults(detections, displaySize);

    //clearing canvas so that it doesn't come above video element
    canvas.getContext("2d").clearRect(0, 0, video.width, video.height);

    //drawing detections
    faceapi.draw.drawDetections(canvas, resizedDetections);

    try {
      if (resizedDetections._score >= 0.98) {
        video.pause();
        let box = resizedDetections._box;
        let ctx = canvas.getContext("2d");
        // let cropper = new Cropper(canvas, {
        //   autoCropArea:0,
        //   strict:false,
        //   guides:false,
        //   highlight:true,
        //   dragCrop:false,
        //   cropBoxResizble:false,
        //   data:{
        //     width:box._width,
        //     height:box._height,
        //     x:box._x,
        //     y:box._y
        //   },crop(event){
        //     console.log(event.detail.width);
        //     console.log(event.detail.height);
        //   }
        // })
        // console.log(cropper.getCroppedCanvas().toDataURL('image/png'));

        //cropping and resizing the image
        ctx.drawImage(
          video,
          box._x,
          box._y,
          box._width,
          box._height,
          box._x,
          box._y,
          box._width,
          box._height
        );
        let imgData = ctx.getImageData(box._x,
          box._y,
          box._width,
          box._height);
         canvas.width = box._width;
         canvas.height = box._height;
         ctx.putImageData(imgData,0,0);
        // ctx.beginPath();
        // ctx.rect(box._x, box._y, box._width, box._height);
        // ctx.clip();
        // ctx.clearRect(0,0, displaySize.width, displaySize.height);
        // ctx.restore();
        // ctx.closePath();
        // console.log(canvas.toDataURL('image/jpeg'));

         canvas.toBlob((obj) => {
          console.log(obj);
          saveUserImage(obj);
        }, "image/jpeg");

        video.src = "";
        localstream.getTracks()[0].stop();
        video.remove();

        clearInterval(id);
      }
    } catch (err) {}
  }, 100);
});

function readBlob(input) {
  const file = new File([input], "image", { type: input.type });
  return new Promise((resolve, _) => {
    fr = new FileReader();

    fr.onloadend = () => resolve(fr.result);
    fr.readAsBinaryString(file);
    console.log(fr.result);
  });
}
