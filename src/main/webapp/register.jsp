<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Register - FashionHub</title>

<link rel="stylesheet"
      href="css/style.css">

<style>

.auth-container{

    width: 100%;

    min-height: 100vh;

    display: flex;

    justify-content: center;

    align-items: center;

    background: linear-gradient(
            to right,
            #2563eb,
            #1e40af);

    padding: 40px 0;
}

.auth-box{

    width: 420px;

    background-color: white;

    padding: 40px;

    border-radius: 25px;

    box-shadow: 0 10px 30px rgba(0,0,0,0.2);
}

.auth-box h1{

    text-align: center;

    margin-bottom: 30px;

    color: #2563eb;
}

.input-group{

    margin-bottom: 20px;
}

.input-group label{

    display: block;

    margin-bottom: 8px;

    font-weight: bold;

    color: #1e293b;
}

.input-group input{

    width: 100%;

    padding: 12px;

    border: 1px solid #cbd5e1;

    border-radius: 10px;

    outline: none;

    background-color: white;
}

.auth-footer{

    text-align: center;

    margin-top: 20px;
}

.auth-footer a{

    color: #2563eb;

    text-decoration: none;

    font-weight: bold;
}

</style>

</head>

<body>

<div class="auth-container">

    <div class="auth-box">

        <h1>Create Account</h1>

        <form action="register"
              method="post">

            <div class="input-group">

                <label>Full Name</label>

                <input type="text"
                       name="fullName"

                       placeholder="Enter Full Name"
                       required>

            </div>

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

                Register

            </button>

        </form>

        <div class="auth-footer">

            Already have an account?

            <a href="login.jsp">

                Login

            </a>

        </div>

    </div>

</div>

</body>
</html>