var jobsApi = Vue.resource('/scrap/job?keyword=php');
Vue.component('job-item', {
    props:['job'],
    template: '<div class="col-xs-12" >'+
             '<a class="item-block" href="job-detail.html">'+
                '<header>'+
                  '<img src="assets/img/logo-google.jpg" alt="">'+
                  '<div class="hgroup">'+
                    '<h4>{{job.title}}</h4>'+
                    '<h5>{{job.description}}</h5>'+
                  '</div>'+
                  '<div class="header-meta">'+
                    '<span class="location">Menlo park, CA</span>'+
                    '<span class="label label-success">Full-time</span></div></header></a></div>'
});

Vue.component(  'job-list',{
    props:['jobs'],
    template:'<div><job-item v-for="job in jobs"  :job="job" track-by="id" /></div>',
    created:function () {
        jobsApi.get().then(result =>
        result.json().then(data =>
                data.forEach( job => this.jobs.push(job);)
            )
        )
    }
              });

var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello, Vue!',
    jobs:[]
  }
});
