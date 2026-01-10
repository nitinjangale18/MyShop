<%@ page import="com.example.dao.CategoryDAO, com.example.model.Category" %>

<%@ include file="../common/common-css.jsp" %>
<jsp:include page="../common/navbar.jsp" />

<%
    int id = Integer.parseInt(request.getParameter("id"));
    CategoryDAO dao = new CategoryDAO();
    Category category = dao.getCategoryById(id);
%>

<div class="container mt-5">
    <h3 class="text-center mb-4">Edit Category</h3>

    <form method="post"
          action="<%=request.getContextPath()%>/admin/update-category">

        <input type="hidden" name="id" value="<%=category.getId()%>">

        <div class="mb-3">
            <label class="form-label">Category Name</label>
            <input type="text"
                   name="name"
                   class="form-control"
                   value="<%=category.getName()%>"
                   required />
        </div>

        <div class="mb-3">
            <label class="form-label">Description</label>
            <textarea name="description"
                      class="form-control"
                      rows="3"><%=category.getDescription()%></textarea>
        </div>

        <button type="submit" class="btn btn-success">Update</button>
        <a href="<%=request.getContextPath()%>/admin/view-categories.jsp"
           class="btn btn-secondary">Cancel</a>
    </form>
</div>

<jsp:include page="../common/footer.jsp" />
