const mix = require('laravel-mix');

 

mix.js('resources/js/app.js', 'public/js/app.js')
    .sass('resources/sass/app.scss', 'public/css');


mix.webpackConfig({
    output: {
      
	publicPath: '/abir/tv-chanel/public/',
    },
});

