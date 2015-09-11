var gulp = require('gulp');
var flatten = require('gulp-flatten');
var gulpFilter = require('gulp-filter');
var uglify = require('gulp-uglify');
var minifycss = require('gulp-minify-css');
var rename = require('gulp-rename');
var concat = require('gulp-concat');
var inject = require('gulp-inject');
var es = require('event-stream');
var jshint = require('gulp-jshint');
var browserify = require('gulp-browserify');
var embedlr = require('gulp-embedlr');
var refresh = require('gulp-livereload');
var lrserver = require('tiny-lr')();
var express = require('express');
var livereload = require('connect-livereload');
var mainBowerFiles = require('main-bower-files');
var stylus = require('gulp-stylus');
var path = require('path');
var less = require('gulp-less');
var angularFilesort = require('gulp-angular-filesort');
var del = require('del');
var runSequence = require('run-sequence');
var plumber = require('gulp-plumber');
var livereloadport = 35729;
var serverport = 5000;




// JSHint task
gulp.task('lint', function () {
    return gulp.src('app/scripts/**/*.js')
            .pipe(jshint())
            // You can look into pretty reporters as well, but that's another story
            .pipe(jshint.reporter('default'));
});

gulp.task('less', function () {
    return gulp.src('./app/styles/less/app.less')
            .pipe(less({
                paths: [path.join(__dirname, 'less', 'includes')]
            }))
            .pipe(gulp.dest('dist/css'));
});

gulp.task('bower-files', function () {
    var jsFilter = gulpFilter('*.js', {restore: true});
    var cssFilter = gulpFilter('*.css', {restore: true});
    var fontFilter = gulpFilter(['*.eot', '*.woff', '*.svg', '*.ttf'], {restore: true});

    return gulp.src(mainBowerFiles())


            // grab vendor js files from bower_components, minify and push in /public
            .pipe(jsFilter)
            .pipe(concat('thirdparty.js'))
            .pipe(gulp.dest('dist/lib/'))
            
            // .pipe(uglify())
//            .pipe(rename({
//                suffix: ".min"
//            }))
//            .pipe(gulp.dest('dist/lib/'))
            .pipe(jsFilter.restore)

            // grab vendor css files from bower_components, minify and push in /public
            .pipe(cssFilter)
            .pipe(concat('thirdparty.css'))
            //.pipe(gulp.dest('dist/css/'))
            //  .pipe(minifycss())
//            .pipe(rename({
//                suffix: ".min"
//            }))
            .pipe(gulp.dest('dist/css/'))
            .pipe(cssFilter.restore);

    // grab vendor font files from bower_components and push in /public
//            .pipe(fontFilter)
//            .pipe(flatten())
//            .pipe(gulp.dest('dist/fonts'));


});

gulp.task('views', function () {
    gulp.src('app/index.html')
            .pipe(gulp.dest('dist/'));

    gulp.src('app/views/**/*')
            .pipe(gulp.dest('dist/views/'))
            .pipe(refresh(lrserver)); // Tell the lrserver to refresh
});


// Browserify task
gulp.task('browserify', function () {
    // Single point of entry (make sure not to src ALL your files, browserify will figure it out for you)
    return gulp.src(['app/scripts/app.js'])
            .pipe(angularFilesort())
            .pipe(browserify({
                insertGlobals: true,
                debug: true
            }))

            //  .pipe(uglify())
            // Bundle to a single file
            .pipe(concat('bundle.js'))
            // Output it to our dist folder
            .pipe(gulp.dest('dist/js'));
});

gulp.task('inject', function () {
    var cssFiles = gulp.src('dist/css/*.css')
            .pipe(stylus())
            .pipe(gulp.dest('dist/css/'));

    return gulp.src('dist/index.html')
            .pipe(inject(gulp.src('dist/lib/*.js', {read: false}), {name: 'libs', ignorePath: 'dist/', relative: true}))
            .pipe(inject(es.merge(
                    cssFiles,
                    gulp.src('dist/js/*.js', {read: false})
                    ), {ignorePath: 'dist/', relative: true}))
            .pipe(gulp.dest('dist/'));

});

gulp.task('watch', ['lint'], function () {
    // Watch our scripts
    gulp.watch(['app/scripts/*.js', 'app/scripts/**/*.js'], [
        'lint',
        'browserify', 
        'inject'
    ]);
    gulp.watch(['app/index.html', 'app/views/**/*.html'], ['views', 'inject'], notifyLiveReload);

    gulp.watch('app/styles/less/**/*.less', ['less', 'inject'], notifyLiveReload);
});


gulp.task('express', function () {
    var express = require('express');
    var app = express();
    app.use(require('connect-livereload')({port: 35729}));
    app.use(express.static('dist/'));

    app.listen(5000, '0.0.0.0');
});

var tinylr;
gulp.task('livereload', function () {
    tinylr = require('tiny-lr')();
    tinylr.listen(35729);
});

function notifyLiveReload(event) {
    var fileName = require('path').relative('dist/', event.path);

    tinylr.changed({
        body: {
            files: [fileName]
        }
    });
}

gulp.task('clean', function () {
    return del([
        'dist/'
    ]);
});



// Dev task
gulp.task('build', function (callback) {
    runSequence(
            'clean', 'views', 'browserify', 'less', 'bower-files', 'inject',
            function (error) {
                if (error) {
                    console.log(error.message);
                } else {
                    console.log('RELEASE FINISHED SUCCESSFULLY');
                }
                callback(error);
            });
});

gulp.task('default', ['build', 'express', 'livereload', 'watch'], function () {

});