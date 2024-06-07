 @extends('adminlte.dashboard')



 @section('content')
 <div class="card">
     <div class="table-header" style="height: 40px;">
         <h3 class="card-title" style="margin: 1rem;">Advertise</h3>
     </div>
     <!-- /.card-header -->
     <div class=" card-body">
         <form method="post" action="{{ route('updateadd', $add) }}">
             {{ csrf_field() }}
             @method('PATCH')


             <div class="card mb-5">
                 <div class="card-header">
                     Admob
                 </div>
                 <div class="card-body">
                     <div class="row">
                         <div class="col-sm-6">
                             <!-- text input -->
                             <div class="form-group">
                                 <label>Admob Interstitial</label>
                                 <div style="display:flex"> <input readonly type="text" class="form-control" placeholder="Enter ..." id="admob_inter" value="{{$add->admob_inter}}" name="admob_inter" style="width:25rem;">
                                     <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('admob_inter')"></i>
                                 </div>


                             </div>
                         </div>
                         <div class="col-sm-6">
                             <!-- text input -->
                             <div class="form-group">
                                 <label>Admob Banner</label>
                                 <div style="display:flex"> <input readonly type="text" class="form-control" placeholder="Enter ..." id="admob_banner" value="{{$add->admob_banner}}" name="admob_banner" style="width:25rem;">
                                     <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('admob_banner')"></i>
                                 </div>



                             </div>
                         </div>
                         <div class="col-sm-6">
                             <!-- text input -->
                             <div class="form-group">
                                 <label>Admob Native</label>
                                 <div style="display:flex"><input readonly type="text" class="form-control" placeholder="Enter ..." id="admob_native" value="{{$add->admob_native}}" name="admob_native" style="width:25rem;">
                                     <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('admob_native')"></i>
                                 </div>



                             </div>
                         </div>
                         <div class="col-sm-6">
                             <!-- text input -->
                             <div class="form-group">
                                 <label>Admob App open</label>
                                 <div style="display:flex"> <input readonly type="text" class="form-control" placeholder="Enter ..." id="admob_appopen" value="{{$add->admob_appopen}}" name="admob_appopen" style="width:25rem;">
                                     <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('admob_appopen')"></i>
                                 </div>


                             </div>
                         </div>
                         <div class="col-sm-6">
                             <!-- text input -->
                             <div class="form-group">
                                 <label>Admob Reward</label>
                                 <div style="display:flex"> <input readonly type="text" class="form-control" placeholder="Enter ..." id="admob_reward" value="{{$add->admob_reward}}" name="admob_reward" style="width:25rem;">
                                     <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('admob_reward')"></i>
                                 </div>



                             </div>
                         </div>
                     </div>
                 </div>
             </div>





             <div class="card mb-5">
                 <div class="card-header">
                     Facebook
                 </div>
                 <div class="card-body">
                     <div class="row">
                         <div class="col-sm-6">
                             <!-- text input -->
                             <div class="form-group">
                                 <label>Facebook Interstitial</label>
                                 <div style="display:flex"> <input readonly type="text" class="form-control" placeholder="Enter ..." id="fb_inter" value="{{$add->fb_inter}}" name="fb_inter" style="width:25rem;">
                                     <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('fb_inter')"></i>
                                 </div>



                             </div>
                         </div>
                         <div class="col-sm-6">
                             <!-- text input -->
                             <div class="form-group">
                                 <label>Facebook Banenr</label>
                                 <div style="display:flex"> <input readonly type="text" class="form-control" placeholder="Enter ..." id="fb_banner" value="{{$add->fb_banner}}" name="fb_banner" style="width:25rem;">
                                     <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('fb_banner')"></i>
                                 </div>



                             </div>
                         </div>
                         <div class="col-sm-6">
                             <!-- text input -->
                             <div class="form-group">
                                 <label>Facebook Native</label>
                                 <div style="display:flex"> <input readonly type="text" class="form-control" placeholder="Enter ..." id="fb_native" value="{{$add->fb_native}}" name="fb_native" style="width:25rem;">
                                     <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('fb_native')"></i>
                                 </div>



                             </div>
                         </div>
                         <div class="col-sm-6">
                             <!-- text input -->
                             <div class="form-group">
                                 <label>Facebook Reward</label>
                                 <div style="display:flex"><input readonly type="text" class="form-control" placeholder="Enter ..." id="fb_reward" value="{{$add->fb_reward}}" name="fb_reward" style="width:25rem;">
                                     <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('fb_reward')"></i>
                                 </div>



                             </div>
                         </div>

                     </div>
                 </div>
             </div>

             <div class="card mb-5">
                 <div class="card-header">
                     Company
                 </div>
                 <div class="card-body">
                     <div class="row">
                         <div class="col-sm-6">
                             <!-- text input -->
                             <div class="form-group">
                                 <label>App Nex App ID</label>
                                 <div style="display:flex"> <input readonly type="text" class="form-control" id="appnex_inter" placeholder="Enter ..." value="{{$add->appnex_inter}}" name="appnex_inter" style="width:25rem;">
                                     <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('appnex_inter')"></i>
                                 </div>



                             </div>
                         </div>
                         <div class="col-sm-6">
                             <!-- text input -->
                             <div class="form-group">
                                 <label>Unity App ID</label>
                                 <div style="display:flex"> <input readonly type="text" class="form-control" placeholder="Enter ..." id="appnex_banner" value="{{$add->appnex_banner}}" name="appnex_banner" style="width:25rem;">
                                     <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('appnex_banner')"></i>
                                 </div>



                             </div>
                         </div>

                         <div class="col-sm-6">
                             <!-- text input -->
                             <div class="form-group">
                                 <label>Iron Source Adds</label>
                                 <div style="display:flex"> <input readonly type="text" class="form-control" placeholder="Enter ..." id="iron_source_ads" value="{{$add->iron_source_ads}}" name="iron_source_ads" style="width:25rem;">
                                     <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('iron_source_ads')"></i>
                                 </div>



                             </div>
                         </div>

                         <div class="col-sm-6">
                             <!-- text input -->
                             <div class="form-group">
                                 <label>App lovin</label>
                                 <div style="display:flex"> <input readonly type="text" class="form-control" placeholder="Enter ..." id="app_lovin" value="{{$add->app_lovin}}" name="app_lovin" style="width:25rem;">
                                     <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('app_lovin')"></i>
                                 </div>



                             </div>
                         </div>


                     </div>
                 </div>
             </div>

             <div class="card mb-5">
                 <div class="card-header">
                     Startapp
                 </div>
                 <div class="card-body">
                     <div class="row">
                         <div class="col-sm-6">
                             <!-- text input -->
                             <div class="form-group">
                                 <label>Start app id</label>
                                 <div style="display:flex"> <input readonly type="text" class="form-control" id="startapp_app_id" placeholder="Enter ..." value="{{$add->startapp_app_id}}" name="startapp_app_id" style="width:25rem;">
                                     <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('startapp_app_id')"></i>
                                 </div>

                             </div>
                         </div>



                     </div>
                 </div>
             </div>


             <div class="card w-50">

                 <div class="card-body">

                     <div class="form-group">
                         <label>Advertise count</label>
                         <div style="display:flex"><input readonly type="text" id="add_count" class="form-control" placeholder="Enter ..." value="{{$add->add_count	}}" name="add_count" style="width:25rem;">
                             <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('add_count')"></i>
                         </div>



                     </div>

                     <div class="form-group">
                         <label>Native Advertise count</label>
                         <div style="display:flex"><input readonly type="text" id="add_count_native" class="form-control" placeholder="Enter ..." value="{{$add->add_count_native}}" name="add_count_native" style="width:25rem;">
                             <i class="fas fa-pen-square" style="margin-left:3rem;margin-top:1rem;font-size: 20px;" role="button" onClick="toogleEdit('add_count_native')"></i>
                         </div>



                     </div>
                     <!-- select -->

                     <label>Advertise Type</label>
                     <select class="form-control" name="addtype_id" id="typeSelectbox">

                         @foreach(App\AddType::all() as $addtype)
                         @if($add->addtype_id==$addtype->id)
                         <option value="{{$addtype->id}}" selected> {{$addtype->name}} </option>
                         @else
                         <option value="{{ $addtype->id}}">{{ $addtype->name}}</option>
                         @endif

                         @endforeach

                     </select>




                 </div>
             </div>

             <div class="form-group float-right">
                 <button type="submit" class="btn btn-info">Update</button>
             </div>

         </form>
     </div>
     <!-- /.card-body -->
 </div>
 <script src="{{ asset('js/toogleInput.js')}}"></script>
 @endsection