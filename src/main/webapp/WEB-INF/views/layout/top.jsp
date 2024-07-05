<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-07-05
  Time: 오후 1:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <style>
    /* 추가적인 CSS 스타일링을 원할 경우 여기에 추가 */
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
      position: fixed;
      height: 100%;
      overflow-y: auto;
      overflow-x: hidden;
      padding-top: 20px;
      z-index: 1000; /* 사이드바가 페이지 컨텐츠 위로 나오도록 설정 */
    }

    #page-content-wrapper {
      width: 100%;
      padding: 20px;
      transition: margin-left 0.3s ease;
      margin-left: 250px; /* 초기에 사이드바와 동일한 넓이로 설정 */
    }

    .navbar {
      z-index: 999;
    }

    #menu-toggle {
      float: right;
    }

    /* 추가적인 스타일링을 원할 경우 여기에 추가 */
  </style>
    <title>Title</title>
</head>
<body>
<div id="page-content-wrapper">

  <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
    <button class="btn btn-primary" id="menu-toggle">Toggle Menu</button>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
        <li class="nav-item active">
          <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Dropdown
          </a>
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="#">Action</a>
            <a class="dropdown-item" href="#">Another action</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">Something else here</a>
          </div>
        </li>
      </ul>
    </div>
  </nav>

</div>

</body>
</html>
