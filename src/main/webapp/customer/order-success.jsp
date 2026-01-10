<%@ page import="java.util.*, com.example.model.CartItem" %>
<%@ include file="../common/common-css.jsp" %>
<jsp:include page="../common/navbar.jsp" />

<%
    int orderId = (int) request.getAttribute("orderId");
    List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems");
    double totalAmount = (double) request.getAttribute("totalAmount");
%>

<div class="container mt-4 text-center">
    <h3 class="text-success mb-4">Payment Successful!</h3>
    <p>Your Order ID: <strong>#<%= orderId %></strong></p>

    <h5 class="mt-4">Order Details:</h5>
    <div class="table-responsive">
        <table class="table table-bordered text-center align-middle">
            <thead class="table-dark">
                <tr>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Qty</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (CartItem item : cartItems) {
                        double total = item.getProduct().getPrice() * item.getQuantity();
                %>
                <tr>
                    <td><%= item.getProduct().getName() %></td>
                    <td> <%= item.getProduct().getPrice() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td> <%= total %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>

    <h4 class="mt-3">Grand Total:  <%= totalAmount %></h4>

    <a href="<%=request.getContextPath()%>/view-products" class="btn btn-primary mt-3">
        Continue Shopping
    </a>
</div>

<jsp:include page="../common/footer.jsp" />
