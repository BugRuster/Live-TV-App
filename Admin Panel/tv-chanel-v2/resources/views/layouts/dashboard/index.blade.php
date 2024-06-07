 @extends('adminlte.dashboard')



 @section('content')
 <section class="content">
     <div class="container-fluid">
         <!-- Small boxes (Stat box) -->
         <h2 style="margin-bottom: 30px">Dashboard</h2>
         <div class="row">
             <div class="col-lg-3 col-6">
                 <!-- small box -->
                 <div class="small-box bg-info">
                     <div class="inner">
                         <h3>{{$item_counts['category']}}</h3>

                         <p>Categories</p>
                     </div>
                     <div class="icon">
                         <i class="fas fa-chevron-circle-down"></i>
                     </div>
                     <a href="{{route('category.index')}}" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
                 </div>
             </div>
             <!-- ./col -->
             <div class="col-lg-3 col-6">
                 <!-- small box -->
                 <div class="small-box bg-success">
                     <div class="inner">

                         <h3>{{$item_counts['product']}}</h3>


                         <p>Channels</p>
                     </div>
                     <div class="icon">
                         <i class="fas fa-box"></i>
                     </div>
                     <a href="{{route('product.index')}}" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
                 </div>
             </div>
             <!-- ./col -->
             <div class="col-lg-3 col-6">
                 <!-- small box -->
                 <div class="small-box bg-warning">
                     <div class="inner">
                         <h3>{{$item_counts['slider']}}</h3>
                         <p>Sliders</p>
                     </div>
                     <div class="icon">
                         <i class="fas fa-images"></i>
                     </div>
                     <a href="{{route('slider.index')}}" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
                 </div>
             </div>
             <!-- ./col -->
             <div class="col-lg-3 col-6">
                 <!-- small box -->
                 <div class="small-box bg-danger">
                     <div class="inner">
                         <h3>{{$item_counts['notification']}}</h3>

                         <p>Notifications</p>
                     </div>
                     <div class="icon">
                         <i class="far fa-bell"></i>
                     </div>
                     <a href="{{route('notification.index')}}" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
                 </div>
             </div>
         </div>
     </div>

 </section>

 @endsection