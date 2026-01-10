<%@ include file="../common/common-css.jsp" %>
<jsp:include page="../common/navbar.jsp" />

<div class="container mt-5">
    <div class="card shadow-sm border-0">
        <div class="card-header bg-primary text-white fw-bold">
            <i class="fa-solid fa-plus me-2"></i>Add New Product
        </div>
        <div class="card-body">
            <!-- IMPORTANT: multipart/form-data for image upload -->
            <form method="post"
                  action="<%=request.getContextPath()%>/admin/add-product"
                  enctype="multipart/form-data">

                <div class="mb-3">
                    <label class="form-label fw-semibold">Product Name</label>
                    <input type="text"
                           name="name"
                           class="form-control shadow-sm"
                           placeholder="Enter product name (e.g. iPhone 15)"
                           required />
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">Description</label>
                    <textarea name="description"
                              class="form-control shadow-sm"
                              placeholder="Enter product description"
                              rows="3"></textarea>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">Price</label>
                    <input type="number"
                           name="price"
                           class="form-control shadow-sm"
                           step="0.01"
                           placeholder="Enter price (e.g. 49999.99)"
                           required />
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">Quantity</label>
                    <input type="number"
                           name="quantity"
                           class="form-control shadow-sm"
                           placeholder="Available stock"
                           required />
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">Product Image</label>
                    <input type="file"
                           name="image"
                           class="form-control shadow-sm"
                           accept="image/*"
                           required />
                </div>
                
                <div class="mb-3">
    <label class="form-label fw-semibold">Category</label>
    <select name="categoryId" class="form-control shadow-sm" required>
        <option value="">-- Select Category --</option>
        <%
            com.example.dao.CategoryDAO categoryDAO = new com.example.dao.CategoryDAO();
            java.util.List<com.example.model.Category> categories = categoryDAO.getAllCategories();
            for(com.example.model.Category cat : categories) {
        %>
            <option value="<%=cat.getId()%>"><%=cat.getName()%></option>
        <%
            }
        %>
    </select>
</div>
                

                <button type="submit" class="btn btn-success btn-lg w-100">
                    <i class="fa-solid fa-circle-plus me-1"></i> Add Product
                </button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp" />
