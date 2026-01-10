<%@ page import="java.util.*, com.example.model.User" %>
<%@ include file="../common/common-css.jsp" %>
<jsp:include page="../common/navbar.jsp" />

<%
    List<User> users = (List<User>) request.getAttribute("users");
    int currentPage = (Integer) request.getAttribute("currentPage");
    int totalPages = (Integer) request.getAttribute("totalPages");
    String search = (String) request.getAttribute("search");
%>

<div class="container mt-4">

    <h3 class="text-center mb-4">Customers List</h3>

    <!-- 🔍 Search Form -->
    <form method="get"
          action="<%=request.getContextPath()%>/admin/view-customers"
          class="d-flex mb-3">

        <input type="text"
               name="search"
               class="form-control me-2"
               placeholder="Search by username"
               value="<%=search%>"/>

        <button class="btn btn-primary">Search</button>
    </form>

    <!-- 📋 Users Table -->
    <div class="table-responsive">
        <table class="table table-bordered table-hover text-center">
            <thead class="table-dark">
                <tr>
    <th>ID</th>
    <th>Username</th>
    <th>Role</th>
    <th>Action</th>
</tr>

            </thead>
            <tbody>

            <%
                if (users == null || users.isEmpty()) {
            %>
                <tr>
                    <td colspan="4">No users found</td>
                </tr>
            <%
                } else {
                    for (User u : users) {
            %>
               <tr>
    <td><%=u.getId()%></td>
    <td><%=u.getUsername()%></td>
    <td><%=u.getRole()%></td>

    <td>
        <a href="<%=request.getContextPath()%>/admin/delete-user?id=<%=u.getId()%>"
           class="btn btn-danger btn-sm"
           onclick="return confirm('Are you sure you want to delete this user?');">
            Delete
        </a>
    </td>
</tr>

            <%
                    }
                }
            %>

            </tbody>
        </table>
    </div>

    <!-- 📄 Pagination -->
    <nav>
        <ul class="pagination justify-content-center">

            <%
                for (int i = 1; i <= totalPages; i++) {
            %>
                <li class="page-item <%= (i == currentPage) ? "active" : "" %>">
                    <a class="page-link"
                       href="<%=request.getContextPath()%>/admin/view-customers?page=<%=i%>&search=<%=search%>">
                        <%=i%>
                    </a>
                </li>
            <%
                }
            %>

        </ul>
    </nav>

</div>

<jsp:include page="../common/footer.jsp" />
