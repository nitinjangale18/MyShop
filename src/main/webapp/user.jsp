<%@ page session="true" %>
<%@ page import="com.example.model.User" %>

<%
    User user = (User) session.getAttribute("currentUser");
    if(user == null || !"USER".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<%@ include file="common/common-css.jsp" %>
<%@ include file="common/header.jsp" %>

<div class="container mt-4">
    <div class="card shadow">
        <div class="card-body">
            <h3 class="card-title">User Dashboard</h3>
            <p class="card-text">
                Welcome, <strong><%= user.getUsername() %></strong>
            </p>

            <hr>

            <a href="#" class="btn btn-primary">View Products</a>
            <a href="#" class="btn btn-success">View Cart</a>
        </div>
    </div>
</div>

<%@ include file="common/footer.jsp" %>
