 @extends('adminlte.dashboard')



 @section('content')
 <div class="container fluid" style="overflow-x:auto;">
     <div class="card">


         <form method="post" action="{{route('slider.store')}}" enctype="multipart/form-data" style="margin:3rem;">
             {{ csrf_field() }}
             <div class="form-group">
                 <label for="formGroupExampleInput1">Name</label>
                 <input type="text" class="form-control" id="formGroupExampleInput1" placeholder="name" name="name" requireds>
             </div>
             <div class="row">
                 <div class="col">
                     <div class="form-group">
                         <label for="formGroupExampleInput3">Channel</label>
                         <select class="form-control w-75" name="product_id">
                             @foreach(App\Product::all() as $product)
                             <option value="{{$product->id}}">{{$product->name}}</option>

                             @endforeach
                         </select>
                     </div>
                 </div>
                 <div class="col">
                     <div class="form-group">
                         <label for="formGroupExampleInput3">Preview Image</label>
                         <input type="file" name="image" class="form-control w-75">
                     </div>
                 </div>
             </div>



             <div class="form-group float-right" style="margin-top: 3rem">
                 <button type="submit" class="btn btn-info">Add Slider</button>
             </div>

         </form>
     </div>
 </div>
 </div>
 </div>
 @endsection