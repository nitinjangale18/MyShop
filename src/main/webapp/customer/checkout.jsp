<%@ page import="java.util.*, com.example.model.CartItem" %>
<%@ include file="../common/common-css.jsp" %>
<jsp:include page="../common/navbar.jsp" />

<%
    List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems");
    double totalAmount = (double) request.getAttribute("totalAmount");
%>

<div class="container mt-4">
    <h3 class="text-center mb-4">Checkout</h3>

    <div class="row">
        <!-- Cart Summary -->
        <div class="col-md-6">
            <h5>Order Summary</h5>
            <ul class="list-group mb-3">
                <%
                    for (CartItem item : cartItems) {
                        double total = item.getProduct().getPrice() * item.getQuantity();
                %>
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <%= item.getProduct().getName() %> x <%= item.getQuantity() %>
                    <span> <%= total %></span>
                </li>
                <%
                    }
                %>
                <li class="list-group-item d-flex justify-content-between">
                    <strong>Total</strong>
                    <strong> <%= totalAmount %></strong>
                </li>
            </ul>
        </div>

        <!-- Payment Form -->
        <div class="col-md-6">
            <h5>Payment Details</h5>
            <form method="post" action="<%=request.getContextPath()%>/payment">
                <div class="mb-3">
                    <label>Cardholder Name</label>
                    <input type="text" name="cardName" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label>Card Number</label>
                    <input type="text" name="cardNumber" class="form-control" maxlength="16" required>
                </div>

                <div class="mb-3">
                    <label>Expiry Date (MM/YY)</label>
                    <input type="text" name="expiry" class="form-control" placeholder="MM/YY" required>
                </div>

                <div class="mb-3">
                    <label>CVV</label>
                    <input type="password" name="cvv" class="form-control" maxlength="3" required>
                </div>

                <button type="submit" class="btn btn-success w-100 mt-2">
                    Pay  <%= totalAmount %>
                </button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp" />
