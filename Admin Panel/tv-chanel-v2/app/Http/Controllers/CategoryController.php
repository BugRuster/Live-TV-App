<?php

namespace App\Http\Controllers;

use App\Category;

use Illuminate\Http\Request;


class CategoryController extends Controller
{
    public function index()
    {
        $categories = Category::orderBy('id', 'DESC')->get();
        return view('layouts.category.index', compact('categories'));
    }

    public function create()
    {
        return view('layouts.category.add');
    }





    public function store(Request $request)
    {


        $category = new Category;

        $category->cat_name = $request->cat_name;
        $category->category_type = $request->category_type;

        if ($request->category_type == 1) {
            $category->pass = "";
        } else {
            $category->pass = $request->pass;
        }

	 

        // $category = Category::create($request->except('image'));



        if (isset($request->image)) {
            $imageName = time() . '.' . $request->image->extension();
            $path = 'images/category';
            $request->image->move(public_path($path), $imageName);

            $category->image = $path . '/' . $imageName;
            $category->save();
        } else {
            $category->save();
        }

        return redirect()->route('category.index')->with('message', 'category created');
    }

    public function show($id)
    {
        return null;
    }

    public function edit($id)
    {

        $category = Category::find($id);

        return view('layouts.category.edit', compact('category'));
    }

    public function update(Request $request, Category $category)
    {

        $category->cat_name = $request->cat_name;
        $category->category_type = $request->category_type;

        if ($request->category_type == 1) {
            $category->pass = "";
        } else {
            $category->pass = $request->pass;
        }


        if (isset($request->image)) {
            $category->update($request->except('image'));
            $imageName = time() . '.' . $request->image->extension();
            $path = 'images/category';
            $request->image->move(public_path($path), $imageName);

            $category->image = $path . '/' . $imageName;
            $category->save();
        } else {
            $category->save();
        }

        return redirect()->route('category.index')->with('message', 'product created');
    }

    public function delete($id)
    {

        $category = Category::find($id);
        if (isset($category->image)) {
            $imgae = $category->image;
            $path = public_path() . '/' . $imgae;
            unlink($path);
        }
        $category->delete();


        return redirect()->route('category.index')->with('message', 'category deleted');
    }
}
