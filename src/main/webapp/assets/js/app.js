/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global Integer */

function onLoad() {
    gapi.load('auth2', function () {
        gapi.auth2.init();
    });
}

function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail());
    var id_token = googleUser.getAuthResponse().id_token;
    //       location.replace('./LoginGoogleServlet?email=' + profile.getEmail() + '&id=' + profile.getId() + '&fullname=' + profile.getName());
    var http = new XMLHttpRequest();
    var url = './LoginGoogleServlet';
    var params = 'email=' + profile.getEmail() + '&id=' + profile.getId() + '&fullname=' + profile.getName();
    http.open('POST', url, true);
    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState === 4 && http.status === 200) {
            location.replace('index.jsp');
        }
    };
    http.send(params);
}

 function signOut() {
      var auth2 = gapi.auth2.getAuthInstance();
      auth2.signOut().then(function () {
        console.log('User signed out.');
      });
    }
 
 
function wcqib_refresh_quantity_increments() {
    jQuery("div.quantity:not(.buttons_added), td.quantity:not(.buttons_added)").each(function(a, b) {
        var c = jQuery(b);
        c.addClass("buttons_added"), c.children().first().before('<input type="button" value="-" class="minus" />'), c.children().last().after('<input type="button" value="+" class="plus" />');
    });
}
String.prototype.getDecimals || (String.prototype.getDecimals = function() {
    var a = this,
        b = ("" + a).match(/(?:\.(\d+))?(?:[eE]([+-]?\d+))?$/);
    return b ? Math.max(0, (b[1] ? b[1].length : 0) - (b[2] ? +b[2] : 0)) : 0;
}), jQuery(document).ready(function() {
    wcqib_refresh_quantity_increments();
}), jQuery(document).on("updated_wc_div", function() {
    wcqib_refresh_quantity_increments();
}), jQuery(document).on("click", ".plus, .minus", function() {
    var a = jQuery(this).closest(".quantity").find(".qty"),
        b = parseFloat(a.val()),
        c = parseFloat(a.attr("max")),
        d = parseFloat(a.attr("min")),
        e = a.attr("step");
        console.log(a);
    b && "" !== b && "NaN" !== b || (b = 0), "" !== c && "NaN" !== c || (c = ""), "" !== d && "NaN" !== d || (d = 0), "any" !== e && "" !== e && void 0 !== e && "NaN" !== parseFloat(e) || (e = 1), jQuery(this).is(".plus") ? c && b >= c ? a.val(c) : a.val((b + parseFloat(e)).toFixed(e.getDecimals())) : d && b <= d ? a.val(d) : b > 0 && a.val((b - parseFloat(e)).toFixed(e.getDecimals())), a.trigger("change");
});

function update_sub_quantity(row){
  console.log('Row: ' + row);
  var amount = document.getElementsByClassName('qty')[row-1].value;
  if(amount.value === 1) return;
  amount--;
  var id = document.getElementsByClassName('id')[row-1].value;
  console.log('ID: ' + id);
  var http = new XMLHttpRequest();
  var url = 'UpdateCartServlet';
  var params = 'btAction=Update Cart&id=' + id +'&amount=' + amount;
  http.open('POST', url, true);
  http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState === 4 && http.status === 200) {
            update_total(row);
            document.getElementById('noti').innerHTML = '<a class="btn btn-block c-btn btn-lg c-theme-btn c-font-uppercase c-font-bold c-btn-square m-t-20" href="#">Update Amount Cart Successfully</a>';
        } else{
          document.getElementById('noti').innerHTML = '<a class="btn btn-block c-btn btn-lg btn-danger c-theme-btn c-font-uppercase c-font-bold c-btn-square m-t-20" href="#">Update Amount Cart Error</a>';
        }
    };
    http.send(params);
}

function update_plus_quantity(row){
  console.log('Row: ' + row);
  var amount = document.getElementsByClassName('qty')[row-1].value;
  if(amount.value === 1) return;
  amount++;
 
  var id = document.getElementsByClassName('id')[row-1].value;
  console.log('ID: ' + id);
  var http = new XMLHttpRequest();
  var url = 'UpdateCartServlet';
  var params = 'btAction=Update Cart&id=' + id +'&amount=' + amount;
  http.open('POST', url, true);
  http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState === 4 && http.status === 200) {
            update_total(row);
            document.getElementById('noti').innerHTML = '<a class="btn btn-block c-btn btn-lg c-theme-btn c-font-uppercase c-font-bold c-btn-square m-t-20" href="#">Update Amount Cart Successfully</a>';
        } else{
          document.getElementById('noti').innerHTML = '<a class="btn btn-block c-btn btn-lg btn-danger c-theme-btn c-font-uppercase c-font-bold c-btn-square m-t-20" href="#">Update Amount Cart Error</a>';
        }
    };
    http.send(params);
}

function update_total(row){
  var price = document.getElementsByClassName('price')[row-1].innerHTML;
  
  var amount = document.getElementsByClassName('qty')[row-1].value;
  document.getElementsByClassName('total_1')[row-1].innerHTML = price*amount;
  var dom = document.getElementsByClassName('total_1');
  var sum = 0;
    for (var item of dom) {
        sum += parseInt(item.innerHTML);
    }
    document.getElementsByClassName('total')[0].innerHTML = sum;
    
}

