if(config==undefined)var config={};
//lista de todos os eventos
config.events=function () {
    return {
        asyncLoader:{
            start:'asyncLoaderStart',
            end:'asyncLoaderEnd'
        }
    };
};
