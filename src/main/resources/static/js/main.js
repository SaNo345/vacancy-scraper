Vue.component('job-item', {
    props:['job'],
    template: '<div class="col-xs-12" >'+
             '<a class="item-block" v-bind:href="job.link" target="_blank" v-bind:title="job.description" >'+
                '<header>'+
                  '<div><img v-bind:src="job.companyLogo" class="job-logo" alt=""></div>'+
                  '<div class="hgroup">'+
                    '<h4>{{job.title}}</h4>'+
                  '</div>'+
                  '<div class="header-meta">'+
                    '<span class="location">Menlo park, CA</span>'+
                    '<span class="label label-success">Full-time</span></div></header></a></div>'
});

Vue.component(  'job-list',{
    props:['jobs'],
    template:'<div id="res"><job-item v-for="job in jobs"  :job="job" :key="job.id"  /></div>'

});



var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello, Vue!',
        keyword:'',
        jobs:[]
    },
    watch: {
        jobs: function (val) {
            console.log("new value");
                //this.jobs =[];
                this.jobs = val;
            $('html, body').animate({
                scrollTop: $("#result-list").offset().top
            }, 2000);
        },
        keyword: function (val) {
            this.keyword = val;
        }
    },
    methods: {
        find: function () {
            const jobsApi = Vue.resource('/scrap/job?keyword='+ this.keyword);
            jobsApi.get().then(result => {
                this.keyword='';
            result.json().then(data => {
                this.jobs=data;
                // console.log(data);
           /*     data.forEach(job => {
                this.jobs.push(job);
        });*/
            console.log(this.jobs);
        })
        })
        }
    }
});
