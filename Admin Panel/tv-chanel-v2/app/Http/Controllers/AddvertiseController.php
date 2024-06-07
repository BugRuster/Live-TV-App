<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Addvertise;

class AddvertiseController extends Controller
{

    public function index()
    {
        $add = Addvertise::find(1);
        // dd($add);
        return view('layouts.addvertise.index', compact('add'));
    }

    public function update(Request $request, Addvertise $add)
    {

        // dd($add);

        $add->update($request->all());

        // dd($test);
        return redirect()->route('addvertise.index')->with('message', 'addvertise updated');
    }
}
