<%@ page import="com.example.dao.ProductDAO, com.example.model.Product" %>
<%@ include file="../common/common-css.jsp" %>
<jsp:include page="../common/navbar.jsp" />

<%
    int id = 0;
    Product product = null;

    try {
        id = Integer.parseInt(request.getParameter("id"));
        ProductDAO dao = new ProductDAO();
        product = dao.getProductById(id);
    } catch (Exception e) {
        e.printStackTrace();
    }

    if(product == null){
%>
    <div class="container mt-5 text-center">
        <h3>Product not found!</h3>
        <a href="<%=request.getContextPath()%>/admin/all-products.jsp" class="btn btn-primary mt-3">Go Back</a>
    </div>
<%
    } else {
%>

<div class="container mt-5">
    <h3 class="text-center mb-4">Edit Product</h3>

    <form action="<%=request.getContextPath()%>/admin/update-product" method="post" enctype="multipart/form-data">
        <!-- Hidden field to send product ID -->
        <input type="hidden" name="id" value="<%=product.getId()%>">

        <div class="mb-3">
            <label for="name" class="form-label">Product Name</label>
            <input type="text" name="name" id="name" class="form-control" 
                   value="<%=product.getName()%>" required>
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea name="description" id="description" class="form-control" rows="4" required><%=product.getDescription()%></textarea>
        </div>

        <div class="mb-3">
            <label for="price" class="form-label">Price</label>
            <input type="number" name="price" id="price" class="form-control" 
                   value="<%=product.getPrice()%>" step="0.01" required>
        </div>

        <div class="mb-3">
            <label for="quantity" class="form-label">Quantity</label>
            <input type="number" name="quantity" id="quantity" class="form-control" 
                   value="<%=product.getQuantity()%>" required>
        </div>

        <div class="mb-3">
            <label for="imagePath" class="form-label">Product Image Path</label>
            <input type="text" name="imagePath" id="imagePath" class="form-control"
                   value="<%=product.getImagePath()%>" required>
            <small class="text-muted">You can upload a new image later or keep the same path</small>
        </div>
        
        <div class="mb-3">
    <label class="form-label fw-semibold">Category</label>
    <select name="categoryId" class="form-control shadow-sm" required>
        <option value="">-- Select Category --</option>
        <%
            com.example.dao.CategoryDAO categoryDAO = new com.example.dao.CategoryDAO();
            java.util.List<com.example.model.Category> categories = categoryDAO.getAllCategories();
            for(com.example.model.Category cat : categories) {
                String selected = (cat.getId() == product.getCategoryId()) ? "selected" : "";
        %>
            <option value="<%=cat.getId()%>" <%=selected%>><%=cat.getName()%></option>
        <%
            }
        %>
    </select>
</div>
        

        <div class="text-center">
            <button type="submit" class="btn btn-success">Update Product</button>
            <a href="<%=request.getContextPath()%>/admin/all-products.jsp" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>

<% } %>

<jsp:include page="../common/footer.jsp" />
