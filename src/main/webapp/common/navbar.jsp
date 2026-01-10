<%@ page import="com.example.model.User" %>
<%
    User user = (User) session.getAttribute("currentUser");
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm px-4">
    <a class="navbar-brand fw-bold" href="<%= request.getContextPath() %>/customer/home.jsp">
        <i class="fa-solid fa-store me-2"></i>MyShop
    </a>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="mainNavbar">
        <ul class="navbar-nav ms-auto align-items-lg-center gap-lg-2">

            <% if (user == null) { %>
                <!-- Not logged in -->
                <li class="nav-item">
                    <a class="nav-link" href="<%= request.getContextPath() %>/login.jsp">
                        <i class="fa-solid fa-right-to-bracket me-1"></i>Login
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="<%= request.getContextPath() %>/register.jsp">
                        <i class="fa-solid fa-user-plus me-1"></i>Register
                    </a>
                </li>

            <% } else { %>

                <%-- USER ROLE --%>
                <% if ("USER".equals(user.getRole())) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/view-products">
                            <i class="fa-solid fa-box-open me-1"></i>Products
                        </a>
                    </li>

                   <li class="nav-item">
    <a class="nav-link" href="<%= request.getContextPath() %>/view-cart">
        <i class="fa-solid fa-cart-shopping me-1"></i>Cart
    </a>
</li>

                <% } %>

                <%-- ADMIN ROLE --%>
                <% if ("ADMIN".equals(user.getRole())) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/admin/dashboard.jsp">
                            <i class="fa-solid fa-gauge-high me-1"></i>Dashboard
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/admin/add-product.jsp">
                            <i class="fa-solid fa-plus me-1"></i>Add Product
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/view-products">
                            <i class="fa-solid fa-boxes-stacked me-1"></i>Products
                        </a>
                    </li>
                <% } %>

                <!-- USER DROPDOWN -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle fw-semibold" href="#" role="button" data-bs-toggle="dropdown">
                        <i class="fa-solid fa-circle-user me-1"></i>
                        <%= user.getUsername().substring(0,1).toUpperCase() + user.getUsername().substring(1) %>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li>
                            <span class="dropdown-item-text text-muted small">
                                Role: <%= user.getRole() %>
                            </span>
                        </li>
                        <li><hr class="dropdown-divider"></li>
                        <li>
                            <a class="dropdown-item text-danger" href="<%= request.getContextPath() %>/logout">
                                <i class="fa-solid fa-right-from-bracket me-1"></i>Logout
                            </a>
                        </li>
                    </ul>
                </li>

            <% } %>
        </ul>
    </div>
</nav>
