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

Route::get('/register', function (){
    abort(403);
});
Route::get('/','ModemController@getAllModems')->name('home');

Auth::routes();

Route::get('/home', 'ModemController@getAllModems')->name('home');
Route::get('/modem/new', 'ModemController@getNewModemView');
Route::post('/modem/new/submit', 'ModemController@submitModem');

Route::get('/modem/spec/{id}', 'ModemController@getModemSpec');
Route::post('/modem/spec/change/{id}', 'ModemController@submitModemChange');

Route::get('/modem/remove/{id}', 'ModemController@removeModem');

//Auth::routes();

//Route::get('/home', 'HomeController@index')->name('home');

Route::get('/modems', 'ModemController@getModems');

Route::get('/modem/get/{id}', 'ModemController@getModem');

Route::get('/modem/delete/{id}', 'ModemController@deleteModem');

Route::post('/modem/add', 'ModemController@addModem');

Route::post('/modem/update', 'ModemController@updateModem');


