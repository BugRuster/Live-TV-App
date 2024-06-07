 @extends('adminlte.dashboard')



 @section('content')



 <div class="col-md-10" style="margin-left:40px">
     <div class="card">
         <div class="card-header">
             <h3 class="card-title">Add User</h3>

             <div class="card-tools">
                 <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                     <i class="fas fa-minus"></i>
                 </button>
             </div>
         </div>
         <form method="post" action="{{route('administration.store')}}" enctype="multipart/form-data">
             {{ csrf_field() }}

             <div class="card-body">
                 <div class="form-group">
                     <label for="inputName">User Name</label>
                     <input type="text" id="inputName" class="form-control" name="name">
                 </div>
                 <div class="form-group">
                     <label for="inputName">Email </label>
                     <input type="email" id="inputName" class="form-control" name="email">
                 </div>
                 <div class="form-group">
                     <label for="inputName">Password </label>
                     <input type="password" id="txtPassword" class="form-control" name="password">
                 </div>
                 <div class="form-group">
                     <label for="inputName">Confirm Password </label>
                     <input type="password" id="txtConfirmPassword" class="form-control" name="password">
                 </div>

                 <div class="form-group">
                     <label for="role">Role</label>
                     <select class="form-control" name="role" id="role">
                         @foreach(App\Role::all() as $role)
                         <option value="{{ $role->id}}">{{ $role->name}}</option>
                         @endforeach

                     </select>
                 </div>

                 <div class="form-group float-right">
                     <button type="submit" class="btn btn-info" onclick="return Validate()">Add User</button>
                 </div>
             </div>
         </form>


         <!-- /.card-body -->
     </div>
     <!-- /.card -->

 </div>
 <script type="text/javascript">
     function Validate() {
         var password = document.getElementById("txtPassword").value;
         var confirmPassword = document.getElementById("txtConfirmPassword").value;
         if (password != confirmPassword) {
             alert("Passwords do not match.");
             return false;
         }
         return true;
     }
 </script>

 @endsection