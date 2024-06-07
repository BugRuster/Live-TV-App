<?php

namespace App\Http\Controllers\api;

use App\Category;
use App\Http\Controllers\Controller;
use App\Product;
use Exception;
use Facade\FlareClient\Http\Response;
use Illuminate\Http\Request;

class CategoryController extends Controller


{

    public function index()
    {

        $categories = Category::all();
        if (isset($categories)) {
            return response()->json(['data' => $categories]);
        } else {
            return response()->json(['message' => 'category data not available!'], 404);
        }
    }



    public function show($id)
    {
        $category = Category::find($id);

        if (isset($category)) {
            return response()->json(['data' => $category]);
        } else {
            return response()->json(['message' => 'category not Found!'], 404);
        }
    }


    public function store(Request $request)
    {

        $category = Category::create($request->except('image'));

        if (isset($request->image)) {
            $imageName = time() . '.' . $request->image->extension();
            $path = 'images\category';
            $request->image->move(public_path($path), $imageName);

            $category->image = $path . '/' . $imageName;
        }

        $saved = $category->save();
        if (!$saved) {
            return response()->json(['message' => 'data could not be saved!'], 404);
        } else {
            return response()->json(['message' => 'data is  saved!'], 201);
        }
    }

    public function update(Request $request, Category $category)
    {
        $updated = $category->update($request->all());
        if (!$updated) {
            return response()->json(['message' => 'data could not be updated!'], 404);
        } else {
            return response()->json(['message' => 'data is  updated!'], 201);
        }
    }


    public function delete($id)
    {
        $category = Category::find($id);
        if (!isset($Category)) {
            response()->json(['message' => 'category not found'], 201);
        }
        $deleted = $category->delete();
        if ($deleted) {
            return response()->json(['message' => 'category deleted'], 201);
        }
    }
}
