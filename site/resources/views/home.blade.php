@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row">
            <div class="col-md-9 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Modems

                        <button type="button" class="btn btn-success col-md-3"
                                onclick="window.location = '/modem/new'"
                                style="position: absolute; right: 50px; margin-top: 10px;">
                            Add Modemst
                        </button>
                    </div>

                    <div class="panel-body">
                        {{--@if (session('status'))--}}
                        {{--<div class="alert alert-success">--}}
                        {{--{{ session('status') }}--}}
                        {{--</div>--}}
                        {{--@endif--}}
                        @foreach($modems as $modem)
                            <div class="col-md-3 panel panel-default" style="padding: 0;">
                                <a href="/modem/spec/{{$modem->_id}}" style="{color: #aaaaaa}">
                                    <div class="panel-body">
                                        <img src="{{$modem->image}}" style="width: 100%; height: 100%"
                                             onerror="this.src='/images/modem.svg'"/>
                                        <p style="border-top: 1px solid #dddddd;text-align: center; margin: 0; padding-top: 5px">
                                            {{ $modem->business_name }}
                                        </p>
                                    </div>
                                </a>
                            </div>
                        @endforeach
                    </div>

                </div>
            </div>
        </div>
    </div>
@endsection
