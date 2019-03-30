<?php

namespace App\Http\Controllers;

use App\User;
use Illuminate\Http\Request;

class UserController extends Controller
{
    public function userOrCreate(Request $request)
    {
        $openid = $request->input('openid');
        $username = $request->input('username');
        $User = User::find($openid);
        if(isset($User))
            return response()->json(['result'=>'exist']);
        elseif (isset($username)){
            $newUser = new User();
            $newUser->openid = $openid;
            $newUser->username = $username;
            $bool = $newUser->save();
            if($bool)
                return response()->json(['result'=>'success']);
            else
                return response()->json(['result'=>'fail']);
        }
    }
}