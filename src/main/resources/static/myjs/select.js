var locationArr = document.getElementsByClassName("location");
var seasonArr = document.getElementsByClassName("season");
var timeArr = document.getElementsByClassName("time");
var typeArr = document.getElementsByClassName("type");
var anime_location="";
var anime_season="";
var anime_time="";
var anime_type="";

for(var i=0;i<locationArr.length;i++){
    locationArr[i].onclick = function(ev){
        changeLocationFocus(this);
        //getLocation(this.innerHTML);
    }
}
for(var i=0;i<seasonArr.length;i++){
    seasonArr[i].onclick = function(){
        changeSeasonFocus(this);
        //getSeason(this.innerHTML);
    }
}
for(var i=0;i<timeArr.length;i++){
    timeArr[i].onclick = function(){
        changeTimeFocus(this);
        //getTime(this.time);
    }
}
for(var i=0;i<typeArr.length;i++){
    typeArr[i].onclick = function(){
        changeTypeFocus(this);
        //getType(this.type);
    }
}

function getLocation(location){
    anime_location=location;
}
function getSeason(season){
    anime_season=season;
}
function getTime(time){
    anime_time=time;
}
function getType(type){
    anime_type=type;
}

function changeLocationFocus(locationFocus){
    var index = 0;
    for(var i=0;i<locationArr.length;i++){
        if(locationArr[i].classList.contains("myui-this"))
            index = i;
    }
    locationArr[index].classList.remove("myui-this");
    locationFocus.classList.add("myui-this");
}
function changeSeasonFocus(seasonFocus){
    var index=0;
    for(var i=0;i<seasonArr.length;i++){
        if(seasonArr[i].classList.contains("myui-this"))
            index=i;

    }
    seasonArr[index].classList.remove("myui-this");
    seasonFocus.classList.add("myui-this");
}
function changeTimeFocus(timeFocus){
    var index=0;
    for(var i=0;i<timeArr.length;i++){
        if(timeArr[i].classList.contains("myui-this"))
            index=i;
    }
    timeArr[index].classList.remove("myui-this");
    timeFocus.classList.add("myui-this");
}
function changeTypeFocus(typeFocus){
    var index=0;
    for(var i=0;i<typeArr.length;i++){
        if(typeArr[i].classList.contains("myui-this"))
            index=i;
    }
    typeArr[index].classList.remove("myui-this");
    typeFocus.classList.add("myui-this");
}
function getAnime(){
    var datalist = {"anime_location":location,"anime_quarter":season,"anime_time":time,"anime_type":type};
    $.ajax({
        url:"anime_square/get",
        dataType:"json",
        async:true,
        data:JSON.stringify(datalist),
        type:"post",
        success : function(result) {
            console.log(result);
        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}