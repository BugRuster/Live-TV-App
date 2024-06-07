<?php

use App\Category;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});


//category api

Route::get('category', 'api\CategoryController@index');
Route::get('category/{id}', 'api\CategoryController@show');
// Route::post('category', 'api\CategoryController@store');
// Route::put('category/{category}', 'CategoryController@update');
// Route::delete('/category/{id}', 'api\CategoryController@delete');


//product api

Route::get('channel', 'api\ProductController@index');
Route::get('channel/{id}', 'api\ProductController@show');
Route::post('channel', 'api\ProductController@store');
Route::get('/channel/category/{category}', 'api\ProductController@productsBycat');
Route::get('channel/search/{query}', 'api\ProductController@search');  //searching
// Route::put('channel/{channel}', 'ProductController@update');
// Route::delete('/channel/{id}', 'api\ProductController@delete');

//slider api
Route::get('slider', 'api\SliderController@index');
Route::get('slider/{id}', 'api\SliderController@show');
// Route::post('slider', 'api\SliderController@store');
// Route::put('slider/{slider}', 'SliderController@update');
// Route::delete('/slider/{id}', 'api\SliderController@delete');


//notification api
Route::get('notification', 'api\NotificationController@index');
Route::get('notification/{id}', 'api\NotificationController@show');
// Route::post('notification', 'api\NotificationController@store');
// Route::put('notification/{notification}', 'NotificationController@update');
// Route::delete('/notification/{id}', 'api\NotificationController@delete');

//settings route
Route::get('settings', 'api\SettingsController@index');

//advertise route
Route::get('advertise', 'api\AdvertiseController@index');

Route::post('store-token', 'api\DeviceController@StoreToken');


//home dialogue url
Route::get('homeDialogueUrl', 'api\HomePageController@homeDialogueUrl');




