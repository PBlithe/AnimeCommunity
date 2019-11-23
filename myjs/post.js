var sendPost = document.getElementById("sendPost");
var closePost = document.getElementById("closePost");
var post = document.getElementById("post");

closePost.onclick = function(){
    console.log("关闭成功");
    post.classList.replace("post-show","post-hide");
};

sendPost.onclick = function(){
    post.classList.replace("post-hide","post-show");
};
