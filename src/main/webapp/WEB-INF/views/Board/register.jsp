<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .form-container {
            max-width: 100%;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        input[type="text"], textarea, input[type="email"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>

</head>
<body>
<%@ include file="/WEB-INF/views/layout/sidebar.jsp" %>
<div class="container-fluid">
    <%@ include file="/WEB-INF/views/layout/top.jsp" %>
    <!-- Main Content -->
    <div id="content" class="mt-4">
        <div class="row">
            <div class="col-12 form-container">
                <h1 class="mb-4">Board Register Page</h1>
                <form>
                    <table>
                        <tr>
                            <th>Title</th>
                            <td><input type="text" name="title" required></td>
                        </tr>
                        <tr>
                            <th>Content</th>
                            <td><textarea name="content" rows="5" required></textarea></td>
                        </tr>
                        <tr>
                            <th>Writer Email</th>
                            <td><input type="email" name="writerEmail" required></td>
                        </tr>
                        <tr>
                            <td colspan="2" style="text-align: center;">
                                <input type="submit" value="Register" id="addUser">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>


<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

    $(function adUser() {
        $("#addUser").on("click", function() {
            $("form").attr("method", "POST").attr("action", "/board/register").submit();
        });
    });

</script>
</body>
</html>
