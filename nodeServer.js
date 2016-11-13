var http = require('http');
var fs=require('fs');
var express = require('express');
var app = express();
var querystring = require('querystring');
var request = require("request");

app.get('/getAllDevice',function(req,res){
var data=fs.readFileSync('indentoryData.csv','utf8');
var deviceArray=data.split("|");
var deviceJsonArray=[];
for(var i=0;i<deviceArray.length-1;i++)
{
	var tempdevice=deviceArray[i];
	var tempdeviceFields=tempdevice.split(",");

	deviceJsonArray.push({
		  deviceState:tempdeviceFields[0].trim(),
            device_name:tempdeviceFields[1],
            model_name:tempdeviceFields[2],  
            os_type:tempdeviceFields[3],
            firmware:tempdeviceFields[4],
			imei_number:tempdeviceFields[5],
            issued_Date:tempdeviceFields[6],
            employee_id:tempdeviceFields[7],
            damge_Description:tempdeviceFields[8]
	});
}

res.send(deviceJsonArray);
})
app.get('/SaveDeviceData',function(req,res){
	
	var deviceData=JSON.parse(req.get('DeviceData'));
	var data='';
	//console.log(deviceData);
	for(var i=0;i<deviceData.length;i++)
	{
		data+=deviceData[i].deviceState.value.trim()+",";
		data+=deviceData[i].device_name+",";
		data+=deviceData[i].model_name+",";
		data+=deviceData[i].os_type+",";
		data+=deviceData[i].firmware+",";
		data+=deviceData[i].imei_number+",";
		data+=deviceData[i].issued_Date+",";
		data+=deviceData[i].employee_id+",";
		data+=deviceData[i].damge_Description+"|";
	}
	fs.writeFile("indentoryData.csv",data,function(output){
		res.send("Done");
	});

})


app.get('/RegisterUser',function(req,res)
{
	var userData = JSON.parse(req.get('UserData'));
	var data='';
	for(var i=0;i<userData.length;i++)
	{
		data+= userData[i].userType.value.trim()+",";
		data+= userData[i].userQuestion.PIVCode.trim()+",";
		data+= userData[i].userPivAnswer.trim()+",";
		data+= userData[i].username.trim()+",";
		data+= userData[i].fullname.trim()+",";
		data+= userData[i].employeeId.trim()+",";
		data+= userData[i].password.trim()+"|";
	}
	
	fs.writeFile("userData.csv",data,function(output){
		res.send("Done");
	});
})

app.get('/getUserData',function(req,res){
var data=fs.readFileSync('userData.csv','utf8');
var userArray=data.split("|");
var userJsonArray=[];
for(var i=0;i<userArray.length-1;i++)
{
	var tempuser=userArray[i];
	var tempuserFields=tempuser.split(",");

	userJsonArray.push({
		userType:tempuserFields[0].trim(),
            userQuestionCode:tempuserFields[1].trim(),   
            userPivAnswer:tempuserFields[2],
			username:tempuserFields[3],
            fullname:tempuserFields[4],
            employeeId:tempuserFields[5],
            password:tempuserFields[6]
	});
}

res.send(userJsonArray);
})


app.post('/SendEmails',function(req,res){


var options = { method: 'POST',
  url: 'https://api.postmarkapp.com/email',
  headers:
   {
     'cache-control': 'no-cache',
     'content-type': 'application/json',
     'x-postmark-server-token': '735f0edf-bc2e-4ed6-9d2f-af8c252c12b3',
     'accept': 'application/json' },
  body:
   { From: '4grj43+8ocyb40cp8wj8@pokemail.net',
     To: req.get("To"),
     Subject: 'string',
     Tag: 'string',
     HtmlBody: req.get("htmlbody"),
     TextBody: 'enigma',
     TrackOpens: true,
     Headers: [ { Name: 'string', Value: 'string' } ] },
  json: true };

request(options, function (error, response, body) {
  if (error) throw new Error(error);

  console.log(body);
});

})

//app.post('/SendEmail',function(req,res)
//{
//	console.log("something here.req ."+ req.get("To"));
//	console.log("something here. res.."+ req.get("htmlbody"));
//
//	var post_data = JSON.stringify({
//	'From':'4grj43+8ocyb40cp8wj8@pokemail.net',
//	'To':req.get("To"),
//	'Subject':'Inventory Data',
//	'HtmlBody':req.get("htmlbody")
//    });
//
//	var options =
//    {
//      host: 'api.postmarkapp.com',
//      port: '80',
//      path: '/email',
//	method:'POST',
////	url:'https://api.postmarkapp.com/email',
//	headers:{
//		'Content-Type':'application/json',
//		'Accept':'application/json',
//
//		'X-Postmark-Server-Token':'735f0edf-bc2e-4ed6-9d2f-af8c252c12b3'
//	        }
//    };
//    console.log("something here...22");
//
//	var post_request=http.request(options,function(response)
//	{
//	console.log("something here...3");
//	var output="";
//
//	response.on('data',function(chunk){
//		output+=chunk;
//	});
//	response.on('end',function()
//	{
//		res.send(output);
//	});
//	console.log("something here...4");
//    });
//    post_request.write(post_data);
//    post_request.end();
//})

app.get('/', function (req, res) {
res.sendFile( __dirname + "/" + "index.html" );
})

app.get('/index.html', function (req, res) {
res.sendFile( __dirname + "/" + "index.html" );
})

app.get('/device.html', function (req, res) {
res.sendFile( __dirname + "/" + "device.html" );
})
app.get('/Sunadmin.html', function (req, res) {
res.sendFile( __dirname + "/" + "Sunadmin.html" );
})

app.get('/register.html', function (req, res) {
res.sendFile( __dirname + "/" + "register.html" );
})
var server = app.listen(5000,function()
{
console.log("server started on 5000");
})




