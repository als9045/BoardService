<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sidebar</title>
    <style>
        body{
            display: flex;
        }
        #sidebar-wrapper {
            width: 200px;
            background-color: #f8f9fa;
            border-right: 1px solid #dee2e6;
            height: 100vh;
        }
        .list-group-item {
            display: block;
            width: 100%;
            padding: 0.75rem 1.25rem;
            margin-bottom: -1px;
            background-color: #fff;
            border: 1px solid rgba(0,0,0,.125);
        }
        .list-group-item-action {
            width: 100%;
            color: #495057;
            text-align: inherit;
        }
        .list-group-item-action:hover {
            z-index: 1;
            color: #495057;
            text-decoration: none;
            background-color: #f8f9fa;
        }
    </style>
</head>
<body>
<div class="bg-light border-right" id="sidebar-wrapper">
    <div class="sidebar-heading"></div>
    <div class="list-group list-group-flush">
        <a href="index.jsp?page=dashboard.jsp" class="list-group-item list-group-item-action bg-light">Dashboard</a>
        <a href="index.jsp?page=shortcuts.jsp" class="list-group-item list-group-item-action bg-light">Shortcuts</a>
        <a href="index.jsp?page=overview.jsp" class="list-group-item list-group-item-action bg-light">Overview</a>
        <a href="index.jsp?page=events.jsp" class="list-group-item list-group-item-action bg-light">Events</a>
        <a href="index.jsp?page=profile.jsp" class="list-group-item list-group-item-action bg-light">Profile</a>
        <a href="index.jsp?page=status.jsp" class="list-group-item list-group-item-action bg-light">Status</a>
    </div>
</div>
</body>
</html>
