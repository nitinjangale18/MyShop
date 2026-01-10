<%@ page import="java.util.*, com.example.model.Product" %>

<%@ include file="../common/common-css.jsp" %>
<jsp:include page="../common/navbar.jsp" />

<%
    List<Product> products = (List<Product>) request.getAttribute("products");
%>

<div class="container mt-4">
    <h3 class="text-center mb-4"> All Products</h3>
    
    <form method="get"
      action="<%=request.getContextPath()%>/view-products"
      class="d-flex mb-4">

    <input type="text"
           name="search"
           class="form-control me-2"
           placeholder="Search product by name"
           value="<%= request.getAttribute("search") != null ? request.getAttribute("search") : "" %>">

    <button class="btn btn-primary">Search</button>
</form>
    

    <div class="row g-4">
        <%
            if (products == null || products.isEmpty()) {
        %>
            <div class="col-12 text-center">
                <h5>No products available</h5>
            </div>
        <%
            } else {
                for (Product p : products) {
        %>

        <div class="col-lg-4 col-md-6">
            <div class="card h-100 shadow-sm">

                <img src="<%=request.getContextPath()%>/<%=p.getImagePath()%>"
     class="card-img-top img-fluid"
     style="height:220px; width:100%; object-fit:contain; background:#f8f9fa;">


                <div class="card-body d-flex flex-column">
                    <h5 class="card-title"><%= p.getName() %></h5>
                    <p class="card-text text-muted" style="font-size:14px;">
                        <%= p.getDescription() %>
                    </p>

                    <div class="mt-auto">
                      <div class="fw-bold mb-2">&#8377; <%= p.getPrice() %></div>


                        <div class="d-flex gap-2">
                        
                            <!-- Edit button -->
				<a href="<%=request.getContextPath()%>/admin/edit-product.jsp?id=<%=p.getId()%>"
				   class="btn btn-warning btn-sm w-50">
				   <i class="fa fa-edit"></i> Edit
				</a>

                    <!-- Delete button -->
						<a href="<%=request.getContextPath()%>/admin/delete-product?id=<%=p.getId()%>"
						   class="btn btn-danger btn-sm w-50"
						   onclick="return confirm('Delete this product?');">
						   <i class="fa fa-trash"></i> Delete
						</a>

                        </div>
                    </div>
                </div>

            </div>
        </div>

        <%
                }
            }
        %>
    </div>
</div>

<jsp:include page="../common/footer.jsp" />
