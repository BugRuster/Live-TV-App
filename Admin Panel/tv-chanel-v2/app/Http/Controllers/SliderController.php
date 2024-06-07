<?php

namespace App\Http\Controllers;

use App\Slider;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class SliderController extends Controller
{
    public function index()
    {
        $sliders = Slider::paginate(5);
        return view('layouts.sliders.index', compact('sliders'));
    }

    public function create()
    {
        return view('layouts.sliders.create');
    }

    public function store(Request $request)
    {

        $validator = Validator::make($request->all(), [

            'name' => 'required'
        ]);




        $slider = Slider::create($request->except('image'));
        if (isset($request->image)) {
            $imageName = time() . '.' . $request->image->extension();
            $path = 'images/sliders';
            $request->image->move(public_path($path), $imageName);

            $slider->image = $path . '/' . $imageName;
            $slider->save();
        }

        return redirect()->route('slider.index')->with('message', 'slider created');
    }
    public function show($id)
    {
        return null;
    }
    public function edit($id)
    {

        $slider = Slider::find($id);

        return view('layouts.sliders.edit', compact('slider'));
    }

    public function update(Request $request, Slider $slider)
    {

        if (!isset($request->image)) {
            $slider->update($request->except('image'));
        } else {
            $slider->update($request->except('image'));
            $imageName = time() . '.' . $request->image->extension();
            $path = 'images/sliders';
            $request->image->move(public_path($path), $imageName);

            $slider->image = $path . '/' . $imageName;
            $slider->save();
        }



        return redirect()->route('slider.index')->with('message', 'slider created');
    }
    public function delete($id)
    {

        $slider = Slider::find($id);
        if (isset($slider->image)) {
            $imgae = $slider->image;
            $path = public_path() . '/' . $imgae;
            unlink($path);
        }
        $slider->delete();


        return redirect()->route('slider.index')->with('message', 'slider deleted');
    }
}
