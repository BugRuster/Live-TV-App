<?php

namespace App\Http\Controllers\api;

use App\Http\Controllers\Controller;
use App\Product;
use Illuminate\Http\Request;

class ProductController extends Controller
{
    public function index()
    {

            $products = Product::leftJoin('categories', 'products.cat_id', 'categories.id')
            ->orderBy('id', 'DESC')
            ->select('products.*', 'categories.cat_name', 'categories.category_type')
            ->get();
        $object = ['data' => $products];
        if (isset($products)) {
            return response()->json($object);
        } else {
            return response()->json(['message' => 'product  data not available!'], 404);
        }
    }



    public function show($id)
    {
        $product = Product::find($id);


        if (isset($product)) {
            $category_name = $product->category->cat_name;
            $category_type = $product->category->category_type;
            $product->category_name = $category_name;
            $product->category_type = $category_type;
        }

        if (isset($product)) {
              return response()->json(['data' => [$product]]);
        } else {
            return response()->json(['message' => 'product not Found!'], 404);
        }
    }

    public function store(Request $request)
    {

        $product = Product::create($request->except('image'));
        if (isset($request->image)) {
            $imageName = time() . '.' . $request->image->extension();
            $path = 'images\prod$product';
            $request->image->move(public_path($path), $imageName);

            $product->image = $path . '/' . $imageName;
        }
        $saved = $product->save();
        if (!$saved) {
            return response()->json(['message' => 'data could not be saved!'], 404);
        } else {
            return response()->json(['message' => 'data is  saved!'], 201);
        }
    }

    public function update(Request $request, Product $product)
    {
        $updated = $product->update($request->all());
        if (!$updated) {
            return response()->json(['message' => 'data could not be updated!'], 404);
        } else {
            return response()->json(['message' => 'data is  updated!'], 201);
        }
    }


    public function delete($id)
    {
        $product = Product::find($id);
        if (!isset($product)) {
            response()->json(['message' => 'product not found'], 201);
        }
        $deleted = $product->delete();
        if ($deleted) {
            return response()->json(['message' => 'product deleted'], 201);
        }
    }

    public function productsBycat($id)
    {

      $product = Product::where('cat_id', $id)
            ->leftJoin('categories', 'products.cat_id', 'categories.id')
            ->select('products.*', 'categories.cat_name', 'categories.category_type')
            ->get();

        return response()->json(['data' => $product]);         
         
    }

  public function search($query)
    {
         $products = Product::leftJoin('categories', 'products.cat_id', 'categories.id')
            ->where('categories.category_type', 1)
            ->where('name', "like", "%" . $query . "%")
            ->select('products.*', 'categories.cat_name', 'categories.category_type')
            ->get();


        // $result = Product::where('name', "like", "%" . $query . "%")->get();

        return response()->json(['data' => $products]);
    }
}
