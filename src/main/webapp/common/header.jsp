<%@ page import="com.example.model.User" %>

<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">OnlineShoppingCart</a>

        <%
            User u = (User) session.getAttribute("currentUser");
            if(u != null) {
        %>
            <span class="text-white">Welcome, <%= u.getUsername() %></span>
            <a href="logout" class="btn btn-danger btn-sm ms-2">Logout</a>
        <%
            }
        %>
    </div>
</nav>
