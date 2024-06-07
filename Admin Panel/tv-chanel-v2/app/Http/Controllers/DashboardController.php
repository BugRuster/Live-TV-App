<?php

namespace App\Http\Controllers;

use App\Category;
use App\Notification;
use App\Product;
use App\Slider;
use Illuminate\Http\Request;

class DashboardController extends Controller
{
    public function index()
    {
        $item_counts = [
            'product' => Product::count(),
            'category' => Category::count(),
            'slider' => Slider::count(),
            'notification' => Notification::count()
        ];

        return view('layouts.dashboard.index', compact('item_counts'));
    }
}
