<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네이버지도API</title>
<script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=md2hudtp1j&submodules=geocoder"></script>
</head>
<body>
	<H1>네이버지도API</H1>
	<div id="root" style="width:100%;height:400px;"></div>
	<script>
	var mapOptions = {
		 center: new naver.maps.LatLng(37.5679212, 126.9830358),
	     zoom: 11
	};
	var map = new naver.maps.Map("root", mapOptions);
	
	var markerOptions = {
		position : new naver.maps.LatLng(37.5679212, 126.9830358),
		map : map
	}
	var marker = new naver.maps.Marker(markerOptions);
	var content = "<div>인포윈도우</div>"
	var infoWindowOptions = {
			content : content
	}
	var infoWindow = new naver.maps.InfoWindow(infoWindowOptions);
	infoWindow.open(map, marker);
	
	//이벤트 연결1
	naver.maps.Event.addListener(map, "click", function(e) {
		
		//마커 옮기기
		marker.setPosition(e.coord)
		
		//위도,경도로 주소 가져오기
		naver.maps.Service.reverseGeocode({
			location : new naver.maps.LatLng(e.coord.lat(), e.coord.lng())
		}
		, function(status, response) {
			var result = response.result;
			var items = result.items;
			var address = items[1].address;
			console.log(address);
			content = address;
		});
	});
	
	
	//이벤트 연결2
	naver.maps.Event.addListener(marker, "click", function(e){

		if(infoWindow != null) {         //널이 아니고
			if(infoWindow.getMap()) {    //열려있으면
				infoWindow.close();
			}
		}
		
		//마커를 클릭하면 인포윈도우 띄우기
		infoWindow = new naver.maps.InfoWindow({
			content : content
		});
		infoWindow.open(map, marker);
		
	});
	
	
	
	
	
	
	</script>
</body>
</html>