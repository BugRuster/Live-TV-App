 @extends('adminlte.dashboard')



 @section('content')

 <div class="content">
     <div class="container-fluid">
         <div class="row">
             <div class="col-12">
                 <div class="card">

                     <!-- /.card-header -->

                     <div class="card-header">
                         <h3 class="card-title">Sliders</h3>

                         <div class="card-tools">


                             <a href="{{route('slider.create')}}">
                                 <button type="button" class="btn btn-create">Add Slider</button></a>



                         </div>
                     </div>


                     <div class="card-body" id="app">
                         <div style="overflow-x:auto;">
                             <table id="example" class="table table-striped">
                                 <thead>
                                     <tr class="table-header">
                                         <th scope="col"><i class="fas fa-sort"></i></th>
                                         <th>Name</th>
                                         <th>Channel</th>
                                         <th class="hide">Image</th>
                                         <th>Actions</th>
                                     </tr>
                                 </thead>
                                 <tbody>

                                     @foreach($sliders as $key => $slider)
                                     <tr>
                                         <th scope="row">{{++$key}}</th>
                                         <td>{{$slider->name}}</td>
                                         <td>{{$slider->product->name}}</td>
                                         <td class="hide">
                                             <div class="table-img">
                                                 <img src="{{asset($slider->image)}}" alt="image not found" width="200" height="200">
                                             </div>
                                         </td>

                                         <td>
                                             <div class="" role="group" aria-label="Basic example" style="display:flex">
                                                 <a href="editslider/{{$slider->id}}" class="btn btn-light" role="button">
                                                     <i class="fas fa-edit"></i>
                                                 </a>
                                                 <a href="delslider/{{$slider->id}}" class="btn btn-light" role="button" onclick="return confirm('Are you sure you want to delete this item')">
                                                     <i class="fas fa-trash-alt" style="margin-left:20px"></i>
                                                 </a>

                                             </div>
                                             <div style="margin:1rem">
                                                 <modal-slide :item="{{$slider}}" size="xl" />
                                             </div>
                                         </td>



                                     </tr>
                                     @endforeach


                                 </tbody>

                             </table>

                         </div>
                     </div>
                 </div>
             </div>
         </div>
     </div>
 </div>
 <!-- <script src="{{ asset('js/app.js') }}"></script> -->

 @endsection