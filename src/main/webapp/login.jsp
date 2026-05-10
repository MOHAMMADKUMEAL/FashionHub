<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Login - FashionHub</title>

<link rel="stylesheet"
      href="css/style.css">

<style>

body{

    margin: 0;

    font-family: Arial, sans-serif;

    overflow: hidden;
}

.auth-container{

    width: 100%;

    height: 100vh;

    display: flex;

    justify-content: center;

    align-items: center;

    background:
    linear-gradient(
    135deg,
    rgba(15,23,42,0.95),
    rgba(30,58,138,0.9),
    rgba(59,130,246,0.8)),

    url("images/banner.jpg");

    background-size: cover;

    background-position: center;
}

.auth-box{

    width: 420px;

    background: rgba(255,255,255,0.12);

    backdrop-filter: blur(18px);

    border: 1px solid rgba(255,255,255,0.2);

    padding: 40px;

    border-radius: 25px;

    box-shadow: 0 10px 30px rgba(0,0,0,0.35);

    transition: 0.4s;
}

.auth-box:hover{

    transform: translateY(-5px);
}

.auth-box h1{

    text-align: center;

    margin-bottom: 30px;

    color: white;

    font-size: 38px;
}

.input-group{

    margin-bottom: 20px;
}

.input-group label{

    display: block;

    margin-bottom: 8px;

    font-weight: bold;

    color: white;
}

.input-group input{

    width: 100%;

    padding: 14px;

    border: none;

    border-radius: 12px;

    outline: none;

    background: rgba(255,255,255,0.9);

    transition: 0.3s;
}

.input-group input:focus{

    box-shadow: 0 0 10px rgba(255,255,255,0.5);
}

.error-box{

    background-color: rgba(220,38,38,0.15);

    color: #fecaca;

    padding: 14px;

    border-radius: 10px;

    margin-bottom: 20px;

    text-align: center;

    font-weight: bold;

    border: 1px solid rgba(254,202,202,0.4);
}

.btn{

    width: 100%;

    padding: 14px;

    border: none;

    border-radius: 12px;

    background: linear-gradient(
                to right,
                #2563eb,
                #3b82f6);

    color: white;

    font-size: 16px;

    font-weight: bold;

    cursor: pointer;

    margin-top: 10px;

    transition: 0.3s;
}

.btn:hover{

    transform: scale(1.02);

    background: linear-gradient(
                to right,
                #1e40af,
                #2563eb);
}

.auth-footer{

    text-align: center;

    margin-top: 20px;

    color: white;
}

.auth-footer a{

    color: #93c5fd;

    text-decoration: none;

    font-weight: bold;
}

</style>

</head>

<body>

<div class="auth-container">

    <div class="auth-box">

        <h1>Login</h1>

        <%
            String error =
                request.getParameter("error");
        %>

        <%
            if(error != null){
        %>

        <div class="error-box">

            <%= error %>

        </div>

        <%
            }
        %>

        <form action="login"
              method="post">

            <div class="input-group">

                <label>Email</label>

                <input type="email"
                       name="email"

                       placeholder="Enter Email"
                       required>

            </div>

            <div class="input-group">

                <label>Password</label>

                <input type="password"
                       name="password"

                       placeholder="Enter Password"
                       required>

            </div>

            <button class="btn">

                Login

            </button>

        </form>

        <div class="auth-footer">

            Don't have an account?

            <a href="register.jsp">

                Register

            </a>

        </div>

    </div>

</div>

</body>
</html>