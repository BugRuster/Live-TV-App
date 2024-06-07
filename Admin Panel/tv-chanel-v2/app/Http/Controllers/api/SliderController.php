<?php

namespace App\Http\Controllers\api;

use App\Http\Controllers\Controller;
use App\Slider;
use Illuminate\Http\Request;

class SliderController extends Controller
{
    public function index()
    {

        $sliders = Slider::all();
        if (isset($sliders)) {
            return response()->json(['data' => $sliders]);
        } else {
            return response()->json(['message' => 'slider data not available!'], 404);
        }
    }



    public function show($id)
    {
        $slider = Slider::find($id);

        if (isset($slider)) {
            return response()->json(['data' => $slider]);
        } else {
            return response()->json(['message' => 'slider not Found!'], 404);
        }
    }


    public function store(Request $request)
    {
        if (isset($request->image)) {
            $slider = Slider::create($request->except('image'));
            $imageName = time() . '.' . $request->image->extension();
            $path = 'images\slider';
            $request->image->move(public_path($path), $imageName);

            $slider->image = $path . '/' . $imageName;
        }
        $saved = $slider->save();
        if (!$saved) {
            return response()->json(['message' => 'data could not be saved!'], 404);
        } else {
            return response()->json(['message' => 'data is  saved!'], 201);
        }
    }

    public function update(Request $request, Slider $slider)
    {
        $updated = $slider->update($request->all());
        if (!$updated) {
            return response()->json(['message' => 'data could not be updated!'], 404);
        } else {
            return response()->json(['message' => 'data is  updated!'], 201);
        }
    }


    public function delete($id)
    {
        $slider = Slider::find($id);
        if (!isset($Slider)) {
            response()->json(['message' => 'slider not found'], 201);
        }
        $deleted = $slider->delete();
        if ($deleted) {
            return response()->json(['message' => 'slider deleted'], 201);
        }
    }
}
