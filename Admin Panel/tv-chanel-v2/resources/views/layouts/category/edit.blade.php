 @extends('adminlte.dashboard')



 @section('content')

 <div class="container fluid">
     <div class="card">


         <form method="post" action="{{ route('updatecategory', $category) }}" enctype="multipart/form-data" style="margin:4rem;">
             {{ csrf_field() }}
             @method('PATCH')
             <div class=" form-group">
                 <label for="">name</label>
                 <input type="text" class="form-control  w-50" id="namebox" placeholder="name" name="cat_name" value="{{$category->cat_name}}">
             </div>

             <div class="row">
                 <div class="col">
                     <div class="form-group">
                         <label for="">image</label>
                         <input type="file" name="image" class="form-control" value="{{$category->image}}">
                     </div>
                 </div>
                 <div class="col" style="position:relative; margin-left:10rem;">
                     <img src=" {{asset($category->image)}}" alt="image" width="200" height="150">
                 </div>
             </div>




             <div class="form-group">
                 <label for="">Category Type</label>
                 <select id="selectbox" name="category_type" onChange="myFunction()">
                     <option value="0" <?php
                                        if ($category->category_type == 0)
                                            echo 'selected';
                                        ?>>Locked</option>
                     <option value="1" <?php
                                        if ($category->category_type == 1)
                                            echo 'selected';
                                        ?>>Unlocked</option>

                 </select>
             </div>

             <div class="form-group" id="passdiv">
                 <label for="">pass</label>
                 <input type="text" class="form-control  w-50" id="" placeholder="category_type" name="pass" value="{{$category->pass}}">
             </div>





             <div class="form-group">
                 <button type="submit" class="btn btn-info" onclick="return Validate()">update category</button>
             </div>


         </form>
     </div>
 </div>
 <script type="text/javascript">
     document.addEventListener("DOMContentLoaded", function() {

         var selectVAl = document.getElementById("selectbox").value;
         var passdiv = document.getElementById("passdiv");
         if (selectVAl == 1) {

             passdiv.setAttribute("style", "display: none;");
         }

     });

     function myFunction() {

         var pass = document.getElementById("passdiv");

         var val = document.getElementById("selectbox").value;

         if (val == 0) {
             pass.setAttribute("style", "display: block;");
         }
         if (val == 1) {
             pass.setAttribute("style", "display: none;");
         }

     }

     function Validate() {
         var nameVal = document.getElementById("namebox").value;

         if (nameVal == "") {
             alert('category name is needed');
             return false;
         }



         return true;
     }
 </script>

 @endsection