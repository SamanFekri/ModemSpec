<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Modem;
use Illuminate\Support\Facades\Auth;
use Validator;

class ModemController extends Controller
{

    function getModems()
    {
        $modems = Modem::get();
        return response()->json($modems, 200);
    }

    function getModem($id)
    {
        $modem = Modem::find($id);
        if ($modem != null) {
            return response()->json($modem, 200);
        } else {
            return response()->json(['message' => 'Not found'], 404);
        }
    }

    function deleteModem($id)
    {
        $user = Auth::user();
        if ($user != null) {
            $modem = Modem::find($id);
            if ($modem != null) {
                $modem->delete();
                $result = $modem;
                $result['message'] = 'ok';
                return response()->json($result, 200);
            } else {
                return response()->json(['message' => 'Not found'], 404);
            }
        } else {
            return response()->json(['message' => 'forbidden'], 403);
        }
    }

    function addModem(Request $request)
    {
        $user = Auth::user();
        if ($user != null) {
            // data validation
            $validator = Validator::make($request->all(),
                [
                    'business_name' => 'required|string'
                ]);
            if ($validator->fails()) {
                return response()->json(['result' => 'bad request'], 400);
            }
            $data = $validator->valid();
            $data += $validator->invalid();

            // storing data

            if ($request->hasFile('image')) {
                $image = $request->file('image');
                $path = public_path() . '/images/';
                $filename = $data['business_name'] . '_' . time() . '.' . $image->getClientOriginalExtension();
                $image->move($path, $filename);

                $imagepath = $path . $filename;
                $data['image'] = $imagepath;

            }

            $modem = new Modem;
            foreach ($data as $key => $value) {
                if ($key != '_id' || $key != 'image') {
                    $modem->{$key} = $value;
                }
            }
            $modem->save();
            $modem->message = 'ok';
            return response()->json($modem, 200);
        } else {
            return response()->json(['message' => 'forbidden'], 403);
        }
    }


    function updateModem(Request $request)
    {
        $user = Auth::user();
        if ($user != null) {
            // data validation
            $validator = Validator::make($request->all(),
                [
                    '_id' => 'required|string'
                ]);
            if ($validator->fails()) {
                return response()->json(['result' => 'bad request'], 400);
            }

            $data = $validator->valid();
            $data += $validator->invalid();


            $modem = Modem::find($data['_id']);
            if ($modem != null) {
                foreach ($data as $key => $value) {
                    if ($key != '_id' || $key != 'image') {
                        $modem->{$key} = $value;
                    }
                }

                if ($request->hasFile('image')) {
                    $image = $request->file('image');
                    $path = public_path() . '/images/';
                    $filename = $data['business_name'] . '_' . time() . '.' . $image->getClientOriginalExtension();
                    $image->move($path, $filename);

                    $imagepath = $path . $filename;
                    $modem->image = $imagepath;
                }

                $modem->save();

                $modem->message = 'ok';
                return response()->json($modem, 200);
            } else {
                return response()->json(['message' => 'Not found'], 404);
            }
        } else {
            return response()->json(['message' => 'forbidden'], 403);
        }
    }
}
