/**
 * Created by sano on 3/24/19.
 */
let allJobs = [];
let firstJobs = [];
const firstCount = 15;
$( function() {
    $( document ).tooltip();
} );
let find = function () {

    let input = $('#search_keyword');
    let keyword = input.val();
    if (keyword && keyword.length > 0) {
        new_search();
        $.get("/scrap/job?keyword=" + keyword, function (data, status) {
            console.info(status);

            input.val('');
            allJobs = data;
            console.log(allJobs.length);
            if (allJobs && allJobs.length > 0) {
                //display jobs count
                $('#open-count').append('<h4>Open vacancies now  ' + allJobs.length + '</h4>');

                for (let i = 0; i < firstCount; i++) {
                    firstJobs.push(allJobs[i]);
                    let job = allJobs[i];
                    //create job html
                    let jobHtml = generate_html(job);
                    //display job
                    $('#res').append(jobHtml);
                }
                //scroll to jobs
                $('html, body').animate({
                    scrollTop: $("#result-list").offset().top
                }, 1500);


                if (allJobs.length > firstCount) {
                    $('#browse_btn').append(' <button class="btn btn-info" ' +
                        'onclick="browse_all()" ' +
                        'href=""> Browse all jobs ' +
                        ' </button>');
                }
            }
            else {
                $('#open-count').append('<h4>Pleasse try another keywrod </h4>');
                //    alert("Some Error pleasse try again");
            }
        })
    }
};
let browse_all = function () {
    for (let i = firstCount; i < allJobs.length; i++) {
        $('#res').append(generate_html(allJobs[i]));
    }
    $('#browse_btn').empty();
};

$('#search_keyword').on('keyup', function (e) {
    if (e.keyCode == 13) {
        find();
    }
});

let generate_html = function (job) {
    let desq = job.description?job.description:'';
    let str = '<div class="col-xs-12"><div class="  job-item">' +
        '<a  href="' + job.link + '" target="_blank" ' +
        'title="' + desq + '">' +
        '<div>' +
        '<div class="col-sm-2"><img src="' + job.companyLogo + '" class="job-logo" alt=""></div>' +
        '<div class="col-sm-8">' +
        '<h4>' + job.title + '</h4>' +
        '</div>' +
        '<div class="col-sm-2">' +
        '<span class="location">Armenia</span>' +
        '<span class="label label-success">Full-time</span></div></div>' +
        '</a></div></div>';
    return str;
};

let new_search = function () {
    $('#res').empty();
    allJobs = [];
    firstJobs = [];
    $('#browse_btn').empty();
    $('#open-count').empty();
};