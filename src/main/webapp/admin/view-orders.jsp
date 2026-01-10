<%@ page import="java.util.*, com.example.model.Order" %>
<%@ include file="../common/common-css.jsp" %>
<jsp:include page="../common/navbar.jsp" />

<div class="container mt-4">
    <h3 class="text-center mb-4">All Orders</h3>

    <%
        List<Order> orders = (List<Order>) request.getAttribute("orders");
        if (orders == null || orders.isEmpty()) {
    %>
        <div class="text-center">
            <h5>No orders found</h5>
        </div>
    <%
        } else {
    %>
        <table class="table table-bordered text-center">
            <thead class="table-dark">
                <tr>
                    <th>Order ID</th>
                    <th>User ID</th>
                    <th>Total Amount</th>
                    <th>Payment Status</th>
                    <th>Order Date</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Order o : orders) {
                %>
                <tr>
                    <td><%= o.getId() %></td>
                    <td><%= o.getUserId() %></td>
                    <td>&#8377; <%= o.getTotalAmount() %></td>
                    <td><%= o.getPaymentStatus() %></td>
                    <td><%= o.getOrderDate() %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <%
        }
    %>
</div>

<jsp:include page="../common/footer.jsp" />
