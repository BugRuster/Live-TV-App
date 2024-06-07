<?php

namespace App\Http\Controllers\api;

use App\Http\Controllers\Controller;
use App\Settings;
use Illuminate\Http\Request;

class HomePageController extends Controller
{
    public function homeDialogueUrl()
    {
        //get itmes for first row in settigns table

        $setting = Settings::first();
        $home_dialogue_url = $setting->home_dialogue_link;
        $home_dialogue_visibility = $setting->visibility_home_dialogue;
        return response()->json([
            'url' => $home_dialogue_url,
            'visibility' => $home_dialogue_visibility
        ]);
    }
}
