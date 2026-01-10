<%@ page session="true" %>
<%@ page import="com.example.model.User" %>

<%
    User user = (User) session.getAttribute("currentUser");
    if(user == null || !"ADMIN".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<%@ include file="common/common-css.jsp" %>
<%@ include file="common/header.jsp" %>

<style>
    .admin-box {
        border: 1px solid #ddd;
        border-radius: 10px;
        padding: 30px;
        text-align: center;
        transition: 0.3s;
        background: #fff;
    }

    .admin-box:hover {
        box-shadow: 0 0 15px rgba(0,0,0,0.15);
    }

    .admin-icon {
        width: 120px;
        height: 20px;
        margin-bottom: 15px;
    }
</style>

<div class="container mt-4">

    <div class="card shadow">
        <div class="card-body">

            <h3 class="text-center mb-2">Admin Dashboard</h3>
            <p class="text-center text-muted">
                Welcome, <strong><%= user.getUsername() %></strong>
            </p>

            <hr>

            <div class="row mt-4">

                <!-- Add Product -->
                <div class="col-md-4">
                    <div class="admin-box">
<i class="fa-solid fa-box admin-icon text-primary"></i>
                        <br>
                        <a href="#" class="btn btn-primary w-100">Add Product</a>
                    </div>
                </div>

                <!-- View Users -->
                <div class="col-md-4">
                    <div class="admin-box">
<i class="fa-solid fa-users admin-icon text-warning"></i>
                        <br>
                        <a href="#" class="btn btn-warning w-100">View Users</a>
                    </div>
                </div>

                <!-- View Orders -->
                <div class="col-md-4">
                    <div class="admin-box">
<i class="fa-solid fa-cart-shopping admin-icon text-info"></i>
                        <br>
                        <a href="#" class="btn btn-info w-100">View Orders</a>
                    </div>
                </div>

            </div>

        </div>
    </div>

</div>

<%@ include file="common/footer.jsp" %>
