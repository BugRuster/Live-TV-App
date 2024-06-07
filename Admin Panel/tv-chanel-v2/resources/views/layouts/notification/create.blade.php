 @extends('adminlte.dashboard')



 @section('content')

 <div class="container fluid">
     <div class="card" style="width: 68rem; margin-left: 3rem;">
         <div class="category-form" style="margin: 3rem 3rem;">

             <div class="category-from">

                 <form method="post" action="{{route('sendNotification')}}" enctype="multipart/form-data">
                     {{ csrf_field() }}

                     <div class="form-group">
                         <label for="title">Title</label>
                         <input type="text" class="form-control" id="title" placeholder="Titlle" name="title"></input>
                     </div>

                     <div class="form-group">
                         <label for="formGroupExampleInput1">Message</label>
                         <textarea type="text" class="form-control" id="formGroupExampleInput1" placeholder="message" name="message"></textarea>
                     </div>

			   <div class="form-group">
                         <label for="url">url</label>
                         <input type="text" class="form-control" id="url" placeholder="url (insert https before url)" name="url"></input>
                     </div>

                     <div class="form-group">
                         <label for="imageURl">Image Url</label>
                         <input type="text" class="form-control" id="imageURl" placeholder="image url" name="image_url"></input>
                     </div>
                     <!-- <div class="form-group">

                         <div class="row">
                             <div class="col"><label for="formGroupExampleInput2">Url</label>
                                 <input type="text" class="form-control w-75" id="formGroupExampleInput2" placeholder="url insert https before url" name="url">
                             </div>
                             <div class="form-group">
                                 <label for="formGroupExampleInput3">Preview Image</label>
                                 <input type="file" name="image" class="form-control">
                             </div>
                         </div>

                     </div> -->



                     <div class="form-group float-right">
                         <button type="submit" class="btn btn-info">send notification</button>
                     </div>

                 </form>
             </div>
         </div>
     </div>
 </div>
 @endsection