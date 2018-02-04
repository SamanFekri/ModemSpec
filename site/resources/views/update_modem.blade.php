@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row">
            <div class="col-md-9 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">Modem Specification</div>

                    <div class="panel-body">
                        {!! Form::open(['url' => '/modem/spec/change/'.$modem->_id,'files' => true]) !!}
                        <div class="col-md-4">
                        <img src="{{$modem->image}}" style="width: 100%"
                             onerror="this.src='/images/modem.svg'"/>
                        {!! Form::file('image'); !!}
                        </div>

                        {!! Form::label('business_name', 'Business Name: ') !!}
                        {!! Form::text('business_name',$modem->business_name, ['required']) !!}

                        {!! Form::label('a', 'Business: ') !!}
                        {!! Form::text('a',$modem->a,['class' => 'form-control']) !!}
                        <br/>

                        {!! Form::submit('Submit', ['class' => 'btn btn-info']) !!}

                        <button type="button" class="btn btn-danger"
                                onclick="window.location='/modem/remove/{{$modem->_id}}'"
                                style="margin: 5px;">
                            Delete
                        </button>
                        {!! Form::close() !!}

                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection