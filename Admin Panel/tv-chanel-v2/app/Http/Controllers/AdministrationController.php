<?php

namespace App\Http\Controllers;

use App\User;
use Illuminate\Http\Request;

class AdministrationController extends Controller
{


    public function index()
    {
        $users = User::orderBy('id', 'DESC')->get();
        return view('layouts.administration.index', compact('users'));
    }
    public function create()
    {
        return view('layouts.administration.addUser');
    }

    public function store(Request $request)
    {
        $user = new User;
        $user->password = bcrypt($request->password);
        $user->name = $request->name;
        $user->role = $request->role;
        $user->email = $request->email;

        $user->save();



        return redirect()->route('administration.index')->with('message', 'user added');
    }
    public function show($id)
    {
        return null;
    }

    public function edit($id)
    {

        $user = User::find($id);

        return view('layouts.administration.edit', compact('user'));
    }

    public function update(Request $request, User $user)
    {



        if (!isset($request->password)) {

            $user->name = $request->name;
            $user->role = $request->role;
            $user->email = $request->email;
            $user->save();
        } else {
            $user->name = $request->name;
            $user->role = $request->role;
            $user->email = $request->email;
            $user->password = bcrypt($request->password);
            $user->save();
        }

        return redirect()->route('administration.index')->with('message', 'user created');
    }

    public function delete($id)
    {

        $user = User::find($id);
        $user->delete();


        return redirect()->route('administration.index')->with('message', 'user deleted');
    }
}
