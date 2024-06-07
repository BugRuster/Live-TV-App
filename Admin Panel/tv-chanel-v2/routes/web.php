<?php

use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Auth::routes();
Route::middleware('auth')->group(
    function () {
        Route::get('/', 'DashboardController@index');


        Route::get('/admin', function () {
            return view('adminlte.dashboard');
        });




        Route::get('/home', 'DashboardController@index')->name('home');
        Route::resource('category', 'CategoryController');
        Route::resource('product', 'ProductController');
        Route::resource('notification', 'NotificationController');
        Route::resource('slider', 'SliderController');
        Route::resource('administration', 'AdministrationController');
        Route::resource('addvertise', 'AddvertiseController');
        Route::resource('settings', 'SettingsController');


        //product route
        Route::get('/getproduct', 'ProductController@index')->name('getproduct');
        Route::get('/addproduct', 'ProductController@create')->name('addproduct');
        Route::post('/storeproduct', 'ProductController@store')->name('storeproduct');
        Route::get('/delproduct/{id}', 'ProductController@delete')->name('delproduct');
        Route::get('/editproduct/{id}', 'ProductController@edit');

        //category route
        // Route::get('/addcategory', 'CategoryController@create')->name('addcategory');
        Route::post('/storecatagory', 'CategoryController@store')->name('storecatagory');
        Route::get('/delcategory/{id}', 'CategoryController@delete')->name('delcategory');
        Route::get('/ editcategory/{id}', 'CategoryController@edit');
        Route::patch('/updatecategory/{category}', 'CategoryController@update')->name('updatecategory');

        //slider route
        Route::get('/delslider/{id}', 'SliderController@delete')->name('delslider');
        Route::get('/ editslider/{id}', 'SliderController@edit');
        Route::patch('/updateslider/{slider}', 'SliderController@update')->name('updateslider');

        //notification route
        Route::get('/delnotification/{id}', 'NotificationController@delete')->name('delnotification');

        //administration route
        Route::get('/deluser/{id}', 'AdministrationController@delete');
        Route::get('/edituser/{id}', 'AdministrationController@edit');
        Route::patch('/updateuser/{user}', 'AdministrationController@update')->name('updateuser');

        //addvertise route
        Route::patch('/updateadd/{add}', 'AddvertiseController@update')->name('updateadd');
        //settings route
        Route::patch('/updatesettings/{settings}', 'SettingsController@update')->name('updatesettings');

 //send notification
        Route::post('/send-notification', 'NotificationController@send')->name('sendNotification');
    }
);



Route::get('logout', '\App\Http\Controllers\Auth\LoginController@logout');
