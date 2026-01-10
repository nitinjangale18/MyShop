<%@ page import="com.example.model.Product" %>
<%@ include file="../common/common-css.jsp" %>
<jsp:include page="../common/navbar.jsp" />

<%
    Product product = (Product) request.getAttribute("product");
%>

<div class="container mt-5">
    <div class="row">
        <!-- Product Image -->
        <div class="col-md-6 text-center">
            <img src="<%=request.getContextPath()%>/<%=product.getImagePath()%>"
                 class="img-fluid"
                 style="max-height:400px; object-fit:contain; background:#f8f9fa;">
        </div>

        <!-- Product Details -->
        <div class="col-md-6">
            <h3><%= product.getName() %></h3>
            <p class="text-muted"><%= product.getDescription() %></p>
            <h4>&#8377; <%= product.getPrice() %></h4>
            <p>Available Quantity: <%= product.getQuantity() %></p>

            <!-- Add to Cart -->
            <a href="<%=request.getContextPath()%>/add-to-cart?id=<%=product.getId()%>"
               class="btn btn-primary me-2">
                <i class="fa fa-cart-plus"></i> Add to Cart
            </a>

            <!-- Buy Now → go directly to Checkout with single product -->
            <form method="post" action="<%=request.getContextPath()%>/checkout-single" style="display:inline;">
                <input type="hidden" name="productId" value="<%=product.getId()%>">
                <input type="hidden" name="quantity" value="1">
                <button type="submit" class="btn btn-success">
                    Buy Now
                </button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp" />
