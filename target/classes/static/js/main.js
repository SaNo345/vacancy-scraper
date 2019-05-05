/*import Vue from 'vue'

new Vue ({
    el: '#app',
    render:a=>a(App)
});




Vue.component('job-item', {
    props:['job'],
    template: '<div class="col-xs-12"><div class="  job-item">'+
             '<a  v-bind:href="job.link" target="_blank" v-bind:title="job.description" >'+
                '<div>'+
                  '<div class="col-sm-2"><img v-bind:src="job.companyLogo" class="job-logo" alt=""></div>'+
                  '<div class="col-sm-8">'+
                    '<h4>{{job.title}}</h4>'+
                  '</div>'+
                  '<div class="col-sm-2">'+
                    '<span class="location ">Armenia</span>'+
                    '<span class="label label-success">Full-time</span></div></div></a></div></div>'
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
        jobs:[],
        allResult:[],
        resultSize:-1
    },
    watch: {
        jobs: function (val) {
                this.jobs = val;
            if(this.jobs.length <16) {
                $('html, body').animate({
                    scrollTop: $("#result-list").offset().top
                }, 2000);
            }
        },
        allResult:function (val) {
            this.allResult = val;
            //alert(this.allResult.length);
        },
        keyword: function (val) {
            this.keyword = val;
        }
    },
    methods: {
        find: function () {
            const jobsApi = Vue.resource('/scrap/job?keyword='+ this.keyword);
            jobsApi.get().then(result => {this.keyword='';
            result.json().then(data => {this.allResult=[];
                this.allResult=data;
                this.resultSize=data.length;

                if(this.allResult.length >15) {
                    this.jobs=[];
                    for(var i=0;i<15;i++){
                        this.jobs.push(this.allResult[i]);
                        }
                }else {
                    this.jobs = this.allResult;
                }
        })
        })
        },
        browseAll: function () {
            for(var i=15;i<this.allResult.length;i++){
                this.jobs.push(this.allResult[i]);
            }
            this.allResult=[];
        }
    }
});
*/
