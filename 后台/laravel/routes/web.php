<?php

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

//Route::get('/', function () {
//    return view('welcome');
//});
//
//Route::get('test',function() {
//   return 'test1';
//});
//
//Route::post('test2',function(){
//   return 'test2';
//});
//
//Route::match(['get','post'],'test3',function (){
//    return 'test3';
//});
//
//Route::any('test4',function(){
//    return 'test4';
//});

//路由参数
//Route::get('user/{id}',function ($id){
//    return 'UserBackup-'.$id;
//});

//默认值
//Route::get('user/{name?}',function ($name ='sean'){
//    return 'UserBackup-name-'.$name;
//});

//正则表达式验证
//Route::get('user/{name?}',function ($name ='sean'){
//    return 'UserBackup-name-'.$name;
//})->where('name','[a-zA-z]+');

//多个参数
//Route::get('user/{id}/{name?}',function ($id , $name ='sean'){
//    return 'UserBackup-name-'.$name.'id-'.$id;
//})->where(['id'=>'[0-9]+','name'=>'[A-Za-z]+']);

// 路由别名
//Route::get('user/center',['as' => 'center',function() {
//    return route('center');
//}]);

//// 路由群组
//Route::group(['prefix' => 'member'],function(){
//
//    Route::get('user/center',['as' => 'center',function() {
//    return route('center');
//    }]);
//
//});
//
//// 路由中输出视图
//Route::get('welcome', function () {
//    return view('welcome');
//});
Route::any('/user/userOrCreate','UserController@userOrCreate');
Route::any('/studyTime/ranking','StudyTimeController@ranking');