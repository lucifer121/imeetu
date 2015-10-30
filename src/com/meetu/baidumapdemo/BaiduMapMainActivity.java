package com.meetu.baidumapdemo;

import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.meetu.R;
import com.meetu.baidumapdemo.MyOrientationListener.OnOrientationListener;











import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BaiduMapMainActivity extends Activity {
	private MapView mMapView;
	
	private BaiduMap mBaidumap;
	
	private Context context;
	
	//定位相关
	private LocationClient mLocationClient;
	private MyLocationListener mLocationListener;
	private boolean idFirstIn=true;
	private double mLatitude;
	private double mLongtitude;
	//自定义定位图标
	private BitmapDescriptor mIconLocation;
	private MyOrientationListener myOrientationListener;
	private float mCurrentX;
	
	private LocationMode mLocationMode;
	
	//覆盖物相关
	
	private BitmapDescriptor mMarker;
	private RelativeLayout mMarkerLy;
	
	//控件点击相关
	private Button addressButton;
	private ImageView back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		 //在使用SDK各组件之前初始化context信息，传入ApplicationContext  
        //注意该方法要再setContentView方法之前实现 
        SDKInitializer.initialize(getApplicationContext());  
		setContentView(R.layout.activity_baidumap_main);
		this.context=this;
		initView();
		
		//初始化定位
		initLocation();
		
		initMarkers();
		
		//控件相关
		
		initkongjian();
		
		mBaidumap.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker marker) {
				Bundle extraInfo=marker.getExtraInfo();
				Info info=(Info) extraInfo.getSerializable("info");
				
				ImageView iv = (ImageView) mMarkerLy
						.findViewById(R.id.id_info_img);
				TextView distance = (TextView) mMarkerLy
						.findViewById(R.id.id_info_distance);
				TextView name = (TextView) mMarkerLy
						.findViewById(R.id.id_info_name);
				TextView zan = (TextView) mMarkerLy
						.findViewById(R.id.id_info_zan);
				iv.setImageResource(info.getImgId());
				distance.setText(info.getDistance());
				name.setText(info.getName());
				zan.setText(info.getZan() + ""); 
				
				InfoWindow infoWindow;
				TextView tv=new TextView(context);
				tv.setBackgroundResource(R.drawable.location_tips);
				tv.setPadding(30, 20, 30, 50);
				tv.setText(info.getName());
				tv.setTextColor(Color.parseColor("#ffffff"));
				
				final LatLng latLng = marker.getPosition();
				Point p = mBaidumap.getProjection().toScreenLocation(latLng);
				p.y -= 47;
				LatLng ll = mBaidumap.getProjection().fromScreenLocation(p);
				
				infoWindow=new InfoWindow(mMarker, ll, 47, new OnInfoWindowClickListener() {

					@Override
					public void onInfoWindowClick() {
						// TODO Auto-generated method stub
						mBaidumap.hideInfoWindow();
					}
				});
			
				
				
				mMarkerLy.setVisibility(View.VISIBLE);
				return true;
			 }
		});
		mBaidumap.setOnMapClickListener(new OnMapClickListener() {
			
			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void onMapClick(LatLng arg0) {
				mMarkerLy.setVisibility(View.GONE);
			}
		});
		
	}
	private void initkongjian() {
		addressButton=(Button) super.findViewById(R.id.address_baidumap_detial);
		addressButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addOverlays(Info.infos);
				
			}
		});
		back=(ImageView) super.findViewById(R.id.back_baidumap);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
				
			}
		});
		
	}
	private void initMarkers() {
		mMarker=BitmapDescriptorFactory.fromResource(R.drawable.maker);
		mMarkerLy = (RelativeLayout) findViewById(R.id.id_maker_ly);
		
	}
	private void initLocation() {
		
		mLocationMode=LocationMode.NORMAL;
		mLocationClient=new LocationClient(this);
		mLocationListener=new MyLocationListener();
		mLocationClient.registerLocationListener(mLocationListener);
		
		LocationClientOption option=new LocationClientOption();
		option.setCoorType("bd09ll");
		option.setIsNeedAddress(true);
		option.setOpenGps(true);
		option.setScanSpan(1000);
		mLocationClient.setLocOption(option);
		//初始化图标
		mIconLocation=BitmapDescriptorFactory.fromResource(R.drawable.navi_map_gps_locked);
		
		myOrientationListener=new MyOrientationListener(context);
		myOrientationListener.setmOnOrientationListener(new OnOrientationListener() {
			
			public void OnOrientationChanged(float x) {
				
				mCurrentX=x;
			}
		});
		
	}
	private void initView(){
		mMapView=(MapView) findViewById(R.id.id_bmapView);
		mBaidumap=mMapView.getMap();
		//设置题图刚进去的时候的比例 500米左右 15.0f
		MapStatusUpdate msu=MapStatusUpdateFactory.zoomTo(15.0f);
		mBaidumap.setMapStatus(msu);
		
		
		//设置是否显示缩放控件
		BaiduMapOptions bmp=new BaiduMapOptions();
		bmp.zoomControlsEnabled(false);
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		 //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理  
        mMapView.onDestroy(); 
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
        mMapView.onResume(); 
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
        mMapView.onResume(); 
	}
	
	@Override
	protected void onStart() {
		
		super.onStart();
		//开启定位
		mBaidumap.setMyLocationEnabled(true);
		if(!mLocationClient.isStarted())
			mLocationClient.start();
		//开启方向传感器
		myOrientationListener.start();
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//关闭定位
		mBaidumap.setMyLocationEnabled(false);
		mLocationClient.stop();
		//停止方向传感器
		myOrientationListener.stop();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.baidumapmain, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.id_map_common:
			mBaidumap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
			break;
		case R.id.id_map_site:
			mBaidumap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);	
			break;
		case R.id.id_map_traffic:
			if(mBaidumap.isTrafficEnabled()){
				mBaidumap.setTrafficEnabled(false);
				item.setTitle("实时交通(off)");
			}else{
				mBaidumap.setTrafficEnabled(true);
				item.setTitle("实时交通(on)");
			}
		
			break;
		case R.id.id_map_location:
			centerToMyLocation();
			break;
		case R.id.id_map_mode_common:
			mLocationMode=LocationMode.NORMAL;
			break;
		case R.id.id_map_mode_following:
			mLocationMode=LocationMode.FOLLOWING;
			break;
		case R.id.id_map_mode_compass:
			mLocationMode=LocationMode.COMPASS;
			break;
//		case R.id.id_add_overlay:
//			addOverlays(Info.infos);
//			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	/**
	 * 添加覆盖物
	 * @param infos
	 */
	private void addOverlays(List<Info> infos) {
		mBaidumap.clear();
		LatLng latLng = null;
		Marker marker = null;
		OverlayOptions options;
		for (Info info : infos)
		{
			// 经纬度
			latLng = new LatLng(info.getLatitude(), info.getLongitude());
			// 图标
			options = new MarkerOptions().position(latLng).icon(mMarker)
					.zIndex(5);
			marker = (Marker) mBaidumap.addOverlay(options);
			Bundle arg0 = new Bundle();
			arg0.putSerializable("info", info);
			marker.setExtraInfo(arg0);
		}

		MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
		mBaidumap.setMapStatus(msu);
		
		
	}
	/**
	 * 定位到我的位置
	 */
	private void centerToMyLocation() {
		LatLng latLng=new LatLng(mLatitude, mLongtitude);
		MapStatusUpdate msu=MapStatusUpdateFactory.newLatLng(latLng);
		mBaidumap.animateMapStatus(msu);
	}
	
	private class MyLocationListener implements BDLocationListener{

		@Override
		public void onReceiveLocation(BDLocation location) {
			
			MyLocationData data=new MyLocationData.Builder()//
			.direction(mCurrentX)//
			.accuracy(location.getRadius())//
			.latitude(location.getLatitude())//
			.longitude(location.getLongitude())//
			.build();
			
			mBaidumap.setMyLocationData(data);
			//设置自定义图标
			MyLocationConfiguration config=new 
					MyLocationConfiguration(mLocationMode, true, mIconLocation);
			mBaidumap.setMyLocationConfigeration(config);
			mLatitude=location.getLatitude();
			mLongtitude=location.getLongitude();
			
			if (idFirstIn) {
				LatLng latLng=new LatLng(location.getLatitude(), location.getLongitude());
				MapStatusUpdate msu=MapStatusUpdateFactory.newLatLng(latLng);
				mBaidumap.animateMapStatus(msu);
				idFirstIn=false;
				Toast.makeText(context, location.getAddrStr(), Toast.LENGTH_SHORT).show();
			}
		}
		
	}

}
