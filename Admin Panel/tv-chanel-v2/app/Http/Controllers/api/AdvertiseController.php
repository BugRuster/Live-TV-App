<?php

namespace App\Http\Controllers\api;

use App\Addvertise;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

class AdvertiseController extends Controller
{
    public function index()
    {

        $add = Addvertise::get()->first();

        if (isset($add)) {
            return response()->json([

                'data' => $add,
            ]);
        } else {
            return response()->json(['message' => 'advertise not available!'], 404);
        }
    }
}
