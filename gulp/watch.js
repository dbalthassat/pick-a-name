'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');

var browserSync = require('browser-sync');

function isOnlyChange(event) {
  return event.type === 'changed';
}

gulp.task('watch', ['scripts:watch', 'inject'], function () {

  gulp.watch([path.join(conf.paths.src, '/*.html'), 'bower.json'], ['inject']);

  gulp.watch([
    path.join(conf.paths.src, '/webapp/**/*.css'),
    path.join(conf.paths.src, '/webapp/**/*.scss')
  ], function(event) {
    console.log(event);
    if(isOnlyChange(event)) {
      gulp.start('styles');
    } else {
      gulp.start('inject');
    }
  });


  gulp.watch(path.join(conf.paths.src, '/webapp/**/*.html'), function(event) {
    browserSync.reload(event.path);
  });
});
