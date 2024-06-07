<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Product extends Model
{

    protected $guarded = [];


    public function category()
    {
        return $this->belongsTo('App\Category', 'cat_id');
    }

    public function url_type()
    {
        return $this->hasOne(Url_type::class, 'id', 'url_id');
    }
}
