<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <style>
        /* 추가적인 스타일링이 필요한 경우 여기에 작성 */
    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/layout/sidebar.jsp" %>
<div class="container-fluid">
    <%@ include file="/WEB-INF/views/layout/top.jsp" %>
    <!-- Main Content -->
    <div id="content" class="mt-4">
        <div class="container">

            <div style="display: flex; align-items: center;">
                <h1 class="mb-4" style="margin-right: 10px;">Board Main Page</h1>
                <a href="/board/register" class="btn btn-outline-primary mb-4">REGISTER</a>
            </div>


            <form action="/board/list" method="get" id="searchForm">
            <div>
                <input type="hidden" name="page" value = "1">

                <select class="form-select" aria-label="Default select example"  name="type" >
                <option value="t" ${pageRequestDTO.type == 't' ? 'selected' : ''}>제목</option>
                <option value="c" ${pageRequestDTO.type == 'c' ? 'selected' : ''}>내용</option>
                <option value="w" ${pageRequestDTO.type == 'w' ? 'selected' : ''}>작성자</option>
                <option value="tc" ${pageRequestDTO.type == 'tc' ? 'selected' : ''}>제목 + 내용</option>
                <option value="tcw" ${pageRequestDTO.type == 'tcw' ? 'selected' : ''}>제목 + 내용 + 작성자</option>
            </select>

                <input type="text" name="keyword" style="width: 300px;"
                value="${pageRequestDTO.keyword}">
                <button type="button" class="btn btn-outline-secondary btn-search">Search</button>
                <button type="button" class="btn btn-outline-secondary btn-clear">Clear</button>


            </div>


            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Title</th>
                    <th scope="col">Writer</th>
                    <th scope="col">Date</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="dto" items="${result.dtoList}">
                    <tr>
                        <td><a href="/board/read?bno=${dto.bno}">${dto.bno}</a></td>
                        <td>${dto.title}</td>
                        <td>${dto.writerName}</td>
                        <td>${dto.regDate}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <ul class="pagination justify-content-center">
                <li class="page-item">
                    <c:choose>
                        <c:when test="${!result.prev}">
                            <a class="page-link" href="/board/list?page=${result.page - 1}&amp;type=${pageRequestDTO.type}&amp;keyword=${pageRequestDTO.keyword}">
                                Previous
                            </a>
                        </c:when>
                        <c:otherwise>
                            <span class="page-link">Previous</span>
                        </c:otherwise>
                    </c:choose>
                </li>

                <c:forEach var="page" items="${result.pageList}">
                    <li class="page-item ${result.page == page ? 'active' : ''}">
                        <a class="page-link" href="/board/list?page=${page}&amp;type=${pageRequestDTO.type}&amp;keyword=${pageRequestDTO.keyword}">
                                ${page}
                        </a>
                    </li>
                </c:forEach>

                <li class="page-item">
                    <c:choose>
                        <c:when test="${!result.next}">
                            <a class="page-link" href="/board/list?page=${result.end + 1}&amp;type=${pageRequestDTO.type}&amp;keyword=${pageRequestDTO.keyword}">
                                Next
                            </a>
                        </c:when>
                        <c:otherwise>
                            <span class="page-link">Next</span>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>


        </div>
    </div>

</div>

<!-- Bootstrap core JavaScript and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

    var searchForm = $("#searchForm");

    $('.btn-search').click(function(e){

        searchForm.submit();

    });

    $('.btn-clear').click(function(e){

        searchForm.empty().submit();

    });


</script>

</body>
</html>
