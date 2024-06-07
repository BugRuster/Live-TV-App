 @extends('adminlte.dashboard')



 @section('content')

 <section class="content">
     <div class="container-fluid">
         <div class="row">
             <div class="col-12">
                 <div class="card">

                     <!-- /.card-header -->

                     <div class="card-header">
                         <h3 class="card-title">Notofocation</h3>

                         <div class="card-tools">


                             <a href="{{route('notification.create')}}">
                                 <button type="button" class="btn btn-create">Send Notification</button></a>



                         </div>
                     </div>
                     <div class="card-body" id="app">
                         <div style="overflow-x:auto;">
                             <table id="example" class="table  table-hover">
                                 <thead>
                                     <tr class="table-header">
                                         <th scope=" col"><i class="fas fa-sort"></i></th>
                                         <th>Title</th>
                                         <th>Messages</th>
                                         <th>Url</th>
                                         <th>Image</th>
                                         <th>Actions</th>


                                     </tr>
                                 </thead>
                                 <tbody>
                                     <?php $i = ($notifications->currentpage() - 1) * $notifications->perpage();
                                        $i = $i + 1;
                                        ?>
                                     @foreach($notifications as $key => $notification)
                                     <tr>
                                         <th scope="row">{{$i++}}</th>
                                         <td>{{$notification->title}}</td>
                                         <td>{{$notification->message}}</td>
                                         <td>
                                             <a href=" {{$notification->url}}">
                                                 {{$notification->url}}
                                             </a>

                                         </td>
                                         <td><img src="{{asset( $notification->image)}}" alt="image not found" width="100" height="100"></td>

                                         <td>
                                             <div class="" role="group" aria-label="Basic example" style="display:flex;">


                                                 <a href="delnotification/{{$notification->id}}" class="btn btn-light" role="button" onclick="return confirm('Are you sure you want to delete this item')">
                                                     <i class="fas fa-trash-alt" style=""></i>
                                                 </a>
                                                 <div style="margin-left:2rem">
                                                     <modal-notification :item="{{$notification}}" size="lg" />
                                                 </div>

                                             </div>
                                         </td>


                                     </tr>
                                     @endforeach


                                 </tbody>

                             </table>
                             {{ $notifications->links()}}
                         </div>


                     </div>
                 </div>
             </div>
         </div>
     </div>
 </section>

 @endsection