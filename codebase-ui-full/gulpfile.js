var gulp = require('gulp');
var uglify = require('gulp-uglify');
var concat = require('gulp-concat');
var es = require('event-stream');


gulp.task('scripts', function () {
    var appJS = gulp.src('app/app.js');
    var controllersJS = gulp.src('app/controllers/*.js');
    var servicesJS = gulp.src('app/services/*.js');
    
    return es.merge(appJS, controllersJS, servicesJS)
            .pipe(concat('codebase.min.js'))
            .pipe(uglify())
            .pipe(gulp.dest('app/dist'));
});

gulp.task('watch', function () {
    gulp.watch('app/*.js', ['scripts']);
});