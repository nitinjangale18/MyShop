<%@ include file="../common/common-css.jsp" %>
<jsp:include page="../common/navbar.jsp" />

<style>
    html, body {
        height: 100%;
        margin: 0;
        font-family: Arial, sans-serif;
        background-color: #f5f6fa;
        display: flex;
        flex-direction: column;
    }

    .dashboard-container {
        flex: 1;
        padding: 40px 20px;
        max-width: 1200px;
        margin: 0 auto;
    }

    .dashboard-container h2 {
        text-align: center;
        margin-bottom: 40px;
        color: #333;
        font-size: 32px;
    }

    .dashboard-cards {
        display: flex;
        flex-wrap: wrap;
        gap: 25px;
        justify-content: center;
    }

    .card {
        flex: 1 1 250px;
        max-width: 300px;
        background-color: #fff;
        border-radius: 15px;
        box-shadow: 0 6px 15px rgba(0,0,0,0.1);
        padding: 25px 20px;
        text-align: center;
        transition: transform 0.3s, box-shadow 0.3s;
        cursor: pointer;
    }

    .card:hover {
        transform: translateY(-8px);
        box-shadow: 0 10px 20px rgba(0,0,0,0.15);
    }

    .card h3 {
        margin-bottom: 20px;
        color: #007bff;
        font-size: 22px;
    }

    .card p {
        color: #555;
        margin-bottom: 20px;
        font-size: 15px;
        line-height: 1.5;
    }

    .card a {
        text-decoration: none;
        padding: 10px 20px;
        background-color: #007bff;
        color: #fff;
        border-radius: 8px;
        font-weight: bold;
        display: inline-block;
        transition: background-color 0.3s;
    }

    .card a:hover {
        background-color: #0056b3;
    }

    jsp\\:include + footer, footer {
        margin-top: auto;
    }
</style>

<div class="dashboard-container">
    <h2>Admin Dashboard</h2>

    <div class="dashboard-cards">

        <!-- Category Management -->
        <div class="card">
            <h3> Categories</h3>
            <p>Create, edit, or delete product categories like Mobile, Laptop, Accessories.</p>
            <a href="<%=request.getContextPath()%>/admin/view-categories">Go</a>
        </div>

        <!-- Customer Management -->
       <!-- Customer Management -->
			<div class="card">
			    <h3>Customers</h3>
			    <p>View and manage registered customers of your store.</p>
			    <a href="<%=request.getContextPath()%>/admin/view-customers">Go</a>
			</div>



        <!-- Coupons & Discounts -->
        <div class="card">
            <h3>Coupons & Discounts</h3>
            <p>Create, edit, or delete promo codes and manage seasonal offers.</p>
            <a href="manage-coupons.jsp">Go</a>
        </div>

        <!-- Add Category -->
        <div class="card">
            <h3>Add Category</h3>
            <p>Add new product categories to organize your store better.</p>
            <a href="<%=request.getContextPath()%>/admin/add-category.jsp">Go</a>
        </div>
        
        <div class="card">
    <h3>Track Orders</h3>
    <p>View all orders placed by customers and their details.</p>
    <a href="<%=request.getContextPath()%>/admin/view-orders">Go</a>
     </div>
        

    </div>
</div>

<jsp:include page="../common/footer.jsp" />
