<%@ page import="java.util.*, com.example.model.CartItem" %>

<%@ include file="../common/common-css.jsp" %>
<jsp:include page="../common/navbar.jsp" />

<%
    List<CartItem> cartItems =
        (List<CartItem>) request.getAttribute("cartItems");

    double grandTotal = 0;
%>

<div class="container mt-4">
    <h3 class="text-center mb-4">My Cart</h3>

    <%
        if (cartItems == null || cartItems.isEmpty()) {
    %>
        <div class="text-center">
            <h5>Your cart is empty</h5>
            <a href="<%=request.getContextPath()%>/view-products"
               class="btn btn-primary mt-3">
                Continue Shopping
            </a>
        </div>
    <%
        } else {
    %>

    <div class="table-responsive">
        <table class="table table-bordered align-middle text-center">
            <thead class="table-dark">
                <tr>
                    <th>Product</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>Qty</th>
                    <th>Total</th>
                    <th>Remove</th>   <%-- NEW COLUMN --%>
                    
                </tr>
            </thead>

            <tbody>
                <%
                    for (CartItem item : cartItems) {
                        double total =
                            item.getProduct().getPrice()
                            * item.getQuantity();
                        grandTotal += total;
                %>
                <tr>
                    <td><%= item.getProduct().getName() %></td>

                    <td>
                        <img src="<%=request.getContextPath()%>/<%= item.getProduct().getImagePath() %>"
                             style="height:80px;">
                    </td>

                    <td><%= item.getProduct().getPrice() %></td>

					<td>
					   <a href="<%=request.getContextPath()%>/cart-action?action=dec&id=<%= item.getId() %>"
   class="btn btn-sm btn-outline-secondary">-</a>

					
					    <span class="mx-2 fw-bold"><%= item.getQuantity() %></span>
					
					    <a href="<%=request.getContextPath()%>/cart-action?action=inc&id=<%= item.getId() %>"
					       class="btn btn-sm btn-outline-secondary">+</a>
					</td>

                    <td> <%= total %></td>
                    
                    <td>
					    <a href="<%=request.getContextPath()%>/cart-action?action=remove&id=<%= item.getId() %>"
					       class="btn btn-sm btn-danger">
					        <i class="fa fa-trash"></i>
					    </a>
					</td>
                    
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>

    <div class="text-end mt-3">
        <h4>Total Amount: <%= grandTotal %></h4>
        <form method="get" action="<%=request.getContextPath()%>/checkout">
    <button type="submit" class="btn btn-success mt-2">
        Proceed to Checkout
    </button>
</form>

    </div>

    <%
        }
    %>
</div>

<jsp:include page="../common/footer.jsp" />
