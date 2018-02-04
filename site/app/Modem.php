<?php

namespace App;

use Jenssegers\Mongodb\Eloquent\Model as Eloquent;

class Modem extends Eloquent {

    /**
     * The database table used by the model.
     *
     * @var string
     */
    protected $collection = 'modems';

    /**
     * The database primary key value.
     *
     * @var string
     */
    protected $primaryKey = '_id';

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'business_name'
    ];

    /**
     * The attributes that should be hidden for arrays.
     *
     * @var array
     */
    protected $hidden = [
        'updated_at', 'created_at', '_token'
    ];
}