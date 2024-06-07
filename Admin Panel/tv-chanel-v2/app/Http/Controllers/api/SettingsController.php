<?php

namespace App\Http\Controllers\api;

use App\Http\Controllers\Controller;
use App\Settings;
use Illuminate\Http\Request;

class SettingsController extends Controller
{
    public function index()
    {

        $settings = Settings::get()->first();





        if (isset($settings)) {
            return response()->json([

                'data' => $settings,
            ]);
        } else {
            return response()->json(['message' => 'settings  data not available!'], 404);
        }
    }
}
