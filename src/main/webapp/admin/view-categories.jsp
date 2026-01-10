<%@ page import="java.util.*, com.example.model.Category" %>

<%@ include file="../common/common-css.jsp" %>
<jsp:include page="../common/navbar.jsp" />




<%
    String msg = (String) session.getAttribute("msg");
    if (msg != null) {
%>
    <div class="alert alert-warning text-center mt-3">
        <%= msg %>
    </div>
<%
        session.removeAttribute("msg");
    }
%>


<%
    List<Category> categories =
        (List<Category>) request.getAttribute("categories");
%>

<div class="container mt-4">
    <h3 class="text-center mb-4">All Categories</h3>

    <div class="table-responsive">
        <table class="table table-bordered table-hover text-center">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Actions</th>
                </tr>
            </thead>

            <tbody>
            <%
                if (categories == null || categories.isEmpty()) {
            %>
                <tr>
                    <td colspan="4">No categories found</td>
                </tr>
            <%
                } else {
                    for (Category c : categories) {
            %>
                <tr>
                    <td><%=c.getId()%></td>
                    <td><%=c.getName()%></td>
                    <td><%=c.getDescription()%></td>
                    <td>
                        <a href="<%=request.getContextPath()%>/admin/edit-category.jsp?id=<%=c.getId()%>"
                           class="btn btn-warning btn-sm">Edit</a>

                        <a href="<%=request.getContextPath()%>/admin/delete-category?id=<%=c.getId()%>"
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('Delete this category?');">
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
</div>

<jsp:include page="../common/footer.jsp" />
