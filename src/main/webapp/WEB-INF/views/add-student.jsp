<!DOCTYPE html>
<html>
    <head>
        <title>Add Student</title>
        <%@include file="/components/common_css_js.jsp"%>
    </head>
    <body>
        <div>
            <div>
                <form action="" method="post" style="max-width:400px; margin:auto ; border-style: groove ; padding: 10px ; border-radius:2-px;border-radius: 10px;" class="row g-3">
                    <div class="col-md-6">
                      <label for="inputEmail4" for=""name class="form-label">Name</label>
                      <input type="text" id="name" name="name" class="form-control" id="inputEmail4">
                    </div>
                    <div class="col-md-6">
                      <label for="inputPassword4" for="age" class="form-label">Age</label>
                      <input id="age" onchange="checkNum()" type="text" name="age" class="form-control">
                    </div>
                    <div class="col-md-12">
                      <label for="inputZip" for="address" class="form-label">Address
                      </label>
                      <textarea id="address" style="height:100px" name="address" type="text" class="form-control" ></textarea>
                    </div>
              
                    <div style="margin:auto;margin-top:10px;margin-left:110px" class="col-12">
                      <button id="create" type="submit" class="btn btn-primary">create</button>
                      <a href="<c:url value='/student' />" class="btn btn-primary">Back</a>
                    </div>
                  </form>
            </div>
        </div>
    </body>
    <script>
      function checkNum()
      {
        let inputt =  document.getElementById("age").value
        try{
          document.getElementById("create").hidden = false;
          document.getElementById("address").readOnly = false;
          eval(inputt);
        }
        catch
        {
          alert("error : age is a number")
          document.getElementById("address").readOnly = true;
          document.getElementById("create").hidden = true;
        }
      }
    </script>
</html>