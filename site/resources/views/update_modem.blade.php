@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row">
            <div class="col-md-9 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">Modem Specification</div>

                    <div class="panel-body" style="direction: rtl">
                        {!! Form::open(['url' => '/modem/spec/change/'.$modem->_id,'files' => true]) !!}
                        <div class="col-md-4" style="float: right; display: block; clear: both;">
                        <img src="{{$modem->image}}" style="width: 100%"
                             onerror="this.src='/images/modem.svg'"/>
                        {!! Form::file('image'); !!}
                        </div>

                        <label for="business_name"  style="display: block; clear: both">نام تجاری: </label>
                        {!! Form::text('business_name',$modem->business_name, ['required', 'class' => 'form-control']) !!}

                        {!! Form::hidden('features[0][farsi]', 'نوع مودم') !!}
                        {!! Form::label('features[0][value]', 'نوع مودم: ') !!}
                        {!! Form::text('features[0][value]'
                        , isset($modem->features[0]['value']) ? $modem->features[0]['value'] :  ''
                        , ['class' => 'form-control']) !!}


                        {!! Form::hidden('features[1][farsi]', 'ابعاد مودم') !!}
                        {!! Form::label('features[1][value]', 'ابعاد مودم: ') !!}
                        {!! Form::text('features[1][value]'
                        , isset($modem->features[1]['value']) ? $modem->features[1]['value'] :  ''
                        , ['class' => 'form-control']) !!}

                        {!! Form::hidden('features[2][farsi]', 'وزن') !!}
                        {!! Form::label('features[2][value]', 'وزن: ') !!}
                        {!! Form::text('features[2][value]'
                        , isset($modem->features[2]['value']) ? $modem->features[2]['value'] :  ''
                        , ['class' => 'form-control']) !!}


                        {!! Form::hidden('features[3][farsi]', 'شبکه های قابل پشتیبانی') !!}
                        {!! Form::label('features[3][value]', 'شبکه های قابل پشتیبانی: ') !!}
                        {!! Form::text('features[3][value]'
                        , isset($modem->features[3]['value']) ? $modem->features[3]['value'] :  ''
                        , ['class' => 'form-control']) !!}


                        {!! Form::hidden('features[4][farsi]', 'فرکانس ها') !!}
                        {!! Form::label('features[4][value]', 'فرکانس ها: ') !!}
                        {!! Form::textarea('features[4][value]'
                        , isset($modem->features[4]['value']) ? $modem->features[4]['value'] :  ''
                        , ['class' => 'form-control']) !!}


                        {!! Form::hidden('features[5][farsi]', 'پشتیبانی از CA') !!}
                        {!! Form::label('features[5][value]', 'پشتیبانی از CA: ') !!}
                        {!! Form::text('features[5][value]'
                        , isset($modem->features[5]['value']) ? $modem->features[5]['value'] :  ''
                        , ['class' => 'form-control']) !!}


                        {!! Form::hidden('features[6][farsi]', 'MIMO پشتیبانی از') !!}
                        {!! Form::label('features[6][value]', 'MIMO پشتیبانی از: ') !!}
                        {!! Form::text('features[6][value]'
                        , isset($modem->features[6]['value']) ? $modem->features[6]['value'] :  ''
                        , ['class' => 'form-control']) !!}


                        {!! Form::hidden('features[7][farsi]', 'MIMO Wi-Fi') !!}
                        {!! Form::label('features[7][value]', 'MIMO Wi-Fi: ') !!}
                        {!! Form::text('features[7][value]'
                        , isset($modem->features[7]['value']) ? $modem->features[7]['value'] :  ''
                        , ['class' => 'form-control']) !!}


                        {!! Form::hidden('features[8][farsi]', 'منبع تغذیه') !!}
                        {!! Form::label('features[8][value]', 'منبع تغذیه: ') !!}
                        {!! Form::text('features[8][value]'
                        , isset($modem->features[8]['value']) ? $modem->features[8]['value'] :  ''
                        , ['class' => 'form-control']) !!}


                        {!! Form::hidden('features[9][farsi]', 'چیپست') !!}
                        {!! Form::label('features[9][value]', 'چیپست: ') !!}
                        {!! Form::text('features[9][value]'
                        , isset($modem->features[9]['value']) ? $modem->features[9]['value'] :  ''
                        , ['class' => 'form-control']) !!}


                        {!! Form::hidden('features[10][farsi]', 'رابط ها') !!}
                        {!! Form::label('features[10][value]', 'رابط ها: ') !!}
                        {!! Form::text('features[10][value]'
                        , isset($modem->features[10]['value']) ? $modem->features[10]['value'] :  ''
                        , ['class' => 'form-control']) !!}


                        {!! Form::hidden('features[11][farsi]', 'کلیدها') !!}
                        {!! Form::label('features[11][value]', 'کلیدها: ') !!}
                        {!! Form::text('features[11][value]'
                        , isset($modem->features[11]['value']) ? $modem->features[11]['value'] :  ''
                        , ['class' => 'form-control']) !!}


                        {!! Form::hidden('features[12][farsi]', 'CAT') !!}
                        {!! Form::label('features[12][value]', 'CAT: ') !!}
                        {!! Form::text('features[12][value]'
                        , isset($modem->features[12]['value']) ? $modem->features[12]['value'] :  ''
                        , ['class' => 'form-control']) !!}


                        {!! Form::hidden('features[13][farsi]', 'پشتیبانی از VoWiFi') !!}
                        {!! Form::label('features[13][value]', 'پشتیبانی از VoWiFi: ') !!}
                        {!! Form::text('features[13][value]'
                        , isset($modem->features[13]['value']) ? $modem->features[13]['value'] :  ''
                        , ['class' => 'form-control']) !!}


                        {!! Form::hidden('features[14][farsi]', 'پشتیبانی از VoLTE') !!}
                        {!! Form::label('features[14][value]', 'پشتیبانی از VoLTE: ') !!}
                        {!! Form::text('features[14][value]'
                        , isset($modem->features[14]['value']) ? $modem->features[14]['value'] :  ''
                        , ['class' => 'form-control']) !!}


                        {!! Form::hidden('features[15][farsi]', 'بیشینه سرعت انتقال داده') !!}
                        {!! Form::label('features[15][value]', 'بیشینه سرعت انتقال داده: ') !!}
                        {!! Form::textarea('features[15][value]'
                        , isset($modem->features[15]['value']) ? $modem->features[15]['value'] :  ''
                        , ['class' => 'form-control']) !!}


                        {!! Form::hidden('features[16][farsi]', 'تعداد کاربران قابل اتصال') !!}
                        {!! Form::label('features[16][value]', 'تعداد کاربران قابل اتصال: ') !!}
                        {!! Form::text('features[16][value]'
                        , isset($modem->features[16]['value']) ? $modem->features[16]['value'] :  ''
                        , ['class' => 'form-control']) !!}


                        {!! Form::hidden('features[17][farsi]', 'اقلام همراه') !!}
                        {!! Form::label('features[17][value]', 'اقلام همراه: ') !!}
                        {!! Form::text('features[17][value]'
                        , isset($modem->features[17]['value']) ? $modem->features[17]['value'] :  ''
                        , ['class' => 'form-control']) !!}


                        {!! Form::hidden('features[18][farsi]', 'نوع سیمکارت') !!}
                        {!! Form::label('features[18][value]', 'نوع سیمکارت: ') !!}
                        {!! Form::text('features[18][value]'
                        , isset($modem->features[18]['value']) ? $modem->features[18]['value'] :  ''
                        , ['class' => 'form-control']) !!}


                        {!! Form::hidden('features[19][farsi]', 'سایر مشخصات') !!}
                        {!! Form::label('features[19][value]', 'سایر مشخصات: ') !!}
                        {!! Form::textarea('features[19][value]'
                         , isset($modem->features[19]['value']) ? $modem->features[19]['value'] :  ''
                         , ['class' => 'form-control']) !!}


                        {!! Form::hidden('features[20][farsi]', 'قیمت') !!}
                        {!! Form::label('features[20][value]', 'قیمت: ') !!}
                        {!! Form::text('features[20][value]'
                         , isset($modem->features[20]['value']) ? $modem->features[20]['value'] :  ''
                         , ['class' => 'form-control']) !!}
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