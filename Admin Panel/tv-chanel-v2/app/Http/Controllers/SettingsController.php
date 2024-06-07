<?php

namespace App\Http\Controllers;

use App\Settings;
use Illuminate\Http\Request;

class SettingsController extends Controller
{
    public function index()
    {
        $settings = Settings::find(1);

        return view('layouts.settings.index', compact('settings'));
    }


    public function update(Request $request, Settings $settings)
    {



        $settings->update($request->all());


        return redirect()->route('settings.index')->with('message', 'settings updated');
    }
}
