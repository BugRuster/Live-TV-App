 @extends('adminlte.dashboard')



 @section('content')

 <div class="container fluid">
     <div class="card">


         <form method="post" action="{{ route('updateslider', $slider) }}" enctype="multipart/form-data" style="margin:3rem;">
             {{ csrf_field() }}
             @method('PATCH')
             <div class=" form-group">
                 <label for="">name</label>
                 <input type="text" class="form-control  w-50" id="" placeholder="name" name="name" value="{{$slider->name}}">
             </div>

             <div class="row">
                 <div class="col">
                     <div class="form-group">
                         <label for="">image</label>
                         <input type="file" name="image" class="form-control" value="{{$slider->image}}">
                     </div>
                 </div>
                 <div class="col" style="position:relative; margin-left:10rem;">
                     <img src=" {{asset($slider->image)}}" alt="image" width="200" height="150">
                 </div>
             </div>



             <div class="col"> <label for="1">Chanel</label>
                 <select class="form-control" name="product_id" id="1">


                     @foreach(App\Product::all() as $prod)

                     @if($slider->product_id ==$prod->id)
                     <option value="{{$prod->id}}" selected> {{$prod->name}} </option>
                     @else
                     <option value="{{ $prod->id}}">{{ $prod->name}}</option>
                     @endif
                     @endforeach

                 </select>
             </div>







             <div class="form-group mt-4 float-right">
                 <button type="submit" class="btn btn-info">update slider</button>
             </div>


         </form>
     </div>
 </div>
 </div>
 </div>
 @endsection