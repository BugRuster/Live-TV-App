 @extends('adminlte.dashboard')



 @section('content')
 <div class="card card-warning">
     <div class="table-header" style="height:2rem">
         <h3 class="card-title">Settings</h3>
     </div>
     <!-- /.card-header -->
     <div class="card-body">
         <form method="post" action="{{ route('updatesettings', $settings) }}">
             {{ csrf_field() }}
             @method('PATCH')




             <!-- text input -->
             <div class="form-group mb-2">

                 <label>Privacy Url</label>
                 <div style="display:flex">
                     <input readonly type="text" class="form-control" placeholder="Enter ..." id="privacy-url" value="{{$settings->privacy_url}}" name="privacy_url" style="width:25rem">
                     <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('privacy-url')"></i>
                 </div>

             </div>

             <!-- text input -->
             <div class="form-group mb-2">

                 <label>settings</label>
                 <div style="display:flex">
                     <input readonly type="text" class="form-control" placeholder="Enter ..." id="settings" value="{{$settings->settings}}" name="settings" style="width:25rem;">
                     <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('settings')"></i>
                 </div>

             </div>
             <!-- text input -->
             <div class="form-group mb-2">
                 <label>fCM Api key</label>
                 <div style="display:flex"> <input readonly type="text" class="form-control" placeholder="Enter ..." id="fcm_api" value="{{$settings->fcm_api}}" name="fcm_api" style="width:25rem;">
                     <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('fcm_api')"></i>
                 </div>

             </div>
             <!-- text input -->
             <div class="form-group mb-2">
                 <label>One Singnal ID</label>
                 <div style="display:flex"><input readonly type="text" class="form-control" placeholder="Enter ..." id="one_signalID" value="{{$settings->one_signalID}}" name="one_signalID" style="width:25rem;">
                     <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('one_signalID')"></i>
                 </div>

             </div>
             <!-- text input -->
             <div class="form-group mb-2">
                 <label>Support Email</label>

                 <div style="display:flex"> <input readonly type="text" class="form-control" placeholder="Enter ..." id="support_email" value="{{$settings->support_email}}" name="support_email" style="width:25rem;">
                     <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('support_email')"></i>
                 </div>

             </div>



             <div class="form-group mb-2" style="margin-top: 20px;">

                 <div class="card" style="width:710px">
                     <div style="margin:8px">

                         <div class="">
                             <label>Home Dialogue Url</label>

                             <div style="display:flex">
                                 <input readonly type="text" class="form-control" placeholder="Enter ..." id="home_dialogue_link" value="{{$settings->home_dialogue_link}}" name="home_dialogue_link" style="width:1000px;">
                                 <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('home_dialogue_link')"></i>
                             </div>

                         </div>

                         <label>Home Dialogue Url visibility</label>
                         <div class="form-check">
                             <input class="form-check-input" type="radio" name="visibility_home_dialogue" id="exampleRadios1" value="yes" <?php echo $settings->visibility_home_dialogue == 'yes' ?  "checked" : null ?>>
                             <label class="form-check-label" for="exampleRadios1">
                                 Visible
                             </label>
                         </div>
                         <div class="form-check">
                             <input class="form-check-input" type="radio" name="visibility_home_dialogue" id="exampleRadios2" value="no" <?php echo $settings->visibility_home_dialogue == 'no' ? "checked" : null ?>>
                             <label class="form-check-label" for="exampleRadios2">
                                 Hidden
                             </label>
                         </div>
                     </div>

                 </div>











                 <!-- text input -->
                 <div class="form-group row">
                     <div class="col"> <label>token</label>
                         <input readonly type="text" id="tokenBox" class="form-control" placeholder="Enter ..." value="{{$settings->token}}" name="token" style="width:25rem">
                         <button type="button" class="btn btn-default mt-3" data-toggle="modal" data-target="#modal-default">
                             Generate Token
                         </button>
                     </div>


                 </div>

                 <div class="form-group float-right">
                     <button type="submit" class="btn btn-info">Update</button>
                 </div>

         </form>

         <!-- first moda -->
         <div class="modal fade" id="modal-default">
             <div class="modal-dialog">
                 <div class="modal-content">
                     <div class="modal-header">
                         <h4 class="modal-title">Default Modal</h4>
                         <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                             <span aria-hidden="true">&times;</span>
                         </button>
                     </div>
                     <div class="modal-body">
                         <input type="text" id="generateBox" class="form-control" placeholder="Enter ..." value="" name="">
                     </div>
                     <div class="modal-footer justify-content-between">
                         <button type="button" onclick="generateToken()" class="btn btn-info">Generate Token</button>
                         <button type="button" onclick="copyToken()" class="btn btn-primary">Apply Token</button>
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

                     </div>
                 </div>
                 <!-- /.modal-content -->
             </div>
             <!-- /.modal-dialog -->
         </div>
         <!-- second modal -->
         <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
             <div class="modal-dialog" role="document">
                 <div class="modal-content">

                     <div class="modal-body">
                         are you sure you want to apply token?
                         <p class="text-warning"> warning: you have to change your token in mobile devices also</p>
                     </div>
                     <div class="modal-footer">
                         <button type="button" onclick="applyToken()" class="btn btn-primary">yes</button>
                         <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>

                     </div>
                 </div>
             </div>
         </div>
     </div>
     <!-- /.card-body -->
 </div>


 <script src="{{ asset('js/settings.js')}}"></script>
 <script src="{{ asset('js/toogleInput.js')}}"></script>
 @endsection