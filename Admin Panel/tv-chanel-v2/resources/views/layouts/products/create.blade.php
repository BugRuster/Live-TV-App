 @extends('adminlte.dashboard')



 @section('content')

 <div class="container fluid">
     <div class="card">


         <form method="post" action="{{route('storeproduct')}}" enctype="multipart/form-data" style="margin:3rem;">
             {{ csrf_field() }}
             <div class="form-group">
                 <div class="col"> <label for="formGroupExampleInput1">Channel Name</label>
                     <input type="text" class="form-control w-80" id="namebox" placeholder=" name" name="name">
                 </div>
                 <div class="row">

                     <div class="col"> <label for="formGroupExampleInput3">Preview Image</label>
                         <input type="file" name="image" class="form-control w-75">
                     </div>

                     <div class="col"> <label for="1">Category</label>
                         <select class="form-control" name="cat_id" id="catSelectbox">
                             <option value="#" selected disabled>select category</option>
                             @foreach(App\Category::all() as $cat)
                             <option value="{{ $cat->id}}">{{ $cat->cat_name}}</option>
                             @endforeach

                         </select>
                     </div>

                 </div>

             </div>




             <div class="form-group">


                 <div class="row">
                     <div class="col"><label for="formGroupExampleInput1">Url</label>
                         <input type="text" class="form-control w-75" id="urlbox" placeholder="url" name="url">
                     </div>
                     <div class="col"> <label for="formGroupExampleInput1">Url type</label>

                         <select class="form-control" name="url_id" id="urlSelectbox">
                             <option value="#" selected disabled>select url type</option>
                             @foreach(App\Url_type::all() as $url)

                             <option value="{{ $url->id}}">{{ $url->name}}</option>
                             @endforeach

                         </select>
                     </div>
                 </div>

             </div>

             <div class="form-group">

                 <div class="row">
                     <div class="col"> <label for="formGroupExampleInput1">Useragent</label>
                         <input type="text" class="form-control w-75" id="formGroupExampleInput1" placeholder=" user_agent" name="user_agent">
                     </div>
                     <div class="col"><label for="formGroupExampleInput1">Token</label>
                         <input type="text" class="form-control w-75" id="formGroupExampleInput1" placeholder=" token" name="token">
                     </div>
                 </div>

             </div>

             <div class="form-group">
                 <label for="formGroupExampleInput1">Extra</label>
                 <input type="text" class="form-control w-50" id="formGroupExampleInput1" placeholder=" naextrame" name="extra">
             </div>


             <!-- /.card-header -->
             <div class="form-group">
                 <label for="formGroupExampleInput1">Description</label>
                 <textarea id="summernote" name="description" placeholder="description">

              </textarea>

             </div>
             <!-- /.col-->

             <!-- /.col-->
             <div class="form-group float-right">
                 <button type="submit" class="btn btn-info" onclick="return Validate()">Add Channel</button>
             </div>

         </form>
     </div>
 </div>
 <script type="text/javascript">
     function Validate() {
         var nameVal = document.getElementById("namebox").value;
         var catSelectVal = document.getElementById("catSelectbox").value;
         var urlSelectVal = document.getElementById("urlSelectbox").value;
         var urlVal = document.getElementById("urlbox").value;
         if (nameVal == "") {
             alert('Chanel name is needed');
             return false;
         }

         if (catSelectVal == '#') {
             alert('category  is needed');
             return false;
         }

         if (urlSelectVal == '#') {
             alert('url type is needed');
             return false;
         }

         if (urlVal == "") {
             alert('url is needed');
             return false;
         }


         return true;
     }
 </script>
 @endsection