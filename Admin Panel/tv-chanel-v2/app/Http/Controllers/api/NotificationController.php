<?php

namespace App\Http\Controllers\api;

use App\Http\Controllers\Controller;
use App\Notification;
use Illuminate\Http\Request;

class NotificationController extends Controller
{
    public function index()
    {

        $notifications = Notification::all();
        if (isset($notifications)) {
            return response()->json(['data' => $notifications]);
        } else {
            return response()->json(['message' => 'slider data not available!'], 404);
        }
    }



    public function show($id)
    {
        $notification = Notification::find($id);

        if (isset($notification)) {
            return response()->json(['data' => $notification]);
        } else {
            return response()->json(['message' => 'notification not Found!'], 404);
        }
    }


    public function store(Request $request)
    {

        $notification = Notification::create($request->except('image'));
        $imageName = time() . '.' . $request->image->extension();
        $path = 'images\notification';
        $request->image->move(public_path($path), $imageName);

        $notification->image = $path . '/' . $imageName;
        $saved = $notification->save();
        if (!$saved) {
            return response()->json(['message' => 'data could not be saved!'], 404);
        } else {
            return response()->json(['message' => 'data is  saved!'], 201);
        }
    }

    public function update(Request $request, Notification $notification)
    {
        $updated = $notification->update($request->all());
        if (!$updated) {
            return response()->json(['message' => 'data could not be updated!'], 404);
        } else {
            return response()->json(['message' => 'data is  updated!'], 201);
        }
    }


    public function delete($id)
    {
        $notification = Notification::find($id);
        if (!isset($Notification)) {
            response()->json(['message' => 'notification not found'], 201);
        }
        $deleted = $notification->delete();
        if ($deleted) {
            return response()->json(['message' => 'notification deleted'], 201);
        }
    }
}
