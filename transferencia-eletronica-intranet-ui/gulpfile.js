var gulp    = require('gulp');
var replace = require('gulp-replace');
var insert  = require('gulp-insert');

var profile={
    desenvolvimento:{
        url:"http://10.3.3.80:7000"
    },
    homologacao:{
        url:"http://x-oc-httpext12c-hom.sefa.pa.gov.br"
    },
    producao:{
        url:"http://x-oc-httpext12c-hom.sefa.pa.gov.br"
    }
}

gulp.task('default', function() {
  // place code for your default task here
  console.log('gulp funcionando corretamente');
});

gulp.task('desenvolvimento', function(){

  //editar index.html e templates substituindo a origem dos scripts e css

  gulp.src(['WebContent/index.html'])
    .pipe(replace(/<script(.+)src *= *"(.*)\/sefa-ui/g, '<script$1src="'+profile.desenvolvimento.url+'/sefa-ui'))
    .pipe(replace(/<link(.+)href *= *"(.*)\/sefa-ui/g, '<link$1href="'+profile.desenvolvimento.url+'/sefa-ui'))
    .pipe(replace(/<img(.+)src *= *"(.*)\/sefa-ui/g, '<img$1src="'+profile.desenvolvimento.url+'/sefa-ui'))
    .pipe(replace(/<a(.+)href *= *"(.*)\/sefa-ui/g, '<a$1href="'+profile.desenvolvimento.url+'/sefa-ui'))
    .pipe(gulp.dest('WebContent'));
});


gulp.task('homologacao', function(){

  //editar index.html e templates substituindo a origem dos scripts e css

  gulp.src(['WebContent/index.html'])
    .pipe(replace(/<script(.+)src *= *"(.*)\/sefa-ui/g, '<script$1src="'+profile.homologacao.url+'/sefa-ui'))
    .pipe(replace(/<link(.+)href *= *"(.*)\/sefa-ui/g, '<link$1href="'+profile.homologacao.url+'/sefa-ui'))
    .pipe(replace(/<img(.+)src *= *"(.*)\/sefa-ui/g, '<img$1src="'+profile.homologacao.url+'/sefa-ui'))
    .pipe(replace(/<a(.+)href *= *"(.*)\/sefa-ui/g, '<a$1href="'+profile.homologacao.url+'/sefa-ui'));
});

gulp.task('homologacao', function(){

  //editar index.html e templates substituindo a origem dos scripts e css

  gulp.src(['WebContent/index.html'])
    .pipe(replace(/<script(.+)src *= *"(.*)\/sefa-ui/g, '<script$1src="'+profile.producao.url+'/sefa-ui'))
    .pipe(replace(/<link(.+)href *= *"(.*)\/sefa-ui/g, '<link$1href="'+profile.producao.url+'/sefa-ui'))
    .pipe(replace(/<img(.+)src *= *"(.*)\/sefa-ui/g, '<img$1src="'+profile.producao.url+'/sefa-ui'))
    .pipe(replace(/<a(.+)href *= *"(.*)\/sefa-ui/g, '<a$1href="'+profile.producao.url+'/sefa-ui'));
});
