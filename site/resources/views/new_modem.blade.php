@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row">
            <div class="col-md-9 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">New Modem</div>

                    <div class="panel-body">
                        {!! Form::open(['url' => '/modem/new/submit','files' => true]) !!}
                        {!! Form::label('business_name', 'Business Name: ') !!}
                        {!! Form::text('business_name','', ['required']) !!}

                        {!! Form::hidden('features[0][farsi]', 'نوع مودم') !!}
                        {!! Form::label('features[0][value]', 'Modem Type: ') !!}
                        {!! Form::text('features[0][value]','', ['required']) !!}
                        <br/>
                        {!! Form::label('image', 'Image: ') !!}
                        {!! Form::file('image'); !!}
                        {!! Form::submit('Submit', ['class' => 'btn btn-success']) !!}
                        {!! Form::close() !!}

                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection