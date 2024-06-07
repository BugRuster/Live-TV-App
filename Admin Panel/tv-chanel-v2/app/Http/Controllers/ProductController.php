<?php

namespace App\Http\Controllers;

use App\Product;
use Illuminate\Database\Eloquent\ModelNotFoundException;
use Illuminate\Http\Request;

class ProductController extends Controller
{
    public function index()
    {
        $products = Product::with('category')->orderBy('id', 'DESC')->get();



        return view('layouts.products.index', compact('products'));
    }

    public function create()
    {
        return view('layouts.products.create');
    }


    public function store(Request $request)
    {

        $product = Product::create($request->except('image'));


        if (isset($request->image)) {
            $imageName = time() . '.' . $request->image->extension();
            $path = 'images/products';
            $request->image->move(public_path($path), $imageName);

            $product->image = $path . '/' . $imageName;
            $product->save();
        }


        return redirect()->route('product.index')->with('message', 'product created');
    }

    public function show($id)
    {
        return null;
    }

    public function edit($id)
    {

        $product = Product::find($id);

        return view('layouts.products.edit', compact('product'));
    }

    public function update(Request $request, Product $product)
    {

        if (!isset($request->image)) {
            $product->update($request->except('image'));
        } else {
            $product->update($request->except('image'));
            $imageName = time() . '.' . $request->image->extension();
            $path = 'images/products';
            $request->image->move(public_path($path), $imageName);

            $product->image = $path . '/' . $imageName;
            $product->save();
        }



        return redirect()->route('product.index')->with('message', 'product created');
    }


    public function delete($id)
    {

        $product = Product::find($id);
        if (isset($product->image)) {
            $imgae = $product->image;
            $path = public_path() . '/' . $imgae;
            unlink($path);
        }

        $product->delete();


        return redirect()->route('product.index')->with('message', 'product deleted');
    }


    public function viewProduct($id)
    {
        return view('layouts.products.view');
    }
}
