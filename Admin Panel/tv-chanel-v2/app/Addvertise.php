<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Addvertise extends Model
{
    protected $guarded = [];

    public function add_type()
    {
        return $this->hasOne(AddType::class, 'id', 'addtype_id');
    }
}
