<!DOCTYPE html>>
<html>
    <head>
        <title>Add Student</title>
        <%@include file="/components/common_css_js.jsp"%>
    </head>
    <body>
        <div>
            <div>
                <form action="<c:url value='/student/update' />"  method="post" style="max-width:400px; margin:auto ; border-style: groove ; padding: 10px ; border-radius:2-px;border-radius: 10px;" class="row g-3">
                <input type="hidden" value="${student.id}" name="id" />    
                <div class="col-md-6">
                      <label for="inputEmail4" for=""name class="form-label">Name</label>
                      <input type="text" value="${student.name}" id="name" name="name" class="form-control" id="inputEmail4">
                    </div>
                    <div class="col-md-6">
                      <label for="inputPassword4" for="age" class="form-label">Age</label>
                      <input type="text" value="${student.age}" name="age" class="form-control" id="inputPassword4">
                    </div>
                    <div class="col-md-12">
                      <label for="inputZip" for="address" class="form-label">Address
                      </label>
                      <textarea style="height:100px" value="${student.address}" name="address" type="text" class="form-control" >
                        ${student.address}
                    </textarea>
                    </div>
              
                    <div style="margin:auto;margin-top:10px;margin-left:110px" class="col-12">
                      <button type="submit" class="btn btn-primary">update</button>
                      <a href="<c:url value='/student' />" class="btn btn-primary">Back</a>
                    </div>
                  </form>
            </div>
        </div>
    </body>
</html>