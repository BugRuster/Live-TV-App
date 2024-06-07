 @extends('adminlte.dashboard')



 @section('content')

 <div class="container fluid">
     <div class="card">

         <form method="post" action="{{ route('product.update', $product) }}" enctype="multipart/form-data" style="margin:3rem;">
             {{ csrf_field() }}
             @method('PATCH')
             <div class="form-group">
                 <label for="">name</label>
                 <input type="text" class="form-control" id="namebox" placeholder="name" name="name" value="{{$product->name}}">
             </div>

             <div class="row">
                 <div class="col">
                     <div class="form-group">
                         <label for="">image</label>
                         <input type="file" name="image" class="form-control" value="{{$product->image}}" >
                     </div>
                 </div>
                 <div class="col">
                     <img src=" {{asset($product->image)}}" alt="image" width="100" height="100">
                 </div>
             </div>


             <div class=" form-group">
                 <label for="1">category</label>
                 <select class="form-control" name="cat_id" id="catSelectbox">
                     @foreach(App\Category::all() as $cat)


                     @if($product->cat_id==$cat->id)
                     <option value="{{$cat->id}}" selected> {{$cat->cat_name}} </option>
                     @else
                     <option value="{{ $cat->id}}">{{ $cat->cat_name}}</option>
                     @endif
                     @endforeach

                 </select>
             </div>

             <div class="form-group">
                 <label for="">url</label>
                 <input type="text" class="form-control" id="urlbox" placeholder="url" name="url" value="{{$product->url}}">
             </div>
             <div class=" form-group">
                 <label for="">url type</label>

                 <select class="form-control" name="url_id" id="urlSelectbox">
                     @foreach(App\Url_type::all() as $url)


                     @if($product->url_id==$url->id)
                     <option value="{{$url->id}}" selected> {{$url->name}} </option>
                     @else
                     <option value="{{ $url->id}}">{{ $url->name}}</option>
                     @endif
                     @endforeach

                 </select>
             </div>
             <div class=" form-group">
                 <label for="">useragent</label>
                 <input type="text" class="form-control" id="" placeholder=" user_agent" name="user_agent" value="{{$product->user_agent}}">
             </div>
             <div class=" form-group">
                 <label for="">token</label>
                 <input type="text" class="form-control" id="" placeholder=" token" name="token" value="{{$product->token}}">
             </div>
             <div class=" form-group">
                 <label for="">extra</label>
                 <input type="text" class="form-control" id="" placeholder=" naextrame" name="extra" value="{{$product->extra}}">
             </div>


             <!-- /.card-header -->
             <div class=" form-group">
                 <label for="">description</label>
                 <textarea id="summernote" name="description" placeholder="description">
                 {{$product->description}}
                 </textarea>

             </div>
             <!-- /.col-->

             <!-- /.col-->

             <div class="form-group float-right">
                 <button type="submit" class="btn btn-info" onclick="return Validate()">update Channel</button>
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
             alert('category name is needed');
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