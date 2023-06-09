<%@ page language="java" contentType="text/html; charset =UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feed3</title>
    <link rel="stylesheet" href="style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.15.4/css/fontawesome.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.15.4/css/fontawesome.min.css" integrity="sha384-jLKHWM3JRmfMU0A5x5AkjWkw/EYfGUAGagvnfryNV3F9VqM98XiIH7VBGVoxVSc7" crossorigin="anonymous">

 <!-- Required meta tags -->
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">

 <!-- Bootstrap CSS -->
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <section class="header">
        <nav>
            <a href="index.html"><img src="images/logo.png"></a>
            <div class="nav-links" id="navLinks">
                <i class="fa fa-times" onclick="hideMenu()"></i>
                <ul>
                    <li><a href="">HOME</a></li>
                    <li><a href="">ATTENDANCE</a></li>
                    <li><a href="user.html">USER</a></li>
                    <li><a href="admin.html">ADMIN</a></li>
                </ul>
            </div>
            <i class="fa fa-bars" onclick="showMenu()"></i>
        </nav>
        <div class="text-box">
            <h1>ATTENDANCE SYSTEM</h1>
            <p>Hey, Well wishes from Us. Our site offers you the free database that will store your organisation employee's or student's attendance safely.<br> This will decrease the paper work and help in decreasing the use of paper which cause a positive change in environment.  </p>
            <!-- <a href="" class="hero-btn">Explore to know more!</a> -->
        </div>
        
    </section>

    <!-- ------- course ------- -->
    <!-- <section class="course">
        <h1>Facilites We Offer</h1>
        <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Neque, quos.</p>

        <div class="row">
            <div class="column-1">
                <h3>Foodec</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corrupti natus fuga ex quos cupiditate magni nihil omnis, nulla sed ipsum.</p>
            </div>
            <div class="column-1">
                <h3>Utilities</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corrupti natus fuga ex quos cupiditate magni nihil omnis, nulla sed ipsum.</p>
            </div>
            <div class="column-1">
                <h3>Shopping</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corrupti natus fuga ex quos cupiditate magni nihil omnis, nulla sed ipsum.</p>
            </div>
        </div>
    </section> -->

    <section class="gadgets">
        <h1>Our Fabulous Gadgets</h1>
        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Eligendi, laborum.</p>
        <div class="row">
            <div class="gadgets-col">
                <img src="images/london.png">
                <div class="layer">
                    <h3>CHARTSHEET</h3>
                </div>
            </div>
            <div class="gadgets-col">
                <a href="user.html">
                    <img src="images/newyork.png" >
                
                <div class="layer">
                    <h3>USER</h3>
                </div>
                </a>
            </div>
            <div class="gadgets-col">
                <a href="admin.html">
                    <img src="images/washington.png" >
                
                <div class="layer">
                    <h3>ADMIN</h3>
                </div>
                </a>
            </div> 
        </div>
    </section>

    <!-- ---------facilities---------- -->
    <section class="facilities">
        <h1>Our Expertise</h1>
        <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ad, modi?</p>
        <div class="row">
             <div class="facility-col">
                 <img src="images/library.png">
                 <h3>World class content</h3>
                 <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Rem aliquam amet sint reprehenderit nisi eius necessitatibus nam ad voluptate. Facilis?</p>
             </div>
             <div class="facility-col">
                <img src="images/basketball.png">
                <h3>Widest Range of Product</h3>
                <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Rem aliquam amet sint reprehenderit nisi eius necessitatibus nam ad voluptate. Facilis?</p>
            </div>
            <div class="facility-col">
                <img src="images/cafeteria.png">
                <h3>Refreshments</h3>
                <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Rem aliquam amet sint reprehenderit nisi eius necessitatibus nam ad voluptate. Facilis?</p>
            </div>
        </div>
    </section>

    <!-- --------testimonial-------- -->
    <section class="testimonials">
        <h1>Our Reviews</h1>
        <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ad, modi?</p>
        <div class="row">
             <div class="testimonial-col">
                 <img src="images/user1.jpg">
                 <div>
                    <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Rem aliquam amet sint reprehenderit nisi eius necessitatibus nam ad voluptate. Facilis?</p>
                    <h3>Christine Lopez</h3>
                    <i class="fa fa-star"></i>
                    <i class="fa fa-star"></i>
                    <i class="fa fa-star"></i>
                    <i class="fa fa-star"></i>
                    <i class="fa fa-star-o"></i>
                 </div>
             </div>
             <div class="testimonial-col">
                <img src="images/user2.jpg">
                <div>
                    <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Rem aliquam amet sint reprehenderit nisi eius necessitatibus nam ad voluptate. Facilis?</p>
                <h3>David Dhawan</h3>
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star-half-o"></i>
                </div>
            </div>
        </div> 
    </section>

    <!-- -------Call To Action-------- -->
    <section class="cta">
        <h1>Enroll To Our Platform For <br> The Hillarious Experience</h1>
        <a href="" class="hero-btn">CONTACT US</a>

    </section>

    <!-- -------Footer-------- -->
    <section class="footer">
        <h4>About Us</h4>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum nemo ex tempore perferendis, quisquam cumque maiores<br> voluptas perspiciatis aspernatur nesciunt autem aperiam optio minima harum id? Reprehenderit dignissimos blanditiis eveniet.</p>
        <div class="icons">
            <i class="fa fa-facebook"></i>
            <i class="fa fa-twitter"></i>
            <i class="fa fa-instagram"></i>
            <i class="fa fa-linkedin"></i>
        </div>
        <p>Made with <i class="fa fa-heart-o"> Love by Yash</i></p>
    </section>
    
    <!-- ------------Javascript for toggle manu-------------->
    <script>
        var navLinks = document.getElementById("navLinks");

        function showMenu(){
            navLinks.style.right = "0";
        } 
        function hideMenu(){
            navLinks.style.right = "-200px";
        } 
    </script>
    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    -->
</body>
</html>