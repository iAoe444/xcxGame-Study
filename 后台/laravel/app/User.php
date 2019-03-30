<?php

namespace App;
use Illuminate\Database\Eloquent\Model;

class User extends Model
{
    protected $table='user';
    protected $primaryKey = 'openid';

    public $timestamps = true;
    public function getDateFormat()
    {
        return time();	//自定义时间戳
    }
    protected function asDateTime($value)
    {
        return $value;
    }
}