<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateProductsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('products', function (Blueprint $table) {
            $table->id();
            $table->unsignedBigInteger('cat_id');
            // $table->unsignedBigInteger('url_type');
            // $table->foreign('url_type')->references('id')->on('url_types');
            $table->foreign('cat_id')->references('id')->on('categories')->onDelete('cascade');
            $table->integer('url_id')->nullable();
            $table->string('name');
            $table->string('image')->nullable();
            $table->string('description', 5000)->nullable();
            $table->string('url')->nullable();

            // $table->string('url_type');
            $table->string('user_agent')->nullable();
            $table->string('token')->nullable();
            $table->string('extra')->nullable();
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
        Schema::dropIfExists('products');
    }
}
