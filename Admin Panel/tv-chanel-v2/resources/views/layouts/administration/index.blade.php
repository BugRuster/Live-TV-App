 @extends('adminlte.dashboard')



 @section('content')

 <div class="card">
     <div class="card-header">
         <h3 class="card-title">Administration</h3>

         <div class="card-tools">


             <a href="{{route('administration.create')}}">
                 <button type="button" class="btn btn-info">Add new user</button>
             </a>


         </div>
     </div>
     <div class="card-body p-0">
         <table id="example" class="table table-striped projects">
             <thead>
                 <tr>
                     <th style="width: 1%">

                     </th>
                     <th style="width: 10%">
                         User Name
                     </th>
                     <th style="width: 6%">
                         Image
                     </th>
                     <th style="width: 5%">
                         Role
                     </th>
                     <th style="width: 8%">
                         Actions
                     </th>

                 </tr>
             </thead>
             <tbody>



                 @foreach($users as $key=>$user )
                 <tr>
                     <td>
                         {{++$key}}
                     </td>
                     <td>
                         <a>
                             {{$user->name}}
                         </a>
                         <br />
                         <small>
                             Created 01.01.2019
                         </small>
                     </td>
                     <td>
                         <ul class="list-inline">
                             <li class="list-inline-item">
                                 <img alt="Avatar" class="table-avatar" src="../../dist/img/avatar.png">
                             </li>

                         </ul>

                     </td>
                     <td>
                         @if( $user->role==1)


                         <p>admin</p>


                         @else
                         <p>manager</p>

                         @endif
                     </td>

                     <td class="">


                         <a href="edituser/{{$user->id}}" class="btn btn-light" role="button">
                             <i class="fas fa-edit"></i> Edit
                         </a>



                         <a class="btn btn-danger btn-sm" href="deluser/{{$user->id}}" role="button" onclick="return confirm('Are you sure you want to delete this item')">
                             <i class="fas fa-trash">
                             </i>
                             Delete
                         </a>
                     </td>
                 </tr>
                 @endforeach
             </tbody>
         </table>

     </div>
     <!-- /.card-body -->
 </div>
 @endsection