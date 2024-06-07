<?php

namespace App\Http\Controllers\api;

use App\Device;
use App\Http\Controllers\Controller;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class DeviceController extends Controller
{
    public function StoreToken(Request $request)
    {

                $validator = Validator::make($request->all(), [
            'packeg_name' => 'required',
            'device_token' => 'required'

        ]);

        if ($validator->fails()) {
            return response()->json(['message' => 'require all details'], 404);
        }

        $saved = Device::create($request->all());

        if ($saved) {
            return response()->json(['message' => 'token saved successfully']);
        } else {
            return response()->json(['message' => 'token could not be saved!'], 404);
        }
    }
}
