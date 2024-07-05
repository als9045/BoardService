<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <style>
        /* 기본 스타일링 */
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }

        #wrapper {
            display: flex;
        }

        #sidebar-wrapper {
            width: 250px;
            height: 100%;
            overflow-y: auto;
            overflow-x: hidden;
            padding-top: 20px;
            z-index: 1000; /* 사이드바가 페이지 컨텐츠 위로 나오도록 설정 */
            position: fixed;
            background-color: #f8f9fa; /* 적절한 배경색 설정 */
        }

        .navbar {
            z-index: 999;
        }

        #menu-toggle {
            float: right;
        }

        #content {
            margin-left: 250px; /* 사이드바 너비만큼 오른쪽으로 밀어줌 */
            padding: 20px;
        }

        /* 추가적인 스타일링을 원할 경우 여기에 추가 */
    </style>
</head>
<body>

<div id="wrapper">

    <!-- Include Sidebar -->
    <%@ include file="/WEB-INF/views/layout/sidebar.jsp" %>

    <!-- Main Content -->
    <div id="content">
        <div class="container">
            <div style="display: flex; align-items: center;">
                <h1 class="mt-4" style="margin-right: 10px;">Main Page</h1>
                <a href="/board/register">
                    <button type="button" class="btn btn-outline-primary">REGISTER</button>
                </a>
            </div>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Title</th>
                    <th scope="col">Writer</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${result}</td>
                    <%-- <td>${dto.title}</td> --%>
                    <%-- <td>${dto.writerName} (${dto.writerEmail})</td> --%>
                </tr>
                </tbody>
            </table>
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
</script>

</body>
</html>
