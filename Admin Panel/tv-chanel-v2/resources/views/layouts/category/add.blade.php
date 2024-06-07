 @extends('adminlte.dashboard')



 @section('content')

 <div class="container fluid">
     <div class="card">


         <form method="post" action="{{route('storecatagory')}}" enctype="multipart/form-data" style="margin:4rem;">
             {{ csrf_field() }}
             <div class="form-group">
                 <label for="formGroupExampleInput1">Category Name</label>
                 <input type="text" class="form-control" id="namebox" placeholder=" name" name="cat_name">
             </div>
             <div class="form-group">

                 <div class="row">
                     <div class="col mt-1">
                         <div style="display:block;">
                             <label for="formGroupExampleInput2">Category Type</label>
                             <select class="form-control w-50" id="selectbox" name="category_type" onChange="myFunction()">
                                 <option value="#" selected disabled>select type</option>
                                 <option value=" 0">Locked</option>
                                 <option value="1">Unlocked</option>

                             </select>
                         </div>

                     </div>

                 </div>
             </div>
             <div class="col form-group" id="passdiv" style="display:none;"> <label for=" formGroupExampleInput3">Pass</label>
                 <input type="text" class="form-control w-75" id="passinput" placeholder="pass" name="pass">
             </div>







             <div class="form-group mt-4">
                 <label for="formGroupExampleInput3">Preview Image</label>
                 <input type="file" name="image" class="form-control w-50">
             </div>
             <div class="form-group float-right">
                 <button type="submit" class="btn btn-info" onclick="return Validate()">Add Category</button>
             </div>

         </form>
     </div>
 </div>

 <script type="text/javascript">
     function myFunction() {

         var pass = document.getElementById("passdiv");

         var value = document.getElementById("selectbox").value;

         if (value == 0) {
             pass.setAttribute("style", "display: block;");
         }
         if (value == 1) {
             pass.setAttribute("style", "display: none;");
         }

     }

     function Validate() {
         var nameVal = document.getElementById("namebox").value;
         var selectVAl = document.getElementById("selectbox").value;
         var passVal = document.getElementById("passinput").value;
         if (nameVal == "") {
             alert('category name is needed');
             return false;
         }

         if (selectVAl == '#') {
             alert('category type is needed');
             return false;
         }


         if (selectVAl == 0 && passVal == "") {
             alert('password is  needed');
             return false;
         }


         return true;
     }
 </script>






 @endsection