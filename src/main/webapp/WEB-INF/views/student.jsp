
<!DOCTYPE html>
<html lang ="vi">
    <head>
        <!-- <meta http-equiv="Content-Type" content="text/html"  charset="UTF-8"> -->
        <title>student List</title>
        <%@include file="/components/common_css_js.jsp"%>
    </head>
    <body>
        <div style="width:1200px; margin: auto; margin-top: 50px;">
            <div class="container mb-3 btn-add">
                <a href="<c:url value='/student/add' />" class="btn btn-primary">Add student</a>
                <a href="<c:url value='/student/pdfview' />" class="btn btn-primary">Export PDF</a>
            </div>
            <div>
                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Age</th>
                        <th scope="col">Address</th>
                        <th scope="col">Action</th>
                      </tr>
                    </thead>
                    <tbody class="table-group-divider">
                        <c:forEach items="${ student }" var="student" varStatus="status">
                            <tr>
                                <th scope="row">SV-${student.id}</th>
                                <td>${student.name}</td>
                                <td>${student.age}</td>
                                <td>${student.address}</td>
                                <td>
                                    <div>
                                        <a href="<c:url value='/student/update/${ student.id }' />" class="btn btn-ountline-info btn-action" >update</a>
                                        <a href="<c:url value='/student/delete/${ student.id }' />" class="btn btn-ountline-info btn-action" >Delete</a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
