<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateAddvertisesTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('addvertises', function (Blueprint $table) {
            $table->id();
            $table->string('admob_inter');
            $table->string('admob_banner');
            $table->string('admob_native');
            $table->string('admob_appopen');
            $table->string('admob_reward');
            $table->string('fb_inter');
            $table->string('fb_banner');
            $table->string('fb_native');
            $table->string('fb_reward');
            $table->string('appnex_inter');
            $table->string('appnex_banner');

            $table->string('startapp_inter');

            $table->string('startapp_banner');
            $table->integer('addtype_id');


            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('addvertises');
    }
}
