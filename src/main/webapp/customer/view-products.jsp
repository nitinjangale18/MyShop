<%@ page import="java.util.*, com.example.model.Product" %>

<%@ include file="../common/common-css.jsp" %>
<jsp:include page="../common/navbar.jsp" />

<%
    List<Product> products = (List<Product>) request.getAttribute("products");
%>

<div class="container mt-4">
    <h3 class="text-center mb-4">Products</h3>
    
    
    <form method="get"
      action="<%=request.getContextPath()%>/view-products"
      class="d-flex mb-4">

    <input type="text"
           name="search"
           class="form-control me-2"
           placeholder="Search product"
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

                   <div class="mt-auto d-flex justify-content-between align-items-center">
    <span class="btn btn-outline-primary fw-bold">
       &#8377; <%= p.getPrice() %>
    </span>

  <div>
    <!-- View Details button -->
    <a href="<%=request.getContextPath()%>/product-detail?id=<%=p.getId()%>"
       class="btn btn-primary btn-sm me-1">
        View
    </a>

    <!-- Add to Cart button -->
    <a href="<%=request.getContextPath()%>/add-to-cart?id=<%=p.getId()%>"
       class="btn btn-primary btn-sm">
        <i class="fa fa-cart-plus"></i> Add to Cart
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
