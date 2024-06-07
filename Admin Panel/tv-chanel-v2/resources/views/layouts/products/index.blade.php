 @extends('adminlte.dashboard')



 @section('content')

 <!-- Main content -->
 <section class="section">
     <div class="section-body">
         <div class="row">
             <div class="col-12">
                 <div class=" card">

                     <!-- /.card-header -->

                     <div class="card-header">
                         <h3 class="card-title">Channels</h3>

                         <div class="card-tools">


                             <a href="{{route('addproduct')}}">
                                 <button type="button" class="btn btn-create" style="background-color: #343A40;">

                                     Add new Channel</button></a>



                         </div>
                     </div>


                     <div class="card-body" id="app">

                         <div style="overflow-x:auto;">
                             <table id="example" class="table">
                                 <thead >
                                     <tr class="table-header" style="height: 10px;">
                                         <th style="width: 1%"> </i></th>
                                         <th>Name</th>
                                         <th style="width: 8%">Category</th>
                                         <th class="hide" style="width: 25%">Image</th>
                                         <th class="hide">Url</th>
                                         <th class="hide">Url Type</th>
                                         <th>Actions</th>
                                     </tr>
                                 </thead>
                                 <tbody>

                                     @foreach($products as $key => $product)

                                     <tr>
                                         <td>{{++$key}}</td>
                                         <td>{{$product->name}}</td>
                                         <td>{{$product->category->cat_name}}</td>
                                         <td class="hide">
                                             <div class="table-img">
                                                 <img src="{{asset($product->image)}}" alt="image not found" width="100" height="100">
                                             </div>

                                         </td>


                                         <td class="hide">
                                             <div class="table-link">
                                                 <a href="{{$product->url}}">
                                                     {{$product->url}}
                                                 </a>
                                             </div>


                                         </td>
                                         <td class="hide">{{$product->url_type->name}}</td>
                                         <td>
                                             <div class="" role="group" aria-label="Basic example" style="display:flex;">
                                                  <div style="margin:1rem">
                                     <modal-el showing="exampleModalShowing" :item="{{$product}}" type="product" size="lg" />
                                 </div>

                                                 <a href="editproduct/{{$product->id}}" class="btn btn-light" role="button">
                                                     <i class="fas fa-edit"></i>
                                                 </a>

                                                 <a href="delproduct/{{$product->id}}" class="btn btn-light" role="button" onclick="return confirm('Are you sure you want to delete this item')">
                                                     <i class="fas fa-trash-alt" style="margin-left:20px"></i>
                                                 </a>


                                             </div>


                                             <!-- <modal-el showing="exampleModalShowing" :item="{{$product}}" type='product' size="xl" /> -->

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

         <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
             <div class="modal-dialog" role="document">
                 <div class="modal-content">
                     <div class="modal-header">
                         <h5 class="modal-title" id="exampleModalLabel">New message</h5>
                         <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                             <span aria-hidden="true">&times;</span>
                         </button>
                     </div>
                     <div class="modal-body">
                         <form>
                             <div class="form-group">
                                 <label for="recipient-name" class="col-form-label">Recipient:</label>
                                 <input type="text" class="form-control" id="recipient-name">
                             </div>
                             <div class="form-group">
                                 <label for="message-text" class="col-form-label">Message:</label>
                                 <textarea class="form-control" id="message-text"></textarea>
                             </div>
                         </form>
                     </div>
                     <div class="modal-footer">
                         <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                         <button type="button" class="btn btn-primary">Send message</button>
                     </div>
                 </div>
             </div>
         </div>

     </div>
 </section>



 @endsection