<%@ include file="../common/common-css.jsp" %>
<jsp:include page="../common/navbar.jsp" />

<div class="container mt-5">
    <div class="card shadow-sm border-0">
        <div class="card-header bg-primary text-white fw-bold">
            Add New Category
        </div>

        <div class="card-body">
            <form method="post" action="<%=request.getContextPath()%>/admin/add-category">

                <div class="mb-3">
                    <label class="form-label fw-semibold">Category Name</label>
                    <input type="text"
                           name="name"
                           class="form-control"
                           placeholder="e.g. Electronics"
                           required />
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">Description</label>
                    <textarea name="description"
                              class="form-control"
                              rows="3"
                              placeholder="Category description"></textarea>
                </div>

                <button type="submit" class="btn btn-success w-100">
                    Add Category
                </button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp" />
