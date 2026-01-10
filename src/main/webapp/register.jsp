<%@ include file="common/common-css.jsp" %>
<jsp:include page="common/navbar.jsp" />

<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-4">

            <div class="card shadow">
                <div class="card-header bg-success text-white text-center">
                    <h4>Register</h4>
                </div>

                <div class="card-body">
                    <form method="post" action="register">

                        <div class="mb-3">
                            <label>Username</label>
                            <input type="text" name="username" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label>Password</label>
                            <input type="password" name="password" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label>Role</label>
                            <select name="role" class="form-select">
                                <option value="USER">User</option>
                                <option value="ADMIN">Admin</option>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-success w-100">
                            Register
                        </button>
                    </form>

                    <div class="text-center mt-3">
                        <a href="login.jsp">Already have an account? Login</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>
