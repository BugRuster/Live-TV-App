 @extends('adminlte.dashboard')



 @section('content')
 <div class="card">
     <div class="card-header">
         <h3 class="card-title">Categories</h3>

         <div class="card-tools">


             <a href="{{route('category.create')}}">
                 <button type="button" class="btn btn-create">Add new category</button>
             </a>


         </div>
     </div>

     <div class="content-table">
         <div class="card-body" id="app">
             <div style="overflow-x:auto;">
                 <table id="example" class="table">
                     <thead>
                         <tr class="table-header" style="height: 10px;">
                             >
                             <th scope=" col" ><i class="fas fa-sort"></i></th>
                             <th>Name</th>
                             <th>Category Type</th>
                             <th class="hide"> Image </th>

                             <th>Actions</th>


                         </tr>
                     </thead>
                     <tbody>

                         @foreach($categories as $key => $category)
                         <tr>
                             <th scope="row">{{++$key}}</th>
                             <td>{{$category->cat_name}}</td>

                             <td>
                                 @if($category->category_type==0)
                                 Locked
                                 @else
                                 Unlocked
                                 @endif
                             </td>
                             <td class="hide">
                                 <div class="table-img">
                                     <img src=" {{asset( $category->image)}}" alt="image not found" width="200" height="300">
                                 </div>
                             </td>




                             <td>
                                 <div class="" role="group" aria-label="Basic example" style="display:flex;">
                                     <a href="editcategory/{{$category->id}}" class="btn btn-light" role="button">
                                         <i class="fas fa-edit"></i>
                                     </a>
                                     <a href="delcategory/{{$category->id}}" class="btn btn-light" role="button" onclick="return confirm('Are you sure you want to delete this item')">
                                         <i class="fas fa-trash-alt" style=""></i>
                                     </a>

                                 </div>
                                 <div style="margin:1rem">
                                     <modal-el showing="exampleModalShowing" :item="{{$category}}" type="category" size="lg" />
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

 @endsection